package br.com.eclinic.controler.contas;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.contas.ContasPagar;

import br.com.eclinic.entity.contas.TipoContaPagamentoEnum;
import br.com.eclinic.service.contas.ContasPagarService;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.representante.RepresentanteService;
import br.com.eclinic.validator.ContasPagarValidator;

@Controller
public class ContasPagarController extends EclinicController {
	
	private static final String MODEL_ATTR_ENTIDADE = "contasPagar";
	private static final String URL_REQUEST_LISTA = "/contas_pagar";
	private static final String URL_REQUEST_SALVAR = "/contasPagar/salvar";
	
	
	@Autowired
	private ContasPagarService contasPagarService;

	@Autowired
	private CredenciadoService credenciadoService;

	@Autowired
	private RepresentanteService representanteService;
	
	
	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String contas(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new ContasPagar());
		model.addAttribute("tipos", TipoContaPagamentoEnum.values());
		
		return "contas_pagar";
	}
	
	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String contasPagarPesquisar(@ModelAttribute ContasPagar contasPagar, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		if (contasPagar.getCodigoTipoTransiente() != null
				&& contasPagar.getCodigoTipoTransiente()!= "") {
			contasPagar.setTipo(
					TipoContaPagamentoEnum.getEnumPorCodigo(Integer.parseInt(contasPagar.getCodigoTipoTransiente())));
		}


		List<ContasPagar> contas = contasPagarService.consultar(contasPagar);

		if (!contas.isEmpty()) {
			for (ContasPagar conta : contas) {

				Format formatter = new SimpleDateFormat("dd/MM/yyyy");
				String data = formatter.format(conta.getDataPagamento());
				conta.setDataFormatada(data);
				
			}
		}

		model.addAttribute("contas", contas);
		model.addAttribute(MODEL_ATTR_ENTIDADE, contasPagar);
		model.addAttribute("tipos", TipoContaPagamentoEnum.values());
		
		return "contas_pagar";
	}
	
	
	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new ContasPagar());
		model.addAttribute("tipos", TipoContaPagamentoEnum.values());
		model.addAttribute("credenciados", credenciadoService.listar());
		model.addAttribute("representantes", representanteService.listar());

		return "contas_pagar_incluir";
	}
	
	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute ContasPagar contasPagar, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		String vazio = "";

		if (contasPagar.getValorFormatado().equalsIgnoreCase("0,00")) {
			contasPagar.setValorFormatado(null);
		}

		ModelAndView modelAndView = new ModelAndView("contas_pagar_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, contasPagar);
		modelAndView.addObject("tipos", TipoContaPagamentoEnum.values());
		

		if (contasPagar.getCodigoTipoTransiente() != null
				&& contasPagar.getCodigoTipoTransiente()!= vazio) {

			contasPagar.setTipo(
					TipoContaPagamentoEnum.getEnumPorCodigo(Integer.parseInt(contasPagar.getCodigoTipoTransiente())));
		}

		if (contasPagar.getValorFormatado() != null && !contasPagar.getValorFormatado().isEmpty()) {
			contasPagar
					.setValor(new BigDecimal(contasPagar.getValorFormatado().replace(".", "").replace(",", ".")));
		}

		ContasPagarValidator validator = new ContasPagarValidator();
		validator.validate(contasPagar, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("contas_pagar_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, contasPagar);
			return mav;
		} else {

			if (contasPagar.getCredenciado().getId() == null) {
				contasPagar.setCredenciado(null);
				contasPagar.setTipo(TipoContaPagamentoEnum.REPRESENTANTE);
			} else {
				contasPagar.setRepresentante(null);
				contasPagar.setTipo(TipoContaPagamentoEnum.CREDENCIADO);
			}

			contasPagar.setValorFormatado(null);
			contasPagarService.salvar(contasPagar);

			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/contas_pagar");
			return retorno;
		}

	}
}
