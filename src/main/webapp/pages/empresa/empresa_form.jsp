
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<form:input path="id" type="hidden" />


<div class="panel-body">
	<fieldset class="content-group">
		<legend class="text-bold">Dados da Contratação</legend>

		<div class="row">

			<div class="col-xs-1">
				<label>Status*</label>
			</div>
			<div class="col-xs-2" style="margin-left: -1px">
				<form:select id="#" path="situacaoEnum" class="form-control"
					cssErrorClass="field_error form-control">
					<c:forEach var="situacaoEnum" items="${statusEnum}">
						<option value="${situacaoEnum}">${situacaoEnum.descricao}</option>
					</c:forEach>
				</form:select>
				<form:errors path="cnpj" cssClass="text-danger" class="has-error" />
			</div>

			<div class="col-xs-1 ">
				<label for="inputNome">Nome*</label>
			</div>
			<div class="col-xs-5 form-group " style="margin-left: -0px">

				<form:input required="true" type="text" class="form-control"
					id="inputNome" placeholder="Digite o nome" path="nome"
					cssErrorClass="field_error form-control has-errors" />
				<form:errors path="nome" cssClass="text-danger" class="has-error" />
			</div>

			<div class="col-xs-1" id="divCpf" style="margin-left: -0px">
				<label id="cpfOuCnpjLabel" for="cpfOuCnpj">CNPJ*</label>
			</div>
			<div class="col-xs-2" style="margin-left: -50px">
				<form:input required="true" type="text" class="form-control"
					id="inputcnpj" placeholder="Informe o numero do documento"
					path="cnpj" cssErrorClass="field_error form-control has-errors" />
				<form:errors path="cnpj" cssClass="text-danger" class="has-error" />
			</div>

		</div>


		<div class="row">

			<div class="col-xs-1">
				<label for="inputNome">Adesão*</label>
			</div>
			<div class="col-xs-2" style="margin-left: -1px">
				<form:input type="date" class="form-control" id="#"
					placeholder="Digite a Data" path="dataadesao"
					cssErrorClass="field_error form-control" />
				<form:errors path="dataadesao" cssClass="text-danger"
					class="has-error" />
			</div>

			<div class="col-xs-1">
				<label for="inputNome">Ref. p/ reajuste*</label>
			</div>
			<div class="col-xs-2" style="margin-left: -1px">
				<form:input type="text" class="form-control" id="inputReajuste"
					required="true" placeholder="Mês/Ano" path="refreajuste"
					cssErrorClass="field_error form-control" />
				<form:errors path="refreajuste" cssClass="text-danger"
					class="has-error" />
			</div>



			<div class="col-xs-1">
				<label for="inputNome">Nome fantasia</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o nome" path="nomefantasia"
					cssErrorClass="field_error form-control" />
				<form:errors path="nomefantasia" cssClass="text-danger"
					class="has-error" />
			</div>

			<div class="col-xs-1" style="margin-left: -0px">
				<label for="inputNome">Insc. Estadual</label>
			</div>
			<div class="col-xs-2" style="margin-left: -40px">
				<form:input type="text" class="form-control" id="inputCpf"
					placeholder="Digite a inscrição estadual" path="inscriestadual"
					cssErrorClass="field_error form-control" />
				<form:errors path="inscriestadual" cssClass="text-danger"
					class="has-error" />
			</div>
			<br />
		</div>

		<br />


		<div class="row">
			<div class="col-xs-1">
				<label for="inputNome">Inscrição Municipal</label>
			</div>
			<div class="col-xs-2" style="margin-left: -0px">
				<form:input type="text" class="form-control" id="inputCpf"
					placeholder="Digite a inscrição municipal" path="inscrimunicipal"
					cssErrorClass="field_error form-control" />
				<form:errors path="inscrimunicipal" cssClass="text-danger"
					class="has-error" />
			</div>


			<div class="col-xs-1">
				<label for="inputRua">E-mail</label>
			</div>
			<div class="col-xs-2" style="margin-left: -1px">
				<form:input type="text" class="form-control" id="email"
					placeholder="Digite o nome da rua" path="email"
					cssErrorClass="field_error form-control" />
				<form:errors path="email" cssClass="text-danger" class="has-error" />
			</div>
			<div class="col-xs-1">
				<label for="inputRua">Contato</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:input type="text" class="form-control" id="contato"
					placeholder="Informe o contato" path="contato"
					cssErrorClass="field_error form-control" />
				<form:errors path="contato" cssClass="text-danger" class="has-error" />
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
				<form:errors path="cnpj" cssClass="text-danger" class="has-error" />

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
				<label for="inputCep">CEP</label>
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
			</div>
			<div class="col-xs-1">
				<label for="inputMunicipio">Ramal</label>
			</div>
			<div class="col-xs-2">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o ramal" path="ramal"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">

			<div class="col-xs-1">
				<label for="inputBairro">Telefone 2</label>
			</div>
			<div class="col-xs-2" style="margin-left: -20px">
				<form:input type="text" class="form-control"
					id="inputTelefoneSecundario" placeholder="Digite o telefone"
					path="telefoneSecundario" cssErrorClass="field_error form-control" />
			</div>
			<div class="col-xs-1">
				<label for="inputMunicipio">Ramal</label>
			</div>
			<div class="col-xs-2">
				<form:input type="text" class="form-control" id="inputNome"
					placeholder="Digite o ramal" path="ramalSecundario"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">

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

	</fieldset>
	<fieldset class="content-group">
		<legend class="text-bold"> MENSALIDADE</legend>


		<div class="form-group">
			<div class="col-sm-1"
				style="width: 1%; margin-left: 16%; margin-top: 6px;">
				<form:checkbox id="boletoindividual" path="boletoindividual"
					name="boletoindividual" label="${boletoindividual}"
					value="${boletoindividual}" />
			</div>

			<label for="boletoindividual" class="col-sm-4 control-label"
				style="text-align: left;">Boleto individual por beneficiário
				titular</label>



		</div>
		<div class="form-group">
			<div class="col-sm-1"
				style="width: 1%; margin-left: 16%; margin-top: 6px;">
				<form:checkbox id="buscarvaloresdaultimavigencia"
					path="buscarvaloresdaultimavigencia"
					name="buscarvaloresdaultimavigencia"
					label="${buscarvaloresdaultimavigencia}"
					value="${buscarvaloresdaultimavigencia}" />
			</div>
			<label for="buscarvaloresdaultimavigencia"
				class="col-sm-4 control-label" style="text-align: left;">Buscar
				valores da última vigencia independente do vencimento</label>


		</div>
		<div class="form-group">
			<div class="col-sm-1"
				style="width: 1%; margin-left: 16%; margin-top: 6px;">
				<form:checkbox id="gerarcobranca" path="gerarcobranca"
					name="gerarcobranca" label="${gerarcobranca}"
					value="${gerarcobranca}" />
			</div>
			<label for="gerarcobranca" class="col-sm-4 control-label"
				style="text-align: left;">Gerar cobrança de beneficiário
				inativo (copereticipação/franquia)</label>


		</div>
		<div class="form-group">
			<div class="col-sm-1"
				style="width: 1%; margin-left: 16%; margin-top: 6px;">
				<form:checkbox id="" path="incluircoparticipacao"
					name="incluircoparticipacao" label="${incluircoparticipacao}"
					value="${incluircoparticipacao}" />
			</div>
			<label for="incluircoparticipacao" class="col-sm-4 control-label"
				style="text-align: left;">Incluir copereticipação/franquia</label>


		</div>

		<div class="form-group">
			<div class="col-sm-1"
				style="width: 1%; margin-left: 16%; margin-top: 6px;">
				<form:checkbox id="incluirprorata" path="incluirprorata"
					name="incluirprorata" label="${incluirprorata}"
					value="${incluirprorata}" />
			</div>
			<label for="incluirprorata	" class="col-sm-4 control-label"
				style="text-align: left;">Incluir pró-rata</label>

		</div>

		<div class="text-right">
			<a href="${raiz}empresas" class=" btn bg-danger abrirJanela">Cancelar</a>

			<button type="submit" class="btn btn-primary"
				id="btn-salvar_empresa_incluir">
				Salvar <i class="icon-arrow-right14 position-right"></i>
			</button>
		</div>
	</fieldset>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/empresa/empresa.js" />"></script>
