<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th> Nome</th>
			<th> Login</th>
			<th >Telefone</th>
			<th >Email</th>
			<th >Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="usuario" items="${usuarios}">
			<tr>
				<td><c:out value="${usuario.nome}" /></td>
				<td><c:out value="${usuario.login}" /></td>
				<td><c:out value="${usuario.telefone}" /></td>
				<td><c:out value="${usuario.email}" /></td>
				
				<td><c:if test="${usuario.tipoUsuario.codigo != 1}">
					<c:if test="${CADASTRO_USUARIO_VISUALIZAR != null}">
							<a href="${raiz}usuario/visualizar/${usuario.id}"
								title="Visualizar"> <span class="glyphicon glyphicon-search"></span>
						</a>
					</c:if>
					<c:if test="${CADASTRO_USUARIO_ALTERAR != null}">
						<a href="${raiz}usuario/alterar/${usuario.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span>
						</a>
					</c:if>
						<a href="${raiz}usuario/gerenciar_cliente/${usuario.id}"
							title="Gerênciar Clientes"> <span
							class="glyphicon glyphicon-earphone"></span>
						</a>
							<form:form action="${raiz}usuario/resetar_senha" method="POST"
								modelAttribute="usuario" class="formulario_acoes"
								id="form_resetar_senha${usuario.id}">
								<form:hidden path="id" value="${usuario.id}" />
								<a href="#" data-toggle="modal"
									data-target="#modal_resetar_senha${usuario.id}"
									title="Resetar Senha"> <span
									class="glyphicon glyphicon-asterisk"></span>
								</a>
							</form:form>
							<div class="modal fade modal-sm"
								id="modal_resetar_senha${usuario.id}">
								<div class="modal-dialog modal-sm">
									<div class="modal-content modal-sm">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<h4 class="modal-title">Confirmação</h4>
										</div>
										<div class="modal-body">
											<p>Deseja resetar a senha do usuário?</p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Não</button>
											<button type="button" class="btn btn-primary"
												onclick="submeter(form_resetar_senha${usuario.id})">Sim</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
						</c:if>
						<c:if test="${CADASTRO_USUARIO_EXCLUIR != null}">
							<form:form action="${raiz}usuario/excluir" method="POST"
								modelAttribute="usuario" class="formulario_acoes"
								id="form${usuario.id}">
								<form:hidden path="id" value="${usuario.id}" />
								<a href="#" data-toggle="modal"
									data-target="#modal${usuario.id}" title="Excluir"> <span
									class="glyphicon glyphicon-remove"></span>
								</a>
							</form:form>
							<div class="modal fade modal-sm" id="modal${usuario.id}">
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
												onclick="submeter(form${usuario.id})">Sim</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
						</c:if>
					</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
