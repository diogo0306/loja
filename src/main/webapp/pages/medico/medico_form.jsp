<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />


<div class="content">
	<c:if test="${messageError != null}">
		<div class="row">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				${messageError}
			</div>
		</div>
	</c:if>

	<div class="panel-body">
		<fieldset class="content-group">
			<legend class="text-bold">Dados da Contratação</legend>

			<div class="row">
				<div class="col-xs-1">
					<label for="inputNome">Nº Contrato*</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o número" path="medico.numeroContrato"
						cssErrorClass="field_error form-control" />
				</div>

				<div class="col-xs-1">
					<label for="field_3">Tipo de Contrato*</label>
				</div>
				<div class="col-xs-2" style="margin-left: -0px">
					<form:select id="selectTipoContrato" required="true"
						path="medico.tipoContratoTransiente" class="form-control"
						cssErrorClass="field_error form-control">
						<form:option value="" label="Selecione" />
						<form:options items="${tipoContrato}" itemLabel="descricao"
							itemValue="codigo" />
						<form:errors path="medico.tipoContratoTransiente"
							cssClass="text-danger" class="has-error" />
					</form:select>
				</div>
				<div class="col-xs-1">
					<label for="inputNome">Data de Contratação*</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" required="true"
						id="inputDataContratacao" placeholder="Digite a Data"
						path="dataContratacaoFormatada"
						cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-xs-1">
					<label for="inputNome">Nome*</label>
				</div>
				<div class="col-xs-5" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o nome" path="medico.nome" required="true"
						cssErrorClass="field_error form-control" />
					<form:errors path="medico.nome" cssClass="text-danger"
						class="has-error" />
				</div>
				<div class="col-xs-1">
					<label for="inputNome">CPF</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputCpf"
						placeholder="Digite o CPF" path="medico.cpf" disabled="true"
						cssErrorClass="field_error form-control" />
					<form:errors path="medico.cpf" cssClass="text-danger"
						class="has-error" />
				</div>

				<div class="col-xs-1">
					<label for="inputNome">CNPJ*</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputCnpj"
						placeholder="Digite o CNPJ" path="medico.cnpj" disabled="true"
						cssErrorClass="field_error form-control" />
					<form:errors path="medico.cnpj" cssClass="text-danger"
						class="has-error" />
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-xs-1">
					<label for="inputNome">Número Conselho</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o número" path="medico.crm" required="true"
						cssErrorClass="field_error form-control" />
				</div>
				<div class="col-xs-1" style="margin-left: 20px">
					<label for="inputNome">RG*</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o RG" path="medico.rg" required="true"
						cssErrorClass="field_error form-control" />
					<form:errors path="medico.rg" cssClass="text-danger"
						class="has-error" />
				</div>
				<div class="col-xs-1">
					<label for="inputNome">Órgão Expedidor*</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o órgão" path="medico.orgaoExpedidor"
						cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-xs-1">
					<label for="field_3">Sexo*</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:select id="selectTipoPlano" required="true"
						path="medico.codigoSexoTransiente" class="form-control"
						cssErrorClass="field_error form-control">
						<form:option value="" label="Selecione" />
						<form:options items="${sexos}" itemLabel="descricao"
							itemValue="codigo" />
						<form:errors path="medico.codigoSexoTransiente"
							cssClass="text-danger" class="has-error" />
					</form:select>
				</div>
				<div class="col-xs-1">
					<label for="inputNome">Data de Nascimento*</label>
				</div>
				<div class="col-xs-2" style="margin-left: -1px">
					<form:input type="text" class="form-control"
						id="inputDataNascimento" placeholder="Digite a Data"
						path="dataNascimentoFormatada" required="true"
						cssErrorClass="field_error form-control" />
				</div>

			</div>
			<br />

		</fieldset>

		<fieldset class="content-group">
			<legend class="text-bold">Endereço e Contato</legend>

			<div class="row">

				<div class="col-xs-1">
					<label for="inputRua">Endereço</label>
				</div>
				<div class="col-xs-5" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						required="true" placeholder="Digite o nome da rua"
						path="medico.logradouro" cssErrorClass="field_error form-control" />
				</div>
				<div class="col-xs-1">
					<label for="inputNumero">Número</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o número" path="medico.numero"
						cssErrorClass="field_error form-control" />
				</div>
				<div class="col-xs-1">
					<label for="inputComplemento">Compl.</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o complemento" path="medico.complemento"
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
						placeholder="Digite o bairro" path="medico.bairro"
						cssErrorClass="field_error form-control" />
				</div>
				<div class="col-xs-1">
					<label for="inputMunicipio">Município</label>
				</div>
				<div class="col-xs-2">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o município" path="medico.cidade"
						required="true" cssErrorClass="field_error form-control" />
				</div>

				<div class="col-xs-1">
					<label for="inputCep2">CEP</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputCep2"
						placeholder="Digite o CEP" path="medico.cep" required="true"
						cssErrorClass="field_error form-control" />
				</div>
				<div class="col-xs-1">
					<label for="inputNome">UF*</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:select path="medico.codigoUfTransiente" class="form-control"
						required="true" cssErrorClass="field_error form-control">
						<form:option value="" label="Selecione" />
						<form:options items="${uf}" itemLabel="descricao"
							itemValue="codigo" />
					</form:select>
					<form:errors path="medico.codigoUfTransiente"
						cssClass="text-danger" class="has-error" />
				</div>
			</div>
			<br />
			<div class="row">

				<div class="col-xs-1">
					<label for="inputBairro">Telefone 1</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputTelefone"
						placeholder="Digite o telefone" path="medico.telefone"
						cssErrorClass="field_error form-control" />
				</div>
				<div class="col-xs-1">
					<label for="inputMunicipio">Ramal</label>
				</div>
				<div class="col-xs-2">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o ramal" path="medico.ramal"
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
						path="medico.telefoneSecundario"
						cssErrorClass="field_error form-control" />
				</div>
				<div class="col-xs-1">
					<label for="inputMunicipio">Ramal</label>
				</div>
				<div class="col-xs-2">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o ramal" path="medico.ramalSecundario"
						cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />
			<div class="row">

				<div class="col-xs-1">
					<label for="inputBairro">Celular</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputCelular"
						placeholder="Digite o celular" path="medico.celular"
						required="true" cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />
			<div class="row">

				<div class="col-xs-1">
					<label for="inputBairro">Email</label>
				</div>
				<div class="col-xs-4" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o email" path="medico.email"
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
					<form:select path="especialidade.id" id="inputRepresentante"
						required="true" class="form-control"
						cssErrorClass="field_error form-control">
						<form:option value="" label="Selecione a especialidade" />
						<form:options items="${especialidades}" itemLabel="especialidade"
							itemValue="id" />
						<form:input path="especialidade.id" type="hidden" />
						<form:input path="especialidade.especialidade" type="hidden" />
					</form:select>
				</div>
			</div>
			<br />

		</fieldset>

		<fieldset class="content-group">
			<legend class="text-bold">Agenda</legend>

			<div class="row checkbox-form">

				<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
					<form:checkbox id="domingo" path="agenda.domingo" name="domingo"
						label="${agenda.domingo}" value="${agenda.domingo}" />
				</div>
				<label for="inputDomingo" class="col-sm-1 control-label"
					style="text-align: left; margin-top: -6px">DOMINGO</label>

				<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
					<form:checkbox id="segunda" path="agenda.segunda" name="segunda"
						label="${agenda.segunda}" value="${agenda.segunda}" />
				</div>
				<label for="inputSegunda" class="col-sm-1 control-label"
					style="text-align: left; margin-top: -6px">SEGUNDA</label> <br />

				<div class="col-sm-1"
					style="width: 1%; margin-left: 0%; margin-top: -19px">
					<form:checkbox id="terca" path="agenda.terca" name="terca"
						label="${agenda.terca}" value="${agenda.terca}" />
				</div>
				<label for="inputDomingo" class="col-sm-1 control-label"
					style="text-align: left; margin-top: -26px">TERÇA</label>

				<div class="col-sm-1"
					style="width: 1%; margin-left: 0%; margin-top: -19px">
					<form:checkbox id="quarta" path="agenda.quarta" name="quarta"
						label="${agenda.quarta}" value="${agenda.quarta}" />
				</div>
				<label for="inputDomingo" class="col-sm-1 control-label"
					style="text-align: left; margin-top: -26px">QUARTA</label>

				<div class="col-sm-1"
					style="width: 1%; margin-left: 0%; margin-top: -19px">
					<form:checkbox id="quinta" path="agenda.quinta" name="quinta"
						label="${agenda.quinta}" value="${agenda.quinta}" />
				</div>
				<label for="inputDomingo" class="col-sm-1 control-label"
					style="text-align: left; margin-top: -26px">QUINTA</label>

				<div class="col-sm-1"
					style="width: 1%; margin-left: 0%; margin-top: -19px">
					<form:checkbox id="sexta" path="agenda.sexta" name="sexta"
						label="${agenda.sexta}" value="${agenda.sexta}" />
				</div>
				<label for="inputDomingo" class="col-sm-1 control-label"
					style="text-align: left; margin-top: -26px">SEXTA</label>

				<div class="col-sm-1"
					style="width: 1%; margin-left: 0%; margin-top: -19px">
					<form:checkbox id="sabado" path="agenda.sabado" name="sabado"
						label="${agenda.sabado}" value="${agenda.sabado}" />
				</div>
				<label for="inputDomingo" class="col-sm-1 control-label"
					style="text-align: left; margin-top: -26px">SÁBADO</label>

			</div>
			<br />

			<div class="row">

				<div class="col-lg-2" style="margin-top: 10px">
					<label for="inputBairro">Quantidade de Consultas</label>
				</div>
				<div class="col-xs-2">
					<form:input type="number" class="form-control" id="inputCelular"
						placeholder="Digite o número" path="agenda.quantidade"
						cssErrorClass="field_error form-control" />
				</div>

				<div class="col-lg-2" style="margin-top: 10px; margin-left: 10px">
					<label for="inputBairro">Duração da Consulta</label>
				</div>
				<div class="col-xs-2" style="margin-left: -15px">
					<form:input type="text" class="form-control" id="inputDuracao"
						placeholder="Digite a duração" path="agenda.duracao"
						required="true" cssErrorClass="field_error form-control" />
				</div>

				<div class="col-lg-2" style="margin-top: 10px; margin-left: 10px">
					<label for="inputBairro">Valor da Consulta</label>
				</div>
				<div class="col-xs-2" style="margin-left: -30px">
					<form:input type="text" class="form-control" required="true"
						id="inputValorConsulta" placeholder="Digite a duração"
						path="valorFormatado" cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />

		</fieldset>

		<fieldset class="content-group">
			<legend class="text-bold">Jornada</legend>

			<div class="row">

				<div class="col-xs-1"
					style="margin-left: 21px !important; margin-top: 16px !important;">
					<label>Das:</label>
				</div>

				<div class="col-xs-2"
					style="margin-left: -40px !important; margin-top: 11px !important; height: 33px !important">
					<form:select id="jornadaInicio" path="jornada.inicio"
						class="form-control">
						<option value="0">Selecione</option>
						<option value="00:00">00:00</option>
						<option value="01:00">01:00</option>
						<option value="02:00">02:00</option>
						<option value="03:00">03:00</option>
						<option value="04:00">04:00</option>
						<option value="05:00">05:00</option>
						<option value="06:00">06:00</option>
						<option value="07:00">07:00</option>
						<option value="08:00">08:00</option>
						<option value="09:00">09:00</option>
						<option value="10:00">10:00</option>
						<option value="11:00">11:00</option>
						<option value="12:00">12:00</option>
						<option value="13:00">13:00</option>
						<option value="14:00">14:00</option>
						<option value="15:00">15:00</option>
						<option value="16:00">16:00</option>
						<option value="17:00">17:00</option>
						<option value="18:00">18:00</option>
						<option value="19:00">19:00</option>
						<option value="20:00">20:00</option>
						<option value="21:00">21:00</option>
						<option value="22:00">22:00</option>
						<option value="23:00">23:00</option>
					</form:select>
				</div>

				<div class="col-xs-1" style="margin-top: 16px !important;">
					<label>Até</label>
				</div>

				<div class="col-xs-2"
					style="margin-left: -40px !important; margin-top: 11px !important; height: 33px !important">
					<form:select id="jornadaFim" path="jornada.fim"
						class="form-control">
						<option value="0">Selecione</option>
						<option value="00:00">00:00</option>
						<option value="01:00">01:00</option>
						<option value="02:00">02:00</option>
						<option value="03:00">03:00</option>
						<option value="04:00">04:00</option>
						<option value="05:00">05:00</option>
						<option value="06:00">06:00</option>
						<option value="07:00">07:00</option>
						<option value="08:00">08:00</option>
						<option value="09:00">09:00</option>
						<option value="10:00">10:00</option>
						<option value="11:00">11:00</option>
						<option value="12:00">12:00</option>
						<option value="13:00">13:00</option>
						<option value="14:00">14:00</option>
						<option value="15:00">15:00</option>
						<option value="16:00">16:00</option>
						<option value="17:00">17:00</option>
						<option value="18:00">18:00</option>
						<option value="19:00">19:00</option>
						<option value="20:00">20:00</option>
						<option value="21:00">21:00</option>
						<option value="22:00">22:00</option>
						<option value="23:00">23:00</option>
					</form:select>
				</div>
			</div>
			<br />

		</fieldset>

		<fieldset class="content-group">
			<legend class="text-bold">Responsável</legend>

			<div class="row">
				<div class="col-xs-1">
					<label for="inputRua">Nome</label>
				</div>
				<div class="col-xs-5" style="margin-left: -20px">
					<form:input type="text" class="form-control" id="inputNome"
						placeholder="Digite o nome" path="responsavel.nome"
						cssErrorClass="field_error form-control" />
				</div>
				<div class="col-xs-1">
					<label for="inputNumero">CPF</label>
				</div>
				<div class="col-xs-2" style="margin-left: -20px">
					<form:input type="text" class="form-control"
						id="inputCpfResponsavel" placeholder="Digite o CPF"
						path="responsavel.cpf" cssErrorClass="field_error form-control" />
				</div>
			</div>
			<br />

			<div class="text-right">
				<a href="${raiz}profissionais" class=" btn bg-danger abrirJanela">Cancelar</a>

				<button type="submit" class="btn btn-primary"
					id="btn-salvar_medico_form" disabled="disabled">
					Salvar <i class="icon-arrow-right14 position-right"></i>
				</button>
			</div>

		</fieldset>

	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/medico/medico.js" />"></script>

