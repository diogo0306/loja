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
	
	<nav class="navbar navbar-default navbar-breadcrumbs"
		style="background-color: #EFEFEF !important; border: 0px !important;">
		<div class="container">
			<div class="navbar-header">
				<div class="btn-group btn-breadcrumb navbar-btn"
					style="margin-top: 14px;">
					<a href="#" style="color: #7B7B7B"><i
						class="glyphicon glyphicon-home"></i></a>&nbsp;&nbsp;<a href="#"
						style="color: #7B7B7B"><i
						class="glyphicon glyphicon-chevron-right"></i></a><a href="#"
						style="color: #7B7B7B">&nbsp;Relatório Cadastro de Pacientes</a>
				</div>
			</div>
		</div>
	</nav>

	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h5
				style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
				Filtro Pesquisa
			</h5>

		</div>

		<div class="divSeparador2">

			<form:form method="POST" class="form-horizontal" role="form" target="_blank"
				action="${raiz}relatorio-pacientes/gerar" modelAttribute="paciente">

				<div class="row">
					<div class="col-xs-2">
						<label for="inputDescricao">Data inclusão inicial</label>
					</div>
					<div class="col-xs-2">
						<form:input type="text" class="form-control" id="inputDataNascimento"
							placeholder="Digite a data de inclusão inicial" path="dataInclusaoFormatada" />
					</div>
					<div class="col-xs-2">
						<label for="inputDescricao">Data inclusão final</label>
					</div>
					<div class="col-xs-2">
						<form:input type="text" class="form-control" id="inputDataNascimento2"
							placeholder="Digite a data de inclusão final" path="dataInclusaoFinalFormatada" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-2">
						<button type="submit" class="btn btn-success">Pesquisar</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/paciente/paciente.js" />"></script>
