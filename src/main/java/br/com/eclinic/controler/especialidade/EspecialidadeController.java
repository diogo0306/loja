package br.com.eclinic.controler.especialidade;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.service.especialidade.EspecialidadeService;

@Controller
public class EspecialidadeController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "especialidade";
	private static final String URL_REQUEST_LISTA = "/especialidades";
	private static final String URL_REQUEST_SALVAR = "/especialidade/salvar";
	private static final String URL_REQUEST_ALTERAR = "/especialidade/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/especialidade/visualizar";

	@Autowired
	private EspecialidadeService service;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public ModelAndView especialidade(@ModelAttribute Especialidade especialidade, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("especialidades");
		mv.addObject("espe", service.listar());
		return mv;

	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public ModelAndView exibirSalvar(Locale locale, Model model, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("especialidade_incluir");
		mv.addObject(new Especialidade());

		return mv;
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public String salvar(@ModelAttribute("especialidade") @Valid Especialidade especialidade, Model model,
			Errors errors, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (errors.hasErrors()) {
			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "especialidade_incluir";

		} else {
			service.salvar(especialidade);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			return "redirect:/especialidades";
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) {
		Especialidade especialidadevisualizacao = service.buscar(pk);
		if (especialidadevisualizacao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, especialidadevisualizacao);

			return "especialidade_alterar";
		}
		return "redirect:/especialidades";
	}
	
	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public String alterar(@ModelAttribute("especialidade") @Valid Especialidade especialidade, Errors errors,
			RedirectAttributes attributes, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			
			return "especialidade_alterar";
		}
		
		service.alterar(especialidade);
		return "redirect:/especialidades";
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) {
		Especialidade especialidadevisualizacao = service.buscar(pk);
		if (especialidadevisualizacao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, especialidadevisualizacao);

			return "especialidade_visualizar";
		}
		return "redirect:../especialidade";
	}
	
}
