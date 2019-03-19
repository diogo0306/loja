<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Parametrizações
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}parametrizacoes"> Parametrizações</a></li>

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
				
				<c:if test="${empty parametrizacoes}">
					<div class="alert alert-info-diogo">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						ATENÇÃO! Esse parâmetro não existe. Verifique o código digitado ou cadastre um novo parâmetro.
					</div>
				</c:if>
				
				<div class="panel panel-flat">

					<div class="panel-heading">
						<h5 class="panel-title">Consultar Parametrizações</h5>
						<div class="heading-elements">
							<ul class="icons-list">
								<li><a data-action="collapse"></a></li>
								<li><a data-action="reload"></a></li>
							</ul>
						</div>
						<a class="heading-elements-toggle"><i class="icon-menu"></i></a>
					</div>


					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}parametrizacoes" modelAttribute="parametrizacao">

							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">Código</label>
								</div>
								<div class="col-xs-6">
									<form:input type="text" class="form-control" required="true"
										id="inputDescricao" placeholder="Digite o código" path="codigo" />
								</div>
							</div>
							<br />

							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									<a href="${raiz}parametrizacao/salvar"
										class="btn btn-primary abrirJanela">Incluir</a>
								</div>
							</div>
						</form:form>
					</div>

					<table
						class="table table-striped table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="15%">Código</th>
								<th width="30%">Mês</th>
								<th width="30%">Ano</th>
								<th width="15%" class="text-center">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="parametrizacao" items="${parametrizacoes}">
								<tr>
									<td><c:out value="${parametrizacao.codigo}" /></td>
									<td><c:out value="${parametrizacao.mesProcessamento}" /></td>
									<td><c:out value="${parametrizacao.anoProcessamento}" /></td>
									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a
														href="${raiz}parametrizacao/visualizar/${parametrizacao.id}"><i
															class="glyphicon glyphicon-search"></i> Visualizar</a></li>

													<li><a
														href="${raiz}parametrizacao/alterar/${parametrizacao.id}"><i
															class="glyphicon glyphicon-pencil"></i> Alterar</a></li>
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

