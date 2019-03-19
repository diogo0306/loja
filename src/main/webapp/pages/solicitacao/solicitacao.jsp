<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>

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
								class="text-semibold">Aprovações</span> - Solicitações
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Aprovações</li>
						<li><a href="${raiz}solicitacoes"> Solicitações</a></li>

					</ul>
				</div>
			</div>


			<div class="content">

				<div class="panel panel-flat">
					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}solicitacoes" modelAttribute="solicitacao">

							<div class="row">
								<div class="col-xs-1">
									<label for="field_1">Nome</label>
								</div>
								<div class="col-xs-3">
									<form:input type="text" class="form-control" id="inputNome"
										placeholder="Digite o nome" path="nome" />
								</div>

								<div class="col-xs-1">
									<label for="field_1">CPF/CNPJ</label>
								</div>
								<div class="col-xs-3">
									<form:input type="text" class="form-control" id="inputCp"
										placeholder="Digite o CPF/CNPJ" path="cpf" />
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-xs-1">
									<label for="field_1">Data</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
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
							<br />
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
								<th width="25%">Nome</th>
								<th width="15%">CPF/CNPJ</th>
								<th width="15%">Data</th>
								<th width="15%">Tipo</th>
								<th width="15%">Status</th>
								<th width="10%">Visualizar</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="sol" items="${solicitacoes}">
							
								<c:if test="${sol.status eq 'INCOMPLETO'}">
									<c:url value="bg-blue" var="classe" />
								</c:if>
								
								<c:if test="${sol.status eq 'APROVADO'}">
									<c:url value="bg-success" var="classe" />
								</c:if>
								
								<c:if test="${sol.status eq 'REPROVADO'}">
									<c:url value="bg-danger" var="classe" />
								</c:if>
							
								<tr>
									<td><c:out value="${sol.nome}" /></td>
									<td><c:out value="${sol.cpf}" /></td>
									<td><c:out value="${sol.dataFormatada}" /></td>
									<td><c:out value="${sol.tipo}" /></td>
									<td><span class="label ${classe}"><c:out value="${sol.status}" /></span></td>
									<td><a href="${raiz}solicitacao/visualizar/${sol.id}"
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