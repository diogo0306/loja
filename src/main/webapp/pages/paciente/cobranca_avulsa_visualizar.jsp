<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">


				<c:if test="${messageError != null}">
					<div class="row">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>${messageError}</div>
					</div>
				</c:if>



				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Beneficiário - Visualizar
							Cobranças
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}pacientes">Beneficiários</a></li>
						<li class="active">Cobranças</li>

					</ul>
				</div>
			</div>


			<div class="content">
				<div class="panel panel-flat">
					<div class="panel-heading">
						<div class="heading-elements">
							<ul class="icons-list">
								<li><a data-action="collapse"></a></li>
								<li><a data-action="reload"></a></li>
							</ul>
						</div>
						<a class="heading-elements-toggle"><i class="icon-menu"></i></a>
					</div>

					<div class="panel-body">
						<form:form class="form-horizontal" role="form"
							action="${raiz}paciente/" id="formularioPaciente" method="POST"
							modelAttribute="paciente">
							<fieldset class="content-group">
								<legend class="text-bold">Beneficiário</legend>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">Nome*</label>
									</div>

									<div class="col-xs-3">
										<form:input type="text" class="form-control" id="inputNome"
											placeholder="Digite o Nome Completo" path="nome"
											disabled="true" cssErrorClass="field_error form-control" />
									</div>

								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">CPF*</label>
									</div>
									<div class="col-xs-3">
										<form:input type="text" class="form-control" id="inputCpf"
											placeholder="Digite o CPF" path="documentacao.cpf"
											disabled="true" cssErrorClass="field_error form-control" />
									</div>

								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputDataNascimento">Celular</label>
									</div>

									<div class="col-xs-3">
										<form:input type="text" class="form-control" id="inputCelular"
											disabled="true" placeholder="(99) 99999-9999"
											path="documentacao.celular"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">Email</label>
									</div>

									<div class="col-xs-3">
										<form:input type="text" class="form-control" id="inputNome"
											disabled="true" placeholder="Digite o e-mail"
											path="documentacao.email"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Cobrança(s) Contratual(s)</legend>

								<table class="table table-hover table-striped table-bordered ">
									<thead>
										<tr>
											<th width="40%">Descrição</th>
											<th width="10%">Valor(R$)</th>
											<th width="10%">Vencimento</th>
											<th width="10%">Ações</th>
										</tr>
									</thead>
									<tbody style="font-size: 12px;">
										<c:forEach var="cobrancaContrato"
											items="${listaCobrancaContrato}">
											<tr>
												<td><c:out value="${cobrancaContrato.nome}" /></td>
												<td><c:out value="${cobrancaContrato.valor}" /></td>
												<td><c:out value="${cobrancaContrato.dataVencimento}" /></td>
												<td class="text-center">
													<ul class="icons-list">
														<li class="dropdown"><a href="#"
															class="dropdown-toggle" data-toggle="dropdown"> <i
																class="icon-menu9"></i>
														</a>

															<ul class="dropdown-menu dropdown-menu-right">

																<li><a
																	href="#"><i
																		class="glyphicon glyphicon-usd"></i> Excluir Cobrança
																</a></li>

																<li><a href="#"><i
																		class="glyphicon glyphicon-usd"></i> Negociar Cobrança
																</a></li>

															</ul></li>
													</ul>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Cobrança(s) Avulsa(s)</legend>

								<table class="table table-hover table-striped table-bordered ">
									<thead>
										<tr>
											<th width="40%">Descrição</th>
											<th width="10%">Valor(R$)</th>
											<th width="10%">Vencimento</th>
											<th width="10%">Ações</th>
										</tr>
									</thead>
									<tbody style="font-size: 12px;">
										<c:forEach var="cobrancaAvulsa" items="${listaCobrancaAvulsa}">
											<tr>
												<td><c:out value="${cobrancaAvulsa.nome}" /></td>
												<td><c:out value="${cobrancaAvulsa.valor}" /></td>
												<td><c:out value="${cobrancaAvulsa.data}" /></td>
												<td class="text-center">
													<ul class="icons-list">
														<li class="dropdown"><a href="#"
															class="dropdown-toggle" data-toggle="dropdown"> <i
																class="icon-menu9"></i>
														</a>

															<ul class="dropdown-menu dropdown-menu-right">

																<li><a
																	href="${raiz}paciente/excluir/cobranca_avulsa/${cobrancaAvulsa.id}"><i
																		class="glyphicon glyphicon-usd"></i> Excluir Cobrança
																</a></li>

																<li><a
																	href="${raiz}paciente/visualizar/cobranca_avulsa/${paciente.id}"><i
																		class="glyphicon glyphicon-usd"></i> Negociar Cobrança
																</a></li>

															</ul></li>
													</ul>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</fieldset>


							<div class="text-right">
								<a href="${raiz}pacientes" class=" btn bg-danger abrirJanela">Voltar</a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>


<script type="text/javascript"
	src="<c:url value="/resources/js/paciente/paciente.js" />"></script>

