package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.indice.IndiceEconomico;

public class IndiceEconomicoValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return IndiceEconomico.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "competencia", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "percentualTransiente", "informar.campo.obrigatorio");
	}
}