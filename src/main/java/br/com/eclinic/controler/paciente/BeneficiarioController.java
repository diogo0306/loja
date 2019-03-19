package br.com.eclinic.controler.paciente;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.agendamento.StatusPagamentoEnum;
import br.com.eclinic.entity.contrato.CobrancaContrato;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.contrato.DiaVencimentoEnum;
import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.entity.medico.OrgaoEmissorEnum;
import br.com.eclinic.entity.medico.SexoEnum;
import br.com.eclinic.entity.medico.UfEnum;
import br.com.eclinic.entity.paciente.PacienteDTO;
import br.com.eclinic.entity.solicitacao.Solicitacao;
import br.com.eclinic.entity.usuario.TipoUsuarioEnum;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.service.cobrancaContrato.CobrancaContratoService;
import br.com.eclinic.service.contrato.ContratoService;
import br.com.eclinic.service.dependente.DependenteService;
import br.com.eclinic.service.paciente.PacienteService;
import br.com.eclinic.service.plano.PlanoService;
import br.com.eclinic.service.solicitacao.SolicitacaoService;
import br.com.eclinic.service.usuario.UsuarioService;

@Controller
public class BeneficiarioController extends EclinicController {

	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private DependenteService dependenteService;
	@Autowired
	private CobrancaContratoService cobrancaContratoService;
	@Autowired
	private ContratoService contratoService;
	@Autowired
	private SolicitacaoService solicitacaoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PlanoService planoService;
	
	@RequestMapping(value = "/beneficiario/gestao", method = RequestMethod.GET)
	public String beeficiario(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("pacienteDTO", new PacienteDTO());
		model.addAttribute("sexos", SexoEnum.values());
		model.addAttribute("uf", UfEnum.values());
		model.addAttribute("orgaos", OrgaoEmissorEnum.values());
		model.addAttribute("diaVencimento", DiaVencimentoEnum.values());
		model.addAttribute("planos", planoService.listar());

		return "beneficiario.gestao";
	}

	@RequestMapping(value = "/beneficiario/gestao/salvar", method = RequestMethod.POST)
	public ModelAndView salvarSolicitacaoPaciente(@ModelAttribute PacienteDTO pacienteDTO, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException {

		if (pacienteDTO.getPaciente() != null) {
			pacienteDTO = pacienteService.configurarFormBeneficiario(pacienteDTO, request);
		}

		if (pacienteService.consultarCpf(pacienteDTO.getPaciente()) != null) {
			ModelAndView mav = new ModelAndView("beneficiario.gestao");
			mav.addObject("pacienteDTO", pacienteDTO);
			mav.addObject("sexos", SexoEnum.values());
			mav.addObject("uf", UfEnum.values());
			mav.addObject("orgaos", OrgaoEmissorEnum.values());
			mav.addObject("diaVencimento", DiaVencimentoEnum.values());
			mav.addObject("planos", planoService.listar());
			mav.addObject(MESSAGE_ERROR, getMensagem("paciente.inclusao.erro.existe"));
			return mav;
		}

		try {
			pacienteService.salvar(pacienteDTO.getPaciente());
			contratoService.salvar(pacienteDTO.getContrato());
			contratoCobranca(pacienteDTO.getContrato());

			if (pacienteDTO.getIdSolicitacao() != null) {
				Solicitacao solicitacao = solicitacaoService.buscar(pacienteDTO.getIdSolicitacao());
				solicitacao.setStatus("APROVADO");
				solicitacao.setPaciente(pacienteDTO.getPaciente());

				if (pacienteDTO.getPaciente().getId() != null && pacienteDTO.getPaciente().getId() != 0) {
					Usuario usuario = new Usuario();
					usuario.setNome(pacienteDTO.getPaciente().getNome());
					usuario.setLogin(solicitacao.getCpf().replace(".", "").replace("-", ""));
					usuario.setSenha(solicitacao.getSenha());
					usuario.setCpf(solicitacao.getCpf().replace(".", "").replace("-", ""));
					usuario.setTipoUsuario(TipoUsuarioEnum.PACIENTE);
					usuario.setPaciente(pacienteDTO.getPaciente());
					usuarioService.salvar(usuario);
				}

				solicitacaoService.alterar(solicitacao);
			}

			if (!pacienteDTO.getListaDependente().isEmpty()) {
				if (pacienteDTO.getPaciente().getId() != null) {
					for (Dependente dependente : pacienteDTO.getListaDependente()) {
						dependente.setPacienteVinculo(pacienteDTO.getPaciente());
						if (dependente.getDataFormatada() != null && dependente.getDataFormatada() != "") {
							Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy")
									.parse(dependente.getDataFormatada());
							dependente.setDataNascimento(dataFormatada);
						}
						dependenteService.salvar(dependente);
					}
				}
			}
		} catch (Exception e) {
			new Exception(e);
		}

		redirectAttributes.addFlashAttribute(MESSAGE, "Beneficiário cadastrado com sucesso.");
		ModelAndView mav = new ModelAndView("redirect:/pacientes");

		return mav;
	}

	@RequestMapping(value = "/beneficiario/gestao/acidionar/dependente", method = RequestMethod.POST)
	public ModelAndView adicionarDependente(@ModelAttribute PacienteDTO pacienteDTO, BindingResult result, Model model,
			HttpServletRequest request) throws ParseException {

		pacienteDTO = pacienteService.configurarDependente(pacienteDTO);

		ModelAndView mav = new ModelAndView("beneficiario.gestao");
		mav.addObject("pacienteDTO", pacienteDTO);
		mav.addObject("sexos", SexoEnum.values());
		mav.addObject("uf", UfEnum.values());
		mav.addObject("orgaos", OrgaoEmissorEnum.values());
		mav.addObject("planos", planoService.listar());
		mav.addObject("diaVencimento", DiaVencimentoEnum.values());

		return mav;
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
				cobrancaContratoService.salvar(cobrancaContrato);
			}
		} catch (Exception e) {
			new Exception(e);
		}
	}

}
