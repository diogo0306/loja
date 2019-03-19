package br.com.eclinic.controler.contrato;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.datetime.DateFormatter;
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
import br.com.eclinic.entity.agendamento.StatusPagamentoEnum;
import br.com.eclinic.entity.contrato.CobrancaContrato;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.contrato.DiaVencimentoEnum;
import br.com.eclinic.entity.contrato.TipoPessoaContratoEnum;
import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.entity.enuns.SituacaoContratoEnum;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.representante.Representante;
import br.com.eclinic.service.cobrancaContrato.CobrancaContratoService;
import br.com.eclinic.service.contrato.ContratoService;
import br.com.eclinic.service.empresa.EmpresaService;
import br.com.eclinic.service.paciente.PacienteService;
import br.com.eclinic.service.plano.PlanoService;
import br.com.eclinic.service.representante.RepresentanteService;
import br.com.eclinic.validator.ContratoValidator;

@SuppressWarnings({ "unused" })
@Controller
public class ContratoController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "contrato";
	private static final String URL_REQUEST_LISTA = "/contratos";
	private static final String URL_REQUEST_SALVAR = "/contrato/salvar";
	private static final String URL_REQUEST_ALTERAR = "/contrato/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/contrato/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/contrato/excluir";

	@Autowired
	private ContratoService service;

	@Autowired
	private PlanoService planoService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private RepresentanteService representanteService;

	@Autowired
	private CobrancaContratoService cobrancaService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String contratosPesquisar(@ModelAttribute Contrato contrato, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return listar(contrato.getNumero(), locale, model, request);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String contratos(Locale locale, Model model, HttpServletRequest request) throws Exception {

		List<Contrato> contratos = service.listar();

		for (Contrato contrato : contratos) {
			if (contrato.getPaciente() != null) {
				Paciente paciente = pacienteService.buscar(contrato.getPaciente().getId());
				contrato.setPaciente(paciente);
			}
			if (contrato.getEmpresa() != null) {
				Empresa empresa = empresaService.buscar(contrato.getEmpresa().getId());
				contrato.setEmpresa(empresa);
			}
		}

		model.addAttribute("contrato", new Contrato());
		model.addAttribute("contratos", contratos);

		return "contratos";
	}

	private String listar(String numero, Locale locale, Model model, HttpServletRequest request) {
		String vazio = "";
		List<Contrato> contratos = new ArrayList<Contrato>();
		if (numero != null && numero != vazio) {
			Contrato contratoConsulta = new Contrato();
			contratoConsulta.setNumero(numero);
			contratos = service.consultar(contratoConsulta);
		} else {
			contratos = service.listar();
		}

		if (contratos.size() == 0) {

		}

		configurarPaginacao(contratos, model);
		Contrato contrato = new Contrato();
		contrato.setNumero(numero);
		model.addAttribute(MODEL_ATTR_ENTIDADE, contrato);
		model.addAttribute("contratos", getPagedListHolder().getPageList());

		return "contratos";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new Contrato());
		model.addAttribute("tipoPessoaContrato", TipoPessoaContratoEnum.values());
		model.addAttribute("diaVencimento", DiaVencimentoEnum.values());
		model.addAttribute("planos", planoService.listar());
		model.addAttribute("empresas", empresaService.listar());
		model.addAttribute("pacientes", pacienteService.listar());
		model.addAttribute("representantes", representanteService.listar());

		return "contrato_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute Contrato contrato, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		String vazio = "";

		if (contrato.getValorContratoTransiente().equalsIgnoreCase("0,00")) {
			contrato.setValorContratoTransiente(null);
		}

		ModelAndView modelAndView = new ModelAndView("contrato_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, contrato);
		modelAndView.addObject("tipoPessoaContrato", TipoPessoaContratoEnum.values());
		modelAndView.addObject("diaVencimento", DiaVencimentoEnum.values());
		gerarNumeroAleatorio(contrato);

		contrato.setSituacaoEnum(SituacaoContratoEnum.ATIVO);

		if (contrato.getCodigoTipoPessoaContratoTransiente() != null
				&& contrato.getCodigoTipoPessoaContratoTransiente() != vazio) {

			contrato.setTipoPessoaContratoEnum(TipoPessoaContratoEnum
					.getEnumPorCodigo(Integer.parseInt(contrato.getCodigoTipoPessoaContratoTransiente())));
		}

		if (contrato.getCodigoDiaVencimentoTransiente() != null
				&& contrato.getCodigoDiaVencimentoTransiente() != vazio) {

			contrato.setDiaVencimentoEnum(
					DiaVencimentoEnum.getEnumPorCodigo(Integer.parseInt(contrato.getCodigoDiaVencimentoTransiente())));
		}

		if (contrato.getValorContratoTransiente() != null && !contrato.getValorContratoTransiente().isEmpty()) {
			contrato.setValorContrato(
					new BigDecimal(contrato.getValorContratoTransiente().replace(".", "").replace(",", ".")));
		}

		ContratoValidator validator = new ContratoValidator();
		validator.validate(contrato, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("contrato_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, contrato);
			return mav;
		} else {

			if (contrato.getEmpresa().getId() == null) {
				contrato.setEmpresa(null);
			} else {
				contrato.setPaciente(null);
			}

			contrato.setValorContratoTransiente(null);

			service.salvar(contrato);

			contratoCobranca(contrato);

			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/contratos");
			return retorno;
		}

	}

	private void contratoCobranca(Contrato contrato) {

		List<CobrancaContrato> cobrancas = new ArrayList<CobrancaContrato>();
		List<Date> datesInRange = new ArrayList<Date>();

		CobrancaContrato cob = new CobrancaContrato();
		cob.setValor(contrato.getValorTotal());
		cob.setNome("1ª Parcela da Mensalidade");
		cob.setDataPagamento(new Date());
		cob.setDataVencimento(new Date());
		cob.setPacienteVinculo(contrato.getPaciente());
		cob.setContrato(contrato);
		cob.setParcela(1);
		cobrancas.add(cob);

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(contrato.getInicioVigencia());
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);

		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(contrato.getFimVigencia());

		int parcela = 2;

		while (calendar.before(endCalendar)) {
			Date result = calendar.getTime();
			datesInRange.add(result);
			calendar.add(Calendar.MONTH, 1);
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(result);
			gregorianCalendar.set(Calendar.DAY_OF_MONTH,
					Integer.parseInt(contrato.getDiaVencimentoEnum().getDescricao()));
			int dia = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
			CobrancaContrato cc = new CobrancaContrato();
			cc.setDataVencimento(gregorianCalendar.getTime());
			cc.setValor(contrato.getValorTotal());
			cc.setNome("Mensalidade");
			cc.setDataCobranca(dia);
			cc.setPacienteVinculo(contrato.getPaciente());
			cc.setContrato(contrato);
			cc.setParcela(parcela);
			cc.setStatusPagamento(StatusPagamentoEnum.PENDENTE);
			cobrancas.add(cc);
			parcela++;
		}

		try {
			for (CobrancaContrato cobrancaContrato : cobrancas) {
				cobrancaService.salvar(cobrancaContrato);
			}
		} catch (Exception e) {
			new Exception(e);
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {
		Contrato contratoAlteracao = service.buscar(pk);

		Plano plano = planoService.buscar(contratoAlteracao.getPlano().getId());
		Representante representante = representanteService.buscar(contratoAlteracao.getRepresentante().getId());

		contratoAlteracao.setPlano(plano);
		contratoAlteracao.setRepresentante(representante);

		if (contratoAlteracao.getEmpresa() != null) {
			Empresa empresa = empresaService.buscar(contratoAlteracao.getEmpresa().getId());
			contratoAlteracao.setEmpresa(empresa);
		} else {
			Paciente paciente = pacienteService.buscar(contratoAlteracao.getPaciente().getId());
			contratoAlteracao.setPaciente(paciente);
		}

		if (contratoAlteracao != null) {

			if (contratoAlteracao.getValorContratoTransiente() != null
					&& !contratoAlteracao.getValorContratoTransiente().isEmpty()) {
				contratoAlteracao.setValorContrato(new BigDecimal(
						contratoAlteracao.getValorContratoTransiente().replace(".", "").replace(",", ".")));
			}

			contratoAlteracao.setCodigoTipoPessoaContratoTransiente(
					String.valueOf(contratoAlteracao.getTipoPessoaContratoEnum().getCodigo()));
			contratoAlteracao.setCodigoDiaVencimentoTransiente(
					String.valueOf(contratoAlteracao.getDiaVencimentoEnum().getCodigo()));

			contratoAlteracao.setValorContratoTransiente(contratoAlteracao.getValorContrato().toString());
			contratoAlteracao.setNumero(contratoAlteracao.getNumero());

			model.addAttribute(MODEL_ATTR_ENTIDADE, contratoAlteracao);
			model.addAttribute("tipoPessoaContrato", TipoPessoaContratoEnum.values());
			model.addAttribute("diaVencimento", DiaVencimentoEnum.values());
			model.addAttribute("situacao", SituacaoEnum.values());
			model.addAttribute("planos", planoService.listar());
			model.addAttribute("empresas", empresaService.listar());
			model.addAttribute("pacientes", pacienteService.listar());
			model.addAttribute("representantes", representanteService.listar());

			return "contrato_alterar";
		} else {
			return "redirect:../contratos";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute Contrato contrato, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		String vazio = "";

		ModelAndView modelAndView = new ModelAndView("contrato_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, contrato);
		modelAndView.addObject("tipoPessoaContrato", TipoPessoaContratoEnum.values());
		modelAndView.addObject("diaVencimento", DiaVencimentoEnum.values());
		modelAndView.addObject("situacao", SituacaoEnum.values());
		modelAndView.addObject("planos", planoService.listar());
		modelAndView.addObject("empresas", empresaService.listar());
		modelAndView.addObject("pacientes", pacienteService.listar());
		modelAndView.addObject("representantes", representanteService.listar());

		contrato.setSituacaoEnum(SituacaoContratoEnum.ATIVO);

		if (contrato.getCodigoTipoPessoaContratoTransiente() != null
				&& contrato.getCodigoTipoPessoaContratoTransiente() != vazio) {

			contrato.setTipoPessoaContratoEnum(TipoPessoaContratoEnum
					.getEnumPorCodigo(Integer.parseInt(contrato.getCodigoTipoPessoaContratoTransiente())));
		}

		if (contrato.getCodigoDiaVencimentoTransiente() != null
				&& contrato.getCodigoDiaVencimentoTransiente() != vazio) {

			contrato.setDiaVencimentoEnum(
					DiaVencimentoEnum.getEnumPorCodigo(Integer.parseInt(contrato.getCodigoDiaVencimentoTransiente())));
		}

		if (contrato.getValorContratoTransiente() != null && !contrato.getValorContratoTransiente().isEmpty()) {
			contrato.setValorContrato(
					new BigDecimal(contrato.getValorContratoTransiente().replace(".", "").replace(",", ".")));
		}

		ContratoValidator validator = new ContratoValidator();
		validator.validate(contrato, result);

		if (result.hasErrors()) {

			ModelAndView mav = new ModelAndView("contrato_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, contrato);
			return mav;

		} else {

			if (contrato.getEmpresa().getId() == null) {
				contrato.setEmpresa(null);
			} else {
				contrato.setPaciente(null);
			}

			service.alterar(contrato);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/contratos");
			return retorno;
		}
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {
		Contrato contratoVisualizar = service.buscar(pk);
		Plano plano = planoService.buscar(contratoVisualizar.getPlano().getId());
		Representante representante = representanteService.buscar(contratoVisualizar.getRepresentante().getId());

		Empresa empresa = empresaService.buscar(pk);
		Paciente paciente = pacienteService.buscar(pk);

		contratoVisualizar.setPaciente(paciente);
		contratoVisualizar.setEmpresa(empresa);

		contratoVisualizar.setPlano(plano);
		contratoVisualizar.setRepresentante(representante);

		if (contratoVisualizar != null) {

			model.addAttribute(MODEL_ATTR_ENTIDADE, contratoVisualizar);
			model.addAttribute("tipoPessoaContrato", TipoPessoaContratoEnum.values());
			model.addAttribute("diaVencimento", DiaVencimentoEnum.values());
			model.addAttribute("situacao", SituacaoEnum.values());
			model.addAttribute("planos", planoService.listar());
			model.addAttribute("empresas", empresaService.listar());
			model.addAttribute("pacientes", pacienteService.listar());
			model.addAttribute("representantes", representanteService.listar());

			return "contrato_visualizar";
		} else {
			return "redirect:../contratos";
		}
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Contrato contrato, Model model, final RedirectAttributes redirectAttributes) {

		try {
			service.excluir(contrato);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem(""));
		}

		return "redirect:/contratos";
	}

	@ResponseBody
	@RequestMapping(value = "/contrato/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("contratos", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Contrato());
		return new ModelAndView("contrato.lista");
	}

	public void gerarNumeroAleatorio(Contrato contrato) {

		List<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1; i < 100001; i++) {
			numeros.add(i);
		}

		Collections.shuffle(numeros);

		Random random = new Random();
		int x = random.nextInt(10001);

		contrato.setNumero(numeros.get(x).toString());
	}

}
