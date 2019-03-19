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
								class="text-semibold">Gestão</span> - Contratos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}contratos"> Contratos</a></li>

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


				<c:if test="${empty contratos}">
					<div class="alert alert-info-diogo">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						ATENÇÃO! Esse contrato não existe. Verifique o número digitado ou cadastre um novo contrato.
					</div>
				</c:if>

				<div class="panel panel-flat">



					<div class="panel-heading">
						<h5 class="panel-title">Consultar Contratos</h5>
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
							action="${raiz}contratos" modelAttribute="contrato">


							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">Número do Contrato</label>
								</div>
								<div class="col-xs-6">
									<form:input type="text" class="form-control" required="true"
										id="inputDescricao" placeholder="Digite o número"
										path="numero" />
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}contrato/salvar"
										class="btn btn-primary abrirJanela">Cadastrar</a>
								</div>
							</div>
						</form:form>
					</div>

					<table
						class="table table-striped table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="15%">Número do Contrato</th>
								<th width="50%">Nome do Titular</th>
								<th width="15%">Tipo de Pessoa</th>
								<th width="15%">Situação</th>
								<th width="15%" class="text-center">Ações</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="contrato" items="${contratos}">
								<tr>
									<td><c:out value="${contrato.numero}" /></td>

									<c:if
										test="${contrato.tipoPessoaContratoEnum.descricao eq 'Pessoa Física'}">
										<td><c:out value="${contrato.paciente.nome}" /></td>
									</c:if>

									<c:if
										test="${contrato.tipoPessoaContratoEnum.descricao eq 'Pessoa Jurídica'}">
										<td><c:out value="${contrato.empresa.nome}" /></td>
									</c:if>

									<td><c:out
											value="${contrato.tipoPessoaContratoEnum.descricao}" /></td>
									<td><c:out value="${contrato.situacaoEnum.descricao}" /></td>
									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a
														href="${raiz}contrato/visualizar/${contrato.id}"><i
															class="glyphicon glyphicon-search"></i> Visualizar</a></li>

													<li><a href="${raiz}contrato/alterar/${contrato.id}"><i
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
