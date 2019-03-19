$(document).ready(function() {
	
	//aplicarMascaraExame($("#inputPercentual"));
	$("#inputCompetencia").mask("99/9999");
	
	$('.js-decimal').maskMoney({decimal:'.', thousands:'.'})

});



/*function aplicarMascaraExame(input) {
	
	input.maskMoney({
		thousands : '.',
		decimal : ','
	});
	input.maskMoney('mask');
} */