$(document).ready(function(){
	$("#btn-salvar").click(function(){
		$("#formularioDevice").attr('action', $("#url-salvar").val());
		$("#formularioDevice").submit();
	});
	
	$("#btn-add-device").click(function(){
		$("#formularioDevice").attr('action', $("#url-adicionar-device").val());
		$("#formularioDevice").submit();
	});
	
});


$(document).ready(function(){
	$("#telefoneUsuario").mask("(99)-9999-9999");
	$("#cpfUsuario").mask("999.999.999-99");
	
	
	$("#btn-add-perfil").click(function(){
		$("#formularioPerfil").attr('action', $("#url-adicionar-perfil").val());
		$("#formularioPerfil").submit();
	});
	
});

function paginacao(direcao) {
	  $.ajax({  
         type: "GET",   
         url: "perfil/paginacao/" + direcao,             
         success:   function(response){            	 
		    	 			$("#divList").html(response);
		             	},
		             	error: function(e){
		             		alert(e);
		             	}
				
			});
}
