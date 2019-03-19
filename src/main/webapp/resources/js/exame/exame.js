$(document).ready(function() {
	
	aplicarMascaraExame($("#inputValor"));
	
});

function aplicarMascaraExame(input) {
	// Verifica se o campo existe
	
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