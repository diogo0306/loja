package br.com.eclinic.controler.procedimento;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.procedimento.Procedimento;
import br.com.eclinic.service.procedimento.ProcedimentoService;

@Controller
public class ProcedimentoController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "procedimento";
	private static final String URL_REQUEST_LISTA = "/procedimentos";
	private static final String URL_REQUEST_SALVAR = "/procedimento/salvar";
	private static final String URL_REQUEST_ALTERAR = "/procedimento/alterar";
	//private static final String URL_REQUEST_VISUALIZAR = "/procedimento/visualizar";

	@Autowired
	private ProcedimentoService procedimentoService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	private String procedimentos(Model model, HttpServletRequest request) {
		model.addAttribute("procedimentos", procedimentoService.listar());
		return "procedimento_listar";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	private String salvarProcedimento(Model model, HttpServletRequest request) {
		model.addAttribute("procedimento", new Procedimento());
		return "procedimento_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	private String salvar(@Valid Procedimento procedimento, Model model, HttpServletRequest request,
			BindingResult result, final RedirectAttributes redirectAttributes) throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("procedimento", procedimento);
			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "procedimento_incluir";
		}

		try {

			procedimentoService.salvar(procedimento);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		} catch (Exception e) {
			new Exception(e);
		}

		return "redirect:/procedimentos";
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) {
		Procedimento procedimentovisualizar = procedimentoService.buscar(pk);
		if (procedimentovisualizar != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, procedimentovisualizar);

			return "procedimento_alterar";
		}
		return "redirect:../grupos";
	}

	
	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public String alterar(@ModelAttribute("procedimento") @Valid Procedimento procedimento, Errors errors,
			RedirectAttributes attributes, Model model) {

		if (errors.hasErrors()) {
			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "procedimento_alterar";
		}else {
			
			attributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			procedimentoService.alterar(procedimento);
			return "redirect:../procedimentos";
			
		}
		
	}

}
