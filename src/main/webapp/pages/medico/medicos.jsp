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
								class="text-semibold">Gestão</span> - Profissionais
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}profissionais"> Profissionais</a></li>

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

				<c:if test="${empty medicos}">
					<div class="row">
						<div class="alert alert-info-diogo">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>
							ATENÇÃO! Profissionais não cadastrada.
						</div>
					</div>
				</c:if>

				<div class="panel panel-flat">
					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}profissionais" modelAttribute="medico">

							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">Nome</label>
								</div>
								<div class="col-xs-4">
									<form:input type="text" class="form-control"
										id="inputDescricao" placeholder="Digite o nome" path="nome" />
								</div>
								<div class="col-xs-1">
									<label for="inputDescricao">CRM</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										id="inputDescricao" placeholder="Digite o CRM" path="crm" />
								</div>
							</div>
							<br />

							<div class="row">
								<div class="col-xs-1">
									<label for="inputDescricao">CPF</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										id="inputDescricao" placeholder="Digite o CPF" path="cpf" />
								</div>
								<div class="col-xs-1">
									<label for="inputDescricao">RG</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										id="inputDescricao" placeholder="Digite o CPF" path="cpf" />
								</div>

							</div>
							<br />



							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									<a href="${raiz}medico/salvar"
										class="btn btn-primary abrirJanela">Incluir</a> <a
										href="${raiz}especialidades"
										class="btn btn-primary abrirJanela">Especialidades</a>
								</div>
							</div>
						</form:form>
					</div>


					<table class="table table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th width="25%">Nome</th>
								<th width="20%">Telefone</th>
								<th width="15%">CRM</th>
								<th width="15%">Especialidade</th>
								<th width="15%">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="medico" items="${medicos}">
								<tr>
									<td><c:out value="${medico.nome}" /></td>
									<td><c:out value="${medico.telefone}" /></td>
									<td><c:out value="${medico.crm}" /></td>
									<td><c:out value="${medico.especialidade.especialidade}" /></td>
									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a href="${raiz}medico/visualizar/${medico.id}"><i
															class="glyphicon glyphicon-search"></i> Visualizar</a></li>

													<li><a href="${raiz}medico/alterar/${medico.id}"><i
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

<script type="text/javascript"
	src="<c:url value="/resources/js/medico/medico.js" />"></script>