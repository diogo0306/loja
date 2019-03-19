<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="40%">Competência</th>
			<th width="40%">Nome</th>
			<th width="20%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="indice" items="${indices}">
			<tr>
				<td><c:out value="${indice.competencia}" /></td>
				<td><c:out value="${indice.nome}" /></td>
				<td><c:if test="${CADASTRO_INDICE_VISUALIZAR != null}">
						<a href="${raiz}indice/visualizar/${indice.id}"
							title="Visualizar"> <span class="glyphicon glyphicon-search"></span>
						</a>&nbsp;&nbsp;
				</c:if> <c:if test="${CADASTRO_INDICE_ALTERAR != null}">
						<a href="${raiz}indice/alterar/${indice.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span>
						</a>&nbsp;&nbsp;
				</c:if>
				 <c:if test="${CADASTRO_INDICE_EXCLUIR != null}">
						<form:form action="${raiz}indice/excluir" method="POST"
							modelAttribute="indice" class="formulario_acoes" id="form${indice.id}">
							<form:hidden path="id" value="${indice.id}" />
							<a href="#" data-toggle="modal" data-target="#modal${indice.id}"
								title="Excluir"> <span class="glyphicon glyphicon-remove"></span>
							</a>
						</form:form>
						<div class="modal fade modal-sm" id="modal${indice.id}">
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
							       <button type="button" class="btn btn-primary" onclick="submeter(form${indice.id})">Sim</button>
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
