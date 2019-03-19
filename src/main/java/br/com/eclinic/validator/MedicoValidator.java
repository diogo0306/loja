package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.medico.Medico;

public class MedicoValidator implements Validator {
	public boolean supports(Class<?> c) {
		return Medico.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medico.nome", "informar.campo.obrigatorio");
/*		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medico.tipoContratoTransiente", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medico.cpf", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medico.rg", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medico.dataNascimentoFormatada", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medico.codigoSexoTransiente", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medico.codigoUfTransiente", "informar.campo.obrigatorio");*/
	}
}
