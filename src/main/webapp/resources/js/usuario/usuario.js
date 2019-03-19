$(document).ready(function(){
	$("#telefoneUsuario").mask("(99)-9999-9999");
	$("#cpfUsuario").mask("999.999.999-99");

	$("#btn-add-perfil").click(function(){
		$("#formularioPerfil").attr('action', $("#url-adicionar-perfil").val());
		$("#formularioPerfil").submit();
	});
	
	$("#btn-add-usuario").click(function(){
		$("#formularioAdicionarUsuario").attr('action', $("#url-adicionar-usuario").val());
		$("#formularioAdicionarUsuario").submit();
	});

	$("#btn-add-funcionalidade").click(function(){
        addFuncionalidadeHiddenFields();
	})

    $("#alerta-funcionalidade-escolhida").css("visibility", "hidden");
});

//Pega todos os valores dos campos do formulário da página e atribui no formulário do modal de inserir Perfil
function setHiddenFieldsFormCadastrarPerfil(){
	const fields = ["nome","login","telefone","cpf","rg","email"];
	fields.map(function(field){
		$("#formCadastrarPerfil :input[name='" + field  +  "']")
			.val($("#id-form :input[name='" + field  +  "']").val())
		//console.log($("#formCadastrarPerfil :input[name='" + field  +  "']").val())
	})
}

var listaFuncionalidades = []

function addFuncionalidadeHiddenFields(){
	var textoSelecionado = $("#funcionalidades option:selected").text();
    var valorSelecionado = $("#funcionalidades option:selected").val();

	if(listaFuncionalidades.indexOf(valorSelecionado) == -1){
        listaFuncionalidades.push(valorSelecionado)
        if($("#funcionalidades option:selected").val() !== "NULL"){
            var row = '<tr id="tr-' + valorSelecionado +'">'
                .concat('<td>' + textoSelecionado+ '</td>' )
                .concat('<td>' + getCheckboxFields('chk-inserir') + '</td>')
                .concat('<td>' + getCheckboxFields('chk-alterar') + '</td>')
                .concat('<td>' + getCheckboxFields('chk-excluir') + '</td>')
                .concat('<td>' + getCheckboxFields('chk-visualizar') + '</td>')
                .concat('<td><a href="#" data-toggle="modal" data-target="#modal1" title="Excluir"> <span class="glyphicon glyphicon-remove"></span></a></td></tr>')
            $("#tabela-funcionalidades tbody:last").append(row)
        }
	}else{
        $("#alerta-funcionalidade-escolhida").css("visibility", "visible")
        .fadeTo(2000, 500).slideUp(500, function(){
            $("#alerta-funcionalidade-escolhida").slideUp(500);
        });

		//alert('A funcionalidade ' + $("#funcionalidades option:selected").text() +  ' já  foi escolhida');
	}
}

function getCheckboxFields(nomeField){
	/*console.log($("#" + nomeField).is(":checked"));*/
	return $("#" + nomeField).is(":checked").toString().toUpperCase()
}

function paginacao(direcao) {
	  $.ajax({  
         type: "GET",   
         url: "usuario/paginacao/" + direcao,             
         success:   function(response){            	 
		    	 			$("#divList").html(response);
		             	},
		             	error: function(e){
		             		alert(e);
		             	}
				
			});
}
