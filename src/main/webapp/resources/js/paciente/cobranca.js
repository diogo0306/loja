$(document).ready(function() {

	$("#inputCPF").mask("999.999.999-99");
	aplicarMascaraExame($("#inputValor"));
	aplicarMascaraExame($("#inputPercentual"));

	$("#divValor").hide();
	$("#divPercentual").hide();

	$("#divCodigoBanco").hide();
	$("#divBanco").hide();
	$("#divAgencia").hide();
	$("#divConta").hide();

	$("#selectTipoTaxa").change(function() {
		if ($(this).find('option:selected').val() === '1') {
			$("#divValor").show();
			$("#divPercentual").hide();
			$("#divPercentual").val(null);
		} else if ($(this).find('option:selected').val() === '2') {
			$("#divValor").hide();
			$("#divPercentual").show();
			$("#inputValor").val(null);
		} else {
			$("#divValor").hide();
			$("#divPercentual").hide();
		}
	});
	
	if($("#selectTipoTaxa").val() === '1'){
		$("#divValor").show();
		$("#divPercentual").hide();
	}
	if($("#selectTipoTaxa").val() === '2'){
		$("#divPercentual").show();
		$("#divValor").hide();
	}

	$("#debitoAutomatico").change(function() {
		if ($("#debitoAutomatico").prop('checked') === true) {
			$("#divCodigoBanco").show();
			$("#divBanco").show();
			$("#divAgencia").show();
			$("#divConta").show();
		} else {
			$("#divCodigoBanco").hide();
			$("#divBanco").hide();
			$("#divAgencia").hide();
			$("#divConta").hide();
		}

	})
	
	$("#botao-salvar").on('click', function() {
		if($("#inputValor").val() === '0,00'){
			$("#inputValor").remove();
		}
		if($("#inputPercentual").val() === '0,00'){
			$("#inputPercentual").remove();
		}
	})

});

function aplicarMascaraExame(input) {

	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
}
