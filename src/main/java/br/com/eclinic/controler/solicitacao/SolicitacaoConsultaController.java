package br.com.eclinic.controler.solicitacao;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.agendamento.StatusAgendamentoEnum;
import br.com.eclinic.entity.agendamento.StatusPagamentoEnum;
import br.com.eclinic.entity.agendamento.TipoAgendamentoEnum;
import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.autorizacao.CanalEnum;
import br.com.eclinic.entity.autorizacao.SituacaoAutorizacaoEnum;
import br.com.eclinic.entity.autorizacao.StatusAutorizacaoEnum;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.consulta.StatusConsultaEnum;
import br.com.eclinic.entity.contas.ContasReceber;
import br.com.eclinic.entity.contas.TipoContaEnum;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.documentacao.Documentacao;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.solicitacao.SolicitacaoConsulta;
import br.com.eclinic.entity.solicitacao.SolicitacaoDTO;
import br.com.eclinic.entity.solicitacao.StatusEnum;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.agendamento.AgendamentoService;
import br.com.eclinic.service.autorizacao.AutorizacaoService;
import br.com.eclinic.service.consulta.ConsultaService;
import br.com.eclinic.service.contas.ContasReceberService;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.documentacao.DocumentacaoService;
import br.com.eclinic.service.medico.MedicoService;
import br.com.eclinic.service.paciente.PacienteService;
import br.com.eclinic.service.solicitacao.SolicitacaoConsultaService;

@Controller
public class SolicitacaoConsultaController extends EclinicController {

	@Autowired
	private SolicitacaoConsultaService solicitacaoConsultaService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private MedicoService medicoService;
	@Autowired
	private ConsultaService consultaService;
	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private DocumentacaoService documentacaoService;
	@Autowired
	private AutorizacaoService autorizacaoService;
	@Autowired
	private ContasReceberService contasReceberService;
	@Autowired
	private CredenciadoService credenciadoService;

	@RequestMapping(value = "/solicitacoes/consulta", method = RequestMethod.GET)
	public String solicitacaoExame(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("solicitacaoConsulta", new SolicitacaoConsulta());
		model.addAttribute("solicitacoes", solicitacaoConsultaService.listar());
		return "solicitacao/consulta";
	}

	@RequestMapping(value = "/solicitacoes/consulta", method = RequestMethod.POST)
	public String solicitacoesPesquisar(@ModelAttribute SolicitacaoConsulta solicitacaoConsulta, Locale locale,
			Model model, HttpServletRequest request) throws Exception {
		List<SolicitacaoConsulta> solicitacoes = solicitacaoConsultaService.consultar(solicitacaoConsulta);
		model.addAttribute("solicitacoes", solicitacoes);
		model.addAttribute("solicitacaoConsulta", solicitacaoConsulta);
		return "solicitacao/consulta";
	}

	@RequestMapping(value = "/solicitacoes/consulta/visualizar/{solicitacaoId}", method = RequestMethod.GET)
	public ModelAndView solicitacaoVisualizar(@PathVariable String solicitacaoId, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		ModelAndView modelAndView = new ModelAndView();

		SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaService.buscar(new Long(solicitacaoId));
		Paciente paciente = pacienteService.buscar(solicitacaoConsulta.getIdPaciente());
		Credenciado credenciado = credenciadoService.buscar(solicitacaoConsulta.getIdCredenciado());
		Documentacao documentacao = documentacaoService.buscar(paciente.getDocumentacao().getId());
		paciente.setDocumentacao(documentacao);

		String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
				.format(credenciado.getValorCobrado().doubleValue());

		solicitacaoConsulta.setValorConsultaFormatado(valor);

		SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();

		solicitacaoDTO.setPaciente(paciente);
		solicitacaoDTO.setCredenciado(credenciado);
		solicitacaoDTO.setSolicitacaoConsulta(solicitacaoConsulta);

		if (solicitacaoConsulta.getStatus().equalsIgnoreCase("APROVADO")
				|| solicitacaoConsulta.getStatus().equalsIgnoreCase("REPROVADO")
				|| solicitacaoConsulta.getStatus().equalsIgnoreCase("CONSULTA AGENDADA")) {
			modelAndView = new ModelAndView("solicitacao.consulta.detalhar");
			modelAndView.addObject("solicitacaoDTO", solicitacaoDTO);
			modelAndView.addObject("status", false);
			if (solicitacaoDTO.getSolicitacaoConsulta().getStatusEnum().equals(StatusEnum.APROVADO)) {
				modelAndView.addObject("status", true);
			}
		} else {
			modelAndView = new ModelAndView("solicitacao.consulta.visualizar");
			modelAndView.addObject("solicitacaoDTO", solicitacaoDTO);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/solicitacoes/consulta/salvar", method = RequestMethod.POST)
	public ModelAndView salvarSolicitacaoExame(@ModelAttribute SolicitacaoDTO solicitacaoDTO, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException, NegocioException {

		ModelAndView retorno = new ModelAndView("redirect:/solicitacoes/consulta");

		if (solicitacaoDTO.isReprovado() != null && solicitacaoDTO.isReprovado() == true) {
			if (solicitacaoDTO.getSolicitacaoConsulta().getId() != null
					&& solicitacaoDTO.getSolicitacaoConsulta().getId() != 0) {
				SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaService
						.buscar(solicitacaoDTO.getSolicitacaoConsulta().getId());
				solicitacaoConsulta.setStatus("REPROVADO");
				solicitacaoConsulta.setStatusEnum(StatusEnum.REPROVADO);

				if (solicitacaoDTO.getSolicitacaoConsulta().getMotivo().equalsIgnoreCase("")
						|| solicitacaoDTO.getSolicitacaoConsulta().getMotivo() == null) {
					solicitacaoConsulta.setMotivo("NÃO INFORMADO");
				} else {
					solicitacaoConsulta.setMotivo(solicitacaoDTO.getSolicitacaoConsulta().getMotivo());
				}
				solicitacaoConsultaService.alterar(solicitacaoConsulta);
				retorno.addObject(MESSAGE_ERROR, getMensagem("reprovacao.sucesso"));
			}
		}

		if (solicitacaoDTO.isAprovado() != null && solicitacaoDTO.isAprovado() == true) {
			if (solicitacaoDTO.getSolicitacaoConsulta().getId() != null
					&& solicitacaoDTO.getSolicitacaoConsulta().getId() != 0) {
				SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaService
						.buscar(solicitacaoDTO.getSolicitacaoConsulta().getId());
				solicitacaoConsulta.setStatus("APROVADO");
				solicitacaoConsulta.setStatusEnum(StatusEnum.APROVADO);
				solicitacaoConsultaService.alterar(solicitacaoConsulta);

				// FIXME Autorização e contas a receber
				Usuario usuario = HomeController.getUsuarioLogado(request);

				if (solicitacaoDTO != null) {
					Autorizacao autorizacao = new Autorizacao();
					autorizacao.setStatus(StatusAutorizacaoEnum.PAGO);
					autorizacao.setSituacao(SituacaoAutorizacaoEnum.AUTORIZADO);

					autorizacao.setValor(new BigDecimal(solicitacaoDTO.getSolicitacaoConsulta()
							.getValorConsultaFormatado().replace("R$ ", "").replace(".", "").replace(",", ".")));
					autorizacao.setValorPago(new BigDecimal(solicitacaoDTO.getSolicitacaoConsulta()
							.getValorConsultaFormatado().replace("R$ ", "").replace(".", "").replace(",", ".")));
					autorizacao.setDataAutorizacao(new Date());
					autorizacao.setCredenciado(solicitacaoDTO.getCredenciado());
					autorizacao.setPaciente(solicitacaoDTO.getPaciente());
					autorizacao.setCanal(CanalEnum.APP);
					autorizacao.setUsuarioAutorizado(usuario);
					autorizacaoService.salvar(autorizacao);

					ContasReceber contasReceber = new ContasReceber();
					contasReceber.setValor(autorizacao.getValor());
					contasReceber.setValorPago(autorizacao.getValorPago());
					contasReceber.setDataPagamento(autorizacao.getDataAutorizacao());
					contasReceber.setTipoContaEnum(TipoContaEnum.AUTORIZACAO);
					contasReceber.setStatus(StatusPagamentoEnum.PAGO);
					contasReceber.setCredenciado(autorizacao.getCredenciado());
					contasReceber.setAutorizacao(autorizacao);
					contasReceber.setUsuarioAutorizado(usuario);

					contasReceberService.salvar(contasReceber);
				}

				retorno.addObject(MESSAGE, getMensagem("aprovacao.sucesso"));
			}
		}

		return retorno;
	}

	@RequestMapping(value = "/solicitacoes/consulta/aprovar", method = RequestMethod.POST)
	public String aprovarConsulta(@ModelAttribute SolicitacaoDTO solicitacaoDTO, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		if (solicitacaoDTO.getSolicitacaoConsulta().getId() != null) {
			SolicitacaoConsulta solicitacaoConsulta = solicitacaoConsultaService
					.buscar(solicitacaoDTO.getSolicitacaoConsulta().getId());
			solicitacaoConsulta.setStatus("CONSULTA AGENDADA");
			solicitacaoConsulta.setStatusEnum(StatusEnum.CONSULTA_AGENDADA);
			solicitacaoConsultaService.alterar(solicitacaoConsulta);

			Paciente paciente = new Paciente();
			Medico medico = new Medico();
			Consulta consulta = new Consulta();
			Agendamento agendamento = new Agendamento();

			if (solicitacaoConsulta.getIdPaciente() != null) {
				paciente = pacienteService.buscar(solicitacaoConsulta.getIdPaciente());
			}
			if (solicitacaoConsulta.getIdCredenciado() != null) {
				medico = medicoService.buscar(solicitacaoConsulta.getIdCredenciado());
			}

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			int hora = calendar.get(Calendar.HOUR_OF_DAY);
			int minuto = calendar.get(Calendar.MINUTE);
			int ano = calendar.get(Calendar.YEAR);
			int mes = calendar.get(Calendar.MONTH) + 1;
			String min = String.valueOf(minuto);
			if (minuto < 10) {
				min = "0" + min;
			}
			agendamento.setHora(hora + ":" + min);

			agendamento.setDataConsulta(calendar.getTime());
			agendamento.setHoraConsulta(calendar.getTime());
			agendamento.setStatusAgendamentoEnum(StatusAgendamentoEnum.CONFIRMADO);
			agendamento.setDataHoraConfirmacao(GregorianCalendar.getInstance().getTime());
			agendamento.setTipoAgendamentoEnum(TipoAgendamentoEnum.PRESENCIAL);
			agendamento.setCliente(HomeController.getUsuarioLogado(request).getCliente());
			agendamento.setCodigoConsulta(solicitacaoConsulta.getCodigo());
			agendamento.setMedico(medico);
			agendamento.setPaciente(paciente);

			consulta.setMes(mes);
			consulta.setAno(ano);
			consulta.setPaciente(paciente);
			consulta.setMedico(medico);
			consulta.setStatus(StatusConsultaEnum.PAGO);
			consulta.setValorConsulta(consulta.getMedico().getValorConsulta());
			consulta.setDataConsulta(agendamento.getDataConsulta());
			consulta.setCodigoConsulta(solicitacaoConsulta.getCodigo());
			consulta.setAgendamento(agendamento);

			agendamentoService.salvar(agendamento);
			consultaService.salvar(consulta);
			agendamento.setConsulta(consulta);
			agendamentoService.alterar(agendamento);
		}

		model.addAttribute("solicitacaoConsulta", new SolicitacaoConsulta());
		model.addAttribute("solicitacoes", solicitacaoConsultaService.listar());

		return "solicitacao/consulta";
	}

}
