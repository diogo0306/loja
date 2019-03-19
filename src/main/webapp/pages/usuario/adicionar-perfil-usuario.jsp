
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript"
	src="<c:url value="/resources/js/usuario/perfil.js" />"></script>

<c:url value="/" var="raiz" />
<c:url var="urlBase" value="/..${raiz}perfil/" />
<input type="hidden" value="${urlBase}adicionar-perfil"
	id="url-adicionar-perfil" />
<input type="hidden" value="${urlBase}excluir-perfil"
	id="url-remover-perfil" />
<input type="hidden" value="${urlBase}salvar" id="url-salvar" />
<!-- Begin page content -->
<div class="container">
	<div class="page-header">
		<ol class="breadcrumb">
			<li class=""><a href="">Home</a></li>
			<li class=""><a href="">Perfil</a></li>
			<li class=""><a href="">Incluir Perfil</a></li>
		</ol>
	</div>
	<form:form class="form-horizontal" role="form"
		action="${raiz}perfil/salvar" id="formularioPerfil" method="POST"
		modelAttribute="funcionalidadeOperacao">
		<tiles:insertAttribute name="camposForm" />
	</form:form>
</div>
