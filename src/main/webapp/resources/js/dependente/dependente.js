$(document).ready(function() {

	$("#inputCpf").mask("999.999.999-99");
	aplicarMascaraCobranca($("#inputValorCobranca"));
	
	$("#btn-salvar-dependente").click(function(){
		$("#formularioIncluirDependente").attr('action', $("#url-salvar").val());
		$("#formularioIncluirDependente").submit();
	});
	
	var table = $('#tabelaCobranca').DataTable();
	 
	$('#tabelaCobranca tbody').on( 'click', '.removerLinha', function () {
	    table
	        .row( $(this).parents('tr') )
	        .remove()
	        .draw();
	} );
	
});

function aplicarMascaraCobranca(input) {
	// Verifica se o campo existe

	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
}