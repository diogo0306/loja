package br.com.eclinic.controler.usuarioLoja;

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
import br.com.eclinic.entity.usuarioLoja.TipoUsuarioLojaEnum;
import br.com.eclinic.entity.usuarioLoja.UsuarioLoja;
import br.com.eclinic.service.usuarioLoja.UsuarioLojaService;
import br.com.eclinic.validator.UsuarioLojaValidator;

@Controller
public class UsuarioLojaController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "usuarioLoja";
	private static final String URL_REQUEST_LISTA = "/usuariosLoja";
	private static final String URL_REQUEST_SALVAR = "/usuarioLoja/salvar";
	private static final String URL_REQUEST_ALTERAR = "/usuarioLoja/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/usuarioLoja/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/usuarioLoja/excluir";

	@Autowired
	private UsuarioLojaService usuarioLojaService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String usuarioLojaPesquisar(@ModelAttribute UsuarioLoja usuarioLoja, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		List<UsuarioLoja> usuariosLoja = usuarioLojaService.consultar(usuarioLoja);
		model.addAttribute("usuariosLoja", usuariosLoja);
		model.addAttribute("usuarioLoja", new UsuarioLoja());

		return "usuariosLoja";
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String usuariosLoja(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("usuarioLoja", new UsuarioLoja());
		return "usuariosLoja";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new UsuarioLoja());
		model.addAttribute("tipos", TipoUsuarioLojaEnum.values());

		return "usuarioLoja_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute UsuarioLoja usuarioLoja, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		String vazio = "";

		ModelAndView modelAndView = new ModelAndView("usuarioLoja_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, usuarioLoja);
		modelAndView.addObject("tipos", TipoUsuarioLojaEnum.values());

		if (usuarioLoja.getCodigoTipoUsuarioLojaTransiente() != null
				&& usuarioLoja.getCodigoTipoUsuarioLojaTransiente() != vazio) {

			usuarioLoja.setTipoUsuarioLojaEnum(TipoUsuarioLojaEnum
					.getEnumPorCodigo(Integer.parseInt(usuarioLoja.getCodigoTipoUsuarioLojaTransiente())));
		}

		UsuarioLojaValidator validator = new UsuarioLojaValidator();
		validator.validate(usuarioLoja, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("usuarioLoja_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, usuarioLoja);
			return mav;
		} else {

			usuarioLojaService.salvar(usuarioLoja);

			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/usuariosLoja");
			return retorno;
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {

		UsuarioLoja usuarioLojaAlteracao = usuarioLojaService.buscar(pk);

		if (usuarioLojaAlteracao != null) {

			usuarioLojaAlteracao.setCodigoTipoUsuarioLojaTransiente(
					String.valueOf(usuarioLojaAlteracao.getTipoUsuarioLojaEnum().getCodigo()));

			model.addAttribute(MODEL_ATTR_ENTIDADE, usuarioLojaAlteracao);
			model.addAttribute("tipos", TipoUsuarioLojaEnum.values());

			return "usuarioLoja_alterar";
		} else {
			return "redirect:../usuariosLoja";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute UsuarioLoja usuarioLoja, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		String vazio = "";

		ModelAndView modelAndView = new ModelAndView("usuarioLoja_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, usuarioLoja);
		modelAndView.addObject("tipos", TipoUsuarioLojaEnum.values());

		if (usuarioLoja.getCodigoTipoUsuarioLojaTransiente() != null
				&& usuarioLoja.getCodigoTipoUsuarioLojaTransiente() != vazio) {

			usuarioLoja.setTipoUsuarioLojaEnum(TipoUsuarioLojaEnum
					.getEnumPorCodigo(Integer.parseInt(usuarioLoja.getCodigoTipoUsuarioLojaTransiente())));
		}

		UsuarioLojaValidator validator = new UsuarioLojaValidator();
		validator.validate(usuarioLoja, result);

		if (result.hasErrors()) {

			ModelAndView mav = new ModelAndView("usuarioLoja_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, usuarioLoja);
			return mav;

		} else {

			usuarioLojaService.alterar(usuarioLoja);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/usuariosLoja");
			return retorno;
		}
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {

		UsuarioLoja usuarioLojaVisualizar = usuarioLojaService.buscar(pk);

		if (usuarioLojaVisualizar != null) {

			model.addAttribute(MODEL_ATTR_ENTIDADE, usuarioLojaVisualizar);
			model.addAttribute("tipos", TipoUsuarioLojaEnum.values());

			return "usuarioLoja_visualizar";
		} else {
			return "redirect:../usuariosLoja";
		}
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute UsuarioLoja usuarioLoja, Model model,
			final RedirectAttributes redirectAttributes) {

		try {
			usuarioLojaService.excluir(usuarioLoja);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem(""));
		}

		return "redirect:/usuariosLoja";
	}

	@ResponseBody
	@RequestMapping(value = "/usuarioLoja/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("usuariosLoja", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new UsuarioLoja());
		return new ModelAndView("usuarioLoja.lista");
	}

}
