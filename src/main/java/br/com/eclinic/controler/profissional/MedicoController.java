package br.com.eclinic.controler.profissional;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.agenda.Agenda;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.entity.medico.*;
import br.com.eclinic.entity.supervisor.Responsavel;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.agenda.AgendaService;
import br.com.eclinic.service.especialidade.EspecialidadeService;
import br.com.eclinic.service.jornada.JornadaService;
import br.com.eclinic.service.medico.MedicoService;
import br.com.eclinic.service.supervisor.ResponsavelService;
import br.com.eclinic.validator.MedicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

@SuppressWarnings({ "unused" })
@Controller
public class MedicoController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "medico";
	private static final String URL_REQUEST_LISTA = "/profissionais";
	private static final String URL_REQUEST_SALVAR = "/medico/salvar";
	private static final String URL_REQUEST_ALTERAR = "/medico/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/medico/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/medico/excluir";

	@Autowired
	private MedicoService service;
	@Autowired
	private AgendaService agendaService;
	@Autowired
	private EspecialidadeService especialidadeService;
	@Autowired
	private JornadaService jornadaService;
	@Autowired
	private ResponsavelService responsavelService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String medicosPesquisar(@ModelAttribute Medico medico, Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		return listar(medico, locale, model, request);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public ModelAndView medicos(@ModelAttribute("medico") Medico medico) {
		List<Medico> todosmedicos = service.listar();
		ModelAndView mv = new ModelAndView("medicos");
		mv.addObject("medicos", todosmedicos);
		return mv;
	}

	private String listar(@ModelAttribute Medico medico, Locale locale, Model model, HttpServletRequest request) {
		List<Medico> medicos = new ArrayList<Medico>();
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		if (medico.getNome() != null) {
			Medico medicoConsulta = new Medico();
			medicoConsulta.setNome(medico.getNome());
			medicos = service.consultar(medicoConsulta, usuarioLogado.getCliente());
		} else {
			medicos = service.listar();
		}

		configurarPaginacao(medicos, model);
		Medico medicoNovo = new Medico();
		medicoNovo.setNome(medico.getNome());
		model.addAttribute(MODEL_ATTR_ENTIDADE, medicoNovo);
		model.addAttribute("medicos", getPagedListHolder().getPageList());

		return "medicos";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute("medicoDTO", new MedicoDTO());
		model.addAttribute("profissionais", TipoProfissionalEnum.values());
		model.addAttribute("tipoContrato", TipoContratoEnum.values());
		model.addAttribute("uf", UfEnum.values());
		model.addAttribute("sexos", SexoEnum.values());
		model.addAttribute("situacao", SituacaoEnum.values());
		model.addAttribute("especialidades", especialidadeService.listar());

		return "medico_incluir";
	}

	@RequestMapping(value = "/medico/salvar_medico", method = RequestMethod.POST)
	public ModelAndView salvarSolicitacaoMedico(@ModelAttribute MedicoDTO medicoDTO, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		String vazio = "";
		String zero = "0";

		ModelAndView modelAndView = new ModelAndView("medico_incluir");
		modelAndView.addObject("medicoDTO", medicoDTO);
		modelAndView.addObject("tipoContrato", TipoContratoEnum.values());
		modelAndView.addObject("uf", UfEnum.values());
		modelAndView.addObject("situacao", SituacaoEnum.values());
		modelAndView.addObject("sexos", SexoEnum.values());
		modelAndView.addObject("especialidades", especialidadeService.listar());

		Medico medico = new Medico();
		Agenda agenda = new Agenda();
		Jornada jornada = new Jornada();
		Responsavel responsavel = new Responsavel();
		medico = medicoDTO.getMedico();
		
		if(medicoDTO.getJornada().getFim().equalsIgnoreCase("0") || medicoDTO.getJornada().getInicio().equalsIgnoreCase("0")) {
			modelAndView.addObject(MESSAGE_ERROR, getMensagem("erro.inclusao.jornada"));
			return modelAndView;
		}

		medico.setCliente(HomeController.getUsuarioLogado(request).getCliente());

		if (medicoDTO.getDataContratacaoFormatada() != null &&
		medicoDTO.getDataContratacaoFormatada() != vazio) {
		configurarDataContratacaoMedico(medicoDTO);
		}
		
		if (medicoDTO.getDataNascimentoFormatada() != null &&
		medicoDTO.getDataNascimentoFormatada() != vazio) {
		configurarDataNascimentoMedico(medicoDTO);
		}
		
		medico.setDataContratacao(medicoDTO.getDataContratacao());
		medico.setDataNascimento(medicoDTO.getData());

		if (medicoDTO.getMedico().getCodigoSexoTransiente() != null
				&& medicoDTO.getMedico().getCodigoSexoTransiente() != vazio) {
			medico.setSexoEnum(
					SexoEnum.getEnumPorCodigo(Integer.parseInt(medicoDTO.getMedico().getCodigoSexoTransiente())));
		}

		if (medicoDTO.getMedico().getTipoContratoTransiente() != null
				&& medicoDTO.getMedico().getTipoContratoTransiente() != vazio) {
			medico.setTipoContratoEnum(TipoContratoEnum
					.getEnumPorCodigo(Integer.parseInt(medicoDTO.getMedico().getTipoContratoTransiente())));
		}

		if (medicoDTO.getMedico().getCodigoUfTransiente() != null
				&& medicoDTO.getMedico().getCodigoUfTransiente() != vazio) {
			medico.setUfEnum(UfEnum.getEnumPorCodigo(Integer.parseInt(medicoDTO.getMedico().getCodigoUfTransiente())));
		}

		if (medicoDTO.getValorFormatado() != null && medicoDTO.getValorFormatado() != vazio) {
			medico.setValorConsulta(new BigDecimal(medicoDTO.getValorFormatado().replace(".", "").replace(",", ".")));
		}

		if (service.consultarCpf(medico) != null) {
			modelAndView.addObject(MESSAGE_ERROR, getMensagem("profissional.inclusao.erro.existe"));
			return modelAndView;
		}

		MedicoValidator validator = new MedicoValidator();
		validator.validate(medico, result);

		if (result.hasErrors()) {
			modelAndView.addObject(MESSAGE_ERROR, getMensagem("informar.campo"));
			return modelAndView;
		} else {

			jornada = medicoDTO.getJornada();

			if (medicoDTO.getResponsavel().getCpf() != vazio && medicoDTO.getResponsavel().getNome() != vazio) {
				responsavel = medicoDTO.getResponsavel();
			}

			if (jornada.getInicio() != null && jornada.getInicio() != vazio) {
				GregorianCalendar horario = new GregorianCalendar();
				String hora = jornada.getInicio().substring(0, 2);
				String minuto = jornada.getInicio().substring(3, 5);
				horario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
				horario.set(Calendar.MINUTE, Integer.parseInt(minuto));
				horario.set(Calendar.SECOND, 0);
				jornada.setInicioJornada(horario.getTime());
			}

			if (jornada.getFim() != null && jornada.getFim() != vazio) {
				GregorianCalendar horario = new GregorianCalendar();
				String hora = jornada.getInicio().substring(0, 2);
				String minuto = jornada.getInicio().substring(3, 5);
				horario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
				horario.set(Calendar.MINUTE, Integer.parseInt(minuto));
				horario.set(Calendar.SECOND, 0);
				jornada.setFimJornada(horario.getTime());
			}

			if (medicoDTO.getEspecialidade().getId() != null && medicoDTO.getEspecialidade().getId() != 0) {
				Especialidade especialidade = especialidadeService.buscar(medicoDTO.getEspecialidade().getId());
				medico.setEspecialidade(especialidade);
			}

			agenda = medicoDTO.getAgenda();

			if (medicoDTO.getAgenda().getQuantidade() != null && medicoDTO.getAgenda().getQuantidade() != vazio
					&& medicoDTO.getAgenda().getQuantidade() != zero) {
				agenda.setQuantidadeConsultas(Integer.parseInt(medicoDTO.getAgenda().getQuantidade()));
			}

			if (medicoDTO.getAgenda().getDuracao() != null && medicoDTO.getAgenda().getDuracao() != vazio) {
				agenda.setDuracaoConsulta(new BigDecimal(medicoDTO.getAgenda().getDuracao().replace(":", ".")));
				String hora = medicoDTO.getAgenda().getDuracao().split(":")[0];
				String minuto = medicoDTO.getAgenda().getDuracao().split(":")[1];
				int horai = Integer.parseInt(hora);
				int minutoi = Integer.parseInt(minuto);
				horai = horai * 60;
				int total = horai + minutoi;
				agenda.setDuracaoMinutos(total);
			}

			agendaService.salvar(agenda);
			medico.setSituacaoEnum(SituacaoEnum.ATIVO);
			medico.setSituacaoContrato("ATIVO");
			medico.setAgenda(agenda);
			if (responsavel.getNome() != null && responsavel.getCpf() != null && responsavel.getNome() != vazio
					&& responsavel.getCpf() != vazio) {
				responsavel = medicoDTO.getResponsavel();
				responsavelService.salvar(responsavel);
				medico.setResponsavel(responsavel);
			}
			
			service.salvar(medico);
			
			if (medico.getId() != null) {
				jornada.setMedicoVinculo(medico);
				jornadaService.salvar(jornada);
			}
		}

		ModelAndView retorno = new ModelAndView("redirect:/profissionais");
		return retorno;
	}

	private void configurarDataContratacaoMedico(MedicoDTO medicoDTO) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy")
				.parse(medicoDTO.getDataContratacaoFormatada());
		medicoDTO.setDataContratacao(dataFormatada);
	}

	private void configurarDataNascimentoMedico(MedicoDTO medicoDTO) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy")
				.parse(medicoDTO.getDataNascimentoFormatada());
		medicoDTO.setData(dataFormatada);
	}

	private void configurarHoraJornadaInicio(Jornada jornada) throws ParseException {
		Date hora = new SimpleDateFormat("HH:mm").parse(jornada.getInicio());
		jornada.setInicioJornada(hora);
	}

	private void configurarHoraJornadaFim(Jornada jornada) throws ParseException {
		Date hora = new SimpleDateFormat("HH:mm").parse(jornada.getFim());
		jornada.setFimJornada(hora);
	}

	private void configurarDataNascimento(Medico medico) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(medico.getDataNascimentoFormatada());
		medico.setDataNascimento(dataFormatada);
	}

	private void configurarDataContratacao(Medico medico) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(medico.getDataContratacaoFormatada());
		medico.setDataContratacao(dataFormatada);
	}

	@RequestMapping(value = "/medico" + "/{pk}", produces = "application/json")
	public @ResponseBody Medico buscar(@PathVariable Long pk, Locale locale, Model model, HttpServletRequest request)
			throws Exception {

		return service.buscar(pk);
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {
		Medico medicoAlteracao = service.buscar(pk);
		Especialidade especialidade = especialidadeService.buscar(medicoAlteracao.getEspecialidade().getId());
		medicoAlteracao.setEspecialidade(especialidade);

		if (medicoAlteracao != null) {
			medicoAlteracao.setDataNascimentoFormatada(medicoAlteracao.getDataNascimento().toString());
			model.addAttribute(MODEL_ATTR_ENTIDADE, medicoAlteracao);
			model.addAttribute("especialidades", especialidadeService.listar());
			model.addAttribute("tipoContrato", TipoContratoEnum.values());
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("sexos", SexoEnum.values());
			model.addAttribute("situacao", SituacaoEnum.values());

			return "medico_visualizar";
		} else {
			return "redirect:../profissionais";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, MedicoDTO medicoDTO, Model model) throws Exception {
		
		Medico medicoAlteracao = service.buscar(pk);
		if(medicoAlteracao.getEspecialidade() != null && medicoAlteracao.getEspecialidade().getId() != null) {
			Especialidade especialidade = especialidadeService.buscar(medicoAlteracao.getEspecialidade().getId());
			medicoAlteracao.setEspecialidade(especialidade);
		}
		
		Jornada jornada = jornadaService.buscarPorMedico(medicoAlteracao.getId());
		
		if(medicoAlteracao.getResponsavel() != null && medicoAlteracao.getResponsavel().getId() != null) {
			Responsavel responsavel = responsavelService.buscar(medicoAlteracao.getResponsavel().getId());
			medicoDTO.setResponsavel(responsavel);
		}
		
		medicoAlteracao.setTipoContratoTransiente(String.valueOf(medicoAlteracao.getTipoContratoEnum().getCodigo()));
		medicoAlteracao.setCodigoSexoTransiente(String.valueOf(medicoAlteracao.getSexoEnum().getCodigo()));
		medicoAlteracao.setCodigoUfTransiente(String.valueOf(medicoAlteracao.getUfEnum().getCodigo()));

		if(medicoAlteracao.getAgenda() != null) {
			Agenda agenda = medicoAlteracao.getAgenda();
			agenda.setQuantidade(agenda.getQuantidadeConsultas().toString());
			
			if(agenda.getDuracaoConsulta().compareTo(new BigDecimal(1.00)) < 0) {
				String duracao = agenda.getDuracaoConsulta().toString();
				String hora = duracao.split("\\.")[0];
				String minuto = duracao.split("\\.")[1];
				String duracaoFormatada = "";
				int h = Integer.valueOf(hora);
				if(h < 10) {
					hora = "0" + hora;
				}
				
				duracaoFormatada = hora + minuto;		
				agenda.setDuracao(duracaoFormatada);
			}
		}
		
		if(jornada != null) {
			jornada.setInicio(jornada.getInicio().replace(":", ""));
			jornada.setFim(jornada.getFim().replace(":", ""));
			medicoDTO.setJornada(jornada);
		}
		
		if(medicoAlteracao.getDataContratacao() != null) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(medicoAlteracao.getDataContratacao());
			int dia = calendar.get(Calendar.DAY_OF_MONTH);
			int mes = calendar.get(Calendar.MONTH) + 1;
			int ano = calendar.get(Calendar.YEAR);
			String d = "";
			String m = "";
			String a = String.valueOf(ano);
			if(dia < 10) {
				d = "0" + dia;
			} else {
				d = String.valueOf(dia);
			}
			
			if(mes < 10) {
				m = "0" + mes;
			} else {
				m = String.valueOf(mes);
			}
			
			String dataFormatada = d + m + a;
			medicoDTO.setDataContratacaoFormatada(dataFormatada);
		}
		
		if(medicoAlteracao.getDataNascimento() != null) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(medicoAlteracao.getDataNascimento());
			int dia = calendar.get(Calendar.DAY_OF_MONTH);
			int mes = calendar.get(Calendar.MONTH) + 1;
			int ano = calendar.get(Calendar.YEAR);
			String d = "";
			String m = "";
			String a = String.valueOf(ano);
			if(dia < 10) {
				d = "0" + dia;
			} else {
				d = String.valueOf(dia);
			}
			
			if(mes < 10) {
				m = "0" + mes;
			} else {
				m = String.valueOf(mes);
			}
			
			String dataFormatada = d + m + a;
			medicoDTO.setDataNascimentoFormatada(dataFormatada);
		}
		
		medicoDTO.setMedico(medicoAlteracao);
		medicoDTO.setEspecialidade(medicoAlteracao.getEspecialidade());
		medicoDTO.setAgenda(medicoAlteracao.getAgenda());
		String valorFormatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(medicoAlteracao.getValorConsulta().doubleValue()).replace("R$ ", "");
		medicoDTO.setValorFormatado(valorFormatado);
		

		model.addAttribute("sexos", SexoEnum.values());
		model.addAttribute("uf", UfEnum.values());
		model.addAttribute("tipoContrato", TipoContratoEnum.values());
		model.addAttribute("especialidades", especialidadeService.listar());

		return "medico_alterar";
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute MedicoDTO medicoDTO, Model model, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		String vazio = "";
		String zero = "0";

		ModelAndView modelAndView = new ModelAndView("medico_incluir");
		modelAndView.addObject("medicoDTO", medicoDTO);
		modelAndView.addObject("tipoContrato", TipoContratoEnum.values());
		modelAndView.addObject("uf", UfEnum.values());
		modelAndView.addObject("situacao", SituacaoEnum.values());
		modelAndView.addObject("sexos", SexoEnum.values());
		modelAndView.addObject("especialidades", especialidadeService.listar());

		Medico medico = new Medico();
		Agenda agenda = new Agenda();
		Jornada jornada = new Jornada();
		Responsavel responsavel = new Responsavel();
		medico = medicoDTO.getMedico();
		
		if(medicoDTO.getJornada().getFim().equalsIgnoreCase("0") || medicoDTO.getJornada().getInicio().equalsIgnoreCase("0")) {
			modelAndView.addObject(MESSAGE_ERROR, getMensagem("erro.inclusao.jornada"));
			return modelAndView;
		}

		medico.setCliente(HomeController.getUsuarioLogado(request).getCliente());

		if (medicoDTO.getDataContratacaoFormatada() != null &&
		medicoDTO.getDataContratacaoFormatada() != vazio) {
		configurarDataContratacaoMedico(medicoDTO);
		}
		
		if (medicoDTO.getDataNascimentoFormatada() != null &&
		medicoDTO.getDataNascimentoFormatada() != vazio) {
		configurarDataNascimentoMedico(medicoDTO);
		}
		
		medico.setDataContratacao(medicoDTO.getDataContratacao());
		medico.setDataNascimento(medicoDTO.getData());

		if (medicoDTO.getMedico().getCodigoSexoTransiente() != null
				&& medicoDTO.getMedico().getCodigoSexoTransiente() != vazio) {
			medico.setSexoEnum(
					SexoEnum.getEnumPorCodigo(Integer.parseInt(medicoDTO.getMedico().getCodigoSexoTransiente())));
		}

		if (medicoDTO.getMedico().getTipoContratoTransiente() != null
				&& medicoDTO.getMedico().getTipoContratoTransiente() != vazio) {
			medico.setTipoContratoEnum(TipoContratoEnum
					.getEnumPorCodigo(Integer.parseInt(medicoDTO.getMedico().getTipoContratoTransiente())));
		}

		if (medicoDTO.getMedico().getCodigoUfTransiente() != null
				&& medicoDTO.getMedico().getCodigoUfTransiente() != vazio) {
			medico.setUfEnum(UfEnum.getEnumPorCodigo(Integer.parseInt(medicoDTO.getMedico().getCodigoUfTransiente())));
		}

		if (medicoDTO.getValorFormatado() != null && medicoDTO.getValorFormatado() != vazio) {
			medico.setValorConsulta(new BigDecimal(medicoDTO.getValorFormatado().replace(".", "").replace(",", ".")));
		}

		if (service.consultarCpf(medico) != null) {
			modelAndView.addObject(MESSAGE_ERROR, getMensagem("profissional.inclusao.erro.existe"));
			return modelAndView;
		}

		MedicoValidator validator = new MedicoValidator();
		validator.validate(medico, result);

		if (result.hasErrors()) {
			modelAndView.addObject(MESSAGE_ERROR, getMensagem("informar.campo"));
			return modelAndView;
		} else {

			jornada = medicoDTO.getJornada();

			if (medicoDTO.getResponsavel().getCpf() != vazio && medicoDTO.getResponsavel().getNome() != vazio) {
				responsavel = medicoDTO.getResponsavel();
			}

			if (jornada.getInicio() != null && jornada.getInicio() != vazio) {
				GregorianCalendar horario = new GregorianCalendar();
				String hora = jornada.getInicio().substring(0, 2);
				String minuto = jornada.getInicio().substring(3, 5);
				horario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
				horario.set(Calendar.MINUTE, Integer.parseInt(minuto));
				horario.set(Calendar.SECOND, 0);
				jornada.setInicioJornada(horario.getTime());
			}

			if (jornada.getFim() != null && jornada.getFim() != vazio) {
				GregorianCalendar horario = new GregorianCalendar();
				String hora = jornada.getInicio().substring(0, 2);
				String minuto = jornada.getInicio().substring(3, 5);
				horario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
				horario.set(Calendar.MINUTE, Integer.parseInt(minuto));
				horario.set(Calendar.SECOND, 0);
				jornada.setFimJornada(horario.getTime());
			}

			if (medicoDTO.getEspecialidade().getId() != null && medicoDTO.getEspecialidade().getId() != 0) {
				Especialidade especialidade = especialidadeService.buscar(medicoDTO.getEspecialidade().getId());
				medico.setEspecialidade(especialidade);
			}

			agenda = medicoDTO.getAgenda();

			if (medicoDTO.getAgenda().getQuantidade() != null && medicoDTO.getAgenda().getQuantidade() != vazio
					&& medicoDTO.getAgenda().getQuantidade() != zero) {
				agenda.setQuantidadeConsultas(Integer.parseInt(medicoDTO.getAgenda().getQuantidade()));
			}

			if (medicoDTO.getAgenda().getDuracao() != null && medicoDTO.getAgenda().getDuracao() != vazio) {
				agenda.setDuracaoConsulta(new BigDecimal(medicoDTO.getAgenda().getDuracao().replace(":", ".")));
				String hora = medicoDTO.getAgenda().getDuracao().split(":")[0];
				String minuto = medicoDTO.getAgenda().getDuracao().split(":")[1];
				int horai = Integer.parseInt(hora);
				int minutoi = Integer.parseInt(minuto);
				horai = horai * 60;
				int total = horai + minutoi;
				agenda.setDuracaoMinutos(total);
			}

			agendaService.salvar(agenda);
			medico.setSituacaoEnum(SituacaoEnum.ATIVO);
			medico.setSituacaoContrato("ATIVO");
			medico.setAgenda(agenda);
			if (responsavel.getNome() != null && responsavel.getCpf() != null && responsavel.getNome() != vazio
					&& responsavel.getCpf() != vazio) {
				responsavel = medicoDTO.getResponsavel();
				responsavelService.salvar(responsavel);
				medico.setResponsavel(responsavel);
			}
			
			service.salvar(medico);
			
			if (medico.getId() != null) {
				jornada.setMedicoVinculo(medico);
				jornadaService.salvar(jornada);
			}
		}

		ModelAndView retorno = new ModelAndView("redirect:/profissionais");
		return retorno;
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Medico medico, Model model, final RedirectAttributes redirectAttributes) {

		try {
			service.excluir(medico);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (NegocioException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, e.getMessage());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem("medico.dependencia.com.consulta"));
		}

		return "redirect:/profissionais";
	}

	@ResponseBody
	@RequestMapping(value = "/medico/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("medicos", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Medico());
		return new ModelAndView("medico.lista");
	}
}