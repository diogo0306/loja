$(document).ready(function() {

	$("#inputDataNascimento").mask("99/99/9999");
	$("#inputDataContratacao").mask("99/99/9999");
	$("#inputCpf").mask("999.999.999-99");
	$("#inputCnpj").mask("99.999.999/9999-99");
	$("#inputCpfResponsavel").mask("999.999.999-99");
	$("#inputTelefone").mask("(99)9999-9999");
	$("#inputTelefoneSecundario").mask("(99)9999-9999");
	$("#inputCelular").mask("(99)99999-9999");
	$("#inputCep").mask("99999-999");
	$("#inputCep2").mask("99999-999");
	$("#inputDuracao").mask("99:99");
	$("#inputInicio").mask("99:99");
	$("#inputFim").mask("99:99");
	
	aplicarMascaraValor($("#inputValorConsulta"));
	
	if ($("#selectTipoContrato").val() === '1') {
		$("#inputCpf").removeAttr("disabled");
		$("#inputCnpj").attr("disabled", "disabled");
	}
	
	if ($("#selectTipoContrato").val() === '2') {
		$("#inputCnpj").removeAttr("disabled");
		$("#inputCpf").attr("disabled", "disabled");
	}
	
	if ($("#selectTipoContrato").val() === '0') {
		$("#inputCnpj").attr("disabled", "disabled");
		$("#inputCpf").attr("disabled", "disabled");
	}
	
	$("#selectTipoContrato").change(function(){
		if ($("#selectTipoContrato").val() === '1') {
			$("#inputCpf").removeAttr("disabled");
			$("#inputCnpj").attr("disabled", "disabled");
		} else if ($("#selectTipoContrato").val() === '2') {
			$("#inputCnpj").removeAttr("disabled");
			$("#inputCpf").attr("disabled", "disabled");
		} else {
			$("#inputCnpj").attr("disabled", "disabled");
			$("#inputCpf").attr("disabled", "disabled");
		}
	})
	
	$("#selectTipoProfissional").change(function(){
		$("#inputRegistro").val($("#selectTipoProfissional").val());
	})
	
	$("#btn-salvar_medico").click(function(){
		$("#formularioSolicitacaoMedico").attr('action', $("#url-salvar").val());
		$("#formularioSolicitacaoMedico").submit();
	});
	
	$("#btn-salvar").click(function(){
		if($("#reprovado").is(":checked") === true){
			$('#selectTipoPlano').removeAttr("required");
			$('#inputNome').removeAttr("required");
			$('#inputCpf').removeAttr("required");
			$('#inputDataContratacao').removeAttr("required");
			$('#inputConselho').removeAttr("required");
			$('#inputRg').removeAttr("required");
			$('#selectTipoSexo').removeAttr("required");
			$('#inputDataNascimento').removeAttr("required");
			$('#inputEndereco').removeAttr("required");
			$('#selectTipoUf').removeAttr("required");
			$('#inputEspecialidade').removeAttr("required");
			$('#inputDuracao').removeAttr("required");
			$('#jornadaInicio').removeAttr("required");
			$('#jornadaFim').removeAttr("required");
		}
		if($("#aprovado").is(":checked") === true){
			$('#selectTipoPlano').attr("required", "true");
			$('#inputNome').attr("required", "true");
			$('#inputCpf').attr("required", "true");
			$('#inputDataContratacao').attr("required", "true");
			$('#inputConselho').attr("required", "true");
			$('#inputRg').attr("required", "true");
			$('#selectTipoSexo').attr("required", "true");
			$('#inputDataNascimento').attr("required", "true");
			$('#inputEndereco').attr("required", "true");
			$('#selectTipoUf').attr("required", "true");
			$('#inputEspecialidade').attr("required", "true");
			$('#inputDuracao').attr("required", "true");
			$('#jornadaInicio').attr("required", "true");
			$('#jornadaFim').attr("required", "true");
		}
	});
	
	$(".aprovacao-form").click(function(){
		if($("#reprovado").is(":checked") === true || $("#aprovado").is(":checked") === true){
			$("#btn-salvar").removeAttr("disabled");
		} else {
			$("#btn-salvar").attr("disabled", "disabled");
		}
	});
	
	$(".checkbox-form").click(function(){
		if($("#domingo").is(":checked") === true || $("#segunda").is(":checked") === true || 
				$("#terca").is(":checked") === true || $("#quarta").is(":checked") === true || 
					$("#quinta").is(":checked") === true || $("#sexta").is(":checked") === true ||
						$("#sabado").is(":checked") === true ){
			$("#btn-salvar_medico_form").removeAttr("disabled");
		} else {
			$("#btn-salvar_medico_form").attr("disabled", "disabled");
		}
	});
	
});

function aplicarMascaraValor(input) {
	// Verifica se o campo existe
	
		input.maskMoney({
			thousands : '.',
			decimal : ','
		});
		input.maskMoney('mask');
}

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "medico/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}
