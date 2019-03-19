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
				class="btn btn-default">exames</a>
				 <a href="#"
				class="btn btn-default">visualizar exame</a>
		</div>
	</div>

	<form:form class="form-horizontal" role="form"
		action="${raiz}exame/alterar" id="id-form" method="POST"
		modelAttribute="exame">

		<div class="panel panel-default">

			<div class="panel-heading">
				<h6 class="panel-title">Dados do Exame</h6>
			</div>


			<c:url value="/" var="raiz" />

			<form:input path="id" type="hidden" />
			<div class="divSeparador2">
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="nome" cssClass="text-danger" />
					</div>
				</div>
				<c:if test="${campos_obrigatorios != null}">
					<div class="row">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert" 
								aria-hidden="true">&times;</button>${campos_obrigatorios}</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col-xs-1">
						<label for="inputNome">Nome*</label>
					</div>
					<div class="col-xs-6">
						<form:input type="text" class="form-control" id="inputNome" disabled="true"
							placeholder="Digite o nome" path="nome"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-1">
						<label for="inputNome">Valor</label>
					</div>
					<div class="col-xs-4">
						<form:input type="text" class="form-control" id="inputValor" disabled="true"
							placeholder="Digite o Valor" path="valor"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-1">
						<label for="inputNome">Duração(dias)</label>
					</div>
					<div class="col-xs-2">
						<form:input type="text" class="form-control" id="inputNome" disabled="true"
							placeholder="Digite a duração" path="diasDuracao"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<a href="${raiz}exames" class="btn btn-danger abrirJanela">Cancelar</a>
			</div>
		</div>

		<script type="text/javascript"
			src="<c:url value="/resources/js/exame/exame.js" />"></script>
	</form:form>
</div>
