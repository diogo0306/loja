package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.eclinic.controler.tabela.TabelaFaixaDTO;

public class TabelaFaixaValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return TabelaFaixaDTO.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tabelaFaixa.nome", "informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoBeneficiarioTransiente", "informar.campo.obrigatorio");
	}
}