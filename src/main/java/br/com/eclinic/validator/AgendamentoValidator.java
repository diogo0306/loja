package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.agendamento.AgendamentoDTO;

public class AgendamentoValidator implements Validator {

	public boolean supports(Class<?> c) {
		return Agendamento.class.isAssignableFrom(c);
	}

	public void validate(Object object, Errors errors) {
		Agendamento agendamento = ((Agendamento) object);

		if (agendamento.getMedico() == null || agendamento.getMedico().getId() == null) {
			errors.rejectValue("medico.id", "informar.campo.obrigatorio");
		}
	}

	public void validateDTO(Object object, Errors errors) {
		AgendamentoDTO agendamentoDTO = ((AgendamentoDTO) object);

		if (agendamentoDTO.getMedico() == null || agendamentoDTO.getMedico().getId() == null) {
			errors.rejectValue("medico.id", "informar.campo.obrigatorio");
		}
	}
}