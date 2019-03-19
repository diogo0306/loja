$(document).ready(function() {

	$("#inputDataConsulta").mask("99/99/9999");
	$("#hora").mask("99:99");
	$("#inputCelular").mask("(99)99999-9999");
	$("#inputDataNascimento").mask("99/99/9999");
	$("#inputDataNascimento2").mask("99/99/9999");
	$("#inputTelefone").mask("(99)9999-9999");
	$("#inputCpf").mask("999.999.999-99");
	$(".cpf").mask("999.999.999-99");
	aplicarMascara($(".valor"));

	$("#a-selecionar-paciente").click(function() {

		var idPoste = $("input[name='idPaciente']:checked").val();
		if (idPoste != null) {
			$("#id-paciente").attr("value", idPoste);
		}

		$("#form-selecionar").submit();
	});

});

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