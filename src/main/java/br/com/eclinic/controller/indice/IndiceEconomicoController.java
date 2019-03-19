package br.com.eclinic.controller.indice;

import java.math.BigDecimal;
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
import br.com.eclinic.entity.indice.IndiceEconomico;
import br.com.eclinic.service.indice.IndiceEconomicoService;
import br.com.eclinic.validator.IndiceEconomicoValidator;

@Controller
public class IndiceEconomicoController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "indice";
	private static final String URL_REQUEST_LISTA = "/indices";
	private static final String URL_REQUEST_SALVAR = "/indice/salvar";
	private static final String URL_REQUEST_ALTERAR = "/indice/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/indice/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/indice/excluir";

	@Autowired
	private IndiceEconomicoService indiceEconomicoService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String indicePesquisar(@ModelAttribute IndiceEconomico indiceEconomico, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return listar(indiceEconomico.getNome(), locale, model, request);

	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String indices(Model model, Locale locale, HttpServletRequest request) throws Exception {
		return listar(null, locale, model, request);
	}

	private String listar(String nome, Locale locale, Model model, HttpServletRequest request) {

		String vazio = "";
		List<IndiceEconomico> indices = new ArrayList<IndiceEconomico>();
		/* Usuario usuarioLogado = HomeController.getUsuarioLogado(request); */
		if (nome != null && nome != vazio) {
			IndiceEconomico indiceConsulta = new IndiceEconomico();
			indiceConsulta.setNome(nome);
			indices = indiceEconomicoService.consultar(indiceConsulta);
		} else {
			indices = indiceEconomicoService.listar();
		}

		configurarPaginacao(indices, model);	
		IndiceEconomico indiceEconomico = new IndiceEconomico();
		indiceEconomico.setNome(nome);
		model.addAttribute(MODEL_ATTR_ENTIDADE, indiceEconomico);
		model.addAttribute("indices", getPagedListHolder().getPageList());

		return "indices";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new IndiceEconomico());
		return "indice_economico_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute IndiceEconomico indiceEconomico, Model model, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {

		IndiceEconomicoValidator	 validator = new IndiceEconomicoValidator();
		validator.validate(indiceEconomico, result);
		
		if(indiceEconomico.getPercentualTransiente() != null && indiceEconomico.getPercentualTransiente() != "") {
			indiceEconomico.setPercentual(new BigDecimal(indiceEconomico.getPercentualTransiente().replace(",", ".")));
		}

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("indice_economico_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, indiceEconomico);
			return mav;
		} else {

			indiceEconomicoService.salvar(indiceEconomico);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/indices");
			return retorno;
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {
		
		IndiceEconomico indiceAlteracao = indiceEconomicoService.buscar(pk);
		
		if (indiceAlteracao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, indiceAlteracao);
			return "indice_economico_alterar";
		} else {
			return "redirect:../indices";
		}
	}
	
	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute IndiceEconomico indiceEconomico, Model model, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {

		IndiceEconomicoValidator validator = new IndiceEconomicoValidator();
		validator.validate(indiceEconomico, result);

		if (result.hasErrors()) {

			ModelAndView mav = new ModelAndView("indice_economico_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, indiceEconomico);
			return mav;
			
		} else {
			indiceEconomicoService.alterar(indiceEconomico);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/indices");
			return retorno;
		}

	}
	
	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {
		IndiceEconomico indiceVisualizacao = indiceEconomicoService.buscar(pk);
		if (indiceVisualizacao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, indiceVisualizacao);
			return "indice_economico_visualizar";
		} else {
			return "redirect:../indices";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/indice/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("indices", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new IndiceEconomico());
		return new ModelAndView("indice.lista");
	}
	
	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute IndiceEconomico indiceEconomico, Model model, final RedirectAttributes redirectAttributes) {

		try {
			indiceEconomicoService.excluir(indiceEconomico);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem("planoSaude.dependencia.com.consulta"));
		}

		return "redirect:../indices";
	}

}
