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
							action="${raiz}solicitacao/" id="formularioSolicitacaoPaciente"
							method="POST" modelAttribute="solicitacaoDTO">

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
									<label for="inputPcmso" class="col-lg-1 control-label">Telefone:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.telefone" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.cpf" type="hidden" />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Celular:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.telefone" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.celular" type="hidden" />
									</div>
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
										<a href="https://thumbs.jusbr.com/filters:format(webp)/imgs.jusbr.com/publications/noticias/images/cnh-mr-bean1496831588.jpg" target="blank" class="btn btn-primary">Visualizar Documentação</a>
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Incluir Beneficiário</legend>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">Nome*</label>
									</div>

									<div class="col-xs-8">
										<form:input type="text" class="form-control" id="inputNome"
											disabled="true" placeholder="Digite o Nome Completo"
											path="paciente.nome" cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputDataNascimento">Data Nascimento.*</label>
									</div>

									<div class="col-xs-2">
										<form:input type="text" class="form-control"
											id="inputDataNascimento" placeholder="Digite a Data"
											disabled="true" path="paciente.dataNascimentoFormatada"
											cssErrorClass="field_error form-control" />
									</div>

								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="field_3">Sexo*</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCpf"
											disabled="true" placeholder="Digite o CPF"
											path="paciente.sexoEnum.descricao"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">CPF*</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCpf"
											disabled="true" placeholder="Digite o CPF"
											path="paciente.documentacao.cpf"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">RG*</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputRg"
											disabled="true" placeholder="Digite o RG"
											path="paciente.documentacao.rg"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Orgão Expedidor</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputRg"
											disabled="true" placeholder="Digite o RG"
											path="paciente.documentacao.orgaoEmissorEnum.descricao"
											cssErrorClass="field_error form-control" />
									</div>

								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">Endereço</label>
									</div>

									<div class="col-xs-8">
										<form:input type="text" class="form-control" id="inputNome"
											disabled="true" placeholder="Endereço Residencial: Rua, AV."
											path="paciente.endereco.logradouro"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Bairro</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputNome"
											disabled="true" placeholder="Digite o bairro"
											path="paciente.endereco.bairro"
											cssErrorClass="field_error form-control" />
									</div>

								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="field_3">Cidade*</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCidade"
											disabled="true" placeholder="Digite a Cidade"
											path="paciente.endereco.cidade"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">UF*</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCidade"
											disabled="true" placeholder="Digite a Cidade"
											path="paciente.endereco.estado.descricao"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Número</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputNumero"
											disabled="true" placeholder="Digite o número"
											path="paciente.endereco.numero"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Apt.</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" disabled="true"
											id="inputComplemento" placeholder="Apt."
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
											disabled="true" placeholder="Digite o CEP"
											path="paciente.endereco.cep"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputDataNascimento">Telefone*</label>
									</div>

									<div class="col-xs-2">
										<form:input type="text" class="form-control" disabled="true"
											id="inputTelefone" placeholder="(99) 9999-9999"
											path="paciente.documentacao.telefone"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputDataNascimento">Celular</label>
									</div>

									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCelular"
											disabled="true" placeholder="(99) 99999-9999"
											path="paciente.documentacao.celular"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">Email</label>
									</div>

									<div class="col-xs-8">
										<form:input type="text" class="form-control" id="inputNome"
											disabled="true" placeholder="Digite o e-mail"
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
											disabled="true"
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
											disabled="true"
											placeholder="Digite o Nome Completo do Pai do Titular"
											path="paciente.filiacaoPai"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

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


