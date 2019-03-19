package br.com.eclinic.controler.relatorio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.datasource.RelatorioPacienteDataSource;
import br.com.eclinic.datasource.RelatorioReceitaDataSource;
import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.agendamento.StatusAgendamentoEnum;
import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.autorizacao.AutorizacaoDTO;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.contas.ContasPagar;
import br.com.eclinic.entity.contas.ContasPagarDTO;
import br.com.eclinic.entity.contas.ContasReceber;
import br.com.eclinic.entity.contas.ContasReceberDTO;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.receita.Medicamento;
import br.com.eclinic.entity.receita.Receita;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.relatorio.RelatorioFaturamentoDia;
import br.com.eclinic.relatorio.RelatorioFaturamentoMes;
import br.com.eclinic.service.autorizacao.AutorizacaoService;
import br.com.eclinic.service.consulta.ConsultaService;
import br.com.eclinic.service.contas.ContasPagarService;
import br.com.eclinic.service.contas.ContasReceberService;
import br.com.eclinic.service.paciente.PacienteService;

@Controller
public class RelatorioController extends EclinicController {

	@Autowired
	private ServletContext servletContext;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private ConsultaService consultaService;
	@Autowired
	private ContasPagarService contasPagarService;
	@Autowired
	private ContasReceberService contasReceberService;
	@Autowired
	private AutorizacaoService autorizacaoService;

	@RequestMapping(value = "/relatorio_paciente", method = RequestMethod.GET)
	public String relatorioCentroCusto(Locale locale, Model model, HttpServletRequest request) throws Exception {
		return "relatorio.paciente";
	}

	@RequestMapping(value = "/paciente/imprimir/{pk}", method = RequestMethod.GET)
	public String gerarCentroCusto(@PathVariable Long pk, Locale locale, Model model, HttpServletRequest request)
			throws Exception {
		Paciente paciente = pacienteService.buscar(pk);

		new RelatorioPacienteDataSource(paciente, model);
		model.addAttribute("pathJasper", servletContext.getRealPath("/resources/jasper/"));
		return "pacienteReport";
	}

	@RequestMapping(value = "/paciente/receita/gerar", method = RequestMethod.POST)
	public String gerarReceita(@ModelAttribute Receita receita, Model model, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws NegocioException {

		Paciente paciente = pacienteService.buscar(receita.getPaciente().getId());

		List<Medicamento> medicamentosBase = new ArrayList<Medicamento>();

		if (receita.getMedicamentos() != null) {
			for (Medicamento medicamento : receita.getMedicamentos()) {
				if (medicamento.getId() != null) {
					Medicamento medicamentoBase = pacienteService.buscarMedicamento(medicamento.getId());
					medicamentosBase.add(medicamentoBase);
				}
			}
		}

		receita.setPaciente(paciente);
		receita.setMedicamentos(medicamentosBase);

		new RelatorioReceitaDataSource(receita, model);
		model.addAttribute("pathJasper", servletContext.getRealPath("/resources/jasper/"));
		return "receitaReport";
	}

	@RequestMapping(value = "/relatorio-faturamento-dia", method = RequestMethod.GET)
	public String gerarRelatorioFaturamentoDia(Locale locale, Model model, HttpServletRequest request)
			throws Exception {

		model.addAttribute("relatorio", new RelatorioFaturamentoDia());

		return "relatorio-faturamento-dia";
	}

	@RequestMapping(value = "/relatorio-faturamento-dia/gerar", method = RequestMethod.POST)
	public String gerarRelatorioFaturamentoDiaImpressao(@ModelAttribute RelatorioFaturamentoDia formRelatorio,
			Locale locale, Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes)
			throws Exception {

		if (formRelatorio == null || formRelatorio.getDiaFormatado() == null
				|| formRelatorio.getDiaFormatado().equals("")) {

			model.addAttribute("relatorio", new RelatorioFaturamentoMes());
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem("relatorio.dia.obrigatorio"));
			return "relatorio-faturamento-dia";

		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

			// Cria Filtro
			Consulta consulta = new Consulta();
			consulta.setAgendamento(new Agendamento());
			consulta.setDataConsulta(format.parse(formRelatorio.getDiaFormatado()));
			consulta.getAgendamento().setStatusAgendamentoEnum(StatusAgendamentoEnum.ATENDIDO);

			List<Consulta> consultas = consultaService.consultarConsultaPorFiltro(consulta, usuarioLogado.getCliente());

			format = new SimpleDateFormat("dd-MM-yyyy");
			model.addAttribute("consultas", consultas);
			model.addAttribute("dataFormatada", format.format(consulta.getDataConsulta()));

			return "relatorio-faturamento-dia-gerar";
		}
	}

	@RequestMapping(value = "/relatorio/contas-pagar", method = RequestMethod.GET)
	public String relatorioContasPagar(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("contasDTO", new ContasPagarDTO());

		return "relatorio-contas-pagar";
	}

	@RequestMapping(value = "/relatorio/contas-pagar", method = RequestMethod.POST)
	public String gerarRelatorioContasPagar(@ModelAttribute ContasPagarDTO contasPagarDTO, BindingResult result,
			Model model, HttpServletRequest request) throws Exception {
		
		List<ContasPagar> contas = contasPagarService.consultarRelatorioPorPeriodo(contasPagarDTO.getDataInicial(), contasPagarDTO.getDataFinal());
		
		if(contas != null) {
			contas = contasPagarService.configurarGuiaRelatorio(contas);
		}

		model.addAttribute("contasDTO", contasPagarDTO);
		model.addAttribute("contas", contas);

		return "guia-relatorio-contas-pagar";
	}
	
	@RequestMapping(value = "/relatorio/contas-receber", method = RequestMethod.GET)
	public String relatorioContasReceber(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("contasDTO", new ContasReceberDTO());

		return "relatorio-contas-receber";
	}
	
	@RequestMapping(value = "/relatorio/contas-receber", method = RequestMethod.POST)
	public String gerarRelatorioContasReceber(@ModelAttribute ContasReceberDTO contasReceberDTO, BindingResult result,
			Model model, HttpServletRequest request) throws Exception {
		
		List<ContasReceber> contas = contasReceberService.consultarRelatorioPorPeriodo(contasReceberDTO.getDataInicial(), contasReceberDTO.getDataFinal());
		
		if(contas != null) {
			contas = contasReceberService.configurarGuiaRelatorio(contas);
		}
		
		model.addAttribute("contasDTO", contasReceberDTO);
		model.addAttribute("contas", contas);

		return "guia-relatorio-contas-receber";
	}
	
	@RequestMapping(value = "/relatorio/autorizacoes", method = RequestMethod.GET)
	public String relatorioautorizacoes(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("autorizacaoDTO", new AutorizacaoDTO());

		return "relatorio-autorizacoes";
	}
	
	@RequestMapping(value = "/relatorio/autorizacoes", method = RequestMethod.POST)
	public String gerarRelatorioAutorizacoes(@ModelAttribute AutorizacaoDTO autorizacaoDTO, BindingResult result,
			Model model, HttpServletRequest request) throws Exception {
		
		List<Autorizacao> autorizacoes = autorizacaoService.consultarRelatorioAutorizacoes(autorizacaoDTO.getDataInicial(), autorizacaoDTO.getDataFinal());
		
		if(autorizacoes != null) {
			autorizacoes = autorizacaoService.configurarGuiaRelatorio(autorizacoes);
		}
		
		model.addAttribute("autorizacaoDTO", autorizacaoDTO);
		model.addAttribute("autorizacoes", autorizacoes);

		return "guia-relatorio-autorizacoes";
	}

}
