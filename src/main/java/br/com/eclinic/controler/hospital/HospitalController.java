package br.com.eclinic.controler.hospital;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.hospital.Hospital;
import br.com.eclinic.service.hospital.HospitalService;

@Controller
public class HospitalController extends EclinicController {
	
	@Autowired
	private HospitalService hospitalService;

	@RequestMapping(value = "/hospitais", method = RequestMethod.GET)
	public String hospitais(Model model, HttpServletRequest request) {
		model.addAttribute("hospital", new Hospital());
		return "hospitais";
	}
	
	@RequestMapping(value = "/hospitais", method = RequestMethod.POST)
	public String pesquisar(@ModelAttribute Hospital hospital, Model model, HttpServletRequest request) {
		List<Hospital> hospitais = hospitalService.pesquisar(hospital);
		if(!hospitais.isEmpty()) {
			for(Hospital hosp : hospitais) {
				if(hosp.getValorCobrado() != null) {
					String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(hosp.getValorCobrado().doubleValue());
					hosp.setValorTransiente(valor);
				}
			}
		}
		model.addAttribute("hospital", hospital);
		model.addAttribute("hospitais", hospitais);
		return "hospitais";
	}
	
	@RequestMapping(value = "/hospital/incluir", method = RequestMethod.GET)
	public String incluir (Model model, HttpServletRequest request) {		
		model.addAttribute("hospital", new Hospital());		
		return "hospital_incluir";
	}
	
	@RequestMapping(value = "/hospital/salvar", method = RequestMethod.POST)
	public String salvar(@ModelAttribute @Valid Hospital hospital, Errors errors, Model model, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {		
		if(errors.hasErrors()) {
			model.addAttribute("hospital", hospital);
			return "hospital_incluir";
		} else {
			if(hospital.getValorTransiente() != null && hospital.getValorTransiente() != "") {
				hospital.setValorCobrado(new BigDecimal(hospital.getValorTransiente().replace(".", "").replace(",", ".")));
			}
			gerarNumeroAleatorio(hospital);
			hospital.setNome(hospital.getNome().toUpperCase());
			hospitalService.salvar(hospital);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			return "redirect:/hospitais";
		}
	}
	
	public void gerarNumeroAleatorio(Hospital hospital) {
		List<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1; i < 100001; i++) {
			numeros.add(i);
		}
		Collections.shuffle(numeros);
		Random random = new Random();
		int x = random.nextInt(10001);
		hospital.setCodigo(numeros.get(x).toString());
	}
	
}
