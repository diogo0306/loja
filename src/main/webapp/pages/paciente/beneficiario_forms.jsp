
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
														placeholder="Digite o Nome Completo" path="paciente.nome"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputDataNascimento">Data Nascimento.*</label>
												</div>

												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputDataNascimento" placeholder="Digite a Data"
														path="paciente.dataNascimentoFormatada"
														cssErrorClass="field_error form-control" />
												</div>

											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="field_3">Sexo*</label>
												</div>
												<div class="col-xs-2">
													<form:select id="selectTipoSexo"
														path="paciente.codigoSexoTransiente" class="form-control"
														cssErrorClass="field_error form-control">
														<form:option value="" label="Selecione" />
														<form:options items="${sexos}" itemLabel="descricao"
															itemValue="codigo" />
													</form:select>
												</div>

												<div class="col-xs-1">
													<label for="inputNome">CPF*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="inputCpf"
														placeholder="Digite o CPF"
														path="paciente.documentacao.cpf"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">RG*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="inputRg"
														placeholder="Digite o RG" path="paciente.documentacao.rg"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">Orgão Expedidor</label>
												</div>
												<div class="col-xs-2">
													<form:select id="selectTipoSexo"
														path="paciente.documentacao.orgaoEmissorTransiente"
														class="form-control"
														cssErrorClass="field_error form-control">
														<form:option value="" label="Selecione" />
														<form:options items="${orgaos}" itemLabel="descricao"
															itemValue="codigo" />
													</form:select>
												</div>

											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">Endereço</label>
												</div>

												<div class="col-xs-8">
													<form:input type="text" class="form-control" id="inputNome"
														placeholder="Endereço Residencial: Rua, AV."
														path="paciente.endereco.logradouro"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">Bairro</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control" id="inputNome"
														placeholder="Digite o bairro"
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
														id="inputCidade" placeholder="Digite a Cidade"
														path="paciente.endereco.cidade"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">UF*</label>
												</div>
												<div class="col-xs-2">
													<form:select id="selectTipoSexo"
														path="paciente.endereco.estadoTransiente"
														class="form-control"
														cssErrorClass="field_error form-control">
														<form:option value="" label="Selecione" />
														<form:options items="${uf}" itemLabel="descricao"
															itemValue="codigo" />
													</form:select>
												</div>

												<div class="col-xs-1">
													<label for="inputNome">Número</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputNumero" placeholder="Digite o número"
														path="paciente.endereco.numero"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">Complemento</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputComplemento" placeholder="Complemento."
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
														placeholder="Digite o CEP" path="paciente.endereco.cep"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputDataNascimento">Telefone*</label>
												</div>

												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputTelefone" placeholder="(99) 9999-9999"
														path="paciente.documentacao.telefone"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputDataNascimento">Celular</label>
												</div>

												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputCelular" placeholder="(99) 99999-9999"
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
														placeholder="Digite o e-mail"
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
														placeholder="Digite o Nome Completo da Mãe do Titular"
														path="paciente.filiacaoMae"
														cssErrorClass="field_error form-control" />
												</div>
											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">Nome da Pai*</label>
												</div>

												<div class="col-xs-8">
													<form:input type="text" class="form-control" id="inputNome"
														placeholder="Digite o Nome Completo do Pai do Titular"
														path="paciente.filiacaoPai"
														cssErrorClass="field_error form-control" />
												</div>
											</div>

											<div class="text-right">
												<button type="submit" class="btn btn-primary">
													Incluir <i class="icon-arrow-right14 position-right"></i>
												</button>
											</div>

										</fieldset>
									</div>

									<div class="tab-pane" id="tabContrato">
										<fieldset class="content-group">
											<legend class="text-bold">Dados do Contrato</legend>

											<div class="form-group">
												<label class="control-label col-lg-2">Plano*</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-8">
														<form:select id="selectPlano" path="plano.id"
															required="true" class="form-control"
															cssErrorClass="field_error form-control">
															<form:option value="" label="Selecione" />
															<form:options items="${planos}" itemLabel="nome"
																itemValue="id" />
														</form:select>
														<form:errors path="plano.id" cssClass="text-danger"
															class="has-error" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-lg-2">Início da
													vigência*</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-5">
														<form:input type="text" class="form-control"
															required="true" id="inputInicioVigencia"
															placeholder="Digite o data inicial"
															path="contrato.inicioVigencia"
															cssErrorClass="field_error form-control" />
														<form:errors path="contrato.inicioVigencia"
															cssClass="text-danger" class="has-error" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-2">Final da
													vigência*</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-5">
														<form:input type="text" class="form-control"
															required="true" id="inputFimVigencia"
															placeholder="Digite o data final"
															path="contrato.fimVigencia"
															cssErrorClass="field_error form-control" />
														<form:errors path="contrato.fimVigencia"
															cssClass="text-danger" class="has-error" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-lg-2">Taxa*</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-5">
														<form:input type="text" class="form-control"
															required="true" id="inputTaxaContrato"
															placeholder="Digite o Valor"
															path="contrato.valorTaxaTransiente"
															cssErrorClass="field_error form-control" />
														<form:errors path="contrato.valorTaxaTransiente"
															cssClass="text-danger" class="has-error" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-lg-2">Dia de
													Vencimento*</label>
												<div class="col-lg-4">
													<div class="input-group col-xs-5">
														<form:select id="selectDiaVencimento" required="true"
															path="contrato.codigoDiaVencimentoTransiente"
															class="form-control"
															cssErrorClass="field_error form-control">
															<form:option value="" label="Selecione" />
															<form:options items="${diaVencimento}"
																itemLabel="descricao" itemValue="codigo" />
														</form:select>
														<form:errors path="contrato.codigoDiaVencimentoTransiente"
															cssClass="text-danger" class="has-error" />
													</div>
												</div>
											</div>

										</fieldset>
									</div>

									<div class="tab-pane" id="tabDependentes">
										<fieldset class="content-group">
											<legend class="text-bold">Dados do Dependente</legend>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">Nome*</label>
												</div>
												<div class="col-xs-8">
													<form:input type="text" class="form-control"
														id="inputNomeDependente"
														placeholder="Digite o Nome Completo"
														path="dependente.nome"
														cssErrorClass="field_error form-control" />
												</div>
												
												<div class="col-xs-1">
													<label for="inputDataNascimento2">Data Nascimento:*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputDataNascimento2" placeholder="Digite a Data"
														path="dependente.dataFormatada"
														cssErrorClass="field_error form-control" />
												</div>

											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="inputNome">Nome da Mãe*</label>
												</div>
												<div class="col-xs-8">
													<form:input type="text" class="form-control"
														id="inputFiliacaoDependente"
														placeholder="Digite o Nome Completo da Mãe do Dependente"
														path="dependente.filiacaoMae"
														cssErrorClass="field_error form-control" />
												</div>
											</div>

											<div class="form-group">

												<div class="col-xs-1">
													<label for="field_3">Sexo*</label>
												</div>
												<div class="col-xs-2">
													<form:select id="selectTipoSexoDependente"
														path="dependente.sexoTransiente" class="form-control"
														cssErrorClass="field_error form-control">
														<form:option value="" label="Selecione" />
														<form:options items="${sexos}" itemLabel="descricao"
															itemValue="codigo" />
													</form:select>
												</div>

												<div class="col-xs-1">
													<label for="inputNome">CPF*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputCpfDependente" placeholder="Digite o CPF"
														path="dependente.cpf"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">RG*</label>
												</div>
												<div class="col-xs-2">
													<form:input type="text" class="form-control"
														id="inputRgDependente" placeholder="Digite o RG"
														path="dependente.rg"
														cssErrorClass="field_error form-control" />
												</div>

												<div class="col-xs-1">
													<label for="inputNome">Orgão Expedidor*</label>
												</div>
												<div class="col-xs-2">
													<form:select id="selectTipoOrgaoDependente"
														path="dependente.orgaoExpedidor" class="form-control"
														cssErrorClass="field_error form-control">
														<form:option value="" label="Selecione" />
														<form:options items="${orgaos}" itemLabel="descricao"
															itemValue="codigo" />
													</form:select>
												</div>

											</div>
											<br />

											<div class="text-right">
												<button class="btn btn-success" type="button"
													style="width: 10%;" id="btn-adicionar">Adicionar</button>
											</div>
											<br />

										</fieldset>

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

																<form:input path="listaDependente[${status.index}].nome"
																	type="hidden" />
																<form:input path="listaDependente[${status.index}].cpf"
																	type="hidden" />
																<form:input
																	path="listaDependente[${status.index}].dataFormatada"
																	type="hidden" />
																<form:input
																	path="listaDependente[${status.index}].dataNascimento"
																	type="hidden" />
																<form:input path="listaDependente[${status.index}].rg"
																	type="hidden" />
																<form:input
																	path="listaDependente[${status.index}].filiacaoMae"
																	type="hidden" />
																<form:input path="listaDependente[${status.index}].sexo"
																	type="hidden" />
																<form:input
																	path="listaDependente[${status.index}].orgaoExpedidor"
																	type="hidden" />
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>

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