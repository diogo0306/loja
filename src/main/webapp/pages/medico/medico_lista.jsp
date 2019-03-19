<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="25%">Nome</th>
			<th width="15%">Profissional</th>
			<th width="20%">Empresa</th>
			<th width="15%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="medico" items="${medicos}">
			<tr>
				<td><c:out value="${medico.nome}" /></td>
				<td><c:out value="${medico.profissionalEnum.descricao}" /></td>
				<td><c:out value="${medico.cliente.nome}" /></td>
				<td><c:if test="${CADASTRO_MEDICO_VISUALIZAR != null}">
						<a href="${raiz}medico/visualizar/${medico.id}" title="Visualizar">
							<span class="glyphicon glyphicon-search"></span>
						</a>&nbsp;&nbsp;
				</c:if> <c:if test="${CADASTRO_MEDICO_ALTERAR != null}">
						<a href="${raiz}medico/alterar/${medico.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span>
						</a>&nbsp;&nbsp;
				</c:if>
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
