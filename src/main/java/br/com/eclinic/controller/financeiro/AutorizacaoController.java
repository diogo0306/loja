package br.com.eclinic.controller.financeiro;

import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import br.com.eclinic.entity.agendamento.StatusPagamentoEnum;
import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.autorizacao.AutorizacaoDTO;
import br.com.eclinic.entity.autorizacao.CanalEnum;
import br.com.eclinic.entity.autorizacao.SituacaoAutorizacaoEnum;
import br.com.eclinic.entity.autorizacao.StatusAutorizacaoEnum;
import br.com.eclinic.entity.autorizacao.TipoAutorizacaoEnum;
import br.com.eclinic.entity.contas.ContasPagar;
import br.com.eclinic.entity.contas.ContasReceber;
import br.com.eclinic.entity.contas.TipoContaEnum;
import br.com.eclinic.entity.contas.TipoContaPagamentoEnum;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.fornecedor.Fornecedor;
import br.com.eclinic.entity.hospital.Hospital;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.autorizacao.AutorizacaoService;
import br.com.eclinic.service.contas.ContasPagarService;
import br.com.eclinic.service.contas.ContasReceberService;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.exame.ExameService;
import br.com.eclinic.service.fornecedor.FornecedorService;
import br.com.eclinic.service.hospital.HospitalService;
import br.com.eclinic.service.paciente.PacienteService;

@Controller
public class AutorizacaoController extends EclinicController {

	@Autowired
	private CredenciadoService credenciadoService;
	@Autowired
	private AutorizacaoService autorizacaoService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private FornecedorService fornecedorService;
	@Autowired
	private ExameService exameService;
	@Autowired
	private ContasReceberService contasReceberService;
	@Autowired
	private ContasPagarService contasPagarService;

	@RequestMapping(value = "/autorizacoes", method = RequestMethod.GET)
	public String autorizacoes(Model model, HttpServletRequest request) {

		model.addAttribute("autorizacao", new Autorizacao());
		model.addAttribute("credenciados", credenciadoService.listar());
		model.addAttribute("tipos", TipoAutorizacaoEnum.values());
		return "autorizacoes";
	}

	@RequestMapping(value = "/autorizacoes", method = RequestMethod.POST)
	public String pesquisar(@ModelAttribute Autorizacao autorizacao, BindingResult result, Model model,
			HttpServletRequest request) throws NegocioException {

		if (StringUtils.isNotEmpty(autorizacao.getCodicoTipoTransiente())) {
			autorizacao.setTipo(
					TipoAutorizacaoEnum.getEnumPorCodigo(Integer.parseInt(autorizacao.getCodicoTipoTransiente())));
		}

		List<Autorizacao> autorizacoes = autorizacaoService.consultar(autorizacao);

		if (!autorizacoes.isEmpty()) {
			for (Autorizacao auto : autorizacoes) {
				if (auto.getPaciente() != null) {
					Paciente paciente = pacienteService.buscar(auto.getPaciente().getId());
					auto.setPaciente(paciente);
				}
				if (auto.getCredenciado() != null) {
					Credenciado credenciado = credenciadoService.buscar(auto.getCredenciado().getId());
					auto.setCredenciado(credenciado);
				}
				if (auto.getDataAutorizacao() != null) {
					Format format = new SimpleDateFormat("dd/MM/yyyy");
					String data = format.format(auto.getDataAutorizacao());
					auto.setDataFormatada(data);
				}
				if (auto.getValor() != null) {
					String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
							.format(auto.getValor().doubleValue());
					auto.setValorFormatado(valor);
				}
			}
		}

		model.addAttribute("autorizacao", autorizacao);
		model.addAttribute("credenciados", credenciadoService.listar());
		model.addAttribute("tipos", TipoAutorizacaoEnum.values());
		model.addAttribute("autorizacoes", autorizacoes);

		return "autorizacoes";
	}

	@RequestMapping(value = "/autorizacao/incluir", method = RequestMethod.GET)
	public String incluir(Model model, HttpServletRequest request) {

		model.addAttribute("autorizacaoDTO", new AutorizacaoDTO());
		model.addAttribute("credenciados", credenciadoService.listar());
		model.addAttribute("hospitais", hospitalService.listar());
		model.addAttribute("fornecedores", fornecedorService.listar());
		model.addAttribute("exames", exameService.listar());
		model.addAttribute("pacientes", pacienteService.listar());
		model.addAttribute("tipos", TipoAutorizacaoEnum.values());

		return "autorizacao_incluir";
	}

	@RequestMapping(value = "/autorizacao/salvar", method = RequestMethod.POST)
	public String salvar(@ModelAttribute AutorizacaoDTO autorizacaoDTO, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		Usuario usuario = HomeController.getUsuarioLogado(request);

		if (autorizacaoDTO.getAutorizacao() != null) {
			autorizacaoDTO = autorizacaoService.configurarForm(autorizacaoDTO, request);
			Autorizacao autorizacao = autorizacaoDTO.getAutorizacao();
			autorizacao.setStatus(StatusAutorizacaoEnum.PAGO);
			autorizacao.setSituacao(SituacaoAutorizacaoEnum.AUTORIZADO);
			autorizacao.setCanal(CanalEnum.CLINICA);
			autorizacao.setUsuarioAutorizado(usuario);
			if(autorizacaoDTO.getExames() != null) {
				autorizacao.setExames(autorizacaoDTO.getExames());
			}
			autorizacaoService.salvar(autorizacao);
			
			ContasReceber contasReceber = new ContasReceber();
			contasReceber.setValor(autorizacao.getValor());
			contasReceber.setValorPago(autorizacao.getValorPago());
			contasReceber.setDataPagamento(autorizacao.getDataAutorizacao());
			contasReceber.setDataVencimento(autorizacao.getDataAutorizacao());
			contasReceber.setTipoContaEnum(TipoContaEnum.AUTORIZACAO);
			contasReceber.setStatus(StatusPagamentoEnum.PAGO);
			contasReceber.setCredenciado(autorizacao.getCredenciado());
			contasReceber.setAutorizacao(autorizacao);
			contasReceber.setUsuarioAutorizado(usuario);
			
			contasReceberService.salvar(contasReceber);
		}

		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		return "redirect:/autorizacoes";
	}
	
	@RequestMapping(value = "/autorizacao/gerar-orcamento", method = RequestMethod.POST)
	public String gerarOrxcamento(@ModelAttribute AutorizacaoDTO autorizacaoDTO, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		Usuario usuario = HomeController.getUsuarioLogado(request);
		
		if (autorizacaoDTO.getAutorizacao() != null) {
			autorizacaoDTO = autorizacaoService.configurarForm(autorizacaoDTO, request);
			Autorizacao autorizacao = autorizacaoDTO.getAutorizacao();
			autorizacao.setStatus(StatusAutorizacaoEnum.PENDENTE);
			autorizacao.setSituacao(SituacaoAutorizacaoEnum.ORCAMENTO);
			autorizacao.setCanal(CanalEnum.CLINICA);
			autorizacao.setUsuarioAutorizado(usuario);
			if(autorizacaoDTO.getExames() != null) {
				autorizacao.setExames(autorizacaoDTO.getExames());
			}
			autorizacaoService.salvar(autorizacao);
			
			ContasReceber contasReceber = new ContasReceber();
			contasReceber.setValor(autorizacao.getValor());
			contasReceber.setValorPago(autorizacao.getValorPago());
			contasReceber.setDataPagamento(autorizacao.getDataAutorizacao());
			contasReceber.setTipoContaEnum(TipoContaEnum.AUTORIZACAO);
			contasReceber.setStatus(StatusPagamentoEnum.PENDENTE);
			contasReceber.setCredenciado(autorizacao.getCredenciado());
			contasReceber.setAutorizacao(autorizacao);
			contasReceber.setUsuarioAutorizado(usuario);
			
			contasReceberService.salvar(contasReceber);
		}
		
		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		return "redirect:/autorizacoes";
	}

	@RequestMapping(value = "/autorizacao/adicionar-exame", method = RequestMethod.POST)
	public ModelAndView adicionarExame(@ModelAttribute AutorizacaoDTO autorizacaoDTO, BindingResult result, Model model,
			final HttpServletRequest request) {

		if (autorizacaoDTO.getExame() != null && autorizacaoDTO.getExame().getId() != null) {
			autorizacaoDTO = autorizacaoService.configurarExame(autorizacaoDTO, request);
		}

		ModelAndView mav = new ModelAndView("autorizacao_incluir");
		mav.addObject("autorizacaoDTO", autorizacaoDTO);
		mav.addObject("valorTotal", autorizacaoDTO.getValorTotalFormatado());
		mav.addObject("credenciados", credenciadoService.listar());
		mav.addObject("hospitais", hospitalService.listar());
		mav.addObject("fornecedores", fornecedorService.listar());
		mav.addObject("exames", exameService.listar());
		mav.addObject("pacientes", pacienteService.listar());
		mav.addObject("tipos", TipoAutorizacaoEnum.values());

		return mav;
	}
	
	@RequestMapping(value = "/autorizacao/detalhar/{id}", method = RequestMethod.GET)
	public String detalhar(@PathVariable String id, Model model) throws NegocioException {
		
		Autorizacao autorizacao = autorizacaoService.buscar(new Long(id));
		
		if(autorizacao != null) {
			autorizacao = autorizacaoService.configurarFormDetalhar(autorizacao);
			model.addAttribute("autorizacao", autorizacao);
			model.addAttribute("tipo", autorizacao.getTipo());
			model.addAttribute("valorTotal", autorizacao.getValorTotalFormatado());
			return "autorirazao_detalhar";
		} else {
			model.addAttribute("autorizacao", new Autorizacao());
			return "autorizacoes";
		}
	}
	
	@RequestMapping(value = "/autorizacao/confirmar", method = RequestMethod.POST)
	public String confirmarAutorizacao(@ModelAttribute Autorizacao autorizacao, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		Usuario usuario = HomeController.getUsuarioLogado(request);
		
		if(autorizacao.getId() != null) {
			Autorizacao auto = autorizacaoService.buscar(autorizacao.getId());
			auto.setStatus(StatusAutorizacaoEnum.REALIZADO);
			auto.setSituacao(SituacaoAutorizacaoEnum.AUTORIZADO);
			auto.setDataHoraExecucao(new Date());
			auto.setUsuarioAutorizado(usuario);
			autorizacaoService.alterar(auto);
			
			ContasPagar contasPagar = new ContasPagar();
			contasPagar.setValor(auto.getValorPago());
			contasPagar.setDataRealizado(new Date());
			contasPagar.setTipo(TipoContaPagamentoEnum.CREDENCIADO);
			contasPagar.setCredenciado(auto.getCredenciado());
			contasPagar.setAutorizacao(auto);
			contasPagar.setUsuarioAutorizado(usuario);
			contasPagar.setTipoPagamento(TipoContaEnum.AUTORIZACAO);
			contasPagar.setStatus(StatusPagamentoEnum.PENDENTE);
			contasPagarService.salvar(contasPagar);
		}
		
		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("autorizacao.sucesso"));
		return "redirect:/autorizacoes";
	}
	
	@RequestMapping(value = "/autorizacao/cancelar", method = RequestMethod.POST)
	public String cancelarAutorizacao(@ModelAttribute Autorizacao autorizacao, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		Usuario usuario = HomeController.getUsuarioLogado(request);
		
		if(autorizacao.getId() != null) {
			Autorizacao auto = autorizacaoService.buscar(autorizacao.getId());
			auto.setStatus(StatusAutorizacaoEnum.CANCELADO);
			auto.setSituacao(SituacaoAutorizacaoEnum.CANCELADO);
			auto.setDataCancelamento(new Date());
			auto.setUsuarioAutorizado(usuario);
			autorizacaoService.alterar(auto);
		}
		
		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("cancelamento.sucesso"));
		return "redirect:/autorizacoes";
	}

	@ResponseBody
	@RequestMapping(value = "/autorizacao/buscar-hospital/{idHospital}", method = RequestMethod.GET)
	public Hospital buscarInfoHospital(@PathVariable String idHospital, Model model) {
		Hospital hospital = hospitalService.buscar(new Long(idHospital));
		if (hospital != null) {
			if (hospital.getValorCobrado() != null) {
				String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(hospital.getValorCobrado().doubleValue());
				hospital.setValorTransiente(valor);
			}
		}
		return hospital;
	}

	@ResponseBody
	@RequestMapping(value = "/autorizacao/buscar-fornecedor/{idFornecedor}", method = RequestMethod.GET)
	public Fornecedor buscarInfoFornecedor(@PathVariable String idFornecedor, Model model) {
		Fornecedor fornecedor = fornecedorService.buscar(new Long(idFornecedor));
		if (fornecedor != null) {
			if (fornecedor.getValorCobrado() != null) {
				String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(fornecedor.getValorCobrado().doubleValue());
				fornecedor.setValorTransiente(valor);
			}
		}
		return fornecedor;
	}

	@ResponseBody
	@RequestMapping(value = "/autorizacao/buscar-credenciado/{idCredenciado}", method = RequestMethod.GET)
	public Credenciado buscarInfoCredenciado(@PathVariable String idCredenciado, Model model) {
		Credenciado credenciado = credenciadoService.buscar(new Long(idCredenciado));
		credenciado.setEspecialidades(null);
		credenciado.setConsultas(null);
		if (credenciado != null) {
			if (credenciado.getValorCobrado() != null) {
				String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(credenciado.getValorCobrado().doubleValue());
				credenciado.setValorCobradoTransiente(valor);
			}
		}
		return credenciado;
	}
	
	@RequestMapping(value = "/autorizacao/emitir-guia", method = RequestMethod.POST)
	public String exibirGuia(@ModelAttribute Autorizacao autorizacao, BindingResult result, 
			Model model, HttpServletRequest request) throws Exception {
		
		Autorizacao auto = new Autorizacao();
		
		if(autorizacao.getId() != null) {
			auto = autorizacaoService.buscar(autorizacao.getId());
		}
		
		if(auto.getTipo().equals(TipoAutorizacaoEnum.CONSULTA)) {
			auto = autorizacaoService.configurarGuiaConsulta(auto);
			model.addAttribute("autorizacao", auto);
			return "guia_consulta";
		} else if (auto.getTipo().equals(TipoAutorizacaoEnum.SP_SADP)) {
			auto = autorizacaoService.configurarGuiaProc(auto);
			model.addAttribute("autorizacao", auto);
			return "guia_sadp";
		} else {
			auto = autorizacaoService.configurarGuiaCirurgia(auto);
			model.addAttribute("autorizacao", auto);
			return "guia_cirurgia";
		}
	}
	
	@RequestMapping(value = "/autorizacao/emitir-guias", method = RequestMethod.POST)
	public String exibirGuias(@PathVariable String id, BindingResult result, 
			Model model, HttpServletRequest request) throws Exception {
		
		Autorizacao auto = autorizacaoService.buscar(new Long(id));
		
		if(auto.getTipo().equals(TipoAutorizacaoEnum.CONSULTA)) {
			auto = autorizacaoService.configurarGuiaConsulta(auto);
			model.addAttribute("autorizacao", auto);
			return "guia_consulta";
		} else if (auto.getTipo().equals(TipoAutorizacaoEnum.SP_SADP)) {
			auto = autorizacaoService.configurarGuiaProc(auto);
			model.addAttribute("autorizacao", auto);
			return "guia_sadp";
		} else {
			auto = autorizacaoService.configurarGuiaCirurgia(auto);
			model.addAttribute("autorizacao", auto);
			return "guia_cirurgia";
		}		
	}
}
