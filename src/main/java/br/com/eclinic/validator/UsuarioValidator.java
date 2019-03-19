package br.com.eclinic.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.usuario.Usuario;

@Component
public class UsuarioValidator implements Validator {

	public boolean supports(Class<?> c) {
		return Usuario.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {
		// Usuario usuario = ((Usuario)object);

		/*
		 * if(usuario.getPerfil().getId() == null || usuario.getPerfil().getId() == 0){
		 * errors.rejectValue("perfil", "informar.campo.obrigatorio"); }
		 */

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rg", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefone", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "perfil.id", "informar.campo.obrigatorio");
	}
}
