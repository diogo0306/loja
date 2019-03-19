package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.paciente.Paciente;

public class PacienteValidator implements Validator {

	public boolean supports(Class<?> c) {
		return Paciente.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente.nome", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente.filiacaoMae", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente.documentacao.telefone", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente.endereco.cidade", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente.dataNascimentoFormatada", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente.documentacao.cpf", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente.documentacao.rg", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente.codigoSexoTransiente", "informar.campo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente.endereco.cep", "informar.campo");
	}
}
