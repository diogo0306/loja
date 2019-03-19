package br.com.eclinic.controler.paciente;

import java.math.BigDecimal;
import java.text.Format;
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
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.contrato.CobrancaContrato;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.contrato.DiaVencimentoEnum;
import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.entity.documentacao.Documentacao;
import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.entity.enuns.TipoPessoaEnum;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.entity.medico.OrgaoEmissorEnum;
import br.com.eclinic.entity.medico.SexoEnum;
import br.com.eclinic.entity.medico.UfEnum;
import br.com.eclinic.entity.paciente.Cobranca;
import br.com.eclinic.entity.paciente.CobrancaAvulsa;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.paciente.PacienteDTO;
import br.com.eclinic.entity.paciente.TipoControleEnum;
import br.com.eclinic.entity.paciente.TipoTaxaEnum;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.plano.TipoPlanoSaudeEnum;
import br.com.eclinic.entity.receita.Receita;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.cliente.ClienteService;
import br.com.eclinic.service.cobranca.CobrancaService;
import br.com.eclinic.service.cobrancaAvulsa.CobrancaAvulsaService;
import br.com.eclinic.service.cobrancaContrato.CobrancaContratoService;
import br.com.eclinic.service.contrato.ContratoService;
import br.com.eclinic.service.dependente.DependenteService;
import br.com.eclinic.service.documentacao.DocumentacaoService;
import br.com.eclinic.service.paciente.EnderecoService;
import br.com.eclinic.service.paciente.PacienteService;
import br.com.eclinic.service.plano.PlanoService;
import br.com.eclinic.validator.PacienteValidator;
import br.com.eclinic.validator.PlanoValidator;

@SuppressWarnings({ "unused" })
@Controller
public class PacienteController extends EclinicController {

	private static final String VAZIO = "";
	private static final String MODEL_ATTR_ENTIDADE = "paciente";
	private static final String URL_REQUEST_LISTA = "/pacientes";
	private static final String URL_REQUEST_SALVAR = "/paciente/salvar";
	private static final String URL_REQUEST_ALTERAR = "/paciente/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/paciente/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/paciente/excluir";
	private static final String URL_REQUEST_SALVAR_PLANO = "/paciente/plano/salvar";

	@Autowired
	private PacienteService service;
	@Autowired
	private PlanoService planoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private DependenteService dependenteService;
	@Autowired
	private CobrancaService cobrancaService;
	@Autowired
	private CobrancaContratoService cobrancaContratoService;
	@Autowired
	private CobrancaAvulsaService cobrancaAvulsaService;
	@Autowired
	private ContratoService contratoService;
	@Autowired
	private DocumentacaoService documentacaoService;
	@Autowired
	private EnderecoService enderecoService;
	

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String pacientesPesquisar(@ModelAttribute Paciente paciente, Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		return listar(paciente, locale, model, request);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String pacientes(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new Paciente());

		return "pacientes";
	}

	private String listar(Paciente paciente, Locale locale, Model model, HttpServletRequest request) {

		List<Paciente> pacientes = new ArrayList<Paciente>();
		if (paciente != null && (paciente.getNome() != null || paciente.getCpf() != null || paciente.getRg() != null
				|| paciente.getDataInclusaoFormatada() != null)) {
			Paciente pacienteConsulta = new Paciente();
			if (paciente.getNome() != null) {
				pacienteConsulta.setNome(paciente.getNome());
			}
			if (paciente.getRg() != null) {
				pacienteConsulta.setRg(paciente.getRg());
			}
			if (paciente.getCpf() != null) {
				pacienteConsulta.setCpf(paciente.getCpf());
			}

			if (paciente.getDataInclusaoFormatada() != null && !paciente.getDataInclusaoFormatada().isEmpty()) {

				String dia = paciente.getDataInclusaoFormatada().substring(0, 2);
				String mes = paciente.getDataInclusaoFormatada().substring(3, 5);
				String ano = paciente.getDataInclusaoFormatada().substring(6, 10);

				GregorianCalendar dataInclusaoPesquisa = new GregorianCalendar();
				dataInclusaoPesquisa.setTime(new Date());
				dataInclusaoPesquisa.set(Calendar.YEAR, Integer.parseInt(ano));
				dataInclusaoPesquisa.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
				dataInclusaoPesquisa.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
				dataInclusaoPesquisa.set(Calendar.HOUR_OF_DAY, 0);
				dataInclusaoPesquisa.set(Calendar.MINUTE, 0);
				dataInclusaoPesquisa.set(Calendar.SECOND, 0);

				paciente.setDataInclusao(dataInclusaoPesquisa.getTime());

				pacienteConsulta.setDataInclusao(paciente.getDataInclusao());
			}

			pacientes = service.consultar(pacienteConsulta);
		}

		configurarPaginacao(pacientes, model);
		Paciente pacienteBind = new Paciente();
		pacienteBind.setNome(paciente.getNome());
		model.addAttribute(MODEL_ATTR_ENTIDADE, pacienteBind);
		model.addAttribute("pacientes", getPagedListHolder().getPageList());

		return "pacientes";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Locale locale, Model model, HttpServletRequest request) throws Exception {

		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		PacienteDTO pacienteDTO = new PacienteDTO();
		Paciente paciente = new Paciente();

		// paciente.setUsuarioCadastro(usuarioLogado);
		// paciente.setUsuarioUltimaAlteracao(usuarioLogado);

		pacienteDTO.setPaciente(paciente);

		model.addAttribute("pacienteDTO", pacienteDTO);
		model.addAttribute("sexos", SexoEnum.values());
		model.addAttribute("orgaos", OrgaoEmissorEnum.values());
		model.addAttribute("uf", UfEnum.values());

		return "paciente_incluir";
	}

	@RequestMapping(value = "/paciente/salvar_paciente", method = RequestMethod.POST)
	public ModelAndView salvarSolicitacaoPaciente(@ModelAttribute PacienteDTO pacienteDTO, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException {

		if (pacienteDTO.getPaciente().getCodigoSexoTransiente().equalsIgnoreCase("")) {
			pacienteDTO.getPaciente().setCodigoSexoTransiente(null);
		}
		if (pacienteDTO.getPaciente().getDataNascimentoFormatada().equalsIgnoreCase("")) {
			pacienteDTO.getPaciente().setDataNascimentoFormatada(null);
		}

		ModelAndView modelAndView = new ModelAndView("paciente_incluir");
		modelAndView.addObject("pacienteDTO", pacienteDTO);
		modelAndView.addObject("sexos", SexoEnum.values());

		if (pacienteDTO.getPaciente().getDataNascimentoFormatada() != null) {
			configurarDataPaciente(pacienteDTO);
		}

		if (pacienteDTO.getPaciente().getCodigoSexoTransiente() != null) {
			pacienteDTO.getPaciente().setSexoEnum(
					SexoEnum.getEnumPorCodigo(Integer.parseInt(pacienteDTO.getPaciente().getCodigoSexoTransiente())));
		}

		if (pacienteDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != null
				&& pacienteDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != VAZIO) {
			pacienteDTO.getPaciente().getDocumentacao().setOrgaoEmissorEnum(OrgaoEmissorEnum.getEnumPorCodigo(
					Integer.parseInt(pacienteDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente())));
		}

		if (pacienteDTO.getPaciente().getEndereco().getEstadoTransiente() != null
				&& pacienteDTO.getPaciente().getEndereco().getEstadoTransiente() != VAZIO) {
			pacienteDTO.getPaciente().getEndereco().setEstado(UfEnum
					.getEnumPorCodigo(Integer.parseInt(pacienteDTO.getPaciente().getEndereco().getEstadoTransiente())));
		}

		Paciente paciente = new Paciente();
		paciente = pacienteDTO.getPaciente();
		paciente.setDataNascimento(pacienteDTO.getDataPaciente());
		paciente.setCliente(HomeController.getUsuarioLogado(request).getCliente());

		if (service.consultarCpf(paciente) != null) {
			modelAndView.addObject(MESSAGE, getMensagem("paciente.inclusao.erro.existe"));
			return modelAndView;
		}

		PacienteValidator validator = new PacienteValidator();
		validator.validate(paciente, result);

		if (result.hasErrors()) {
			modelAndView.addObject(MESSAGE_ERROR, getMensagem("informar.campo"));
			return modelAndView;
		} else {
			service.salvar(paciente);
			ModelAndView retorno = new ModelAndView("redirect:/pacientes");
			return retorno;
		}
	}

	@RequestMapping(value = "/paciente/adicionar/cobranca_avulsa" + "/{pk}", method = RequestMethod.GET)
	public String adicionarCobrancaAvulsaPaciente(@PathVariable Long pk, Model model) throws Exception {

		Paciente pacienteBanco = service.buscar(pk);
		if (pacienteBanco.getId() != null) {
			PacienteDTO pacienteDTO = new PacienteDTO();
			pacienteDTO.setPaciente(pacienteBanco);

			model.addAttribute("pacienteDTO", pacienteDTO);

		}

		return "cobranca_avulsa_incluir";
	}

	@RequestMapping(value = "/paciente/adicionar/dependente" + "/{pk}", method = RequestMethod.GET)
	public String adicionarDependentePaciente(@PathVariable Long pk, Model model) throws Exception {

		Paciente pacienteBanco = service.buscar(pk);
		if (pacienteBanco.getId() != null) {
			PacienteDTO pacienteDTO = new PacienteDTO();
			pacienteDTO.setPaciente(pacienteBanco);

			model.addAttribute("pacienteDTO", pacienteDTO);
			model.addAttribute("sexos", SexoEnum.values());
		}

		return "dependente_incluir";
	}

	@RequestMapping(value = "/paciente/adicionar/cobranca_avulsa", method = RequestMethod.POST)
	public ModelAndView adicionarCobrancaAvulsa(@ModelAttribute PacienteDTO pacienteDTO, BindingResult result,
			Model model, HttpServletRequest request) throws ParseException {

		List<CobrancaAvulsa> listaCobrancaAvulsa = pacienteDTO.getListaCobrancaAvulsa();

		if (listaCobrancaAvulsa == null) {
			listaCobrancaAvulsa = new ArrayList<CobrancaAvulsa>();
		}

		CobrancaAvulsa cobrancaAvulsa = new CobrancaAvulsa();
		cobrancaAvulsa.setNome(pacienteDTO.getCobrancaAvulsa().getNome());
		cobrancaAvulsa.setDataFormatada(pacienteDTO.getCobrancaAvulsa().getDataFormatada());

		if (pacienteDTO.getCobrancaAvulsa().getValorTransiente() != ""
				&& !pacienteDTO.getCobrancaAvulsa().getValorTransiente().isEmpty()) {
			cobrancaAvulsa.setValor(new BigDecimal(
					pacienteDTO.getCobrancaAvulsa().getValorTransiente().replace(".", "").replace(",", ".")));
			cobrancaAvulsa.setValorTransiente(pacienteDTO.getCobrancaAvulsa().getValorTransiente());
		}

		if (pacienteDTO.getCobrancaAvulsa().getDataFormatada() != null
				&& pacienteDTO.getCobrancaAvulsa().getDataFormatada() != "") {
			Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd")
					.parse(pacienteDTO.getCobrancaAvulsa().getDataFormatada());
			cobrancaAvulsa.setData(dataFormatada);
		}

		listaCobrancaAvulsa.add(cobrancaAvulsa);

		pacienteDTO.setListaCobrancaAvulsa(listaCobrancaAvulsa);
		pacienteDTO.setCobrancaAvulsa(null);

		ModelAndView mav = new ModelAndView("cobranca_avulsa_incluir");
		mav.addObject("pacienteDTO", pacienteDTO);

		return mav;
	}

	@RequestMapping(value = "/paciente/acidionar/dependente", method = RequestMethod.POST)
	public ModelAndView adicionarDependente(@ModelAttribute PacienteDTO pacienteDTO, BindingResult result, Model model,
			HttpServletRequest request) throws ParseException {

		List<Dependente> listaDepentente = pacienteDTO.getListaDependente();

		if (listaDepentente == null) {
			listaDepentente = new ArrayList<Dependente>();
		}

		Dependente dependente = new Dependente();
		dependente.setNome(pacienteDTO.getDependente().getNome());
		dependente.setFiliacaoMae(pacienteDTO.getDependente().getFiliacaoMae());

		if (pacienteDTO.getDependente().getSexoTransiente() != null
				&& pacienteDTO.getDependente().getSexoTransiente() != "") {
			dependente.setSexo(
					SexoEnum.getEnumPorCodigo(Integer.parseInt(pacienteDTO.getDependente().getSexoTransiente())));

		}

		dependente.setCpf(pacienteDTO.getDependente().getCpf());
		dependente.setRg(pacienteDTO.getDependente().getRg());
		dependente.setOrgaoExpedidor(pacienteDTO.getDependente().getOrgaoExpedidor());

		if (pacienteDTO.getDependente().getDataFormatada() != null
				&& pacienteDTO.getDependente().getDataFormatada() != "") {
			Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd")
					.parse(pacienteDTO.getDependente().getDataFormatada());
			dependente.setDataNascimento(dataFormatada);
		}

		listaDepentente.add(dependente);

		pacienteDTO.setListaDependente(listaDepentente);
		pacienteDTO.setDependente(null);

		ModelAndView mav = new ModelAndView("dependente_incluir");
		mav.addObject("pacienteDTO", pacienteDTO);
		mav.addObject("sexos", SexoEnum.values());

		return mav;
	}

	@RequestMapping(value = "/salvar/cobranca_avulsa", method = RequestMethod.POST)
	public String salvarCobrancaAvulsa(@ModelAttribute PacienteDTO pacienteDTO, BindingResult bindingResult,
			Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes) throws Exception {

		if (pacienteDTO.getPaciente().getId() != null) {
			Paciente paciente = service.buscar(pacienteDTO.getPaciente().getId());
			for (CobrancaAvulsa cobrancaAvulsa : pacienteDTO.getListaCobrancaAvulsa()) {
				cobrancaAvulsa.setPacienteVinculo(paciente);

				if (cobrancaAvulsa.getDataFormatada() != null && cobrancaAvulsa.getDataFormatada() != "") {
					Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(cobrancaAvulsa.getDataFormatada());
					cobrancaAvulsa.setData(dataFormatada);
				}
				cobrancaAvulsaService.salvar(cobrancaAvulsa);
			}
		}

		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		return "redirect:../pacientes";
	}

	@RequestMapping(value = "/salvar/dependente", method = RequestMethod.POST)
	public String salvarDependente(@ModelAttribute PacienteDTO pacienteDTO, BindingResult bindingResult, Model model,
			HttpServletRequest request, final RedirectAttributes redirectAttributes) throws Exception {

		if (pacienteDTO.getPaciente().getId() != null) {
			Paciente paciente = service.buscar(pacienteDTO.getPaciente().getId());
			for (Dependente dependente : pacienteDTO.getListaDependente()) {
				dependente.setPacienteVinculo(paciente);
				if (dependente.getDataFormatada() != null && dependente.getDataFormatada() != "") {
					Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(dependente.getDataFormatada());
					dependente.setDataNascimento(dataFormatada);
				}
				dependenteService.salvar(dependente);
			}
		}

		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		return "redirect:../pacientes";
	}

	@RequestMapping(value = "/paciente/visualizar/dados_cobranca" + "/{pk}", method = RequestMethod.GET)
	private String visualizarDadosCobranca(@PathVariable Long pk, Model model) throws NegocioException {
		Paciente paciente = service.buscar(pk);
		Cobranca cobranca = cobrancaService.buscar(paciente.getDadosCobranca().getId());
		if (cobranca.getTipoTaxaEnum().getDescricao().equalsIgnoreCase("Valor")) {
			String numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
					.format(cobranca.getTaxaAdesao().doubleValue());
			cobranca.setTaxaAdesaoTransiente(numberFormat);
		} else {
			cobranca.setTaxaAdesaoTransiente(cobranca.getTaxaAdesao().toString().replace(".", ",") + " %");
		}
		paciente.setDadosCobranca(cobranca);
		model.addAttribute("paciente", paciente);
		return "dados_cobranca_visualizar";
	}

	@RequestMapping(value = "/paciente/alterar/dados_cobranca" + "/{pk}", method = RequestMethod.GET)
	private String alterarDadosCobranca(@PathVariable Long pk, Model model) throws NegocioException {
		Paciente paciente = service.buscar(pk);
		Cobranca cobranca = cobrancaService.buscar(paciente.getDadosCobranca().getId());
		cobranca.setTipoControleTransiente(String.valueOf(cobranca.getTipoControleEnum().getCodigo()));
		cobranca.setTipoTaxaTransiente(String.valueOf(cobranca.getTipoTaxaEnum().getCodigo()));
		if (cobranca.getTipoTaxaEnum().getDescricao().equalsIgnoreCase("Valor")) {
			cobranca.setTaxaAdesaoTransiente(cobranca.getTaxaAdesao().toString());
		} else {
			cobranca.setTaxaAdesaoPercentualTransiente(cobranca.getTaxaAdesao().toString());
		}
		paciente.setDadosCobranca(cobranca);
		if (paciente.getId() != null && paciente.getId() != 0) {
			cobranca.setIdPaciente(paciente.getId());
			cobranca.setNomePaciente(paciente.getNome());
		}

		model.addAttribute("cobranca", cobranca);
		model.addAttribute("tipos", TipoControleEnum.values());
		model.addAttribute("tipoTaxa", TipoTaxaEnum.values());

		return "dados_cobranca_alterar";
	}

	@RequestMapping(value = "/paciente/alterar/dados_cobranca", method = RequestMethod.POST)
	public String alterar(@ModelAttribute @Valid Cobranca cobranca, BindingResult result, RedirectAttributes attributes,
			Model model) throws Exception {

		String vazio = "";

		if (result.hasErrors()) {
			model.addAttribute("cobranca", cobranca);
			model.addAttribute("tipos", TipoControleEnum.values());
			model.addAttribute("tipoTaxa", TipoTaxaEnum.values());
			return "dados_cobranca_alterar";
		}

		if (cobranca.getTipoTaxaTransiente() != null && cobranca.getTipoTaxaTransiente() != vazio) {
			cobranca.setTipoTaxaEnum(TipoTaxaEnum.getEnumPorCodigo(Integer.parseInt(cobranca.getTipoTaxaTransiente())));
		}

		if (cobranca.getTaxaAdesaoTransiente() != null && cobranca.getTaxaAdesaoTransiente() != vazio
				&& cobranca.getTaxaAdesaoTransiente() != "0,00") {
			cobranca.setTaxaAdesao(
					new BigDecimal(cobranca.getTaxaAdesaoTransiente().replace(".", "").replace(",", ".")));
		}

		if (cobranca.getTaxaAdesaoPercentualTransiente() != null
				&& cobranca.getTaxaAdesaoPercentualTransiente() != vazio
				&& cobranca.getTaxaAdesaoPercentualTransiente() != "0,00") {
			cobranca.setTaxaAdesao(
					new BigDecimal(cobranca.getTaxaAdesaoPercentualTransiente().replace(".", "").replace(",", ".")));
		}

		if (cobranca.getTipoControleTransiente() != null && cobranca.getTipoControleTransiente() != vazio) {
			cobranca.setTipoControleEnum(
					TipoControleEnum.getEnumPorCodigo(Integer.parseInt(cobranca.getTipoControleTransiente())));
		}

		cobrancaService.alterar(cobranca);

		attributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		return "redirect:/pacientes";
	}

	@RequestMapping(value = "/paciente/adicionar/dados_cobranca" + "/{pk}", method = RequestMethod.GET)
	private String adicionarDadosCobranca(@PathVariable Long pk, Model model) throws NegocioException {
		Paciente paciente = service.buscar(pk);
		Cobranca cobranca = new Cobranca();
		if (paciente.getId() != null && paciente.getId() != 0) {
			cobranca.setIdPaciente(paciente.getId());
			cobranca.setNomePaciente(paciente.getNome());
		}

		model.addAttribute("cobranca", cobranca);
		model.addAttribute("tipos", TipoControleEnum.values());
		model.addAttribute("tipoTaxa", TipoTaxaEnum.values());

		return "dados_cobranca_incluir";
	}

	@RequestMapping(value = "/paciente/adicionar/dados_cobranca", method = RequestMethod.POST)
	public String Salvar(@ModelAttribute @Valid Cobranca cobranca, BindingResult result, RedirectAttributes attributes,
			Model model) throws Exception {

		String vazio = "";

		if (result.hasErrors()) {
			model.addAttribute("cobranca", cobranca);
			model.addAttribute("tipos", TipoControleEnum.values());
			model.addAttribute("tipoTaxa", TipoTaxaEnum.values());
			return "dados_cobranca_incluir";
		}

		if (cobranca.getTipoTaxaTransiente() != null && cobranca.getTipoTaxaTransiente() != vazio) {
			cobranca.setTipoTaxaEnum(TipoTaxaEnum.getEnumPorCodigo(Integer.parseInt(cobranca.getTipoTaxaTransiente())));
		}

		if (cobranca.getTaxaAdesaoTransiente() != null && cobranca.getTaxaAdesaoTransiente() != vazio) {
			cobranca.setTaxaAdesao(
					new BigDecimal(cobranca.getTaxaAdesaoTransiente().replace(".", "").replace(",", ".")));
		}

		if (cobranca.getTipoControleTransiente() != null && cobranca.getTipoControleTransiente() != vazio) {
			cobranca.setTipoControleEnum(
					TipoControleEnum.getEnumPorCodigo(Integer.parseInt(cobranca.getTipoControleTransiente())));
		}

		Paciente paciente = new Paciente();
		cobrancaService.salvar(cobranca);

		if (cobranca.getIdPaciente() != null) {
			paciente = service.buscar(cobranca.getIdPaciente());
			paciente.setDadosCobranca(cobranca);
			service.alterar(paciente);
		}

		attributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		return "redirect:/pacientes";
	}

	private void configurarDataPaciente(PacienteDTO pacienteDTO) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy")
				.parse(pacienteDTO.getPaciente().getDataNascimentoFormatada());
		pacienteDTO.setDataPaciente(dataFormatada);
	}

	private void configurarDataNascimento(Paciente paciente) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(paciente.getDataNascimentoFormatada());
		paciente.setDataNascimento(dataFormatada);
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterarBeneficiario(@PathVariable Long pk, Model model) throws Exception {
			
		PacienteDTO pacienteDTO = new PacienteDTO();
		
		Paciente pacienteAlteracao = service.buscar(pk);
		Contrato contrato = contratoService.buscarPorBeneficiario(pacienteAlteracao.getId());
		List<Dependente> dependentes = dependenteService.listarPorPaciente(pacienteAlteracao.getId());
		Plano plano = planoService.buscar(contrato.getPlano().getId());
		List<CobrancaContrato> cobrancasContrato = cobrancaContratoService.listarPorPaciente(pacienteAlteracao.getId());
		List<CobrancaAvulsa> cobrancasAvulsas = cobrancaAvulsaService.listarPorPaciente(pacienteAlteracao.getId());
		pacienteDTO.setPaciente(pacienteAlteracao);
		pacienteDTO.setContrato(contrato);
		pacienteDTO.setListaDependente(dependentes);
		pacienteDTO.setPlano(plano);
		pacienteDTO.setListaCobrancaContrato(cobrancasContrato);
		pacienteDTO.setListaCobrancaAvulsa(cobrancasAvulsas);
		
		if (pacienteAlteracao != null) {
			
			pacienteAlteracao.setCodigoSexoTransiente(
					String.valueOf(pacienteAlteracao.getSexoEnum().getCodigo()));
			pacienteAlteracao.getEndereco().setEstadoTransiente(
					String.valueOf(pacienteAlteracao.getEndereco().getEstado().getCodigo()));
			pacienteAlteracao.getDocumentacao().setOrgaoEmissorTransiente(
					String.valueOf(pacienteAlteracao.getDocumentacao().getOrgaoEmissorEnum().getCodigo()));

			model.addAttribute("pacienteDTO", pacienteDTO);
			model.addAttribute("sexos", SexoEnum.values());
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("orgaos", OrgaoEmissorEnum.values());
			model.addAttribute("diaVencimento", DiaVencimentoEnum.values());
			model.addAttribute("planos", planoService.listar());

			return "paciente_alterar";
		} else {
			return "redirect:../pacientes";
		}
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {
		
		PacienteDTO pacienteDTO = new PacienteDTO();
		Paciente pacienteVisualizar = service.buscar(pk);
		Contrato contrato = contratoService.buscarPorBeneficiario(pacienteVisualizar.getId());
		List<Dependente> dependentes = dependenteService.listarPorPaciente(pacienteVisualizar.getId());
		List<CobrancaAvulsa> cobrancasAvulsas = cobrancaAvulsaService.listarPorPaciente(pacienteVisualizar.getId());
		List<CobrancaContrato> cobrancasContrato = cobrancaContratoService.listarPorPaciente(pacienteVisualizar.getId()); 
		Plano plano = planoService.buscar(contrato.getPlano().getId());
		pacienteDTO.setPaciente(pacienteVisualizar);
		pacienteDTO.setContrato(contrato);
		pacienteDTO.setListaDependente(dependentes);
		pacienteDTO.setPlano(plano);
		pacienteDTO.setListaCobrancaAvulsa(cobrancasAvulsas);
		pacienteDTO.setListaCobrancaContrato(cobrancasContrato);
		
		for(CobrancaContrato cobranca : pacienteDTO.getListaCobrancaContrato()) {
			Format format = new SimpleDateFormat("dd/MM/yyyy");
			String data = format.format(cobranca.getDataVencimento());
			cobranca.setDataFormatada(data);
		}
		
		if (pacienteVisualizar != null) {

			model.addAttribute("pacienteDTO", pacienteDTO);
			model.addAttribute("sexos", SexoEnum.values());
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("orgaos", OrgaoEmissorEnum.values());
			model.addAttribute("diaVencimento", DiaVencimentoEnum.values());
			model.addAttribute("planos", planoService.listar());

			return "paciente_visualizar";
		} else {
			return "redirect:../pacientes";
		}
		
	}

	@RequestMapping(value = "/paciente/visualizar/cobranca_avulsa" + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizarCobrancaAvulsa(@PathVariable Long pk, Model model) throws Exception {

		Paciente pacienteAlteracao = service.buscar(pk);

		List<CobrancaAvulsa> cobrancaAvulsas = cobrancaAvulsaService.listarPorPaciente(pacienteAlteracao.getId());
		List<CobrancaContrato> cobrancaContratos = cobrancaContratoService.listarPorPaciente(pacienteAlteracao.getId());
		if (pacienteAlteracao != null) {

			model.addAttribute(MODEL_ATTR_ENTIDADE, pacienteAlteracao);
			model.addAttribute("listaCobrancaAvulsa", cobrancaAvulsas);
			model.addAttribute("listaCobrancaContrato", cobrancaContratos);
			return "cobranca_avulsa_visualizar";
		} else {
			return "redirect:../pacientes";
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public String alterar(@ModelAttribute PacienteDTO pacienteDTO, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException, NegocioException {

		Paciente paciente = new Paciente();
		Documentacao documentacao = new Documentacao();
		Endereco endereco = new Endereco();
		
		if (pacienteDTO.getPaciente() != null) {
			pacienteDTO = service.configurarFormAlterar(pacienteDTO, request);
			paciente = service.buscar(pacienteDTO.getPaciente().getId());
			documentacao = documentacaoService.buscar(paciente.getDocumentacao().getId());
			endereco = enderecoService.buscar(new Long(paciente.getEndereco().getId()));
		}
		
		try {
			pacienteDTO.getPaciente().getDocumentacao().setId(documentacao.getId());
			documentacaoService.alterar(pacienteDTO.getPaciente().getDocumentacao());
			pacienteDTO.getPaciente().getEndereco().setId(endereco.getId());
			enderecoService.alterar(pacienteDTO.getPaciente().getEndereco());
			pacienteDTO.getPaciente().setEndereco(pacienteDTO.getPaciente().getEndereco());
			pacienteDTO.getPaciente().setDocumentacao(pacienteDTO.getPaciente().getDocumentacao());
			pacienteDTO.getPaciente().setId(paciente.getId());
			service.alterar(pacienteDTO.getPaciente());
		} catch (Exception e) {
			new Exception(e);
		}
		
		redirectAttributes.addFlashAttribute(MESSAGE, "Beneficiário alterado com sucesso.");
		return "redirect:/pacientes";
	}

	@RequestMapping(value = "/paciente/excluir/cobranca_avulsa", method = RequestMethod.POST)
	public String excluirCobrancaAvulsa(@ModelAttribute CobrancaAvulsa cobrancaAvulsa, Model model,
			final RedirectAttributes redirectAttributes) {

		try {
			cobrancaAvulsa = cobrancaAvulsaService.buscar(cobrancaAvulsa.getId());
			cobrancaAvulsaService.excluir(cobrancaAvulsa);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (NegocioException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, e.getMessage());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}

		return "redirect:/cobranca_avulsa_visualizar";
	}

	/*@RequestMapping(value = "/paciente/excluir/cobranca_avulsa/" + "/{pk}", method = RequestMethod.POST)
	public String excluirCobrancaAvulsa(@PathVariable Long pk, Model model, final RedirectAttributes redirectAttributes)
			throws NegocioException {

		try {
			cobrancaAvulsaService.excluir(pk);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}

		return "redirect:/cobranca_avulsa_visualizar";
	}
*/
	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Paciente paciente, Model model, final RedirectAttributes redirectAttributes) {

		try {
			service.excluir(paciente);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (NegocioException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, e.getMessage());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem("paciente.dependencia.com.consulta"));
		}

		return "redirect:/pacientes";
	}

	@RequestMapping(value = "/paciente/gerar-receita/{pk}", method = RequestMethod.GET)
	public String gerarCentroCusto(@PathVariable Long pk, Locale locale, Model model, HttpServletRequest request)
			throws Exception {
		Paciente paciente = service.buscar(pk);
		Receita receita = new Receita();
		receita.setPaciente(paciente);

		model.addAttribute("receita", receita);
		model.addAttribute("medicamentos", service.listarMedicamentos());

		return "receita";
	}

	@ResponseBody
	@RequestMapping(value = "/paciente/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("pacientes", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Paciente());
		return new ModelAndView("paciente.lista");
	}

	@RequestMapping(value = "/relatorio-pacientes", method = RequestMethod.GET)
	public String gerarRelatorioPacientes(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("paciente", new Paciente());

		return "relatorio-pacientes";
	}

	@RequestMapping(value = "/relatorio-pacientes/gerar", method = RequestMethod.POST)
	public String gerarRelatorioPacientesImpressao(@ModelAttribute Paciente paciente, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		if ((paciente.getDataInclusaoFormatada() != null && paciente.getDataInclusaoFinalFormatada() != null)
				&& (StringUtils.isNotBlank(paciente.getDataInclusaoFormatada())
						&& StringUtils.isNotBlank(paciente.getDataInclusaoFinalFormatada()))) {

			String dia = paciente.getDataInclusaoFormatada().substring(0, 2);
			String mes = paciente.getDataInclusaoFormatada().substring(3, 5);
			String ano = paciente.getDataInclusaoFormatada().substring(6, 10);

			GregorianCalendar dataInclusaoPesquisa = new GregorianCalendar();
			dataInclusaoPesquisa.setTime(new Date());
			dataInclusaoPesquisa.set(Calendar.YEAR, Integer.parseInt(ano));
			dataInclusaoPesquisa.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
			dataInclusaoPesquisa.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
			dataInclusaoPesquisa.set(Calendar.HOUR_OF_DAY, 0);
			dataInclusaoPesquisa.set(Calendar.MINUTE, 0);
			dataInclusaoPesquisa.set(Calendar.SECOND, 0);

			paciente.setDataInclusao(dataInclusaoPesquisa.getTime());

			List<PacienteDTO> listaPacientes = service.consultarDTO(paciente, usuarioLogado.getCliente());

			model.addAttribute("pacientes", listaPacientes);
			model.addAttribute("dataInicial", paciente.getDataInclusaoFormatada());
			model.addAttribute("dataFinal", paciente.getDataInclusaoFinalFormatada());

			return "relatorio-pacientes-gerar";

		} else {
			model.addAttribute(MESSAGE_ERROR,
					"Preencha as datas de início e fim, para consultar apenas um dia informe a mesma data para data início e data fim");
			model.addAttribute("paciente", new Paciente());
			return "relatorio-pacientes";
		}

	}

	// Rota para salvar o plano vindo do modal
	// Colocar para retornar um json
	@ResponseBody
	@RequestMapping(value = URL_REQUEST_SALVAR_PLANO, method = RequestMethod.POST)
	public Object salvarPlano(@ModelAttribute Plano plano, BindingResult result) throws Exception {

		PlanoValidator validator = new PlanoValidator();
		validator.validate(plano, result);

		if (result.hasErrors()) {
			return result.getFieldErrors();
		}

		plano.setTipoPlanoSaudeEnum(
				TipoPlanoSaudeEnum.getEnumPorCodigo(Integer.parseInt(plano.getCodigoTipoPlanoTransiente())));

		planoService.salvar(plano);
		List<Plano> planoSaudeList = planoService.listar();
		return planoSaudeList;
	}

}
