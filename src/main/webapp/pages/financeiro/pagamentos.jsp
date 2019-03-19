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
								class="text-semibold">Financeiro</span> - Pagamentos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Financeiro</li>
						<li><a href="${raiz}pagamentos">Pagamentos</a></li>

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

				<div class="panel panel-flat">
					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}pagamentos" modelAttribute="consulta">

							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">Médico:</label>
								</div>
								<div class="col-xs-4">
									<form:select path="medico.id" class="form-control"
										cssErrorClass="field_error form-control">
										<form:option value="" label="Selecione" />
										<form:options items="${medicos}" itemLabel="nome"
											itemValue="id" />
									</form:select>
								</div>
								<div class="col-xs-1">
									<label for="inputDescricao">Mês:</label>
								</div>
								<div class="col-xs-2">
									<form:input type="number" min="1" max="12" required="true" class="form-control" placeholder="Mês"
										path="mes" />
								</div>
								<div class="col-xs-1">
									<label for="inputDescricao">Ano:</label>
								</div>
								<div class="col-xs-2">
									<form:input type="number" required="true" class="form-control" placeholder="Ano"
										path="ano" />
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
								<th width="15%">Beneficiário</th>
								<th width="15%">CPF do Beneficiário</th>
								<th width="15%">Médico</th>
								<th width="10%">Status</th>
								<th width="10%">Valor</th>
								<th width="12%">Data</th>
							</tr>
						</thead>
						<tbody style="font-size: 12px;">
							<c:forEach var="consulta" items="${consultas}">
								<tr>
									<td><c:out value="${consulta.paciente.nome}" /></td>
									<td><c:out value="${consulta.paciente.documentacao.cpf}" /></td>
									<td><c:out value="${consulta.medico.nome}" /></td>
									<td><c:out value="${consulta.status.descricao}" /></td>
									<td><c:out value="${consulta.valorFormatado}" /></td>
									<td><c:out value="${consulta.dataFormatada}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</div>
