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
								class="text-semibold">Gestão</span> - Usuários
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}usuarios"> Usuários</a></li>

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

				<c:if test="${empty usuarios}">
					<div class="alert alert-info-diogo">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						ATENÇÃO! Clique em "Pesquisar" para gerar a lista de Usuários.
						Caso a lista não seja gerada, clique em "Cadastrar Usuário"!
					</div>
				</c:if>

				<div class="panel panel-flat">

					<div class="panel-heading">
						<h5 class="panel-title">Consultar Usuário</h5>
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
							action="${raiz}usuarios" modelAttribute="usuario">

							<div class="form-group">
								<div class="col-xs-1">
									<label for="field_1">Nome</label>
								</div>
								<div class="col-xs-5">
									<form:input type="text" class="form-control" id="#"
										placeholder="Digite o nome" path="nome" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
									&nbsp; <a href="${raiz}usuario/salvar"
										class="btn btn-primary abrirJanela">Cadastrar Usuário</a>
								</div>
							</div>
						</form:form>
					</div>

					<table
						class="table table-striped table-bordered table-hover datatable-highlight">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Login</th>
								<th>Telefone</th>
								<th>Email</th>
								<th>Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="usuario" items="${usuarios}">
								<tr>
									<td><c:out value="${usuario.nome}" /></td>
									<td><c:out value="${usuario.login}" /></td>
									<td><c:out value="${usuario.telefone}" /></td>
									<td><c:out value="${usuario.email}" /></td>
									<td class="text-center">
										<ul class="icons-list">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown"> <i class="icon-menu9"></i>
											</a>

												<ul class="dropdown-menu dropdown-menu-right">

													<li><a href="${raiz}usuario/alterar/${usuario.id}"><i
															class="glyphicon glyphicon-pencil"></i> Alterar</a></li>

													<li><a href="${raiz}usuario/visualizar/${usuario.id}"><i
															class="glyphicon glyphicon-search"></i> Visualizar</a></li>
												</ul></li>
										</ul>
									</td>

									<%-- <td><c:if test="${usuario.tipoUsuario.codigo != 1}">
										<c:if test="${CADASTRO_USUARIO_VISUALIZAR != null}">
											<a href="${raiz}usuario/visualizar/${usuario.id}"
												title="Visualizar"> <span
												class="glyphicon glyphicon-search"></span>
											</a>
										</c:if>
										<c:if test="${CADASTRO_USUARIO_ALTERAR != null}">
											<a href="${raiz}usuario/alterar/${usuario.id}"
												title="Alterar"> <span class="glyphicon glyphicon-cog"></span>
											</a>
										</c:if>
										<a href="${raiz}usuario/gerenciar_cliente/${usuario.id}"
											title="Gerênciar Clientes"> <span
											class="glyphicon glyphicon-earphone"></span>
										</a>
										<form:form action="${raiz}usuario/resetar_senha" method="POST"
											modelAttribute="usuario" class="formulario_acoes"
											id="form_resetar_senha${usuario.id}">
											<form:hidden path="id" value="${usuario.id}" />
											<a href="#" data-toggle="modal"
												data-target="#modal_resetar_senha${usuario.id}"
												title="Resetar Senha"> <span
												class="glyphicon glyphicon-asterisk"></span>
											</a>
										</form:form>
										<div class="modal fade modal-sm"
											id="modal_resetar_senha${usuario.id}">
											<div class="modal-dialog modal-sm">
												<div class="modal-content modal-sm">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title">Confirmação</h4>
													</div>
													<div class="modal-body">
														<p>Deseja resetar a senha do usuário?</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Não</button>
														<button type="button" class="btn btn-primary"
															onclick="submeter(form_resetar_senha${usuario.id})">Sim</button>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div>
										<!-- /.modal -->
									</c:if> <c:if test="${CADASTRO_USUARIO_EXCLUIR != null}">
										<form:form action="${raiz}usuario/excluir" method="POST"
											modelAttribute="usuario" class="formulario_acoes"
											id="form${usuario.id}">
											<form:hidden path="id" value="${usuario.id}" />
											<a href="#" data-toggle="modal"
												data-target="#modal${usuario.id}" title="Excluir"> <span
												class="glyphicon glyphicon-remove"></span>
											</a>
										</form:form>
										<div class="modal fade modal-sm" id="modal${usuario.id}">
											<div class="modal-dialog modal-sm">
												<div class="modal-content modal-sm">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title">Confirmação</h4>
													</div>
													<div class="modal-body">
														<p>Deseja realmente excluir este item?</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Não</button>
														<button type="button" class="btn btn-primary"
															onclick="submeter(form${usuario.id})">Sim</button>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div>
										<!-- /.modal -->
									</c:if></td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>






<%-- <%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
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


	<div class="page-header">
		<ol class="breadcrumb">
			<li class=""><a href="${raiz}">Home</a></li>
			<li class=""><a href="${raiz}">Usuário</a></li>
			<li class="active"><a href="${raiz}usuarios">Pesquisar Usuários</a></li>
		</ol>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h5
				style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
				<i class="fa fa-user"></i> &nbsp;&nbsp; Usuários - Gestão
			</h5>
		</div>

		<div class="divSeparador2">
			<form:form method="POST" class="form-horizontal" role="form"
				action="${raiz}usuarios" modelAttribute="usuario">

				<div class="row">
					<div class="col-xs-1">
						<label for="inputDescricao">Login</label>
					</div>
					<div class="col-xs-6">
						<form:input type="text" class="form-control" id="inputDescricao"
							placeholder="Digite o login" path="login" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
						&nbsp;
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div id="divList">
		<tiles:insertAttribute name="list" />
	</div>
</div> --%>

<script type="text/javascript"
	src="<c:url value="/resources/js/usuario/usuario.js" />"></script>
