
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
	<c:if test="${messageError != null}">
		<div class="row">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>${messageError}</div>
		</div>
	</c:if>


	<form:form class="form-horizontal" role="form"
		action="${raiz}agendamento/salvar" id="id-form" method="POST"
		modelAttribute="agendamento">
		<form:input path="id" type="hidden" />
		<form:input path="paciente.id" type="hidden" />

		<div class="panel panel-default">

			<div class="panel-heading">
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					<i class="fa fa-stethoscope"></i> &nbsp;&nbsp;Agendamento Consulta
					- Dados do Paciente
				</h5>
			</div>
			<div class="divSeparador2">

				<c:if test="${agendamento.paciente.id == null}">
					<div class="row">
						<div class="col-xs-6">
							<form:errors path="nomePacienteNaoCadastrado"
								cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-2">
							<label>Nome do Paciente*</label>
						</div>
						<div class="col-xs-4">
							<form:input type="text" cssClass="form-control" id="inputNome" readonly="true"
								placeholder="Digite o nome" path="nomePacienteNaoCadastrado" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-6">
							<form:errors path="nomePacienteNaoCadastrado"
								cssClass="text-danger" />
						</div>
					</div>
					<div class="row">

						<div class="col-xs-2">
							<label for="inputCep">CPF do Paciente</label>
						</div>
						<div class="col-xs-3">
							<form:input type="text" cssClass="form-control" id="inputCpf" readonly="true"
								placeholder="Digite o CPF" path="cpfPacienteNaoCadastrado" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-2">
							<label for="inputCep">RG do Paciente</label>
						</div>
						<div class="col-xs-3">
							<form:input type="text" cssClass="form-control" id="inputRg" readonly="true"
								placeholder="Digite o RG" path="rgPacienteNaoCadastrado" />
						</div>
					</div>
				</c:if>
				<c:if test="${agendamento.paciente.id != null}">
					<div class="row">
						<div class="col-xs-6">
							<form:errors path="paciente.nome" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-2">
							<label>Nome do Paciente*</label>
						</div>
						<div class="col-xs-4">
							<form:input type="text" cssClass="form-control" id="inputNome"
								readonly="true" placeholder="Digite o nome" path="paciente.nome" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-2">
							<label for="inputCep">CPF do Paciente</label>
						</div>
						<div class="col-xs-3">
							<form:input type="text" cssClass="form-control" id="inputCpf"
								readonly="true" placeholder="Digite o CPF" path="paciente.cpf" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-2">
							<label for="inputCep">RG do Paciente</label>
						</div>
						<div class="col-xs-3">
							<form:input type="text" cssClass="form-control" id="inputRg"
								readonly="true" placeholder="Digite o RG" path="paciente.rg" />
						</div>
					</div>
				</c:if>
			</div>
		</div>

		<div class="panel panel-default">

			<div class="panel-heading">
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					<i class="fa fa-stethoscope"></i> &nbsp;&nbsp;Agendamento Consulta
					- Dados do Agendamento
				</h5>
			</div>
			<div class="divSeparador2">
				<div class="row">
					<div class="col-xs-6">
						<form:errors path="dataConsultaFormatada" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<label>Data da Consulta*</label>
					</div>
					<div class="col-xs-2">
						<form:input type="text" cssClass="form-control" id="dataConsulta"
							enabled="false" disabled="true"
							placeholder="Digite a data da consulta"
							path="dataConsultaFormatada" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-6">
						<form:errors path="medico.id" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<label for="field_3">Médico*</label>
					</div>
					<div class="col-xs-4">
						<form:select path="medico.id" class="form-control" disabled="true"
							cssErrorClass="field_error form-control">
							<form:option value="" label="Selecione" />
							<form:options items="${medicos}" itemLabel="nome" itemValue="id" />
						</form:select>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-6">
						<form:errors path="planoSaude.id" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<label for="field_3">Plano*</label>
					</div>
					<div class="col-xs-3">
						<form:select path="planoSaude.id" class="form-control" disabled="true"
							cssErrorClass="field_error form-control">
							<form:option value="" label="Selecione" />
							<form:options items="${planos}" itemLabel="nome" itemValue="id" />
						</form:select>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-6">
						<form:errors path="idTipoAgendamentoConsultaTransiente"
							cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<label for="field_3">Tipo Consulta*</label>
					</div>
					<div class="col-xs-3">
						<form:select path="idTipoAgendamentoConsultaTransiente" disabled="true"
							class="form-control" cssErrorClass="field_error form-control">
							<form:option value="0" label="Selecione" />
							<form:options items="${tiposAgendamentoConsulta}"
								itemLabel="descricao" itemValue="codigo" />
						</form:select>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-6">
						<form:errors path="idTipoAgendamentoTransiente"
							cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<label for="field_3">Tipo Agendamento*</label>
					</div>
					<div class="col-xs-3">
						<form:select path="idTipoAgendamentoTransiente" disabled="true"
							class="form-control" cssErrorClass="field_error form-control">
							<form:option value="0" label="Selecione" />
							<form:options items="${tiposAgendamento}" itemLabel="descricao"
								itemValue="codigo" />
						</form:select>
					</div>
				</div>
				<br />
			</div>
		</div>

		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<a href="${raiz}agendamentos" class="btn btn-danger abrirJanela">Cancelar</a>
			</div>
		</div>
		<br />
		<br />

		<script type="text/javascript"
			src="<c:url value="/resources/js/agendamento/agendamento.js" />"></script>

	</form:form>
</div>
