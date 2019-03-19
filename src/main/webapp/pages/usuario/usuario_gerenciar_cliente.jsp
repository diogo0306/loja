<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript"
	src="<c:url value="/resources/js/usuario/usuario.js" />"></script>

<c:url value="/" var="raiz" />
<c:url var="urlBase" value="/..${raiz}usuario/" />
<input type="hidden" value="${urlBase}gerenciar_cliente"
	id="url-adicionar-usuario" />
<input type="hidden" value="${urlBase}excluir_cliente"
	id="url-remover-usuario" />
<input type="hidden" value="${urlBase}salvar" id="url-salvar" />
<!-- Begin page content -->
<div class="container">
	
	<div class="page-header">
		<div class="btn-group btn-breadcrumb">
			<a href="#" class="btn btn-default"><i
				class="glyphicon glyphicon-home"></i></a> <a href="#"
				class="btn btn-default">usuários</a> <a href="#"
				class="btn btn-default">gerenciar clientes</a>
		</div>
	</div>

	<form:form class="form-horizontal" role="form"
		action="${raiz}usuario/salvar" id="formularioAdicionarUsuario" method="POST"
		modelAttribute="usuario">

		<c:url value="/" var="raiz" />

		<form:input path="id" type="hidden" />

		<c:if test="${erroAdicionarClienteUsuario != null}">
			<div class="row">
				<div class="alert alert-danger">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>${erroAdicionarClienteUsuario}</div>
			</div>
		</c:if>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h6 class="panel-title">Usuário - ${usuario.login}</h6>
			</div>
			<div class="divSeparador2">
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="*" cssClass="text-danger" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-1">
						<label for="inputNome">Clientes</label>
					</div>
					<div class="col-xs-3">
						<form:select id="clientes" class="form-control"
							path="clienteAdicionar.id">
							<option value="${null}">Selecione</option>
							<c:forEach var="cliente" items="${clientes}">
								<option value="${cliente.id}">${cliente.id}-
									${cliente.nome}</option>
							</c:forEach>
						</form:select>
					</div>
						<a href="#" id="btn-add-usuario" class="btn btn-primary">Adicionar</a>
				</div>
				<div class="col-lg-4">
				
				</div>
			</div>

			<br /> <br />

		</div>
		<div class="panel panel-default" style="height: 200px !important;">
			<div class="panel-heading">
				<h6 class="panel-title">Lista de Clientes</h6>
			</div>
			<div id="divList"
				style="overflow: auto !important; height: 162px !important;">
				<c:url value="/" var="raiz" />

				<form:form action="${raiz}usuario/excluir_cliente" method="POST"
					modelAttribute="usuario" id="form-excluir">
					<form:hidden path="clienteAdicionar.id" id="input-excluir" />
				</form:form>

				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>Nome</th>
							<th>CNPJ</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="clienteUsuario" items="${usuario.clientesPermissao}">
							<tr>
								<td><c:out value="${clienteUsuario.nome}" /></td>
								<td><c:out value="${clienteUsuario.cnpj}" /></td>
								<td><form:form action="${raiz}usuario/excluir_cliente"
										method="POST" modelAttribute="usuario"
										class="formulario_acoes">
										<form:hidden path="id"
											value="${usuario.id}" />
										<form:hidden path="clienteAdicionar.id"
											value="${clienteUsuario.id}" />
										<a href="javascript:;" onclick="parentNode.submit();"
											title="Excluir"> <span class="glyphicon glyphicon-remove"></span>
										</a>
									</form:form></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>


		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<a href="${raiz}usuarios" class="btn btn-danger">voltar</a>
			</div>
		</div>
	</form:form>
</div>