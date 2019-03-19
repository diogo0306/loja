
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<form:input path="id" type="hidden" />

<fieldset class="content-group">
	<legend class="text-bold">Dados pessoais</legend>

	<div class="row">


		<div class="col-xs-1">
			<label>Status*</label>
		</div>
		<div class="col-xs-2" style="margin-left: -20px">
			<form:select id="#" path="situacaoEnum" class="form-control"
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
			<form:select id="tipoPessoaJs" path="tipoRepresentanteEnum"
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
			<form:select required="true" path="supervisorVinculo.id"
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
			<form:input required="true" type="text" class="form-control"
				id="inputNome" placeholder="Digite o nome" path="nome"
				cssErrorClass="field_error form-control" />
			<form:errors path="nome" cssClass="text-danger" class="has-error" />
		</div>
		<div class="col-xs-1" id="divCpf" style="margin-left: +10px">
			<label id="cpfOuCnpjLabel" for="cpfOuCnpj">CPF*</label>
		</div>
		<div class="col-xs-2" style="margin-left: -10px">
			<form:input required="true" type="text" class="form-control"
				id="inputCpfCnpj" placeholder="Informe o numero do documento"
				path="cpfcnpj" cssErrorClass="field_error form-control has-errors" />
			<form:errors path="cpfcnpj" cssClass="text-danger" class="has-error" />
		</div>
		<div class="col-xs-1">
			<label for="inputNome">RG*</label>
		</div>
		<div class="col-xs-2" style="margin-left: -40px">
			<form:input type="text" class="form-control"
				id="inputDataContratacao" placeholder="Digite o RG" path="rg"
				cssErrorClass="field_error form-control" />
			<form:errors path="rg" cssClass="text-danger" class="has-error" />
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
				path="orgaoExpedidor" cssErrorClass="field_error form-control" />
			<form:errors path="orgaoExpedidor" cssClass="text-danger"
				class="has-error" />
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
			<form:input required="true" type="text" class="form-control"
				id="inputDataNascimento" placeholder="Digite a Data"
				path="dataNascimento" cssErrorClass="field_error form-control" />
			<form:errors path="dataNascimento" cssClass="text-danger"
				class="has-error" />
		</div>

	</div>
	<br />

	<div class="row">
		<div class="col-xs-1">
			<label for="inputNome">Nome da Mãe*</label>
		</div>
		<div class="col-xs-5" style="margin-left: -20px">
			<form:input type="text" class="form-control" id="inputNome"
				placeholder="Digite o nome" path="nomeMae"
				cssErrorClass="field_error form-control" />
			<form:errors path="nomeMae" cssClass="text-danger" class="has-error" />
		</div>

		<div class="col-xs-1">
			<label for="inputNome">Nome do Pai*</label>
		</div>
		<div class="col-xs-5" style="margin-left: -0px">
			<form:input type="text" class="form-control" id="inputNome"
				placeholder="Digite o nome" path="nomePai"
				cssErrorClass="field_error form-control" />
			<form:errors path="nomePai" cssClass="text-danger" class="has-error" />
		</div>
	</div>
	<br />
</fieldset>
<fieldset class="content-group">

	<legend class="text-bold">Endereço e Contato </legend>


	<div class="divSeparador2">

		<div class="row">

			<div class="col-xs-1">
				<label for="inputRua">Endereço</label>
			</div>
			<div class="col-xs-5" style="margin-left: -20px">
				<form:input required="true" type="text" class="form-control"
					id="inputNome" placeholder="Digite o nome da rua" path="logradouro"
					cssErrorClass="field_error form-control" />
				<form:errors path="logradouro" cssClass="text-danger"
					class="has-error" />
			</div>
			<div class="col-xs-1">
				<label for="inputNumero">Número</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:input required="true" type="text" class="form-control"
					id="inputNome" placeholder="Digite o número" path="numero"
					cssErrorClass="field_error form-control" />
				<form:errors path="numero" cssClass="text-danger" class="has-error" />
			</div>
			<div class="col-xs-1">
				<label for="inputComplemento">Compl.</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o complemento" path="complemento"
					cssErrorClass="field_error form-control" />
				<form:errors path="complemento" cssClass="text-danger"
					class="has-error" />
			</div>
		</div>
		<br />
		<div class="row">

			<div class="col-xs-1">
				<label for="inputBairro">Bairro</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:input required="true" type="text" class="form-control"
					id="inputNome" placeholder="Digite o bairro" path="bairro"
					cssErrorClass="field_error form-control" />
				<form:errors path="bairro" cssClass="text-danger" class="has-error" />
			</div>
			<div class="col-xs-1">
				<label for="inputMunicipio">Município</label>
			</div>
			<div class="col-xs-2">
				<form:input required="true" type="text" class="form-control"
					id="inputNome" placeholder="Digite o município" path="cidade"
					cssErrorClass="field_error form-control" />
				<form:errors path="cidade" cssClass="text-danger" class="has-error" />
			</div>

			<div class="col-xs-1">
				<label for="inputCep2">CEP</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:input required="true" type="text" class="form-control"
					id="inputCep" placeholder="Digite o CEP" path="cep"
					cssErrorClass="field_error form-control" />
				<form:errors path="cep" cssClass="text-danger" class="has-error" />
			</div>
			<div class="col-xs-1">
				<label for="inputNome">UF*</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:select path="ufEnum" class="form-control"
					cssErrorClass="field_error form-control">
					<c:forEach var="ufEnum" items="${uf}">
						<option value="${ufEnum}">${ufEnum.descricao}</option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<br />
		<div class="row">

			<div class="col-xs-1">
				<label for="inputBairro">Telefone 1</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:input type="text" class="form-control" id="inputTelefone"
					placeholder="Digite o telefone" path="telefone"
					cssErrorClass="field_error form-control" />
				<form:errors path="telefone" cssClass="text-danger"
					class="has-error" />
			</div>
			<div class="col-xs-1">
				<label for="inputBairro">Telefone 2</label>
			</div>
			<div class="col-xs-2">
				<form:input type="text" class="form-control"
					id="inputTelefoneSecundario" placeholder="Digite o telefone"
					path="telefoneSecundario" cssErrorClass="field_error form-control" />
				<form:errors path="telefoneSecundario" cssClass="text-danger"
					class="has-error" />
			</div>
			<div class="col-xs-1">
				<label for="inputBairro">Celular</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:input required="true" type="text" class="form-control"
					id="inputCelular" placeholder="Digite o celular" path="celular"
					cssErrorClass="field_error form-control" />
				<form:errors path="celular" cssClass="text-danger" class="has-error" />
			</div>
		</div>
		<br />

		<div class="row">

			<div class="col-xs-1">
				<label for="inputBairro">Email</label>
			</div>
			<div class="col-xs-5" style="margin-left: -20px">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o email" path="email"
					cssErrorClass="field_error form-control" />
				<form:errors path="email" cssClass="text-danger" class="has-error" />
			</div>
		</div>
	</div>





	<div class="text-right">
		<a href="${raiz}representantes" class=" btn bg-danger abrirJanela">Cancelar</a>

		<button type="submit" class="btn btn-primary">
			Salvar <i class="icon-arrow-right14 position-right"></i>
		</button>
	</div>

</fieldset>




<script type="text/javascript"
	src="<c:url value="/resources/js/representante/representante.js" />"></script>
