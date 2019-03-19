<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}tabela/salvar_tabela" id="url-salvar" />


<div class="content">
	<div class="panel panel-flat">
		<div class="panel-heading">
			<div class="heading-elements">
				<ul class="icons-list">
					<li><a data-action="collapse"></a></li>
					<li><a data-action="reload"></a></li>
				</ul>
			</div>
			<a class="heading-elements-toggle"><i class="icon-menu"></i></a>
		</div>

		<div class="panel-body">
			<fieldset class="content-group">
				<legend class="text-bold">Dados da Tabela</legend>

				<c:if test="${campos_obrigatorios != null}">
					<div class="row">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>${campos_obrigatorios}</div>
					</div>
				</c:if>

				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="tabela.nome" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="inputNome">Nome*</label>
					</div>
					<div class="col-xs-6">
						<form:input type="text" class="form-control" id="inputNome"
							placeholder="Digite o nome" path="tabela.nome"
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
							placeholder="Até 254 caracteres" path="tabela.descricao"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="form-inline">
					<button class="btn btn-success abrirJanela" style="width: 7%;">
						Adicionar <i class=" icon-folder-plus4"></i>
					</button>
				</div>
				<br />

			</fieldset>

			<fieldset class="content-group">
				<legend class="text-bold">Resumo</legend>

				<table class="table table-hover table-striped table-bordered "
					id="tabelaExames">
					<thead>
						<tr>
							<th width="10%">Nome</th>
							<th width="5%">Ações</th>
						</tr>
					</thead>
					<tbody id="listaItens">
						<c:forEach var="tabela" items="${exameDTO.listaTabela}"
							varStatus="status">
							<tr>
								<td><c:out value="${tabela.nome}" /></td>
								<td><a class="glyphicon glyphicon-remove"
									id="btn-excluir_tabela_lista" onclick="exluirLista($(this))"></a></td>

								<form:input path="listaTabela[${status.index}].nome"
									type="hidden" />
								<form:input path="listaTabela[${status.index}].id" type="hidden" />
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</fieldset>

			<div class="text-right">
				<a href="${raiz}tabelas" class=" btn bg-danger abrirJanela">Cancelar</a>

				<button type="submit" class="btn btn-primary"
					id="btn-salvar_tabela">
					Salvar <i class="icon-arrow-right14 position-right"></i>
				</button>
			</div>

		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/exame/tabela.js" />"></script>

