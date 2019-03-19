$(document).ready(function() {
	$(".abrirJanela").click(function() {
		$('#modalWaiting').modal('show');
	});
});


function aplicarMascara(input) {
	// Verifica se o campo existe
	if (input.length) {
		if (input.val().split('.').length == 2
				&& input.val().split('.')[1].length == 1) {
			input.attr("value", input.val() + "0");
		} else if (input.val().split('.').length == 1) {
			input.attr("value", input.val() + "00");
		}
		input.maskMoney({
			thousands : '',
			decimal : '.'
		});
		input.maskMoney('mask');
	}
}

function exibirPainelErro() {

	if ($("#msg-erro").html().length == 0) {
		$("#painel-erro").addClass('hidden');
	} else {
		$("#painel-erro").removeClass('hidden');
	}
}

function submeter(form) {
	form.submit();
}

function submeterComJanela(form, modal) {
	form.submit();
	$('#' + modal.id).modal('hide');
}

