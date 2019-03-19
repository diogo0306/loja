package br.com.eclinic.controler.representante;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.medico.SexoEnum;
import br.com.eclinic.entity.medico.UfEnum;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.representante.Representante;
import br.com.eclinic.entity.representante.TipoRepresentanteEnum;
import br.com.eclinic.service.representante.RepresentanteService;
import br.com.eclinic.service.supervisor.SupervisorService;

@Controller
public class RepresentanteController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "representante";
	private static final String URL_REQUEST_LISTA = "/representantes";
	private static final String URL_REQUEST_SALVAR = "/representante/salvar";
	private static final String URL_REQUEST_ALTERAR = "/representante/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/representante/visualizar";

	@Autowired
	private RepresentanteService representanteService;
	@Autowired
	private SupervisorService supervisorService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String representantesPesquisar(@ModelAttribute Representante representante, Locale locale, Model model)
			throws Exception {
		return listar(representante.getNome(), locale, model);

	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String representantes(Locale locale, Model model) throws Exception {

		return listar(null, locale, model);
	}

	private String listar(String nome, Locale locale, Model model) {

		List<Representante> representantes = new ArrayList<Representante>();

		if (nome != null) {
			representantes = representanteService.consultarPorDescricao(nome);
		} else {
			representantes = representanteService.listar();
		}

		configurarPaginacao(representantes, model);
		Representante representantenovo = new Representante();
		representantenovo.setNome(nome);
		model.addAttribute(MODEL_ATTR_ENTIDADE, representantenovo);
		model.addAttribute("representantes", getPagedListHolder().getPageList());
		return "representantes";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public ModelAndView solicitacoes(Representante representante) throws Exception {

		ModelAndView mv = new ModelAndView("representante_incluir");
		mv.addObject(new Representante());
		mv.addObject("tipo", TipoRepresentanteEnum.values());
		mv.addObject("uf", UfEnum.values());
		mv.addObject("sexos", SexoEnum.values());
		mv.addObject("supervisores", supervisorService.listar());
		mv.addObject("statusEnum", SituacaoEnum.values());

		return mv;
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public String salvarRepresentante(@ModelAttribute("representante") @Valid Representante representante,
			Errors errors, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (errors.hasErrors()) {

			model.addAttribute("tipo", TipoRepresentanteEnum.values());
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("sexos", SexoEnum.values());
			model.addAttribute("supervisores", supervisorService.listar());
			model.addAttribute("statusEnum", SituacaoEnum.values());

			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));

			return "representante_incluir";
			
		} else {

			representanteService.salvar(representante);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			return "redirect:/representantes";
		}
	}
	

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	private String visualizar(@PathVariable Long pk, Model model) throws Exception {
		Representante representanteVisualizar = representanteService.buscar(pk);
		if (representanteVisualizar != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, representanteVisualizar);
			model.addAttribute("tipo", TipoRepresentanteEnum.values());
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("sexos", SexoEnum.values());
			model.addAttribute("supervisores", supervisorService.listar());
			model.addAttribute("statusEnum", SituacaoEnum.values());

			return "representante_visualizar";
		} else {
			return "redirect:../representantes";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {
		Representante representanteVisualizar = representanteService.buscar(pk);
		if (representanteVisualizar != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, representanteVisualizar);
			model.addAttribute("tipo", TipoRepresentanteEnum.values());
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("sexos", SexoEnum.values());
			model.addAttribute("supervisores", supervisorService.listar());
			model.addAttribute("statusEnum", SituacaoEnum.values());


			return "representante_alterar";
		} else {

			return "redirect:../representantes";
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public String alterar(@ModelAttribute("representante") @Valid Representante representante, Errors errors,
			RedirectAttributes attributes, Model model) {

		if (errors.hasErrors()) {

			model.addAttribute("tipo", TipoRepresentanteEnum.values());
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("sexos", SexoEnum.values());
			model.addAttribute("supervisores", supervisorService.listar());
			model.addAttribute("statusEnum", SituacaoEnum.values());

			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));

			return "representante_alterar";
		} else {

			representanteService.alterar(representante);
			attributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			return "redirect:/representantes";

		}

	}
	
	@ResponseBody
	@RequestMapping(value = "/representante/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("representante", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Plano());
		return new ModelAndView("representante.lista");
	}

}
