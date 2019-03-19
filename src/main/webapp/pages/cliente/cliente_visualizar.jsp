<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>


<c:url value="/" var="raiz" />



<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

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

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Unidades - Visualizar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}clientes"> Unidades</a></li>
						<li class="active">Visualizar</li>

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
							action="${raiz}cliente/alterar" id="id-form" method="POST"
							modelAttribute="cliente">

							<div class="panel-body">
								<fieldset class="content-group">
									<legend class="text-bold">Dados da Unidade</legend>

									<div class="row">
										<div class="col-xs-1">
											<label for="inputNome">Razao Social*</label>
										</div>
										<div class="col-xs-6">
											<form:input type="text" class="form-control" id="inputNome"
												readonly="true" disabled="true" placeholder="Digite o nome"
												path="nome" cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputNome">Nome Fantasia</label>
										</div>
										<div class="col-xs-4">
											<form:input type="text" class="form-control" id="inputNome"
												readonly="true" disabled="true" placeholder="Digite o nome"
												path="fantasia" cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />

									<div class="row">
										<div class="col-xs-offset-1 col-xs-6">
											<form:errors path="cnpj" cssClass="text-danger" />
										</div>
									</div>
									<div class="row">
										<div class="col-xs-1">
											<label for="inputCnpj">CNPJ*</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control" id="inputCnpj"
												readonly="true" disabled="true" placeholder="Digite o cnpj"
												path="cnpj" cssErrorClass="field_error form-control" />
										</div>

										<div class="col-xs-1">
											<label for="inputCnae">CNAE</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control" id="inputCnae"
												readonly="true" disabled="true"
												placeholder="Digite o numero" path="cnae"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />

									<div class="row">
										<div class="col-xs-1">
											<label for="inputTexto">Descrição do CNAE</label>
										</div>
										<div class="col-xs-6"
											style="margin: 0px -97px 0px 0px; width: 662px; height: 72px;">
											<form:textarea maxlength="254" rows="3" cols="20"
												class="form-control" id="inputTexto" readonly="true"
												disabled="true" placeholder="Até 254 caracteres"
												path="cnaeDescricao"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />

									<div class="row">
										<div class="col-xs-offset-1 col-xs-2">
											<form:errors path="pais" cssClass="text-danger" />
										</div>
										<div class="col-xs-offset-1 col-xs-2"
											style="margin-left: 5.2%">
											<form:errors path="estado" cssClass="text-danger" />
										</div>
										<div class="row">
											<div class="col-xs-offset-1 col-xs-2"
												style="margin-left: 3.2%">
												<form:errors path="cidade" cssClass="text-danger" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-1">
											<label for="field_1">País*</label>
										</div>
										<div class="col-xs-2">
											<form:input type="text" cssClass="form-control" id="inputRua"
												readonly="true" disabled="true" placeholder="Digite o país"
												path="pais" cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1" style="width: 5%">
											<label for="field_1">Estado*</label>
										</div>
										<div class="col-xs-2" style="width: 15%">
											<form:input type="text" cssClass="form-control" id="inputRua"
												readonly="true" disabled="true"
												placeholder="Digite o estado" path="estado"
												cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1" style="width: 5%">
											<label for="field_1">Cidade*</label>
										</div>
										<div class="col-xs-2">
											<form:input type="text" cssClass="form-control" id="inputRua"
												readonly="true" disabled="true"
												placeholder="Digite a cidade" path="cidade"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />

									<div class="row">
										<div class="col-xs-offset-1 col-xs-6">
											<form:errors path="logradouro" cssClass="text-danger" />
										</div>
									</div>
									<div class="row">
										<div class="col-xs-1">
											<label for="field_1">Logradouro*</label>
										</div>
										<div class="col-xs-4">
											<form:input type="text" cssClass="form-control" id="inputRua"
												readonly="true" disabled="true"
												placeholder="Digite a Rua e número" path="logradouro"
												cssErrorClass="field_error form-control" />
										</div>
									</div>

									<br />

									<div class="row">
										<div class="col-xs-offset-1 col-xs-6">
											<form:errors path="cep" cssClass="text-danger" />
										</div>
									</div>
									<div class="row">
										<div class="col-xs-1">
											<label for="field_1">CEP*</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" cssClass="form-control" id="inputCep"
												readonly="true" disabled="true" placeholder="Digite o CEP"
												path="cep" cssErrorClass="field_error form-control" />
										</div>
									</div>

									<br />

									<div class="row">
										<div class="col-xs-offset-1 col-xs-6">
											<form:errors path="bairro" cssClass="text-danger" />
										</div>
									</div>
									<div class="row">
										<div class="col-xs-1">
											<label for="field_1">Bairro*</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" cssClass="form-control"
												id="inputBairro" readonly="true" disabled="true"
												placeholder="Digite o bairro" path="bairro"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />
									<div class="row">
										<div class="col-xs-offset-1 col-xs-6">
											<form:errors path="telefone" cssClass="text-danger" />
										</div>
									</div>
									<div class="row">
										<div class="col-xs-1">
											<label for="inputTelefone">Telefone*</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control"
												id="inputTelefone" readonly="true" disabled="true"
												placeholder="Digite o número fixo" path="telefone"
												cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputTelefoneSec">Telefone</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control"
												id="inputTelefoneSec" readonly="true" disabled="true"
												placeholder="Digite o número fixo" path="telefoneSecundario"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />
									<div class="row">
										<div class="col-xs-1">
											<label for="inputCelular">Celular</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control"
												id="inputCelular" readonly="true" disabled="true"
												placeholder="Digite o número" path="celular"
												cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputCelularSec">Celular</label>
										</div>
										<div class="col-xs-3">
											<form:input type="text" class="form-control"
												id="inputCelularSec" readonly="true" disabled="true"
												placeholder="Digite o número" path="celularSecundario"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />

									<div class="row">
										<label for="inputTabela"
											style="text-align: left; margin-top: -1%"
											class="col-lg-1 control-label">Tabela de Preço*</label>
										<div class="col-lg-2">
											<form:select path="tabela.id" class="form-control"
												id="selectTabela" readonly="true" disabled="true"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${tabela}" itemLabel="nome"
													itemValue="id" />
												<form:input path="tabela.id" type="hidden" />
											</form:select>
										</div>
									</div>

								</fieldset>

								<div class="text-right">
									<a href="${raiz}clientes	" class=" btn bg-danger abrirJanela">Voltar</a>

								</div>
							</div>

							<script type="text/javascript"
								src="<c:url value="/resources/js/cliente/cliente.js" />"></script>
					</div>
				</div>
			</div>
			</form:form>
		</div>
	</div>
</div>