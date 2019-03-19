$(document).ready(function() {
	
	aplicarMascaraExame($("#inputVariacao"));
	
	$("#btn-salvar_faixa").click(function(){
		$("#formularioTabelaFaixa").attr('action', $("#url-salvar").val());
		$("#formularioTabelaFaixa").submit();
	});
	
});

function aplicarMascaraExame(input) {
	
		input.maskMoney({
			thousands : '.',
			decimal : ','
		});
		input.maskMoney('mask');
}

function exluirLista(link){
	
	var linha = link.parent().parent();
	console.log(linha);
	linha.remove();
	
}