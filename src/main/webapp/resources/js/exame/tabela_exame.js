$(document).ready(function() {
	
	aplicarMascaraExame($("#inputValor"));
	
	$("#btn-salvar_valor").click(function(){
		$("#formularioValor").attr('action', $("#url-salvar").val());
		$("#formularioValor").submit();
	});
	
	var sel = $("#selectTabela")
    sel.change(function () {
        $('#tabelaExames > tbody').html('')
        $('#listaItens').html('')
    })

});

function aplicarMascaraExame(input) {
	// Verifica se o campo existe
	
		input.maskMoney({
			thousands : '.',
			decimal : ','
		});
		input.maskMoney('mask');
}

function exluirLista(link){
	
	var linha = link.parent().parent();
	console.log(linha);
	linha.remove();
	
}


function submeterFormModalTabela(){
	

	var inputNome = $("#inputNomeTabela")
	var divFieldDescricaoRequerido = $('#msgFieldDescricaoRequerido')

	if($('#inputNomeTabela').val() === ''){
		inputNome.addClass('field_error')
		divFieldDescricaoRequerido.removeAttr('hidden')
        return
	}

	var formModal = $('#formCadastrarTabela')
	var rota = formModal.attr('action')
    
		
		$.post(rota, formModal.serialize())
		.success(function (res) {
			if(res == null) return
			console.log(res);
			
			if(res.status == true){
				$('#selectTabela').append('<option value = "'+ res.tabela.id +'">' + res.tabela.nome + '</option>');
				$('#selectTabela').val(0);
				inputNome.val('');
			} else {
				$('#selectTabela').val(0);
			}			
			
			//Tratar os erros com o css dos campos
			if(res.fieldErrors != null){
               	$("#msgFieldDescricaoRequerido").removeAttr('hidden')
				return
			}
			
			//VERIFICAR SE O AGENTE JÁ FOI INSERIDO
			
			console.log(res.status);
									
            $('#alerta-insercao-tabela').html(
                '<div class="alert row alert-'+ (res.status? 'success':'danger') +' ">'+
                '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
                res.mensagem +
                '</div>'
            ).fadeTo(2000, 500).slideUp(500, function(){
                $('#alerta-insercao-tabela').slideUp(500);
            });
            
      

            $("#modalCadastrarTabela").modal('hide')
		})
		.error(function (err) {
			$('#alerta-insercao-tabela').html(
            	'<div class="alert row alert-danger">'+
            	'<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
            	'Não foi possível cadastrar a tabela. Tente novamente!'+
            	'</div>'
			).fadeTo(2000, 500).slideUp(500, function(){
                $('#alerta-insercao-tabela').slideUp(500);
            });
            $("#modalCadastrarTabela").modal('hide')
    	})

}

function submeterFormModalExame(){
	

	var inputNome = $("#inputNomeExame")
	var inputDescricao = $("#inputDescricao")
	var divFieldDescricaoRequeridoExame = $('#msgFieldDescricaoRequeridoExame')

	if($('#inputNomeExame').val() === ''){
		inputNome.addClass('field_error')
		divFieldDescricaoRequeridoExame.removeAttr('hidden')
        return
	}

	var formModal = $('#formCadastrarExame')
	var rota = formModal.attr('action')
    
		
		$.post(rota, formModal.serialize())
		.success(function (res) {
			if(res == null) return
			console.log(res);
			
			if(res.status == true){
				$('#selectExame').append('<option value = "'+ res.exame.id +'">' + res.exame.nome + '</option>');
				$('#selectExame').val(0);
				inputNome.val('');
			} else {
				$('#selectExame').val(0);
			}			
			
			//Tratar os erros com o css dos campos
			if(res.fieldErrors != null){
               	$("#msgFieldDescricaoRequeridoExame").removeAttr('hidden')
				return
			}
			
			//VERIFICAR SE O AGENTE JÁ FOI INSERIDO
			
			console.log(res.status);
									
            $('#alerta-insercao-tabela').html(
                '<div class="alert row alert-'+ (res.status? 'success':'danger') +' ">'+
                '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
                res.mensagem +
                '</div>'
            ).fadeTo(2000, 500).slideUp(500, function(){
                $('#alerta-insercao-tabela').slideUp(500);
            });
            
            $("#modalCadastrarExame").modal('hide')
		})
		.error(function (err) {
			$('#alerta-insercao-tabela').html(
            	'<div class="alert row alert-danger">'+
            	'<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
            	'Não foi possível cadastrar a tabela. Tente novamente!'+
            	'</div>'
			).fadeTo(2000, 500).slideUp(500, function(){
                $('#alerta-insercao-tabela').slideUp(500);
            });
            $("#modalCadastrarExame").modal('hide')
    	})

}