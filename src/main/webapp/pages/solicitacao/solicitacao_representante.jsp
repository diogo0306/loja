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
						<li><a href="${raiz}solicitacoes">Solicitações -
								Representante</a></li>
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
							action="${raiz}solicitacao/salvar_representante"
							id="formularioSolicitacaoRepresentante" method="POST"
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

								<div class="row">
									<div class="col-xs-offset-1 col-xs-6">
										<a href="" class="btn btn-primary">Visualizar Documentação</a>
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Incluir Representante</legend>

								<div class="row">
									<div class="col-xs-1">
										<label for="inputTipos">Tipo Represent.</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:select
											path="representante.codigoRepresentanteTransiente"
											id="tipoPessoaSolicitacao" class="form-control"
											required="true" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione o Tipo" />
											<form:options items="${tipos}" itemLabel="descricao"
												itemValue="codigo" />
										</form:select>
									</div>
								</div>
								<br />

								<div class="row">
									<div class="col-xs-1">
										<label for="inputNome">Nome*</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputNome"
											required="true" placeholder="Digite o nome"
											path="representante.nome"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="cpfOuCnpj">CPF/CNPJ*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -0px">
										<form:input type="text" class="form-control"
											id="inputCpfCnpjSolicitacao" required="true"
											placeholder="Número do documento"
											path="representante.cpfcnpj"
											cssErrorClass="field_error form-control" />
										<form:errors path="representante.cpfcnpj"
											cssClass="text-danger" class="has-error" />
									</div>
									<div class="col-xs-1">
										<label for="inputNome">RG*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -1px">
										<form:input type="text" class="form-control" required="true"
											id="inputRG" placeholder="Digite o RG"
											path="representante.rg"
											cssErrorClass="field_error form-control" />
									</div>
								</div>
								<br />

								<div class="row">

									<div class="col-xs-1">
										<label for="inputNome">Órgão Expedidor*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control"
											id="inputDataContratacao" placeholder="Digite o órgão"
											path="representante.orgaoExpedidor"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Sexo*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -0px">
										<form:select id="selectTipoPlano"
											path="representante.codigoSexoTransiente" required="true"
											class="form-control" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${sexos}" itemLabel="descricao"
												itemValue="codigo" />
										</form:select>
									</div>
									<div class="col-xs-1">
										<label for="inputNome">Data de Nascimento*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -1px">
										<form:input type="text" class="form-control" required="true"
											id="inputDataNascimento" placeholder="Digite a Data"
											path="representante.dataNascimentoFormatada"
											cssErrorClass="field_error form-control" />
									</div>

								</div>
								<br />

								<div class="row">
									<div class="col-xs-1">
										<label for="inputNome">Nome da Mãe*</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputMae"
											required="true" placeholder="Digite o nome"
											path="representante.nomeMae"
											cssErrorClass="field_error form-control" />
									</div>
								</div>
								<br />

								<div class="row">
									<div class="col-xs-1">
										<label for="inputNome">Nome do Pai*</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input type="text" class="form-control"
											placeholder="Digite o nome" path="representante.nomePai"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Endereço e Contato</legend>

								<div class="row">

									<div class="col-xs-1">
										<label for="inputRua">Endereço</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input type="text" class="form-control"
											id="inputEndereco" required="true"
											placeholder="Digite o nome da rua"
											path="representante.logradouro"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="inputNumero">Número</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control"
											placeholder="Digite o número" path="representante.numero"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="inputComplemento">Compl.</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control"
											placeholder="Digite o complemento"
											path="representante.complemento"
											cssErrorClass="field_error form-control" />
									</div>
								</div>
								<br />
								<div class="row">

									<div class="col-xs-1">
										<label for="inputBairro">Bairro</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputBairro"
											required="true" placeholder="Digite o bairro"
											path="representante.bairro"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="inputMunicipio">Município</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control"
											id="inputMunicipio" required="true"
											placeholder="Digite o município" path="representante.cidade"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputCep2">CEP</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputCep"
											required="true" placeholder="Digite o CEP"
											path="representante.cep"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="inputNome">UF*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:select path="representante.codigoUfTransiente"
											id="inputUF" required="true" class="form-control"
											cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${uf}" itemLabel="descricao"
												itemValue="codigo" />
										</form:select>
									</div>
								</div>
								<br />
								<div class="row">

									<div class="col-xs-1">
										<label for="inputBairro">Telefone 1</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control"
											id="inputTelefone" placeholder="Digite o telefone"
											path="representante.telefone"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="inputBairro">Telefone 2</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control"
											id="inputTelefoneSecundario" placeholder="Digite o telefone"
											path="representante.telefoneSecundario"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="inputBairro">Celular</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputCelular"
											required="true" placeholder="Digite o celular"
											path="representante.celular"
											cssErrorClass="field_error form-control" />
									</div>
								</div>
								<br />

								<div class="row">

									<div class="col-xs-1">
										<label for="inputBairro">Email</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input type="text" class="form-control"
											placeholder="Digite o email" path="representante.email"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Aprovar solicitação?</legend>

								<div class="form-group aprovacao-form">

									<div class="col-lg-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="aprovado" path="aprovado" name="aprovado"
											label="${aprovado}" value="${aprovado}" />
									</div>
									<label for="inputAprovado" class="col-lg-1 control-label"
										style="text-align: left; margin-top: -6px">SIM</label>

									<div class="col-lg-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="reprovado" path="reprovado"
											name="reprovado" label="${reprovado}" value="${reprovado}" />
									</div>
									<label for="inputReprovado" class="col-lg-1 control-label"
										style="text-align: left; margin-top: -6px">NÃO*</label> <br />
								</div>
								<div class="row">
									<div class="col-xs-1">
										<label for="inputDescricao">Motivo*</label>
									</div>
									<div class="col-xs-6">
										<form:textarea maxlength="254" rows="4" cols="50"
											class="form-control" id="inputTurno"
											placeholder="Até 254 caracteres" path="solicitacao.motivo"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<div class="text-right">
									<a href="${raiz}solicitacoes"
										class=" btn bg-danger abrirJanela">Voltar</a>

									<button type="submit" id="btn-salvar" disabled="disabled" class="btn btn-primary">
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
	src="<c:url value="/resources/js/representante/representante.js" />">
	
</script>

