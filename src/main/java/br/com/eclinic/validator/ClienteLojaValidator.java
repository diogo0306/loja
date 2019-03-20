package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.clienteLoja.ClienteLoja;

public class ClienteLojaValidator implements Validator {

	public boolean supports(Class<?> c) {
		return ClienteLoja.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo.obrigatorio");

	}

}
