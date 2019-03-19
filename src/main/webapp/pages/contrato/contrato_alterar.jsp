<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

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
								class="text-semibold">Gestão</span> - Contrato - Alterar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}contratos">Contratos</a></li>
						<li class="active">Alterar</li>

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
							action="${raiz}contrato/alterar" id="id-form" method="POST"
							modelAttribute="contrato">

							<form:input path="id" type="hidden" />


							<fieldset class="content-group">
								<legend class="text-bold">Alterar Contrato</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Número do
										contrato</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:input type="text"
												class="touchspin-set-value form-control" disabled="true"
												path="numero" style="display: block;"></form:input>
											<form:input path="numero" type="hidden" />
										</div>
									</div>
								</div>

								<div class="form-group">

									<label class="control-label col-lg-2">Tipo de pessoa</label>

									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:select id="selectTipoPessoaContrato"
												path="codigoTipoPessoaContratoTransiente"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${tipoPessoaContrato}"
													itemLabel="descricao" itemValue="codigo" />
											</form:select>
										</div>
									</div>
								</div>
								<div class="form-group" id="divPaciente">
									<label class="control-label col-lg-2">Beneficiário</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:select id="selectPaciente" path="paciente.id"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${pacientes}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
								</div>
								<div class="form-group" id="divEmpresa">
									<label class="control-label col-lg-2">Empresa</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:select id="selectEmpresa" path="empresa.id"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${empresas}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Situação do
										Contrato</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control"
												id="inputSituacao" placeholder=""
												path="situacaoEnum.descricao"
												cssErrorClass="field_error form-control" disabled="true" />
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-lg-2">Plano</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:select id="selectPlano" path="plano.id"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${planos}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Representante</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:select id="selectRepresentante" path="representante.id"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${representantes}" itemLabel="nome"
													itemValue="id" />
											</form:select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Início da
										vigência</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control"
												id="inputInicioVigencia" placeholder="Digite o data inicial"
												path="inicioVigencia"
												cssErrorClass="field_error form-control" />
											<form:errors path="inicioVigencia"
												cssClass="text-danger" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Final da vigência</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control"
												id="inputFimVigencia" placeholder="Digite o data final"
												path="fimVigencia"
												cssErrorClass="field_error form-control" />
											<form:errors path="fimVigencia"
												cssClass="text-danger" />

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-offset-1 col-xs-6">
										<form:errors path="valorContratoTransiente"
											cssClass="text-danger" />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Valor contratual</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control"
												id="inputValorContrato" placeholder="Digite o Valor"
												path="valorContratoTransiente"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Dia de Vencimento</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:select id="selectDiaVencimento"
												path="codigoDiaVencimentoTransiente" class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${diaVencimento}" itemLabel="descricao"
													itemValue="codigo" />
											</form:select>
										</div>
									</div>
								</div>

								<div class="text-right">
									<a href="${raiz}contratos" class=" btn bg-danger abrirJanela">Cancelar</a>

									<button type="submit" class="btn btn-primary">
										Alterar <i class="icon-arrow-right14 position-right"></i>
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
	src="<c:url value="/resources/js/contrato/contrato.js" />"></script>