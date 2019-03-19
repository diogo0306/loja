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
								class="text-semibold">Exames - Procedimentos</span> - Tabelas de
							Preço
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Exames - Procedimentos</li>
						<li><a href="${raiz}tabelas"> Tabelas de Preço</a></li>

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
							action="${raiz}tabelas" modelAttribute="tabelaExame">

							<div class="row">
								<div class="col-xs-1">
									<label for="inputNome">Nome</label>
								</div>
								<div class="col-xs-6">
									<form:input type="text" class="form-control" id="inputNome"
										placeholder="Digite o nome" path="nome" />
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}tabela/salvar"
										class="btn btn-primary abrirJanela">Nova Tabela</a> &nbsp; <a
										href="${raiz}tabela_exame/salvar"
										class="btn btn-primary abrirJanela">Incluir Preço</a>
								</div>
							</div>
						</form:form>
					</div>

					<table class="table table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="40%">Nome</th>
								<th width="40%">Descrição</th>
								<th width="20%">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="tabela" items="${tabelaLista}">
								<tr>
									<td><c:out value="${tabela.nome}" /></td>
									<td><c:out value="${tabela.descricao}" /></td>

									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a href="${raiz}tabela/visualizar/${tabela.id}"><i
															class="glyphicon glyphicon-search"></i> Visualizar</a></li>

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
	src="<c:url value="/resources/js/exame/tabela.js" />"></script>
