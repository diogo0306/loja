<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}beneficiarioExame/emitir-comprovante"
	id="url-emitir" />

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
								class="text-semibold">Exames - Procedimentos</span> Beneficiário
							- Exames - Detalhar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Exames - Procedimentos</li>
						<li><a href="${raiz}beneficiarioExames">Beneficiário -
								Exames</a></li>
						<li class="active">Detalhar</li>

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

					<div class="panel-body">
						<form:form class="form-horizontal" role="form"
							action="${raiz}beneficiarioExame/confirmar"
							id="formularioBeneficiario" method="POST"
							modelAttribute="beneficiarioExame">
							<fieldset class="content-group">
								<legend class="text-bold">Dados</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Código:</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" path="codigo"
												cssErrorClass="field_error form-control" />
											<form:input path="id" type="hidden" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Beneficiário:</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" path="paciente.nome"
												cssErrorClass="field_error form-control" />
											<form:input path="id" type="hidden" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Credenciado:</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" path="credenciado.nome"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Valor Total:</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" path="valorTotalFormatado"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Detalhamento</legend>
								
								<br />
								<div class="form-inline">
									<c:if
										test="${beneficiarioExame.status.descricao == 'Pendente'}">

										<button class="btn btn-primary" style="width: 10%; margin-left: 3%">
											Confirmar Pagamento</button>

									</c:if>
									<c:if
										test="${beneficiarioExame.status.descricao == 'Pago'}">

										<button class="btn btn-primary" id="btn-emitir-comprovante" formtarget="_blank" style="width: 10%; margin-left: 3%">
											Emitir Comprovante</button>

									</c:if>
								</div>
								<br />

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Tabela/Exames</legend>

								<table
									class="table table-striped table-bordered table-hover datatable-highlight">
									<thead>
										<tr>
											<th width="10%">Tabela</th>
											<th width="10%">Exame</th>
											<th width="10%">Valor</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="beneficiario"
											items="${beneficiarioExame.listaTabelaExame}">
											<tr>
												<td><c:out value="${beneficiario.tabela.nome}" /></td>
												<td><c:out value="${beneficiario.exame.nome}" /></td>
												<td><c:out value="${beneficiario.valorTransiente}" /></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>

							</fieldset>

							<div class="text-right">
								<a href="${raiz}beneficiarioExames"
									class=" btn bg-danger abrirJanela">Voltar</a>
							</div>
						</form:form>
					</div>
				</div>
			</div>

		</div>
	</div>

</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/exame/beneficiario_exame.js" />"></script>
