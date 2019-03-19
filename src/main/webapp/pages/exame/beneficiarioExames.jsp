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
								class="text-semibold">Exames - Procedimentos</span> -
							Beneficiário - Exames
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Exames - Procedimentos</li>
						<li><a href="${raiz}beneficiarioExames"> Beneficiário -
								Exames</a></li>

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
							action="${raiz}beneficiarioExames"
							modelAttribute="beneficiarioExame">

							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">Beneficiário</label>
								</div>
								<div class="col-xs-5">
									<form:input type="text" class="form-control"
										id="inputDescricao" placeholder="Digite o nome"
										path="paciente.nome" />
								</div>
								<div class="col-xs-1">
									<label for="inputDescricao">Credenciado</label>
								</div>
								<div class="col-xs-5">
									<form:input type="text" class="form-control"
										id="inputDescricao" placeholder="Digite o nome"
										path="credenciado.nome" />
								</div>
							</div>
							<br />

							<div class="row">
								<div class="col-xs-1">
									<label for="field_1">Data</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control" id="inputData"
										placeholder="Digite a data de Cadastro" path="dataFormatada" />
								</div>
								<div class="col-xs-1">
									<label for="field_1">Código</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										placeholder="Digite o código" path="codigo" />
								</div>
								<div class="col-xs-1">
									<label for="field_1">Status</label>
								</div>
								<div class="col-xs-2">
									<form:select path="tipoTransiente" class="form-control"
										cssErrorClass="field_error form-control has-errors">
										<form:option value="" label="Selecione" />
										<form:options items="${status}" itemLabel="descricao"
											itemValue="codigo" />
									</form:select>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}beneficiarioExame/salvar"
										class="btn btn-primary abrirJanela">Incluir</a>
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
								<th width="15%">Credenciado</th>
								<th width="10%">Código</th>
								<th width="10%">Status</th>
								<th width="12%">Data</th>
								<th width="9%">Ações</th>
							</tr>
						</thead>
						<tbody style="font-size: 12px;">
							<c:forEach var="beneficiario" items="${beneficiarioExames}">
								<tr>
									<td><c:out value="${beneficiario.paciente.nome}" /></td>
									<td><c:out
											value="${beneficiario.paciente.documentacao.cpf}" /></td>
									<td><c:out value="${beneficiario.credenciado.nome}" /></td>
									<td><c:out value="${beneficiario.codigo}" /></td>
									<td><c:out value="${beneficiario.status.descricao}" /></td>
									<td><c:out value="${beneficiario.dataFormatada}" /></td>
									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a
														href="${raiz}beneficiarioExame/visualizar/${beneficiario.id}"><i
															class="glyphicon glyphicon-search"></i> Detalhar</a></li>

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


<script type="text/javascript"
	src="<c:url value="/resources/js/exame/beneficiario_exame.js" />"></script>