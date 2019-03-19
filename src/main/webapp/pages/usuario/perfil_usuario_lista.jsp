<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<form:form action="${raiz}perfil/excluir-perfil" method="POST"
							modelAttribute="funcionalidadeOperacao" id="form-excluir">
	<form:hidden path="idExcluir" id="input-excluir"/>		
</form:form>

<table class="table table-striped table-hover table-bordered ">
	<thead>
		<tr>
			<th>Funcionalidade</th>
			<th>Inserir</th>
			<th>Alterar</th>
			<th>Excluir</th>
			<th>Visualizar</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="funcionalidadeOperacao"
			items="${funcionalidadesOperacao}">
			<tr>
				<td><c:out
						value="${funcionalidadeOperacao.funcionalidade.nome}" /></td>
				<td><c:if test="${funcionalidadeOperacao.inserir eq true}">
						<c:out value="SIM" />
					</c:if> <c:if test="${funcionalidadeOperacao.inserir eq false}">
						<c:out value="NÃO" />
					</c:if></td>
				<td><c:if test="${funcionalidadeOperacao.alterar eq true}">
						<c:out value="SIM" />
					</c:if> <c:if test="${funcionalidadeOperacao.alterar eq false}">
						<c:out value="NÃO" />
					</c:if></td>
				<td><c:if test="${funcionalidadeOperacao.excluir eq true}">
						<c:out value="SIM" />
					</c:if> <c:if test="${funcionalidadeOperacao.excluir eq false}">
						<c:out value="NÃO" />
					</c:if></td>
				<td><c:if test="${funcionalidadeOperacao.visualizar eq true}">
						<c:out value="SIM" />
					</c:if> <c:if test="${funcionalidadeOperacao.visualizar eq false}">
						<c:out value="NÃO" />
					</c:if></td>
					<td>
					<c:if test="${CADASTRO_PERFIL_EXCLUIR_PERFIL != null}">
						<form:form action="${raiz}perfil/excluir-perfil" method="POST"
							modelAttribute="funcionalidadeOperacao" class="formulario_acoes">
							<form:hidden path="idExcluir" value="${funcionalidadeOperacao.funcionalidade.id}-${funcionalidadeOperacao.perfil.id}" />
							<a href="javascript:;" onclick="parentNode.submit();"
								title="Excluir"> <span class="glyphicon glyphicon-remove"></span>
							</a>
						</form:form>
					</c:if></td>

			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
