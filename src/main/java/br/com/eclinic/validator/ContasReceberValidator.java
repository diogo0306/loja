package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import br.com.eclinic.entity.contas.ContasReceber;
import org.springframework.validation.Validator;

public class ContasReceberValidator implements Validator {

	public boolean supports(Class<?> c) {
		return ContasReceber.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataPagamento", "informar.campo.obrigatorio");

	}

}
