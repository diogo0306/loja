package br.com.eclinic.controler.credenciado;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;

import br.com.eclinic.entity.credenciado.Credenciado;

import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.enuns.TipoPessoaEnum;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.especialidade.EspecialidadeService;


@Controller
public class CredenciadoController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "credenciado";
	private static final String URL_REQUEST_LISTA = "/credenciados";
	private static final String URL_REQUEST_SALVAR = "/credenciado/salvar";
	private static final String URL_REQUEST_ALTERAR = "/credenciado/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/credenciado/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/credenciado/excluir";

	@Autowired
	private CredenciadoService service;

	@Autowired
	private EspecialidadeService especialidadeService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String credenciadosPesquisar(@ModelAttribute Credenciado credenciado, Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		return listar(credenciado.getNome(), locale, model, request);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String credenciados(Locale locale, Model model, HttpServletRequest request) throws Exception {
		return listar(null, locale, model, request);
	}

	private String listar(String nome, Locale locale, Model model, HttpServletRequest request) {
		String vazio = "";
		List<Credenciado> credenciados = new ArrayList<Credenciado>();
		if (nome != null && nome != vazio) {
			Credenciado credenciadoConsulta = new Credenciado();
			credenciadoConsulta.setNome(nome);
			credenciados = service.Consultar(credenciadoConsulta);
		} else {
			credenciados = service.listar();
		}

		configurarPaginacao(credenciados, model);
		Credenciado credenciado = new Credenciado();
		credenciado.setNome(nome);
		model.addAttribute(MODEL_ATTR_ENTIDADE, credenciado);
		model.addAttribute("credenciados", getPagedListHolder().getPageList());

		return "credenciados";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		model.addAttribute(MODEL_ATTR_ENTIDADE, new Credenciado());
		model.addAttribute("tipoPessoas", TipoPessoaEnum.values());
		model.addAttribute("situacao", SituacaoEnum.values());
		model.addAttribute("especialidades", especialidadeService.listar());

		return "credenciado_incluir";

	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute Credenciado credenciado, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		String vazio = "";

		if (credenciado.getValorCobradoTransiente().equalsIgnoreCase("0,00")) {
			credenciado.setValorCobradoTransiente(null);
		}

		ModelAndView modelAndView = new ModelAndView("credenciado_incluir");
		modelAndView.addObject(MODEL_ATTR_ENTIDADE, credenciado);
		modelAndView.addObject("tipoPessoas", TipoPessoaEnum.values());
		modelAndView.addObject("situacao", SituacaoEnum.values());

		if (credenciado.getCodigoTipoPessoaTransiente() != null
				&& credenciado.getCodigoTipoPessoaTransiente() != vazio) {
			credenciado.setTipoPessoa(
					TipoPessoaEnum.getEnumPorCodigo(Integer.parseInt(credenciado.getCodigoTipoPessoaTransiente())));
		}

		if (credenciado.getCodigoSituacaoTransiente() != null && credenciado.getCodigoSituacaoTransiente() != vazio) {
			credenciado.setSituacao(
					SituacaoEnum.getEnumPorCodigo(Integer.parseInt(credenciado.getCodigoSituacaoTransiente())));
		}

		if (credenciado.getValorCobradoTransiente() != null && !credenciado.getValorCobradoTransiente().isEmpty()) {
			credenciado.setValorCobrado(
					new BigDecimal(credenciado.getValorCobradoTransiente().replace(".", "").replace(",", ".")));
		}

		if (credenciado.getValorPagoTransiente() != null && !credenciado.getValorPagoTransiente().isEmpty()) {
			credenciado.setValorPago(
					new BigDecimal(credenciado.getValorPagoTransiente().replace(".", "").replace(",", ".")));
		}

		service.salvar(credenciado);

		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		ModelAndView retorno = new ModelAndView("redirect:/credenciados");
		return retorno;

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model, HttpServletRequest request) throws Exception {

		Credenciado credenciadoAlteracao = service.buscar(pk);

		if (credenciadoAlteracao != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, credenciadoAlteracao);
			model.addAttribute("tipoPessoas", TipoPessoaEnum.values());
			model.addAttribute("situacao", SituacaoEnum.values());

			return "credenciado_alterar";
		} else {
			return "redirect:../credenciados";
		}
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public String alterar(@Valid Credenciado credenciado, Errors errors, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		if (errors.hasErrors()) {
			model.addAttribute("tipoPessoas", TipoPessoaEnum.values());
			model.addAttribute("situacao", SituacaoEnum.values());
			model.addAttribute(MESSAGE_ERROR, getMensagem("informar.campo"));
			return "credenciado_alterar";
		} else {

			service.alterar(credenciado);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			return "redirect:/credenciados";
		}
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model, HttpServletRequest request) throws Exception {
		Credenciado credenciadoVisualizar = service.buscar(pk);
		if (credenciadoVisualizar != null) {
			model.addAttribute(MODEL_ATTR_ENTIDADE, credenciadoVisualizar);
			model.addAttribute("tipoPessoas", TipoPessoaEnum.values());
			model.addAttribute("situacao", SituacaoEnum.values());

			return "credenciado_visualizar";
		} else {
			return "redirect:../credenciados";
		}
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Credenciado credenciado, Model model,
			final RedirectAttributes redirectAttributes) {

		try {
			service.excluir(credenciado);
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem("credenciado.dependencia.com.consulta"));
		}

		return "redirect:../credenciados";
	}

	@ResponseBody
	@RequestMapping(value = "/credenciado/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("credenciados", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Credenciado());
		return new ModelAndView("credenciado.lista");
	}
}
