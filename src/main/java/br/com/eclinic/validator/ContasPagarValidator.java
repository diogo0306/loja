package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import br.com.eclinic.entity.contas.ContasPagar;

public class ContasPagarValidator implements Validator {
	
	public boolean supports(Class<?> c) {
		return ContasPagar.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataPagamento", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorFormatado", "informar.campo.obrigatorio");

	}

}
