<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}solicitacao/salvar_medico"
	id="url-salvar" />

<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">

				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Aprovações</span> - Solicitações -
							Detalhar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Aprovações</li>
						<li><a href="${raiz}solicitacoes">Solicitações - Médico</a></li>
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
							action="${raiz}solicitacao/salvar_medico"
							id="formularioSolicitacaoMedico" method="POST"
							modelAttribute="solicitacaoDTO">

							<fieldset class="content-group">
								<legend class="text-bold">Dados da Solicitação</legend>

								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Nome:</label>
									<div class="col-lg-4">
										<form:input path="solicitacao.nome" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.nome" type="hidden" />
										<form:input path="solicitacao.id" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">CPF/CNPJ:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.cpf" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.cpf" type="hidden" />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Data:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.dataFormatada"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.dataFormatada" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">Tipo:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.tipo" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.tipo" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">Status:</label>
									<div class="col-lg-2">
										<form:input path="solicitacao.status" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="solicitacao.status" type="hidden" />
									</div>
								</div>
								<br />

								<div class="row">
									<div class="col-xs-offset-1 col-xs-6">
										<a href="" class="btn btn-primary">Visualizar Documentação</a>
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Situação</legend>
						
								<div class="row">
									<div class="col-xs-1">
										<label for="inputDescricao">Motivo*</label>
									</div>
									<div class="col-xs-6">
										<form:textarea maxlength="254" rows="4" cols="50" disabled="true"
											class="form-control" id="inputTurno"
											path="solicitacao.motivo"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<div class="text-right">
									<a href="${raiz}solicitacoes"
										class=" btn bg-danger abrirJanela">Voltar</a>					
								</div>

							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

