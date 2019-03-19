$(document).ready(function() {

	$("#inputData").mask("99/99/9999");
	$("#inputPeriodoFim").mask("99/99/9999");
	$("#inputCep").mask("99999-999");
	$("#inputTelefone").mask("(99)9999-9999");
	$("#inputCelular").mask("(99)99999-9999");
	$("#inputCpf").mask("999.999.999-99");
	$("#inputCnpj").mask("99.999.999/9999-99");
	aplicarMascaraCredenciado($("#inputValorCobrado"));
	aplicarMascaraCredenciado($("#inputValorPago"));
	
	
	$("#divCpf").hide();
	$("#divCnpj").hide();
	$("#divRg").hide();
	
	
	$("#tipoPessoaJs").change(function() {
		if ($(this).find('option:selected').val() === '1') {

			$("#divCpf").show();
			$("#divRg").show();
			$("#divCnpj").hide();
			$("#divCnpj").val(null);
		} else if ($(this).find('option:selected').val() === '2') {

			$("#divCnpj").show();
			$("#divCpf").hide();
			$("#divRg").hide();
			$("#inputCnpj").val(null);
			$("#inputRg").val(null);
			
		} else {
			$("#divCpf").hide();
			$("#divCnpj").hide();
			$("#divRg").hide();
		}
	});

	if ($("#tipoPessoaJs").val() === '1') {
		$("#divCpf").show();
		$("#divCnpj").hide();
	}
	if ($("#tipoPessoaJs").val() === '2') {
		$("#divCnpj").show();
		$("#divCpf").hide();
	}

	function changeCpfCnjp() {

		if ($("#tipoPessoaJs").val() === 'FISICA') {
			$('#cpfOuCnpjLabel').text("CPF*");
			$("#inputCpfCnpj").mask("999.999.999-99");
		} else {
			$('#cpfOuCnpjLabel').text("CNPJ*");
			$("#inputCpfCnpj").mask("99.999.999/9999-99");
		}
	}

	$("#tipoPessoaJs").change(changeCpfCnjp);

	changeCpfCnjp();

});

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "credenciado/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}

function aplicarMascaraCredenciado(input) {
	// Verifica se o campo existe

	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
}
