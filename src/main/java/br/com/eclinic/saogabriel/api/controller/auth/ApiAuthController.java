package br.com.eclinic.saogabriel.api.controller.auth;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.representante.Representante;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.saogabriel.api.entity.auth.AuthDTO;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.paciente.PacienteService;
import br.com.eclinic.service.representante.RepresentanteService;
import br.com.eclinic.service.usuario.UsuarioService;

@Controller
public class ApiAuthController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private RepresentanteService representanteService;
	@Autowired
	private CredenciadoService credenciadoService;

	@RequestMapping(value = "/api/auth", produces = "application/json")
	public @ResponseBody AuthDTO auth(@RequestBody Usuario usuario, Locale locale, Model model,
			HttpServletRequest request) {
		AuthDTO authDto = new AuthDTO();

		try {

			Usuario usu = usuarioService.realizarCpf(usuario);

			// FIXME paciente
			if (usu.getPaciente() != null && usu.getPaciente().getId() != null && usu.getPaciente().getId() != 0) {
				Paciente paciente = pacienteService.buscar(usu.getPaciente().getId());

				if (paciente != null) {
					authDto.setId(paciente.getId());
					authDto.setTipoUsuario(usu.getTipoUsuario().getDescricao());
				} else {
					authDto.setMessage("Usuario nao encontrado");
				}
			}

			// FIXME Credenciado
			if (usu.getCredenciado() != null && usu.getCredenciado().getId() != null && usu.getCredenciado().getId() != 0) {
				Credenciado credenciado = credenciadoService.buscar(usu.getCredenciado().getId());

				if (credenciado != null) {
					authDto.setId(credenciado.getId());
					authDto.setTipoUsuario(usu.getTipoUsuario().getDescricao());
				} else {
					authDto.setMessage("Credenciado nao encontrado");
				}
			}

			// FIXME Representante
			if (usu.getRepresentante() != null && usu.getRepresentante().getId() != null
					&& usu.getRepresentante().getId() != 0) {
				Representante representante = representanteService.buscar(usu.getRepresentante().getId());

				if (representante != null) {
					authDto.setId(representante.getId());
					authDto.setTipoUsuario(usu.getTipoUsuario().getDescricao());
				} else {
					authDto.setMessage("Representante nao encontrato");
				}
			}

		} catch (NullPointerException e) {
			authDto.setMessage("error requisicao");
		} catch (Exception e) {
			authDto.setMessage("error comunicacao");
		}

		return authDto;
	}
}
