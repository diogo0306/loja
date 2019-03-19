package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.exame.Tabela;

public class TabelaValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return Tabela.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tabela.nome", "informar.campo.obrigatorio");
	}
}
