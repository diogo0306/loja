package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.procedimento.Procedimento;

public class ProcedimentoValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return Procedimento.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo.obrigatorio");
	}
}
