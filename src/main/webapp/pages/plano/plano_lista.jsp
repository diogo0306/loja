<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="40%">Nome</th>
			<th width="40%">Tipo</th>
			<th width="20%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="plano" items="${planos}">
			<tr>
				<td><c:out value="${plano.nome}" /></td>
				<td><c:out value="${plano.tipoPlanoSaudeEnum.descricao}" /></td>
				<td><c:if test="${CADASTRO_PLANO_VISUALIZAR != null}">
						<a href="${raiz}plano/visualizar/${plano.id}"
							title="Visualizar"> <span class="glyphicon glyphicon-search"></span>
						</a>&nbsp;&nbsp;
				</c:if> <c:if test="${CADASTRO_PLANO_ALTERAR != null}">
						<a href="${raiz}plano/alterar/${plano.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span>
						</a>&nbsp;&nbsp;
				</c:if>
				 <c:if test="${CADASTRO_PLANO_EXCLUIR != null}">
						<form:form action="${raiz}plano/excluir" method="POST"
							modelAttribute="plano" class="formulario_acoes" id="form${plano.id}">
							<form:hidden path="id" value="${plano.id}" />
							<a href="#" data-toggle="modal" data-target="#modal${plano.id}"
								title="Excluir"> <span class="glyphicon glyphicon-remove"></span>
							</a>
						</form:form>
						<div class="modal fade modal-sm" id="modal${plano.id}">
							 <div class="modal-dialog modal-sm">
							   <div class="modal-content modal-sm">
							     <div class="modal-header">
							       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							       <h4 class="modal-title">Confirmação</h4>
							     </div>
							     <div class="modal-body">
							       <p>Deseja realmente excluir este item?</p>
							     </div>
							     <div class="modal-footer">
							       <button type="button" class="btn btn-default" data-dismiss="modal">Não</button>
							       <button type="button" class="btn btn-primary" onclick="submeter(form${plano.id})">Sim</button>
							     </div>
							   </div><!-- /.modal-content -->
							 </div><!-- /.modal-dialog -->
						</div><!-- /.modal -->
					</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
