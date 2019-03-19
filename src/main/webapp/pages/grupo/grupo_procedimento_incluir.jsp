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
								class="text-semibold">Gestão</span> - Grupos/Procedimentos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}grupos">Grupos/Procedimentos</a></li>
						<li class="active">Incluir Grupo</li>

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
							action="${raiz}grupoProcedimento/salvar"
							id="formularioGrupoProcedimento" method="POST"
							modelAttribute="grupoProcedimento">
							<fieldset class="content-group">
								<legend class="text-bold">Grupos/Procedimentos -
									Incluir Grupo</legend>



								<div class="form-group">
									<label class="control-label col-lg-2">Nome</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-10">
											<form:input type="text" class="form-control" id="inputNome"
												placeholder="Digite o nome" path="nome" required="true"
												cssErrorClass="field_error form-control" />
											<form:errors path="nome" cssClass="text-danger"
												class="has-error" />
										</div>

									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Código</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-6">
											<form:input type="number" class="form-control" id="inputNome"
												placeholder="Digite o número" path="codigo" required="true"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Tipo de
										Atendimento</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-6">
											<form:select id="selectTipoPlano" path="tipoTransiente"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${tipos}" itemLabel="descricao"
													itemValue="codigo" />
											</form:select>
											<form:errors path="tipoTransiente" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Valor de
										Pagamento (R$)</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control js-decimal"
												id="inputNome" placeholder="Digite o valor"
												path="valorPagamento" required="true"
												cssErrorClass="field_error form-control" />
											<form:errors path="valorPagamento" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Valor Cobrança
										(R$)</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control js-decimal"
												id="inputNome" placeholder="Digite o valor"
												path="valorCobranca" required="true"
												cssErrorClass="field_error form-control" />
											<form:errors path="valorCobranca" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>
								</div>
								<%-- <div class="col-xs-1">
										<label class="control-label">Hora inicial*</label>
									</div>
									<div class="col-xs-1">
										<form:input type="text" class="form-control js-horasMinutos" id="inputNome"
											placeholder="Digite a hora" path="horainicial" required="true"
											cssErrorClass="field_error form-control" />
										<form:errors path="horainicial" cssClass="text-danger"
											class="has-error" />
									</div>
									
									<div class="col-xs-1">
										<label class="control-label">Hora final*</label>
									</div>
									<div class="col-xs-1">
										<form:input type="text" class="form-control js-horasMinutos" id="inputNome"
											placeholder="Digite a hora" path="horarFinal" required="true"
											cssErrorClass="field_error form-control" />
										<form:errors path="horarFinal" cssClass="text-danger"
											class="has-error" />
									</div>
									 --%>

								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 16.5%; margin-top: 6px;">
										<form:checkbox id="pagamentoBalcao" path="imprimir"
											name="imprimirCarteirinha" label="${imprimir}"
											value="${imprimir}" />
									</div>

									<label for="pagamentoBalcao" class="col-sm-4 control-label"
										style="text-align: left;">Imprimir na carteirinha.</label>

								</div>
								<br />

								<div class="text-right">
									<a href="${raiz}grupos" class=" btn bg-danger abrirJanela">Cancelar</a>

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
	src="<c:url value="/resources/js/grupo_procedimento/grupo_procedimento.js" />"></script>
