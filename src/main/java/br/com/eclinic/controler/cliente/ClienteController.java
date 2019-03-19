package br.com.eclinic.controler.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.endereco.Cidade;
import br.com.eclinic.entity.endereco.Estado;
import br.com.eclinic.entity.endereco.Pais;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.cliente.ClienteService;
import br.com.eclinic.service.exame.TabelaService;
import br.com.eclinic.util.JsonEndereco;
import br.com.eclinic.validator.ClienteValidator;
import freemarker.core.ParseException;

@Controller
public class ClienteController extends EclinicController {

	private static final String MODEL_ATTR_ENTIDADE = "cliente";
	private static final String URL_REQUEST_LISTA = "/clientes";
	private static final String URL_REQUEST_SALVAR = "/cliente/salvar";
	private static final String URL_REQUEST_ALTERAR = "/cliente/alterar";
	private static final String URL_REQUEST_VISUALIZAR = "/cliente/visualizar";
	private static final String URL_REQUEST_EXCLUIR = "/cliente/excluir";

	@Autowired
	private ClienteService service;
	@Autowired
	private TabelaService tabelaService;

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.POST)
	public String clientesPesquisar(@ModelAttribute Cliente cliente, Locale locale, Model model) throws Exception {
		return listar(cliente.getNome(), locale, model);
	}

	@RequestMapping(value = URL_REQUEST_LISTA, method = RequestMethod.GET)
	public String clientes(Locale locale, Model model) throws Exception {

		return listar(null, locale, model);
	}

	private String listar(String nome, Locale locale, Model model) {

		List<Cliente> clientes = new ArrayList<Cliente>();

		if (nome != null) {
			clientes = service.consultarPorDescricao(nome);
		} else {
			clientes = service.listar();
		}

		configurarPaginacao(clientes, model);
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		model.addAttribute(MODEL_ATTR_ENTIDADE, cliente);
		model.addAttribute("clientes", getPagedListHolder().getPageList());

		return "clientes";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.GET)
	public String exibirSalvar(Model model) throws Exception {

		String falso = "false";
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Cliente());
		List<String> paises = new ArrayList<String>();
		List<Pais> paisesBanco = service.consultarPaises();
		for (Pais pais : paisesBanco) {
			paises.add(pais.getNomePais());
		}

		model.addAttribute("tabela", tabelaService.listar());
		model.addAttribute("paises", paises);
		model.addAttribute("alterar", falso);

		return "cliente_incluir";
	}

	@RequestMapping(value = URL_REQUEST_SALVAR, method = RequestMethod.POST)
	public ModelAndView salvar(Model model, @ModelAttribute Cliente cliente, BindingResult result,
			final RedirectAttributes redirectAttributes) throws Exception {

		String falso = "false";
		ClienteValidator validator = new ClienteValidator();
		validator.validate(cliente, result);

		ModelAndView mav = new ModelAndView("cliente_incluir");
		configurarModelEnderecoMav(mav, cliente);
		mav.addObject(MODEL_ATTR_ENTIDADE, cliente);
		mav.addObject("alterar", falso);
		mav.addObject("tabela", tabelaService.listar());

		if (cliente.getCnpj().length() < 11) {
			return mav;
		}

		if (result.hasErrors()) {
			return mav;
		} else {
			List<Cliente> clientesBanco = service.consultarPorDescricao(cliente.getNome());

			if (clientesBanco.size() != 0) {
				mav.addObject(MESSAGE_ERROR, getMensagem("cliente.existente"));
				return mav;
			} else {

				if (service.consultarCnpj(cliente) != null) {
					mav.addObject(MESSAGE_ERROR, getMensagem("empresa.inclusao.erro.existe"));
					return mav;
				}

				service.salvar(cliente);
				redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
				ModelAndView retorno = new ModelAndView("redirect:/clientes");
				return retorno;
			}
		}

	}

	@RequestMapping(value = URL_REQUEST_ALTERAR + "/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model) throws Exception {
		Cliente clienteAlteracao = service.buscar(pk);
		if (clienteAlteracao != null) {

			// configurarModelEndereco(model, clienteAlteracao);

			String tru = "true";
			model.addAttribute(MODEL_ATTR_ENTIDADE, clienteAlteracao);
			model.addAttribute("tabela", tabelaService.listar());
			model.addAttribute("alterar", tru);
			model.addAttribute("alterarTela", "");
			return "cliente_alterar";
		} else {
			return "redirect:../clientes";
		}
	}

	@RequestMapping(value = URL_REQUEST_VISUALIZAR + "/{pk}", method = RequestMethod.GET)
	public String exibirVisualizar(@PathVariable Long pk, Model model) throws Exception {
		Cliente clienteAlteracao = service.buscar(pk);
		if (clienteAlteracao != null) {

			// configurarModelEndereco(model, clienteAlteracao);

			model.addAttribute(MODEL_ATTR_ENTIDADE, clienteAlteracao);
			return "cliente_visualizar";
		} else {
			return "redirect:../clientes";
		}
	}

	@SuppressWarnings("unused")
	private void configurarModelEndereco(Model model, Cliente clienteAlteracao) {
		// FIXME RHAS criar consulta para retornar pais pela string.
//		Pais paisFake = new Pais();
//		paisFake.setId(1);
//		paisFake.setNomePais("Brasil");
//		paisFake.setSiglaPais("BR");
//
//		List<Estado> estadosBanco = service.consultarEstadosPorPais(paisFake);
//		List<Cidade> cidadesBanco = service
//				.consultarCidadesPorEstado(clienteAlteracao.getEndereco()
//						.getEstado().getSiglaEstado());
//		List<String> paisesBanco = new ArrayList<String>();
//		paisesBanco.add("Brasil");
//
//		model.addAttribute("paises", paisesBanco);
//		model.addAttribute("estados", estadosBanco);
//		model.addAttribute("cidades", cidadesBanco);
	}

	private void configurarModelEnderecoMav(ModelAndView model, Cliente clienteAlteracao) {
		// FIXME RHAS criar consulta para retornar pais pela string.
//		Pais paisFake = new Pais();
//		paisFake.setId(1);
//		paisFake.setNomePais("Brasil");
//		paisFake.setSiglaPais("BR");
//
//		List<Estado> estadosBanco = service.consultarEstadosPorPais(paisFake);
//		List<Cidade> cidadesBanco = service
//				.consultarCidadesPorEstado(clienteAlteracao.getEndereco()
//						.getEstado().getSiglaEstado());
//		List<String> paisesBanco = new ArrayList<String>();
//		paisesBanco.add("Brasil");
//
//		model.addObject("paises", paisesBanco);
//		model.addObject("estados", estadosBanco);
//		model.addObject("cidades", cidadesBanco);
	}

	@RequestMapping(value = URL_REQUEST_ALTERAR, method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute Cliente cliente, Model model, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		// Estado estado = service.buscarEstadoPorNome(cliente.getEndereco()
		// .getEstadoTransiente());
		// Cidade cidade = service.buscarCidadePorNome(cliente.getEndereco()
		// .getCidadeTransiente(), estado.getId());
		// Pais pais = service.buscarPaisPorNome(cliente.getEndereco()
		// .getPaisTransiente());
		//
		// cliente.getEndereco().setCidade(cidade);
		// cliente.getEndereco().setEstado(estado);
		// cliente.getEndereco().setPais(pais);

		String tru = "true";
		ClienteValidator validator = new ClienteValidator();
		validator.validate(cliente, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("cliente_alterar");
			mav.addObject(MODEL_ATTR_ENTIDADE, cliente);
			configurarModelEnderecoMav(mav, cliente);
			mav.addObject("alterar", tru);
			return mav;
		} else {

			List<Cliente> clientesBanco = service.consultarPorDescricao(cliente.getNome());

			if (clientesBanco.size() != 0 && clientesBanco.get(0).getId() != cliente.getId()) {
				ModelAndView mav = new ModelAndView("cliente_alterar");
				mav.addObject(MESSAGE_ERROR, getMensagem("cliente.existente"));
				mav.addObject(MODEL_ATTR_ENTIDADE, cliente);
				configurarModelEnderecoMav(mav, cliente);
				return mav;
			} else {
				service.alterar(cliente);
				redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
				ModelAndView retorno = new ModelAndView("redirect:/clientes");
				return retorno;
			}

		}
	}

	@RequestMapping(value = URL_REQUEST_EXCLUIR, method = RequestMethod.POST)
	public String excluir(@ModelAttribute Cliente cliente, Model model, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
		try {
			if (usuarioLogado.getCliente().getId() == cliente.getId()) {
				redirectAttributes.addFlashAttribute(MESSAGE_ERROR, getMensagem("exclusao.cliente.corrente"));
			} else {
				service.excluir(cliente);
				redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("exclusao.sucesso"));
			}

		} catch (NegocioException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(MESSAGE_ERROR, e.getMessage());
		}

		return "redirect:/clientes";
	}

	@ResponseBody
	@RequestMapping(value = "/cliente/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {
		navegarPaginacao(acao, model);
		model.addAttribute("clientes", getPagedListHolder().getPageList());
		model.addAttribute(MODEL_ATTR_ENTIDADE, new Cliente());
		return new ModelAndView("cliente.lista");
	}

	@ResponseBody
	@RequestMapping(value = "/cliente/listar-cidades/{siglaEstado}", method = RequestMethod.GET)
	public List<Cidade> listarCidadesAlterar(@PathVariable String siglaEstado, Model model) {
		List<Cidade> cidadesBanco = service.consultarCidadesPorEstado(siglaEstado);
		return cidadesBanco;
	}

	@ResponseBody
	@RequestMapping(value = "/cliente/listar-estados/{pais}", method = RequestMethod.GET)
	public List<Estado> listarEstadosAlterar(@PathVariable String pais, Model model) {

		// FIXME RHAS criar consulta para retornar pais pela string.
		Pais paisFake = new Pais();
		paisFake.setId(1);
		paisFake.setNomePais("Brasil");
		paisFake.setSiglaPais("BR");

		List<Estado> estadosBanco = service.consultarEstadosPorPais(paisFake);

		return estadosBanco;
	}

	@ResponseBody
	@RequestMapping(value = "cliente/consultar-endereco/{cep}", method = RequestMethod.GET)
	public JsonEndereco consultarEndereco(@PathVariable String cep, Model model) throws ParseException {
		JsonEndereco jsonEndereco = service.consultarEnderecoPorCep(cep.replace("-", ""));
		if (jsonEndereco.getCidade() != null && jsonEndereco.getCidade().getNomeCidade() != null
				&& jsonEndereco.getCidade().getNomeCidade() != "") {

			Estado estado = jsonEndereco.getEstado();

			// FIXME RHAS criar consulta para retornar pais pela string.
			Pais paisFake = new Pais();
			paisFake.setId(1);
			paisFake.setNomePais("Brasil");
			paisFake.setSiglaPais("BR");

			List<Estado> estadosBanco = service.consultarEstadosPorPais(paisFake);
			List<Cidade> cidadesBanco = service.consultarCidadesPorEstado(estado.getSiglaEstado());
			List<String> paisesBanco = new ArrayList<String>();
			paisesBanco.add("Brasil");

			jsonEndereco.setListaCidades(cidadesBanco);
			jsonEndereco.setListaEstados(estadosBanco);

		}

		return jsonEndereco;

	}

	@ResponseBody
	@RequestMapping(value = "/cliente/alterar/consultar-endereco/{cep}", method = RequestMethod.GET)
	public JsonEndereco consultarEnderecoAlterar(@PathVariable String cep, Model model) throws ParseException {
		JsonEndereco jsonEndereco = service.consultarEnderecoPorCep(cep.replace("-", ""));
		if (jsonEndereco.getCidade() != null && jsonEndereco.getCidade().getNomeCidade() != null
				&& jsonEndereco.getCidade().getNomeCidade() != "") {

			Estado estado = jsonEndereco.getEstado();

			// FIXME RHAS criar consulta para retornar pais pela string.
			Pais paisFake = new Pais();
			paisFake.setId(1);
			paisFake.setNomePais("Brasil");
			paisFake.setSiglaPais("BR");

			List<Estado> estadosBanco = service.consultarEstadosPorPais(paisFake);
			List<Cidade> cidadesBanco = service.consultarCidadesPorEstado(estado.getSiglaEstado());
			List<String> paisesBanco = new ArrayList<String>();
			paisesBanco.add("Brasil");

			jsonEndereco.setListaCidades(cidadesBanco);
			jsonEndereco.setListaEstados(estadosBanco);

		}

		return jsonEndereco;

	}

}
