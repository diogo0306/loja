<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />



<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="2%"></th>
			<th width="2%">Registro</th>
			<th width="24%">Nome</th>
			<th width="10%">CPF</th>
			<th width="10%">RG</th>
			<th width="15%">F. Mãe</th>
			<th width="10%">Data Nasc.</th>
			<th width="15%">Endereço</th>
			<th width="15%">Bairro</th>
			<th width="15%">Cidade</th>
			<th width="14%">Telefone</th>
		</tr>
	</thead>
	<tbody style="font-size: 12px;">
		<c:forEach var="paciente" items="${pacientes}">
			<tr>
				<td><input type="radio" name="idPaciente" value="${paciente.id}" /></td>
				<td><c:out value="${paciente.codigoPacienteLegado}" /></td>
				<td><c:out value="${paciente.nome}" /></td>
				<td><c:out value="${paciente.cpf}" /></td>
				<td><c:out value="${paciente.rg}" /></td>
				<td><c:out value="${paciente.filiacaoMae}" /></td>
				<td><c:out value="${paciente.dataNascimentoFormatada}" /></td>
				<td><c:out value="${paciente.endereco}" /></td>
				<td><c:out value="${paciente.bairro}" /></td>
				<td><c:out value="${paciente.cidade}" /></td>
				<td><c:out value="${paciente.telefone}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<tiles:insertAttribute name="paginacao" />
