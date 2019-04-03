package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import br.com.eclinic.entity.usuarioLoja.UsuarioLoja;

public class UsuarioLojaValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return UsuarioLoja.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeUsuario", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigoTipoUsuarioLojaTransiente", "informar.campo.obrigatorio");
	}

}
