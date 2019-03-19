$(document).ready(function() {

	$("#inputDataNascimento").mask("99/99/9999");
	$("#inputDataNascimento2").mask("99/99/9999");
	$("#inputCpf").mask("999.999.999-99");
	$("#inputCpf2").mask("999.999.999-99");
	$("#inputCep").mask("99999-999");
	$("#inputTelefone").mask("(99)9999-9999");
	$("#inputCelular").mask("(99)99999-9999");
	
	$("#btn-salvar_paciente").click(function(){
		$("#formularioPaciente").attr('action', $("#url-salvar").val());
		$("#formularioPaciente").submit();
	});
	
});

