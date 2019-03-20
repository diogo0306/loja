$(document).ready(function() {

	$("#inputCelular").mask("(99)99999-9999");
	$("#inputCpf").mask("999.999.999-99");

});

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "clienteLoja/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}

