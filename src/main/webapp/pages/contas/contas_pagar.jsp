<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - CONTAS A PAGAR
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}contas_pagar"> Contas a pagar</a></li>

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

					<div class="panel-heading">
						<h5 class="panel-title">Consultar Contas</h5>
						<div class="heading-elements">
							<ul class="icons-list">
								<li><a data-action="collapse"></a></li>
								<li><a data-action="reload"></a></li>
							</ul>
						</div>
						<a class="heading-elements-toggle"><i class="icon-menu"></i></a>
					</div>


					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}contas_pagar" modelAttribute="contasPagar">

							<div class="form-group">
								<div class="col-xs-1">
									<label for="field_1">Data de Pagamento</label>
								</div>
								<div class="col-xs-3">
									<form:input type="text" class="form-control"
										id="inputDataPagamento" placeholder="Digite a Data"
										path="dataPagamento" />
								</div>
								<div class="col-xs-1">
									<label for="field_3">Tipo</label>
								</div>
								<div class="col-xs-3">
									<form:select id="#" path="codigoTipoTransiente"
										class="form-control" cssErrorClass="field_error form-control">
										<form:option value="" label="Selecione" />
										<form:options items="${tipos}" itemLabel="descricao"
											itemValue="codigo" />
									</form:select>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}contasPagar/salvar"
										class="btn btn-primary abrirJanela">Incluir Conta</a>
								</div>
							</div>
						</form:form>
					</div>

					<table
						class="table table-striped table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th>Tipo</th>
								<th>Data de Pagamento</th>
								<th>Valor Cobrado</th>
								<th class="text-center">Ações</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="conta" items="${contas}">
								<tr>
									<td><c:out value="${conta.tipo.descricao}" /></td>
									<td><c:out value="${conta.dataFormatada}" /></td>
									<td><c:out value="${conta.valor}" /></td>

									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a href="#"><i
															class="glyphicon glyphicon-search"></i> A definir</a></li>

													<li><a href="#"><i
															class="glyphicon glyphicon-pencil"></i> A definir</a></li>
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
	src="<c:url value="/resources/js/contas_receber/contas_receber.js" />"></script>
