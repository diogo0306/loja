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
								class="text-semibold">Gestão</span> - Contratos - Visualizar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}contratos">Contratos</a></li>
						<li class="active">Visualizar</li>

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
							action="${raiz}contrato/salvar" id="id-form" method="POST"
							modelAttribute="contrato">
							<fieldset class="content-group">
								<legend class="text-bold">Visualizar Contrato</legend>

								<c:if test="${contrato.tipoPessoaContratoEnum eq 'FISICA'}">
									<div class="form-group" id="">
										<label class="control-label col-lg-2">Beneficiário</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-8">
												<form:input type="text"
													class="touchspin-set-value form-control" disabled="true"
													path="paciente.nome" style="display: block;"></form:input>
											</div>
										</div>
									</div>


								</c:if>

								<c:if test="${contrato.tipoPessoaContratoEnum eq 'JURIDICA'}">
									<div class="form-group" id="">
										<label class="control-label col-lg-2">Empresa</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-8">
												<form:input type="text"
													class="touchspin-set-value form-control" disabled="true"
													path="empresa.nomefantasia" style="display: block;"></form:input>
											</div>
										</div>
									</div>
								</c:if>

								<div class="form-group">

									<label class="control-label col-lg-2">Tipo de pessoa</label>

									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:input type="text" id=""
												class="touchspin-set-value form-control" disabled="true"
												path="tipoPessoaContratoEnum.descricao"
												style="display: block;"></form:input>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Número do
										contrato</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:input type="text"
												class="touchspin-set-value form-control" disabled="true"
												path="numero" style="display: block;"></form:input>
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
											<form:input type="text"
												class="touchspin-set-value form-control" disabled="true"
												path="plano.nome" style="display: block;"></form:input>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Representante</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-8">
											<form:input type="text"
												class="touchspin-set-value form-control" disabled="true"
												path="representante.nome" style="display: block;"></form:input>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Início da
										vigência</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control" id=""
												disabled="true" placeholder="" path="inicioVigencia"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Final da vigência</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control" id=""
												disabled="true" placeholder="" path="fimVigencia"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Valor contratual</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control"
												id="inputValorContrato" placeholder="Digite o Valor"
												path="valorContrato"
												cssErrorClass="field_error form-control" disabled="true" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Dia de Vencimento</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control"
												id="inputDiaVencimento" placeholder=""
												path="diaVencimentoEnum.descricao"
												cssErrorClass="field_error form-control" disabled="true" />
										</div>
									</div>
								</div>


								<div class="text-right">
									<a href="${raiz}contratos" class=" btn bg-danger abrirJanela">Voltar</a>
								</div>

							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

