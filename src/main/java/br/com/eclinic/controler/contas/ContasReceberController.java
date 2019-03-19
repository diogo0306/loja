package br.com.eclinic.controler.contas;

import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.contas.ContasReceber;
import br.com.eclinic.entity.contas.TipoContaEnum;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.representante.Representante;
import br.com.eclinic.service.autorizacao.AutorizacaoService;
import br.com.eclinic.service.contas.ContasReceberService;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.representante.RepresentanteService;
import br.com.eclinic.validator.ContasReceberValidator;

@Controller
public class ContasReceberController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "contasReceber";
	private static final String URL_REQUEST_LISTA = "/contas_receber";
	private static final String URL_REQUEST_SALVAR = "/contasReceber/salvar";
	private static final String URL_REQUEST_ALTERAR = "/contasReceber/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/contasReceber/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/contasReceber/excluir";

	@Autowired
	private ContasReceberService contasReceberService;
	@Autowired
	private CredenciadoService credenciadoService;
	@Autowired
	private RepresentanteService representanteService;
	@Autowired
	private AutorizacaoService autorizacaoService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String contasPesquisar(@ModelAttribute ContasReceber contasReceber, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		if (contasReceber.getCodigoTipoContaTransiente() != null
				&& contasReceber.getCodigoTipoContaTransiente() != "") {
			contasReceber.setTipoContaEnum(
					TipoContaEnum.getEnumPorCodigo(Integer.parseInt(contasReceber.getCodigoTipoContaTransiente())));
		}

		if (contasReceber.getCodigoTipoStatusTransiente() != null
				&& contasReceber.getCodigoTipoStatusTransiente() != "") {
			contasReceber.setStatus(StatusPagamentoEnum
					.getEnumPorCodigo(Integer.parseInt(contasReceber.getCodigoTipoStatusTransiente())));
		}

		List<ContasReceber> contas = contasReceberService.consultar(contasReceber);

		if (!contas.isEmpty()) {
			for (ContasReceber conta : contas) {
				if(conta.getAutorizacao() != null) {
					Autorizacao autorizacao = autorizacaoService.buscar(conta.getAutorizacao().getId());
					autorizacao.setExames(null);
					autorizacao.setHospital(null);
					autorizacao.setFornecedor(null);
					autorizacao.setCredenciado(null);
					autorizacao.setPaciente(null);
				}
				if(conta.getDataPagamento() != null) {
					Format formatter = new SimpleDateFormat("dd/MM/yyyy");
					String data = formatter.format(conta.getDataPagamento());
					conta.setDataPagamentoFormatada(data);
				}
				if(conta.getDataVencimento() != null) {
					Format format = new SimpleDateFormat("dd/MM/yyyy");
					String dataVencimento = format.format(conta.getDataVencimento());
					conta.setDataVencimentoFormatada(dataVencimento);
				}
			}
		}

		model.addAttribute("contas", contas);
		model.addAttribute("contasReceber", contasReceber);
		model.addAttribute("tipos", TipoContaEnum.values());
		model.addAttribute("status", StatusPagamentoEnum.values());

		return "contas_receber";
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String contas(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new ContasReceber());
		model.addAttribute("tipos", TipoContaEnum.values());
		model.addAttribute("status", StatusPagamentoEnum.values());
		return "contas_receber";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new ContasReceber());
		model.addAttribute("tipos", TipoContaEnum.values());
		model.addAttribute("status", StatusPagamentoEnum.values());
		model.addAttribute("credenciados", credenciadoService.listar());
		model.addAttribute("representantes", representanteService.listar());

		return "contas_receber_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute ContasReceber contasReceber, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		String vazio = "";

		if (contasReceber.getValorTransiente().equalsIgnoreCase("0,00")) {
			contasReceber.setValorTransiente(null);
		}

		if (contasReceber.getValorPagoTransiente().equalsIgnoreCase("0,00")) {
			contasReceber.setValorPagoTransiente(null);
		}

		ModelAndView modelAndView = new ModelAndView("contas_receber_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, contasReceber);
		modelAndView.addObject("tipos", TipoContaEnum.values());
		modelAndView.addObject("status", StatusPagamentoEnum.values());

		if (contasReceber.getCodigoTipoContaTransiente() != null
				&& contasReceber.getCodigoTipoContaTransiente() != vazio) {

			contasReceber.setTipoContaEnum(
					TipoContaEnum.getEnumPorCodigo(Integer.parseInt(contasReceber.getCodigoTipoContaTransiente())));
		}

		if (contasReceber.getCodigoTipoStatusTransiente() != null
				&& contasReceber.getCodigoTipoStatusTransiente() != vazio) {

			contasReceber.setStatus(StatusPagamentoEnum
					.getEnumPorCodigo(Integer.parseInt(contasReceber.getCodigoTipoStatusTransiente())));
		}

		if (contasReceber.getValorTransiente() != null && !contasReceber.getValorTransiente().isEmpty()) {
			contasReceber
					.setValor(new BigDecimal(contasReceber.getValorTransiente().replace(".", "").replace(",", ".")));
		}

		if (contasReceber.getValorPagoTransiente() != null && !contasReceber.getValorPagoTransiente().isEmpty()) {
			contasReceber.setValorPago(
					new BigDecimal(contasReceber.getValorPagoTransiente().replace(".", "").replace(",", ".")));
		}

		ContasReceberValidator validator = new ContasReceberValidator();
		validator.validate(contasReceber, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("contas_receber_incluir");
			mav.addObject(MODEL_ATTR_ENTIDADE, contasReceber);
			return mav;
		} else {

			if (contasReceber.getCredenciado().getId() == null) {
				contasReceber.setCredenciado(null);
			} else {
				contasReceber.setRepresentante(null);
			}

			contasReceber.setStatus(StatusPagamentoEnum.PAGO);
			contasReceber.setValorTransiente(null);
			contasReceber.setValorPagoTransiente(null);
			contasReceberService.salvar(contasReceber);

			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/contas_receber");
			return retorno;
		}

	}

	@SuppressWarnings("unused")
	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {

		ContasReceber contasAlteracao = contasReceberService.buscar(pk);
		Credenciado credenciado = credenciadoService.buscar(contasAlteracao.getCredenciado().getId());
		Representante representante = representanteService.buscar(contasAlteracao.getRepresentante().getId());

		contasAlteracao.setCredenciado(credenciado);
		contasAlteracao.setRepresentante(representante);

		if (contasAlteracao != null) {

			if (contasAlteracao.getValorTransiente() != null && !contasAlteracao.getValorTransiente().isEmpty()) {
				contasAlteracao.setValor(
						new BigDecimal(contasAlteracao.getValorTransiente().replace(".", "").replace(",", ".")));
			}

			if (contasAlteracao.getValorPagoTransiente() != null
					&& !contasAlteracao.getValorPagoTransiente().isEmpty()) {
				contasAlteracao.setValorPago(
						new BigDecimal(contasAlteracao.getValorPagoTransiente().replace(".", "").replace(",", ".")));
			}

			contasAlteracao
					.setCodigoTipoContaTransiente(String.valueOf(contasAlteracao.getTipoContaEnum().getCodigo()));

			contasAlteracao.setCodigoTipoStatusTransiente(String.valueOf(contasAlteracao.getStatus().getCodigo()));

			contasAlteracao.setValorTransiente(contasAlteracao.getValor().toString());
			contasAlteracao.setValorPagoTransiente(contasAlteracao.getValorPago().toString());

			model.addAttribute(MODEL_ATTR_ENTIDADE, contasAlteracao);
			model.addAttribute("tipos", TipoContaEnum.values());
			model.addAttribute("status", StatusPagamentoEnum.values());
			model.addAttribute("credenciados", credenciadoService.listar());
			model.addAttribute("representantes", representanteService.listar());

			return "contas_receber_alterar";
		} else {
			return "redirect:../contas_receber";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute ContasReceber contasReceber, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		String vazio = "";

		ModelAndView modelAndView = new ModelAndView("contas_receber_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, contasReceber);
		modelAndView.addObject("tipos", TipoContaEnum.values());
		modelAndView.addObject("status", StatusPagamentoEnum.values());
		modelAndView.addObject("credenciados", credenciadoService.listar());
		modelAndView.addObject("representantes", representanteService.listar());

		if (contasReceber.getCodigoTipoContaTransiente() != null
				&& contasReceber.getCodigoTipoContaTransiente() != vazio) {

			contasReceber.setTipoContaEnum(
					TipoContaEnum.getEnumPorCodigo(Integer.parseInt(contasReceber.getCodigoTipoContaTransiente())));
		}

		if (contasReceber.getCodigoTipoStatusTransiente() != null
				&& contasReceber.getCodigoTipoStatusTransiente() != vazio) {

			contasReceber.setStatus(StatusPagamentoEnum
					.getEnumPorCodigo(Integer.parseInt(contasReceber.getCodigoTipoStatusTransiente())));
		}

		if (contasReceber.getValorTransiente() != null && !contasReceber.getValorTransiente().isEmpty()) {
			contasReceber
					.setValor(new BigDecimal(contasReceber.getValorTransiente().replace(".", "").replace(",", ".")));
		}

		if (contasReceber.getValorPagoTransiente() != null && !contasReceber.getValorPagoTransiente().isEmpty()) {
			contasReceber.setValorPago(
					new BigDecimal(contasReceber.getValorPagoTransiente().replace(".", "").replace(",", ".")));
		}

		ContasReceberValidator validator = new ContasReceberValidator();
		validator.validate(contasReceber, result);

		if (result.hasErrors()) {

			ModelAndView mav = new ModelAndView("contas_receber_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, contasReceber);
			return mav;

		} else {

			contasReceberService.alterar(contasReceber);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/contas_receber");
			return retorno;
		}
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {

		ContasReceber contasVisualizar = contasReceberService.buscar(pk);
		Credenciado credenciado = credenciadoService.buscar(contasVisualizar.getCredenciado().getId());
		Representante representante = representanteService.buscar(contasVisualizar.getRepresentante().getId());

		contasVisualizar.setCredenciado(credenciado);
		contasVisualizar.setRepresentante(representante);

		if (contasVisualizar != null) {

			model.addAttribute(MODEL_ATTR_ENTIDADE, contasVisualizar);
			model.addAttribute("tipos", TipoContaEnum.values());
			model.addAttribute("status", StatusPagamentoEnum.values());
			model.addAttribute("credenciados", credenciadoService.listar());
			model.addAttribute("representantes", representanteService.listar());

			return "contas_receber_visualizar";
		} else {
			return "redirect:../contas_receber";
		}
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute ContasReceber contasReceber, Model model,
			final RedirectAttributes redirectAttributes) {

		try {
			contasReceberService.excluir(contasReceber);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem(""));
		}

		return "redirect:/contas_receber";
	}

	@ResponseBody
	@RequestMapping(value = "/contas/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("contas_receber", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new ContasReceber());
		return new ModelAndView("contas_receber.lista");
	}

}
