package br.com.eclinic.controler.usuario;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.usuario.OTDAlterarSenha;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.usuario.UsuarioService;
import br.com.eclinic.validator.AlterarSenhaValidator;

@Controller
public class AlterarSenhaController extends EclinicController {

	@Autowired
	private UsuarioService service;

	@RequestMapping(value = "alterar_senha", method = RequestMethod.GET)
	public String alterarSenha(Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		OTDAlterarSenha otd = new OTDAlterarSenha();
		Usuario usuario = HomeController.getUsuarioLogado(request);
		otd.setIdUsuario(usuario.getId());
		
		model.addAttribute("otdAlterarSenha", otd);
		
		return "alterar.senha";
	}

	@RequestMapping(value = "alterar_senha/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(Locale locale, Model model,
			@ModelAttribute OTDAlterarSenha otdAlterarSenha, BindingResult result,
			final RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws Exception {

		AlterarSenhaValidator validator = new AlterarSenhaValidator();
		validator.validate(otdAlterarSenha, result);
		ModelAndView retorno = new ModelAndView("alterar.senha");

		if (result.hasErrors()) {
			
			String mensagemErro = "";
			for (ObjectError object : result.getAllErrors()) {
				mensagemErro += getMensagem(object.getCode());
			}
			retorno.addObject("otdAlterarSenha", otdAlterarSenha);
			model.addAttribute(MESSAGE_ERROR, mensagemErro);
			return retorno;
		} else {
			
			try {
				service.alterarSenha(otdAlterarSenha.getIdUsuario(), otdAlterarSenha.getSenhaAtual(), otdAlterarSenha.getSenhaNova());
				Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
				usuarioLogado.setAlterarSenha("N");
				return new ModelAndView("redirect:/");
			} catch (NegocioException erro) {
				redirectAttributes.addFlashAttribute(MESSAGE_ERROR,
						erro.getMessage());
				model.addAttribute(MESSAGE_ERROR, erro.getMessage());
			}
		}
		
		return retorno;
	}	
}
