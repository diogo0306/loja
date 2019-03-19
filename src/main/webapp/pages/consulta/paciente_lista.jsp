<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="2%"></th>
			<th width="30%">Nome</th>
			<th width="20%">RG</th>
			<th width="20%">CPF</th>
			<th width="14%">Telefone</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="paciente" items="${pacientes}">
			<tr>
				<td><input type="radio" name="idPaciente" value="${paciente.id}" /></td>
				<td><c:out value="${paciente.nome}" /></td>
				<td><c:out value="${paciente.documentacao.rg}" /></td>
				<td><c:out value="${paciente.documentacao.cpf}" /></td>
				<td><c:out value="${paciente.documentacao.telefone}" /></td>				
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
