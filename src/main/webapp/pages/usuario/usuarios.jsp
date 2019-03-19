<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
	<c:if test="${messageError != null}">
		<div class="row">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>${messageError}</div>
		</div>
	</c:if>
	<c:if test="${message != null}">
		<div class="row">
			<div class="alert alert-info">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>${message}</div>
		</div>
	</c:if>


	<div class="page-header">
		<ol class="breadcrumb">
			<li class=""><a href="${raiz}">Home</a></li>
			<li class=""><a href="${raiz}">Usuário</a></li>
			<li class="active"><a href="${raiz}usuarios">Pesquisar Usuários</a></li>
		</ol>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h5
				style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
				<i class="fa fa-user"></i> &nbsp;&nbsp; Usuários - Gestão
			</h5>
		</div>

		<div class="divSeparador2">
			<form:form method="POST" class="form-horizontal" role="form"
				action="${raiz}usuarios" modelAttribute="usuario">

				<div class="row">
					<div class="col-xs-1">
						<label for="inputDescricao">Login</label>
					</div>
					<div class="col-xs-6">
						<form:input type="text" class="form-control" id="inputDescricao"
							placeholder="Digite o login" path="login" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
						&nbsp;
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div id="divList">
		<tiles:insertAttribute name="list" />
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/usuario/usuario.js" />"></script>
