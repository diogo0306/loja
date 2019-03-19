$(document).ready(function(){
	
	$("#field-1, #field-2").mask("99/99/9999");
	
	$("#field-3").change(function() {
		carregarComboTecnico($(this).val());
	});
	
	//Ao recarregar a tela
	if ($("#field-3").val() != 0) {
		carregarComboTecnico($("#field-3").val());
	}
	
});

function carregarComboTecnico(idPrestadora) {
	$.ajax({  
        type: "GET",   
        url: $("#url-tecnicos").val() + "?idPrestadora=" + idPrestadora,             
        success:   function(response){            	 
	    	 			$("#divTecnicos").html(response);
	             	},
	             	error: function(e){
	             		alert(e);
	             	}
		});
}
