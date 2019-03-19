$(document).ready(function() {
	
	$("#btn-salvar_tabela").click(function(){
		$("#formularioTabela").attr('action', $("#url-salvar").val());
		$("#formularioTabela").submit();
	});
	
});

function exluirLista(link){
	
	var linha = link.parent().parent();
	console.log(linha);
	linha.remove();
	
}

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "tabela/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}