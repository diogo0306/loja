
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
								class="text-semibold">Gestão</span> - Representantes -
							Visualizar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}representantes">Representante</a></li>
						<li class="active">Visualizar</li>

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
							action="${raiz}representante/salvar" id="id-form" method="POST"
							modelAttribute="representante">
							<fieldset class="content-group">
								<legend class="text-bold">Representante</legend>

								<div class="row">


									<div class="col-xs-1">
										<label>Status*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:select disabled="true" id="#" path="situacaoEnum" class="form-control"
											cssErrorClass="field_error form-control">
											<c:forEach var="situacaoEnum" items="${statusEnum}">
												<option value="${situacaoEnum}">${situacaoEnum.descricao}</option>
											</c:forEach>
										</form:select>
									</div>

									<div class="col-xs-1">
										<label for="inputTipos">Tipo Represent.</label>
									</div>
									<div class="col-xs-2" style="margin-left: +10px">
										<form:select disabled="true" id="tipoPessoaJs" path="tipoRepresentanteEnum"
											class="form-control js-select-tipo-pessoa"
											cssErrorClass="field_error form-control">
											<c:forEach var="tipopessoa" items="${tipo}">
												<option value="${tipopessoa}">${tipopessoa.descricao}</option>
											</c:forEach>
										</form:select>
									</div>
									<div class="col-xs-1">
										<label for="inputSupervisor">Supervisor</label>
									</div>
									<div class="col-xs-5" style="margin-left: -10px">
										<form:select disabled="true" required="true" path="supervisorVinculo.id"
											id="inputRepresentante" class="form-control"
											cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione o supervisor" />
											<form:options items="${supervisores}" itemLabel="nome"
												itemValue="id" />
											<form:errors path="supervisorVinculo" cssClass="text-danger"
												class="has-error" />
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
											disabled="true" readonly="true" path="nome"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="inputNome">CPF*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -0px">
										<form:input type="text" class="form-control" id="inputCpf"
											disabled="true" readonly="true" path="cpfcnpj"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="inputNome">RG*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -1px">
										<form:input type="text" class="form-control"
											id="inputDataContratacao" disabled="true" readonly="true"
											path="rg" cssErrorClass="field_error form-control" />
									</div>
								</div>
								<br />

								<div class="row">

									<div class="col-xs-1">
										<label for="inputNome">Órgão Expedidor*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control"
											id="inputDataContratacao" disabled="true" readonly="true"
											path="orgaoExpedidor"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Sexo*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -0px">
										<form:select path="sexoEnum" class="form-control"
											cssErrorClass="field_error form-control">
											<c:forEach var="sexo" items="${sexos}">
												<option value="${sexo}">${sexo.descricao}</option>
											</c:forEach>
										</form:select>
									</div>
									<div class="col-xs-1">
										<label for="inputNome">Data de Nascimento*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -1px">
										<form:input type="text" class="form-control"
											id="inputDataNascimento" disabled="true" readonly="true"
											path="dataNascimentoFormatada"
											cssErrorClass="field_error form-control" />
									</div>

								</div>
								<br />

								<div class="row">
									<div class="col-xs-1">
										<label for="inputNome">Nome da Mãe*</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input disabled="true" type="text" class="form-control" id="inputNome"
											placeholder="Digite o nome" path="nomeMae"
											cssErrorClass="field_error form-control" />
										<form:errors path="nomeMae" cssClass="text-danger"
											class="has-error" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Nome do Pai*</label>
									</div>
									<div class="col-xs-5" style="margin-left: -0px">
										<form:input disabled="true" type="text" class="form-control" id="inputNome"
											placeholder="Digite o nome" path="nomePai"
											cssErrorClass="field_error form-control" />
										<form:errors path="nomePai" cssClass="text-danger"
											class="has-error" />
									</div>
								</div>
								<br />


								<div class="panel-heading">
									<h5
										style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
										<i class="fa fa-user"></i> &nbsp;&nbsp; Endereço e Contato
									</h5>
								</div>

								<div class="divSeparador2">

									<div class="row">

										<div class="col-xs-1">
											<label for="inputRua">Endereço</label>
										</div>
										<div class="col-xs-5" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" readonly="true" path="logradouro"
												cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputNumero">Número</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" readonly="true" path="numero"
												cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputComplemento">Compl.</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" readonly="true" path="complemento"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />
									<div class="row">

										<div class="col-xs-1">
											<label for="inputBairro">Bairro</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" readonly="true" path="bairro"
												cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputMunicipio">Município</label>
										</div>
										<div class="col-xs-2">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" readonly="true" path="cidade"
												cssErrorClass="field_error form-control" />
										</div>

										<div class="col-xs-1">
											<label for="inputCep2">CEP</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="inputCep"
												disabled="true" readonly="true" path="cep"
												cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputNome">UF*</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="inputCep"
												disabled="true" readonly="true" path="ufEnum"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />
									<div class="row">

										<div class="col-xs-1">
											<label for="inputBairro">Telefone 1</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control"
												id="inputTelefone" disabled="true" readonly="true"
												path="telefone" cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputBairro">Telefone 2</label>
										</div>
										<div class="col-xs-2">
											<form:input type="text" class="form-control"
												id="inputTelefoneSecundario" disabled="true" readonly="true"
												path="telefoneSecundario"
												cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputBairro">Celular</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="text" class="form-control"
												id="inputCelular" disabled="true" readonly="true"
												path="celular" cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />

									<div class="row">

										<div class="col-xs-1">
											<label for="inputBairro">Email</label>
										</div>
										<div class="col-xs-5" style="margin-left: -20px">
											<form:input type="text" class="form-control" id="inputNome"
												readonly="true" path="email" disabled="true"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="text-right">
									<a href="${raiz}representantes"
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

