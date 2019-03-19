package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.supervisor.Supervisor;

public class SupervisorValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return Supervisor.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "informar.campo");
	}
}
