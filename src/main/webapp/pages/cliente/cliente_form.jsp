<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />


<div class="panel-body">
	<fieldset class="content-group">
		<legend class="text-bold">Dados da Contratação</legend>


			<form:input path="id" type="hidden" />
			
			<div class="divSeparador2">
				<div class="row">
				</div>
				<c:if test="${campos_obrigatorios != null}">
					<div class="row">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>${campos_obrigatorios}</div>
					</div>
				</c:if>
			</div>
			<div class="row">
				<div class="col-xs-1">
					<label for="inputNome">Razao Social*</label>
				</div>
				<div class="col-xs-4">
					<form:input required="true" type="text" class="form-control" id="inputNome"
						placeholder="Digite o nome" path="nome"
						cssErrorClass="field_error form-control" />
						<form:errors path="nome" cssClass="text-danger" />
				</div>
				<div class="col-xs-1">
					<label for="inputNome">Nome Fantasia</label>
				</div>
				<div class="col-xs-4">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o nome" path="fantasia"
						cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-xs-1">
					<label for="inputCnpj">CNPJ*</label>
				</div>
				<div class="col-xs-4">
					<form:input required="true" type="text" class="form-control" id="inputCnpj"
						placeholder="Digite o cnpj" disabled="${alterar}" path="cnpj"
						cssErrorClass="field_error form-control" />
						<form:errors path="cnpj" cssClass="text-danger" />

					<c:if test="${alterar eq true}">
						<form:input path="cnpj" type="hidden" />
					</c:if>

				</div>

				<div class="col-xs-1">
					<label for="inputCnae">CNAE</label>
				</div>
				<div class="col-xs-4">
					<form:input type="text" class="form-control" id="inputCnae"
						placeholder="Digite o numero" path="cnae"
						cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-xs-1">
					<label for="inputTexto">Descrição do CNAE</label>
				</div>
				<div class="col-xs-11"
					style="margin: 0px -97px 0px 0px; width: 662px; height: 72px;">
					<form:textarea maxlength="254" rows="3" cols="20"
						class="form-control" id="inputTexto"
						placeholder="Até 254 caracteres" path="cnaeDescricao"
						cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />

			

			<div class="row">
				<div class="col-xs-1">
					<label for="field_1">CEP*</label>
				</div>
				<div class="col-xs-3">
					<form:input required="true" type="text" cssClass="form-control" id="inputCep"
						placeholder="Digite o CEP" path="cep"
						cssErrorClass="field_error form-control" />
						<form:errors path="cep" cssClass="text-danger" />
				</div>
				<div class="col-xs-1" style="width: 5%">
					<label for="field_1">Estado*</label>
				</div>
				<div class="col-xs-3"  style="margin-left: 26px">
					<form:input required="true" type="text" cssClass="form-control" id="inputRua"
						placeholder="Digite o estado" path="estado"
						cssErrorClass="field_error form-control" />
						<form:errors path="estado" cssClass="text-danger" />
				</div>
				<div class="col-xs-1" style="width: 5%">
					<label for="field_1">Cidade*</label>
				</div>
				<div class="col-xs-3">
					<form:input required="true" type="text" cssClass="form-control" id="inputRua"
						placeholder="Digite a cidade" path="cidade"
						cssErrorClass="field_error form-control" />
						<form:errors path="cidade" cssClass="text-danger" />
				</div>
			</div>
			<br />

			
			<div class="row">
				<div class="col-xs-1">
					<label for="field_1">Bairro*</label>
				</div>
				<div class="col-xs-3">
					<form:input required="true" type="text" cssClass="form-control" id="inputBairro"
						placeholder="Digite o bairro" path="bairro"
						cssErrorClass="field_error form-control" />
						<form:errors path="bairro" cssClass="text-danger" />
				</div>
				<div class="col-xs-1">
					<label for="field_1">Logradouro*</label>
				</div>
				<div class="col-xs-3" style="margin-left: -10px">
					<form:input required="true" type="text" cssClass="form-control" id="inputRua"
						placeholder="Digite a Rua e número" path="logradouro"
						cssErrorClass="field_error form-control" />
						<form:errors path="logradouro" cssClass="text-danger" />
				</div>
			</div>

			<br /> 
			
			<div class="row">
				<div class="col-xs-1">
					<label for="inputTelefone">Telefone*</label>
				</div>
				<div class="col-xs-3" >
					<form:input required="true" type="text" class="form-control" id="inputTelefone"
						placeholder="Digite o número fixo" path="telefone"
						cssErrorClass="field_error form-control" />
						<form:errors path="telefone" cssClass="text-danger" />
				</div>
				<div class="col-xs-1">
					<label for="inputTelefoneSec">Telefone 2</label>
				</div>
				<div class="col-xs-3" style="margin-left: -10px">
					<form:input type="text" class="form-control" id="inputTelefoneSec"
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
					<form:input type="text" class="form-control" id="inputCelular"
						placeholder="Digite o número" path="celular"
						cssErrorClass="field_error form-control" />
				</div>
				<div class="col-xs-1">
					<label for="inputCelularSec">Celular 2</label>
				</div>
				<div class="col-xs-3" style="margin-left: -10px">
					<form:input type="text" class="form-control" id="inputCelularSec"
						placeholder="Digite o número" path="celularSecundario"
						cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />


			<div class="text-right">
				<a href="${raiz}clientes" class=" btn bg-danger abrirJanela">Cancelar</a>

				<button type="submit" class="btn btn-primary">
					Salvar <i class="icon-arrow-right14 position-right"></i>
				</button>
			</div>

	</fieldset>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/cliente/cliente.js" />"></script>