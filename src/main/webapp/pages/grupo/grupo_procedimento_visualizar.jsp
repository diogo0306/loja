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

				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Grupos/Procedimentos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}grupos">Grupos/Procedimentos</a></li>
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
							action="${raiz}grupoProcedimento/salvar"
							id="formularioGrupoProcedimento" method="POST"
							modelAttribute="grupoProcedimento">
							<fieldset class="content-group">
								<legend class="text-bold">Grupos/Procedimentos -
									Detalhar</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Código</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:input type="number" class="form-control" id="inputNome"
												disabled="true" path="codigo"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Nome</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" path="nome"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Tipo de
										Atendimento</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" path="tipoAtendimento.descricao"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 16.5%; margin-top: 6px;">
										<form:checkbox id="pagamentoBalcao" path="imprimir"
											disabled="true" name="imprimirCarteirinha"
											label="${imprimir}" value="${imprimir}" />
									</div>

									<label for="pagamentoBalcao" class="col-sm-4 control-label"
										style="text-align: left;">Imprimir na carteirinha.</label>

								</div>
							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Procedimentos</legend>

								<table
									class="table table-striped table-bordered table-hover datatable-highlight">
									<thead>
										<tr>
											<th width="5%">Codigo</th>
											<th width="30%">Nome</th>
											<th width="10%">Valor Pagamento</th>
											<th width="10%">Valor Cobrança</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="procedimento" items="${grupoProcedimento.procedimentos}">
											<tr>
												<td><c:out value="${procedimento.codigo}" /></td>
												<td><c:out value="${procedimento.nome}" /></td>
												<td><c:out value="R$ ${procedimento.valorPagamento} " /></td>
												<td><c:out value="R$ ${procedimento.valorCobranca}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</fieldset>

							<div class="text-right">
								<a href="${raiz}grupos" class=" btn bg-danger abrirJanela">Voltar</a>
							</div>
						</form:form>
					</div>
				</div>
			</div>

		</div>
	</div>

</div>

