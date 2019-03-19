<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/" var="raiz" />

<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

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

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Unidades - Alterar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}clientes"> Unidades</a></li>
						<li class="active">Alterar</li>

					</ul>
				</div>
			</div>


			<div class="content">
				<div class="panel panel-flat">
					<div class="panel-heading">
						<div class="heading-elements">
							<ul class="icons-list">
								<li><a data-action="collapse"></a></li>
								<li><a data-action="reload"></a></li>
							</ul>
						</div>
						<a class="heading-elements-toggle"><i class="icon-menu"></i></a>
					</div>
					<form:form class="form-horizontal" role="form"
						action="${raiz}cliente/alterar" id="id-form" method="POST"
						modelAttribute="cliente">
						<tiles:insertAttribute name="camposForm" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
