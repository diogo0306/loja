$(document).ready(function() {

	$("#inputInicioVigencia").mask("99/99/9999");
	$("#inputFimVigencia").mask("99/99/9999");
	aplicarMascaraContrato($("#inputPaciente"));
	aplicarMascaraContrato($("#inputEmpresa"));
	aplicarMascaraContrato($("#inputValorContrato"));

	$("#divEmpresa").hide();
	$("#divPaciente").hide();
	/*$("#divEmpresaVisualizar").hide();
	$("#divPacienteVisualizar").hide();*/
	

	$("#selectTipoPessoaContrato").change(function() {
		if ($(this).find('option:selected').val() === '1') {

			$("#divPaciente").show();
			$("#divEmpresa").hide();
			$("#divEmpresa").val(null);
		} else if ($(this).find('option:selected').val() === '2') {

			$("#divEmpresa").show();
			$("#divPaciente").hide();
			$("#inputEmpresa").val(null);
		} else {
			$("#divPaciente").hide();
			$("#divEmpresa").hide();
		}
	});

	if ($("#selectTipoPessoaContrato").val() === '1') {
		$("#divPaciente").show();
		$("#divEmpresa").hide();
	}
	if ($("#selectTipoPessoaContrato").val() === '2') {
		$("#divEmpresa").show();
		$("#divPaciente").hide();
	}

	$("#btn-salvar_valor").click(function() {
		$("#formularioValor").attr('action', $("#url-salvar").val());
		$("#formularioValor").submit();
	});

	var sel = $("#selectTabela")
	sel.change(function() {
		$('#tabelaExames > tbody').html('')
		$('#listaItens').html('')
	})
	
	/*if ($("#tipoPessoa").val() === 'Pessoa Física') {
		$("#divPacienteVisualizar").show();
		$("#divEmpresaVisualizar").hide();
	}
	if ($("#tipoPessoa").val() === 'Pessoa Jurídica') {
		$("#divEmpresaVisualizar").show();
		$("#divPacienteVisualizar").hide();
	}*/

});

function aplicarMascaraContrato(input) {
	// Verifica se o campo existe

	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
}