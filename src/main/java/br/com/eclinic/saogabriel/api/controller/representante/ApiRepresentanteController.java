package br.com.eclinic.saogabriel.api.controller.representante;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eclinic.entity.enuns.SituacaoContratoEnum;
import br.com.eclinic.entity.solicitacao.Solicitacao;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.saogabriel.api.entity.contrato.ContratoDTO;
import br.com.eclinic.saogabriel.api.entity.solicitacao.SolicitacoesPendentesDTO;
import br.com.eclinic.service.contrato.ContratoService;
import br.com.eclinic.service.solicitacao.SolicitacaoService;

@Controller
public class ApiRepresentanteController {

	@Autowired
	private ContratoService contratoService;

	@Autowired
	private SolicitacaoService solicitacaoService;

	@RequestMapping(value = "/api/contrato" + "/{id}", produces = "application/json")
	public @ResponseBody List<ContratoDTO> buscar(@PathVariable Long id, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return contratosAtivos(id);
	}

	protected List<ContratoDTO> contratosAtivos(Long id) {
		ContratoDTO contratDTO = new ContratoDTO();
		List<ContratoDTO> contratoDTO = null;
		List<Contrato> contratos = contratoService.listarContratosPorRepresentante(id);

		if (contratoDTO == null) {
			contratoDTO = new ArrayList<ContratoDTO>();
		}

		if (contratos != null && contratos.size() == 0) {
			contratDTO.setMassage("nenhum contrato");
			contratoDTO.add(contratDTO);
		}

		for (Contrato contrato : contratos) {

			contratDTO.setId(contrato.getId());
			contratDTO.setNomePaciente(contrato.getPaciente().getNome());
			contratDTO.setSituacaoEnum(contrato.getSituacaoEnum());
			contratDTO.setValorContrato(contrato.getValorContrato());
			contratDTO.setInicioVigecia(contrato.getInicioVigencia());
			contratDTO.setMassage("sucesso");
			if (SituacaoContratoEnum.ATIVO.equals(contratDTO.getSituacaoEnum())) {
				contratoDTO.add(contratDTO);
			}

		}
		return contratoDTO;
	}

	@RequestMapping(value = "/api/contrato/pendentes" + "/{id}", produces = "application/json")
	public @ResponseBody List<SolicitacoesPendentesDTO> buscarPendente(@PathVariable Long id, Locale locale,
			Model model, HttpServletRequest request) throws Exception {
		List<SolicitacoesPendentesDTO> listaRetorno = solicitacoesPendentes(id);
		return listaRetorno;
	}

	protected List<SolicitacoesPendentesDTO> solicitacoesPendentes(Long id) {
		SolicitacoesPendentesDTO solicitacoesPendentes = new SolicitacoesPendentesDTO();
		List<SolicitacoesPendentesDTO> listaSolicitacoesDTO = null;
		List<Solicitacao> solicitacoes = solicitacaoService.listarSolicitacoesPorRepresentante(id);

		if (listaSolicitacoesDTO == null) {
			listaSolicitacoesDTO = new ArrayList<SolicitacoesPendentesDTO>();
		}

		for (Solicitacao solicitacao : solicitacoes) {
			solicitacoesPendentes.setNomePaciente(solicitacao.getNome());
			solicitacoesPendentes.setData(solicitacao.getDataFormatada());
			solicitacoesPendentes.setStatus(solicitacao.getStatus());
			solicitacoesPendentes.setMessage("sucesso");
			if (solicitacoesPendentes.getStatus().equals("PENDENTE")) {
				listaSolicitacoesDTO.add(solicitacoesPendentes);
			}
		}
		return listaSolicitacoesDTO;
	}

	@RequestMapping(value = "/api/contrato/paciente" + "/{id}", produces = "application/json")
	public @ResponseBody List<ContratoDTO> buscarPorPaciente(@PathVariable Long id, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return contratosPaciente(id);
	}

	protected List<ContratoDTO> contratosPaciente(Long id) {
		List<ContratoDTO> contratoDTO = null;
		List<Contrato> contratos = contratoService.listarContratosPorPaciente(id);

		if (contratoDTO == null) {
			contratoDTO = new ArrayList<ContratoDTO>();
		}

		for (Contrato contrato : contratos) {

			ContratoDTO contratDTO = new ContratoDTO();
			contratDTO.setId(contrato.getId());
			contratDTO.setNomePaciente(contrato.getPaciente().getNome());
			contratDTO.setSituacaoEnum(contrato.getSituacaoEnum());
			contratDTO.setValorContrato(contrato.getValorContrato());
			contratDTO.setInicioVigecia(contrato.getInicioVigencia());
			contratDTO.setFimVigencia(contrato.getFimVigencia());

			if (SituacaoContratoEnum.ATIVO.equals(contratDTO.getSituacaoEnum())) {
				contratoDTO.add(contratDTO);
			}

		}
		return contratoDTO;
	}
}
