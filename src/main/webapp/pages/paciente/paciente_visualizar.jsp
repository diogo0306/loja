<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />
<input type="hidden"
	value="${raiz}beneficiario/gestao/acidionar/dependente" id="url-add" />

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
								class="text-semibold">Gestão</span> - Beneficiário - Incluir
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li>Beneficiários</li>
						<li class="active">Incluir</li>

					</ul>
				</div>
			</div>


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

						<form:form class="form-horizontal" role="form"
							action="${raiz}beneficiario/gestao/salvar"
							id="formularioPaciente" method="POST"
							modelAttribute="pacienteDTO">

							<form:input path="idSolicitacao" type="hidden" />

							<div class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#tabDados" data-toggle="tab">Dados</a></li>
									<li><a href="#tabContrato" data-toggle="tab">Contrato</a></li>
									<li><a href="#tabDependentes" data-toggle="tab">Dependentes</a></li>
									<li><a href="#tabFinanceiro" data-toggle="tab">Financeiro</a></li>
								</ul>

								<div class="tab-content">
									<div class="tab-pane active" id="tabDados">
										<fieldset class="content-group">
											<legend class="text-bold">Dados do Beneficiário</legend>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">Nome*</label>
												</div>

												<div class="col-xs-8">
													<form:input type="text" class="form-control" id="inputNome"
														disabled="true" placeholder="Não informado"
														path="paciente.nome"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputDataNascimento">Data Nascimento.*</label>
												</div>

												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="#"
														placeholder="Não informado" disabled="true"
														path="paciente.dataNascimentoFormatada"
														cssErrorClass="field_error form-control" />
												</div>

											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="field_3">Sexo*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="#"
														placeholder="Não informado"
														path="paciente.sexoEnum.descricao"
														cssErrorClass="field_error form-control" disabled="true" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">CPF*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="inputCpf"
														disabled="true" placeholder="Não informado"
														path="paciente.documentacao.cpf"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">RG*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="inputRg"
														disabled="true" placeholder="Não informado"
														path="paciente.documentacao.rg"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">Orgão Expedidor</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="#"
														placeholder="Não informado"
														path="paciente.documentacao.orgaoEmissorEnum.descricao"
														cssErrorClass="field_error form-control" disabled="true" />
												</div>

											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">Endereço</label>
												</div>

												<div class="col-xs-8">
													<form:input type="text" class="form-control" id="inputNome"
														disabled="true" placeholder="Não informado"
														path="paciente.endereco.logradouro"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">Bairro</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="inputNome"
														placeholder="Não informado" disabled="true"
														path="paciente.endereco.bairro"
														cssErrorClass="field_error form-control" />
												</div>

											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="field_3">Cidade*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputCidade" placeholder="Não informado"
														path="paciente.endereco.cidade" disabled="true"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">UF*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="#"
														placeholder="Não informado"
														path="paciente.endereco.estado.descricao"
														cssErrorClass="field_error form-control" disabled="true" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">Número</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														disabled="true" id="inputNumero"
														placeholder="Não informado"
														path="paciente.endereco.numero"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">Complemento</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														disabled="true" id="inputComplemento"
														placeholder="Não informado"
														path="paciente.endereco.complemento"
														cssErrorClass="field_error form-control" />
												</div>

											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">CEP*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="inputCep"
														placeholder="Não informado" path="paciente.endereco.cep"
														disabled="true" cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputDataNascimento">Telefone*</label>
												</div>

												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputTelefone" placeholder="Não informado"
														disabled="true" path="paciente.documentacao.telefone"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputDataNascimento">Celular</label>
												</div>

												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														disabled="true" id="inputCelular"
														placeholder="Não informado"
														path="paciente.documentacao.celular"
														cssErrorClass="field_error form-control" />
												</div>
											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">E-mail</label>
												</div>

												<div class="col-xs-8">
													<form:input type="text" class="form-control" id="inputNome"
														placeholder="Não informado" disabled="true"
														path="paciente.documentacao.email"
														cssErrorClass="field_error form-control" />
												</div>
											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">Nome da Mãe*</label>
												</div>

												<div class="col-xs-8">
													<form:input type="text" class="form-control" id="inputNome"
														placeholder="Não informado" path="paciente.filiacaoMae"
														disabled="true" cssErrorClass="field_error form-control" />
												</div>
											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">Nome da Pai*</label>
												</div>

												<div class="col-xs-8">
													<form:input type="text" class="form-control" id="inputNome"
														disabled="true" placeholder="Não informado"
														path="paciente.filiacaoPai"
														cssErrorClass="field_error form-control" />
												</div>
											</div>
										</fieldset>
									</div>

									<div class="tab-pane" id="tabContrato">
										<fieldset class="content-group">
											<legend class="text-bold">Dados do Contrato</legend>

											<div class="form-group">
												<label class="control-label col-lg-2">Plano</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-8">
														<form:input type="text"
															class="touchspin-set-value form-control" disabled="true"
															path="contrato.plano.nome" style="display: block;"></form:input>
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-lg-2">Início da
													vigência</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-5">
														<form:input type="text" class="form-control"
															disabled="true" id="inputInicioVigencia"
															placeholder="Não informado"
															path="contrato.inicioVigencia"
															cssErrorClass="field_error form-control" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-2">Final da
													vigência</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-5">
														<form:input type="text" class="form-control"
															disabled="true" id="inputFimVigencia"
															placeholder="Não informado" path="contrato.fimVigencia"
															cssErrorClass="field_error form-control" />
														<form:errors path="contrato.fimVigencia"
															cssClass="text-danger" class="has-error" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-lg-2">Valor Total</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-5">
														<form:input type="text" class="form-control"
															disabled="true" id="inputTaxaContrato"
															placeholder="Não informado" path="contrato.valorTotal"
															cssErrorClass="field_error form-control" />

													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-lg-2">Dia de
													Vencimento</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-5">
														<form:input type="text" class="form-control" id="#"
															placeholder="Não informado"
															path="contrato.diaVencimentoEnum.descricao"
															cssErrorClass="field_error form-control" disabled="true" />
													</div>
												</div>
											</div>

										</fieldset>
									</div>

									<div class="tab-pane" id="tabDependentes">

										<fieldset class="content-group">
											<legend class="text-bold">Lista de Dependentes</legend>

											<div class="form-group">
												<table
													class="table table-hover table-striped table-bordered ">
													<thead>
														<tr>
															<th width="10%">Nome</th>
															<th width="10%">CPF</th>
															<th width="10%">RG</th>
															<th width="10%">Data</th>
															<th width="10%">Ações</th>
														</tr>
													</thead>
													<tbody id="tb">
														<c:forEach var="dependente"
															items="${pacienteDTO.listaDependente}" varStatus="status">
															<tr>
																<td><c:out value="${dependente.nome}" /></td>
																<td><c:out value="${dependente.cpf}" /></td>
																<td><c:out value="${dependente.rg}" /></td>
																<td><c:out value="${dependente.dataFormatada}" /></td>
																<td class="text-center">
																	<ul class="icons-list">
																		<li class="dropdown"><a href="#"
																			class="dropdown-toggle" data-toggle="dropdown"> <i
																				class="icon-menu9"></i>
																		</a>
																			<ul class="dropdown-menu dropdown-menu-right">
																				<li><a href="${raiz}paciente/alterar/dependente/${dependente.id}"><i
																						class="glyphicon glyphicon-usd"></i>Alterar</a></li>
																			</ul></li>
																	</ul>
																</td>

															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div>

									<div class="tab-pane" id="tabFinanceiro">
										<fieldset class="content-group">
											<legend class="text-bold">Cobranças Contratuais</legend>

											<table
												class="table table-hover table-striped table-bordered ">
												<thead>
													<tr>
														<th width="40%">Descrição</th>
														<th width="10%">Valor(R$)</th>
														<th width="10%">Vencimento</th>
														<th width="10%">Ações</th>
													</tr>
												</thead>
												<tbody style="font-size: 12px;">
													<c:forEach var="cobrancaContrato"
														items="${pacienteDTO.listaCobrancaContrato}">
														<tr>
															<td><c:out value="${cobrancaContrato.nome}" /></td>
															<td><c:out value="${cobrancaContrato.valor}" /></td>
															<td><c:out value="${cobrancaContrato.dataFormatada}" /></td>
															<td class="text-center">
																<ul class="icons-list">
																	<li class="dropdown"><a href="#"
																		class="dropdown-toggle" data-toggle="dropdown"> <i
																			class="icon-menu9"></i>
																	</a>
																		<ul class="dropdown-menu dropdown-menu-right">
																			<li><a href="#"><i
																					class="glyphicon glyphicon-usd"></i> Baixar
																					Cobrança </a></li>
																			<li><a href="#"><i
																					class="glyphicon glyphicon-usd"></i> Negociar
																					Cobrança </a></li>
																		</ul></li>
																</ul>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>

										</fieldset>

										<fieldset class="content-group">
											<legend class="text-bold">Cobrança(s) Avulsa(s)</legend>

											<table
												class="table table-hover table-striped table-bordered ">
												<thead>
													<tr>
														<th width="40%">Descrição</th>
														<th width="10%">Valor(R$)</th>
														<th width="10%">Vencimento</th>
														<th width="10%">Ações</th>
													</tr>
												</thead>
												<tbody style="font-size: 12px;">
													<c:forEach var="cobrancaAvulsa"
														items="${pacienteDTO.listaCobrancaAvulsa}">
														<tr>
															<td><c:out value="${cobrancaAvulsa.nome}" /></td>
															<td><c:out value="${cobrancaAvulsa.valor}" /></td>
															<td><c:out value="${cobrancaAvulsa.dataFormatada}" /></td>
															<td class="text-center">
																<ul class="icons-list">
																	<li class="dropdown"><a href="#"
																		class="dropdown-toggle" data-toggle="dropdown"> <i
																			class="icon-menu9"></i>
																	</a>

																		<ul class="dropdown-menu dropdown-menu-right">

																			<li><a
																				href="${raiz}paciente/excluir/cobranca_avulsa/${cobrancaAvulsa.id}"><i
																					class="glyphicon glyphicon-usd"></i> Baixar
																					Cobrança </a></li>

																			<li><a
																				href="${raiz}paciente/visualizar/cobranca_avulsa/${paciente.id}"><i
																					class="glyphicon glyphicon-usd"></i> Negociar
																					Cobrança </a></li>

																		</ul></li>
																</ul>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>

										</fieldset>
									</div>

								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/paciente/beneficiario.js" />"></script>