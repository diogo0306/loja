package br.com.eclinic.controler.fornecedor;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.fornecedor.Fornecedor;
import br.com.eclinic.service.fornecedor.FornecedorService;

@Controller
public class FornecedorController extends EclinicController {
	
	@Autowired
	private FornecedorService fornecedorService;

	@RequestMapping(value = "/fornecedores", method = RequestMethod.GET)
	public String fornecedores(Model model, HttpServletRequest request) {
		model.addAttribute("fornecedor", new Fornecedor());
		return "fornecedores";
	}
	
	@RequestMapping(value = "/fornecedores", method = RequestMethod.POST)
	public String pesquisar(@ModelAttribute Fornecedor fornecedor, Model model, HttpServletRequest request) {		
		List<Fornecedor> fornecedores = fornecedorService.pesquisar(fornecedor);		
		if(!fornecedores.isEmpty()) {
			for(Fornecedor forn : fornecedores) {
				if(forn.getValorCobrado() != null) {
					String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(forn.getValorCobrado().doubleValue());
					forn.setValorTransiente(valor);
				}
			}
		}	
		model.addAttribute("fornecedor", fornecedor);
		model.addAttribute("fornecedores", fornecedores);
		return "fornecedores";
	}

	@RequestMapping(value = "/fornecedor/incluir", method = RequestMethod.GET)
	public String incluir(Model model, HttpServletRequest request) {
		model.addAttribute("fornecedor", new Fornecedor());
		return "fornecedor_incluir";
	}

	@RequestMapping(value = "fornecedor/salvar", method= RequestMethod.POST)
	public String salvar(@ModelAttribute Fornecedor fornecedor, Errors errors, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if(errors.hasErrors()) {
			model.addAttribute("fornecedor", fornecedor);
			return "fornecedor_incluir";
		} else {
			if(fornecedor.getValorTransiente() != null && fornecedor.getValorTransiente() != "") {
				fornecedor.setValorCobrado(new BigDecimal(fornecedor.getValorTransiente().replace(".", "").replace(",", ".")));
			}
			gerarNumeroAleatorio(fornecedor);
			fornecedor.setNome(fornecedor.getNome().toUpperCase());
			fornecedorService.salvar(fornecedor);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			return "redirect:/fornecedores";
		}
	}
	
	public void gerarNumeroAleatorio(Fornecedor fornecedor) {
		List<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1; i < 100001; i++) {
			numeros.add(i);
		}
		Collections.shuffle(numeros);
		Random random = new Random();
		int x = random.nextInt(10001);
		fornecedor.setCodigo(numeros.get(x).toString());
	}
}
