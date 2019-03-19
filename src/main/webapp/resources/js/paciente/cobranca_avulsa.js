$(document).ready(function() {

	$("#inputDataVencimento").mask("99/99/9999");
	aplicarMascaraCobranca($("#inputValorCobranca"));
	
	$("#btn-salvar-cobranca").click(function(){
		$("#formularioIncluirCobrancaAvulsa").attr('action', $("#url-salvar").val());
		$("#formularioIncluirCobrancaAvulsa").submit();
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