$(document).ready(function() {

	
	
	
	$("#inputCpf").mask("999.999.999-99");
	$("#inputCep").mask("99999-999");
	$("#inputTelefone").mask("(99)9999-9999");
	$("#inputCelular").mask("(99)99999-9999");


});

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "parametrizacao/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}


