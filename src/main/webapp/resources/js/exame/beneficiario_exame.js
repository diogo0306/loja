$(document).ready(function() {
	
	$('#selectTabela').change(function() {
		var codigoTabela = $(this).val();
        $.ajax({ 
            type: "GET",   
            url: "buscarExames/" + codigoTabela,
            success:   function(response){
            	 $('#selectExame').children().remove();
            	 $('#selectExame').append($('<option></option>').val(null).html("Selecione"));  
            	 $.each(response, function(val, text){  
            		 $('#selectExame').append($('<option></option>').val(text.id).html(text.nome));  
                 });  
		         },
		         error: function(e){
		         }
        });  
	});
	
	$('#inputData').mask('99/99/9999');
	
	var sel = $("#selectPaciente")
    sel.change(function () {
        $('#tabelaSetores > tbody').html('')
        $('#listaItens').html('')
    })
    
    var sel = $("#selectCredenciado")
    sel.change(function () {
        $('#tabelaSetores > tbody').html('')
        $('#listaItens').html('')
    })
    
    var sel = $("#selectTabela")
    sel.change(function () {
        $('#tabelaSetores > tbody').html('')
        $('#listaItens').html('')
    })
	
	$("#btn-salvar_form").click(function(){
		$("#formularioBeneficiario").attr('action', $("#url-salvar").val());
		$("#formularioBeneficiario").submit();
	})
	
	$("#btn-recalcular-valor").click(function(){
		$("#formularioBeneficiario").attr('action', $("#url-valor").val());
		$("#formularioBeneficiario").submit();
	})
	
	$("#btn-emitir-comprovante").click(function(){
		$("#formularioBeneficiario").attr('action', $("#url-emitir").val());
		$("#formularioBeneficiario").submit();
	})
});

function excluirLista(link){
	
	var linha = link.parent().parent();
	console.log(linha);
	linha.remove();
	
}
