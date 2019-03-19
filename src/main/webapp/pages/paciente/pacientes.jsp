<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Beneficiários
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}pacientes"> Beneficiários</a></li>

					</ul>
				</div>
			</div>


			<div class="content">

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

				<div class="panel panel-flat">
					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}pacientes" modelAttribute="paciente">

							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">Nome</label>
								</div>
								<div class="col-xs-4">
									<form:input type="text" class="form-control"
										id="inputDescricao" placeholder="Digite o nome" path="nome" />
								</div>
								<div class="col-xs-1">
									<label for="inputDescricao">RG</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										id="inputDescricao" placeholder="Digite o RG" path="rg" />
								</div>
							</div>
							<br />

							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">CPF</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control" id="inputCpf"
										placeholder="Digite o CPF" path="cpf" />
								</div>
								<div class="col-xs-1">
									<label for="field_1">Data Cadastro</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										id="inputDataNascimento"
										placeholder="Digite a data de Cadastro"
										path="dataInclusaoFormatada" />
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}beneficiario/gestao"
										class="btn btn-primary abrirJanela">Incluir</a>
								</div>
							</div>
						</form:form>
					</div>

					<!-- Highlighting rows and columns -->

					<table class="table table-bordered table-hover datatable-highlight">
						<thead>
							<tr style="text-align: center;">
								<th width="20%">Nome</th>
								<th width="9%">CPF</th>

								<th width="9%">Ações</th>
								<th width="9%">Financeiro</th>
							</tr>
						</thead>
						<tbody style="font-size: 12px;">
							<c:forEach var="paciente" items="${pacientes}">
								<tr>
									<td><c:out value="${paciente.nome}" /></td>
									<td><c:out value="${paciente.documentacao.cpf}" /></td>

									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a
														href="${raiz}paciente/visualizar/${paciente.id}"><i
															class="glyphicon glyphicon-search"></i> Visualizar Dados</a>
													</li>
													<li><a href="${raiz}paciente/alterar/${paciente.id}"><i
															class="glyphicon glyphicon-plus"></i> Alterar Dados</a></li>
													<li><a
														href="${raiz}paciente/adicionar/dependente/${paciente.id}"><i
															class="glyphicon glyphicon-plus"></i> Adicionar
															Dependente</a></li>

												</ul></li>
										</ul>
									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>
												<ul class="dropdown-menu dropdown-menu-right">

													<li><a
														href="${raiz}paciente/adicionar/cobranca_avulsa/${paciente.id}"><i
															class="glyphicon glyphicon-usd"></i> Incluir Cobrança
															Avulsa</a></li>

													<li><a
														href="${raiz}paciente/visualizar/cobranca_avulsa/${paciente.id}"><i
															class="glyphicon glyphicon-usd"></i> Visualizar
															Cobrança(s) </a></li>
													<c:if test="${paciente.dadosCobranca eq null}">
														<li><a
															href="${raiz}paciente/adicionar/dados_cobranca/${paciente.id}"><i
																class="glyphicon glyphicon-usd"></i> Adicionar Dados de
																Cobrança</a></li>
													</c:if>

													<c:if test="${paciente.dadosCobranca ne null}">
														<li><a
															href="${raiz}paciente/visualizar/dados_cobranca/${paciente.id}"><i
																class="glyphicon glyphicon-usd"></i> Visualizar Dados de
																Cobrança</a></li>
														<li><a
															href="${raiz}paciente/alterar/dados_cobranca/${paciente.id}"><i
																class="glyphicon glyphicon-usd"></i> Alterar Dados de
																Cobrança</a></li>
													</c:if>
												</ul></li>
										</ul>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/paciente/paciente.js" />"></script>
