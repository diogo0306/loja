package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.representante.Representante;

public class RepresentanteValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return Representante.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object object, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "representante.nome", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "representante.cpfcnpj", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "representante.codigoRepresentanteTransiente",
				"informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "representante.rg", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "representante.codigoSexoTransiente", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "representante.dataNascimentoFormatada", "informar.campo");
	}

	public void validateFormulario(Object object, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigoRepresentanteTransiente", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rg", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigoSexoTransiente", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascimentoFormatada", "informar.campo");
	}
}