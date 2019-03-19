package br.com.eclinic.saogabriel.api.controller.especialidade;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.saogabriel.api.entity.especialidade.EspecialidadeDTO;
import br.com.eclinic.service.especialidade.EspecialidadeService;

@Controller
public class ApiEspecialidadeController {

	@Autowired
	private EspecialidadeService especialidadeService;

	@RequestMapping(value = "/api/especialidades", produces = "application/json")
	public @ResponseBody List<EspecialidadeDTO> lista(Locale locale, Model model, HttpServletRequest request)
			throws Exception {

		return especialidades();
	}

	protected List<EspecialidadeDTO> especialidades() {
		List<Especialidade> especialidade = especialidadeService.listar();
		List<EspecialidadeDTO> especialidadesDTO = null;

		if (especialidadesDTO == null) {
			especialidadesDTO = new ArrayList<EspecialidadeDTO>();
		}

		for (Especialidade especialidades : especialidade) {

			EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
			especialidadeDTO.setId(especialidades.getId());
			especialidadeDTO.setNome(especialidades.getEspecialidade());

			especialidadesDTO.add(especialidadeDTO);

		}

		return especialidadesDTO;
	}
}