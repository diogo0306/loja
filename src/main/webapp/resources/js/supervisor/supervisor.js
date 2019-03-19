$(document).ready(function() {

	$("#inputCpf").mask("999.999.999-99");
	$("#inputTelefone").mask("(99)9999-9999");

});

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "supervisor/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}
