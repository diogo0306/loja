package br.com.eclinic.controler.plano;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.controler.tabela.TabelaFaixaDTO;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.contrato.DiaVencimentoEnum;
import br.com.eclinic.entity.contrato.TipoPessoaContratoEnum;
import br.com.eclinic.entity.enuns.SituacaoContratoEnum;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.plano.AcomodacaoPlanoSaudeEnum;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.plano.TipoPlanoSaudeEnum;
import br.com.eclinic.entity.tabela.TabelaFaixa;
import br.com.eclinic.entity.tabela.TipoBeneficiarioEnum;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.service.contrato.ContratoService;
import br.com.eclinic.service.plano.PlanoService;
import br.com.eclinic.service.tabela.TabelaFaixaService;
import br.com.eclinic.validator.ContratoValidator;
import br.com.eclinic.validator.PlanoValidator;

@SuppressWarnings({ "unused" })
@Controller
public class PlanoController extends EclinicController {
	private static final String MODEL_ATTR_ENTIDADE = "plano";
	private static final String URL_REQUEST_LISTA = "/planos";
	private static final String URL_REQUEST_SALVAR = "/plano/salvar";
	private static final String URL_REQUEST_ALTERAR = "/plano/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/plano/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/plano/excluir";
	private static final String VAZIO = "";

	@Autowired
	private PlanoService service;
	@Autowired
	private TabelaFaixaService tabelaFaixaService;
	@Autowired
	private ContratoService contratoService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String planosPesquisar(@ModelAttribute Plano plano, Locale locale, Model model, HttpServletRequest request)
			throws Exception {
		return listar(plano.getNome(), locale, model, request);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String planos(Locale locale, Model model, HttpServletRequest request) throws Exception {
		return listar(null, locale, model, request);
	}

	private String listar(String nome, Locale locale, Model model, HttpServletRequest request) {
		String vazio = "";
		List<Plano> planos = new ArrayList<Plano>();
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		if (nome != null && nome != vazio) {
			Plano planoConsulta = new Plano();
			planoConsulta.setNome(nome);
			planos = service.consultar(planoConsulta, usuarioLogado.getCliente());
		} else {
			planos = service.listar();
		}

		configurarPaginacao(planos, model);
		Plano plano = new Plano();
		plano.setNome(nome);
		model.addAttribute(MODEL_ATTR_ENTIDADE, plano);
		model.addAttribute("planos", getPagedListHolder().getPageList());

		return "planos";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String ixibirSalvar(Model model, @ModelAttribute Plano plano, HttpServletRequest request) {

		model.addAttribute("plano", new Plano());
		model.addAttribute("tiposPlano", TipoPlanoSaudeEnum.values());

		return "plano_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView adicionar(Model model, @ModelAttribute Plano plano, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		String vazio = "";

		if (plano.getValorPlanoTransiente().equalsIgnoreCase("0,00")) {
			plano.setValorPlanoTransiente(null);
		}

		if (plano.getValorPlanoTransiente() != null && !plano.getValorPlanoTransiente().isEmpty()) {
			plano.setValorPlano(new BigDecimal(plano.getValorPlanoTransiente().replace(".", "").replace(",", ".")));
		}
		
		if (plano.getCodigoTipoPlanoTransiente() != null
				&& plano.getCodigoTipoPlanoTransiente() != vazio) {

			plano.setTipoPlanoSaudeEnum(TipoPlanoSaudeEnum
					.getEnumPorCodigo(Integer.parseInt(plano.getCodigoTipoPlanoTransiente())));
		}

		PlanoValidator validator = new PlanoValidator();
		validator.validate(plano, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("plano_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, plano);
			mav.addObject("tiposPlano", TipoPlanoSaudeEnum.values());
			return mav;
		} else {

			plano.setAcomodacaoPlanoSaudeEnum(AcomodacaoPlanoSaudeEnum.APARTAMENTO);
			service.salvar(plano);
			plano.setValorPlanoTransiente(null);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/planos");
			return retorno;
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {

		Plano planoAlteracao = service.buscar(pk);

		if (planoAlteracao != null) {

			if (planoAlteracao.getValorPlanoTransiente() != null
					&& !planoAlteracao.getValorPlanoTransiente().isEmpty()) {
				planoAlteracao.setValorPlano(
						new BigDecimal(planoAlteracao.getValorPlanoTransiente().replace(".", "").replace(",", ".")));
			}

			planoAlteracao.setCodigoAcomodacaoPlanoTransiente(
					String.valueOf(planoAlteracao.getAcomodacaoPlanoSaudeEnum().getCodigo()));
			planoAlteracao.setCodigoTipoPlanoTransiente(
					String.valueOf(planoAlteracao.getTipoPlanoSaudeEnum().getCodigo()));
			
			planoAlteracao.setValorPlanoTransiente(planoAlteracao.getValorPlano().toString());

			model.addAttribute(MODEL_ATTR_ENTIDADE, planoAlteracao);
			model.addAttribute("tiposPlano", TipoPlanoSaudeEnum.values());

			return "plano_alterar";
		} else {
			return "redirect:../planos";
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute Plano plano, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		String vazio = "";

		ModelAndView modelAndView = new ModelAndView("plano_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, plano);
		modelAndView.addObject("tiposPlano", TipoPlanoSaudeEnum.values());

		if (plano.getValorPlanoTransiente() != null && !plano.getValorPlanoTransiente().isEmpty()) {
			plano.setValorPlano(new BigDecimal(plano.getValorPlanoTransiente().replace(".", "").replace(",", ".")));
		}
		
		if (plano.getCodigoTipoPlanoTransiente() != null
				&& plano.getCodigoTipoPlanoTransiente()!= vazio) {

			plano.setTipoPlanoSaudeEnum(TipoPlanoSaudeEnum
					.getEnumPorCodigo(Integer.parseInt(plano.getCodigoTipoPlanoTransiente())));
		}

		PlanoValidator validator = new PlanoValidator();
		validator.validate(plano, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("plano_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, plano);
			mav.addObject("tiposPlano", TipoPlanoSaudeEnum.values());
			return mav;
		} else {
			plano.setAcomodacaoPlanoSaudeEnum(AcomodacaoPlanoSaudeEnum.APARTAMENTO);
			service.alterar(plano);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/planos");
			return retorno;

		}
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {

		Plano planoVisualizar = service.buscar(pk);

		if (planoVisualizar != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, planoVisualizar);
			model.addAttribute("tiposPlano", TipoPlanoSaudeEnum.values());
			model.addAttribute("acomodacaoPlano", AcomodacaoPlanoSaudeEnum.values());

			return "plano_visualizar";
		} else {
			return "redirect:../planos";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/plano/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("planos", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Plano());
		return new ModelAndView("plano.lista");
	}

}
