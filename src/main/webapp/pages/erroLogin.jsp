
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!-- Begin page content -->
<div class="container">
	<div class="page-header">
		<h1>Enertec</h1>
	</div>


	<p class="lead">Usuário inválido tente novamente</p>
	
	<c:url var="urlForm" value="/login" />

	<form:form method="POST" class="form-horizontal" role="form"
		action="${urlForm}" modelAttribute="usuario">
		<div class="form-group">
			<div class="form-group">
				<label for="inputMes" class="col-lg-1 control-label">Login</label>
				<div class="col-lg-2">
					<form:input type="text" class="form-control" id="inputLogin"
						placeholder="Digite o Login" name="login" path="login"/>
				</div>
			</div>
			<div class="form-group">
				<label for="inputAno" class="col-lg-1 control-label">Senha</label>
				<div class="col-lg-2">
					<form:input type="password" class="form-control" id="inputSenha"
						placeholder="Digite a Senha" name="senha" path="senha"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-1 control-label"></label>
				<div class="col-lg-6">
					<button type="submit" class="btn btn-success"
						id="btnPesquisarPessoaPorNome">Login</button>
					&nbsp; <a href="#" id="btn-incluir" class="btn btn-primary">Cancelar</a>
				</div>
			</div>
		</div>
	</form:form>
</div>




