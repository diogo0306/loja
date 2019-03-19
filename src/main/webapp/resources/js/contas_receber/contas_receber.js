$(document).ready(function() {

	$("#inputDataVencimento").mask("99/99/9999");
	$("#inputDataPagamento").mask("99/99/9999");

	aplicarMascaraContasReceber($("#inputValor"));
	aplicarMascaraContasReceber($("#inputValorPago"));

	$("#btn-salvar_valor").click(function() {
		$("#formularioValor").attr('action', $("#url-salvar").val());
		$("#formularioValor").submit();
	});

});

function aplicarMascaraContasReceber(input) {

	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
}