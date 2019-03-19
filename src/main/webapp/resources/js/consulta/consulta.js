$(document).ready(function(){
	
	$("#dataConsulta").mask("99/99/9999");
	$("#inputCpf").mask("999.999.999-99");
	$("#hora").mask("99:99:99");
	

	$("#inputCpf2").mask("999.999.999-99");
	$("#inputCpfPagadorComCadastro").mask("999.999.999-99");
	$("#inputCpfPagadorSemCadastro").mask("999.999.999-99");
	$("#cpfPagador").mask("999.999.999-99");
	$("#inputCelular").mask("(99)99999-9999");
	$("#inputDataNascimento").mask("99/99/9999");
	$("#inputDataNascimento2").mask("99/99/9999");
	$("#inputTelefone").mask("(99)9999-9999");
	aplicarMascaraExame($("#inputValorConsulta"));
	$("#inputCpfPagador").mask("999.999.999-99");
	

	$("#a-selecionar-paciente").click(function() {

		var idPoste = $("input[name='idPaciente']:checked").val();
		if (idPoste != null) {
			$("#id-paciente").attr("value", idPoste);
		}

		$("#form-selecionar").submit();
	});
	
	
});

function aplicarMascaraExame(input) {
	// Verifica se o campo existe
	
		input.maskMoney({
			thousands : '.',
			decimal : ','
		});
		input.maskMoney('mask');
}

function paginacao(direcao) {
	  $.ajax({  
         type: "GET",   
         url: "/eclinic/consulta/paginacao/" + direcao,             
         success:   function(response){            	 
		    	 			$("#divList").html(response);
		             	},
		             	error: function(e){
		             		alert(e);
		             	}
				
			});
}