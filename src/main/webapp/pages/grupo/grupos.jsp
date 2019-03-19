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
								class="text-semibold">Gestão</span> - Grupos/Procedimentos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}grupos">Grupos/Procedimentos</a></li>

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

				<c:if test="${empty grupos}">
					<div class="alert alert-info-diogo">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						ATENÇÃO! Esse grupo não existe. Verifique o nome digitado ou
						cadastre um novo grupo.
					</div>
				</c:if>

				<div class="panel panel-flat">

					<div class="panel-heading">
						<h5 class="panel-title">Consultar Grupos</h5>
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
							action="${raiz}grupos" modelAttribute="grupoProcedimento">

							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">Nome</label>
								</div>
								<div class="col-xs-6">
									<form:input type="text" class="form-control" required="true"
										id="inputDescricao" placeholder="Digite o nome" path="nome" />
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}procedimentos"
										class="btn btn-primary abrirJanela">Listar Procedimentos</a>
									&nbsp; <a href="${raiz}grupoProcedimento/salvar"
										class="btn btn-primary abrirJanela">Cadastrar Grupo</a> &nbsp;
									<a href="${raiz}procedimento/salvar"
										class="btn btn-primary abrirJanela">Cadastrar Procedimento</a>
								</div>
							</div>
						</form:form>
					</div>

					<table
						class="table table-striped table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="5%">Código</th>
								<th width="40%">Nome</th>
								<th width="13%">Tipo</th>
								<th width="13%">Valor Pagamento</th>
								<th width="13%">Valor Cobrança</th>

								<th width="5%" class="text-center">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="grupo" items="${grupos}">
								<tr>
									<td><c:out value="${grupo.id}" /></td>
									<td><c:out value="${grupo.nome}" /></td>
									<td><c:out value="${grupo.tipoAtendimento.descricao}" /></td>
									<td><c:out value="${grupo.valorPagamento}" /></td>
									<td><c:out value="${grupo.valorCobranca}" /></td>

									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a
														href="${raiz}grupoProcedimento/visualizar/${grupo.id}"><i
															class="glyphicon glyphicon-search"></i>Visualizar
															Procedimentos</a></li>
													<li><a
														href="${raiz}grupoProcedimento/procedimento/adicionar/${grupo.id}"><i
															class="glyphicon glyphicon-plus"></i>Adicionar
															Procedimentos</a></li>

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