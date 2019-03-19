<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}beneficiarioExame/finalizar"
	id="url-salvar" />
<input type="hidden" value="${raiz}beneficiarioExame/recalcular_valor"
	id="url-valor" />

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
							- Exames - Incluir
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
						<li class="active">Incluir</li>

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
							action="${raiz}beneficiarioExame/salvar"
							id="formularioBeneficiario" method="POST"
							modelAttribute="beneficiarioDTO">

							<fieldset class="content-group">
								<legend class="text-bold">Beneficiário - Incluir Exames</legend>

								<div class="row">
									<label for="inputTabela" style="text-align: left"
										class="col-lg-1 control-label">Beneficiário*</label>
									<div class="col-lg-3">
										<form:select path="idPaciente" class="form-control"
											id="selectPaciente"
											cssErrorClass="field_error form-control has-errors">
											<form:option value="" label="Selecione" />
											<form:options items="${pacientes}" itemLabel="nome"
												itemValue="id" />
											<form:input path="idPaciente" type="hidden" />
										</form:select>
										<form:errors path="idPaciente" cssClass="text-danger"
											class="has-error" />
									</div>
								</div>
								<br />
								<div class="row">
									<label for="inputTabela" style="text-align: left"
										class="col-lg-1 control-label">Credenciado*</label>
									<div class="col-lg-3">
										<form:select path="idCredenciado" class="form-control"
											id="selectCredenciado"
											cssErrorClass="field_error form-control has-errors">
											<form:option value="" label="Selecione" />
											<form:options items="${credenciados}" itemLabel="nome"
												itemValue="id" />
											<form:input path="idCredenciado" type="hidden" />
										</form:select>
										<form:errors path="idCredenciado" cssClass="text-danger"
											class="has-error" />
									</div>
								</div>
								<br />
								<div class="row">
									<label for="inputTabela" style="text-align: left"
										class="col-lg-1 control-label">Tabela*</label>
									<div class="col-lg-3">
										<form:select path="tabela.id" class="form-control"
											id="selectTabela" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${tabelas}" itemLabel="nome"
												itemValue="id" />
											<form:input path="tabela.id" type="hidden" />
											<form:input path="tabela.nome" type="hidden" />
										</form:select>
									</div>
								</div>
								<br />
								<div class="row">
									<label for="inputExame" style="text-align: left"
										class="col-lg-1 control-label">Exames*</label>
									<div class="col-lg-3">
										<form:select path="exame.id" class="form-control"
											id="selectExame" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${exames}" itemLabel="nome"
												itemValue="id" />
											<form:input path="exame.id" type="hidden" />
											<form:input path="exame.nome" type="hidden" />
										</form:select>
									</div>
								</div>
								<br /> <br />

								<div class="form-inline">
									<button class="btn btn-success abrirJanela" style="width: 7%;">
										Adicionar <i class=" icon-folder-plus4"></i>
									</button>
									<c:if test="${valorTotal ne null and valotTotal ne 0}">

										<button class="btn btn-success abrirJanela"
											id="btn-recalcular-valor" style="width: 9%;">
											Recalcular Valor</button>

									</c:if>
								</div>


								<br />

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Resumo</legend>

								<table class="table table-hover table-striped table-bordered "
									id="tabelaExames">
									<thead>
										<tr>
											<th width="10%">Tabela</th>
											<th width="10%">Exame</th>
											<th width="5%">Valor</th>
											<th width="5%">Ações</th>
										</tr>
									</thead>
									<tbody id="listaItens">
										<c:forEach var="exame"
											items="${beneficiarioDTO.listaTabelaExame}"
											varStatus="status">
											<tr>
												<td><c:out value="${exame.tabela.nome}" /></td>
												<td><c:out value="${exame.exame.nome}" /></td>
												<td><c:out value="${exame.valorTransiente}" /></td>
												<td><a class="glyphicon glyphicon-remove"
													id="btn-excluir_exame_lista"
													onclick="excluirLista($(this))"></a></td>

												<form:input
													path="listaTabelaExame[${status.index}].exame.nome"
													type="hidden" />
												<form:input
													path="listaTabelaExame[${status.index}].exame.id"
													type="hidden" />
												<form:input path="listaTabelaExame[${status.index}].id"
													type="hidden" />
												<form:input path="listaTabelaExame[${status.index}].valor"
													type="hidden" />
												<form:input
													path="listaTabelaExame[${status.index}].valorTransiente"
													type="hidden" />
												<form:input
													path="listaTabelaExame[${status.index}].tabela.nome"
													type="hidden" />
												<form:input
													path="listaTabelaExame[${status.index}].tabela.id"
													type="hidden" />
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</fieldset>

							<div class="form-group">
								<label class="control-label col-lg-2"><strong>VALOR
										TOTAL:</strong> ${valorTotal} </label>
							</div>

							<div class="text-right">
								<a href="${raiz}beneficiarioExames"
									class=" btn bg-danger abrirJanela">Cancelar</a>

								<button type="submit" class="btn btn-primary"
									id="btn-salvar_form">
									Salvar <i class="icon-arrow-right14 position-right"></i>
								</button>
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