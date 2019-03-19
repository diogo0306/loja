$(document).ready(function(){
	
	$("#dataSolicitacao").mask("99/99/9999");
	$("#inputDataNascimento").mask("99/99/9999");
	$("#inputDataExame").mask("99/99/9999");
	$("#inputDataNascimento2").mask("99/99/9999");
	$("#inputCpf").mask("999.999.999-99");
	$("#inputCpf2").mask("999.999.999-99");
	$("#inputCep").mask("99999-999");
	$("#inputTelefone").mask("(99)9999-9999");
	$("#inputCelular").mask("(99)99999-9999");
	
	$("#btn-salvar_paciente").click(function(){
		$("#formularioSolicitacaoPaciente").attr('action', $("#url-salvar").val());
		$("#formularioSolicitacaoPaciente").submit();
	});
	
	$("#btn-salvar").click(function(){
		if($("#reprovado").is(":checked") === true){
			$('#inputNome').removeAttr("required");
			$('#inputDataNascimento').removeAttr("required");
			$('#selectTipoSexo').removeAttr("required");
			$('#inputCpf').removeAttr("required");
			$('#inputRg').removeAttr("required");
			$('#selectTipoSexo').removeAttr("required");
			$('#inputEndereco').removeAttr("required");
			$('#inputBairro').removeAttr("required");
			$('#inputCidade').removeAttr("required");
			$('#selectTipoUF').removeAttr("required");
			$('#inputCep').removeAttr("required");
			$('#inputCelular').removeAttr("required");
			$('#selectTipoOrgao').removeAttr("required");
		}
		if($("#aprovado").is(":checked") === true){
			$('#inputNome').attr("required", "true");
			$('#inputDataNascimento').attr("required", "true");
			$('#selectTipoSexo').attr("required", "true");
			$('#inputCpf').attr("required", "true");
			$('#inputRg').attr("required", "true");
			$('#selectTipoSexo').attr("required", "true");
			$('#inputEndereco').attr("required", "true");
			$('#inputBairro').attr("required", "true");
			$('#inputCidade').attr("required", "true");
			$('#selectTipoUF').attr("required", "true");
			$('#inputCep').attr("required", "true");
			$('#inputCelular').attr("required", "true");
			$('#selectTipoOrgao').attr("required", "true");
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
         type: "GET",   
         url: "solicitacao/paginacao/" + direcao,             
         success:   function(response){            	 
		    	 			$("#divList").html(response);
		             	},
		             	error: function(e){
		             		alert(e);
		             	}
				
			});
}