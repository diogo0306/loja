package br.com.eclinic.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import br.com.eclinic.entity.parametrizacao.ParametrizacaoDTO;

public class ParametrizacaoValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		// TODO Auto-generated method stub
		return ParametrizacaoDTO.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parametrizacao.codigoMesProcessamentoTransiente",
				"informar.campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parametrizacao.anoProcessamento",
				"informar.campo.obrigatorio");
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.periodoInicialFormatada", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.periodoFinalFormatada", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.codigoControleBiometricoTransiente",
		 * "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.codigoEditorTextoTransiente", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.utilizarIndiceClinicaMedica", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.utilizarLayoutProprioGuia", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.permitirAtualizarValorLiberacaoGuia",
		 * "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.emitirValoresGuiaSADT", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.emitirValoresGuiaAmbulatorio", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.emitirValoresGuiaInternacao", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.valorLiberacaoOnLine", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.valorLiberacaoSistema", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.codigoSimNaoTransiente", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.consistirRetornoConsultaPrazo",
		 * "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.gerarValidadeCarteiraPrazo", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.integrarCobrancaFinanceiro", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.codigoTipoCobrançaEmpresaTransiente",
		 * "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.codigoControleContratualTransiente",
		 * "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.nossoNumero", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.prazoAtrasadoMensalidade", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.prazoCancelamentoContrato", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.multaAtraso", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.prazoAplicacaoMulta", "informar.campo.obrigatorio");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "parametrizacao.jurosFixoDiario", "informar.campo.obrigatorio");
		 */

	}

	public void validateAlterar(Object target, Errors errors) {
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome",
		 * "informar.campo.obrigatorio");
		 */
	}

}
