$(document).ready(function(){
    	
		$("#btn-salvar_funcao").click(function() {
			$("#formularioFuncao").attr('action', $("#url-salvar").val());
			$("#formularioFuncao").submit();
		});
		
		var sel = $("#inputEmpresa")
	    sel.change(function () {
	        $('#tabelaFuncao > tbody').html('')
	        $('#listaItens').html('')
	    })

			$('#inputEmpresa').change(function(){
						var codigoEmpresa = $(this).val();
						$.ajax({
							type : "GET",
							url : "buscarsetores/" + codigoEmpresa,
							success : function(response) {
								$('#inputSetor').children().remove();
								$('#inputSetor').append($('<option></option>').val(null).html("Selecione"));
								$.each(response, function(val, text){
									$('#inputSetor').append($('<option></option>').val(text.id).html(text.descricao));
								});
							},
							error : function(e) {
							}
						});
			});

			// Procura os setores por empresa.
			$('#inputEmpresa').change(
					function() {
						var codigoEmpresa = $(this).val();
						$.ajax({
							type : "GET",
							url : "funcao/buscarsetores/" + codigoEmpresa,
							success : function(response) {
								$('#inputSetor').children().remove();
								$('#inputSetor').append($('<option></option>').val(null).html("Selecione"));
								$.each(response, function(val, text){
									$('#inputSetor').append($('<option></option>').val(text.id).html(text.descricao));
								});
							},
							error : function(e) {
							}
						});
			});
			
		$('.alert').fadeTo(2000, 500).slideUp(500, function () {
		   $('.alert').slideUp(500)
		})

});

function exluirLista(link){
	
	var linha = link.parent().parent();
	console.log(linha);
	linha.remove();
	
}

function paginacao(direcao) {
    $.ajax({
        type: "GET",
        url: "funcao/paginacao/" + direcao,
        success: function (response) {
            $("#divList").html(response);
        },
        error: function (e) {
            alert(e);
        }

    });
}