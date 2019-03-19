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

				<div class="row">
					<div class="alert alert-danger alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<strong>ATENÇÃO!</strong> Selecione um Paciente para o agendamento
						da consulta e clique em avançar ou apenas clique em avançar caso o
						paciente não esteja cadastrado.
					</div>
				</div>

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

				<div class="panel panel-flat">
					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}agendamento/pacientes" modelAttribute="paciente">

							<div class="row">
								<div class="col-xs-1">
									<label for="field_1">Nome</label>
								</div>
								<div class="col-xs-3">
									<form:input type="text" class="form-control" id="field_1"
										placeholder="Digite o nome" path="nome" />
								</div>
								<div class="col-xs-1">
									<label for="field_1">RG</label>
								</div>
								<div class="col-xs-3">
									<form:input type="text" class="form-control" id="field_1"
										placeholder="Digite o RG" path="rg" />
								</div>
								<div class="col-xs-1">
									<label for="field_1">CPF</label>
								</div>
								<div class="col-xs-3">
									<form:input type="text" class="form-control" id="inputCep"
										placeholder="Digite o CPF" path="cpf" />

								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
								</div>
							</div>
						</form:form>
					</div>

					<!-- Highlighting rows and columns -->


					<table class="table table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="2%"></th>
								<th width="20%">Nome</th>
								<th width="10%">CPF</th>
								<th width="10%">RG</th>
								<th width="10%">Data Nasc.</th>
								<th width="15%">Endereço</th>
								<th width="12%">Bairro</th>
								<th width="12%">Cidade</th>
								<th width="20%">Telefone</th>
							</tr>
						</thead>
						<tbody style="font-size: 12px;">
							<c:forEach var="paciente" items="${pacientes}">
								<tr>
									<td><input type="radio" name="idPaciente"
										value="${paciente.id}" /></td>
									<td><c:out value="${paciente.nome}" /></td>
									<td><c:out value="${paciente.documentacao.cpf}" /></td>
									<td><c:out value="${paciente.documentacao.rg}" /></td>
									<td><c:out value="${paciente.dataNascimentoFormatada}" /></td>
									<td><c:out value="${paciente.endereco.logradouro}" /></td>
									<td><c:out value="${paciente.endereco.bairro}" /></td>
									<td><c:out value="${paciente.endereco.cidade}" /></td>
									<td><c:out value="${paciente.documentacao.telefone}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="row">

					<div class="col-xs-offset-5 col-xs-1">
						<a href="${raiz}agendamentos" class="btn btn-danger abrirJanela">Cancelar</a>
					</div>
					<div class="col-xs-offset-0 col-xs-1">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}agendamento/selecionar/paciente"
							id="form-selecionar" commandName="paciente">
							<form:hidden path="id" id="id-paciente" />
						</form:form>
						<a href="#" class="btn btn-primary abrirJanela"
							id="a-selecionar-paciente">Avançar</a>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>


<script type="text/javascript"
	src="<c:url value="/resources/js/agendamento/agendamento_paciente_paginacao.js" />"></script>

