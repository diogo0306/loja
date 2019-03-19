<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="60%">Descrição</th>
			<th width="8%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="perfil" items="${perfis}">
			<tr>
				<td><c:out value="${perfil.descricao}" /></td>
				<td><a href="${raiz}perfil/adicionar-perfil/${perfil.id}"
					title="Gerênciar Perfis"> <span
						class="glyphicon glyphicon-user"></span>
				</a> <c:if test="${CADASTRO_PERFIL_VISUALIZAR != null}">
						<a href="${raiz}perfil/visualizar/${perfil.id}" title="Visualizar">
							<span class="glyphicon glyphicon-search"></span>
						</a>
					</c:if> <c:if test="${CADASTRO_PERFIL_ALTERAR != null}">
						<a href="${raiz}perfil/alterar/${perfil.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span>
						</a>
					</c:if> <c:if test="${CADASTRO_PERFIL_EXCLUIR != null}">
						<form:form action="${raiz}perfil/excluir" method="POST"
							modelAttribute="perfil" class="formulario_acoes"
							id="form${perfil.id}">
							<form:hidden path="id" value="${perfil.id}" />
							<a href="#" data-toggle="modal" data-target="#modal${perfil.id}"
								title="Excluir"> <span class="glyphicon glyphicon-remove"></span>
							</a>
						</form:form>
						<div class="modal fade modal-sm" id="modal${perfil.id}">
							<div class="modal-dialog modal-sm">
								<div class="modal-content modal-sm">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title">Confirmação</h4>
									</div>
									<div class="modal-body">
										<p>Deseja realmente excluir este item?</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Não</button>
										<button type="button" class="btn btn-primary"
											onclick="submeter(form${perfil.id})">Sim</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
					</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
