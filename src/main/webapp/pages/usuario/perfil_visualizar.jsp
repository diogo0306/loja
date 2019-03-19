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
		<ol class="breadcrumb">
			<li class=""><a href="">Home</a></li>
			<li class=""><a href="">Perfil</a></li>
			<li class=""><a href="">Visualizar Perfil</a></li>
		</ol>
	</div>


	<form:form class="form-horizontal" role="form"
		action="${raiz}perfil/alterar" id="id-form" method="POST"
		modelAttribute="perfil">


		<c:url value="/" var="raiz" />

		<form:input path="id" type="hidden" />
		<div class="panel panel-default">
			<div class="panel-heading">
				<h6 class="panel-title">Dados do Perfil</h6>
			</div>
			<div class="divSeparador2">
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="descricao" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="field_1">Descrição</label>
					</div>
					<div class="col-xs-3">
						<form:input type="text" cssClass="form-control" id="descricao"
							disabled="true" placeholder="Digite a descrição" path="descricao"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
			</div>


		</div>
		<div class="form-inline">
			<a href="${raiz}perfis" class="btn btn-danger abrirJanela"
				style="width: 100%;">Voltar</a>
		</div>
		<br />
		<br />

		<script type="text/javascript"
			src="<c:url value="/resources/js/usuario/perfil.js" />"></script>


	</form:form>
</div>
