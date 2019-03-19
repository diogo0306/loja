
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->


<div class="ui-eClinic">
	<div class="container">
		<nav class="navbar navbar-default navbar-breadcrumbs">
			<div class="container">
				<div class="navbar-header">
					<div class="btn-group btn-breadcrumb navbar-btn">
						<a href="#"><i class="glyphicon glyphicon-home"></i></a> <a
							href="#">></a> <a href="#">pacientes</a> <a href="#">></a> <a
							href="#">receita</a>
					</div>
				</div>
			</div>
		</nav>
		<c:if test="${messageError != null}">
			<div class="row">
				<div class="alert alert-danger">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>${messageError}</div>
			</div>
		</c:if>
		<c:if test="${message != null}">
			<div class="row">
				<div class="alert alert-info">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>${message}</div>
			</div>
		</c:if>
		<div class="container">
			<div class="content">
				<form:form class="form-horizontal" role="form"
					action="${raiz}paciente/receita/gerar" id="id-form" method="POST"
					modelAttribute="receita">
					<form:input path="id" type="hidden" />
					<form:input path="paciente.id" type="hidden" />

					<div class="ui-eClinic panel panel-default">
						<div class="ui-eClinic panel-heading"
							style="height: 50px; padding: 0px 15px;">
							<h3>Receita</h3>
							<h6>${receita.paciente.nome}</h6>
						</div>

						<div class="ui-eClinic panel-body" style="height: 500px;">


							<c:if test="${messageError != null}">
								<div class="row">
									<div class="alert alert-danger">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>${messageError}</div>
								</div>
							</c:if>



							<div id="divList" class="ui-eClinic tabelas table-responsive"
								style="overflow: scroll; height: 477px; border: 1px solid #CCC;">

								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th>#</th>
											<th>Código</th>
											<th>Sub1</th>
											<th>Sub2</th>
											<th>Sub3</th>
											<th>Sub4</th>
											<th>Sub5</th>
											<th>Sub6</th>
											<th>Sub7</th>
											<th>Sub8</th>
											<th>Sub9</th>
											<th>Sub10</th>
											<th>Sub11</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="medicamento" items="${medicamentos}"
											varStatus="status">
											<tr>
												<td><input type="checkbox"
													name="medicamentos[${status.index}].id"
													value="${medicamento.id}" /></td>
												<td><c:out
														value="${medicamento.codigo}" /></td>
												<td><c:out
														value="${medicamento.substancia1} - ${medicamento.dosagem1}" /></td>
												<td><c:out
														value="${medicamento.substancia2} - ${medicamento.dosagem2}" /></td>
												<td><c:out
														value="${medicamento.substancia3} - ${medicamento.dosagem3}" /></td>
												<td><c:out
														value="${medicamento.substancia4} - ${medicamento.dosagem4}" /></td>
												<td><c:out
														value="${medicamento.substancia5} - ${medicamento.dosagem5}" /></td>
												<td><c:out
														value="${medicamento.substancia6} - ${medicamento.dosagem6}" /></td>
												<td><c:out
														value="${medicamento.substancia7} - ${medicamento.dosagem7}" /></td>
												<td><c:out
														value="${medicamento.substancia8} - ${medicamento.dosagem8}" /></td>
												<td><c:out
														value="${medicamento.substancia9} - ${medicamento.dosagem9}" /></td>
												<td><c:out
														value="${medicamento.substancia10} - ${medicamento.dosagem10}" /></td>
												<td><c:out
														value="${medicamento.substancia11} - ${medicamento.dosagem11}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</div>
						</div>
						<div class="panel-footer" style="height: 70px; padding: 0px 15px;">
							<div class="row">

								<div class="col-xs-offset-1 col-xs-1" style="margin-left: 0px;">
									&nbsp;
									<button class="btn btn-success abrirJanela">Gerar
										Receita</button>
								</div>

							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>