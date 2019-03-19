package br.com.eclinic.controller.financeiro;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.service.consulta.ConsultaService;
import br.com.eclinic.service.contrato.ContratoService;
import br.com.eclinic.service.medico.MedicoService;
import br.com.eclinic.service.representante.RepresentanteService;

@Controller
public class FinanceiroController extends EclinicController {

	@Autowired
	private ConsultaService consultaService;
	@Autowired
	private MedicoService medicoService;
	@Autowired
	private ContratoService contratoService;
	@Autowired
	private RepresentanteService representanteService;

	@RequestMapping(value = "/pagamentos", method = RequestMethod.GET)
	public String pagamentos(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("medicos", medicoService.listar());
		model.addAttribute("consulta", new Consulta());

		return "pagamentos";
	}

	@RequestMapping(value = "/pagamentos", method = RequestMethod.POST)
	public String pesquisar(@ModelAttribute Consulta consulta, Model model, HttpServletRequest request)
			throws ParseException {
		
		List<Consulta> consultas = consultaService.listarConsultasRealizadasPorMedico(consulta.getMedico(), consulta.getMes(), consulta.getAno());
		
		for(Consulta consultaBanco : consultas) {
			if(consultaBanco.getValorConsulta() != null) {
				String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(consultaBanco.getValorConsulta().doubleValue());
				consultaBanco.setValorFormatado(valor);
			}
		}
		
		model.addAttribute("medicos", medicoService.listar());
		model.addAttribute("consulta", consulta);
		model.addAttribute("consultas", consultas);

		return "pagamentos";
	}
	
	@RequestMapping(value = "/contratosAtivos", method = RequestMethod.GET)
	public String contratosAtivos(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("representantes", representanteService.listar());
		model.addAttribute("contrato", new Contrato());

		return "contratosAtivos";
	}
	
	@RequestMapping(value = "/contratosAtivos", method = RequestMethod.POST)
	public String pesquisarContratosAtivos(@ModelAttribute Contrato contrato, Model model, HttpServletRequest request)
			throws ParseException {
		
		List<Contrato> contratos = new ArrayList<Contrato>();
		BigDecimal valorTotal = new BigDecimal(0);
		
		if(contrato.getRepresentante() != null && contrato.getRepresentante().getId() != null && contrato.getRepresentante().getId() != 0) {
			contratos = contratoService.listarContratosAtivos(contrato);
		}
		
		for(Contrato contratoValor : contratos) {
			if(contratoValor.getValorContrato() != null) {
				String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(contratoValor.getValorContrato().doubleValue());
				contratoValor.setValorContratoTransiente(valor);
				valorTotal = valorTotal.add(contratoValor.getValorContrato());
			}
		}
		
		if(valorTotal != null) {
			String val = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorTotal.doubleValue());
			model.addAttribute("valorTotal", val);
		}
		
		model.addAttribute("representantes", representanteService.listar());
		model.addAttribute("contrato", contrato);
		model.addAttribute("contratos", contratos);
		
		return "contratosAtivos";
	}

}
