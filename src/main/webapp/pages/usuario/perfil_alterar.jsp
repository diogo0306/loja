<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	
	<div class="page-header">
		<div class="btn-group btn-breadcrumb">
			<a href="#" class="btn btn-default"><i
				class="glyphicon glyphicon-home"></i></a> <a href="#"
				class="btn btn-default">perfis</a>
				<a href="#"
				class="btn btn-default">alterar perfil</a>
		</div>
	</div>
	
	
	<form:form class="form-horizontal" role="form" action="${raiz}perfil/alterar"
		id="id-form" method="POST" modelAttribute="perfil">
		<tiles:insertAttribute name="camposForm" />
	</form:form>
</div>
