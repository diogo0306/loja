package br.com.eclinic.controler.venda;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.venda.Venda;
import br.com.eclinic.service.venda.VendaService;

@Controller
public class VendaController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "venda";
	private static final String URL_REQUEST_LISTA = "/vendas";
	private static final String URL_REQUEST_SALVAR = "/venda/salvar";
	private static final String URL_REQUEST_ALTERAR = "/venda/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/venda/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/venda/excluir";

	@Autowired
	private VendaService vendaService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String vendaPesquisar(@ModelAttribute Venda venda, Locale locale, Model model, HttpServletRequest request)
			throws Exception {

		List<Venda> vendas = vendaService.consultar(venda);
		model.addAttribute("vendas", vendas);
		model.addAttribute("venda", new Venda());

		return "vendas";
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String vendas(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("venda", new Venda());
		return "vendas";
	}

	@ResponseBody
	@RequestMapping(value = "/venda/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("vendas", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Venda());
		return new ModelAndView("venda.lista");
	}

}
