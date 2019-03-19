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
								class="text-semibold">Aprovações</span> - Consulta - Detalhar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Aprovações</li>
						<li><a href="${raiz}solicitacoes/consulta">Consultas</a></li>
						<li class="active">Detalhar</li>

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
						<div class="heading-elements">
							<ul class="icons-list">
								<li><a data-action="collapse"></a></li>
								<li><a data-action="reload"></a></li>
							</ul>
						</div>
						<a class="heading-elements-toggle"><i class="icon-menu"></i></a>
					</div>

					<div class="panel-body">
						<form:form class="form-horizontal" role="form"
							action="${raiz}solicitacoes/consulta/salvar"
							id="formularioSolicitacaoConsulta" method="POST"
							modelAttribute="solicitacaoDTO">

							<fieldset class="content-group">
								<legend class="text-bold">Dados da Solicitação</legend>

								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label"
										style="padding-top: 0px">Nome do Paciente:</label>
									<div class="col-lg-3">
										<form:input path="paciente.nome" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="paciente.nome" type="hidden"/>
										<form:input path="paciente.id" type="hidden" />
										<form:input path="solicitacaoConsulta.id" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">CPF:</label>
									<div class="col-lg-3">
										<form:input path="paciente.documentacao.cpf"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="paciente.documentacao.cpf" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label"
										style="margin-top: -8px">Código:</label>
									<div class="col-lg-2">
										<form:input path="solicitacaoConsulta.codigo"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="solicitacaoConsulta.codigo" type="hidden" />
									</div>
								</div>
								<br />
								<div class="form-group">
									
									<label for="inputPcmso" class="col-lg-1 control-label"
										style="padding-top: 5px">Credenciado:</label>
									<div class="col-lg-3">
										<form:input path="credenciado.nome" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="credenciado.nome" type="hidden" />
										<form:input path="credenciado.id" type="hidden" />
									</div>
									
									<label for="inputPcmso" class="col-lg-1 control-label"
										style="margin-top: -8px">Valor da Consulta:</label>
									<div class="col-lg-2">
										<form:input path="solicitacaoConsulta.valorConsultaFormatado"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="solicitacaoConsulta.valorConsultaFormatado"
											type="hidden" />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Data
										da Solicitação:</label>
									<div class="col-lg-3">
										<form:input path="solicitacaoConsulta.dataFormatada"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="solicitacaoConsulta.dataFormatada"
											type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">Status:</label>
									<div class="col-lg-3">
										<form:input path="solicitacaoConsulta.status"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="solicitacaoConsulta.status" type="hidden" />
									</div>
								</div>

							</fieldset>

							<br>
							<fieldset class="content-group">
								<legend class="text-bold">Aprovar solicitação?</legend>

								<div class="form-group">

									<div class="col-lg-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="aprovado" path="aprovado" name="aprovado"
											label="${aprovado}" value="${aprovado}" />
									</div>
									<label for="inputAprovado" class="col-lg-1 control-label"
										style="text-align: left; margin-top: -6px">SIM</label>

									<div class="col-lg-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="reprovado" path="reprovado"
											name="reprovado" label="${reprovado}" value="${reprovado}" />
									</div>
									<label for="inputReprovado" class="col-lg-1 control-label"
										style="text-align: left; margin-top: -6px">NÃO*</label> <br />
								</div>

								<div class="row">
									<div class="col-xs-1">
										<label for="inputDescricao">Motivo*</label>
									</div>
									<div class="col-xs-6">
										<form:textarea maxlength="254" rows="4" cols="50"
											class="form-control" id="inputTurno"
											placeholder="Até 254 caracteres"
											path="solicitacaoConsulta.motivo"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<br>
								<fieldset class="content-group">
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Orientações:</label>
										</div>
										<div class="col-xs-6">
											<form:textarea rows="5" cols="30" class="form-control"
												id="inputTurno" placeholder="Orientações da autorização"
												path="orientacoes" cssErrorClass="field_error form-control" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Observações:</label>
										</div>
										<div class="col-xs-6">
											<form:textarea rows="5" cols="30" class="form-control"
												id="inputTurno" placeholder="Observações da autorização"
												path="observacoes" cssErrorClass="field_error form-control" />
										</div>
									</div>
								</fieldset>

								<div class="text-right">
									<a href="${raiz}solicitacoes/consulta"
										class=" btn bg-danger abrirJanela">Voltar</a>

									<button type="submit" class="btn btn-primary">
										Salvar <i class="icon-arrow-right14 position-right"></i>
									</button>
								</div>

							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/solicitacao/solicitacao.js" />"></script>

