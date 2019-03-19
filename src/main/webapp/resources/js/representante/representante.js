$(document).ready(function() {

	$("#inputDataNascimento").mask("99/99/9999");
	$("#inputCpf").mask("999.999.999-99");
	$("#inputTelefone").mask("(99)9999-9999");
	$("#inputTelefoneSecundario").mask("(99)9999-9999");
	$("#inputCelular").mask("(99)99999-9999");
	$("#inputCep").mask("99999-999");

	function changeCpfCnjp() {

		if ($("#tipoPessoaJs").val() === 'PESSOA_FISICA') {
			$('#cpfOuCnpjLabel').text("CPF*");
			$("#inputCpfCnpj").mask("999.999.999-99");
		} else {
			$('#cpfOuCnpjLabel').text("CNPJ*");
			$("#inputCpfCnpj").mask("99.999.999/9999-99");
		}
	}
	
	function changeCpfCnjpSolicitacao() {

		if ($("#tipoPessoaSolicitacao").val() === '1') {
			$("#inputCpfCnpjSolicitacao").mask("999.999.999-99");
		} else {
			$("#inputCpfCnpjSolicitacao").mask("99.999.999/9999-99");
		}
	}

	$("#tipoPessoaJs").change(changeCpfCnjp);
	
	$("#tipoPessoaSolicitacao").change(changeCpfCnjpSolicitacao);

	changeCpfCnjp();
	
	changeCpfCnjpSolicitacao();
	
	$("#btn-salvar").click(function(){
		if($("#reprovado").is(":checked") === true){
			$('#tipoPessoaSolicitacao').removeAttr("required");
			$('#inputNome').removeAttr("required");
			$('#inputCpfCnpjSolicitacao').removeAttr("required");
			$('#inputRG').removeAttr("required");
			$('#selectTipoPlano').removeAttr("required");
			$('#inputDataNascimento').removeAttr("required");
			$('#inputMae').removeAttr("required");
			$('#inputEndereco').removeAttr("required");
			$('#inputBairro').removeAttr("required");
			$('#inputMunicipio').removeAttr("required");
			$('#inputCep').removeAttr("required");
			$('#inputUF').removeAttr("required");
			$('#inputCelular').removeAttr("required");
		}
		if($("#aprovado").is(":checked") === true){
			$('#tipoPessoaSolicitacao').attr("required", "true");
			$('#inputNome').attr("required", "true");
			$('#inputCpfCnpjSolicitacao').attr("required", "true");
			$('#inputRG').attr("required", "true");
			$('#selectTipoPlano').attr("required", "true");
			$('#inputDataNascimento').attr("required", "true");
			$('#inputMae').attr("required", "true");
			$('#inputEndereco').attr("required", "true");
			$('#inputBairro').attr("required", "true");
			$('#inputMunicipio').attr("required", "true");
			$('#inputCep').attr("required", "true");
			$('#inputUF').attr("required", "true");
			$('#inputCelular').attr("required", "true");
		}
	});
	
	$(".aprovacao-form").click(function(){
		if($("#reprovado").is(":checked") === true || $("#aprovado").is(":checked") === true){
			$("#btn-salvar").removeAttr("disabled");
		} else {
			$("#btn-salvar").attr("disabled", "disabled");
		}
	});

});

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "representante/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}
