package br.com.eclinic.controler.usuario;

import java.util.ArrayList;
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
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.usuario.Perfil;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.entity.usuario.UsuarioDTO;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.cliente.ClienteService;
import br.com.eclinic.service.usuario.PerfilService;
import br.com.eclinic.service.usuario.UsuarioService;
import br.com.eclinic.validator.UsuarioValidator;

@Controller
public class UsuarioController extends EclinicController {

	private static final String VAZIO = "";
	private static final String MODEL_ATTR_ENTIDADE = "usuario";
	private static final String URL_REQUEST_LISTA = "/usuarios";
	private static final String URL_REQUEST_SALVAR = "/usuario/salvar";
	private static final String URL_REQUEST_ALTERAR = "/usuario/alterar";
	private static final String URL_REQUEST_GERENCIAR_CLIENTE = "/usuario/gerenciar_cliente";
	private static final String URL_REQUEST_VISUALIZAR = "/usuario/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/usuario/excluir";
	private static final String URL_REQUEST_RESETAR_SENHA = "/usuario/resetar_senha";
	private static final String URL_REQUEST_SALVAR_PERFIL = "/usuario/salvar_perfil";
	
	@Autowired
	private UsuarioService service;
	@Autowired
	private PerfilService perfilService;
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String usuarioPesquisar(@ModelAttribute Usuario usuario,
			Locale locale, Model model, HttpServletRequest request)
			throws Exception {
		
		return listar(usuario.getLogin(), locale, model, request);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String usuarios(Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		model.addAttribute("perfil", new Perfil());

		return listar(null, locale, model, request);
	}

	private String listar(String login, Locale locale, Model model,
			HttpServletRequest request) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		Cliente cliente = usuarioLogado.getCliente();

		if (login != null && !VAZIO.equals(login)) {
			usuarios = service.consultarPorLogin(login, cliente);
		} else {
			usuarios = service.listarPorCliente(cliente);
		}

		configurarPaginacao(usuarios, model);
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		model.addAttribute(MODEL_ATTR_ENTIDADE, usuario);
		model.addAttribute("usuarios", getPagedListHolder().getPageList());

		return "usuarios";
	}
	
	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute Usuario usuario,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws Exception {

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, result);

		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("usuario_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, usuario);
			mav.addObject("perfis",
					perfilService.listarPorCliente(usuarioLogado.getCliente()));
			mav.addObject("usuario", usuario);
			mav.addObject("perfil", new Perfil());
			mav.addObject("funcionalidades", perfilService.listarFuncionalidades());
			return mav;
		} else {

			List<Usuario> usuariosBanco = service.consultarPorLogin(
					usuario.getLogin(), usuarioLogado.getCliente());
			if (usuariosBanco.size() != 0) {
				ModelAndView mav = new ModelAndView("usuario_incluir");
				mav.addObject(MODEL_ATTR_ENTIDADE, usuario);
				mav.addObject(MESSAGE_ERROR, getMensagem("usuario.existente"));
				mav.addObject("perfis", perfilService
						.listarPorCliente(usuarioLogado.getCliente()));
				mav.addObject("usuario", usuario);
				mav.addObject("perfil", new Perfil());
				mav.addObject("funcionalidades", perfilService.listarFuncionalidades());
				return mav;
			} else {
				usuario.setCliente(usuarioLogado.getCliente());
				ModelAndView retorno = new ModelAndView("usuario_incluir");
				try {
					service.salvar(usuario);
					retorno.addObject(MESSAGE,
							getMensagem("inclusao.sucesso"));
					retorno.addObject("usuario", new Usuario());
				} catch (NegocioException erro) {
					retorno = new ModelAndView("usuario_incluir");
					retorno.addObject(MODEL_ATTR_ENTIDADE, usuario);
					retorno.addObject(MESSAGE_ERROR, getMensagem("usuario.existente"));
					retorno.addObject("perfis", perfilService
							.listarPorCliente(usuarioLogado.getCliente()));
					retorno.addObject("usuario", usuario);
					retorno.addObject("perfil", new Perfil());
					retorno.addObject("funcionalidades", perfilService.listarFuncionalidades());
				}
				return retorno;
			}

		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model,
			HttpServletRequest request) throws Exception {
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		Usuario usuarioAlteracao = service.buscar(pk);
		if (usuarioAlteracao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, usuarioAlteracao);
			model.addAttribute("perfis",
					perfilService.listarPorCliente(usuarioLogado.getCliente()));
			model.addAttribute("alterarTela", "");
			return "usuario_alterar";
		} else {
			return "redirect:../usuarios";
		}
	}

	@RequestMapping(value = URL_REQUEST_GERENCIAR_CLIENTE + "/{pk}", method = RequestMethod.GET)
	public String exibirGerenciarCliente(@PathVariable Long pk, Model model,
			HttpServletRequest request) throws Exception {
		Usuario usuarioAlteracao = service.buscar(pk);
		if (usuarioAlteracao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, usuarioAlteracao);
			model.addAttribute("clientes", clienteService.listar());
			return "usuario_gerenciar_cliente";
		} else {
			return "redirect:../usuarios";
		}
	}

	@RequestMapping(value = URL_REQUEST_GERENCIAR_CLIENTE, method = RequestMethod.POST)
	public String gerenciarCliente(@ModelAttribute Usuario usuario, Model model)
			throws Exception {

		Usuario usuarioAlteracao = service.buscar(usuario.getId());

		if (usuario.getClienteAdicionar() == null
				|| usuario.getClienteAdicionar().getId() == null
				|| usuario.getClienteAdicionar().getId() == 0) {
			model.addAttribute("erroAdicionarClienteUsuario",
					"Erro: Selecione um cliente.");
			model.addAttribute(MODEL_ATTR_ENTIDADE, usuarioAlteracao);
			model.addAttribute("clientes", clienteService.listar());
		} else {

			Cliente clienteAdicionar = clienteService.buscar(usuario
					.getClienteAdicionar().getId());

			if (isClienteValido(usuarioAlteracao.getClientesPermissao(),
					clienteAdicionar)) {
				usuarioAlteracao.getClientesPermissao().add(clienteAdicionar);

				service.alterar(usuarioAlteracao);
				model.addAttribute(MODEL_ATTR_ENTIDADE, usuarioAlteracao);
				model.addAttribute("clientes", clienteService.listar());
			} else {
				model.addAttribute("erroAdicionarClienteUsuario",
						"Erro: Cliente já inserido.");
				model.addAttribute(MODEL_ATTR_ENTIDADE, usuarioAlteracao);
				model.addAttribute("clientes", clienteService.listar());
			}

		}

		return "usuario_gerenciar_cliente";
	}

	@RequestMapping(value = "usuario/excluir_cliente", method = RequestMethod.POST)
	public String excluirCliente(@ModelAttribute Usuario usuario, Model model)
			throws Exception {

		Usuario usuarioAlteracao = service.buscar(usuario.getId());

		for (int x = 0; x < usuarioAlteracao.getClientesPermissao().size(); x ++) {
			if (usuarioAlteracao.getClientesPermissao().get(x).getId() == usuario
					.getClienteAdicionar().getId()) {
				usuarioAlteracao.getClientesPermissao().remove(x);
			}
		}

		service.alterar(usuarioAlteracao);
		model.addAttribute(MODEL_ATTR_ENTIDADE, usuarioAlteracao);
		model.addAttribute("clientes", clienteService.listar());
		return "usuario_gerenciar_cliente";

	}

	private boolean isClienteValido(List<Cliente> clientesPermissao,
			Cliente clienteAdicionar) {
		for (Cliente cliente : clientesPermissao) {
			if (cliente.getId() == clienteAdicionar.getId()) {
				return false;
			}
		}
		return true;
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model,
			HttpServletRequest request) throws Exception {
		Usuario usuarioAlteracao = service.buscar(pk);
		if (usuarioAlteracao != null) {

			Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

			model.addAttribute("perfis",
					perfilService.listarPorCliente(usuarioLogado.getCliente()));

			model.addAttribute(MODEL_ATTR_ENTIDADE, usuarioAlteracao);
			return "usuario_visualizar";
		} else {
			return "redirect:../usuarios";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute Usuario usuario, Model model,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws NumberFormatException,
			NegocioException {

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, result);

		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("usuario_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, usuario);
			mav.addObject("perfis",
					perfilService.listarPorCliente(usuarioLogado.getCliente()));
			return mav;
		} else {
			Usuario usuarioBanco = service.consultarPorLogin(
					usuario.getLogin());
			if (usuarioBanco != null
					&& usuarioBanco.getId() != usuario.getId()) {
				ModelAndView mav = new ModelAndView("usuario_alterar");

				model.addAttribute("perfis", perfilService
						.listarPorCliente(usuarioLogado.getCliente()));

				mav.addObject(MODEL_ATTR_ENTIDADE, usuario);
				mav.addObject(MESSAGE_ERROR, getMensagem("usuario.existente"));
				mav.addObject("perfis", perfilService
						.listarPorCliente(usuarioLogado.getCliente()));
				return mav;
			} else {
				usuario.setCliente(usuarioLogado.getCliente());
				service.alterar(usuario);
				redirectAttributes.addFlashAttribute(MESSAGE,
						getMensagem("alterar.sucesso"));
				ModelAndView retorno = new ModelAndView("redirect:/usuarios");
				return retorno;
			}
		}
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Usuario usuario, Model model,
			final RedirectAttributes redirectAttributes) {

		try {
			usuario = service.buscar(usuario.getId());
			service.excluir(usuario);
			redirectAttributes.addFlashAttribute(MESSAGE,
					getMensagem("exclusao.sucesso"));
		} catch (NegocioException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, e.getMessage());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}

		return "redirect:/usuarios";
	}

	@RequestMapping(value = URL_REQUEST_RESETAR_SENHA, method = RequestMethod.POST)
	public String resetarSenha(@ModelAttribute Usuario usuario, Model model,
			final RedirectAttributes redirectAttributes) {

		try {
			service.resetarSenha(usuario.getId());
			redirectAttributes.addFlashAttribute(MESSAGE,
					getMensagem("resetar.senha.sucesso"));
		} catch (NegocioException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, e.getMessage());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}

		return "redirect:/usuarios";
	}

	@ResponseBody
	@RequestMapping(value = "/usuario/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model)
			throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("usuarios", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Usuario());
		return new ModelAndView("usuario.lista");
	}
	
	/*@RequestMapping(value = URL_REQUEST_SALVAR_PERFIL, method = RequestMethod.POST)
	public ModelAndView salvarPerfilUsuario(Model model, @ModelAttribute Perfil perfil,
			BindingResult result,  Usuario usuario, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws Exception {

		PerfilValidator validator = new PerfilValidator();
		validator.validate(perfil, result);
		
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("usuario_incluir");
			mav.addObject("perfil", perfil);
			mav.addObject("usuario", usuario);
			return mav;
		} else {
			List<Perfil> usuariosBanco = perfilService.consultarPorDescricao(perfil.getDescricao(), usuarioLogado.getCliente());
			if(usuariosBanco.size() != 0){
				ModelAndView mav = new ModelAndView("usuario_incluir");
				mav.addObject("perfil", perfil);
				mav.addObject(MESSAGE_ERROR,
						getMensagem("perfil.existente"));
				mav.addObject("usuario", usuario);
				return mav;
			}else{
				perfil.setCliente(usuarioLogado.getCliente());
				perfilService.salvar(perfil);
				redirectAttributes.addFlashAttribute(MESSAGE,
						getMensagem("inclusao.sucesso"));
				ModelAndView retorno = new ModelAndView("redirect:/usuario_incluir");
				retorno.addObject("usuario", usuario);
				retorno.addObject("perfil", perfil);
			return retorno;
			}
		}
		ModelAndView modelReturn = new ModelAndView("redirect:/usuario_incluir");
		modelReturn.addObject("usuario", usuario);
		modelReturn.addObject("perfil", perfil);
		return modelReturn;
	}*/
	
	
	@RequestMapping(value = URL_REQUEST_SALVAR_PERFIL, method = RequestMethod.POST)
	public ModelAndView salvarPerfilUsuario(Model model, @ModelAttribute UsuarioDTO usuario,
			BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws Exception {// @ModelAttribute Usuario usuario,

//		PerfilValidator validator = new PerfilValidator();
		//UsuarioValidator validator = new UsuarioValidator();
		//validator.validate(usuario, result);//o result é um UsuarioDTO	
		
		
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("usuario_incluir");
			mav.addObject("usuario", usuario);
			mav.addObject("perfis",
					perfilService.listarPorCliente(usuarioLogado.getCliente()));
			mav.addObject("funcionalidades", perfilService.listarFuncionalidades());
			return mav;
		} else {
			
			Perfil perfil = usuario.getPerfil();
			
			List<Perfil> usuariosBanco = perfilService.consultarPorDescricao(perfil.getDescricao(), usuarioLogado.getCliente());
			if(usuariosBanco.size() != 0){
				ModelAndView mav = new ModelAndView("usuario_incluir");
				mav.addObject(MESSAGE_ERROR,
						getMensagem("perfil.existente"));
				mav.addObject("usuario", usuario);
				mav.addObject("perfis",
						perfilService.listarPorCliente(usuarioLogado.getCliente()));
				mav.addObject("funcionalidades", perfilService.listarFuncionalidades());
				return mav;
			}else{
				perfil.setCliente(usuarioLogado.getCliente());
				perfilService.salvar(perfil);
				/*redirectAttributes.addFlashAttribute(MESSAGE,
						getMensagem("inclusao.sucesso"));*/
				ModelAndView retorno = new ModelAndView("usuario_incluir");//"redirect:/usuario_incluir");
				retorno.addObject("usuario", usuario);
				retorno.addObject("perfis",
						perfilService.listarPorCliente(usuarioLogado.getCliente()));
				retorno.addObject("funcionalidades", perfilService.listarFuncionalidades());
				retorno.addObject(MESSAGE, getMensagem("inclusao.sucesso"));
				return retorno;
			}
		}
		/*ModelAndView modelReturn = new ModelAndView("redirect:/usuario_incluir");
		modelReturn.addObject("usuario", usuario);
		modelReturn.addObject("perfil", perfil);
		return modelReturn;*/
	}
	
	
	
	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model, HttpServletRequest request)
			throws Exception {

		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		model.addAttribute(MODEL_ATTR_ENTIDADE, new Usuario());
		model.addAttribute("usuario", new UsuarioDTO());//Modificado
		model.addAttribute("perfis",
				perfilService.listarPorCliente(usuarioLogado.getCliente()));
		model.addAttribute("funcionalidades", perfilService.listarFuncionalidades());
		return "usuario_incluir";
	}
	
}
