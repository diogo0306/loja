<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}autorizacao/adicionar-exame"
	id="url-add" />
<input type="hidden" value="${raiz}autorizacao/gerar-orcamento"
	id="url-gerar" />

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
								class="text-semibold">Autorização</span> - Autorizações -
							Incluir
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Autorização</li>
						<li><a href="${raiz}autorizacao/incluir">Incluir</a></li>

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

				<form:form method="POST" class="form-horizontal" role="form"
					action="${raiz}autorizacao/salvar" modelAttribute="autorizacaoDTO"
					id="formularioAutorizacao">

					<form:input path="valorTotal" type="hidden" />

					<div class="content">
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
									<legend class="text-bold">Dados da Autorização</legend>

									<div class="form-group">
										<div class="col-xs-1">
											<label for="field_3">Tipo</label>
										</div>
										<div class="col-xs-3">
											<form:select id="tipoAutorizacao" required="true"
												path="autorizacao.codicoTipoTransiente" class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${tipos}" itemLabel="descricao"
													itemValue="codigo" />
											</form:select>
										</div>
										<div class="col-xs-1">
											<label for="field_1">Data da Autorização</label>
										</div>
										<div class="col-xs-2">
											<form:input type="text" class="form-control" required="true"
												id="inputDataAutorizacao" placeholder="Digite a Data"
												path="autorizacao.dataAutorizacao" />
										</div>
									</div>
								</fieldset>

								<fieldset class="content-group" id="divBeneficiario">
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Beneficiário:</label>
										</div>
										<div class="col-xs-3">
											<form:select path="autorizacao.paciente.id"
												class="form-control" required="true"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${pacientes}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Credenciado:</label>
										</div>
										<div class="col-xs-3">
											<form:select path="autorizacao.credenciado.id"
												required="true" class="form-control" id="selectCredenciado"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${credenciados}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
									<div class="form-group" id="divValorCredenciado">
										<div class="col-xs-1">
											<label for="inputDescricao">Valor Cobrado :</label>
										</div>
										<div class="col-xs-1">
											<input type="text" id="inputValorCredenciado"
												class="form-control" placeholder="R$" readonly="true" />
										</div>
										<div class="col-xs-1">
											<label for="inputDescricao">Valor Pago:</label>
										</div>
										<div class="col-xs-1">
											<form:input type="text" class="form-control js-decimal"
												placeholder="R$" path="autorizacao.valorPagoFormatado" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Nome do Solicitante:</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control"
												placeholder="Digite o nome do solicitante"
												path="autorizacao.nomeSolicitante" />
										</div>
									</div>
								</fieldset>

								<fieldset class="content-group" id="divExame">
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Exames/Proc.</label>
										</div>
										<div class="col-xs-3">
											<form:select path="exame.id" class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${exames}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
										<button type="submit" id="btn-add-exame"
											style="margin-top: 0.3%">
											<i class="fas fa-plus" aria-hidden="true"></i>
										</button>
									</div>
								</fieldset>

								<fieldset class="content-group" id="divHospital">
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Hospital:</label>
										</div>
										<div class="col-xs-3">
											<form:select path="autorizacao.hospital.id"
												class="form-control" id="selectHospital"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${hospitais}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Valor Cobrado
												Sala/Hospital:</label>
										</div>
										<div class="col-xs-1">
											<input type="text" id="inputValorSala" class="form-control"
												placeholder="R$" readonly="true" />
										</div>
										<div class="col-xs-1">
											<label for="inputDescricao">Valor Pago Sala/Hospital:</label>
										</div>
										<div class="col-xs-1">
											<form:input type="text" class="form-control js-decimal"
												placeholder="R$" path="autorizacao.valorPagoSalaFormatado" />
										</div>
									</div>
								</fieldset>

								<fieldset class="content-group" id="divFornecedor">
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Fornecedor:</label>
										</div>
										<div class="col-xs-3">
											<form:select path="autorizacao.fornecedor.id"
												class="form-control" id="selectFornecedor"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${fornecedores}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Valor Cobrado Material:</label>
										</div>
										<div class="col-xs-1">
											<input type="text" id="inputValorMaterial"
												class="form-control" placeholder="R$" readonly="true" />
										</div>
										<div class="col-xs-1">
											<label for="inputDescricao">Valor Pago Material:</label>
										</div>
										<div class="col-xs-1">
											<form:input type="text" class="form-control js-decimal"
												placeholder="R$"
												path="autorizacao.valorPagoMaterialFormatado" />
										</div>
									</div>
								</fieldset>

								<fieldset class="content-group">
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Orientações:</label>
										</div>
										<div class="col-xs-6">
											<form:textarea rows="5" cols="30" class="form-control"
												id="inputTurno" placeholder="Orientações da autorização"
												path="autorizacao.orientacoes"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Observações:</label>
										</div>
										<div class="col-xs-6">
											<form:textarea rows="5" cols="30" class="form-control"
												id="inputTurno" placeholder="Observações da autorização"
												path="autorizacao.observacoes"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</fieldset>

								<fieldset class="content-group" id="divTabelaExames">
									<legend class="text-bold">Exames</legend>

									<div class="form-group">
										<table class="table table-hover table-striped table-bordered ">
											<thead>
												<tr>
													<th width="10%">Nome</th>
													<th width="10%">Valor</th>
												</tr>
											</thead>
											<tbody id="tb">
												<c:forEach var="exame" items="${autorizacaoDTO.exames}"
													varStatus="status">
													<tr>
														<td><c:out value="${exame.nome}" /></td>
														<td><c:out value="${exame.valor}" /></td>

														<form:input path="exames[${status.index}].nome"
															type="hidden" />
														<form:input path="exames[${status.index}].valor"
															type="hidden" />
														<form:input path="exames[${status.index}].id"
															type="hidden" />
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>

									Valor Total: ${valorTotal}

								</fieldset>

								<div class="text-right">
									<a href="${raiz}autorizacoes"
										class=" btn bg-danger abrirJanela">Cancelar</a>

									<button type="submit" id="btn-gerar" class="btn btn-primary">Gerar
										Orçamento</button>

									<button type="submit" class="btn btn-primary">
										Autorizar <i class="icon-arrow-right14 position-right"></i>
									</button>
								</div>

							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/autorizacao/autorizacao.js" />"></script>