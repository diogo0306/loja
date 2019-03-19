package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.contrato.Contrato;

public class ContratoValidator implements Validator {

	public boolean supports(Class<?> c) {
		return Contrato.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {

		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numero",
		 * "informar.campo.obrigatorio");
		 */
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empresa",
		 * "informar.campo.obrigatorio");
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inicioVigencia", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fimVigencia", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "representante", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plano", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorContratoTransiente", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigoTipoPessoaContratoTransiente",
				"informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigoDiaVencimentoTransiente",
				"informar.campo.obrigatorio");
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paciente",
		 * "informar.campo.obrigatorio");
		 */

	}
}
