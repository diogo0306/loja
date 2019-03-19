<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Agendamentos</span> - Agendamentos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Agendamentos</li>
						<li><a href="${raiz}agendamentos"> Agendamentos</a></li>

					</ul>
				</div>
			</div>


			<div class="content">

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
				
				<c:if test="${empty agendamentos}">
					<div class="alert alert-info-diogo">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						ATENÇÃO! Não existe agendamento para esse paciente.
					</div>
				</c:if>

				<div class="panel panel-flat">
					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}agendamentos" modelAttribute="agendamento">
							<div class="row">
								<div class="col-xs-1">
									<label for="field_1">Nome Paciente</label>
								</div>
								<div class="col-xs-3">
									<form:input type="text" class="form-control" id="field_1"
										placeholder="Digite o Nome do Paciente" path="paciente.nome" required="true" />
								</div>
								<div class="col-xs-1">
									<label for="field_1">RG Paciente</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control" id="field_1"
										placeholder="Digite o RG do paciente"
										path="paciente.documentacao.rg" />
								</div>
								<div class="col-xs-1">
									<label for="field_1">CPF Paciente</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control" id="field_1"
										placeholder="Digite o CPF do paciente"
										path="paciente.documentacao.cpf" />
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-xs-1">
									<label for="field_1">Data</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										id="inputDataConsulta" placeholder="Digite a data da consulta"
										path="dataConsultaFormatada" />
								</div>
							</div>
							<br />
							<div class="row">

								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}agendamento/pacientes"
										class="btn btn-primary abrirJanela">Novo agendamento</a>
									&nbsp; <a href="${raiz}agendamento"
										class="btn btn-primary abrirJanela" target="blank">Calendário</a>
								</div>
							</div>
						</form:form>
					</div>

					<!-- Highlighting rows and columns -->


					<table class="table table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th>Nome Paciente</th>
								<th>CPF</th>
								<th>RG</th>
								<th>Telefone</th>
								<th>Data Consulta</th>
								<th>Horário</th>
								<th>Status Agendamento</th>
								<th>Ação</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="agendamento" items="${agendamentos}">
								<tr>
									<td>${agendamento.nomePaciente}</td>
									<td>${agendamento.paciente.documentacao.cpf}</td>
									<td>${agendamento.paciente.documentacao.rg}</td>
									<td>${agendamento.paciente.documentacao.celular}</td>
									<td>${agendamento.dataConsultaFormatada}</td>
									<td>${agendamento.hora}</td>
									<td>${agendamento.statusAgendamentoEnum.descricao}</td>
									<td><a
										href="${raiz}agendamento/visualizar/${agendamento.id}"
										title="Visualizar Agendamento"> <span
											class="glyphicon glyphicon-search"></span>
									</a> &nbsp; &nbsp; <c:if test="${agendamento.statusAgendamentoEnum.descricao == 'Aguardando'}">
											<a href="${raiz}agendamento/alterar/${agendamento.id}"
												title="Alterar Agendamento"> <span
												class="glyphicon glyphicon-cog"></span>
											</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</div>


<script type="text/javascript"
	src="<c:url value="/resources/js/agendamento/agendamento.js" />"></script>
