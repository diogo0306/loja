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
							readonly="true" placeholder="Digite o CPF"
							path="paciente.documentacao.cpf" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-2">
						<label for="inputCep">RG do Paciente</label>
					</div>
					<div class="col-xs-3">
						<form:input type="text" cssClass="form-control" id="inputRg"
							readonly="true" placeholder="Digite o RG"
							path="paciente.documentacao.rg" />
					</div>
				</div>

			</fieldset>
			<fieldset class="content-group">
				<legend class="text-bold">Dados da Consulta</legend>

				<div class="row">
					<div class="col-xs-6">
						<form:errors path="dataConsultaFormatada" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<label>Data da Consulta</label>
					</div>
					<div class="col-xs-2">
						<label>${dataConsulta}</label>
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
					<div class="col-xs-2">
						<form:select path="idTipoAgendamentoTransiente"
							class="form-control" cssErrorClass="field_error form-control">
							<form:option value="0" label="Selecione" />
							<form:options items="${tiposAgendamento}" itemLabel="descricao"
								itemValue="codigo" />
						</form:select>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-2">
						<label for="field_3">CPF Contratante</label>
					</div>
					<div class="col-xs-2">
						<form:input type="text" cssClass="form-control cpf"
							name="inputCpfPagador" id="inputCpfPagador" path="cpfPagador" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-2">
						<label for="field_3">Valor Consulta R$</label>
					</div>
					<div class="col-xs-1">
						<form:input type="text" cssClass="form-control valor"
							name="inputValorConsulta" id="inputValorConsulta"
							path="valorConsulta" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-2">
						<label for="field_3">Observação</label>
					</div>
					<div class="col-xs-4">
						<form:input type="text" cssClass="form-control" id="inputRg"
							path="observacaoConsulta" />
					</div>
				</div>
				<br />

				<div class="text-right">
					<a href="${raiz}consultas" class="btn btn-danger abrirJanela">Cancelar</a>

					<button type="submit" class="btn btn-primary">
						Salvar <i class="icon-arrow-right14 position-right"></i>
					</button>
				</div>

			</fieldset>

		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/consulta/consulta.js" />"></script>