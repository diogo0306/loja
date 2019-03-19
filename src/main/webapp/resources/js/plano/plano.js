$(document).ready(function(){
	aplicarMascaraPlano($("#inputValorPlano"));
	$("#inputDataCadastro").mask("99/99/9999");
	$('.js-decimal').maskMoney({decimal:'.', thousands:'.'})
})

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "plano/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}

$(document).ready(function() {
	
	
	$("#btn-salvar_faixa").click(function(){
		$("#formPlano").attr('action', $("#url-salvar").val());
		$("#formPlano").submit();
	});
	
});

function exluirLista(link){
	
	var linha = link.parent().parent();
	console.log(linha);
	linha.remove();
	
}

function aplicarMascaraPlano(input) {
	// Verifica se o campo existe

	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
}
