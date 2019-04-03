package br.com.eclinic.controler.clienteLoja;

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
import br.com.eclinic.entity.clienteLoja.ClienteLoja;

import br.com.eclinic.service.clienteLoja.ClienteLojaService;
import br.com.eclinic.validator.ClienteLojaValidator;

@Controller
public class ClienteLojaController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "clienteLoja";
	private static final String URL_REQUEST_LISTA = "/clientesLoja";
	private static final String URL_REQUEST_SALVAR = "/clienteLoja/salvar";
	private static final String URL_REQUEST_ALTERAR = "/clienteLoja/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/clienteLoja/visualizar";
	private static final String URL_REQUEST_DASHBOARD = "/clienteLoja/dashboard";
	private static final String URL_REQUEST_EXCLUIR = "/clienteLoja/excluir";

	@Autowired
	private ClienteLojaService clienteLojaService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String clienteLojaPesquisar(@ModelAttribute ClienteLoja clienteLoja, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		List<ClienteLoja> clientesLoja = clienteLojaService.consultar(clienteLoja);
		model.addAttribute("clientesLoja", clientesLoja);
		model.addAttribute("clienteLoja", new ClienteLoja());

		return "clientesLoja";
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String clientesLoja(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("clienteLoja", new ClienteLoja());
		return "clientesLoja";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new ClienteLoja());

		return "clienteLoja_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute ClienteLoja clienteLoja, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		ModelAndView modelAndView = new ModelAndView("clienteLoja_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, clienteLoja);

		ClienteLojaValidator validator = new ClienteLojaValidator();
		validator.validate(clienteLoja, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("clienteLoja_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, clienteLoja);
			return mav;
		} else {

			clienteLojaService.salvar(clienteLoja);

			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/clientesLoja");
			return retorno;
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {

		ClienteLoja clienteLojaAlteracao = clienteLojaService.buscar(pk);

		if (clienteLojaAlteracao != null) {

			model.addAttribute(MODEL_ATTR_ENTIDADE, clienteLojaAlteracao);

			return "clienteLoja_alterar";
		} else {
			return "redirect:../clientesLoja";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute ClienteLoja clienteLoja, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		ModelAndView modelAndView = new ModelAndView("clienteLoja_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, clienteLoja);

		ClienteLojaValidator validator = new ClienteLojaValidator();
		validator.validate(clienteLoja, result);

		if (result.hasErrors()) {

			ModelAndView mav = new ModelAndView("clienteLoja_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, clienteLoja);
			return mav;

		} else {

			clienteLojaService.alterar(clienteLoja);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/clientesLoja");
			return retorno;
		}
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {

		ClienteLoja clienteLojaVisualizar = clienteLojaService.buscar(pk);

		if (clienteLojaVisualizar != null) {

			model.addAttribute(MODEL_ATTR_ENTIDADE, clienteLojaVisualizar);

			return "clienteLoja_visualizar";
		} else {
			return "redirect:../clientesLoja";
		}
	}
	
	@RequestMapping(value = URL_REQUEST_DASHBOARD + "/{pk}", method = RequestMethod.GET)
	public String exibirDashboard(@PathVariable Long pk, Model model) throws Exception {

		ClienteLoja clienteLojaDashboard = clienteLojaService.buscar(pk);

		if (clienteLojaDashboard != null) {

			model.addAttribute(MODEL_ATTR_ENTIDADE, clienteLojaDashboard);

			return "clienteLoja_dashboard";
		} else {
			return "redirect:../clientesLoja";
		}
	}

	

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute ClienteLoja clienteLoja, Model model,
			final RedirectAttributes redirectAttributes) {

		try {
			clienteLojaService.excluir(clienteLoja);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem(""));
		}

		return "redirect:/clientesLoja";
	}

	@ResponseBody
	@RequestMapping(value = "/clienteLoja/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("clientesLoja", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new ClienteLoja());
		return new ModelAndView("clienteLoja.lista");
	}

}
