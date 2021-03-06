
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

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
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>${message}</div>
		</div>
	</c:if>
	
	<div class="page-header">
		<ol class="breadcrumb">
			<li class=""><a href="${raiz}">Home</a></li>
			<li class=""><a href="${raiz}">Perfil</a></li>
			<li class="active"><a href="${raiz}perfil/salvar">Cadastrar Perfil</a></li>
		</ol>
	</div>
	
	
	<form:form class="form-horizontal" role="form"
		action="${raiz}perfil/salvar" id="id-form" method="POST"
		modelAttribute="perfil">
		<tiles:insertAttribute name="camposForm" />
	</form:form>
</div>
