<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th width="25%">Nome</th>
			<th width="25%">CPF</th>
			<th width="25%">RG</th>
			<th width="25%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="representante" items="${representantes}">
			<tr>
				<td><c:out value="${representante.nome}" /></td>
				<td><c:out value="${representante.cpf}" /></td>
				<td><c:out value="${representante.rg}" /></td>
				
				<td>
						<a href="${raiz}representante/visualizar/${representante.id}"
							title="Visualizar"> <span class="glyphicon glyphicon-search"></span>
						</a>&nbsp;
					
					
					
						<a href="${raiz}representante/alterar/${representante.id}" title="Alterar">
							<span class="glyphicon glyphicon-cog"></span>
						</a>&nbsp;
					
					
					
							<form:form action="${raiz}representante/excluir" method="POST"
								modelAttribute="representante" class="formulario_acoes"
								id="form${representante.id}">
								<form:hidden path="id" value="${representante.id}" />
								<a href="#" data-toggle="modal"
									data-target="#modal${representante.id}" title="Excluir"> <span
									class="glyphicon glyphicon-remove"></span>
								</a>
							</form:form>
							<div class="modal fade modal-sm" id="modal${representante.id}">
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
												onclick="submeter(form${representante.id})">Sim</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
						
					
					</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<tiles:insertAttribute name="paginacao" />
