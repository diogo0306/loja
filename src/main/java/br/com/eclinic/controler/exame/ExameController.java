package br.com.eclinic.controler.exame;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.exame.ExameService;
import br.com.eclinic.validator.ExameValidator;

@Controller
public class ExameController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "exame";
	private static final String URL_REQUEST_LISTA = "/exames";
	private static final String URL_REQUEST_SALVAR = "/exame/salvar";
	private static final String URL_REQUEST_ALTERAR = "/exame/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/exame/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/exame/excluir";

	@Autowired
	private ExameService service;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String tecnicoPesquisar(@ModelAttribute Exame exame, Locale locale, Model model, HttpServletRequest request)
			throws Exception {

		List<Exame> exames = service.consultar(exame);

		if (!exames.isEmpty()) {
			for (Exame exa : exames) {
				String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(exa.getValor().doubleValue());
				exa.setValorTransiente(valor);
			}
		}

		model.addAttribute("exames", exames);
		model.addAttribute("exame", new Exame());

		return "exames";
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String tecnicos(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("exame", new Exame());
		return "exames";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("exame", new Exame());
		return "exame_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public String salvar(Model model, @ModelAttribute Exame exame, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		if (exame.getValorTransiente().equals("0,00")) {
			exame.setValorTransiente(null);
		}

		ExameValidator validator = new ExameValidator();
		validator.validate(exame, result);

		if (result.hasErrors()) {
			model.addAttribute("exame", exame);
			return "exame_incluir";
		}

		if (exame.getValorTransiente() != null && StringUtils.isNotEmpty(exame.getValorTransiente())) {
			exame.setValor(new BigDecimal(exame.getValorTransiente().replace(".", "").replace(",", ".")));
		}

		if (exame.getDescricao().equalsIgnoreCase("")) {
			exame.setDescricao("NÃO INFORMADO");
		} else {
			exame.setDescricao(exame.getDescricao().toUpperCase());
		}

		exame.setNome(exame.getNome().toUpperCase());

		service.salvar(exame);

		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		return "redirect:/exames";
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model, HttpServletRequest request) throws Exception {
		Exame exameAlteracao = service.buscar(pk);

		if (exameAlteracao != null) {
			String valor = String.valueOf(exameAlteracao.getValor()).replace(".", ",");
			exameAlteracao.setValorTransiente(valor);
			model.addAttribute("exame", exameAlteracao);
			return "exame_alterar";
		} else {
			return "redirect:../exames";
		}
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model, HttpServletRequest request) throws Exception {
		Exame exameAlteracao = service.buscar(pk);
		if (exameAlteracao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, exameAlteracao);
			return "exame_visualizar";
		} else {
			return "redirect:../exames";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public String alterar(@ModelAttribute Exame exame, Model model, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws NumberFormatException, NegocioException {

		if (exame.getValorTransiente().equals("0,00")) {
			exame.setValorTransiente(null);
		}

		ExameValidator validator = new ExameValidator();
		validator.validate(exame, result);

		if (result.hasErrors()) {
			model.addAttribute("exame", exame);
			return "exame_alterar";
		} else {

			if (exame.getValorTransiente() != null && StringUtils.isNotEmpty(exame.getValorTransiente())) {
				exame.setValor(new BigDecimal(exame.getValorTransiente().replace(".", "").replace(",", ".")));
			}

			if (exame.getDescricao().equalsIgnoreCase("")) {
				exame.setDescricao("NÃO INFORMADO");
			} else {
				exame.setDescricao(exame.getDescricao().toUpperCase());
			}

			exame.setNome(exame.getNome().toUpperCase());

			service.alterar(exame);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			return "redirect:/exames";
		}
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Exame exame, Model model, final RedirectAttributes redirectAttributes) {

		try {
			service.excluir(exame);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (NegocioException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, e.getMessage());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem("exame.dependencia.consulta"));
		}

		return "redirect:/exames";
	}

	@ResponseBody
	@RequestMapping(value = "/exame/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("exames", getPagedListHolder().getPageList());
		model.addAttribute("exame", new Exame());
		return new ModelAndView("exame.lista");
	}

}
