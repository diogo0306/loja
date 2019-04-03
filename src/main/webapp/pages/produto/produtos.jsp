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
								class="text-semibold">Gestão</span> - Produtos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}produtos"> Produtos</a></li>

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
				
				<c:if test="${empty produtos}">
					<div class="alert alert-info-diogo">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						ATENÇÃO! Clique em "Pesquisar" para gerar a lista de Produtos.
						Caso a lista não seja gerada, clique em "Cadastrar Produto"!
					</div>
				</c:if>

				<div class="panel panel-flat">

					<div class="panel-heading">
						<h5 class="panel-title">Consultar Produto</h5>
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
							action="${raiz}produtos" modelAttribute="produto">

							<div class="form-group">
								<div class="col-xs-1">
									<label for="field_1">Nome</label>
								</div>
								<div class="col-xs-5">
									<form:input type="text" class="form-control" id="#"
										placeholder="Digite o nome" path="nome" />
								</div>
								<div class="col-xs-1">
									<label for="field_3">Código</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control" id="codigo"
										placeholder="Digite o codigo" path="codigo" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}produto/salvar"
										class="btn btn-primary abrirJanela">Cadastrar Produto</a>
								</div>
							</div>
						</form:form>
					</div>

					<table
						class="table table-striped table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="10%">Código</th>
								<th width="40%">Nome</th>
								<th width="10%">Tipo</th>
								<th width="12%">Preço (R$)</th>
								<th width="14%">Estoque (Und)</th>
								<th class="text-center" width="10%">Ações</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="produto" items="${produtos}">
								<tr>
									<td><c:out value="${produto.codigo}" /></td>
									<td><c:out value="${produto.nome}" /></td>
									<td><c:out value="${produto.tipoProdutoEnum.descricao}" /></td>
									<td><c:out value="${produto.precoVenda}" /></td>
									<td><c:out value="${produto.estoque}" /></td>
									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a href="${raiz}produto/alterar/${produto.id}"><i
															class="glyphicon glyphicon-search"></i> Alterar</a></li>

													<li><a href="${raiz}produto/visualizar/${produto.id}"><i
															class="glyphicon glyphicon-pencil"></i> Visualizar</a></li>
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


