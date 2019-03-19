<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
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
								class="text-semibold">Exames - Procedimentos</span> - Tabela -
							Visualizar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Exames - Procedimentos</li>
						<li><a href="${raiz}tabelas">Tabela</a></li>
						<li class="active">Visualizar Exames</li>

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
						<fieldset class="content-group">
							<legend class="text-bold">Tabela</legend>

							<div class="form-group">
								<label for="inputPcmso" class="col-lg-1 control-label">Nome:</label>
								<div class="col-lg-9">
									<form:input path="tabela.nome" class="form-control"
										readonly="true" disabled="true" id="inputNome"
										cssErrorClass="field_error form-control" />
								</div>
							</div>
							<br />

						</fieldset>

						<fieldset class="content-group">
							<legend class="text-bold">Exames</legend>

							<table
								class="table table-striped table-bordered table-hover datatable-highlight">
								<thead>
									<tr>
										<th width="10%">Nome</th>
										<th width="10%">Valor</th>
										<th width="10%">Descrição</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="exame" items="${tabela.listaExame}"
										varStatus="status">
										<tr>
											<td><c:out value="${exame.nome}" /></td>
											<td><c:out value="${exame.valorTransiente}" /></td>
											<td><c:out value="${exame.descricao}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</fieldset>

						<div class="text-right">
							<a href="${raiz}tabelas" class=" btn bg-danger abrirJanela">Voltar</a>
						</div>

					</div>
				</div>
			</div>



		</div>
	</div>

</div>

