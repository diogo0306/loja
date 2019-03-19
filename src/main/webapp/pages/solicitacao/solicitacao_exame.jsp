<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Aprovações</span> - Exames
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Aprovações</li>
						<li><a href="${raiz}solicitacoes/exame"> Exames</a></li>

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
				
				<c:if test="${empty solicitacoes}">
					<div class="alert alert-info-diogo">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						ATENÇÃO! Esse exame não existe. Verifique a data digitada.
					</div>
				</c:if>

				<div class="panel panel-flat">
					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}exame" modelAttribute="solicitacaoExame">

							<div class="row">
								<div class="col-xs-1">
									<label for="field_1">Data</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control" required="true"
										id="dataSolicitacao" placeholder="Data" path="dataFormatada" />
								</div>

								<div class="col-xs-1 col-xs-offset-1">
									<label for="field_1">Status</label>
								</div>
								<div class="col-xs-3">
									<form:input type="text" class="form-control" id="status"
										placeholder="Status" path="status" />
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
								</div>
							</div>

						</form:form>
					</div>

					<!-- Highlighting rows and columns -->


					<table class="table table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="25%">Data</th>
								<th width="15%">Status</th>
								<th width="10%">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="sol" items="${solicitacoes}">
								<tr>
									<c:if test="${sol.status eq 'PENDENTE'}">
										<c:url value="bg-blue" var="classe" />
									</c:if>

									<c:if test="${sol.status eq 'APROVADO'}">
										<c:url value="bg-success" var="classe" />
									</c:if>

									<c:if test="${sol.status eq 'REPROVADO'}">
										<c:url value="bg-danger" var="classe" />
									</c:if>

									<td><c:out value="${sol.dataFormatada}" /></td>
									<td><span class="label ${classe}"><c:out value="${sol.status}" /></span></td>
									<td><a href="${raiz}exame/visualizar/${sol.id}"
										title="Detalhar"> <span class="glyphicon glyphicon-search"></span>
									</a>&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/solicitacao/solicitacao.js" />"></script>