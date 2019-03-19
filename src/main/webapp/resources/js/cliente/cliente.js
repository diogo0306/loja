$(document).ready(function() {

	$("#inputCnpj").mask("99.999.999/9999-99");
	$("#inputCep").mask("99999-999");
	aplicarMascara($("#inputTarifa"));
	$("#inputTelefone").mask("(99)-9999-9999");
	$("#inputCelular").mask("(99)-99999-9999");
	$("#inputTelefoneSec").mask("(99)-9999-9999");
	$("#inputCelularSec").mask("(99)-99999-9999");
	
	
	$("#inputCep").blur(function(){
		var cep = document.getElementById('inputCep').value;
		if(cep != ''){
			$.ajax({
				type : "GET",
				url : "../poste/poste/consultar-endereco/" + cep,
				success : function(retorno) {
					if (retorno != '') {
						$("#inputRua").val(retorno.rua);
						$("#inputBairro").val(retorno.bairro);
						
						 $('#inputEstado').children().remove();
						 $('#inputPais').children().remove();
			        	 $('#inputCidade').children().remove();
						
			        	 
			        	 if(retorno.cep == null){
			        		 $('#inputEstado').children().remove();
							 $('#inputPais').children().remove();
				        	 $('#inputCidade').children().remove();
				        	 $('#inputEstado').append($('<option></option>').val(null).html("Selecione")); 
				        	 $('#inputCidade').append($('<option></option>').val(null).html("Selecione")); 
				        	 $('#inputPais').append($('<option></option>').val(null).html("Selecione")); 
							 $('#inputPais').append($('<option></option>').val('Brasil').html('Brasil'));  	
							 $("#inputRua").val('');
							 $("#inputBairro").val('');
			        	 }else{
			        		 $('#inputPais').append($('<option></option>').val(retorno.estado.pais.nomePais).html(retorno.estado.pais.nomePais));  
			        		 

		                	 $.each(retorno.listaCidades, function(val, json){  
		                         if(json.id == retorno.cidade.id){
		                        	 $('#inputCidade').append($('<option></option>').val(json.nomeCidade).html(json.nomeCidade).attr("selected","selected"));  
		                         }else{
		                        	   $('#inputCidade').append($('<option></option>').val(json.idnomeCidade).html(json.nomeCidade));  
		                         }
		                     }); 
		                	 $.each(retorno.listaEstados, function(val, json){  
		                         if(retorno.estado.id == json.id){
		                        	 $('#inputEstado').append($('<option></option>').val(json.siglaEstado).html(json.siglaEstado).attr("selected","selected"));  
		                         }else{
		                        	 $('#inputEstado').append($('<option></option>').val(json.siglaEstado).html(json.siglaEstado));  
		                         }
		                     });
			        	 }
						
					} else {
						 $('#inputEstado').children().remove();
						 $('#inputPais').children().remove();
			        	 $('#inputCidade').children().remove();
			        	 $('#inputEstado').append($('<option></option>').val(null).html("Selecione")); 
			        	 $('#inputCidade').append($('<option></option>').val(null).html("Selecione")); 
			        	 $('#inputPais').append($('<option></option>').val(null).html("Selecione")); 
						 $('#inputPais').append($('<option></option>').val('Brasil').html('Brasil'));  	
					}

				}

			});
		}else{
			 
			 $('#inputEstado').children().remove();
			 $('#inputPais').children().remove();
        	 $('#inputCidade').children().remove();
        	 $('#inputEstado').append($('<option></option>').val(null).html("Selecione")); 
        	 $('#inputCidade').append($('<option></option>').val(null).html("Selecione")); 
        	 $('#inputPais').append($('<option></option>').val(null).html("Selecione")); 
			 $('#inputPais').append($('<option></option>').val('Brasil').html('Brasil'));  	
		}
		

	});
	
	//Procura os estados por pa�s.
	$('#inputPais').change(function() {
		$("#inputRua").val('');
		$("#inputBairro").val('');
		$("#inputEstado").text('Selecione');
		 if($('#inputPais option:selected').text().trim() == 'Brasil'){  	
			 $('#inputEstado option:selected').html('Selecione');
	            $.ajax({  
	                type: "GET",   
	                url: "../poste/listar-estados/Brasil",  
	                success:   function(response){
	                	 $('#inputEstado').children().remove();
	                	 $('#inputEstado').append($('<option></option>').val(null).html("Selecione"));  
	                	 $.each(response, function(val, text){  
	                		 $('#inputEstado').append($('<option></option>').val(text.siglaEstado).html(text.siglaEstado));  
	                     });  
	   		         },
	   		         error: function(e){
	   		    	  alert('Error: ' + e);
	   		         }
	            });  
	        }else{
	        	 $('#inputEstado').children().remove();
	        	 $('#inputCidade').children().remove();
            	 $('#inputEstado').append($('<option></option>').val(null).html("Selecione")); 
            	 $('#inputCidade').append($('<option></option>').val(null).html("Selecione")); 
	        }
	});
	
	//Procura as cidades por estado.
	$('#inputEstado').change(function() {
		$("#inputCidade").text('Selecione');
		 $("#inputRua").val('');
		 $("#inputBairro").val('');
		 if($('#inputEstado option:selected').index() > 0){  
			 $('#inputCidade option:selected').html('Selecione');
	            $.ajax({  
	                type: "GET",   
	                url: "../poste/listar-cidades/"+$('#inputEstado option:selected').text(),  
	                success:   function(response){
		                $('#inputCidade').children().remove();
		                $('#inputCidade').append($('<option></option>').val(null).html("Selecione"));  
	                	 $.each(response, function(val, text){  
	                         $('#inputCidade').append($('<option></option>').val(text.nomeCidade).html(text.nomeCidade));  
	                     });  
	   		         },
	   		         error: function(e){
	   		    	  alert('Error: ' + e);
	   		         }
	            });  
	        }
	});
	
});

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "cliente/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}
