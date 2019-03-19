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
								class="text-semibold">Financeiro</span> - Contratos Ativos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Financeiro</li>
						<li><a href="${raiz}pagamentos">Contratos Ativos</a></li>

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
							action="${raiz}contratosAtivos" modelAttribute="contrato">

							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">Dia de Vencimento:</label>
								</div>
								<div class="col-xs-4">
									<form:select id="selectDiaVencimento" path="representante.id"
										class="form-control" cssErrorClass="field_error form-control">
										<form:option value="" label="Selecione" />
										<form:options items="${representantes}" itemLabel="nome"
											itemValue="id" />
									</form:select>
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
								<th width="15%">Número do Contrato</th>
								<th width="40%">Nome do Titular</th>
								<th width="15%">Vencimento</th>
								<th width="15%">Valor</th>
								<th width="15%">Situação</th>
							</tr>
						</thead>
						<tbody style="font-size: 12px;">
							<c:forEach var="contrato" items="${contratos}">
								<tr>
									<td><c:out value="${contrato.numero}" /></td>

									<c:if
										test="${contrato.tipoPessoaContratoEnum.descricao eq 'Pessoa Física'}">
										<td><c:out value="${contrato.paciente.nome}" /></td>
									</c:if>

									<c:if
										test="${contrato.tipoPessoaContratoEnum.descricao eq 'Pessoa Jurídica'}">
										<td><c:out value="${contrato.empresa.nome}" /></td>
									</c:if>

									<td><c:out value="${contrato.diaVencimentoEnum.descricao}" /></td>
									<td><c:out value="${contrato.valorContratoTransiente}" /></td>
									<td><c:out value="${contrato.situacaoEnum.descricao}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="panel panel-flat">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-6">
								<label for="inputDescricao">Valor Total: ${valorTotal}</label>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>