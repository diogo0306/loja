<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">


	<div class="page-header">
		<div class="btn-group btn-breadcrumb">
			<a href="#" class="btn btn-default"><i
				class="glyphicon glyphicon-home"></i></a> <a href="#"
				class="btn btn-default">usuários</a> <a href="#"
				class="btn btn-default">visualizar usuário</a>
		</div>
	</div>

	<form:form class="form-horizontal" role="form"
		action="${raiz}usuario/alterar" id="id-form" method="POST"
		modelAttribute="usuario">


		<c:url value="/" var="raiz" />

		<form:input path="id" type="hidden" />
		<form:input path="alterarSenha" type="hidden" />
		<div class="panel panel-default">
			<div class="panel-heading">
				<h6 class="panel-title">Dados do Usuário</h6>
			</div>
			<div class="divSeparador2">
			<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="nome" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="field_1">Nome</label>
					</div>
					<div class="col-xs-3">
						<form:input type="text" cssClass="form-control" id="nome"
							disabled="true" placeholder="Digite o Nome" path="nome"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="login" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="field_1">Login</label>
					</div>
					<div class="col-xs-3">
						<form:input type="text" cssClass="form-control" id="login"
							disabled="true" placeholder="Digite o login" path="login"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="senha" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="telefone" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="field_1">Telefone</label>
					</div>
					<div class="col-xs-3">
						<form:input type="text" disabled="true" cssClass="form-control"
							id="telefoneUsuario" placeholder="Digite o telefone"
							path="telefone" cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="cpf" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="field_1">CPF</label>
					</div>
					<div class="col-xs-3">
						<form:input type="text" disabled="true" cssClass="form-control"
							id="cpfUsuario" placeholder="Digite o cpf" path="cpf"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="rg" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="field_1">RG</label>
					</div>
					<div class="col-xs-3">
						<form:input type="text" disabled="true" cssClass="form-control"
							id="rg" placeholder="Digite o RG" path="rg"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="email" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="field_1">Email</label>
					</div>
					<div class="col-xs-3">
						<form:input type="text" disabled="true" cssClass="form-control"
							id="email" placeholder="Digite o email" path="email"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="perfil.id" cssClass="text-danger" />
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col-xs-1">
						<label for="inputCargo">Perfil</label>
					</div>
					<div class="col-xs-4">
						<form:select id="inputCargo" path="perfil.id" class="form-control" disabled="true"
							cssErrorClass="field_error form-control">
							<form:option value="0">Selecione um Perfil</form:option>
							<form:options items="${perfis}" itemValue="id" itemLabel="descricao" />
						</form:select>
					</div>
				</div>
			</div>


		</div>
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<a href="${raiz}usuarios" class="btn btn-danger abrirJanela">Cancelar</a>
			</div>
		</div>

		<script type="text/javascript"
			src="<c:url value="/resources/js/usuario/usuario.js" />"></script>


	</form:form>
</div>
