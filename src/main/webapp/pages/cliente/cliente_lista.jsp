<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="10%">Código</th>
			<th width="40%">Nome</th>
			<th width="17%">CNPJ</th>
			<th width="15%">Telefone</th>
			<th width="8%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="cliente" items="${clientes}">
			<tr>
				<td><c:out value="${cliente.id}" /></td>
				<td><c:out value="${cliente.nome}" /></td>
				<td><c:out value="${cliente.cnpj}" /></td>
				<td><c:out value="${cliente.telefone}" /></td>
				<td><c:if test="${CADASTRO_CLIENTE_VISUALIZAR != null}">
						<a href="${raiz}cliente/visualizar/${cliente.id}"
							title="Visualizar"> <span class="glyphicon glyphicon-search"></span>
						</a>&nbsp;&nbsp;
				</c:if> <c:if test="${CADASTRO_CLIENTE_ALTERAR != null}">
						<a href="${raiz}cliente/alterar/${cliente.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span>
						</a>&nbsp;&nbsp;
				</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
