package br.com.eclinic.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.usuario.Perfil;

@Component
public class PerfilValidator implements Validator {

	public boolean supports(Class<?> c) {
		return Perfil.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "informar.campo.obrigatorio");
	}
}
