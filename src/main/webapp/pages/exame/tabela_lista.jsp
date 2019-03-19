<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="40%">Nome</th>
			<th width="40%">Descrição</th>
			<th width="20%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="tabela" items="${tabelaLista}">
			<tr>
				<td><c:out value="${tabela.nome}" /></td>
				<td><c:out value="${tabela.descricao}" /></td>
				<td><a href="${raiz}tabela/visualizar/${tabela.id}"
							title="Visualizar"> <span class="glyphicon glyphicon-search"></span></a>&nbsp;&nbsp;
				 <a href="${raiz}tabela/alterar/${tabela.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span></a>&nbsp;&nbsp;						 
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
