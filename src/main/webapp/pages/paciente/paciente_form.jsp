<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}paciente/salvar_paciente"
	id="url-salvar" />

<form:input path="id" type="hidden" />

<div class="panel panel-default">
	<div class="panel-heading">
		<h5
			style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
			<i class="fa fa-credit-card"></i> &nbsp;&nbsp; Dados Cadastrais
		</h5>
	</div>

	<div class="divSeparador2">

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
					placeholder="Digite o CPF" path="paciente.documentacao.cpf"
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
					path="paciente.documentacao.orgaoEmissorTransiente" class="form-control"
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
					path="paciente.endereco.logradouro" cssErrorClass="field_error form-control" />
			</div>

			<div class="col-xs-1">
				<label for="inputNome">Bairro</label>
			</div>
			<div class="col-xs-2">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o bairro" path="paciente.endereco.bairro"
					cssErrorClass="field_error form-control" />
			</div>

		</div>

		<div class="form-group">

			<div class="col-xs-1">
				<label for="field_3">Cidade*</label>
			</div>
			<div class="col-xs-2">
				<form:input type="text" class="form-control" id="inputCidade"
					placeholder="Digite a Cidade" path="paciente.endereco.cidade"
					cssErrorClass="field_error form-control" />
			</div>

			<div class="col-xs-1">
				<label for="inputNome">UF*</label>
			</div>
			<div class="col-xs-2">
				<form:select id="selectTipoSexo"
					path="paciente.endereco.estadoTransiente" class="form-control"
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
				<form:input type="text" class="form-control" id="inputNumero"
					placeholder="Digite o número" path="paciente.endereco.numero"
					cssErrorClass="field_error form-control" />
			</div>

			<div class="col-xs-1">
				<label for="inputNome">Complemento</label>
			</div>
			<div class="col-xs-2">
				<form:input type="text" class="form-control" id="inputComplemento"
					placeholder="Complemento" path="paciente.endereco.complemento"
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
				<form:input type="text" class="form-control" id="inputTelefone"
					placeholder="(99) 9999-9999" path="paciente.documentacao.telefone"
					cssErrorClass="field_error form-control" />
			</div>
			
			<div class="col-xs-1">
				<label for="inputDataNascimento">Celular</label>
			</div>

			<div class="col-xs-2">
				<form:input type="text" class="form-control" id="inputCelular"
					placeholder="(99) 99999-9999" path="paciente.documentacao.celular"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		
		<div class="form-group">

			<div class="col-xs-1">
				<label for="inputNome">E-mail</label>
			</div>

			<div class="col-xs-8">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o e-mail" path="paciente.documentacao.email"
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
				<label for="inputNome">Nome da Pai</label>
			</div>

			<div class="col-xs-8">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o Nome Completo do Pai do Titular"
					path="paciente.filiacaoPai"
					cssErrorClass="field_error form-control" />
			</div>
		</div>

	</div>

</div>


<!-- 

<div class="panel panel-default">
	<div class="panel-heading">
		<h5
			style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
			<i class="fa fa-credit-card"></i> &nbsp;&nbsp; Dependente(s)
		</h5>
	</div>

	<div class="divSeparador2">

		<div class="form-group">

			<div class="col-xs-1">
				<label for="inputNome">Nome*</label>
			</div>
			<div class="col-xs-8">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o Nome Completo" path="dependente.nome"
					cssErrorClass="field_error form-control" />
			</div>

			<div class="col-xs-1">
				<label for="inputDataNascimento2">Data Nascimento.*</label>
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
				<form:input type="text" class="form-control" id="inputNome"
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
				<form:select id="selectTipoSexo" path="sexoTransienteDependente"
					class="form-control" cssErrorClass="field_error form-control">
					<form:option value="" label="Selecione" />
					<form:options items="${sexos}" itemLabel="descricao"
						itemValue="codigo" />
				</form:select>
			</div>

			<div class="col-xs-1">
				<label for="inputNome">CPF*</label>
			</div>
			<div class="col-xs-2">
				<form:input type="text" class="form-control" id="inputCpf2"
					placeholder="Digite o CPF" path="dependente.cpf"
					cssErrorClass="field_error form-control" />
			</div>

			<div class="col-xs-1">
				<label for="inputNome">RG*</label>
			</div>
			<div class="col-xs-2">
				<form:input type="text" class="form-control" id="inputRg"
					placeholder="Digite o RG" path="dependente.rg"
					cssErrorClass="field_error form-control" />
			</div>

			<div class="col-xs-1">
				<label for="inputNome">Orgão Expedidor*</label>
			</div>
			<div class="col-xs-2">
				<form:input type="text" class="form-control" id="inputOrgao"
					placeholder="Digite o Orgão Expedidor"
					path="dependente.orgaoExpedidor"
					cssErrorClass="field_error form-control" />
			</div>

		</div>
	</div>
</div>

<div class="form-inline">
	<button class="btn btn-success abrirJanela" style="width: 100%;">Adicionar</button>
</div>
<br />

<div class="panel panel-default">
	<div class="panel-heading">
		<h5
			style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
			<i class="fa fa-credit-card"></i> &nbsp;&nbsp; Lista - Dependente(s)
		</h5>
	</div>

	<div class="divSeparador2"
		style="max-height: 400px; overflow: scroll; overflow-x: hidden;">
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
						items="${solicitacaoDTO.listaDependente}" varStatus="status">
						<tr>
							<td><c:out value="${dependente.nome}" /></td>
							<td><c:out value="${dependente.cpf}" /></td>
							<td><c:out value="${dependente.rg}" /></td>
							<td><c:out value="${dependente.dataFormatada}" /></td>


							<form:input path="listaDependente[${status.index}].nome"
								type="hidden" />
							<form:input path="listaDependente[${status.index}].cpf"
								type="hidden" />
							<form:input path="listaDependente[${status.index}].dataFormatada"
								type="hidden" />
							<form:input path="listaDependente[${status.index}].rg"
								type="hidden" />
							<form:input path="listaDependente[${status.index}].filiacaoMae"
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
	</div>

</div> 

 -->

<div class="form-inline">
	<button class="btn btn-success" style="width: 100%;"
		id="btn-salvar_paciente">Salvar</button>
</div>
<br />


<script type="text/javascript"
	src="<c:url value="/resources/js/paciente/paciente.js" />"></script>
