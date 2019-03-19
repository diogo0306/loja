package br.com.eclinic.saogabriel.api.controller.paciente;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eclinic.entity.documentacao.Documentacao;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.saogabriel.api.entity.usuario.PacienteDTO;
import br.com.eclinic.service.documentacao.DocumentacaoService;
import br.com.eclinic.service.paciente.PacienteService;

@Controller
public class ApiPacienteController {
	
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private DocumentacaoService documentacaoService;
	
	@RequestMapping(value = "/api/usuario/perfil" + "/{id}", produces = "application/json")
	public @ResponseBody PacienteDTO perfil(@PathVariable Long id, Locale locale, Model model,
			HttpServletRequest request) {
		return getPaciente(id);
	}
	
	private PacienteDTO getPaciente(long id) {
		PacienteDTO pacienteDTO = null;
		Paciente paciente = pacienteService.getPaciente(id);
		if(paciente != null) {
			Documentacao documentacao = documentacaoService.buscar(paciente.getDocumentacao().getId());
			paciente.setDocumentacao(documentacao);
		}
		
		if(paciente == null) {
			return pacienteDTO;
		}
		
		pacienteDTO = new PacienteDTO();
		pacienteDTO.setId(paciente.getId());
		pacienteDTO.setNome(paciente.getNome());
		pacienteDTO.setCep(paciente.getEndereco().getCep());
		pacienteDTO.setCidade(paciente.getEndereco().getCidade());
		pacienteDTO.setLogradouro(paciente.getEndereco().getLogradouro());
		pacienteDTO.setTelefone(paciente.getEndereco().getTelefone());
		pacienteDTO.setEmail(paciente.getDocumentacao().getEmail());
		pacienteDTO.setCelular(paciente.getEndereco().getCelular());
		pacienteDTO.setCpf(paciente.getDocumentacao().getCpf());
		pacienteDTO.setRg(paciente.getDocumentacao().getRg());
		pacienteDTO.setDataNascimento(paciente.getDataNascimentoFormatada());
		pacienteDTO.setBairro(paciente.getEndereco().getBairro());
		pacienteDTO.setNumero(paciente.getEndereco().getNumero());
		return pacienteDTO;
		
	}
}
