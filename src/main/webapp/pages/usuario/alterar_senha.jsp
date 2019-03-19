<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
	<div class="page-header">
		<h3>Usuário - Alterar Senha</h3>
	</div>

	<form:form class="form-horizontal" role="form"
		action="${raiz}alterar_senha/salvar" id="id-form" method="POST"
		modelAttribute="otdAlterarSenha">
		
		<form:input path="idUsuario" type="hidden" />
		
		<c:if test="${messageError != null}">
			<div class="row">
				<div class="text-danger col-xs-offset-1">${messageError}</div>
			</div>
		</c:if>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h6 class="panel-title">Dados da nova senha</h6>
			</div>
			<div class="divSeparador2">
				<div class="row">
					<div class="col-xs-2">
						<label for="field_1">Senha Atual</label>
					</div>
					<div class="col-xs-3">
						<form:input type="password" cssClass="form-control"
							placeholder="Digite a senha atual" path="senhaAtual"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-2">
						<label for="field_1">Nova Senha</label>
					</div>
					<div class="col-xs-3">
						<form:input type="password" cssClass="form-control" 
							placeholder="Digite a nova senha" path="senhaNova"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-2">
						<label for="field_1">Confirme Nova Senha</label>
					</div>
					<div class="col-xs-3">
						<form:input type="password" cssClass="form-control" 
							placeholder="Digite a nova senha" path="confirmacaoSenhaNova"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-offset-2 col-xs-6">
				<button class="btn btn-success">Salvar</button>
			</div>
		</div>
	</form:form>
</div>
