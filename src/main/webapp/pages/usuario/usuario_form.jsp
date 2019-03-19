<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<form:input path="id" type="hidden" />
<form:input path="alterarSenha" type="hidden" />
<div class="panel panel-default">
	<div class="panel-heading">
		<h5
			style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
			<i class="fa fa-user"></i> &nbsp;&nbsp; Usuários - Dados do Usuário
		</h5>
	</div>
	<div class="divSeparador2">
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="nome" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">Nome*</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="nome"
					placeholder="Digite o Nome" path="nome"
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
				<label for="field_1">Login*</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="login"
					placeholder="Digite o login" path="login"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">Senha*</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="senha"
					placeholder="Digite a senha" path="senha"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
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
				<form:input type="text" cssClass="form-control" id="telefoneUsuario"
					placeholder="Digite o telefone" path="telefone"
					cssErrorClass="field_error form-control" />
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
				<form:input type="text" cssClass="form-control" id="cpfUsuario"
					placeholder="Digite o cpf" path="cpf"
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
				<form:input type="text" cssClass="form-control" id="rg"
					placeholder="Digite o RG" path="rg"
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
				<form:input type="text" cssClass="form-control" id="email"
					placeholder="Digite o email" path="email"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="perfil.id" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="inputCargo">Perfil*</label>
			</div>
			<div class="col-xs-4">
				<form:select id="inputCargo" path="perfil.id" class="form-control"
					cssErrorClass="field_error form-control">
					<form:option value="">Selecione um Perfil</form:option>
					<form:options items="${perfis}" itemValue="id"
						itemLabel="descricao" />
				</form:select>

				


			</div>
			&nbsp; <a href="#" data-toggle="modal" class="btn btn-success"
						data-target="#modalCadastrarPerfil"
						title="Cadastrar Perfil"> <span class="glyphicon glyphicon-plus"></span> </a>
		</div>
	</div>


</div>

<div class="row">
	<div class="col-xs-offset-1 col-xs-12" style="margin-left: 0%">
		<c:if test="${alterarTela == null}">
			<button class="btn btn-success abrirJanela" style="width: 100%;">Salvar</button>
		</c:if>
		<c:if test="${alterarTela != null}">
			<button class="btn btn-success abrirJanela">Alterar</button>
			<a href="${raiz}usuarios" class="btn btn-danger abrirJanela">Voltar</a>
	</c:if>
	</div>
	<br />
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/usuario/usuario.js" />"></script>
