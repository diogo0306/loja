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

				<div class="panel panel-flat">

					<div class="panel-heading">
						<h5 class="panel-title">Procedimentos</h5>
						<div class="heading-elements">
							<ul class="icons-list">
								<li><a data-action="collapse"></a></li>
								<li><a data-action="reload"></a></li>
							</ul>
						</div>
						<a class="heading-elements-toggle"><i class="icon-menu"></i></a>
					</div>

					<table
						class="table table-striped table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="5%">ID</th>
								<th width="5%">Codigo</th>
								<th width="40%">Procedimento</th>
								<th width="10%">Valor Pagamento</th>
								<th width="10%">Valor Cobrança</th>
								<th width="5%" class="text-center">Ações</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="procedimento" items="${procedimentos}">
								<tr>
									<td><c:out value="${procedimento.id}" /></td>
								<td><c:out value="${procedimento.codigo}" /></td>
								<td><c:out value="${procedimento.nome}" /></td>
								<td><c:out value="R$ ${procedimento.valorPagamento} " /></td>
								<td><c:out value="R$ ${procedimento.valorCobranca}" /></td>
								<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">
													<li><a href="${raiz}procedimento/alterar/${procedimento.id}"><i
															class="glyphicon glyphicon-pencil"></i> Alterar</a></li>
												</ul></li>
										</ul>
									</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="text-right">
					<a href="${raiz}grupos" class=" btn bg-danger abrirJanela">Voltar</a>					
				</div>

			</div>
		</div>
	</div>
</div>