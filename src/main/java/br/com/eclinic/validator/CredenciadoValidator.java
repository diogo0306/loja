package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.credenciado.Credenciado;

public class CredenciadoValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		// TODO Auto-generated method stub
		return Credenciado.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo.obrigatorio");
	}
	
	public void validateSolicitacao(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "credenciado.nome", "informar.campo.obrigatorio");
	}

	public void validateAlterar(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo.obrigatorio");
	}
}
