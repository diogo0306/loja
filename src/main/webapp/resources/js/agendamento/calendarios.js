$(document).ready(function() {

	$("#inputDataConsulta").mask("99/99/9999");
	$("#hora").mask("99:99:99");
	$("#inputCelular").mask("(99)99999-9999");
	$("#inputDataNascimento").mask("99/99/9999");
	$("#inputDataNascimento2").mask("99/99/9999");
	$("#inputTelefone").mask("(99)9999-9999");

	$("#inputCpf").mask("999.999.999-99");
	aplicarMascara($(".valor"));

	$("#a-selecionar-paciente").click(function() {

		var idPoste = $("input[name='idPaciente']:checked").val();
		if (idPoste != null) {
			$("#id-paciente").attr("value", idPoste);
		}

		$("#form-selecionar").submit();
	});
	
	// Procura os setores por empresa.
	/*$('#inputMedico').change(function() {
				var idMedico = $(this).val();
	            $.ajax({ 
	                type: "GET",   
	                url: "evento/buscarMedico/" + idMedico,
	                success:   function(response){
	                	 
	   		         },
	   		         error: function(e){
	   		         }
	            });  
	});*/

	
	
	$("#inputData").change(function(event){
		
		var ano = $('#inputData').val().substring(0,4);
		var mes = $('#inputData').val().substring(5,7);
		var dia = $('#inputData').val().substring(8);
		
		
		$("#inputHorario").empty();
		
		$.get('/eclinic/medico/jornada/' + $("#inputMedico").val() + '?data=' +dia+'/'+mes+'/'+ano,function(data){
						
			data.forEach(function(item){
				$('#inputHorario').append('<option value="' + item + '">' + item + '</option>');
			})
				
		})
		
		$("#inputHorario").show();
	})
	
	
});

function listarJornadas(idMedico) {
	$.ajax({
		type : "GET",
		url : "medico/jornada/" + idMedico,

		dataType : 'json',
		contentType : 'application/json;charset=utf-8',
		sucess : function(response) {

		},
		error : function(err) {

		}
	})
}