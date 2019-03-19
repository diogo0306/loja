<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

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
								class="text-semibold">Agendamentos</span> - Beneficiário -
							Alterar Agendamento
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Agendamento</li>
						<li><a href="${raiz}agendamentos">Beneficiários</a></li>
						<li class="active">Alterar Agendamento</li>

					</ul>
				</div>
			</div>

			<form:form class="form-horizontal" role="form"
				action="${raiz}agendamento/alterar" id="id-form" method="POST"
				modelAttribute="agendamento">
				<tiles:insertAttribute name="camposForm" />
			</form:form>

		</div>
	</div>

</div>
