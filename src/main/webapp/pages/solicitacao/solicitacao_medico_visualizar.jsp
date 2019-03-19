<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}solicitacao/salvar_medico"
	id="url-salvar" />

<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">

				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Aprovações</span> - Solicitações -
							Detalhar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Aprovações</li>
						<li><a href="${raiz}solicitacoes">Solicitações - Médico</a></li>
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
							action="${raiz}solicitacao/salvar_medico"
							id="formularioSolicitacaoMedico" method="POST"
							modelAttribute="solicitacaoDTO">

							<fieldset class="content-group">
								<legend class="text-bold">Dados da Solicitação</legend>

								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Nome:</label>
									<div class="col-lg-4">
										<form:input path="solicitacao.nome" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.nome" type="hidden" />
										<form:input path="solicitacao.id" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">CPF/CNPJ:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.cpf" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.cpf" type="hidden" />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Data:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.dataFormatada"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.dataFormatada" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">Tipo:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.tipo" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.tipo" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">Status:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.status" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.status" type="hidden" />
									</div>
								</div>
								<br />

								<!-- <div class="row">
									<div class="col-xs-offset-1 col-xs-6">
										<a href="" class="btn btn-primary">Visualizar Documentação</a>
									</div>
								</div> -->

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">DADOS DE CONTRATAÇÃO</legend>

								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Nome</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input required="true" type="text" class="form-control"
											id="inputNome" placeholder="Digite o nome"
											path="credenciado.nome"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.nome" cssClass="text-danger"
											class="has-error" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Código</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control"
											id="inputCodigo" placeholder="Digite o código"
											path="credenciado.codigo" required="true"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.codigo" cssClass="text-danger"
											class="has-error" />
									</div>

									<div class="col-xs-1">
										<label for="field_3">Tipo Pessoa</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:select id="tipoPessoaJs"
											path="credenciado.TipoPessoa"
											class="form-control js-select-tipo-pessoa"
											cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${tipoPessoas}" itemLabel="descricao"
												itemValue="codigo" />
										</form:select>
									</div>
								</div>

								<br>

								<div class="row">

									<div id="divCpf">
										<div class="col-xs-1">
											<label for="field_3">CPF</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="inputCpf"
												placeholder="Digite o cpf" path="credenciado.cpf"
												cssErrorClass="field_error form-control" />
											<form:errors path="credenciado.cpf" cssClass="text-danger"
												class="has-error" />
										</div>

									</div>
									<div id="divCnpj">
										<div class="col-xs-1" id="divCnpj">
											<label for="field_3">CNPJ</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="inputCnpj"
												placeholder="Digite o cnpj" path="credenciado.cnpj"
												cssErrorClass="field_error form-control" />
											<form:errors path="credenciado.cnpj" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>

									<div class="col-xs-1">
										<label for="field_3">Situação</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:select path="credenciado.SituacaoEnum"
											class="form-control" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${situacao}" itemLabel="descricao"
												itemValue="codigo" />
										</form:select>
									</div>
									<div class="col-xs-1">
										<label for="field_3">Data</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputData"
											placeholder="Digite o data" path="credenciado.data"
											required="true" cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.data" cssClass="text-danger"
											class="has-error" />
									</div>
									<div id="divRg">
										<div class="col-xs-1">
											<label for="field_3">RG</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="rg"
												placeholder="Digite o RG" path="credenciado.rg"
												cssErrorClass="field_error form-control" />
											<form:errors path="credenciado.rg" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>

								</div>
								<br>
								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Rua</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input required="true" type="text" class="form-control"
											id="inputNome" placeholder="Digite a rua"
											path="credenciado.logradouro"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.logradouro"
											cssClass="text-danger" class="has-error" />
									</div>

									<div class="col-xs-1">
										<label for="field_3">Número</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input required="true" type="text" class="form-control"
											id="inputNome" placeholder="Digite número"
											path="credenciado.numero"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.numero" cssClass="text-danger"
											class="has-error" />
									</div>

									<div class="col-xs-1">
										<label for="field_3">Bairro</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input required="true" type="text" class="form-control"
											id="inputNome" placeholder="Digite o bairro"
											path="credenciado.bairro"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.bairro" cssClass="text-danger"
											class="has-error" />
									</div>

								</div>

								<br>

								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Cidade</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input required="true" type="text" class="form-control"
											id="inputNome" placeholder="Digite a cidade"
											path="credenciado.cidade"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.cidade" cssClass="text-danger"
											class="has-error" />
									</div>

									<div class="col-xs-1">
										<label for="field_3">CEP</label>
									</div>
									<div class="col-xs-2">
										<form:input required="true" type="text" class="form-control"
											id="inputCep" placeholder="Digite o CEP"
											path="credenciado.cep"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.cep" cssClass="text-danger"
											class="has-error" />
									</div>

									<div class="col-xs-1">
										<label for="field_3">Telefone</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input required="true" type="text" class="form-control"
											id="inputTelefone" placeholder="Digite o telefone"
											path="credenciado.telefone"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.telefone"
											cssClass="text-danger" class="has-error" />
									</div>

									<div class="col-xs-1">
										<label for="field_3">Celular</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input required="true" type="text" class="form-control"
											id="inputCelular" placeholder="Digite o celular"
											path="credenciado.celular"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.celular" cssClass="text-danger"
											class="has-error" />
									</div>

								</div>

								<br>

								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">E-mail</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input required="true" type="text" class="form-control"
											id="inputNome" placeholder="Digite o e-mail"
											path="credenciado.email"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.email" cssClass="text-danger"
											class="has-error" />
									</div>

								</div>

								<br>
							</fieldset>

							<fieldset class="content-group">

								<legend class="text-bold">Dados Gerais - Dados
									Bancários</legend>

								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Banco</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control"
											id="inputCodigo" placeholder="Digite o código"
											path="credenciado.codBanco"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.codBanco"
											cssClass="text-danger" class="has-error" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Nome do Banco</label>
									</div>
									<div class="col-xs-4" style="margin-left: -0px">
										<form:input type="text" class="form-control" id="input"
											placeholder="Digite o nome" path="credenciado.nomeBanco"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.nomeBanco"
											cssClass="text-danger" class="has-error" />
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Agência</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputCodigo"
											placeholder="Digite o número" path="credenciado.agencia"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.agencia" cssClass="text-danger"
											class="has-error" />
									</div>
									<div class="col-xs-1">
										<label for="field_3" style="margin-left: +20px">Conta</label>
									</div>
									<div class="col-xs-2" style="margin-left: -0px">
										<form:input type="text" class="form-control" id="input"
											placeholder="Digite o número" path="credenciado.conta"
											cssErrorClass="field_error form-control" />
										<form:errors path="credenciado.conta" cssClass="text-danger"
											class="has-error" />
									</div>
								</div>

							</fieldset>
							<br>

							<fieldset class="content-group">
								<legend class="text-bold">Dados Gerais - Repasse</legend>

								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Valor Cobrado</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control"
											id="inputValorCobrado" placeholder="Digite o valor"
											path="credenciado.valorCobrado"
											cssErrorClass="field_error form-control" />
									</div>
								</div>
							</fieldset>


							<fieldset class="content-group">
								<legend class="text-bold">Especialidade</legend>

								<div class="row">
									<div class="col-xs-1" style="width: 12%">
										<label for="field_3">Tipo Profissional</label>
									</div>
									<div class="col-xs-4" style="margin-left: -1%">
										<form:input type="text" class="form-control" id="inputNome"
											disabled="true" placeholder=""
											path="credenciado.especialidade.Especialidade"
											cssErrorClass="field_error form-control" />
									</div>
								</div>
								<br />

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Situação</legend>

								<div class="text-right">
									<a href="${raiz}solicitacoes"
										class=" btn bg-danger abrirJanela">Voltar</a>
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
	src="<c:url value="/resources/js/credenciado/credenciado.js" />"></script>
