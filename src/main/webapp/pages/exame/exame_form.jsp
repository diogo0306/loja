<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}exame/salvar_exame" id="url-salvar" />


<%--Alerta que será tratado via javascript, de acordo com o resultado da inserção de um risco pelo modal--%>
<div class="row" id='alerta-insercao-tabela'></div>

<div class="panel panel-default">


	<div class="panel-heading">
		<h5
			style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
			<i class="fa fa-signal"></i> &nbsp;&nbsp; Exames - Dados do Exame
		</h5>
	</div>

	<c:url value="/" var="raiz" />

	<div class="divSeparador2">
		<c:if test="${campos_obrigatorios != null}">
			<div class="row">
				<div class="alert alert-danger">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>${campos_obrigatorios}</div>
			</div>
		</c:if>

		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="exame.nome" cssClass="text-danger" />
			</div>
		</div>

		<div class="row">
			<div class="col-xs-1">
				<label for="inputNome">Código*</label>
			</div>
			<div class="col-xs-4">
				<form:input type="text" class="form-control" id="inputCodigo"
					placeholder="Digite o código" path="exame.codigo"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="inputNome">Nome*</label>
			</div>
			<div class="col-xs-6">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o nome" path="exame.nome"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-1">
				<label for="inputNome">Valor*</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o nome" path="exame.valorTransiente"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />

		<div class="row">
			<div class="col-xs-1">
				<label for="inputDescricao">Descrição</label>
			</div>
			<div class="col-xs-6">
				<form:textarea maxlength="254" rows="5" cols="30"
					class="form-control" id="inputTurno"
					placeholder="Até 254 caracteres" path="exame.descricao"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
	</div>
</div>

<div class="form-inline">
	<button class="btn btn-success abrirJanela" style="width: 100%;">Adicionar</button>
</div>
<br />

<div class="panel panel-default">
	<div class="panel-heading">
		<h5
			style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
			<i class="fa fa-credit-card"></i> &nbsp;&nbsp; Resumo
		</h5>
	</div>

	<div class="divSeparador2"
		style="max-height: 400px; overflow: scroll; overflow-x: hidden;">
		<div class="form-group">
			<table class="table table-hover table-striped table-bordered "
				id="tabelaExames">
				<thead>
					<tr>
						<th width="10%">Nome</th>
						<th width="10%">Valor</th>
						<th width="10%">Descricao</th>
						<th width="5%">Ações</th>
					</tr>
				</thead>
				<tbody id="listaItens">
					<c:forEach var="exame" items="${exameDTO.lista}" varStatus="status">
						<tr>
							<td><c:out value="${exame.nome}" /></td>
							<td><c:out value="${exame.descricao}" /></td>
							<td><a class="glyphicon glyphicon-remove"
								id="btn-excluir_exame_lista" onclick="exluirLista($(this))"></a></td>

							<form:input path="lista[${status.index}].nome" type="hidden" />
							<form:input path="lista[${status.index}].id" type="hidden" />
							<form:input path="lista[${status.index}].descricao" type="hidden" />
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>

<div class="row">

	<div class="col-xs-offset-1 col-xs-12" style="margin-left: 0%">
		<c:if test="${alterarTela == null}">
			<button class="btn btn-success" id="btn-salvar_exame"
				style="width: 100%;">Salvar</button>
		</c:if>
		<c:if test="${alterarTela != null}">
			<button class="btn btn-success abrirJanela" style="width: 100%;">Alterar</button>&nbsp;
			<a href="${raiz}exames" class="btn btn-danger abrirJanela"
				style="width: 100%;">Voltar</a>
		</c:if>
	</div>
	<br />
</div>
<br />

<script type="text/javascript"
	src="<c:url value="/resources/js/exame/exame.js" />"></script>

