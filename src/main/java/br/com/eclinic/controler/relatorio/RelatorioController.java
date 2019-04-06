package br.com.eclinic.controler.relatorio;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.controler.HomeController;

import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.entity.venda.Venda;
import br.com.eclinic.entity.venda.VendaDTO;

import br.com.eclinic.service.venda.VendaService;

@Controller
public class RelatorioController extends EclinicController {

	@Autowired
	private VendaService vendaService;

	@RequestMapping(value = "/relatorio/vendas", method = RequestMethod.GET)
	public String relatorioVendas(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("vendaDTO", new VendaDTO());

		return "relatorio-vendas";
	}

	@RequestMapping(value = "/relatorio/vendas", method = RequestMethod.POST)
	public String gerarRelatorioVendas(@ModelAttribute VendaDTO vendaDTO, BindingResult result, Model model,
			HttpServletRequest request) throws Exception {

		List<Venda> vendas = vendaService.consultarRelatorioPorPeriodo(vendaDTO.getDataInicial(),
				vendaDTO.getDataFinal());
		BigDecimal valorTotal = new BigDecimal(0);

		if (vendas != null) {
			vendas = vendaService.configurarGuiaRelatorio(vendas);
		}

		for (Venda venda : vendas) {
			if (venda.getValorVenda() != null) {
				valorTotal = valorTotal.add(venda.getValorVenda());
			}
		}

		String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorTotal.doubleValue());

		model.addAttribute("vendaDTO", vendaDTO);
		model.addAttribute("vendas", vendas);
		model.addAttribute("valorTotal", valor);

		return "guia-relatorio-vendas";
	}

}
