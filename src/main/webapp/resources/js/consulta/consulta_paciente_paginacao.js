function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "paciente/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}
