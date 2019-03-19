package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.plano.Plano;

public class PlanoValidator implements Validator {

	public boolean supports(Class<?> c) {
		return Plano.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataCadastro", "informar.campo.obrigatorio");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorPlanoTransiente", "informar.campo.obrigatorio");
		
	}
}
