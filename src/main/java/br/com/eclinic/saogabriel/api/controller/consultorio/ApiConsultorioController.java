package br.com.eclinic.saogabriel.api.controller.consultorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eclinic.saogabriel.api.entity.consultorio.Consultorio;
import br.com.eclinic.saogabriel.api.entity.consultorio.ConsultorioDTO;
import br.com.eclinic.saogabriel.api.service.consultorio.ApiConsultorioService;

@Controller
public class ApiConsultorioController {

	@Autowired
	ApiConsultorioService consultorioService;

	/* Método para cadastrar novo consultório */
	@RequestMapping(value = "/api/salvar/consultorio", produces = "application/json")
	public @ResponseBody Consultorio salvar(@RequestBody Consultorio consultorio, Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		consultorioService.salvar(consultorio);
		return consultorio;
	}
	
	/* Método para excluir consultório */
	@RequestMapping(value = "/api/excluir/consultorio" + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String excluirUsuario(@PathVariable Long id, Model model) {
		consultorioService.excluir(id);
		return "Sucesso";
	}

	/* Método para listar os consultorios referente aos medicos */
	@RequestMapping(value = "/api/consultorios" + "/{id}", produces = "application/json")
	public @ResponseBody List<ConsultorioDTO> lista(@PathVariable Long id, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return consultorios(id);
	}

	/* Método para listar os consultorios referente aos medicos */
	protected List<ConsultorioDTO> consultorios(Long id) {
		List<Consultorio> consultorio = consultorioService.listarConsultoriosPorMedico(id);
		List<ConsultorioDTO> consultoriosDTO = null;

		if (consultoriosDTO == null) {
			consultoriosDTO = new ArrayList<ConsultorioDTO>();
		}

		for (Consultorio consultorios : consultorio) {

			ConsultorioDTO consultorioDTO = new ConsultorioDTO();
			consultorioDTO.setId(consultorios.getId());
			consultorioDTO.setNomeConsultorio(consultorios.getNomeConsultorio());
			consultorioDTO.setCep(consultorios.getCep());
			consultorioDTO.setCidade(consultorios.getCidade());
			consultorioDTO.setEndereco(consultorios.getEndereco());
			consultorioDTO.setBairro(consultorios.getBairro());
			consultorioDTO.setNumero(consultorios.getNumero());
			consultorios.setTelefone(consultorios.getTelefone());

			consultoriosDTO.add(consultorioDTO);

		}

		return consultoriosDTO;
	}

}
