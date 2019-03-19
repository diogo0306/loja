
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">


				<c:if test="${messageError != null}">
					<div class="row">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>${messageError}</div>
					</div>
				</c:if>

				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Exames - Procedimentos</span> - Tabela -
							Incluir Preço
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Exames - Procedimentos</li>
						<li><a href="${raiz}tabelas">Tabela</a></li>
						<li class="active">Incluir Preço</li>

					</ul>
				</div>
			</div>

			<form:form class="form-horizontal" role="form"
				action="${raiz}tabela_exame/salvar" id="formularioValor"
				method="POST" modelAttribute="exameDTO">
				<tiles:insertAttribute name="camposForm" />
			</form:form>

			<%@include file="modal_cadastrar_tabela_exame.jsp"%>
			<%@include file="modal_cadastrar_exame.jsp"%>

		</div>
	</div>

</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/exame/tabela_exame.js" />"></script>
