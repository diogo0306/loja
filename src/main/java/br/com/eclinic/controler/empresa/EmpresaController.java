
package br.com.eclinic.controler.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.enuns.TipoPessoaEnum;
import br.com.eclinic.entity.medico.UfEnum;
import br.com.eclinic.service.cliente.ClienteService;
import br.com.eclinic.service.empresa.EmpresaService;

@Controller
public class EmpresaController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "empresa";
	private static final String URL_REQUEST_LISTA = "/empresas";
	private static final String URL_REQUEST_SALVAR = "/empresa/salvar";
	private static final String URL_REQUEST_ALTERAR = "/empresa/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/empresa/visualizar";

	@Autowired
	private EmpresaService empresaservice;
	@Autowired
	private ClienteService clienteservice;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String empresas(@ModelAttribute Empresa empresa, Locale locale, Model model) {
		List<Empresa> todasEmpresas = empresaservice.listar();
		model.addAttribute("empresas", todasEmpresas);
		
		return "empresas";
		
		
		//return listarPorNome(empresa.getNome(), locale, model);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String empresaspesquisa(@ModelAttribute Empresa empresa, Locale locale, Model model) {

		return listarPorNome(empresa.getNome(), locale, model);
	}

	private String listarPorNome(String nome, Locale locale, Model model) {

		List<Empresa> empresas = new ArrayList<Empresa>();

		if (nome != null) {
			empresas = empresaservice.consultarPorDescricao(nome);
		} else {
			empresas = empresaservice.listar();
		}

		configurarPaginacao(empresas, model);
		Empresa empresa = new Empresa();
		model.addAttribute(MODEL_ATTR_ENTIDADE, empresa);
		model.addAttribute("empresas", getPagedListHolder().getPageList());

		return "empresas";

	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public ModelAndView exibirSalvar(Empresa empresa) throws Exception {
		ModelAndView mv = new ModelAndView("empresa_incluir");
		mv.addObject(new Empresa());
		mv.addObject("uf", UfEnum.values());
		mv.addObject("statusEnum", SituacaoEnum.values());
		mv.addObject("tipopessoas", TipoPessoaEnum.values());
		mv.addObject("cliente", clienteservice.listar());

		return mv;
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public String Salvar(@ModelAttribute("empresa") @Valid Empresa empresa, Errors errors,
			RedirectAttributes attributes, Model model) throws Exception {

		
		if (errors.hasErrors()) {
			
			
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("statusEnum", SituacaoEnum.values());
			model.addAttribute("tipopessoas", TipoPessoaEnum.values());
			model.addAttribute("cliente", clienteservice.listar());

			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "empresa_incluir";
		}

		try {

			empresaservice.salvar(empresa);
			attributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			return "redirect:/empresas";

		} catch (IllegalArgumentException e) {
			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "empresa_incluir";
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {
		Empresa empresavisualizacao = empresaservice.buscar(pk);
		if (empresavisualizacao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, empresavisualizacao);
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("statusEnum", SituacaoEnum.values());
			model.addAttribute("tipopessoa", TipoPessoaEnum.values());
			model.addAttribute("cliente", clienteservice.listar());

			return "empresa_alterar";
		} else {

			return "redirect:../empresas";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public String alterar(@ModelAttribute("empresa") @Valid Empresa empresa, Errors errors,
			RedirectAttributes attributes, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("statusEnum", SituacaoEnum.values());
			model.addAttribute("tipopessoa", TipoPessoaEnum.values());
			model.addAttribute("cliente", clienteservice.listar());

			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "empresa_alterar";
		} else {

			empresaservice.alterar(empresa);
			attributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));

			return "redirect:../empresas";
		}

	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {
		Empresa empresavisualizacao = empresaservice.buscar(pk);
		if (empresavisualizacao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, empresavisualizacao);
			model.addAttribute("uf", UfEnum.values());
			model.addAttribute("statusEnum", SituacaoEnum.values());
			model.addAttribute("tipopessoa", TipoPessoaEnum.values());
			model.addAttribute("cliente", clienteservice.listar());

			return "empresa_visualizar";
		} else {

			return "redirect:../empresas";
		}
	}

}
