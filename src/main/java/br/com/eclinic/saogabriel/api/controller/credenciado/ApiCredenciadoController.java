package br.com.eclinic.saogabriel.api.controller.credenciado;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.saogabriel.api.entity.credenciado.CredenciadoDTO;
import br.com.eclinic.service.credenciado.CredenciadoService;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Controller
public class ApiCredenciadoController {
	
	@Autowired
	private CredenciadoService credenciadoService;
	
	@RequestMapping(value = "/api/alterar/credenciado", produces = "application/json")
	public @ResponseBody String alterarMedico(@ModelAttribute Credenciado credenciado, Locale locale, Model model,
			HttpServletRequest request) {
		
		try {
			credenciado = credenciadoService.buscar(credenciado.getId());
			
			if(credenciado == null) {
				return "Não existe";
			} else {
				credenciadoService.alterar(credenciado);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Sucesso";
	}
	
	@RequestMapping(value = "/api/perfil" + "/{id}", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiResponses(value = {
            @ApiResponse(code = 400, message = "There was a business error", response = VndErrors.class),
            @ApiResponse(code = 500, message = "An internal error occurred")
    })
	public @ResponseBody CredenciadoDTO perfil(@PathVariable Long id, Locale locale, Model model,
			HttpServletRequest request) {
		return credenciado(id);
	}

	@RequestMapping(value = "/api/credenciados", produces = "application/json")
	public @ResponseBody List<Credenciado> lista(Locale locale, Model model, HttpServletRequest request) throws Exception {
		return credenciadoService.listar();
	}

	/* Método para buscar apenas um credenciado */
	@RequestMapping(value = "/api/credenciado" + "/{id}", produces = "application/json")
	public @ResponseBody CredenciadoDTO buscar(@PathVariable Long id, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return credenciado(id);
	}

	/* Método para buscar apenas um médico */
	protected CredenciadoDTO credenciado(Long id) {
		Credenciado credenciado;
		CredenciadoDTO credenciadoDTO = null;
		List<Especialidade> especialidade = credenciadoService.listarEspecialidadesPorCredenciado(id);
		
		try {
			credenciado = credenciadoService.buscar(id);
			
			if(credenciado == null) {
				return credenciadoDTO;
			}
			
			for (Especialidade especialidades : especialidade) {

				credenciadoDTO = new CredenciadoDTO();
				credenciadoDTO.setId(credenciado.getId());
				credenciadoDTO.setNome(credenciado.getNome());
				credenciadoDTO.setCep(credenciado.getCep());
				credenciadoDTO.setCidade(credenciado.getCidade());
				credenciadoDTO.setLogradouro(credenciado.getLogradouro());
				credenciadoDTO.setTelefone(credenciado.getTelefone());
				credenciadoDTO.setEmail(credenciado.getEmail());
				credenciadoDTO.setCelular(credenciado.getCelular());
				credenciadoDTO.setCpf(credenciado.getCpf());
				credenciadoDTO.setRg(credenciado.getRg());
				//credenciadoDTO.setDataNascimento(credenciado.getDataNascimentoFormatada());
				credenciadoDTO.setBairro(credenciado.getBairro());
				credenciadoDTO.setNumero(credenciado.getNumero());
				credenciadoDTO.setValorPago(credenciado.getValorPago());	
				String valorFormatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(credenciado.getValorPago().doubleValue());
				credenciadoDTO.setValorPagoFormatado(valorFormatado);
				credenciadoDTO.setEspecialidade(especialidades.getEspecialidade());

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credenciadoDTO;
	}

	/* Método para listar os médicos referente as especialidade */
	@RequestMapping(value = "/api/cred/credenciado" + "/{id}", produces = "application/json")
	public @ResponseBody List<CredenciadoDTO> lista(@PathVariable Long id, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		return credendiados(id);
	}

	/* Método para listar os credenciados referente a especialidade */
	protected List<CredenciadoDTO> credendiados(Long id) {
		List<Credenciado> credenciado = credenciadoService.listarCredenciadosPorEspecialidades(id);
		List<CredenciadoDTO> credenciadosDTO = null; 

		if (credenciadosDTO == null) {
			credenciadosDTO = new ArrayList<CredenciadoDTO>();
		}

		for (Credenciado credenciados : credenciado) {

			CredenciadoDTO credendiadoDTO = new CredenciadoDTO();
			credendiadoDTO.setId(credenciados.getId());
			credendiadoDTO.setNome(credenciados.getNome());
			credendiadoDTO.setCep(credenciados.getCep());
			credendiadoDTO.setCidade(credenciados.getCidade());
			credendiadoDTO.setLogradouro(credenciados.getLogradouro());
			String valorFormatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(credenciados.getValorPago().doubleValue());
			credendiadoDTO.setValorPagoFormatado(valorFormatado);

			credenciadosDTO.add(credendiadoDTO);

		}

		return credenciadosDTO;
	}
}