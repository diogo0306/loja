<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

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
				action="${raiz}credenciado/salvar" id="id-form" method="POST"
				modelAttribute="credenciado">
				<fieldset class="content-group">

					<legend class="text-bold">Incluir Credenciado</legend>

					<div class="row">
						<div class="col-xs-1">
							<label for="field_3">Nome</label>
						</div>
						<div class="col-xs-5" style="margin-left: -20px">
							<form:input required="true" type="text" class="form-control"
								id="inputNome" placeholder="Digite o nome" path="nome"
								cssErrorClass="field_error form-control" />
							<form:errors path="nome" cssClass="text-danger" class="has-error" />
						</div>
						<div class="col-xs-1">
							<label for="field_3">Código</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="number" class="form-control" id="inputCodigo"
								placeholder="Digite o código" path="codigo" required="true"
								cssErrorClass="field_error form-control" />
							<form:errors path="codigo" cssClass="text-danger"
								class="has-error" />
						</div>

						<div class="col-xs-1">
							<label for="field_3">Tipo Pessoa</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:select id="tipoPessoaJs" path="codigoTipoPessoaTransiente"
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
									placeholder="Digite o cpf" path="cpf"
									cssErrorClass="field_error form-control" />
								<form:errors path="cpf" cssClass="text-danger" class="has-error" />
							</div>

						</div>
						<div id="divCnpj">
							<div class="col-xs-1" id="divCnpj">
								<label for="field_3">CNPJ</label>
							</div>
							<div class="col-xs-2" style="margin-left: -20px">
								<form:input type="text" class="form-control" id="inputCnpj"
									placeholder="Digite o cnpj" path="cnpj"
									cssErrorClass="field_error form-control" />
								<form:errors path="cnpj" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>

						<div class="col-xs-1">
							<label for="field_3">Situação</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:select path="codigoSituacaoTransiente"
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
								placeholder="Digite o data" path="data" required="true"
								cssErrorClass="field_error form-control" />
							<form:errors path="data" cssClass="text-danger" class="has-error" />
						</div>
						<div id="divRg">
							<div class="col-xs-1">
								<label for="field_3">RG</label>
							</div>
							<div class="col-xs-2" style="margin-left: -20px">
								<form:input type="text" class="form-control" id="rg"
									placeholder="Digite o RG" path="rg"
									cssErrorClass="field_error form-control" />
								<form:errors path="rg" cssClass="text-danger" class="has-error" />
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
								id="inputNome" placeholder="Digite a rua" path="logradouro"
								cssErrorClass="field_error form-control" />
							<form:errors path="logradouro" cssClass="text-danger"
								class="has-error" />
						</div>

						<div class="col-xs-1">
							<label for="field_3">Número</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input required="true" type="text" class="form-control"
								id="inputNome" placeholder="Digite número" path="numero"
								cssErrorClass="field_error form-control" />
							<form:errors path="numero" cssClass="text-danger"
								class="has-error" />
						</div>

						<div class="col-xs-1">
							<label for="field_3">Bairro</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input required="true" type="text" class="form-control"
								id="inputNome" placeholder="Digite o bairro" path="bairro"
								cssErrorClass="field_error form-control" />
							<form:errors path="bairro" cssClass="text-danger"
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
								id="inputNome" placeholder="Digite a cidade" path="cidade"
								cssErrorClass="field_error form-control" />
							<form:errors path="cidade" cssClass="text-danger"
								class="has-error" />
						</div>

						<div class="col-xs-1">
							<label for="field_3">CEP</label>
						</div>
						<div class="col-xs-2">
							<form:input required="true" type="text" class="form-control"
								id="inputCep" placeholder="Digite o CEP" path="cep"
								cssErrorClass="field_error form-control" />
							<form:errors path="cep" cssClass="text-danger" class="has-error" />
						</div>

						<div class="col-xs-1">
							<label for="field_3">Telefone</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input required="true" type="text" class="form-control"
								id="inputTelefone" placeholder="Digite o telefone"
								path="telefone" cssErrorClass="field_error form-control" />
							<form:errors path="telefone" cssClass="text-danger"
								class="has-error" />
						</div>

						<div class="col-xs-1">
							<label for="field_3">Celular</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input required="true" type="text" class="form-control"
								id="inputCelular" placeholder="Digite o celular" path="celular"
								cssErrorClass="field_error form-control" />
							<form:errors path="celular" cssClass="text-danger"
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
								id="inputNome" placeholder="Digite o e-mail" path="email"
								cssErrorClass="field_error form-control" />
							<form:errors path="email" cssClass="text-danger"
								class="has-error" />
						</div>

					</div>

					<br>

					<legend class="text-bold">Dados Gerais - Dados Bancários</legend>

					<div class="row">
						<div class="col-xs-1">
							<label for="field_3">Banco</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="number" class="form-control" id="inputCodigo"
								placeholder="Digite o código" path="codBanco"
								cssErrorClass="field_error form-control" />
							<form:errors path="codBanco" cssClass="text-danger"
								class="has-error" />
						</div>
						<div class="col-xs-1">
							<label for="field_3">Nome do Banco</label>
						</div>
						<div class="col-xs-4" style="margin-left: -0px">
							<form:input type="text" class="form-control" id="input"
								placeholder="Digite o nome" path="nomeBanco"
								cssErrorClass="field_error form-control" />
							<form:errors path="nomeBanco" cssClass="text-danger"
								class="has-error" />
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-1">
							<label for="field_3">Agência</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="text" class="form-control" id="inputCodigo"
								placeholder="Digite o número" path="agencia"
								cssErrorClass="field_error form-control" />
							<form:errors path="agencia" cssClass="text-danger"
								class="has-error" />
						</div>
						<div class="col-xs-1">
							<label for="field_3" style="margin-left: +20px">Conta</label>
						</div>
						<div class="col-xs-2" style="margin-left: -0px">
							<form:input type="text" class="form-control" id="input"
								placeholder="Digite o número" path="conta"
								cssErrorClass="field_error form-control" />
							<form:errors path="conta" cssClass="text-danger"
								class="has-error" />
						</div>
					</div>
					<br>

					<legend class="text-bold">Dados Gerais - Repasse</legend>

					<div class="row">
						<div class="col-xs-1">
							<label for="field_3">Valor Cobrado</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="text" class="form-control"
								id="inputValorCobrado" placeholder="Digite o valor"
								path="valorCobradoTransiente"
								cssErrorClass="field_error form-control" />						
						</div>
					</div>
					<br>
					<div class="text-right">
						<a href="${raiz}credenciados" class=" btn bg-danger abrirJanela">Cancelar</a>

						<button type="submit" class="btn btn-primary">
							Salvar <i class="icon-arrow-right14 position-right"></i>
						</button>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/js/credenciado/credenciado.js" />"></script>