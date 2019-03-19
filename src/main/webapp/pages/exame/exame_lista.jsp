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
		<c:forEach var="exame" items="${exames}">
			<tr>
				<td><c:out value="${exame.nome}" /></td>
				<td><c:out value="${exame.descricao}" /></td>
				<td><c:if test="${CADASTRO_EXAME_ALTERAR != null}">
						<a href="${raiz}exame/alterar/${exame.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span>
						</a>&nbsp;&nbsp;
				</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
