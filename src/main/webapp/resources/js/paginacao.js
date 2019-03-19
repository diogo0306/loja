$(document).ready(function() {

	$(".linkAvancar").click(function() {
		paginacao("avancar");
	});

	$(".linkVoltar").click(function() {
		paginacao("voltar");
	});

	atualizaComponentesPaginacao();

});

function atualizaComponentesPaginacao() {

	if ($("#exibir-paginacao").attr("value") == "true") {
		$(".pager").removeClass("hidden");
	} else {
		$(".pager").addClass("hidden");
	}

	if ($("#primeira-pagina").attr("value") == "true") {
		$(".linkVoltar").parent().addClass("disabled");
	} else {
		$(".linkVoltar").parent().removeClass("disabled");
	}

	if ($("#ultima-pagina").attr("value") == "true") {
		$(".linkAvancar").parent().addClass("disabled");
	} else {
		$(".linkAvancar").parent().removeClass("disabled");
	}
}
