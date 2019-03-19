package br.com.eclinic.controler.supervisor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import br.com.eclinic.entity.supervisor.Supervisor;
import br.com.eclinic.service.supervisor.SupervisorService;

@Controller
public class SupervisorController extends EclinicController {
	
	private static final String MODEL_ATTR_ENTIDADE = "supervisor";
	private static final String URL_REQUEST_LISTA = "/supervisores";
	private static final String URL_REQUEST_SALVAR = "/supervisor/salvar";
	private static final String URL_REQUEST_ALTERAR = "/supervisor/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/supervisor/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/supervisor/excluir";

	@Autowired
	private SupervisorService supervisorService;
	
	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String supervisorListar(@ModelAttribute Supervisor supervisor, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return listar(supervisor.getNome(), locale, model, request);

	}
	
	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String supervisores(Model model, Locale locale, HttpServletRequest request) throws Exception {
		return listar(null, locale, model, request);
	}
	
	private String listar(String nome, Locale locale, Model model, HttpServletRequest request) {

		String vazio = "";
		List<Supervisor> supervisores = new ArrayList<Supervisor>();
		
		if (nome != null && nome != vazio) {
			Supervisor supervisorConsulta = new Supervisor();
			supervisorConsulta.setNome(nome);
			supervisores = supervisorService.consultar(supervisorConsulta);
		} else {
			supervisores = supervisorService.listar();
		}

		configurarPaginacao(supervisores, model);
		Supervisor supervisor = new Supervisor();
		supervisor.setNome(nome);
		model.addAttribute(MODEL_ATTR_ENTIDADE, supervisor);
		model.addAttribute("supervisores", getPagedListHolder().getPageList());

		return "supervisores";
	}
	
	
	
	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Locale locale, Model model, HttpServletRequest request) throws Exception {
		
		model.addAttribute("supervisor", new Supervisor());
		return "supervisor_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR,method = RequestMethod.POST)
	public String salvarSupervisor (@ModelAttribute @Valid Supervisor supervisor, BindingResult result, Model model, 
			 RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "supervisor_incluir";
		} 
		
		try {
			supervisorService.salvar(supervisor);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			return "redirect:/supervisores";
			
		}catch(IllegalArgumentException e) {
			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "supervisor_incluir";
		}
		
	}
	
	// começar por aqui
	
	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {
		
		Supervisor supervisorAlteracao = supervisorService.buscar(pk);
		
		if (supervisorAlteracao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, supervisorAlteracao);
			return "supervisor_alterar";
		} else {
			return "redirect:../supervisores";
		}
	}
	
	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public String alterar(@Valid Supervisor supervisor, Model model, BindingResult result,
			 RedirectAttributes redirectAttributes, HttpServletRequest request) {


		if (result.hasErrors()) {

			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "supervisor_alterar";
			
		} else {
			
			supervisorService.alterar(supervisor);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			return "redirect:../supervisores";
		}

	}
	
	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {
		Supervisor supervisorVisualizacao = supervisorService.buscar(pk);
		if (supervisorVisualizacao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, supervisorVisualizacao);
			return "supervisor_visualizar";
		} else {
			return "redirect:../supervisores";
		}
	}
	
	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Supervisor supervisor, Model model, final RedirectAttributes redirectAttributes) {

		try {
			supervisorService.excluir(supervisor);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem("planoSaude.dependencia.com.consulta"));
		}

		return "redirect:../supervisores";
	}

	
	@ResponseBody
	@RequestMapping(value = "/supervisor/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("supervisores", getPagedListHolder().getPageList());
		model.addAttribute("supervisor", new Supervisor());
		return new ModelAndView("supervisor.lista");
	}


}
