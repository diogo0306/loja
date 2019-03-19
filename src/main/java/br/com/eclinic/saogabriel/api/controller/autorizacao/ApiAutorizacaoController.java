package br.com.eclinic.saogabriel.api.controller.autorizacao;

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

import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.saogabriel.api.entity.consulta.AutorizacaoDTO;
import br.com.eclinic.service.autorizacao.AutorizacaoService;
import br.com.eclinic.service.credenciado.CredenciadoService;

@Controller
public class ApiAutorizacaoController {

	@Autowired
	private AutorizacaoService autorizacaoService;

	@Autowired
	private CredenciadoService credenciadoService;

	// FIXME Retornando da tabela consulta
	@RequestMapping(value = "/api/autorizacao" + "/{id}", produces = "application/json")
	public @ResponseBody List<AutorizacaoDTO> buscar(@PathVariable Long id, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return consulta(id);
	}

	// FIXME listar consultas por médico a única diferença é retorno do
	// paciente, ou seja, credenciado
	// retorna o paciente.
	@RequestMapping(value = "/api/autorizacao/credenciado" + "/{id}", produces = "application/json")
	public @ResponseBody List<AutorizacaoDTO> buscarPorMedico(@PathVariable Long id, Integer mes, Integer ano,
			Locale locale, Model model, HttpServletRequest request) throws Exception {

		return consulta(id);
	}

	// FIXME Método retorna tanto as consultas para usuário quanto para o
	// credenciado
	protected List<AutorizacaoDTO> consulta(Long id) throws NegocioException {
		List<AutorizacaoDTO> autorizacaoDTO = null;
		List<Autorizacao> autorizacoes = autorizacaoService.listarAutorizacaoPorPaciente(id);
		AutorizacaoDTO autorizacoesDTO = null;

		try {

			if (autorizacaoDTO == null) {
				autorizacaoDTO = new ArrayList<AutorizacaoDTO>();
			}

			for (Autorizacao autorizacao : autorizacoes) {

				Credenciado credenciado = credenciadoService.buscar(autorizacao.getCredenciado().getId());

				autorizacoesDTO = new AutorizacaoDTO();

				if (credenciado == null) {
					autorizacoesDTO.setMessage("Profssional vazio");
				}

				autorizacoesDTO.setMedico(credenciado.getNome());
				autorizacoesDTO.setNomePaciente(autorizacao.getCredenciado().getNome());
				autorizacoesDTO.setCodigoConsulta(autorizacao.getNumeroAutorizacao());
				autorizacoesDTO.setDataConsulta(autorizacao.getDataAutorizacao());
				autorizacoesDTO.setStatus(autorizacao.getStatus().getDescricao());
				autorizacoesDTO.setValorConsulta(autorizacao.getValor());
				autorizacoesDTO.setMessage("sucesso");
				autorizacaoDTO.add(autorizacoesDTO);
			}
		} catch (NullPointerException e) {
			autorizacoesDTO.setMessage("Error ao retornar as consultas");
		}
		return autorizacaoDTO;
	}

}
