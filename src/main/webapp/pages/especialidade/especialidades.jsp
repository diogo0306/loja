<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">



			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Especialidades
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="#"> Especialidades</a></li>

					</ul>
				</div>
			</div>


			<div class="content">

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

				<c:if test="${empty espe}">
					<div class="row">
						<div class="alert alert-info-diogo">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>
							ATENÇÃO! Especialidade não cadastrada.
						</div>
					</div>
				</c:if>

				<div class="panel panel-flat">
					<div class="panel-body">
						<form:form method="GET" class="form-horizontal" role="form"
							action="${raiz}especialidades" modelAttribute="especialidade">

							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<a href="${raiz}especialidade/salvar"
										class="btn btn-primary abrirJanela">Incluir</a>
									<a href="${raiz}credenciados"
										class="btn btn-primary abrirJanela">Credenciados</a>
								</div>
							</div>
						</form:form>
					</div>

					<table
						class="table table-striped table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="5%">Código</th>
								<th>Descrição</th>
								<th class="text-center">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="espe" items="${espe}">
								<tr>
									<td><c:out value="${espe.id}" /></td>
									<td><c:out value="${espe.especialidade}" /></td>
									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a
														href="${raiz}especialidade/visualizar/${espe.id}"><i
															class="glyphicon glyphicon-search"></i> Visualizar</a></li>

													<li><a href="${raiz}especialidade/alterar/${espe.id}"><i
															class="glyphicon glyphicon-pencil"></i> Alterar</a></li>
												</ul></li>
										</ul>
									</td>
								</tr>
							</c:forEach>


						</tbody>
					</table>


				</div>

			</div>
		</div>
	</div>
</div>