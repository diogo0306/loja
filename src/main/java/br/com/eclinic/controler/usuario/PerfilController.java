package br.com.eclinic.controler.usuario;

import java.util.ArrayList;
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
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.FuncionalidadeOperacaoDTO;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.Perfil;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.usuario.PerfilService;
import br.com.eclinic.validator.PerfilValidator;

@Controller
public class PerfilController extends EclinicController{
	
	private static final String VISUALIZAR = "VISUALIZAR";
	private static final String EXCLUIR = "EXCLUIR";
	private static final String ALTERAR = "ALTERAR";
	private static final String INCLUIR = "INCLUIR";
	private static final String MODEL_ATTR_ENTIDADE = "perfil";
	private static final String URL_REQUEST_LISTA = "/perfis";
	private static final String URL_REQUEST_SALVAR = "/perfil/salvar";
	private static final String URL_REQUEST_ALTERAR = "/perfil/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/perfil/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/perfil/excluir";

	private static final String URL_REQUEST_ADICIONA_PERFIL = "/perfil/adicionar-perfil/{id}";
	private static final String URL_REQUEST_ADICIONA_PERFIL_POST = "/perfil/adicionar-perfil";
	
	private static final String URL_REQUEST_EXCLUIR_PERFIL = "/perfil/excluir-perfil";
	
	@Autowired
	private PerfilService service;

	
	@RequestMapping(value = URL_REQUEST_ADICIONA_PERFIL, method = RequestMethod.GET)
	public String adicionarPerfilUsuario(@PathVariable Long id, Locale locale,
			Model model) throws Exception {
		Perfil perfil = service.buscar(id);
		perfil = service.consultarFuncionalidadesOperacoesUsuario(perfil);

		List<FuncionalidadeOperacaoDTO> listaFuncionalidadesOperacaoConfigurada = configurarListaFuncionalidadesOperacoes(perfil);

		model.addAttribute("perfil", perfil);
		model.addAttribute("funcionalidades", service.listarFuncionalidades());
		model.addAttribute("funcionalidadesOperacao", listaFuncionalidadesOperacaoConfigurada);
		model.addAttribute("funcionalidadeOperacao", new FuncionalidadeOperacaoDTO());

		return "adicionar-perfil";
	}
	
	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String perfilPesquisar(@ModelAttribute Perfil perfil,
			Locale locale, Model model, HttpServletRequest request) throws Exception {

		return listar(perfil.getDescricao(), locale, model,request);
	}
	
	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String perfis(Locale locale, Model model, HttpServletRequest request) throws Exception {

		return listar(null, locale, model,request);
	}
	
	private String listar(String descricao, Locale locale, Model model, HttpServletRequest request) {
		
		List<Perfil> perfis = new ArrayList<Perfil>();
		
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		Cliente cliente = usuarioLogado.getCliente();

		if (StringUtils.isNotBlank(descricao)) {
			perfis = service.consultarPorDescricao(descricao,cliente);
		} else {
			perfis = service.listarPorCliente(cliente);
		}

		configurarPaginacao(perfis, model);
		Perfil perfil = new Perfil();
		perfil.setDescricao(descricao);
		model.addAttribute(MODEL_ATTR_ENTIDADE, perfil);
		model.addAttribute("perfis", getPagedListHolder().getPageList());

		return "perfis";
	}
	
	
	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new Perfil());

		return "perfil_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute Perfil perfil,
			BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws Exception {

		PerfilValidator validator = new PerfilValidator();
		validator.validate(perfil, result);
		
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("perfil_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, perfil);
			return mav;
		} else {
			
			List<Perfil> usuariosBanco = service.consultarPorDescricao(perfil.getDescricao(), usuarioLogado.getCliente());
			if(usuariosBanco.size() != 0){
				ModelAndView mav = new ModelAndView("perfil_incluir");
				mav.addObject(MODEL_ATTR_ENTIDADE, perfil);
				mav.addObject(MESSAGE_ERROR,
						getMensagem("perfil.existente"));
				return mav;
			}else{
				perfil.setCliente(usuarioLogado.getCliente());
				service.salvar(perfil);
				redirectAttributes.addFlashAttribute("alterar", "");
				redirectAttributes.addFlashAttribute(MESSAGE,
						getMensagem("inclusao.sucesso"));
				ModelAndView retorno = new ModelAndView("redirect:/perfil/adicionar-perfil/"+perfil.getId());
				return retorno;
			}					
		}
	}
	
	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model)
			throws Exception {
		Perfil perfilAlteracao = service.buscar(pk);
		if (perfilAlteracao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, perfilAlteracao);
			model.addAttribute("alterarTela", "");
			return "perfil_alterar";
		} else {
			return "redirect:../perfis";
		}
	}
	
	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute Perfil perfil, Model model,
			BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws NumberFormatException, NegocioException {

		PerfilValidator validator = new PerfilValidator();
		validator.validate(perfil, result);
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("perfil_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, perfil);
			return mav;
		} else {
			List<Perfil> perfisBanco = service.consultarPorDescricao(perfil.getDescricao(), usuarioLogado.getCliente());
			if(perfisBanco.size() != 0 && perfisBanco.get(0).getId() != perfil.getId()){
				ModelAndView mav = new ModelAndView("perfil_alterar");
				mav.addObject(MODEL_ATTR_ENTIDADE, perfil);
				mav.addObject(MESSAGE_ERROR,
						getMensagem("perfil.existente"));
				return mav;
			}else{
				perfil.setCliente(usuarioLogado.getCliente());
				service.alterar(perfil);
				redirectAttributes.addFlashAttribute(MESSAGE,
						getMensagem("alterar.sucesso"));
				ModelAndView retorno = new ModelAndView("redirect:/perfis");
				return retorno;
			}
		}
	}
	
	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model)
			throws Exception {
		Perfil perfilAlteracao = service.buscar(pk);
		if (perfilAlteracao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, perfilAlteracao);
			return "perfil_visualizar";
		} else {
			return "redirect:../perfis";
		}
	}
	
	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Perfil perfil, Model model,
			final RedirectAttributes redirectAttributes) {

		try {
			perfil = service.consultarFuncionalidadesOperacoesUsuario(perfil);
			service.excluir(perfil);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (NegocioException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, e.getMessage());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}

		return "redirect:/perfis";
	}
	
	@ResponseBody
	@RequestMapping(value = "/perfil/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {		
		navegarPaginacao(acao, model);
		model.addAttribute("perfis", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Perfil());
		return new ModelAndView("perfil.lista");
	}
	
	
	@RequestMapping(value = URL_REQUEST_ADICIONA_PERFIL_POST, method = RequestMethod.POST)
	public String adicionarPerfilUsuarioSalvar(@ModelAttribute FuncionalidadeOperacaoDTO funcionalidadeOperacaoDTO, Model model) throws Exception {
		Perfil perfil = service.buscar(funcionalidadeOperacaoDTO.getPerfil().getId());
		List<FuncionalidadeOperacaoDTO> listaFuncionalidadesOperacaoConfigurada;
		perfil = service.consultarFuncionalidadesOperacoesUsuario(perfil);
		
		if(funcionalidadeOperacaoDTO.getFuncionalidade().getId() == null || funcionalidadeOperacaoDTO.getFuncionalidade().getId() == 0){
			listaFuncionalidadesOperacaoConfigurada = configurarListaFuncionalidadesOperacoes(perfil);
			model.addAttribute("erroAdicionarPerfil",
					"Erro: Selecione uma funcionalidade.");
		}else{
			Funcionalidade funcionalidade = configurarFuncionalidade(funcionalidadeOperacaoDTO);
			
			listaFuncionalidadesOperacaoConfigurada = configurarListaFuncionalidadesOperacoes(perfil);
			
			
			if(validarInclusaoFuncionalidadeUsuario(perfil,funcionalidade)){
				service.salvarFuncionalidadesOperacoesUsuario(funcionalidade, perfil);
				perfil = service.consultarFuncionalidadesOperacoesUsuario(perfil);
				listaFuncionalidadesOperacaoConfigurada = configurarListaFuncionalidadesOperacoes(perfil);
			}else{
				model.addAttribute("erroAdicionarPerfil",
						"Funcionalidade já inserida para o perfil.");
			}
		}
		
		model.addAttribute("funcionalidadeOperacao", new FuncionalidadeOperacaoDTO());
		model.addAttribute("funcionalidadesOperacao", listaFuncionalidadesOperacaoConfigurada);
		model.addAttribute("funcionalidades", service.listarFuncionalidades());
		model.addAttribute("perfil", perfil);
		
		return "adicionar-perfil";
	}
	
	private Funcionalidade configurarFuncionalidade(
			FuncionalidadeOperacaoDTO funcionalidadeOperacaoDTO) {
		
		Funcionalidade funcionalidade = funcionalidadeOperacaoDTO.getFuncionalidade();
		List<Operacao> listaOperacoes = new ArrayList<Operacao>();
		
		if (funcionalidadeOperacaoDTO.isInserir()) {
			Operacao operacao = new Operacao();
			operacao.setId(new Long(1));
			listaOperacoes.add(operacao);
		}
		if (funcionalidadeOperacaoDTO.isAlterar()) {
			Operacao operacao = new Operacao();
			operacao.setId(new Long(2));
			listaOperacoes.add(operacao);
		}
		if (funcionalidadeOperacaoDTO.isExcluir()) {
			Operacao operacao = new Operacao();
			operacao.setId(new Long(3));
			listaOperacoes.add(operacao);
		}
		if (funcionalidadeOperacaoDTO.isVisualizar()) {
			Operacao operacao = new Operacao();
			operacao.setId(new Long(4));
			listaOperacoes.add(operacao);
		}
		
		funcionalidade.setListaOperacoesFuncionalidade(listaOperacoes);
		
		return funcionalidade;
	}
	
	private List<FuncionalidadeOperacaoDTO> configurarListaFuncionalidadesOperacoes(
			Perfil perfil) {
		List<FuncionalidadeOperacaoDTO> listaFuncionalidadesOperacoes = new ArrayList<FuncionalidadeOperacaoDTO>();

		for (Funcionalidade funcionalidade : perfil.getListaFuncionalidadesUsuario()) {
			FuncionalidadeOperacaoDTO funcionalidadeOperacaoDTO = new FuncionalidadeOperacaoDTO();
			funcionalidadeOperacaoDTO.setFuncionalidade(funcionalidade);
			funcionalidadeOperacaoDTO.setPerfil(perfil);
			for (Operacao operacao : funcionalidade
					.getListaOperacoesFuncionalidade()) {
				if (operacao.getDescricao().equals(INCLUIR)) {
					funcionalidadeOperacaoDTO.setInserir(true);
				}
				if (operacao.getDescricao().equals(ALTERAR)) {
					funcionalidadeOperacaoDTO.setAlterar(true);
				}
				if (operacao.getDescricao().equals(EXCLUIR)) {
					funcionalidadeOperacaoDTO.setExcluir(true);
				}
				if (operacao.getDescricao().equals(VISUALIZAR)) {
					funcionalidadeOperacaoDTO.setVisualizar(true);
				}
			}
			listaFuncionalidadesOperacoes.add(funcionalidadeOperacaoDTO);
		}
		return listaFuncionalidadesOperacoes;
	}
	
	private boolean validarInclusaoFuncionalidadeUsuario(Perfil perfil,
			Funcionalidade funcionalidadeTeste) {
		for (Funcionalidade funcionalidade : perfil.getListaFuncionalidadesUsuario()) {
			if(funcionalidade.getId().equals(funcionalidadeTeste.getId())){
				return false;
			}
		}
		return true;
	}
	
	@RequestMapping(value = URL_REQUEST_EXCLUIR_PERFIL, method = RequestMethod.POST)
	public String excluirDevice(@ModelAttribute FuncionalidadeOperacaoDTO funcionalidadeOperacaoDTO, Model model,
			final RedirectAttributes redirectAttributes) throws NegocioException {
		
		
		int indiceIdFuncionalidade =  funcionalidadeOperacaoDTO.getIdExcluir().indexOf("-");
		String idFuncionalidade = funcionalidadeOperacaoDTO.getIdExcluir().substring(0,indiceIdFuncionalidade);
		String idPerfil = funcionalidadeOperacaoDTO.getIdExcluir().substring(indiceIdFuncionalidade + 1, funcionalidadeOperacaoDTO.getIdExcluir().length());
	
		service.removerFuncionalidadeOperacaoUsuario(idFuncionalidade, idPerfil);
		
		Perfil perfil = service.buscar(new Long(idPerfil));
		
		perfil = service.consultarFuncionalidadesOperacoesUsuario(perfil);
		List<FuncionalidadeOperacaoDTO> listaFuncionalidadesOperacaoConfigurada = configurarListaFuncionalidadesOperacoes(perfil);
		
		model.addAttribute("funcionalidadeOperacao", new FuncionalidadeOperacaoDTO());
		model.addAttribute("funcionalidadesOperacao", listaFuncionalidadesOperacaoConfigurada);
		model.addAttribute("funcionalidades", service.listarFuncionalidades());
		model.addAttribute("perfil", perfil);

		return "adicionar-perfil";
	}


}
