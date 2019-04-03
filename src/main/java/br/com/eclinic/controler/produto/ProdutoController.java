package br.com.eclinic.controler.produto;

import java.math.BigDecimal;

import java.text.ParseException;

import java.util.List;
import java.util.Locale;

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



import br.com.eclinic.entity.produto.Produto;
import br.com.eclinic.entity.produto.TipoProdutoEnum;

import br.com.eclinic.service.produto.ProdutoService;

import br.com.eclinic.validator.ProdutoValidator;

@Controller
public class ProdutoController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "produto";
	private static final String URL_REQUEST_LISTA = "/produtos";
	private static final String URL_REQUEST_SALVAR = "/produto/salvar";
	private static final String URL_REQUEST_ALTERAR = "/produto/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/produto/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/produto/excluir";

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String produtoPesquisar(@ModelAttribute Produto produto, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		List<Produto> produtos = produtoService.consultar(produto);
		model.addAttribute("produtos", produtos);
		model.addAttribute("produto", new Produto());

		return "produtos";
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String tecnicos(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("produto", new Produto());
		return "produtos";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new Produto());
		model.addAttribute("tipos", TipoProdutoEnum.values());

		return "produto_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute Produto produto, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		String vazio = "";

		if (produto.getPrecoCustoTransiente().equalsIgnoreCase("0,00")) {
			produto.setPrecoCustoTransiente(null);
		}

		if (produto.getPrecoVendaTransiente().equalsIgnoreCase("0,00")) {
			produto.setPrecoVendaTransiente(null);
		}

		ModelAndView modelAndView = new ModelAndView("produto_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, produto);
		modelAndView.addObject("tipos", TipoProdutoEnum.values());

		if (produto.getCodigoTipoProdutoTransiente() != null && produto.getCodigoTipoProdutoTransiente() != vazio) {

			produto.setTipoProdutoEnum(
					TipoProdutoEnum.getEnumPorCodigo(Integer.parseInt(produto.getCodigoTipoProdutoTransiente())));
		}

		if (produto.getPrecoCustoTransiente() != null && !produto.getPrecoCustoTransiente().isEmpty()) {
			produto.setPrecoCusto(new BigDecimal(produto.getPrecoCustoTransiente().replace(".", "").replace(",", ".")));
		}

		if (produto.getPrecoVendaTransiente() != null && !produto.getPrecoVendaTransiente().isEmpty()) {
			produto.setPrecoVenda(new BigDecimal(produto.getPrecoVendaTransiente().replace(".", "").replace(",", ".")));
		}

		ProdutoValidator validator = new ProdutoValidator();
		validator.validate(produto, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("produto_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, produto);
			return mav;
		} else {

			produtoService.salvar(produto);

			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/produtos");
			return retorno;
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {

		Produto produtoAlteracao = produtoService.buscar(pk);

		if (produtoAlteracao != null) {

			if (produtoAlteracao.getPrecoCustoTransiente() != null
					&& !produtoAlteracao.getPrecoCustoTransiente().isEmpty()) {
				produtoAlteracao.setPrecoCusto(
						new BigDecimal(produtoAlteracao.getPrecoCustoTransiente().replace(".", "").replace(",", ".")));
			}

			if (produtoAlteracao.getPrecoVendaTransiente() != null
					&& !produtoAlteracao.getPrecoVendaTransiente().isEmpty()) {
				produtoAlteracao.setPrecoVenda(
						new BigDecimal(produtoAlteracao.getPrecoVendaTransiente().replace(".", "").replace(",", ".")));
			}

			produtoAlteracao
					.setCodigoTipoProdutoTransiente(String.valueOf(produtoAlteracao.getTipoProdutoEnum().getCodigo()));

			produtoAlteracao.setPrecoCustoTransiente(produtoAlteracao.getPrecoCusto().toString());
			produtoAlteracao.setPrecoVendaTransiente(produtoAlteracao.getPrecoVenda().toString());

			model.addAttribute(MODEL_ATTR_ENTIDADE, produtoAlteracao);
			model.addAttribute("tipos", TipoProdutoEnum.values());

			return "produto_alterar";
		} else {
			return "redirect:../produtos";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute Produto produto, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		String vazio = "";

		ModelAndView modelAndView = new ModelAndView("produto_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, produto);
		modelAndView.addObject("tipos", TipoProdutoEnum.values());

		if (produto.getCodigoTipoProdutoTransiente() != null && produto.getCodigoTipoProdutoTransiente() != vazio) {

			produto.setTipoProdutoEnum(
					TipoProdutoEnum.getEnumPorCodigo(Integer.parseInt(produto.getCodigoTipoProdutoTransiente())));
		}

		if (produto.getPrecoCustoTransiente() != null && !produto.getPrecoCustoTransiente().isEmpty()) {
			produto.setPrecoCusto(new BigDecimal(produto.getPrecoCustoTransiente().replace(".", "").replace(",", ".")));
		}

		if (produto.getPrecoVendaTransiente() != null && !produto.getPrecoVendaTransiente().isEmpty()) {
			produto.setPrecoVenda(new BigDecimal(produto.getPrecoVendaTransiente().replace(".", "").replace(",", ".")));
		}

		ProdutoValidator validator = new ProdutoValidator();
		validator.validate(produto, result);

		if (result.hasErrors()) {

			ModelAndView mav = new ModelAndView("produto_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, produto);
			return mav;

		} else {

			produtoService.alterar(produto);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/produtos");
			return retorno;
		}
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {

		Produto produtoVisualizar = produtoService.buscar(pk);

		if (produtoVisualizar != null) {

			model.addAttribute(MODEL_ATTR_ENTIDADE, produtoVisualizar);
			model.addAttribute("tipos", TipoProdutoEnum.values());

			return "produto_visualizar";
		} else {
			return "redirect:../produtos";
		}
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Produto produto, Model model, final RedirectAttributes redirectAttributes) {

		try {
			produtoService.excluir(produto);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem(""));
		}

		return "redirect:/produtos";
	}

	@ResponseBody
	@RequestMapping(value = "/produto/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("produtos", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Produto());
		return new ModelAndView("produto.lista");
	}

}
