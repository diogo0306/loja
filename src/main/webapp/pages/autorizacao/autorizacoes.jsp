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
								class="text-semibold">Autorização</span> - Autorizações
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Autorização</li>
						<li><a href="${raiz}autorizacoes">Autorizações</a></li>

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
							action="${raiz}autorizacoes" modelAttribute="autorizacao">

							<div class="form-group">
								<div class="col-xs-1">
									<label for="inputDescricao">Credenciado:</label>
								</div>
								<div class="col-xs-3">
									<form:select path="credenciado.id" class="form-control"
										cssErrorClass="field_error form-control">
										<form:option value="" label="Selecione" />
										<form:options items="${credenciados}" itemLabel="nome"
											itemValue="id" />
									</form:select>
								</div>
								<div class="col-xs-1">
									<label for="field_1">Número</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										placeholder="Digite o número" path="numeroAutorizacao" />
								</div>
							</div>
							<br />
							<div class="form-group">
								<div class="col-xs-1">
									<label for="field_3">Tipo</label>
								</div>
								<div class="col-xs-2">
									<form:select id="#" path="codicoTipoTransiente"
										class="form-control" cssErrorClass="field_error form-control">
										<form:option value="" label="Selecione" />
										<form:options items="${tipos}" itemLabel="descricao"
											itemValue="codigo" />
									</form:select>
								</div>
								<div class="col-xs-1">
									<label for="field_1">Data da Autorização</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										id="inputDataAutorizacao" placeholder="Digite a Data"
										path="dataAutorizacao" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}autorizacao/incluir"
										class="btn btn-primary abrirJanela">Incluir</a>
								</div>
							</div>
						</form:form>
					</div>

					<!-- Highlighting rows and columns -->

					<table class="table table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="10%">Tipo</th>
								<th width="10%">Situação</th>
								<th width="15%">Beneficiário</th>
								<th width="15%">Credenciado</th>
								<th width="10%">Número</th>
								<th width="10%">Data</th>
								<th width="10%">Valor</th>
								<th width="12%">Status</th>
								<th width="12%">Ações</th>
							</tr>
						</thead>
						<tbody style="font-size: 12px;">
							<c:forEach var="autorizacao" items="${autorizacoes}">
								<tr>
									<td><c:out value="${autorizacao.tipo.descricao}" /></td>
									<td><c:out value="${autorizacao.situacao.descricao}" /></td>
									<td><c:out value="${autorizacao.paciente.nome}" /></td>
									<td><c:out value="${autorizacao.credenciado.nome}" /></td>
									<td><c:out value="${autorizacao.numeroAutorizacao}" /></td>
									<td><c:out value="${autorizacao.dataFormatada}" /></td>
									<td><c:out value="${autorizacao.valor}" /></td>
									<td><c:out value="${autorizacao.status}" /></td>
									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a
														href="${raiz}autorizacao/detalhar/${autorizacao.id}"><i
															class="glyphicon glyphicon-usd"></i> Detalhar</a></li>

													<li><a href="#" class="glyphicon glyphicon-search"
														data-toggle="modal" data-target="#modalExemplo">
															Autorizar</a></li>
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

<div class="modal fade" id="modalExemplo" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Autorizações</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Fechar">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer">

				<button type="button" class="btn btn-primary">Autorizar</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>

			</div>
		</div>
	</div>
</div>


<script type="text/javascript"
	src="<c:url value="/resources/js/autorizacao/autorizacao.js" />"></script>
