

/*Limpa a tabela (tabelaSetores) que contém a lista de Setores quando
se escolhe outra empresa no elemento Select (selectEmpresa);
Habilita ou desabilita o botão de submeter, caso nenhuma empresa esteja selecionada
*/
$(document).ready(function(){
	
	$("#btn-salvar_setor").click(function(){
		$("#formularioSetor").attr('action', $("#url-salvar").val());
		$("#formularioSetor").submit();
	});

    var sel = $("#selectEmpresa")
    sel.change(function () {
        $('#tabelaSetores > tbody').html('')
        $('#listaItens').html('')
    })
})

function exluirLista(link){
	
	var linha = link.parent().parent();
	console.log(linha);
	linha.remove();
	
}

function paginacao(direcao) {
    $.ajax({
        type : "GET",
        url : "setor/paginacao/" + direcao,
        success : function(response) {
            $("#divList").html(response);
        },
        error : function(e) {
            alert(e);
        }

    });
}


/*Pega todos os campos do formulário se inserção, o qual possui a lista de Setores
* incluindo todos os campos que nele se encontram dentro do formulário de remoção
* mudando apenas o primeiro elemento, que contém o item a ser removido
* */
function sumbeterFormSetor(formExcluir, formIncluir) {
    var dadosFormExcluir = $(formExcluir).serializeArray()
    var dadosFormIncluir = $(formIncluir).serializeArray()
    dadosFormIncluir[0] = dadosFormExcluir[0]
    console.log(dadosFormExcluir)
    console.log(dadosFormIncluir)

    $.each(dadosFormIncluir, function (key, val) {
        $(formExcluir).append('<input type="hidden" name="' + val.name + '" value="' + val.value + '">')
        //$(formExcluir).append($('<input>').val('32131').html('Selecione'))
    })

    submeter(formExcluir)
    /*
        $.post($(formExcluir).attr('action'), dadosFormIncluir).success(function (res) {
            console.log(res)
        })*/

    /*console.log(JSON.stringify($('#form-salvar-setor').serializeArray()))*/
}

/*
* Submete o formulario de salvamento, que irá de fato persistir todos os setores incluidos
* pelo botão Incluir
* */
$('#botaoSalvarSetor').click(function () {
    $('#formAdicionarSetor').attr('action', $('#formAdicionarSetor').attr('action').replace('adicionar', 'salvar'))
    $('#formAdicionarSetor').submit()


    //alert($('#formAdicionarSetor').attr('action'))
    /*$.post('salvar', $('#formAdicionarSetor').serialize() , function (res) {
        console.log(res)
        console.log($('#formAdicionarSetor').serializeArray())
    })*/
})