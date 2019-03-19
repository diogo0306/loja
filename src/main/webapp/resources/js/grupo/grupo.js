$(document).ready(function() {
	
	$("#btn-salvar_procedimento").click(function(){
		$("#selectProcedimento").removeAttr('required');
		$("#formularioGrupoProcedimentoSalvar").attr('action', $("#url-salvar").val());
		$("#formularioGrupoProcedimentoSalvar").submit();
	});
	
});


function exluirLista(link){
	
	var linha = link.parent().parent();
	console.log(linha);
	linha.remove();
	
}