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
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Consultas</span> - Beneficiário - Nova
							Consulta
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Consultas</li>
						<li><a href="${raiz}consultas">Beneficiário</a></li>
						<li class="active">Nova Consulta</li>

					</ul>
				</div>
			</div>


			<div class="content">

				<c:if test="${messageError != null}">
					<div class="row">
						<div class="col-xs-12">
							<div class="alert alert-danger">
								<b>${messageError}</b>
							</div>
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="alert alert-danger alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<strong>ATENÇÃO!</strong> Selecione um Paciente para a consulta e
						clique em avançar, caso o paciente não seja cadastrado, cadastre o
						mesmo antes de realizar a consulta.
					</div>
				</div>

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
						<fieldset class="content-group">
							<legend class="text-bold">Pesquisar Beneficiário</legend>

							<form:form method="POST" class="form-horizontal" role="form"
								action="${raiz}consulta/pacientes" modelAttribute="paciente"
								id="formPesquisarPaciente">

								<div class="row">
									<div class="col-xs-1">
										<label for="field_1">Nome</label>
									</div>
									<div class="col-xs-3">
										<form:input type="text" class="form-control" id="field_1"
											placeholder="Digite o nome" path="nome" />
									</div>
									<div class="col-xs-1">
										<label for="field_1">RG</label>
									</div>
									<div class="col-xs-3">
										<form:input type="text" class="form-control" id="field_1"
											placeholder="Digite o RG" path="rg" />
									</div>

									<div class="col-xs-1">
										<label for="field_1">CPF</label>
									</div>
									<div class="col-xs-3">
										<form:input type="text" class="form-control" id="inputCep"
											placeholder="Digite o CPF" path="cpf" />

									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-xs-offset-1 col-xs-6">
										<button type="submit" class="btn btn-success abrirJanela">Pesquisar</button>
										&nbsp; <a href="#" data-toggle="modal" class="btn btn-success"
											data-target="#modalCadastrarPacienteSelecao"
											title="Cadastrar Paciente"> Cadastrar Paciente </a>
									</div>
								</div>

							</form:form>


							<div class="modal fade" id="modalCadastrarPacienteSelecao">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">

										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<h4 class="modal-title">
												<b>Cadastrar Paciente</b>
											</h4>
										</div>


										<div class="modal-body">

											<form:form
												action="${raiz}consulta/selecionar/paciente/cadastrar"
												method="POST" modelAttribute="paciente"
												class="formulario_acoes" id="formCadastrarPacienteSelecao">


												<div class="divSeparador2">

													<div class="row">
														<div class="col-xs-2">
															<label>Nome do Paciente</label>
														</div>
														<div class="col-xs-4">
															<form:input type="text" cssClass="form-control"
																id="inputNome" cssErrorClass="field_error form-control"
																path="nome" />
														</div>
														<div class="col-xs-2">
															<label for="inputNome">Data Nascimento</label>
														</div>
														<div class="col-xs-4">
															<form:input type="text" class="form-control"
																id="inputDataNascimento" placeholder="Digite a Data"
																path="dataNascimentoFormatada"
																cssErrorClass="field_error form-control" />
														</div>

													</div>
													<br />
													<div class="row">
														<div class="col-xs-2">
															<label for="field_3">Sexo</label>
														</div>
														<div class="col-xs-4">
															<form:select id="selectTipoPlano"
																path="codigoSexoTransiente" class="form-control"
																cssErrorClass="field_error form-control">
																<form:option value="" label="Selecione" />
																<form:options items="${sexos}" itemLabel="descricao"
																	itemValue="codigo" />
															</form:select>
														</div>
													</div>
													<br />

													<div class="row">
														<div class="col-xs-2">
															<label for="inputCep">RG do Paciente</label>
														</div>
														<div class="col-xs-4">
															<form:input type="text" cssClass="form-control"
																id="inputRg" path="rg" />
														</div>
														<div class="col-xs-2">
															<label for="inputCep">CPF do Paciente</label>
														</div>
														<div class="col-xs-4">
															<form:input type="text" cssClass="form-control"
																id="inputCpf" path="cpf" />
														</div>
													</div>
													<br />
												</div>
											</form:form>
										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Cancelar</button>
											<button type="button" class="btn btn-primary abrirJanela"
												onclick="submeterComJanela(formCadastrarPacienteSelecao, modalCadastrarPacienteSelecao)">Confirmar</button>
										</div>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>

						</fieldset>
					</div>
				</div>

				<div id="divList">
					<tiles:insertAttribute name="list" />
				</div>
				<br />
				<div class="text-right">
					<a href="${raiz}consultas" class="btn btn-danger abrirJanela">Cancelar</a>
					<a href="#" class="btn btn-primary abrirJanela"
						id="a-selecionar-paciente">Avançar</a>
					<form:form method="POST" class="form-horizontal" role="form"
						action="${raiz}consulta/selecionar/paciente" id="form-selecionar"
						commandName="paciente">
						<form:hidden path="id" id="id-paciente" />
					</form:form>
				</div>
			</div>
		</div>
	</div>

</div>


<script type="text/javascript"
	src="<c:url value="/resources/js/consulta/consulta.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/consulta/consulta_paciente_paginacao.js" />"></script>