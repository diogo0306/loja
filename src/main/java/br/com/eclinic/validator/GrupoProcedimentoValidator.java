package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.procedimento.GrupoProcedimento;

public class GrupoProcedimentoValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return GrupoProcedimento.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoTransiente", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "informar.campo");
	}
}
