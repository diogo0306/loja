package br.com.eclinic.controler.solicitacao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.agenda.Agenda;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.contrato.DiaVencimentoEnum;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.enuns.TipoPessoaEnum;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.entity.medico.OrgaoEmissorEnum;
import br.com.eclinic.entity.medico.SexoEnum;
import br.com.eclinic.entity.medico.TipoContratoEnum;
import br.com.eclinic.entity.medico.TipoProfissionalEnum;
import br.com.eclinic.entity.medico.UfEnum;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.paciente.PacienteDTO;
import br.com.eclinic.entity.representante.Representante;
import br.com.eclinic.entity.representante.TipoRepresentanteEnum;
import br.com.eclinic.entity.solicitacao.Solicitacao;
import br.com.eclinic.entity.solicitacao.SolicitacaoDTO;
import br.com.eclinic.entity.supervisor.Responsavel;
import br.com.eclinic.entity.usuario.TipoUsuarioEnum;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.agenda.AgendaService;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.especialidade.EspecialidadeService;
import br.com.eclinic.service.paciente.PacienteService;
import br.com.eclinic.service.plano.PlanoService;
import br.com.eclinic.service.representante.RepresentanteService;
import br.com.eclinic.service.solicitacao.SolicitacaoService;
import br.com.eclinic.service.usuario.UsuarioService;
import br.com.eclinic.validator.CredenciadoValidator;
import br.com.eclinic.validator.MedicoValidator;
import br.com.eclinic.validator.PacienteValidator;
import br.com.eclinic.validator.RepresentanteValidator;

@Controller
public class SolicitacaoController extends EclinicController {

	@Autowired
	private SolicitacaoService solicitacaoService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private CredenciadoService credenciadoService;
	@Autowired
	private RepresentanteService representanteService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EspecialidadeService especialidadeService;
	@Autowired
	private AgendaService agendaService;
	@Autowired
	private PlanoService planoService;

	@RequestMapping(value = "/solicitacoes", method = RequestMethod.GET)
	public String solicitacao(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("solicitacao", new Solicitacao());
		model.addAttribute("solicitacoes", solicitacaoService.listar());
		return "solicitacoes";
	}

	@RequestMapping(value = "/solicitacoes", method = RequestMethod.POST)
	public String solicitacoesPesquisar(@ModelAttribute Solicitacao solicitacao, Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		model.addAttribute("solicitacoes", solicitacaoService.consultar(solicitacao));
		model.addAttribute("solicitacao", solicitacao);
		return "solicitacoes";
	}

	@RequestMapping(value = "/solicitacao/visualizar/{solicitacaoId}", method = RequestMethod.GET)
	public ModelAndView solicitacaoVisualizar(@PathVariable String solicitacaoId, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		Solicitacao solicitacao = solicitacaoService.buscar(new Long(solicitacaoId));
		SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();

		solicitacaoDTO.setSolicitacao(solicitacao);
		solicitacaoDTO.getSolicitacao().setMotivo(null);

		ModelAndView mav = new ModelAndView("solicitacao.paciente");
		mav.addObject("solicitacaoDTO", solicitacaoDTO);
		mav.addObject("sexos", SexoEnum.values());
		mav.addObject("orgaos", OrgaoEmissorEnum.values());
		mav.addObject("uf", UfEnum.values());

		if (solicitacao.getTipo().equalsIgnoreCase("PACIENTE")) {
			if (solicitacao.getStatus().equalsIgnoreCase("APROVADO")) {
				mav = new ModelAndView("solicitacao.paciente.visualizar");
				Paciente paciente = pacienteService.buscar(solicitacao.getPaciente().getId());
				solicitacaoDTO.setPaciente(paciente);
				mav.addObject("solicitacaoDTO", solicitacaoDTO);
				return mav;
			}
			if (solicitacao.getStatus().equalsIgnoreCase("REPROVADO")) {
				mav = new ModelAndView("solicitacao.paciente.reprovado");
				mav.addObject("solicitacaoDTO", solicitacaoDTO);
			}
		}

		if (solicitacao.getTipo().equalsIgnoreCase("MEDICO")) {
			if (solicitacao.getStatus().equalsIgnoreCase("APROVADO")) {
				mav = new ModelAndView("solicitacao.medico.visualizar");
				Credenciado credenciado = credenciadoService.buscar(solicitacao.getCredenciado().getId());
				solicitacaoDTO.setCredenciado(credenciado);
				mav.addObject("solicitacaoDTO", solicitacaoDTO);
				mav.addObject("tipoPessoas", TipoPessoaEnum.values());
				mav.addObject("situacao", SituacaoEnum.values());
				mav.addObject("especialidades", especialidadeService.listar());
				return mav;
			} else if (solicitacao.getStatus().equalsIgnoreCase("REPROVADO")) {
				mav = new ModelAndView("solicitacao.medico.reprovado");
				mav.addObject("solicitacaoDTO", solicitacaoDTO);
			} else {
				mav = new ModelAndView("solicitacao.medico");
				mav.addObject("solicitacaoDTO", solicitacaoDTO);
				mav.addObject("tipoContrato", TipoContratoEnum.values());
				mav.addObject("tipoPessoas", TipoPessoaEnum.values());
				mav.addObject("situacao", SituacaoEnum.values());
				mav.addObject("uf", UfEnum.values());
				mav.addObject("sexos", SexoEnum.values());
				mav.addObject("especialidades", especialidadeService.listar());
				return mav;
			}
		}

		if (solicitacao.getTipo().equalsIgnoreCase("REPRESENTANTE")) {
			if (solicitacao.getStatus().equalsIgnoreCase("APROVADO")) {
				mav = new ModelAndView("solicitacao.representante.visualizar");
				Representante representante = representanteService.buscar(solicitacao.getRepresentante().getId());
				solicitacaoDTO.setRepresentante(representante);
				mav.addObject("solicitacaoDTO", solicitacaoDTO);
				return mav;
			} else if (solicitacao.getStatus().equalsIgnoreCase("REPROVADO")) {
				mav = new ModelAndView("solicitacao.representante.reprovado");
				mav.addObject("solicitacaoDTO", solicitacaoDTO);
			} else {
				mav = new ModelAndView("solicitacao.representante");
				mav.addObject("solicitacaoDTO", solicitacaoDTO);
				mav.addObject("tipos", TipoRepresentanteEnum.values());
				mav.addObject("sexos", SexoEnum.values());
				mav.addObject("uf", UfEnum.values());
				return mav;
			}
		}

		return mav;
	}

	@RequestMapping(value = "/solicitacao/salvar_representante", method = RequestMethod.POST)
	public ModelAndView salvarSolicitacaoRepresentante(@ModelAttribute SolicitacaoDTO solicitacaoDTO,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException, NegocioException {

		String vazio = "";

		if (solicitacaoDTO.isReprovado() != null && solicitacaoDTO.isReprovado() == true) {

			Solicitacao solicitacao = solicitacaoService.buscar(solicitacaoDTO.getSolicitacao().getId());
			solicitacao.setStatus("REPROVADO");

			if (solicitacaoDTO.getSolicitacao().getMotivo().equalsIgnoreCase("")
					|| solicitacaoDTO.getSolicitacao().getMotivo() == null) {
				solicitacao.setMotivo("NÃO INFORMADO");
			} else {
				solicitacao.setMotivo(solicitacaoDTO.getSolicitacao().getMotivo());
			}

			solicitacaoService.alterar(solicitacao);

			ModelAndView retorno = new ModelAndView("redirect:/solicitacoes");
			return retorno;
		} else {

			ModelAndView mav = new ModelAndView("solicitacao.representante");
			mav.addObject("solitacaoDTO", solicitacaoDTO);
			mav.addObject("uf", UfEnum.values());
			mav.addObject("tipos", TipoRepresentanteEnum.values());
			mav.addObject("sexos", SexoEnum.values());

			if (solicitacaoDTO.isAprovado() != null && solicitacaoDTO.isAprovado() == true) {

				if (solicitacaoDTO.getRepresentante().getDataNascimentoFormatada() != null
						&& solicitacaoDTO.getRepresentante().getDataNascimentoFormatada() != vazio) {
					configurarDataRepresentante(solicitacaoDTO);
				}

				Representante representante = new Representante();
				representante = solicitacaoDTO.getRepresentante();
				representante.setDataNascimento(solicitacaoDTO.getData());

				if (solicitacaoDTO.getRepresentante().getCodigoRepresentanteTransiente() != null
						&& solicitacaoDTO.getRepresentante().getCodigoRepresentanteTransiente() != vazio) {
					representante.setTipoRepresentanteEnum(TipoRepresentanteEnum.getEnumPorCodigo(
							Integer.parseInt(solicitacaoDTO.getRepresentante().getCodigoRepresentanteTransiente())));
				}

				if (solicitacaoDTO.getRepresentante().getCodigoSexoTransiente() != null
						&& solicitacaoDTO.getRepresentante().getCodigoSexoTransiente() != vazio) {
					representante.setSexoEnum(SexoEnum.getEnumPorCodigo(
							Integer.parseInt(solicitacaoDTO.getRepresentante().getCodigoSexoTransiente())));
				}

				if (solicitacaoDTO.getRepresentante().getCodigoUfTransiente() != null
						&& solicitacaoDTO.getRepresentante().getCodigoUfTransiente() != vazio) {
					representante.setUfEnum(UfEnum.getEnumPorCodigo(
							Integer.parseInt(solicitacaoDTO.getRepresentante().getCodigoUfTransiente())));
				}

				representante.setSituacaoEnum(SituacaoEnum.ATIVO);

				RepresentanteValidator validator = new RepresentanteValidator();
				validator.validate(representante, result);

				if (result.hasErrors()) {
					mav.addObject(MESSAGE_ERROR, getMensagem("informar.campo"));
					return mav;
				} else {
					representanteService.salvar(representante);
					Solicitacao solicitacao = solicitacaoService.buscar(solicitacaoDTO.getSolicitacao().getId());
					solicitacao.setStatus("APROVADO");
					solicitacao.setRepresentante(representante);
					if (representante.getId() != null && representante.getId() != 0) {
						Usuario usuario = new Usuario();
						usuario.setNome(representante.getNome());
						usuario.setLogin(solicitacao.getCpf().replace(".", "").replace("-", ""));
						usuario.setSenha(solicitacao.getSenha());
						usuario.setCpf(solicitacao.getCpf().replace(".", "").replace("-", ""));
						usuario.setTipoUsuario(TipoUsuarioEnum.REPRESENTANTE);
						usuario.setRepresentante(representante);
						usuarioService.salvar(usuario);
					}
					solicitacaoService.alterar(solicitacao);
				}
			}

			ModelAndView retorno = new ModelAndView("redirect:/solicitacoes");
			return retorno;
		}
	}

	@RequestMapping(value = "/solicitacao/beneficiario/aprovar", method = RequestMethod.POST)
	public ModelAndView aprovarSolicitacaoPaciente(@ModelAttribute SolicitacaoDTO solicitacaoDTO, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException, NegocioException {

		if (solicitacaoDTO.isReprovado() != null && solicitacaoDTO.isReprovado() == true) {
			Solicitacao solicitacao = solicitacaoService.buscar(solicitacaoDTO.getSolicitacao().getId());
			solicitacao.setStatus("REPROVADO");

			if (solicitacaoDTO.getSolicitacao().getMotivo().equalsIgnoreCase("")
					|| solicitacaoDTO.getSolicitacao().getMotivo() == null) {
				solicitacao.setMotivo("NÃO INFORMADO");
			} else {
				solicitacao.setMotivo(solicitacaoDTO.getSolicitacao().getMotivo());
			}

			solicitacaoService.alterar(solicitacao);

			ModelAndView modelAndView = new ModelAndView("redirect:/solicitacoes");
			return modelAndView;
		} else {
			Solicitacao solicitacao = solicitacaoService.buscar(solicitacaoDTO.getSolicitacao().getId());

			PacienteDTO pacienteDTO = new PacienteDTO();
			pacienteDTO.setIdSolicitacao(solicitacao.getId());
			pacienteDTO.setPaciente(null);
			pacienteDTO.setContrato(null);

			ModelAndView modelAndView = new ModelAndView("beneficiario.gestao");
			modelAndView.addObject("pacienteDTO", pacienteDTO);
			modelAndView.addObject("sexos", SexoEnum.values());
			modelAndView.addObject("uf", UfEnum.values());
			modelAndView.addObject("orgaos", OrgaoEmissorEnum.values());
			modelAndView.addObject("diaVencimento", DiaVencimentoEnum.values());
			modelAndView.addObject("planos", planoService.listar());
			return modelAndView;
		}
	}

	@RequestMapping(value = "/solicitacao/salvar_paciente", method = RequestMethod.POST)
	public ModelAndView salvarSolicitacaoPaciente(@ModelAttribute SolicitacaoDTO solicitacaoDTO, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException, NegocioException {

		String vazio = "";

		if (solicitacaoDTO.getPaciente().getCodigoSexoTransiente().equalsIgnoreCase("")) {
			solicitacaoDTO.getPaciente().setCodigoSexoTransiente(null);
		}
		if (solicitacaoDTO.getPaciente().getDataNascimentoFormatada().equalsIgnoreCase("")) {
			solicitacaoDTO.getPaciente().setDataNascimentoFormatada(null);
		}

		if (solicitacaoDTO.isReprovado() != null && solicitacaoDTO.isReprovado() == true) {
			Solicitacao solicitacao = solicitacaoService.buscar(solicitacaoDTO.getSolicitacao().getId());
			solicitacao.setStatus("REPROVADO");

			if (solicitacaoDTO.getSolicitacao().getMotivo().equalsIgnoreCase("")
					|| solicitacaoDTO.getSolicitacao().getMotivo() == null) {
				solicitacao.setMotivo("NÃO INFORMADO");
			} else {
				solicitacao.setMotivo(solicitacaoDTO.getSolicitacao().getMotivo());
			}

			solicitacaoService.alterar(solicitacao);

			ModelAndView modelAndView = new ModelAndView("redirect:/solicitacoes");
			return modelAndView;
		} else {

			ModelAndView modelAndView = new ModelAndView("solicitacao.paciente");
			modelAndView.addObject("solicitacaoDTO", solicitacaoDTO);
			modelAndView.addObject("sexos", SexoEnum.values());

			if (solicitacaoDTO.isAprovado() != null && solicitacaoDTO.isAprovado() == true) {

				if (solicitacaoDTO.getPaciente().getDataNascimentoFormatada() != null) {
					configurarDataPaciente(solicitacaoDTO);
				}

				if (solicitacaoDTO.getListaDependente() != null) {
					for (Dependente dependente : solicitacaoDTO.getListaDependente()) {
						configurarDataDependente(dependente);
					}
				}

				Paciente paciente = new Paciente();
				paciente = solicitacaoDTO.getPaciente();
				paciente.setDataNascimento(solicitacaoDTO.getDataPaciente());

				if (solicitacaoDTO.getPaciente().getCodigoSexoTransiente() != null) {
					paciente.setSexoEnum(SexoEnum.getEnumPorCodigo(
							Integer.parseInt(solicitacaoDTO.getPaciente().getCodigoSexoTransiente())));
				}

				if (solicitacaoDTO.getPaciente().getEndereco().getEstadoTransiente() != null) {
					paciente.getEndereco().setEstado(UfEnum.getEnumPorCodigo(
							Integer.parseInt(solicitacaoDTO.getPaciente().getEndereco().getEstadoTransiente())));
				}

				if (solicitacaoDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != null
						&& solicitacaoDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != vazio) {
					paciente.getDocumentacao().setOrgaoEmissorEnum(OrgaoEmissorEnum.getEnumPorCodigo(Integer
							.parseInt(solicitacaoDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente())));
				}

				if (pacienteService.consultarCpf(paciente) != null) {
					modelAndView.addObject(MESSAGE, getMensagem("paciente.inclusao.erro.existe"));
					return modelAndView;
				}

				PacienteValidator validator = new PacienteValidator();
				validator.validate(paciente, result);

				if (result.hasErrors()) {
					modelAndView.addObject(MESSAGE_ERROR, getMensagem("informar.campo"));
					return modelAndView;
				} else {
					Usuario usuarioLogado = HomeController.getUsuarioLogado(request);
					Cliente cliente = usuarioLogado.getCliente();
					paciente.setCliente(cliente);
					pacienteService.salvar(paciente);
					Solicitacao solicitacao = solicitacaoService.buscar(solicitacaoDTO.getSolicitacao().getId());
					solicitacao.setStatus("APROVADO");
					solicitacao.setPaciente(paciente);
					if (paciente.getId() != null && paciente.getId() != 0) {
						Usuario usuario = new Usuario();
						usuario.setNome(paciente.getNome());
						usuario.setLogin(solicitacao.getCpf().replace(".", "").replace("-", ""));
						usuario.setSenha(solicitacao.getSenha());
						usuario.setCpf(solicitacao.getCpf().replace(".", "").replace("-", ""));
						usuario.setTipoUsuario(TipoUsuarioEnum.PACIENTE);
						usuario.setPaciente(paciente);
						usuarioService.salvar(usuario);
					}
					solicitacaoService.alterar(solicitacao);
				}
			}

			ModelAndView retorno = new ModelAndView("redirect:/solicitacoes");
			return retorno;
		}
	}

	@RequestMapping(value = "/solicitacao/salvar_medico", method = RequestMethod.POST)
	public ModelAndView salvarSolicitacaoMedico(@ModelAttribute SolicitacaoDTO solicitacaoDTO, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException, NegocioException {

		String vazio = "";
		
		if (solicitacaoDTO.getCredenciado().getValorCobradoTransiente().equalsIgnoreCase("0,00")) {
			solicitacaoDTO.getCredenciado().setValorCobradoTransiente(null);
		}

		if (solicitacaoDTO.isReprovado() != null && solicitacaoDTO.isReprovado() == true) {
			Solicitacao solicitacao = solicitacaoService.buscar(solicitacaoDTO.getSolicitacao().getId());
			solicitacao.setStatus("REPROVADO");

			if (solicitacaoDTO.getSolicitacao().getMotivo().equalsIgnoreCase("")
					|| solicitacaoDTO.getSolicitacao().getMotivo() == null) {
				solicitacao.setMotivo("NÃO INFORMADO");
			} else {
				solicitacao.setMotivo(solicitacaoDTO.getSolicitacao().getMotivo());
			}

			solicitacaoService.alterar(solicitacao);

			ModelAndView modelAndView = new ModelAndView("redirect:/solicitacoes");
			return modelAndView;
		} else {

			ModelAndView modelAndView = new ModelAndView("solicitacao.medico");
			modelAndView.addObject("solicitacaoDTO", solicitacaoDTO);

			modelAndView.addObject("tipoPessoas", TipoPessoaEnum.values());

			modelAndView.addObject("situacao", SituacaoEnum.values());

			modelAndView.addObject("especialidades", especialidadeService.listar());

			if (solicitacaoDTO.isAprovado() != null && solicitacaoDTO.isAprovado() == true) {

				Credenciado credenciado = new Credenciado();
				credenciado = solicitacaoDTO.getCredenciado();

				if (solicitacaoDTO.getDataContratacaoFormatada() != null
						&& solicitacaoDTO.getDataContratacaoFormatada() != vazio) {
					configurarDataContratacaoMedico(solicitacaoDTO);
				}

				/*
				 * if (solicitacaoDTO.getDataNascimentoFormatada() != null &&
				 * solicitacaoDTO.getDataNascimentoFormatada() != vazio) {
				 * configurarDataNascimentoMedico(solicitacaoDTO); }
				 */

				// credenciado.setDataContratacao(solicitacaoDTO.getDataContratacao());

				/*
				 * if (solicitacaoDTO.getMedico().getTipoContratoTransiente() != null &&
				 * solicitacaoDTO.getMedico().getTipoContratoTransiente() != vazio) {
				 * credenciado.setTipoContratoEnum(TipoContratoEnum.getEnumPorCodigo(
				 * Integer.parseInt(solicitacaoDTO.getMedico().getTipoContratoTransiente()))); }
				 */

				if (solicitacaoDTO.getCredenciado().getCodigoTipoPessoaTransiente() != null
						&& solicitacaoDTO.getCredenciado().getCodigoTipoPessoaTransiente() != vazio) {
					credenciado.setTipoPessoa(TipoPessoaEnum.getEnumPorCodigo(
							Integer.parseInt(solicitacaoDTO.getCredenciado().getCodigoTipoPessoaTransiente())));
				}

				if (solicitacaoDTO.getCredenciado().getCodigoSituacaoTransiente() != null
						&& solicitacaoDTO.getCredenciado().getCodigoSituacaoTransiente() != vazio) {
					credenciado.setSituacaoEnum(SituacaoEnum.getEnumPorCodigo(
							Integer.parseInt(solicitacaoDTO.getCredenciado().getCodigoSituacaoTransiente())));
				}

				if (credenciadoService.consultarCpf(credenciado) != null) {
					modelAndView.addObject(MESSAGE_ERROR, getMensagem("profissional.inclusao.erro.existe"));
					return modelAndView;
				}

				CredenciadoValidator validator = new CredenciadoValidator();
				validator.validateSolicitacao(credenciado, result);

				if (result.hasErrors()) {
					modelAndView.addObject(MESSAGE_ERROR, getMensagem("informar.campo"));
					return modelAndView;
				} else {

					if (solicitacaoDTO.getEspecialidade().getId() != null
							&& solicitacaoDTO.getEspecialidade().getId() != 0) {
						Especialidade especialidade = especialidadeService
								.buscar(solicitacaoDTO.getEspecialidade().getId());
						credenciado.setEspecialidade(especialidade);
					}

					/*
					 * Agenda agenda = new Agenda(); agenda = solicitacaoDTO.getAgenda();
					 */

					/*
					 * if (solicitacaoDTO.getAgenda().getQuantidade() != null &&
					 * solicitacaoDTO.getAgenda().getQuantidade() != vazio &&
					 * solicitacaoDTO.getAgenda().getQuantidade() != zero) {
					 * agenda.setQuantidadeConsultas(Integer.parseInt(solicitacaoDTO.getAgenda().
					 * getQuantidade())); }
					 * 
					 * if (solicitacaoDTO.getAgenda().getDuracao() != null &&
					 * solicitacaoDTO.getAgenda().getDuracao() != vazio) {
					 * agenda.setDuracaoConsulta( new
					 * BigDecimal(solicitacaoDTO.getAgenda().getDuracao().replace(":", ".")));
					 * String hora = solicitacaoDTO.getAgenda().getDuracao().split(":")[0]; String
					 * minuto = solicitacaoDTO.getAgenda().getDuracao().split(":")[1]; int horai =
					 * Integer.parseInt(hora); int minutoi = Integer.parseInt(minuto); horai = horai
					 * * 60; int total = horai + minutoi; agenda.setDuracaoMinutos(total); }
					 */

					/*
					 * if (solicitacaoDTO.getValorFormatado() != null &&
					 * solicitacaoDTO.getValorFormatado() != vazio) { credenciado.setValorCobrado(
					 * new BigDecimal(solicitacaoDTO.getValorFormatado().replace(".",
					 * "").replace(",", "."))); }
					 */

					if (solicitacaoDTO.getCredenciado().getValorCobradoTransiente() != null
							&& solicitacaoDTO.getCredenciado().getValorCobradoTransiente() != vazio) {
						credenciado.setValorCobrado(new BigDecimal(solicitacaoDTO.getCredenciado()
								.getValorCobradoTransiente().replace(".", "").replace(",", ".")));
					}

					/*
					 * Jornada jornada = solicitacaoDTO.getJornada();
					 * 
					 * if (jornada.getInicio() != null && jornada.getInicio() != vazio) {
					 * GregorianCalendar horario = new GregorianCalendar(); String hora =
					 * jornada.getInicio().substring(0, 2); String minuto =
					 * jornada.getInicio().substring(3, 5); horario.set(Calendar.HOUR_OF_DAY,
					 * Integer.parseInt(hora)); horario.set(Calendar.MINUTE,
					 * Integer.parseInt(minuto)); horario.set(Calendar.SECOND, 0);
					 * jornada.setInicioJornada(horario.getTime()); }
					 * 
					 * if (jornada.getFim() != null && jornada.getFim() != vazio) {
					 * GregorianCalendar horario = new GregorianCalendar(); String hora =
					 * jornada.getInicio().substring(0, 2); String minuto =
					 * jornada.getInicio().substring(3, 5); horario.set(Calendar.HOUR_OF_DAY,
					 * Integer.parseInt(hora)); horario.set(Calendar.MINUTE,
					 * Integer.parseInt(minuto)); horario.set(Calendar.SECOND, 0);
					 * jornada.setFimJornada(horario.getTime()); }
					 */

					credenciado.setSituacaoEnum(SituacaoEnum.ATIVO);
					// credenciado.setSituacaoContrato("ATIVO");
					/* agendaService.salvar(agenda); */
					credenciadoService.salvar(credenciado);
					Solicitacao solicitacao = solicitacaoService.buscar(solicitacaoDTO.getSolicitacao().getId());
					solicitacao.setStatus("APROVADO");
					solicitacao.setCredenciado(credenciado);

					if (credenciado.getId() != null && credenciado.getId() != 0) {
						Usuario usuario = new Usuario();
						usuario.setNome(credenciado.getNome());
						usuario.setLogin(solicitacao.getCpf().replace(".", "").replace("-", ""));
						usuario.setSenha(solicitacao.getSenha());
						usuario.setCpf(solicitacao.getCpf().replace(".", "").replace("-", ""));
						usuario.setTipoUsuario(TipoUsuarioEnum.CREDENCIADO);
						usuario.setCredenciado(credenciado);
						usuarioService.salvar(usuario);
					}
					
					if(solicitacaoDTO.getCredenciado().getTipoPessoa().equals(TipoPessoaEnum.JURIDICA)) {
						credenciado.setCpf(null);
					}
					solicitacaoService.alterar(solicitacao);
				}
			}
			ModelAndView retorno = new ModelAndView("redirect:/solicitacoes");
			return retorno;
		}
	}

	@RequestMapping(value = "/solicitacao/acidionar_responsavel", method = RequestMethod.POST)
	public ModelAndView adicionarResponsavelMedico(@ModelAttribute SolicitacaoDTO solicitacaoDTO, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException {

		List<Responsavel> listaResponsaveis = solicitacaoDTO.getListaResponsavel();
		Responsavel responsavel = new Responsavel();

		if (solicitacaoDTO.getListaResponsavel() == null) {
			listaResponsaveis = new ArrayList<Responsavel>();
		}

		responsavel.setNome(solicitacaoDTO.getResponsavel().getNome());
		responsavel.setCpf(solicitacaoDTO.getResponsavel().getCpf());

		listaResponsaveis.add(responsavel);

		solicitacaoDTO.setListaResponsavel(listaResponsaveis);
		solicitacaoDTO.getResponsavel().setNome(null);
		solicitacaoDTO.getResponsavel().setCpf(null);

		ModelAndView modelAndView = new ModelAndView("solicitacao.medico");
		modelAndView.addObject("solicitacaoDTO", solicitacaoDTO);
		modelAndView.addObject("profissionais", TipoProfissionalEnum.values());
		modelAndView.addObject("tipoContrato", TipoContratoEnum.values());
		modelAndView.addObject("uf", UfEnum.values());
		modelAndView.addObject("situacao", SituacaoEnum.values());
		modelAndView.addObject("sexos", SexoEnum.values());

		return modelAndView;
	}

	private void configurarDataDependente(Dependente dependente) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(dependente.getDataFormatada());
		dependente.setDataNascimento(dataFormatada);
	}

	private void configurarDataPaciente(SolicitacaoDTO solicitacaoDTO) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy")
				.parse(solicitacaoDTO.getPaciente().getDataNascimentoFormatada());
		solicitacaoDTO.setDataPaciente(dataFormatada);
	}

	private void configurarDataRepresentante(SolicitacaoDTO solicitacaoDTO) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy")
				.parse(solicitacaoDTO.getRepresentante().getDataNascimentoFormatada());
		solicitacaoDTO.setData(dataFormatada);
	}

	private void configurarDataContratacaoMedico(SolicitacaoDTO solicitacaoDTO) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(solicitacaoDTO.getDataContratacaoFormatada());
		solicitacaoDTO.setDataContratacao(dataFormatada);
	}

	/*
	 * private void configurarDataNascimentoMedico(SolicitacaoDTO solicitacaoDTO)
	 * throws ParseException { Date dataFormatada = new
	 * SimpleDateFormat("dd/MM/yyyy").parse(solicitacaoDTO.
	 * getDataNascimentoFormatada()); solicitacaoDTO.setData(dataFormatada); }
	 */

	@RequestMapping(value = "solicitacao/consulta", method = RequestMethod.GET)
	public String solicitacaoConsulta(Locale locale, Model model, HttpServletRequest request) throws Exception {
		return "solicitacao/consulta";
	}

}
