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


				<c:if test="${messageError != null}">
					<div class="row">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>${messageError}</div>
					</div>
				</c:if>



				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Aprovações</span> - Exames - Detalhar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Aprovações</li>
						<li><a href="${raiz}solicitacoes/exame">Exames</a></li>
						<li class="active">Detalhar</li>

					</ul>
				</div>
			</div>


			<div class="content">
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
							action="${raiz}solicitacoes/exame/salvar"
							id="formularioSolicitacaoRepresentante" method="POST"
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
										<form:input path="paciente.nome" type="hidden" />
										<form:input path="paciente.id" type="hidden" />
										<form:input path="solicitacaoExame.id" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">CPF:</label>
									<div class="col-lg-2">
										<form:input path="paciente.documentacao.cpf"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="paciente.documentacao.cpf" type="hidden" />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Data:</label>
									<div class="col-lg-2">
										<form:input path="solicitacaoExame.dataFormatada"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="solicitacaoExame.dataFormatada"
											type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">Status:</label>
									<div class="col-lg-2">
										<form:input path="solicitacaoExame.status"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="solicitacaoExame.status" type="hidden" />
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Documentação</legend>

								<div class="row">
									<div class="col-xs-offset-1 col-xs-6">
										<a href="http://mamaedeprimeiraviagem.com/wp-content/uploads/2016/01/IMG_20151030_074428_red.jpg" target="blank" class="btn btn-primary">Baixar Documentação</a>
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Aprovar solicitação?</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Exame</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:select id="selectFaixas" path="exame.id"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${exames}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Credenciado</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:select id="selectFaixas" path="credenciado.id"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${credenciados}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Data do Exame</label>
									<div class="col-lg-2">
										<div class="input-group col-xs-8">
											<form:input path="dataExameFormatada" class="form-control"
												id="inputDataExame" cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>
								<br />

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
											path="solicitacaoExame.motivo"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<div class="text-right">
									<a href="${raiz}solicitacoes/exame"
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

