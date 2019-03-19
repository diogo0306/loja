package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.exame.TabelaExame;

public class TabelaExameValidator implements Validator {

	public boolean supports(Class<?> c) {
		return TabelaExame.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tabelaExame.exame.id", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tabelaExame.tabela.id", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tabelaExame.valorTransiente", "informar.campo.obrigatorio");
	}
}