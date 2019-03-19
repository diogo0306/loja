$(document).ready(function() {

	$("#inputDataNascimento").mask("99/99/9999");
	$("#inputDataNascimento2").mask("99/99/9999");
	$("#inputCpf").mask("999.999.999-99");
	$("#inputCep").mask("99999-999");
	$("#inputCpfDependente").mask("999.999.999-99");
	$("#inputTelefone").mask("(99)9999-9999");
	$("#inputCelular").mask("(99)99999-9999");
	$("#inputInicioVigencia").mask("99/99/9999");
	$("#inputFimVigencia").mask("99/99/9999");
	aplicarMascaraMoney($("#inputValorContrato"));
	aplicarMascaraMoney($("#inputTaxaContrato"));
	$("#inputDataVencimento").mask("99/99/9999");
	aplicarMascaraCobranca($("#inputValorCobranca"));
	
	$("#btn-adicionar").click(function(){
		$("#formularioPaciente").attr('action', $("#url-add").val());
		$("#formularioPaciente").submit();
	});
	
	
	$("#btn-salvar-cobranca").click(function(){
		$("#formularioPaciente").attr('action', $("#url-salvar").val());
		$("#formularioPaciente").submit();
	});
	
	var table = $('#tabelaCobranca').DataTable();
	 
	$('#tabelaCobranca tbody').on( 'click', '.removerLinha', function () {
	    table
	        .row( $(this).parents('tr') )
	        .remove()
	        .draw();
	} );
	
	
	/*$('#btn-adicionar').click(function(){
		console.log('entrou');
		var linha = '';
		var nome = $('#inputNomeDependente').val();
		var data = $('#inputDataNascimento2').val();
		var filiacao = $('#inputFiliacaoDependente').val();
		var sexo = $('#selectTipoSexoDependente').val();
		var cpf = $('#inputCpfDependente').val();
		var rg = $('#inputRgDependente').val();
		var orgao = $('#selectTipoOrgaoDependente').val();
		
		var linha = '<tr>'+
		'<td> <input name="listaDependente.nome[]" value="' + nome + '" readonly="true"/></td>'+
		'<td> <input name="listaDependente.cpf[]" value="' + cpf + '" readonly="true"/></td>'+
		'<td> <input name="listaDependente.rg[]" value="' + rg + '" readonly="true"/></td>'+
		'<td> <input name="listaDependente.dataFormatada[]" value="' + data + '" readonly="true"/></td>'+
		'</tr>';
		console.log(linha)
		$('#tb').append(linha);
		
	});*/
});

function aplicarMascaraMoney(input) {
	
	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
}

function aplicarMascaraCobranca(input) {

	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
}
