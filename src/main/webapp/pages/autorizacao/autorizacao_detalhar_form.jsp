<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}autorizacao/cancelar"
	id="url-cancelar" />
<input type="hidden" value="${raiz}autorizacao/emitir-guia"
	id="url-emitir" />
<input type="hidden" value="${raiz}autorizacao/confirmar"
	id="url-autorizar" />

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
							Detalhar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Autorização</li>
						<li><a href="${raiz}autorizacao/detalhar">Detalhar</a></li>

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
					action="${raiz}autorizacao/confirmar" modelAttribute="autorizacao"
					id="formularioAutorizacaoDetalhar">

					<form:input path="id" type="hidden" />

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
											<label for="field_3">Tipo:</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control" readonly="true"
												path="tipo.descricao" />
											<form:input path="tipo" type="hidden" />
										</div>
										<div class="col-xs-1">
											<label for="field_1">Data da Autorização:</label>
										</div>
										<div class="col-xs-2">
											<form:input type="text" class="form-control" readonly="true"
												path="dataFormatada" />
											<form:input path="dataFormatada" type="hidden" />
										</div>
										<div class="col-xs-1">
											<label for="field_1">Situação:</label>
										</div>
										<div class="col-xs-2">
											<form:input type="text" class="form-control" readonly="true"
												path="situacao.descricao" />
											<form:input path="situacao" type="hidden" />
										</div>				
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="field_3">Status:</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control" readonly="true"
												path="status.descricao" />
											<form:input path="status" type="hidden" />
										</div>
										<div class="col-xs-1">
											<label for="field_1">Canal:</label>
										</div>
										<div class="col-xs-2">
											<form:input type="text" class="form-control" readonly="true"
												path="canal.descricao" />
											<form:input path="canal" type="hidden" />
										</div>
									</div>
								</fieldset>

								<fieldset class="content-group">
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Beneficiário:</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control" readonly="true"
												path="paciente.nome" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Credenciado:</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control" readonly="true"
												path="credenciado.nome" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Valor Cobrado :</label>
										</div>
										<div class="col-xs-1">
											<form:input type="text" class="form-control"
												path="credenciado.valorCobradoTransiente" readonly="true" />
										</div>
										<div class="col-xs-1">
											<label for="inputDescricao">Valor Total Pago:</label>
										</div>
										<div class="col-xs-1">
											<form:input type="text" class="form-control js-decimal"
												readonly="true" path="valorPagoFormatado" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Nome do Solicitante:</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control" readonly="true"
												placeholder="Não informado" path="nomeSolicitante" />
										</div>
									</div>
								</fieldset>

								<c:if test="${tipo eq 'SP_SADP'}">

									<fieldset class="content-group">
										<div class="form-group">
											<div class="col-xs-1">
												<label for="inputDescricao">Hospital:</label>
											</div>
											<div class="col-xs-3">
												<form:input type="text" class="form-control" readonly="true"
													path="hospital.nome" />
											</div>
										</div>
										<div class="form-group">
											<div class="col-xs-1">
												<label for="inputDescricao">Valor Cobrado
													Sala/Hospital:</label>
											</div>
											<div class="col-xs-1">
												<form:input type="text" class="form-control"
													path="hospital.valorTransiente" readonly="true" />
											</div>
											<div class="col-xs-1">
												<label for="inputDescricao">Valor Pago
													Sala/Hospital:</label>
											</div>
											<div class="col-xs-1">
												<form:input type="text" class="form-control js-decimal"
													readonly="true" path="valorPagoSalaFormatado" />
											</div>
										</div>
									</fieldset>

								</c:if>

								<c:if test="${tipo eq 'CIRURGIA'}">

									<fieldset class="content-group">
										<div class="form-group">
											<div class="col-xs-1">
												<label for="inputDescricao">Exames/Proc.</label>
											</div>
											<div class="col-xs-3">
												<form:input type="text" class="form-control" readonly="true"
													path="exame.nome" />
											</div>
										</div>
									</fieldset>

									<fieldset class="content-group">
										<div class="form-group">
											<div class="col-xs-1">
												<label for="inputDescricao">Hospital:</label>
											</div>
											<div class="col-xs-3">
												<form:input type="text" class="form-control" readonly="true"
													path="hospital.nome" />
											</div>
										</div>
										<div class="form-group">
											<div class="col-xs-1">
												<label for="inputDescricao">Valor Cobrado
													Sala/Hospital:</label>
											</div>
											<div class="col-xs-1">
												<form:input type="text" class="form-control"
													path="hospital.valorTransiente" readonly="true" />
											</div>
											<div class="col-xs-1">
												<label for="inputDescricao">Valor Pago
													Sala/Hospital:</label>
											</div>
											<div class="col-xs-1">
												<form:input type="text" class="form-control js-decimal"
													readonly="true" path="valorPagoSalaFormatado" />
											</div>
										</div>
									</fieldset>

									<fieldset class="content-group">
										<div class="form-group">
											<div class="col-xs-1">
												<label for="inputDescricao">Fornecedor:</label>
											</div>
											<div class="col-xs-3">
												<form:input type="text" class="form-control" readonly="true"
													path="fornecedor.nome" />
											</div>
										</div>
										<div class="form-group">
											<div class="col-xs-1">
												<label for="inputDescricao">Valor Cobrado Material:</label>
											</div>
											<div class="col-xs-1">
												<form:input type="text" class="form-control"
													path="fornecedor.valorTransiente" readonly="true" />
											</div>
											<div class="col-xs-1">
												<label for="inputDescricao">Valor Pago Material:</label>
											</div>
											<div class="col-xs-1">
												<form:input type="text" class="form-control js-decimal"
													readonly="true" path="valorPagoMaterialFormatado" />
											</div>
										</div>
									</fieldset>

								</c:if>

								<fieldset class="content-group">
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Orientações:</label>
										</div>
										<div class="col-xs-6">
											<form:textarea rows="5" cols="30" class="form-control"
												placeholder="Não informado" path="orientacoes"
												readonly="true" cssErrorClass="field_error form-control" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Observações:</label>
										</div>
										<div class="col-xs-6">
											<form:textarea rows="5" cols="30" class="form-control"
												placeholder="Não informado" path="observacoes"
												readonly="true" cssErrorClass="field_error form-control" />
										</div>
									</div>
								</fieldset>

								<c:if test="${tipo eq 'SP_SADP'}">
									<fieldset class="content-group">
										<legend class="text-bold">Exames</legend>

										<div class="form-group">
											<table
												class="table table-hover table-striped table-bordered ">
												<thead>
													<tr>
														<th width="10%">Nome</th>
														<th width="10%">Valor</th>
													</tr>
												</thead>
												<tbody id="tb">
													<c:forEach var="exame" items="${autorizacao.exames}"
														varStatus="status">
														<tr>
															<td><c:out value="${exame.nome}" /></td>
															<td><c:out value="${exame.valorTransiente}" /></td>

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

								</c:if>

								<div class="text-right">
									<a href="${raiz}autorizacoes"
										class=" btn bg-danger abrirJanela">Voltar</a>

									<button type="submit" id="btn-cancelar" class="btn btn-primary">Cancelar
										Autorização</button>

									<button type="submit" id="btn-emitir" formtarget="_blank" class="btn btn-primary">
										Emitir Guia</button>

									<button type="submit" id="btn-autorizar" class="btn btn-primary">
										Autorizar</button>
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