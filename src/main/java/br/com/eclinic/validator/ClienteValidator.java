package br.com.eclinic.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.cliente.Cliente;

@Component
public class ClienteValidator implements Validator {

	public boolean supports(Class<?> c) {
		return Cliente.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnpj", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cep", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estado", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cidade", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "logradouro", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bairro", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefone", "informar.campo.obrigatorio");
	}
}
