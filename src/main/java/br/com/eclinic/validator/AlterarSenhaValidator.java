package br.com.eclinic.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.usuario.OTDAlterarSenha;

@Component
public class AlterarSenhaValidator implements Validator {

	public boolean supports(Class<?> c) {
		return OTDAlterarSenha.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {

		OTDAlterarSenha alterarSenha = (OTDAlterarSenha) object;

		if (StringUtils.isBlank(alterarSenha.getSenhaAtual()) || StringUtils.isBlank(alterarSenha.getSenhaNova())
				|| StringUtils.isBlank(alterarSenha.getConfirmacaoSenhaNova())) {
			errors.rejectValue("senhaAtual", "todos.campos.obrigatorio");
		}

		if (StringUtils.isNotBlank(alterarSenha.getSenhaNova())
				&& StringUtils.isNotBlank(alterarSenha.getConfirmacaoSenhaNova())
				&& !alterarSenha.getSenhaNova().equals(alterarSenha.getConfirmacaoSenhaNova())) {
			errors.rejectValue("senhaNova", "senhas.diferentes");
		}
	}
}