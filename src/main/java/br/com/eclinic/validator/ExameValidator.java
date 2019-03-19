package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.exame.Exame;

public class ExameValidator implements Validator {

	public boolean supports(Class<?> c) {
		return Exame.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorTransiente", "informar.campo.obrigatorio");
	}
}
