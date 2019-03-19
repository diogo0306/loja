$(document).ready(function() {

	$("#inputDataVenci").mask("99/99/9999");
	$("#inputDataPagamento").mask("99/99/9999");

	aplicarMascaraProduto($("#inputPrecoCusto"));
	aplicarMascaraProduto($("#inputPrecoVenda"));

	$("#btn-salvar_valor").click(function() {
		$("#formularioValor").attr('action', $("#url-salvar").val());
		$("#formularioValor").submit();
	});

});

function aplicarMascaraProduto(input) {

	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
}