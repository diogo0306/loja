<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<form:input path="id" type="hidden" />
<form:input path="paciente.id" type="hidden" />

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
			<fieldset class="content-group">
				<legend class="text-bold">Dados do Beneficiário</legend>

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
							<form:input type="text" cssClass="form-control" id="inputNome"
								cssErrorClass="field_error form-control"
								placeholder="Digite o nome" path="nomePacienteNaoCadastrado" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-6">
							<form:errors path="telefonePacienteNaoCadastrado"
								cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-2">
							<label for="inputCep">Telefone do Paciente</label>
						</div>
						<div class="col-xs-3">
							<form:input type="text" cssClass="form-control" id="inputCelular"
								cssErrorClass="field_error form-control"
								placeholder="Digite o Telefone"
								path="telefonePacienteNaoCadastrado" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-2">
							<label for="inputCep">CPF do Paciente</label>
						</div>
						<div class="col-xs-3">
							<form:input type="text" cssClass="form-control" id="inputCpf"
								placeholder="Digite o CPF" path="cpfPacienteNaoCadastrado" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-2">
							<label for="inputCep">RG do Paciente</label>
						</div>
						<div class="col-xs-3">
							<form:input type="text" cssClass="form-control" id="inputRg"
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
								readonly="true" path="paciente.documentacao.cpf" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-2">
							<label for="inputCep">RG do Paciente</label>
						</div>
						<div class="col-xs-3">
							<form:input type="text" cssClass="form-control" id="inputRg"
								readonly="true" path="paciente.documentacao.rg" />
						</div>
					</div>
				</c:if>

			</fieldset>
			<fieldset class="content-group">
				<legend class="text-bold">Dados do Agendamento</legend>

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
						<form:input type="date" cssClass="form-control" required="true"
							cssErrorClass="field_error form-control"
							placeholder="Digite a data da consulta"
							path="dataConsultaFormatada" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-2">
						<label>Horário da Consulta*</label>
					</div>
					<div class="col-xs-1">
						<form:input type="text" cssClass="form-control" required="true"
							cssErrorClass="field_error form-control" placeholder="Hora"
							path="hora" />
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
						<form:select path="medico.id" class="form-control"
							cssErrorClass="field_error form-control">
							<form:option value="" label="Selecione" />
							<form:options items="${medicos}" itemLabel="nome" itemValue="id" />
						</form:select>
					</div>
				</div>
				<br />

				<div class="text-right">
					<a href="${raiz}agendamentos" class="btn btn-danger abrirJanela">Cancelar</a>

					<button type="submit" class="btn btn-primary">
						Salvar <i class="icon-arrow-right14 position-right"></i>
					</button>
				</div>

			</fieldset>

		</div>
	</div>
</div>
<script src="http://yui.yahooapis.com/3.18.1/build/yui/yui-min.js"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/agendamento/agendamento_paciente_paginacao.js" />"></script>

