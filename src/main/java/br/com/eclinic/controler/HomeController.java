package br.com.eclinic.controler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.evento.Evento;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.OTDAlterarSenha;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.TipoUsuarioEnum;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.cliente.ClienteService;
import br.com.eclinic.service.evento.EventoService;
import br.com.eclinic.service.medico.MedicoService;
import br.com.eclinic.service.usuario.PerfilService;
import br.com.eclinic.service.usuario.UsuarioService;

/**
 * Handles requests for the application home page.
 */
@SuppressWarnings({"unused", "deprecation"})
@Controller
public class HomeController extends EclinicController {

	private static final String MOVIMENTACOES = "movimentacoes";
	private static final String MOVIMENTACAO = "MOVIMENTACAO";
	private static final String MENSAGEM_LOGIN_INVALIDO = "mensagemLoginInvalido";
	private static final String LOGIN_INVALIDO = "loginInvalido";
	private static final String AGENDAMENTOS = "agendamentosFunc";
	private static final String CADASTROS = "cadastros";
	private static final Long ADMIN = new Long(1);
	private static final String UNDER_LINE = "_";
	private static final String RELATORIO = "RELATORIO";
	private static final String AGENDAMENTO = "AGENDAMENTO";
	private static final String CADASTRO = "CADASTRO";
	private static final String EMPRESA = "EMPRESA";
	private static final String EXAME = "EXAME";
	private static final String USUARIO = "USUARIO";
	private static final String PPRA = "PPRA";
	private static final String PESQUISA = "PESQUISA";
	private static final String PESQUISA_EMPRESA = "PESQUISA_EMPRESA";
	private static final String PESQUISA_EXAME = "PESQUISA_EXAME";
	private static final String PESQUISA_USUARIO = "PESQUISA_USUARIO";
	private static final String PESQUISA_PPRA = "PESQUISA_PPRA";
	public static final String MENU_OFF = "MENU_OFF";
	private static final String USUARIO_LOGADO = "usuarioLogado";

	@Autowired
	private ClienteService clienteService;
	@Autowired
	private PerfilService perfilService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private MedicoService medicoService;
	@Autowired
	private EventoService eventoService;

	/**
	 * Home - controller da página inicial do sistema.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) throws Exception {
		String retorno = "home";
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);

		if (usuarioLogado != null) {
			if (usuarioLogado.getTipoUsuario().equals(usuarioLogado.getTipoUsuario().equals(TipoUsuarioEnum.ADMIN))) {
				request.getSession().setAttribute(USUARIO_LOGADO, usuarioLogado);
				request.getSession().setAttribute("clientes", clienteService.listar());
				model.addAttribute(USUARIO_LOGADO, usuarioLogado);
				
				request.getSession().setAttribute("usuario", usuarioLogado);
				model.addAttribute("usuario", usuarioLogado);
				
				retorno = "selecionar-cliente";
			} else {
				if (usuarioLogado.getClientesPermissao() != null && usuarioLogado.getClientesPermissao().size() > 1) {
					request.getSession().setAttribute(USUARIO_LOGADO, usuarioLogado);
					
					request.getSession().setAttribute("usuario", usuarioLogado);
					model.addAttribute("usuario", usuarioLogado);
					
					request.getSession().setAttribute("clientes", usuarioLogado.getClientesPermissao());
					model.addAttribute(USUARIO_LOGADO, usuarioLogado);
					retorno = "selecionar-cliente";
				} else {
					model.addAttribute(USUARIO_LOGADO, request.getSession().getAttribute(USUARIO_LOGADO));
					request.getSession().setAttribute(USUARIO_LOGADO, usuarioLogado);
					
					
					request.getSession().setAttribute("usuario", request.getSession().getAttribute(USUARIO_LOGADO));
					model.addAttribute("usuario", request.getSession().getAttribute(USUARIO_LOGADO));
					
					// usuarioLogado
					// .setPerfil(perfilService
					// .consultarFuncionalidadesOperacoesUsuario(usuarioLogado
					// .getPerfil()));
					// configurarFuncionalidadesUsuario(usuarioLogado,
					// request.getSession());
				}
				if (usuarioLogado.isExibirTelaAlterarSenha()) {
					OTDAlterarSenha otd = new OTDAlterarSenha();
					otd.setIdUsuario(usuarioLogado.getId());
					model.addAttribute("otdAlterarSenha", otd);
					retorno = "alterar.senha.sem.menu";
				}
			}

		} else {
			model.addAttribute(USUARIO_LOGADO, null);
			model.addAttribute("usuario", new Usuario());
			if (request.getSession().getAttribute(LOGIN_INVALIDO) != null) {
				model.addAttribute(MENSAGEM_LOGIN_INVALIDO, request.getSession().getAttribute(LOGIN_INVALIDO));
			} else {
				model.addAttribute(MENSAGEM_LOGIN_INVALIDO, null);
			}
			model.addAttribute("usuario", new Usuario());
			retorno = "login";
		}

		return retorno;
	}

	/**
	 * Página de erro do sistema.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String paginaLogin(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}

	/**
	 * Página de erro do sistema.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String realizarLogout(Model model, HttpServletRequest request) {
		request.getSession().setAttribute(USUARIO_LOGADO, null);
		model.addAttribute(USUARIO_LOGADO, null);
		model.addAttribute("usuario", new Usuario());

		limparPermissoes(request);

		return "login";
	}

	private void limparPermissoes(HttpServletRequest request) {
		String[] variaveis = request.getSession().getValueNames();
		for (String variavel : variaveis) {
			request.getSession().removeAttribute(variavel);
		}
	}

	/**
	 * Página de erro do sistema.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView realizarLogin(@ModelAttribute Usuario usuario, Model model, BindingResult result,
			HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		limparPermissoes(request);
		Usuario usuarioLogado;
		try {
			usuarioLogado = usuarioService.realizarLogin(usuario);

			if (usuarioLogado.getId().equals(ADMIN)) {
				request.getSession().setAttribute(USUARIO_LOGADO, usuarioLogado);
				model.addAttribute(USUARIO_LOGADO, usuarioLogado);

				request.getSession().setAttribute("usuario", usuarioLogado);
				model.addAttribute("usuario", usuarioLogado);

				request.getSession().setAttribute("clientes", clienteService.listar());
				request.getSession().setAttribute(LOGIN_INVALIDO, null);
				return new ModelAndView("selecionar-cliente");

			} else {
				if (usuarioLogado.getClientesPermissao() != null && usuarioLogado.getClientesPermissao().size() > 1) {
					request.getSession().setAttribute(USUARIO_LOGADO, usuarioLogado);
					request.getSession().setAttribute("clientes", usuarioLogado.getClientesPermissao());
					model.addAttribute(USUARIO_LOGADO, usuarioLogado);
					request.getSession().setAttribute("usuario", usuarioLogado);
					model.addAttribute("usuario", usuarioLogado);
					
					
					request.getSession().setAttribute(LOGIN_INVALIDO, null);
					return new ModelAndView("selecionar-cliente");
				} else {
					request.getSession().setAttribute(USUARIO_LOGADO, usuarioLogado);
					request.getSession().setAttribute(LOGIN_INVALIDO, null);
					
					request.getSession().setAttribute("usuario", usuarioLogado);
					model.addAttribute("usuario", usuarioLogado);

					// usuarioLogado
					// .setPerfil(perfilService
					// .consultarFuncionalidadesOperacoesUsuario(usuarioLogado
					// .getPerfil()));
					// configurarFuncionalidadesUsuario(usuarioLogado,
					// request.getSession());
				}
				if (usuarioLogado.isExibirTelaAlterarSenha()) {
					OTDAlterarSenha otd = new OTDAlterarSenha();
					otd.setIdUsuario(usuarioLogado.getId());
					model.addAttribute("otdAlterarSenha", otd);
					return new ModelAndView("alterar.senha.sem.menu");
				}

				return new ModelAndView("home");
			}

		} catch (NegocioException e) {
			request.getSession().setAttribute(USUARIO_LOGADO, null);
			request.getSession().setAttribute(LOGIN_INVALIDO, e.getMessage());
			return new ModelAndView("redirect:/");
		}
	}

	/**
	 * Página de erro do sistema.
	 */
	@RequestMapping(value = "/selecao-cliente", method = RequestMethod.POST)
	public String selecionarCliente(@ModelAttribute Usuario usuario, Model model, BindingResult result,
			HttpServletRequest request) {

		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);

		Cliente cliente = clienteService.buscar(usuario.getCliente().getId());
		usuarioLogado.setCliente(cliente);

		request.getSession().setAttribute(USUARIO_LOGADO, usuarioLogado);
		model.addAttribute(USUARIO_LOGADO, request.getSession().getAttribute(USUARIO_LOGADO));
		// usuarioLogado.setPerfil(perfilService
		// .consultarFuncionalidadesOperacoesUsuario(usuarioLogado
		// .getPerfil()));
		// configurarFuncionalidadesUsuario(usuarioLogado, request.getSession());
		return "home";
	}

	@RequestMapping(value = "/agendamento", method = RequestMethod.GET)
	public String exibirListaCalendarios(Locale locale, Model model, HttpServletRequest request) throws Exception {
		
		Date data = new Date();
		String dataAtual = new SimpleDateFormat("yyyy-MM-dd").format(data);
		
		model.addAttribute("medicos", medicoService.listar());
		model.addAttribute("medico", new Medico());
		model.addAttribute("dataAtual", dataAtual);
		return "agendamento-usuario";
	}

	@RequestMapping(value = "/agendamento/usuario", method = RequestMethod.POST)
	public String sEv(@ModelAttribute Evento evento, HttpServletRequest request) throws Exception {

		eventoService.salvar(evento);

		return "agendamento-usuario";
	}

	// @ResponseBody
	// @RequestMapping(value = "/agendamento/{idMedico}", method =
	// RequestMethod.GET, produces=
	// {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	// public Agenda buscarMedicos(@PathVariable String idMedico, Model model)
	// throws NumberFormatException, NegocioException{
	//
	// Medico medicosBanco = medicoService.buscar(new Long(idMedico));
	// Agenda a = new Agenda();
	// a.setDomingo(true);
	// a.setSegunda(true);
	// a.setQuinta(true);
	//
	// return a;
	// }

	@ResponseBody
	@RequestMapping(value = "/agendamento/{idMedico}", method = RequestMethod.GET)
	public Medico buscarMedicoPorId(@PathVariable String idMedico, Model model)
			throws NumberFormatException, NegocioException {

		Medico medicoBanco = medicoService.buscar(new Long(idMedico));

		return medicoBanco;
	}

	private void configurarFuncionalidadesUsuario(Usuario usuarioLogado, HttpSession session) {

		List<Funcionalidade> cadastros = perfilService
				.recuperarFuncionalidadesPorSecao(usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), CADASTRO);
		List<Funcionalidade> empresas = perfilService
				.recuperarFuncionalidadesPorSecao(usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), EMPRESA);
		List<Funcionalidade> exames = perfilService
				.recuperarFuncionalidadesPorSecao(usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), EXAME);
		List<Funcionalidade> usuarios = perfilService
				.recuperarFuncionalidadesPorSecao(usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), USUARIO);
		List<Funcionalidade> ppras = perfilService
				.recuperarFuncionalidadesPorSecao(usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), PPRA);
		List<Funcionalidade> agendamentos = perfilService.recuperarFuncionalidadesPorSecao(
				usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), AGENDAMENTO);
		List<Funcionalidade> relatorios = perfilService.recuperarFuncionalidadesPorSecao(
				usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), RELATORIO);
		List<Funcionalidade> movimentacoes = perfilService.recuperarFuncionalidadesPorSecao(
				usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), MOVIMENTACAO);
		List<Funcionalidade> pesquisas = perfilService
				.recuperarFuncionalidadesPorSecao(usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), PESQUISA);
		List<Funcionalidade> pesquisaEmpresas = perfilService.recuperarFuncionalidadesPorSecao(
				usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), PESQUISA_EMPRESA);
		List<Funcionalidade> pesquisaExames = perfilService.recuperarFuncionalidadesPorSecao(
				usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), PESQUISA_EXAME);
		List<Funcionalidade> pesquisaUsuarios = perfilService.recuperarFuncionalidadesPorSecao(
				usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), PESQUISA_USUARIO);
		List<Funcionalidade> pesquisaPpras = perfilService.recuperarFuncionalidadesPorSecao(
				usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), PESQUISA_PPRA);

		List<Funcionalidade> menuOff = perfilService
				.recuperarFuncionalidadesPorSecao(usuarioLogado.getPerfil().getListaFuncionalidadesUsuario(), MENU_OFF);

		session.setAttribute(CADASTROS, cadastros);
		session.setAttribute("EMPRESAS", empresas);
		session.setAttribute("EXAMES", exames);
		session.setAttribute("USUARIOS", usuarios);
		session.setAttribute("PPRAS", ppras);
		session.setAttribute("PESQUISAS", pesquisas);
		session.setAttribute("PESQUISA_EMPRESAS", pesquisaEmpresas);
		session.setAttribute("PESQUISA_EXAMES", pesquisaExames);
		session.setAttribute("PESQUISA_USUARIOS", pesquisaUsuarios);
		session.setAttribute("PESQUISA_PPRAS", pesquisaPpras);
		session.setAttribute(RELATORIO, relatorios);
		session.setAttribute(AGENDAMENTOS, agendamentos);
		session.setAttribute(MOVIMENTACOES, movimentacoes);

		configurarOperacoes(session, cadastros);
		configurarOperacoes(session, agendamentos);
		configurarOperacoes(session, relatorios);
		configurarOperacoes(session, movimentacoes);
		configurarOperacoes(session, empresas);
		configurarOperacoes(session, exames);
		configurarOperacoes(session, usuarios);
		configurarOperacoes(session, ppras);
		configurarOperacoes(session, pesquisas);
		configurarOperacoes(session, pesquisaEmpresas);
		configurarOperacoes(session, pesquisaExames);
		configurarOperacoes(session, pesquisaUsuarios);
		configurarOperacoes(session, pesquisaPpras);
	}

	private void configurarOperacoes(HttpSession session, List<Funcionalidade> funcionalidades) {
		for (Funcionalidade funcionalidade : funcionalidades) {
			for (Operacao operacao : funcionalidade.getListaOperacoesFuncionalidade()) {
				session.setAttribute(funcionalidade.getDescricao() + UNDER_LINE + operacao.getDescricao(),
						new Operacao());
			}
		}
	}

	public static Usuario getUsuarioLogado(HttpServletRequest request) {
		return (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);
	}
	
}
