package br.com.eclinic.controler.consulta;

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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.agendamento.StatusAgendamentoEnum;
import br.com.eclinic.entity.agendamento.TipoAgendamentoConsultaEnum;
import br.com.eclinic.entity.agendamento.TipoAgendamentoEnum;
import br.com.eclinic.entity.agendamento.TurnoAgendamentoEnum;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.consulta.StatusConsultaEnum;
import br.com.eclinic.entity.documentacao.Documentacao;
import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.medico.OrgaoEmissorEnum;
import br.com.eclinic.entity.medico.SexoEnum;
import br.com.eclinic.entity.medico.UfEnum;
import br.com.eclinic.entity.paciente.EstadoCivilEnum;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.paciente.PacienteDTO;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.relatorio.RelatorioFaturamentoMes;
import br.com.eclinic.service.agendamento.AgendamentoService;
import br.com.eclinic.service.consulta.ConsultaService;
import br.com.eclinic.service.exame.ExameService;
import br.com.eclinic.service.medico.MedicoService;
import br.com.eclinic.service.paciente.PacienteService;
import br.com.eclinic.service.plano.PlanoService;
import br.com.eclinic.util.Util;
import br.com.eclinic.validator.AgendamentoValidator;

@Controller
public class ConsultaController extends EclinicController {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static final String MODEL_ATTR_ENTIDADE = "consulta";
	private static final String MODEL_ATTR_ENTIDADE_AGENDAMENTO = "agendamento";
	private static final String URL_REQUEST_LISTA = "/consultas";
	private static final String URL_REQUEST_SALVAR = "/consulta/salvar";

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
	private ConsultaService consultaService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String exibirLista(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("txEnumAguardando", StatusAgendamentoEnum.AGUARDANDO.getDescricao());
		model.addAttribute("txEnumCancelado", StatusAgendamentoEnum.CANCELADO.getDescricao());
		model.addAttribute("txEnumConfirmado", StatusAgendamentoEnum.CONFIRMADO.getDescricao());
		model.addAttribute("txEnumConfirmadoUrgencia", StatusAgendamentoEnum.CONFIRMADO_URGENCIA.getDescricao());
		model.addAttribute("txEnumAtendido", StatusAgendamentoEnum.ATENDIDO.getDescricao());
		model.addAttribute("txEnumEmAtendimento", StatusAgendamentoEnum.EM_ATENDIMENTO.getDescricao());

		return listar(locale, model, new Agendamento(), request);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String exibirLista(@ModelAttribute Agendamento agendamento, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return listar(locale, model, agendamento, request);
	}

	private String listar(Locale locale, Model model, Agendamento agendamentoPesquisa, HttpServletRequest request) throws NegocioException {
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());

		agendamentos = agendamentoService.consultarAgendamentoPorData(usuarioLogado.getCliente(), calendar);
		
		for(Agendamento agendamento : agendamentos){
			if(agendamento.getMedico().getId() != null) {
				Medico medico = medicoService.buscar(agendamento.getMedico().getId());
				agendamento.setMedico(medico);
				String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(medico.getValorConsulta());
				agendamento.setValorConsulta(valor);
			}
		}

		int quantidadeRegistros = 500;

		System.out.println("ANTES ORDENAR");
		for (Agendamento agendamento : agendamentos) {
			System.out.println(agendamento.getStatusAgendamentoEnum());
		}

		if (agendamentos != null && !agendamentos.isEmpty()) {
			//agendamentos.sort(Agendamento.getComparatorPorPrioridade());
		}

		System.out.println("DEPOIS ORDENAR");
		for (Agendamento agendamento : agendamentos) {
			System.out.println(agendamento.getStatusAgendamentoEnum());
		}

		configurarPaginacao(agendamentos, model, quantidadeRegistros);

		String dataConsulta = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.YEAR);

		model.addAttribute("quantidadeAgendamentos", agendamentos.size());
		model.addAttribute("quantidadeConfirmados",
				consultaService.getQuantidadePorStatus(agendamentos, StatusAgendamentoEnum.CONFIRMADO));
		model.addAttribute("quantidadeAtendidos",
				consultaService.getQuantidadePorStatus(agendamentos, StatusAgendamentoEnum.ATENDIDO));
		model.addAttribute("quantidadeAguardando",
				consultaService.getQuantidadePorStatus(agendamentos, StatusAgendamentoEnum.AGUARDANDO));

		model.addAttribute("dataConsulta", dataConsulta);
		model.addAttribute("status", StatusAgendamentoEnum.values());
		model.addAttribute("tipos", TipoAgendamentoEnum.values());
		model.addAttribute("tiposConsulta", TipoAgendamentoConsultaEnum.values());
		model.addAttribute("agendamento", agendamentoPesquisa);
		model.addAttribute("agendamentos", getPagedListHolder().getPageList());
		model.addAttribute("planos", planoService.listar());
		model.addAttribute("sexos", SexoEnum.values());
		model.addAttribute("turnosConsulta", TurnoAgendamentoEnum.values());
		model.addAttribute("tiposAgendamentoConsulta", TipoAgendamentoConsultaEnum.values());
		model.addAttribute("tiposAgendamento", TipoAgendamentoEnum.values());
		model.addAttribute("tiposAgendamento", TipoAgendamentoEnum.values());
		model.addAttribute("orgaos", OrgaoEmissorEnum.values());
		model.addAttribute("uf", UfEnum.values());

		return "consultas";
	}

	@RequestMapping(value = "/consulta/pacientes", method = RequestMethod.POST)
	public String exibirFiltroPoste(@ModelAttribute Paciente paciente, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		listarPaciente(paciente, locale, model, request);
		return "consulta.paciente";
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
		model.addAttribute("planos", planoService.listar());
		model.addAttribute("sexos", SexoEnum.values());
		model.addAttribute("medicos", medicoService.listar());

		return "consulta.paciente";
	}

	@RequestMapping(value = "/consulta/pacientes", method = RequestMethod.GET)
	public String exibirFiltroPacientes(Locale locale, Model model, HttpServletRequest request) throws Exception {

		listarPaciente(new Paciente(), locale, model, request);
		return "consulta.paciente";
	}

	@RequestMapping(value = "/consulta/selecionar/paciente", method = RequestMethod.POST)
	public String selecionarPaciente(@ModelAttribute Paciente paciente, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		if (paciente == null || paciente.getId() == null) {
			listarPaciente(new Paciente(), locale, model, request);
			model.addAttribute(MESSAGE_ERROR, "Selecione um paciente, ou cadastre um novo paciente.");
			return "consulta.paciente";
		} else {
			Agendamento agendamento = new Agendamento();
			paciente = pacienteService.buscar(paciente.getId());
			agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.CONFIRMADO);
			agendamento.setPaciente(paciente);
			montarForm(model, agendamento, request);

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());

			String dataConsulta = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
					+ calendar.get(Calendar.YEAR);

			model.addAttribute("dataConsulta", dataConsulta);
			return "consulta.incluir";
		}
	}

	@RequestMapping(value = "/consulta/selecionar/paciente/cadastrar", method = RequestMethod.POST)
	public String selecionarPacienteCadastrar(@ModelAttribute Paciente paciente, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		if (validarPaciente(paciente)) {

			paciente.setCliente(usuarioLogado.getCliente());
			paciente.setSexoEnum(SexoEnum.getEnumPorCodigo(Integer.parseInt(paciente.getCodigoSexoTransiente())));
			paciente.setDataInclusao(new Date());

			Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(paciente.getDataNascimentoFormatada());
			paciente.setDataNascimento(dataFormatada);
			paciente.setEstadoCivilEnum(EstadoCivilEnum.SOLTEIRO);

			String codigoPacienteLegado = pacienteService.buscarMaiorCodigo();
			Integer codigo = Integer.parseInt(codigoPacienteLegado) + 1;
			paciente.setCodigoPacienteLegado(codigo.toString());
			//paciente.setUsuarioCadastro(usuarioLogado);
			//paciente.setUsuarioUltimaAlteracao(usuarioLogado);
			pacienteService.salvar(paciente);

			model.addAttribute(MESSAGE_WARNING, getMensagem("atencao.atualizar.paciente"));

			Agendamento agendamento = new Agendamento();
			agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.CONFIRMADO);
			agendamento.setPaciente(paciente);
			montarForm(model, agendamento, request);

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());

			String dataConsulta = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
					+ calendar.get(Calendar.YEAR);

			model.addAttribute("dataConsulta", dataConsulta);
			return "consulta.incluir";

		} else {
			listarPaciente(new Paciente(), locale, model, request);
			model.addAttribute(MESSAGE_ERROR, "Selecione um paciente, ou cadastre um novo paciente.");
			return "consulta.paciente";
		}
	}

	private void montarForm(Model model, Agendamento agendamento, HttpServletRequest request) {
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.AGUARDANDO);

		model.addAttribute(MODEL_ATTR_ENTIDADE_AGENDAMENTO, agendamento);
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Consulta());

		model.addAttribute("medicos", medicoService.listar());
		model.addAttribute("planos", planoService.listar());
		model.addAttribute("tiposAgendamentoConsulta", TipoAgendamentoConsultaEnum.values());
		model.addAttribute("turnosConsulta", TurnoAgendamentoEnum.values());
		model.addAttribute("tiposAgendamento", TipoAgendamentoEnum.values());
		model.addAttribute("exames", exameService.listarPorCliente(usuarioLogado.getCliente()));

	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE_AGENDAMENTO, new Agendamento());

		SimpleDateFormat dataFormatada = new SimpleDateFormat(DD_MM_YYYY);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());

		dataFormatada.format(calendar.getTime());

		model.addAttribute("dataConsulta", dataFormatada.toString());
		return "consulta_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public String salvar(Model model, @ModelAttribute Agendamento agendamento, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		int minuto = calendar.get(Calendar.MINUTE);
		int ano = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH) + 1;
		String min = String.valueOf(minuto);
		if(minuto < 10) {
			min = "0" + min;
		}
		agendamento.setHora(hora + ":" + min);
		
		agendamento.setDataConsulta(calendar.getTime());
		agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.CONFIRMADO);
		agendamento.setDataHoraConfirmacao(GregorianCalendar.getInstance().getTime());

		if (agendamento.getIdTurnoConsultaTransiente() != null
				&& !agendamento.getIdTurnoConsultaTransiente().isEmpty()) {
			agendamento.setTurnoAgendamentoEnum(
					TurnoAgendamentoEnum.buscarPorCodigo(Integer.parseInt(agendamento.getIdTurnoConsultaTransiente())));
		}

		AgendamentoValidator validator = new AgendamentoValidator();
		validator.validate(agendamento, result);

		if (result.hasErrors()) {
			model.addAttribute("agendamento", agendamento);
			montarForm(model, agendamento, request);
			return "consulta.incluir";
		} else {

			configurarAgendamento(agendamento);
			agendamento.setCliente(HomeController.getUsuarioLogado(request).getCliente());
			Consulta consulta = new Consulta();
						
			if(agendamento.getMedico().getId() != null) {
				Medico medico = medicoService.buscar(agendamento.getMedico().getId());
				consulta.setMedico(medico);
			}
			
			consulta.setCpfPagador(agendamento.getCpfPagador());
			
			if (consulta.getMedico().getValorConsulta() != null) {
				consulta.setValorConsulta(consulta.getMedico().getValorConsulta());
			} else {
				consulta.setValorConsulta(new BigDecimal(0));
			}
			
			consulta.setMes(mes);
			consulta.setAno(ano);
			consulta.setStatus(StatusConsultaEnum.PENDENTE);
			consulta.setDataConsulta(agendamento.getDataConsulta());
			consulta.setObservacao(agendamento.getObservacaoConsulta());
			consulta.setPaciente(agendamento.getPaciente());
			consulta.setAgendamento(agendamento);

			agendamentoService.salvar(agendamento);
			consultaService.salvar(consulta);
			agendamento.setConsulta(consulta);
			agendamentoService.alterar(agendamento);
			return "redirect:/consultas";
		}
	}

	@RequestMapping(value = "/consulta/confirmar", method = RequestMethod.POST)
	public String confirmarAgendamento(Model model, @ModelAttribute Agendamento agendamento, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		Agendamento agendamentoAlteracao = agendamentoService.buscar(agendamento.getId());

		if (StatusAgendamentoEnum.CONFIRMADO_URGENCIA.equals(agendamentoAlteracao.getStatusAgendamentoEnum())) {
			agendamentoAlteracao.setStatusAgendamentoEnum(StatusAgendamentoEnum.ATENDIDO);
		} else {
			agendamentoAlteracao.setStatusAgendamentoEnum(StatusAgendamentoEnum.CONFIRMADO);
		}

		agendamentoAlteracao.setCliente(HomeController.getUsuarioLogado(request).getCliente());
		agendamentoAlteracao.setCpfPagador(agendamento.getCpfPagador());
		agendamentoAlteracao.setDataHoraConfirmacao(GregorianCalendar.getInstance().getTime());

		if (agendamentoAlteracao.getPaciente() == null || agendamentoAlteracao.getPaciente().getId() == null
				|| agendamentoAlteracao.getPaciente().getId() == 0) {

			agendamento.getPaciente().setNome(agendamento.getNomePacienteNaoCadastrado());
			agendamento.getPaciente().setCpf(agendamento.getCpfPacienteNaoCadastrado());
			agendamento.getPaciente().setRg(agendamento.getRgPacienteNaoCadastrado());

			if (validarPaciente(agendamento.getPaciente())) {
				configurarPaciente(agendamento, usuarioLogado);
				agendamentoAlteracao.setPaciente(agendamento.getPaciente());
				agendamento.setPacienteNovo(true);				
				redirectAttributes.addFlashAttribute(MESSAGE_WARNING, getMensagem("atencao.atualizar.paciente"));
			} else {
				redirectAttributes.addFlashAttribute(MESSAGE_ERROR,
						getMensagem("todos.campos.obrigatorio.paciente.confirmacao"));
				return "redirect:/consultas";
			}
		}

		Consulta consulta = new Consulta();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(agendamentoAlteracao.getDataConsulta());

		consulta.setDataConsulta(agendamentoAlteracao.getDataConsulta());
		consulta.setCpfPagador(agendamentoAlteracao.getCpfPagador());
		consulta.setEmitirNota(false);
		
		int mes = calendar.get(Calendar.MONTH) + 1;
		int ano = calendar.get(Calendar.YEAR);
		consulta.setMes(mes);
		consulta.setAno(ano);

		if (agendamento.getValorConsulta() != null && !agendamento.getValorConsulta().isEmpty()) {
			Medico medico = medicoService.buscar(agendamentoAlteracao.getMedico().getId());
			consulta.setValorConsulta(medico.getValorConsulta());
		} else {
			consulta.setValorConsulta(new BigDecimal(0));
		}
		
		consulta.setObservacao(agendamento.getObservacaoConsulta());
		consulta.setAgendamento(agendamentoAlteracao);
		consulta.setMedico(agendamentoAlteracao.getMedico());
		consulta.setPaciente(agendamentoAlteracao.getPaciente());
		consulta.setStatus(StatusConsultaEnum.PAGO);
		agendamentoAlteracao.setConsulta(consulta);
		
		if(agendamento.getPacienteNovo() != null && agendamento.getPacienteNovo() == true) {
			pacienteService.salvar(consulta.getPaciente());
		}
		consultaService.salvar(consulta);
		agendamentoService.alterar(agendamentoAlteracao);

		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("consulta.confirmada.sucesso"));
		return "redirect:/consultas";

	}

	private boolean validarPaciente(Paciente paciente) {
		boolean resultado = true;

		if (StringUtils.isBlank(paciente.getNome())) {
			resultado = false;
		}

		if (StringUtils.isBlank(paciente.getDataNascimentoFormatada())) {
			resultado = false;
		}
		if (StringUtils.isBlank(paciente.getCpf())) {
			resultado = false;
		}
		if (StringUtils.isBlank(paciente.getRg())) {
			resultado = false;
		}
		if (StringUtils.isBlank(paciente.getCodigoSexoTransiente())) {
			resultado = false;
		}

		return resultado;
	}

	private void configurarPaciente(Agendamento agendamento, Usuario usuarioLogado) throws ParseException {
		Paciente paciente = new Paciente();
		Documentacao documentacao = new Documentacao();
		Endereco endereco = new Endereco();

		paciente.setCliente(usuarioLogado.getCliente());
		documentacao.setCpf(agendamento.getCpfPacienteNaoCadastrado());
		documentacao.setRg(agendamento.getRgPacienteNaoCadastrado());
		documentacao.setCelular(agendamento.getPaciente().getDocumentacao().getCelular());
		
		if(agendamento.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != null 
				&& (agendamento.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != "")) {
			documentacao.setOrgaoEmissorEnum(OrgaoEmissorEnum.getEnumPorCodigo(Integer
					.parseInt(agendamento.getPaciente().getDocumentacao().getOrgaoEmissorTransiente())));
		}
		
		paciente.setDocumentacao(documentacao);
		
		endereco.setBairro(agendamento.getPaciente().getEndereco().getBairro());
		endereco.setCidade(agendamento.getPaciente().getEndereco().getCidade());
		endereco.setLogradouro(agendamento.getPaciente().getEndereco().getLogradouro());
		endereco.setCep(agendamento.getPaciente().getEndereco().getCep());
		endereco.setNumero(agendamento.getPaciente().getEndereco().getNumero());
		
		if(agendamento.getPaciente().getEndereco().getEstadoTransiente() != null 
				&& agendamento.getPaciente().getEndereco().getEstadoTransiente() != "") {
			endereco.setEstado(UfEnum.getEnumPorCodigo(Integer.parseInt(agendamento.getPaciente().getEndereco().getEstadoTransiente())));
		}
		
		paciente.setEndereco(endereco);
		
		if(agendamento.getPaciente().getCodigoSexoTransiente() != null && agendamento.getPaciente().getCodigoSexoTransiente() != "") {
			paciente.setSexoEnum(SexoEnum.getEnumPorCodigo(Integer.parseInt(agendamento.getPaciente().getCodigoSexoTransiente())));
		}
		
		paciente.setNome(agendamento.getNomePacienteNaoCadastrado());
		paciente.setDataInclusao(new Date());

		
		if(agendamento.getPaciente().getDataNascimentoFormatada() != null && agendamento.getPaciente().getDataNascimentoFormatada() != "") {
			Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(agendamento.getPaciente().getDataNascimentoFormatada());
			paciente.setDataNascimento(dataFormatada);
		}

		/*String codigoPacienteLegado = pacienteService.buscarMaiorCodigo();
		Integer codigo = Integer.parseInt(codigoPacienteLegado) + 1;
		paciente.setCodigoPacienteLegado(codigo.toString());*/
		
		//paciente.setUsuarioCadastro(usuarioLogado);
		//paciente.setUsuarioUltimaAlteracao(usuarioLogado);

		agendamento.setPaciente(paciente);
	}

	@RequestMapping(value = "/consulta/cancelar", method = RequestMethod.POST)
	public String cancelarAgendamento(Model model, @ModelAttribute Agendamento agendamento, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		Agendamento agendamentoAlteracao = agendamentoService.buscar(agendamento.getId());
		agendamentoAlteracao.setStatusAgendamentoEnum(StatusAgendamentoEnum.CANCELADO);
		agendamentoAlteracao.setCliente(HomeController.getUsuarioLogado(request).getCliente());
		if(agendamentoAlteracao.getConsulta().getId() != null){
			Consulta consultaBanco = consultaService.buscar(agendamentoAlteracao.getConsulta().getId());
			consultaBanco.setStatus(StatusConsultaEnum.CANCELADO);
			consultaService.alterar(consultaBanco);
		}
		salvarAlterar(agendamentoAlteracao);
		redirectAttributes.addFlashAttribute(MESSAGE, "consulta.cancelada.sucesso");
		return "redirect:/consultas";
	}

	@RequestMapping(value = "/consulta/confirmar-urgencia", method = RequestMethod.POST)
	public String confirmarUrgenciaAgendamento(Model model, @ModelAttribute Agendamento agendamento,
			BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request)
					throws Exception {

		Agendamento agendamentoAlteracao = agendamentoService.buscar(agendamento.getId());
		agendamentoAlteracao.setStatusAgendamentoEnum(StatusAgendamentoEnum.CONFIRMADO_URGENCIA);
		agendamentoAlteracao.setCliente(HomeController.getUsuarioLogado(request).getCliente());
		agendamentoAlteracao.setDataHoraConfirmacao(GregorianCalendar.getInstance().getTime());
		salvarAlterar(agendamentoAlteracao);
		redirectAttributes.addFlashAttribute(MESSAGE, "consulta.confirmada.sucesso");
		return "redirect:/consultas";
	}

	@RequestMapping(value = "consulta/finalizar_atendimento", method = RequestMethod.POST)
	public String finalizarAgendamento(Model model, @ModelAttribute Agendamento agendamento, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		consultaService.finalizarConsulta(agendamento);

		redirectAttributes.addFlashAttribute(MESSAGE, "consulta.finalizada.sucesso");

		return "redirect:/consultas";
	}

	@RequestMapping(value = "/consulta/atender", method = RequestMethod.POST)
	public String atenderAgendamento(Model model, @ModelAttribute Agendamento agendamento, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		Cliente cliente = HomeController.getUsuarioLogado(request).getCliente();

		consultaService.atenderConsulta(agendamento, cliente);

		redirectAttributes.addFlashAttribute(MESSAGE, "Consulta aberta com sucesso.");

		return "redirect:/consultas";
	}

	private void configurarAgendamento(Agendamento agendamento) throws ParseException {
		agendamento.setTipoAgendamentoEnum(TipoAgendamentoEnum.PRESENCIAL);
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

	@RequestMapping(value = "/relatorio-faturamento-mensal", method = RequestMethod.GET)
	public String gerarRelatorioFaturamentoMes(Locale locale, Model model, HttpServletRequest request)
			throws Exception {

		RelatorioFaturamentoMes rel = new RelatorioFaturamentoMes();
		rel.setMesFormatado(new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime()));
		model.addAttribute("relatorio", new RelatorioFaturamentoMes());

		return "relatorio-faturamento-mensal";
	}

	@RequestMapping(value = "/relatorio-faturamento-mensal/gerar", method = RequestMethod.POST)
	public String gerarRelatorioFaturamentoMesImpressao(@ModelAttribute RelatorioFaturamentoMes formRelatorio,
			Locale locale, Model model, HttpServletRequest request) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		GregorianCalendar mes = new GregorianCalendar();
		mes.setTime(format.parse(formRelatorio.getMesFormatado()));
		String mesNome = Util.mesFormatado(mes.get(Calendar.MONTH) + 1);
		String anoNome = String.valueOf(mes.get(Calendar.YEAR));

		List<RelatorioFaturamentoMes> relatorio = consultaService.getRelatorioFaturamentoMes(usuarioLogado.getCliente(),
				mes);

		GregorianCalendar dataAtual = new GregorianCalendar();
		dataAtual.setTime(new Date());

		String dataConsulta = dataAtual.get(Calendar.DAY_OF_MONTH) + "/" + (dataAtual.get(Calendar.MONTH) + 1) + "/"
				+ dataAtual.get(Calendar.YEAR);

		model.addAttribute("relatorio", relatorio);
		model.addAttribute("dataConsulta", dataConsulta);
		model.addAttribute("mesConsulta", mesNome);
		model.addAttribute("anoConsulta", anoNome);

		return "relatorio-faturamento-mensal-gerar";

	}

	@RequestMapping(value = "/consulta/emitir-nota", method = RequestMethod.POST)
	public String gerarEmissaoNota(Model model, @ModelAttribute Agendamento agendamento, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		Agendamento agendamentoAlteracao = agendamentoService.buscar(agendamento.getId());
		Consulta consulta = agendamentoAlteracao.getConsulta();

		GregorianCalendar dataAtual = new GregorianCalendar();
		dataAtual.setTime(new Date());

		String dataConsulta = dataAtual.get(Calendar.DAY_OF_MONTH) + "/" + (dataAtual.get(Calendar.MONTH) + 1) + "/"
				+ dataAtual.get(Calendar.YEAR);

		model.addAttribute("consulta", consulta);
		model.addAttribute("dataEmissao", dataConsulta);

		return "emitir-nota-gerar";
	}

	@ResponseBody
	@RequestMapping(value = "consulta/paciente/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("pacientes", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Paciente());
		return new ModelAndView("paciente.lista");
	}
}
