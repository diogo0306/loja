package br.com.eclinic.controler.parametrizacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
import br.com.eclinic.entity.parametrizacao.ControleBiometricoEnum;
import br.com.eclinic.entity.parametrizacao.ControleContratualEnum;
import br.com.eclinic.entity.parametrizacao.EditorTextoEnum;
import br.com.eclinic.entity.parametrizacao.MesEnum;
import br.com.eclinic.entity.parametrizacao.Parametrizacao;
import br.com.eclinic.entity.parametrizacao.ParametrizacaoDTO;
import br.com.eclinic.entity.parametrizacao.SimNaoEnum;
import br.com.eclinic.entity.parametrizacao.TipoCobrancaEmpresaEnum;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.parametrizacao.ParametrizacaoService;
import br.com.eclinic.validator.ParametrizacaoValidator;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.empresa.Empresa;

@SuppressWarnings("unused")
@Controller
public class ParametrizacaoController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "parametrizacao";
	private static final String URL_REQUEST_LISTA = "/parametrizacoes";
	private static final String URL_REQUEST_SALVAR = "/parametrizacao/salvar";
	private static final String URL_REQUEST_ALTERAR = "/parametrizacao/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/parametrizacao/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/parametrizacao/excluir";

	@Autowired
	private ParametrizacaoService service;
	
/*	@Autowired
	private EmpresaService empresaService;
*/
	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String parametrizacoesPesquisar(@ModelAttribute Parametrizacao parametrizacao, Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		return listar(parametrizacao.getCodigo(), locale, model, request);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String parametrizacoes(Locale locale, Model model, HttpServletRequest request) throws Exception {
		return listar(null, locale, model, request);
	}

	private String listar(String codigo, Locale locale, Model model, HttpServletRequest request) {

		String vazio = "";
		List<Parametrizacao> parametrizacoes = new ArrayList<Parametrizacao>();

		if (codigo != null && codigo != vazio) {
			Parametrizacao parametrizacaoConsulta = new Parametrizacao();
			parametrizacaoConsulta.setCodigo(codigo);
			parametrizacoes = service.consultar(parametrizacaoConsulta);
		} else {
			parametrizacoes = service.listar();
		}

		configurarPaginacao(parametrizacoes, model);
		Parametrizacao parametrizacao = new Parametrizacao();
		parametrizacao.setCodigo(codigo);
		model.addAttribute(MODEL_ATTR_ENTIDADE, parametrizacao);
		model.addAttribute("parametrizacoes", getPagedListHolder().getPageList());

		return "parametrizacoes";

	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String parametrizacaoSalvar(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("parametrizacaoDTO", new ParametrizacaoDTO());
		model.addAttribute("mesProcessamento", MesEnum.values());
		model.addAttribute("editorArquivo", EditorTextoEnum.values());
		model.addAttribute("controleContratual", ControleContratualEnum.values());
		model.addAttribute("tipoCobrancaEmpresa", TipoCobrancaEmpresaEnum.values());
		model.addAttribute("consistirRetornoConsulta", SimNaoEnum.values());
		model.addAttribute("controleBiometrico", ControleBiometricoEnum.values());
		/*model.addAttribute("empresas", service.listar());*/

		return "parametrizacao_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView parametrizacaoSalvarPost(@ModelAttribute ParametrizacaoDTO parametrizacaoDTO,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws Exception {

		String vazio = "";
/*
		if (parametrizacaoDTO.getParametrizacao().getPeriodoInicialFormatada().equalsIgnoreCase("")) {
			parametrizacaoDTO.getParametrizacao().setPeriodoInicialFormatada(null);
		}
		if (parametrizacaoDTO.getParametrizacao().getPeriodoFinalFormatada().equalsIgnoreCase("")) {
			parametrizacaoDTO.getParametrizacao().setPeriodoFinalFormatada(null);
		}*/

		ModelAndView modelAndView = new ModelAndView("parametrizacao_incluir");
		modelAndView.addObject("parametrizacaoDTO", parametrizacaoDTO);
		modelAndView.addObject("mesProcessamento", MesEnum.values());
		modelAndView.addObject("editorArquivo", EditorTextoEnum.values());
		modelAndView.addObject("controleContratual", ControleContratualEnum.values());
		modelAndView.addObject("tipoCobrancaEmpresa", TipoCobrancaEmpresaEnum.values());
		modelAndView.addObject("consistirRetornoConsulta", SimNaoEnum.values());
		modelAndView.addObject("controleBiometrico", ControleBiometricoEnum.values());
		

		/*if (parametrizacaoDTO.getParametrizacao().getPeriodoInicialFormatada() != null) {
			configurarDataContratacaoParametrizacaoInicial(parametrizacaoDTO);
		}

		if (parametrizacaoDTO.getParametrizacao().getPeriodoFinalFormatada() != null) {
			configurarDataContratacaoParametrizacaoFinal(parametrizacaoDTO);
		}
*/
		Parametrizacao parametrizacao = new Parametrizacao();
		parametrizacao = parametrizacaoDTO.getParametrizacao();
	/*	parametrizacao.setPeriodoInicio(parametrizacaoDTO.getPeriodoInicio());
		parametrizacao.setPeriodoFim(parametrizacaoDTO.getPeriodoFim());*/
		gerarNumeroAleatorio(parametrizacao);

		if (parametrizacaoDTO.getParametrizacao().getCodigoMesProcessamentoTransiente() != null
				&& parametrizacaoDTO.getParametrizacao().getCodigoMesProcessamentoTransiente() != vazio) {

			parametrizacao.setMesProcessamento(MesEnum.getEnumPorCodigo(
					Integer.parseInt(parametrizacaoDTO.getParametrizacao().getCodigoMesProcessamentoTransiente())));
		}

		if (parametrizacaoDTO.getParametrizacao().getCodigoControleBiometricoTransiente() != null
				&& parametrizacaoDTO.getParametrizacao().getCodigoControleBiometricoTransiente() != vazio) {

			parametrizacao.setControleBiometrico(ControleBiometricoEnum.getEnumPorCodigo(
					Integer.parseInt(parametrizacaoDTO.getParametrizacao().getCodigoControleBiometricoTransiente())));
		}

		if (parametrizacaoDTO.getParametrizacao().getCodigoEditorTextoTransiente() != null
				&& parametrizacaoDTO.getParametrizacao().getCodigoEditorTextoTransiente() != vazio) {

			parametrizacao.setEditorArquivo(EditorTextoEnum.getEnumPorCodigo(
					Integer.parseInt(parametrizacaoDTO.getParametrizacao().getCodigoEditorTextoTransiente())));
		}

		if (parametrizacaoDTO.getParametrizacao().getCodigoSimNaoTransiente() != null
				&& parametrizacaoDTO.getParametrizacao().getCodigoSimNaoTransiente() != vazio) {

			parametrizacao.setConsistirRetornoConsulta(SimNaoEnum.getEnumPorCodigo(
					Integer.parseInt(parametrizacaoDTO.getParametrizacao().getCodigoSimNaoTransiente())));
		}

		if (parametrizacaoDTO.getParametrizacao().getCodigoTipoCobrançaEmpresaTransiente() != null
				&& parametrizacaoDTO.getParametrizacao().getCodigoTipoCobrançaEmpresaTransiente() != vazio) {

			parametrizacao.setTipoCobrancaEmpresa(TipoCobrancaEmpresaEnum.getEnumPorCodigo(
					Integer.parseInt(parametrizacaoDTO.getParametrizacao().getCodigoTipoCobrançaEmpresaTransiente())));
		}

		if (parametrizacaoDTO.getParametrizacao().getCodigoControleContratualTransiente() != null
				&& parametrizacaoDTO.getParametrizacao().getCodigoControleContratualTransiente() != vazio) {

			parametrizacao.setControleContratual(ControleContratualEnum.getEnumPorCodigo(
					Integer.parseInt(parametrizacaoDTO.getParametrizacao().getCodigoControleContratualTransiente())));
		}

		ParametrizacaoValidator validator = new ParametrizacaoValidator();
		validator.validate(parametrizacao, result);

		if (result.hasErrors()) {
			modelAndView.addObject(MESSAGE_ERROR, getMensagem("informar.campo"));
			return modelAndView;
		} else {
			service.salvar(parametrizacao);
			ModelAndView retorno = new ModelAndView("redirect:/parametrizacoes");
			return retorno;
		}

	}

	/*private void configurarDataContratacaoParametrizacaoInicial(ParametrizacaoDTO parametrizacaoDTO)
			throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy")
				.parse(parametrizacaoDTO.getParametrizacao().getPeriodoInicialFormatada());
		parametrizacaoDTO.setPeriodoInicio(dataFormatada);
	}

	private void configurarDataContratacaoParametrizacaoFinal(ParametrizacaoDTO parametrizacaoDTO)
			throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy")
				.parse(parametrizacaoDTO.getParametrizacao().getPeriodoFinalFormatada());
		parametrizacaoDTO.setPeriodoFim(dataFormatada);
	}

	private void configurarDataContratacaoParametrizacaoInicial(Parametrizacao parametrizacao) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(parametrizacao.getPeriodoInicialFormatada());
		parametrizacao.setPeriodoInicio(dataFormatada);
	}

	private void configurarDataContratacaoParametrizacaoFinal(Parametrizacao parametrizacao) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(parametrizacao.getPeriodoFinalFormatada());
		parametrizacao.setPeriodoFim(dataFormatada);
	}*/

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model, HttpServletRequest request) throws Exception {

		Parametrizacao parametrizacaoAlteracao = service.buscar(pk);
		
		/*Empresa empresa = empresaService.buscar(parametrizacaoAlteracao.getEmpresa().getId());
		parametrizacaoAlteracao.setEmpresa(empresa);
*/
		if (parametrizacaoAlteracao != null) {

			parametrizacaoAlteracao.setCodigoMesProcessamentoTransiente(
					String.valueOf(parametrizacaoAlteracao.getMesProcessamento().getCodigo()));
			parametrizacaoAlteracao.setCodigoControleBiometricoTransiente(
					String.valueOf(parametrizacaoAlteracao.getControleBiometrico().getCodigo()));
			parametrizacaoAlteracao.setCodigoControleContratualTransiente(
					String.valueOf(parametrizacaoAlteracao.getControleContratual().getCodigo()));
			parametrizacaoAlteracao.setCodigoEditorTextoTransiente(
					String.valueOf(parametrizacaoAlteracao.getEditorArquivo().getCodigo()));
			parametrizacaoAlteracao.setCodigoSimNaoTransiente(
					String.valueOf(parametrizacaoAlteracao.getConsistirRetornoConsulta().getCodigo()));
			parametrizacaoAlteracao.setCodigoTipoCobrançaEmpresaTransiente(
					String.valueOf(parametrizacaoAlteracao.getTipoCobrancaEmpresa().getCodigo()));

			/*parametrizacaoAlteracao.setPeriodoInicialFormatada(parametrizacaoAlteracao.getPeriodoInicio().toString());
			parametrizacaoAlteracao.setPeriodoFinalFormatada(parametrizacaoAlteracao.getPeriodoFim().toString());*/
			model.addAttribute(MODEL_ATTR_ENTIDADE, parametrizacaoAlteracao);
			model.addAttribute("mesProcessamento", MesEnum.values());
			model.addAttribute("editorArquivo", EditorTextoEnum.values());
			model.addAttribute("controleContratual", ControleContratualEnum.values());
			model.addAttribute("tipoCobrancaEmpresa", TipoCobrancaEmpresaEnum.values());
			model.addAttribute("consistirRetornoConsulta", SimNaoEnum.values());
			model.addAttribute("controleBiometrico", ControleBiometricoEnum.values());
			/*model.addAttribute("empresas", empresaService.listar());*/
			return "parametrizacao_alterar";
		} else {
			return "redirect:../parametrizacoes";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute Parametrizacao parametrizacao, Model model, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		ParametrizacaoValidator validator = new ParametrizacaoValidator();
		validator.validateAlterar(parametrizacao, result);

		if (result.hasErrors()) {

			ModelAndView mav = new ModelAndView("parametrizacao_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, parametrizacao);
			mav.addObject("mesProcessamento", MesEnum.values());
			mav.addObject("editorArquivo", EditorTextoEnum.values());
			mav.addObject("controleContratual", ControleContratualEnum.values());
			mav.addObject("tipoCobrancaEmpresa", TipoCobrancaEmpresaEnum.values());
			mav.addObject("consistirRetornoConsulta", SimNaoEnum.values());
			mav.addObject("controleBiometrico", ControleBiometricoEnum.values());
			return mav;

		} else {

			parametrizacao.setMesProcessamento(
					MesEnum.getEnumPorCodigo(Integer.parseInt(parametrizacao.getCodigoMesProcessamentoTransiente())));
			parametrizacao.setEditorArquivo(EditorTextoEnum
					.getEnumPorCodigo(Integer.parseInt(parametrizacao.getCodigoEditorTextoTransiente())));
			parametrizacao.setControleContratual(ControleContratualEnum
					.getEnumPorCodigo(Integer.parseInt(parametrizacao.getCodigoControleContratualTransiente())));
			parametrizacao.setTipoCobrancaEmpresa(TipoCobrancaEmpresaEnum
					.getEnumPorCodigo(Integer.parseInt(parametrizacao.getCodigoTipoCobrançaEmpresaTransiente())));
			parametrizacao.setConsistirRetornoConsulta(
					SimNaoEnum.getEnumPorCodigo(Integer.parseInt(parametrizacao.getCodigoSimNaoTransiente())));
			parametrizacao.setControleBiometrico(ControleBiometricoEnum
					.getEnumPorCodigo(Integer.parseInt(parametrizacao.getCodigoControleBiometricoTransiente())));

			/*configurarDataContratacaoParametrizacaoInicial(parametrizacao);
			configurarDataContratacaoParametrizacaoFinal(parametrizacao);*/

			service.alterar(parametrizacao);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/parametrizacoes");
			return retorno;
		}

	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String parametrizacaoVisualizar(@PathVariable Long pk, Model model, HttpServletRequest request)
			throws Exception {

		String vazio = "";

		Parametrizacao parametrizacaoVisualizacao = service.buscar(pk);
		/*Empresa empresa = empresaService.buscar(parametrizacaoVisualizacao.getEmpresa().getId());
		parametrizacaoVisualizacao.setEmpresa(empresa);
*/
		if (parametrizacaoVisualizacao != null) {
			parametrizacaoVisualizacao.setCodigoControleBiometricoTransiente(
					String.valueOf(parametrizacaoVisualizacao.getControleBiometrico().getCodigo()));
			parametrizacaoVisualizacao.setCodigoControleContratualTransiente(
					String.valueOf(parametrizacaoVisualizacao.getControleContratual().getCodigo()));
			parametrizacaoVisualizacao.setCodigoEditorTextoTransiente(
					String.valueOf(parametrizacaoVisualizacao.getEditorArquivo().getCodigo()));
			parametrizacaoVisualizacao.setCodigoMesProcessamentoTransiente(
					String.valueOf(parametrizacaoVisualizacao.getMesProcessamento().getCodigo()));
			parametrizacaoVisualizacao.setCodigoSimNaoTransiente(
					String.valueOf(parametrizacaoVisualizacao.getConsistirRetornoConsulta().getCodigo()));
			parametrizacaoVisualizacao.setCodigoTipoCobrançaEmpresaTransiente(
					String.valueOf(parametrizacaoVisualizacao.getTipoCobrancaEmpresa().getCodigo()));

			/*if (parametrizacaoVisualizacao.getPeriodoInicio() != null) {
				parametrizacaoVisualizacao
						.setPeriodoInicialFormatada(parametrizacaoVisualizacao.getPeriodoInicio().toString());
			} else {
				parametrizacaoVisualizacao.setPeriodoInicialFormatada(vazio);
			}

			if (parametrizacaoVisualizacao.getPeriodoFim() != null) {
				parametrizacaoVisualizacao
						.setPeriodoFinalFormatada(parametrizacaoVisualizacao.getPeriodoFim().toString());
			} else {
				parametrizacaoVisualizacao.setPeriodoFinalFormatada(vazio);
			}*/
			model.addAttribute(MODEL_ATTR_ENTIDADE, parametrizacaoVisualizacao);
			return "parametrizacao_visualizar";
		} else {
			return "redirect:../parametrizacoes";
		}
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String parametrizacaoExcluir(@ModelAttribute Parametrizacao parametrizacao, Model model,
			final RedirectAttributes redirectAttributes) {

		try {
			service.excluir(parametrizacao);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (NegocioException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, e.getMessage());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem("parametrizacao.dependencia.consulta"));
		}

		return "redirect:/parametrizacoes";
	}

	@ResponseBody
	@RequestMapping(value = "/parametrizacao/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView parametrizacaoPaginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("parametrizacoes", getPagedListHolder().getPageList());
		model.addAttribute("parametrizacao", new Parametrizacao());
		return new ModelAndView("parametrizacao.lista");
	}
	
	public void gerarNumeroAleatorio(Parametrizacao parametrizacao) {

		List<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1; i < 100001; i++) {
			numeros.add(i);
		}

		Collections.shuffle(numeros);

		Random random = new Random();
		int x = random.nextInt(10001);

		parametrizacao.setCodigo(numeros.get(x).toString());
	}


}
