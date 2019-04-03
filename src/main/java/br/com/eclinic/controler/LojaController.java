/*package br.com.eclinic.controler;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.entity.usuarioLoja.UsuarioLoja;

@Controller
public class LojaController extends EclinicController{

	@Autowired
	private UsuarioLoja usuarioLoja;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String paginaLogin(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("usuarioLoja", new UsuarioLoja());
		return "loginLoja";
	}
}
*/