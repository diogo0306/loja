<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="24%">Nome</th>
			<th width="9%">CPF</th>
			<th width="9%">Data Nasc.</th>
			<th width="14%">Último Atendimento</th>
			<th width="9%">Ações</th>
		</tr>
	</thead>
	<tbody style="font-size: 12px;">
		<c:forEach var="paciente" items="${pacientes}">
			<tr>
				<td><c:out value="${paciente.nome}" /></td>
				<td><c:out value="${paciente.cpf}" /></td>
				<td><c:out value="${paciente.dataNascimentoFormatada}" /></td>
				<td><c:out value="${paciente.dataUltimaConsultaFormatada}" /></td>
				<td><a href="${raiz}paciente/imprimir/${paciente.id}"
					target="blank" title="Imprimir"> <span
						class="glyphicon glyphicon-print"></span>
				</a>&nbsp;&nbsp; <c:if test="${CADASTRO_PACIENTE_VISUALIZAR != null}">
						<a href="${raiz}paciente/visualizar/${paciente.id}"
							title="Visualizar"> <span class="glyphicon glyphicon-search"></span>
						</a>&nbsp;&nbsp;
				</c:if> <c:if test="${CADASTRO_PACIENTE_ALTERAR != null}">
						<a href="${raiz}paciente/alterar/${paciente.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span>
						</a>&nbsp;&nbsp;
				</c:if> </td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
