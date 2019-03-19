<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}salvar/dependente" id="url-salvar" />

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
								class="text-semibold">Gestão</span> - Beneficiários - Incluir
							dependente
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Beneficiários</li>
						<li><a href="${raiz}solicitacoes">Solicitações</a></li>
						<li class="active">Detalhar</li>

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
							action="${raiz}paciente/acidionar/dependente"
							id="formularioIncluirDependente" method="POST"
							modelAttribute="pacienteDTO">

							<fieldset class="content-group">
								<legend class="text-bold">Dados do Beneficiário</legend>

								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Nome</label>
									<div class="col-lg-4">
										<form:input path="paciente.nome" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="paciente.nome" type="hidden" />
										<form:input path="paciente.id" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">CPF</label>
									<div class="col-lg-2">
										<form:input path="paciente.documentacao.cpf"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="paciente.documentacao.cpf" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">Telefone</label>
									<div class="col-lg-2">
										<form:input path="paciente.documentacao.telefone"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="paciente.documentacao.telefone"
											type="hidden" />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Celular</label>
									<div class="col-lg-2">
										<form:input path="paciente.documentacao.celular"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="paciente.documentacao.celular" type="hidden" />
									</div>
								</div>
								<br />

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Incluir Dependente</legend>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">Nome*</label>
									</div>
									<div class="col-xs-8">
										<form:input type="text" class="form-control" id="inputNome" required="true"
											placeholder="Digite o Nome Completo" path="dependente.nome"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputDataNascimento2">Data Nascimento:*</label>
									</div>
									<div class="col-xs-2">
										<form:input type="date" class="form-control"
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
										<form:input type="text" class="form-control" id="inputNome" required="true"
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
										<form:select id="selectTipoSexo"
											path="dependente.sexoTransiente" class="form-control" required="true"
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
										<form:input type="text" class="form-control" id="inputCpf" required="true"
											placeholder="Digite o CPF" path="dependente.cpf"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">RG*</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputRg" required="true"
											placeholder="Digite o RG" path="dependente.rg"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Orgão Expedidor*</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputOrgao" required="true"
											placeholder="Digite o Orgão Expedidor"
											path="dependente.orgaoExpedidor"
											cssErrorClass="field_error form-control" />
									</div>

								</div>
								<br />
								
								<div class="text-right">
									<button class="btn btn-success" style="width: 20%;">Adicionar</button>
								</div>
								<br />

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Lista de Dependentes</legend>

								<div class="form-group">
									<table class="table table-hover table-striped table-bordered ">
										<thead>
											<tr>
												<th width="10%">Nome</th>
												<th width="10%">CPF</th>
												<th width="10%">RG</th>
												<th width="10%">Data</th>
											</tr>
										</thead>
										<tbody>
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

								<div class="text-right">
									<a href="${raiz}pacientes" class=" btn bg-danger abrirJanela">Voltar</a>

									<button type="submit" class="btn btn-primary" id="btn-salvar-dependente">
										Salvar <i class="icon-arrow-right14 position-right"></i>
									</button>
								</div>

							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/dependente/dependente.js" />"></script>

