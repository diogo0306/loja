<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th>Nome Paciente</th>
			<th>Telefone</th>
			<th>Data Consulta</th>
			<th>Turno</th>
			<th>Status Agendamento</th>
			<th>Tipo Agendamento</th>
			<th>Ação</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="agendamento" items="${agendamentos}">
			<tr>
				<td>${agendamento.nomePaciente}</td>
				<td>${agendamento.telefonePaciente}</td>				
				<td>${agendamento.dataConsultaFormatada}</td>
				<td>${agendamento.turnoAgendamentoEnum.descricao}</td>
				<td>${agendamento.statusAgendamentoEnum.descricao}</td>
				<td>${agendamento.tipoAgendamentoConsultaEnum.descricao}</td>
				<td><a href="${raiz}agendamento/visualizar/${agendamento.id}"
					title="Visualizar Agendamento"> <span
						class="glyphicon glyphicon-search"></span>
				</a> <a href="${raiz}agendamento/alterar/${agendamento.id}"
					title="Alterar Agendamento"> <span
						class="glyphicon glyphicon-cog"></span>
				</a> <form:form action="${raiz}agendamento/excluir" method="POST"
						modelAttribute="agendamento" class="formulario_acoes"
						id="form${agendamento.id}">
						<form:hidden path="id" value="${agendamento.id}" />
						<a href="#" data-toggle="modal"
							data-target="#modal${agendamento.id}" title="Cancelar"> <span
							class="glyphicon glyphicon-remove"></span>
						</a>
					</form:form>
					<div class="modal fade modal-sm" id="modal${agendamento.id}">
						<div class="modal-dialog modal-sm">
							<div class="modal-content modal-sm">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">Confirmação</h4>
								</div>
								<div class="modal-body">
									<p>Deseja realmente CANCELAR este agendamento?</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Não</button>
									<button type="button" class="btn btn-primary"
										onclick="submeter(form${agendamento.id})">Sim</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div> <!-- /.modal --></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
