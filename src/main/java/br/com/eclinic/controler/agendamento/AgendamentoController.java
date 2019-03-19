package br.com.eclinic.controler.agendamento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.agenda.Agenda;
import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.agendamento.AgendamentoDTO;
import br.com.eclinic.entity.agendamento.StatusAgendamentoEnum;
import br.com.eclinic.entity.agendamento.StatusPagamentoEnum;
import br.com.eclinic.entity.agendamento.TipoAgendamentoConsultaEnum;
import br.com.eclinic.entity.agendamento.TipoAgendamentoEnum;
import br.com.eclinic.entity.agendamento.TurnoAgendamentoEnum;
import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.paciente.PacienteDTO;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.agenda.AgendaService;
import br.com.eclinic.service.agendamento.AgendamentoService;
import br.com.eclinic.service.evento.EventoService;
import br.com.eclinic.service.exame.ExameService;
import br.com.eclinic.service.jornada.JornadaService;
import br.com.eclinic.service.medico.MedicoService;
import br.com.eclinic.service.paciente.PacienteService;
import br.com.eclinic.service.plano.PlanoService;
import br.com.eclinic.validator.AgendamentoValidator;

@SuppressWarnings({ "unused" })
@Controller
public class AgendamentoController extends EclinicController {

	private static final String HORA_INICIAL = "00:00:00";
	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static final String MODEL_ATTR_ENTIDADE = "agendamento";
	private static final String URL_REQUEST_LISTA = "/agendamentos";
	private static final String URL_REQUEST_SALVAR = "/agendamento/salvar";
	private static final String URL_REQUEST_EXCLUIR = "/agendamento/excluir";
	private static final String URL_REQUEST_ALTERAR = "/agendamento/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/agendamento/visualizar";

	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private MedicoService medicoService;
	@Autowired
	private PlanoService planoService;
	@Autowired
	private ExameService exameService;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private JornadaService jornadaService;
	@Autowired
	private AgendaService agendaService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String exibirLista(Locale locale, Model model, HttpServletRequest request) throws Exception {

		return listar(locale, model, new Agendamento(), request);
	}

	@RequestMapping(value = "/agendamento/incluir_horario", method = RequestMethod.POST)
	@ResponseBody
	public AgendamentoPacienteDTO salvarAgendamento(@RequestBody AgendamentoPacienteDTO agendamentoPacienteDTO, HttpServletRequest request) {
		
		Agendamento agendamento = new Agendamento();
		String h = "";
		String m = "";
		
		agendamento.setNomePacienteNaoCadastrado(agendamentoPacienteDTO.getNomePaciente());
		Date data;
		try {
			data = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(agendamentoPacienteDTO.getDataInicio());
			agendamento.setDataConsulta(data);
			agendamento.setHoraConsulta(data);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(agendamento.getHoraConsulta());
			int hora = calendar.get(Calendar.HOUR_OF_DAY);
			int minuto = calendar.get(Calendar.MINUTE);
			
			if(hora < 10) {
				h = "0" + hora;
			} else {
				h = String.valueOf(hora);
			}
			
			if(minuto < 10) {
				m = "0" + minuto;
			} else {
				m = String.valueOf(minuto);
			}
			
			agendamento.setHora(h + ":" + m);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		agendamento.setCliente(HomeController.getUsuarioLogado(request).getCliente());
		agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.AGUARDANDO);
		agendamento.setTipoAgendamentoEnum(TipoAgendamentoEnum.PRESENCIAL);
		agendamento.setStatusPagamentoEnum(StatusPagamentoEnum.PAGO);
		if (agendamentoPacienteDTO.getIdMedico() != null) {
			Medico medico;
			try {
				medico = medicoService.buscar(agendamentoPacienteDTO.getIdMedico());
				agendamento.setMedico(medico);
			} catch (NegocioException e) {
				e.printStackTrace();
			}
		}

		agendamentoService.salvar(agendamento);

		return agendamentoPacienteDTO;
	}
	
	@RequestMapping(value = "/agendamento/alterar_horario", method = RequestMethod.POST)
	@ResponseBody
	public AgendamentoPacienteDTO alterarAgendamento(@RequestBody AgendamentoPacienteDTO agendamentoPacienteDTO, HttpServletRequest request) {
		
		if(agendamentoPacienteDTO.getIdAgendamento() != null) {
			String h = "";
			String m = "";
			Agendamento agendamentoBanco = agendamentoService.buscar(new Long(agendamentoPacienteDTO.getIdAgendamento()));
			try {
				Date data = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(agendamentoPacienteDTO.getDataInicio());
				agendamentoBanco.setDataConsulta(data);
				agendamentoBanco.setHoraConsulta(data);
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(agendamentoBanco.getHoraConsulta());
				int hora = calendar.get(Calendar.HOUR_OF_DAY);
				int minuto = calendar.get(Calendar.MINUTE);
				
				if(hora < 10) {
					h = "0" + hora;
				} else {
					h = String.valueOf(hora);
				}
				
				if(minuto < 10) {
					m = "0" + minuto;
				} else {
					m = String.valueOf(minuto);
				}
				
				agendamentoBanco.setHora(h + ":" + m);
				agendamentoBanco.setStatusAgendamentoEnum(StatusAgendamentoEnum.AGUARDANDO);
				agendamentoBanco.setStatusPagamentoEnum(StatusPagamentoEnum.PAGO);
				agendamentoBanco.setTipoAgendamentoEnum(TipoAgendamentoEnum.PRESENCIAL);
				
				agendamentoService.alterar(agendamentoBanco);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return agendamentoPacienteDTO;
	}

	@RequestMapping(value = "/calendarios", method = RequestMethod.GET)
	public String exibirListaCalendarios(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("agendamentoDTO", new AgendamentoDTO());
		model.addAttribute("medicos", medicoService.listar());

		return "calendarios";
	}

	@RequestMapping(value = "/calendarios", method = RequestMethod.POST)
	public String salvarAgendamentoMedico(@ModelAttribute AgendamentoDTO agendamentoDTO, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		AgendamentoValidator validator = new AgendamentoValidator();
		validator.validateDTO(agendamentoDTO, result);

		if (result.hasErrors()) {
			model.addAttribute("agendamentoDTO", agendamentoDTO);
			model.addAttribute("medicos", medicoService.listar());
			return "calendarios";
		}

		Agendamento agendamento = new Agendamento();

		agendamento.setNomePacienteNaoCadastrado(agendamentoDTO.getNomePacienteSemCadastro());
		agendamento.setTelefonePacienteNaoCadastrado(agendamentoDTO.getTelefonePaciente());
		agendamento.setCpfPacienteNaoCadastrado(agendamentoDTO.getCpfPacienteSemCadastro());
		agendamento.setRgPacienteNaoCadastrado(agendamentoDTO.getRgPacienteSemCadastro());

		if (agendamentoDTO.getDataConsultaFormatada() != null && agendamentoDTO.getDataConsultaFormatada() != "") {
			Date data = new SimpleDateFormat("yyyy-MM-dd").parse(agendamentoDTO.getDataConsultaFormatada());
			agendamento.setDataConsulta(data);
		}

		agendamento.setHora(agendamentoDTO.getHoraConsultaFormatada());

		if (agendamentoDTO.getHoraConsultaFormatada() != null && agendamentoDTO.getHoraConsultaFormatada() != "") {
			GregorianCalendar horario = new GregorianCalendar();
			String horas = agendamentoDTO.getHoraConsultaFormatada().substring(0, 2);
			String minutos = agendamentoDTO.getHoraConsultaFormatada().substring(3, 5);
			horario.setTime(agendamento.getDataConsulta());
			horario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horas));
			horario.set(Calendar.MINUTE, Integer.parseInt(minutos));
			horario.set(Calendar.SECOND, 0);

			agendamento.setHoraConsulta(horario.getTime());
		}
		agendamento.setCliente(HomeController.getUsuarioLogado(request).getCliente());
		agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.AGUARDANDO);
		agendamento.setTipoAgendamentoEnum(TipoAgendamentoEnum.PRESENCIAL);
		agendamento.setStatusPagamentoEnum(StatusPagamentoEnum.PAGO);
		if (agendamentoDTO.getMedico().getId() != null) {
			Medico medico = medicoService.buscar(agendamentoDTO.getMedico().getId());
			agendamento.setMedico(medico);
		}

		agendamentoService.salvar(agendamento);

		model.addAttribute("agendamentoDTO", new AgendamentoDTO());
		model.addAttribute("medicos", medicoService.listar());
		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("agendamento.inclusao.sucesso"));
		return "redirect:/calendarios";
	}

	@ResponseBody
	@RequestMapping(value = "/medico/jornada/{id}", method = RequestMethod.GET)
	public List<String> getJornadaMedico(@PathVariable String id, @RequestParam Date data, HttpServletRequest request)
			throws Exception {

		Medico medico = medicoService.buscar(new Long(id));
		List<Agendamento> agendamentos = agendamentoService.consultarAgendamentoPorMedico(medico);
		List<Jornada> jornadas = jornadaService.pesquisarPorMedico(medico.getId());
		List<String> horarios = new ArrayList<String>();
		medico.setJornadas(jornadas);
		Agenda agenda = agendaService.buscar(medico.getAgenda().getId());
		medico.setAgenda(agenda);

		boolean verificado = medicoService.diaDisponivel(medico, data);

		if (verificado == true) {
			horarios = medicoService.horariosDeAgendamentoDisponiveis(medico, agendamentos, data);
		} else {
			horarios.add("O medico não trabalha esse dia");
		}

		return horarios;
	}

	@ResponseBody
	@RequestMapping(value = "/evento/buscarMedico/{idMedico}", method = RequestMethod.GET)
	public Medico buscarMedicoPorId(@PathVariable String idMedico, HttpServletRequest request, Model model)
			throws NumberFormatException, NegocioException {

		Medico medicoBanco = medicoService.buscar(new Long(idMedico));
		List<Agendamento> agendamentos = agendamentoService.consultarAgendamentoPorMedico(medicoBanco);
		List<Jornada> jornadas = jornadaService.pesquisarPorMedico(medicoBanco.getId());
		medicoBanco.setAgendamentos(agendamentos);
		medicoBanco.setJornadas(jornadas);
		medicoBanco.setCliente(null);
		medicoBanco.setConsultas(null);
		medicoBanco.setResponsavel(null);
		return medicoBanco;
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String exibirLista(@ModelAttribute Agendamento agendamento, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return listar(locale, model, agendamento, request);
	}

	private String listar(Locale locale, Model model, Agendamento agendamentoPesquisa, HttpServletRequest request)
			throws ParseException {
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		agendamentos = agendamentoService.consultarAgendamentoPorFiltro(agendamentoPesquisa,
				usuarioLogado.getCliente());

		configurarPaginacao(agendamentos, model);
		model.addAttribute("tipos", TipoAgendamentoEnum.values());
		model.addAttribute("tiposConsulta", TipoAgendamentoConsultaEnum.values());
		model.addAttribute("turnosConsulta", TurnoAgendamentoEnum.values());
		model.addAttribute("agendamento", agendamentoPesquisa);
		model.addAttribute("agendamentos", getPagedListHolder().getPageList());

		return "agendamentos";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new Agendamento());
		return "agendamento_incluir";
	}

	@RequestMapping(value = "/agendamento/pacientes", method = RequestMethod.GET)
	public String exibirFiltroPacientes(Locale locale, Model model, HttpServletRequest request) throws Exception {

		listarPaciente(new Paciente(), locale, model, request);
		return "agendamento.paciente";
	}

	@RequestMapping(value = "/agendamento/selecionar/paciente", method = RequestMethod.POST)
	public String selecionarPoste(@ModelAttribute Paciente paciente, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		if (paciente == null || paciente.getId() == null) {
			montarForm(model, new Agendamento(), request);
			return "agendamento.incluir";
		} else {
			Agendamento agendamento = new Agendamento();
			paciente = pacienteService.buscar(paciente.getId());
			agendamento.setPaciente(paciente);
			montarForm(model, agendamento, request);
			return "agendamento.incluir";
		}
	}

	private void montarForm(Model model, Agendamento agendamento, HttpServletRequest request) {
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.AGUARDANDO);

		model.addAttribute(MODEL_ATTR_ENTIDADE, agendamento);
		model.addAttribute("medicos", medicoService.listar());
		model.addAttribute("tiposAgendamentoConsulta", TipoAgendamentoConsultaEnum.values());
		model.addAttribute("turnosConsulta", TurnoAgendamentoEnum.values());
		model.addAttribute("tiposAgendamento", TipoAgendamentoEnum.values());
		model.addAttribute("exames", exameService.listarPorCliente(usuarioLogado.getCliente()));

	}

	private void montarForm(ModelAndView model, Agendamento agendamento, HttpServletRequest request) {
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.AGUARDANDO);

		model.addObject(MODEL_ATTR_ENTIDADE, agendamento);
		model.addObject("medicos", medicoService.listarPorCliente(usuarioLogado.getCliente()));
		model.addObject("planos", planoService.listar());
		model.addObject("turnosConsulta", TurnoAgendamentoEnum.values());
		model.addObject("tiposAgendamentoConsulta", TipoAgendamentoConsultaEnum.values());
		model.addObject("tiposAgendamento", TipoAgendamentoEnum.values());
		model.addObject("exames", exameService.listarPorCliente(usuarioLogado.getCliente()));

	}

	@RequestMapping(value = "/agendamento/pacientes", method = RequestMethod.POST)
	public String exibirFiltroPoste(@ModelAttribute Paciente paciente, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		listarPaciente(paciente, locale, model, request);
		return "agendamento.paciente";
	}

	private String listarPaciente(Paciente pacientePesquisa, Locale locale, Model model, HttpServletRequest request) {

		List<PacienteDTO> pacientes = new ArrayList<PacienteDTO>();
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		if (pacientePesquisa.getNome() != null || pacientePesquisa.getCpf() != null
				|| pacientePesquisa.getRg() != null) {
			pacientes = pacienteService.consultarDTO(pacientePesquisa, usuarioLogado.getCliente());
		} else {
			pacientes = pacienteService.listarPorClienteDTO(usuarioLogado.getCliente());
		}

		configurarPaginacao(pacientes, model);
		model.addAttribute("paciente", pacientePesquisa);
		model.addAttribute("pacientes", getPagedListHolder().getPageList());

		return "agendamento.paciente";
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{idAgendamento}", method = RequestMethod.GET)
	public String alterarAgendamento(Locale locale, Model model, @PathVariable Long idAgendamento,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		Agendamento agendamento = agendamentoService.buscar(idAgendamento);
		if (agendamento != null && agendamento.getId() != null) {
			agendamento.setIdStatusAgendamentoTransiente(
					String.valueOf(agendamento.getStatusAgendamentoEnum().getCodigo()));
			model.addAttribute("agendamento", agendamento);
			model.addAttribute("medicos", medicoService.listarPorCliente(usuarioLogado.getCliente()));
			return "agendamento_alterar";
		} else {
			return "redirect:/agendamento";
		}

	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{idAgendamento}", method = RequestMethod.GET)
	public String visualizarAgendamento(Locale locale, Model model, @PathVariable Long idAgendamento,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		Agendamento agendamento = agendamentoService.buscar(idAgendamento);

		if (agendamento != null && agendamento.getId() != null) {

			if (agendamento.getMedico().getId() != null) {
				Medico medico = medicoService.buscar(agendamento.getMedico().getId());
				agendamento.setMedico(medico);
			}
			if (agendamento.getPaciente().getId() != null) {
				Paciente paciente = pacienteService.buscar(agendamento.getPaciente().getId());
				agendamento.setPaciente(paciente);
			}
			model.addAttribute("agendamento", agendamento);
			return "agendamento_visualizar";
		} else {
			return "redirect:/agendamentos";
		}

	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public String salvar(Locale locale, Model model, @ModelAttribute Agendamento agendamento, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		AgendamentoValidator validator = new AgendamentoValidator();
		validator.validate(agendamento, result);

		if (result.hasErrors()) {
			model.addAttribute("agendamento", agendamento);
			montarForm(model, agendamento, request);
			return "agendamento.incluir";
		} else {

			configurarAgendamento(agendamento);

			if (agendamento.getHora() != null && agendamento.getHora() != "") {
				GregorianCalendar horario = new GregorianCalendar();
				String horas = agendamento.getHora().substring(0, 2);
				String minutos = agendamento.getHora().substring(3, 5);
				horario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horas));
				horario.set(Calendar.MINUTE, Integer.parseInt(minutos));
				horario.set(Calendar.SECOND, 0);

				agendamento.setHoraConsulta(horario.getTime());
			}
			agendamento.setCliente(HomeController.getUsuarioLogado(request).getCliente());
			agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.AGUARDANDO);
			agendamento.setTipoAgendamentoEnum(TipoAgendamentoEnum.PRESENCIAL);
			if (agendamento.getMedico().getId() != null) {
				Medico medico = medicoService.buscar(agendamento.getMedico().getId());
				agendamento.setMedico(medico);
			}

			salvarAlterar(agendamento);

			configurarModelSalvarAlterar(model, request);

			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("agendamento.inclusao.sucesso"));
			return "redirect:/agendamentos";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public String alterar(Locale locale, Model model, @ModelAttribute Agendamento agendamento, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		AgendamentoValidator validator = new AgendamentoValidator();
		validator.validate(agendamento, result);

		if (result.hasErrors()) {
			model.addAttribute("agendamento", agendamento);
			montarForm(model, agendamento, request);
			return "agendamento_alterar";
		} else {

			configurarAgendamento(agendamento);

			if (agendamento.getHora() != null && agendamento.getHora() != "") {
				GregorianCalendar horario = new GregorianCalendar();
				String horas = agendamento.getHora().substring(0, 2);
				String minutos = agendamento.getHora().substring(3, 5);
				horario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horas));
				horario.set(Calendar.MINUTE, Integer.parseInt(minutos));
				horario.set(Calendar.SECOND, 0);

				agendamento.setHoraConsulta(horario.getTime());
			}

			agendamento.setCliente(HomeController.getUsuarioLogado(request).getCliente());
			agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.AGUARDANDO);

			salvarAlterar(agendamento);

			configurarModelSalvarAlterar(model, request);

			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("agendamento.alteracao.sucesso"));
			return "redirect:/agendamentos";
		}
	}

	private void configurarModelSalvarAlterar(Model model, HttpServletRequest request) throws ParseException {
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		List<Agendamento> agendamentos = agendamentoService.consultarAgendamentoPorFiltro(null,
				usuarioLogado.getCliente());
		configurarPaginacao(agendamentos, model);

		Agendamento novoAgendamento = new Agendamento();

		model.addAttribute("agendamento", novoAgendamento);
		model.addAttribute("status", StatusAgendamentoEnum.values());
		model.addAttribute("tipos", TipoAgendamentoEnum.values());
		model.addAttribute("tiposConsulta", TipoAgendamentoConsultaEnum.values());
		model.addAttribute("turnosConsulta", TurnoAgendamentoEnum.values());
		model.addAttribute("agendamentos", getPagedListHolder().getPageList());
	}

	private void configurarAgendamento(Agendamento agendamento) throws ParseException {
		Date data = new SimpleDateFormat("yyyy-MM-dd").parse(agendamento.getDataConsultaFormatada());
		agendamento.setDataConsulta(data);
	}

	private void salvarAlterar(Agendamento agendamento) throws NegocioException {
		if (agendamento.getPaciente() == null || agendamento.getPaciente().getId() == null
				|| agendamento.getPaciente().getId() == 0) {
			agendamento.setPaciente(null);
		}
		if (agendamento.getId() == null) {
			agendamentoService.salvar(agendamento);
		} else {
			agendamentoService.alterar(agendamento);
		}

	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Agendamento agendamento, Model model,
			final RedirectAttributes redirectAttributes) {

		try {

			Agendamento agendamentoCancelar = agendamentoService.buscar(agendamento.getId());

			agendamentoCancelar.setStatusAgendamentoEnum(StatusAgendamentoEnum.CANCELADO);
			agendamentoService.alterar(agendamentoCancelar);
			redirectAttributes.addFlashAttribute(MESSAGE, "Agendamento Cancelado com Sucesso");
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}

		return "redirect:/agendamentos";
	}

	@ResponseBody
	@RequestMapping(value = "agendamento/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("agendamentos", getPagedListHolder().getPageList());
		model.addAttribute("agendamento", new Agendamento());
		return new ModelAndView("lista.agendamento");
	}

	@ResponseBody
	@RequestMapping(value = "agendamento/paciente/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacaoAgendamento(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("pacientes", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Paciente());
		return new ModelAndView("paciente.lista");
	}

	@ResponseBody
	@RequestMapping(value = "/agendamentos/medico/{id}", method = RequestMethod.GET)
	public List<AgendamentoMedicoDTO> listarAgendamentosPorMedico(@PathVariable String id, HttpServletRequest request)
			throws Exception {

		List<AgendamentoMedicoDTO> lista = new ArrayList<AgendamentoMedicoDTO>();
		Medico medico = medicoService.buscar(new Long(id));
		Agenda agenda = agendaService.buscar(medico.getAgenda().getId());

		List<Agendamento> agendamentos = agendamentoService.consultarAgendamentoPorMedico(medico);

		for (Agendamento agendamento : agendamentos) {
			AgendamentoMedicoDTO agendamentoMedicoDTO = new AgendamentoMedicoDTO();
			agendamentoMedicoDTO.setId(agendamento.getId().toString());
			if (agendamento.getNomePacienteNaoCadastrado() != null) {
				agendamentoMedicoDTO.setNomePaciente(agendamento.getNomePacienteNaoCadastrado());
			} else {
				agendamentoMedicoDTO.setNomePaciente(agendamento.getPaciente().getNome());
			}
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(agendamento.getDataConsulta());
			int dia = calendar.get(Calendar.DAY_OF_MONTH);
			int mes = calendar.get(Calendar.MONTH) + 1;
			int ano = calendar.get(Calendar.YEAR);
			String diaString = String.valueOf(dia);
			String mesString = String.valueOf(mes);
			if (mes < 10) {
				mesString = "0" + mesString;
			}
			if (dia < 10) {
				diaString = "0" + diaString;
			}
			String data = ano + "-" + mesString + "-" + diaString;
			agendamentoMedicoDTO.setDataConsulta(data);
			agendamentoMedicoDTO.setHoraConsulta(agendamento.getHora());
			agendamentoMedicoDTO.setDataFinal(data + "T" + agendamentoMedicoDTO.getHoraConsulta());

			GregorianCalendar horaFim = new GregorianCalendar();
			horaFim.setTime(agendamento.getHoraConsulta());
			horaFim.add(Calendar.MINUTE, agenda.getDuracaoMinutos());
			String dataFormatada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date(horaFim.getTimeInMillis()));
			agendamentoMedicoDTO.setDataFinalConsulta(dataFormatada);

			if (agendamento.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.AGUARDANDO)) {
				agendamentoMedicoDTO.setStatusAgendamento("#26a69a");
			}
			if (agendamento.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.CONFIRMADO)) {
				agendamentoMedicoDTO.setStatusAgendamento("#039be5");
			}
			if (agendamento.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.ATENDIDO)) {
				agendamentoMedicoDTO.setStatusAgendamento("#9826a6");
			}
			if (agendamento.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.CANCELADO)) {
				agendamentoMedicoDTO.setStatusAgendamento("#e06464");
			}

			lista.add(agendamentoMedicoDTO);
		}

		return lista;
	}

}
