package br.com.eclinic.controler.exame;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.exame.BeneficiarioExame;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.entity.exame.TabelaExame;
import br.com.eclinic.entity.exame.TipoStatusEnum;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.exame.BeneficiarioExameService;
import br.com.eclinic.service.exame.TabelaExameService;
import br.com.eclinic.service.exame.TabelaService;
import br.com.eclinic.service.paciente.PacienteService;

@Controller
public class BeneficiarioExameController extends EclinicController {

	@Autowired
	private BeneficiarioExameService beneficiarioExameService;
	@Autowired
	private CredenciadoService credenciadoService;
	@Autowired
	private TabelaService tabelaService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private TabelaExameService tabelaExameService;

	@RequestMapping(value = "/beneficiarioExames", method = RequestMethod.GET)
	public String beneficiarioExames(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("beneficiarioExame", new BeneficiarioExame());
		model.addAttribute("beneficiarioExames", beneficiarioExameService.listar());
		model.addAttribute("status", TipoStatusEnum.values());
		return "beneficiarioExames";
	}

	@RequestMapping(value = "/beneficiarioExames", method = RequestMethod.POST)
	public String pesquisar(@ModelAttribute BeneficiarioExame beneficiarioExame, Model model,
			HttpServletRequest request) throws ParseException {
		
		List<BeneficiarioExame> lista = new ArrayList<BeneficiarioExame>();
		
		if(beneficiarioExame.getTipoTransiente() != null && beneficiarioExame.getTipoTransiente() != "") {
			beneficiarioExame.setStatus(TipoStatusEnum.getEnumPorCodigo(Integer.parseInt(beneficiarioExame.getTipoTransiente())));
		}
		
		lista = beneficiarioExameService.consultar(beneficiarioExame);
		
		if(lista == null) {
			lista = beneficiarioExameService.listar();
		}
		
		model.addAttribute("beneficiarioExame", beneficiarioExame);
		model.addAttribute("beneficiarioExames", lista);
		model.addAttribute("status", TipoStatusEnum.values());

		return "beneficiarioExames";
	}

	@RequestMapping(value = "/beneficiarioExame/salvar", method = RequestMethod.GET)
	public String salvar(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("beneficiarioDTO", new BeneficiarioDTO());
		model.addAttribute("pacientes",
				pacienteService.listarPorCliente(HomeController.getUsuarioLogado(request).getCliente()));
		model.addAttribute("credenciados", credenciadoService.listar());
		model.addAttribute("tabelas", tabelaService.listar());
		model.addAttribute("exames", new ArrayList<Exame>());
		return "beneficiarioExame_incluir";
	}

	@RequestMapping(value = "/beneficiarioExame/salvar", method = RequestMethod.POST)
	public ModelAndView adicionar(@ModelAttribute @Valid BeneficiarioDTO beneficiarioDTO, BindingResult result,
			Model model, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("beneficiarioExame_incluir");
		mav.addObject("pacientes",
				pacienteService.listarPorCliente(HomeController.getUsuarioLogado(request).getCliente()));
		mav.addObject("credenciados", credenciadoService.listar());
		mav.addObject("tabelas", tabelaService.listar());
		if (beneficiarioDTO.getTabela().getId() != null) {
			mav.addObject("exames", tabelaExameService.consultarExamesPorTabela(beneficiarioDTO.getTabela()));
		} else {
			mav.addObject("exames", new ArrayList<Exame>());
		}

		if (result.hasErrors()) {
			mav.addObject("beneficiarioDTO", beneficiarioDTO);
			return mav;
		}

		if (beneficiarioDTO.getIdPaciente() != null) {
			beneficiarioExameService.configurarForm(beneficiarioDTO);
		}

		mav.addObject("beneficiarioDTO", beneficiarioDTO);
		mav.addObject("valorTotal", beneficiarioDTO.getValorTotalFormatado());

		return mav;
	}

	@RequestMapping(value = "/beneficiarioExame/finalizar", method = RequestMethod.POST)
	public String salvar(@ModelAttribute @Valid BeneficiarioDTO beneficiarioDTO, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors() || beneficiarioDTO.getListaTabelaExame() == null) {
			model.addAttribute("beneficiarioDTO", beneficiarioDTO);
			model.addAttribute("pacientes",
					pacienteService.listarPorCliente(HomeController.getUsuarioLogado(request).getCliente()));
			model.addAttribute("credenciados", credenciadoService.listar());
			model.addAttribute("tabelas", tabelaService.listar());
			model.addAttribute("exames", tabelaExameService.consultarExamesPorTabela(beneficiarioDTO.getTabela()));
			return "beneficiarioExame_incluir";
		}

		beneficiarioExameService.configurarTotalizadorValores(beneficiarioDTO);

		Paciente paciente = new Paciente();
		Credenciado credenciado = new Credenciado();
		BeneficiarioExame beneficiarioExame = new BeneficiarioExame();
		paciente.setId(beneficiarioDTO.getIdPaciente());
		credenciado.setId(beneficiarioDTO.getIdCredenciado());
		beneficiarioExame.setData(new Date());
		beneficiarioExame.setPaciente(paciente);
		beneficiarioExame.setCredenciado(credenciado);
		beneficiarioExame.setValorTotal(beneficiarioDTO.getValorTotal());
		beneficiarioExame.setStatus(TipoStatusEnum.PENDENTE);
		gerarNumeroAleatorio(beneficiarioExame);

		beneficiarioExameService.salvar(beneficiarioExame);

		for (TabelaExame tabelaExame : beneficiarioDTO.getListaTabelaExame()) {
			if (tabelaExame.getValor() != null) {
				tabelaExame.setBeneficiarioExame(beneficiarioExame);
				tabelaExameService.salvar(tabelaExame);
			}
		}

		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));

		return "redirect:../beneficiarioExames";
	}

	@RequestMapping(value = "/beneficiarioExame/recalcular_valor", method = RequestMethod.POST)
	public ModelAndView recalcularValorTotal(@ModelAttribute @Valid BeneficiarioDTO beneficiarioDTO,
			BindingResult result, Model model, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("beneficiarioExame_incluir");
		mav.addObject("pacientes",
				pacienteService.listarPorCliente(HomeController.getUsuarioLogado(request).getCliente()));
		mav.addObject("credenciados", credenciadoService.listar());
		mav.addObject("tabelas", tabelaService.listar());
		if (beneficiarioDTO.getTabela().getId() != null) {
			mav.addObject("exames", tabelaExameService.consultarExamesPorTabela(beneficiarioDTO.getTabela()));
		} else {
			mav.addObject("exames", new ArrayList<Exame>());
		}

		beneficiarioExameService.ajustarLista(beneficiarioDTO);
		beneficiarioExameService.configurarTotalizadorValores(beneficiarioDTO);
		mav.addObject("beneficiarioDTO", beneficiarioDTO);
		mav.addObject("valorTotal", beneficiarioDTO.getValorTotalFormatado());

		return mav;
	}

	@RequestMapping(value = "/beneficiarioExame/visualizar/{id}", method = RequestMethod.GET)
	public String visualizar(@PathVariable String id, Model model) throws NegocioException {

		BeneficiarioExame beneficiarioExame = beneficiarioExameService.buscar(new Long(id));
		Credenciado credenciado = credenciadoService.buscar(beneficiarioExame.getCredenciado().getId());
		Paciente paciente = pacienteService.buscar(beneficiarioExame.getPaciente().getId());
		beneficiarioExame.setCredenciado(credenciado);
		beneficiarioExame.setPaciente(paciente);
		List<TabelaExame> lista = tabelaExameService.consultarPorBeneficiarioExame(beneficiarioExame);
		beneficiarioExame.setListaTabelaExame(lista);

		for (TabelaExame tabelaExame : beneficiarioExame.getListaTabelaExame()) {
			String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
					.format(tabelaExame.getValor().doubleValue());
			tabelaExame.setValorTransiente(valor);
		}

		String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
				.format(beneficiarioExame.getValorTotal().doubleValue());
		beneficiarioExame.setValorTotalFormatado(valor);

		model.addAttribute("beneficiarioExame", beneficiarioExame);

		return "beneficiarioExame_visualizar";
	}

	@RequestMapping(value = "/beneficiarioExame/confirmar", method = RequestMethod.POST)
	public String confirmar(@ModelAttribute BeneficiarioExame beneficiarioExame, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws NegocioException {

		try {
			if (beneficiarioExame.getId() != null) {
				BeneficiarioExame beneficiarioBanco = beneficiarioExameService.buscar(beneficiarioExame.getId());
				beneficiarioBanco.setStatus(TipoStatusEnum.PAGO);
				beneficiarioBanco.setDataPagamento(new Date());
				beneficiarioExameService.alterar(beneficiarioBanco);

				redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("pagamento.confirmado"));
			}

		} catch (Exception e) {
			new Exception(e);
		}

		return "redirect:../beneficiarioExames";
	}
	
	@RequestMapping(value = "/beneficiarioExame/emitir-comprovante", method = RequestMethod.POST)
	public String emitirComprovante(@ModelAttribute BeneficiarioExame beneficiarioExame, BindingResult result, Model model,
			HttpServletRequest request) throws NegocioException {
		
		try {
			BeneficiarioExame beneficiarioBanco = beneficiarioExameService.buscar(beneficiarioExame.getId());
			Credenciado credenciado = credenciadoService.buscar(beneficiarioBanco.getCredenciado().getId());
			Paciente paciente = pacienteService.buscar(beneficiarioBanco.getPaciente().getId());
			beneficiarioBanco.setCredenciado(credenciado);
			beneficiarioBanco.setPaciente(paciente);
			List<TabelaExame> lista = tabelaExameService.consultarPorBeneficiarioExame(beneficiarioBanco);
			beneficiarioBanco.setListaTabelaExame(lista);

			for (TabelaExame tabelaExame : beneficiarioBanco.getListaTabelaExame()) {
				String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(tabelaExame.getValor().doubleValue());
				tabelaExame.setValorTransiente(valor);
			}

			String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
					.format(beneficiarioBanco.getValorTotal().doubleValue());
			beneficiarioBanco.setValorTotalFormatado(valor);
			
			if(beneficiarioBanco.getDataPagamento() != null) {
				GregorianCalendar dataConfec = new GregorianCalendar();
				dataConfec.setTime(beneficiarioBanco.getDataPagamento());
				String dataFormatada = dataConfec.get(Calendar.DAY_OF_MONTH) + "/"
						+ (dataConfec.get(Calendar.MONTH) + 1) + "/" + dataConfec.get(Calendar.YEAR);
				model.addAttribute("dataPagamentoFormatada", dataFormatada);
			}

			model.addAttribute("beneficiarioExame", beneficiarioBanco);
			
		} catch (Exception e) {
			new Exception(e);
		}
		
		return "emitir-comprovante-gerar";
	}

	@ResponseBody
	@RequestMapping(value = "/beneficiarioExame/buscarExames/{idTabela}", method = RequestMethod.GET)
	public List<ExameDTO> buscarSetoresPorEmpresa(@PathVariable String idTabela, Model model) {
		Tabela tabela = new Tabela();
		tabela.setId(new Long(idTabela));
		List<Exame> examesBanco = tabelaExameService.consultarExamesPorTabela(tabela);

		List<ExameDTO> exames = new ArrayList<ExameDTO>();
		for (Exame exame : examesBanco) {
			ExameDTO exameDTO = new ExameDTO();
			exameDTO.setId(exame.getId());
			exameDTO.setNome(exame.getNome());

			exames.add(exameDTO);
		}

		return exames;
	}

	public void gerarNumeroAleatorio(BeneficiarioExame beneficiarioExame) {

		List<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1; i < 100001; i++) {
			numeros.add(i);
		}

		Collections.shuffle(numeros);

		Random random = new Random();
		int x = random.nextInt(10001);

		beneficiarioExame.setCodigo(numeros.get(x).toString());
	}

}
