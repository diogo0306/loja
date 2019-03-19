<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

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
				action="${raiz}contasReceber/salvar" id="id-form" method="POST"
				modelAttribute="contasReceber">
				<fieldset class="content-group">
					<legend class="text-bold">Incluir Conta a Receber</legend>

					<div class="form-group">
						<label class="control-label col-lg-2">Credenciado</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:select id="selectCredenciado" path="credenciado.id"
									class="form-control" cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${credenciados}" itemLabel="nome"
										itemValue="id" />
								</form:select>
								<form:errors path="credenciado.id" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
						<label class="control-label col-lg-2" style="margin-left: -50px">Representante</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:select id="selectRepresentante" path="representante.id"
									class="form-control" cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${representantes}" itemLabel="nome"
										itemValue="id" />
								</form:select>
								<form:errors path="representante.id" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-2">Valor Cobrado (R$)</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">

								<form:input type="text" class="form-control" required="true"
									id="inputValor" placeholder="Digite o Valor"
									path="valorTransiente" cssErrorClass="field_error form-control" />
								<form:errors path="valorTransiente" cssClass="text-danger"
									class="has-error" />

							</div>
						</div>
						<label class="control-label col-lg-2" style="margin-left: -50px">Valor
							Pago (R$)</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:input type="text" class="form-control" required="true"
									id="inputValorPago" placeholder="Digite o Valor"
									path="valorPagoTransiente"
									cssErrorClass="field_error form-control" />
								<form:errors path="valorPagoTransiente" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-2">Data de Vencimento</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:input type="text" class="form-control" required="true"
									id="inputDataVencimento" placeholder="Digite o data"
									path="dataVencimento" cssErrorClass="field_error form-control" />
								<form:errors path="dataVencimento" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>

						<label class="control-label col-lg-2" style="margin-left: -50px">Data
							de Pagamento</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:input type="text" class="form-control" required="true"
									id="inputDataPagamento" placeholder="Digite o data"
									path="dataPagamento" cssErrorClass="field_error form-control" />
								<form:errors path="dataPagamento" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>


					<div class="form-group">
						<label class="control-label col-lg-2">Tipo de Conta</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:select id="selectTipoConta" required="true"
									path="codigoTipoContaTransiente" class="form-control"
									cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${tipos}" itemLabel="descricao"
										itemValue="codigo" />
								</form:select>
								<form:errors path="codigoTipoContaTransiente"
									cssClass="text-danger" class="has-error" />
							</div>
						</div>
					</div>

					<div class="text-right">
						<a href="${raiz}contas_receber" class=" btn bg-danger abrirJanela">Cancelar</a>

						<button type="submit" class="btn btn-primary">
							Salvar <i class="icon-arrow-right14 position-right"></i>
						</button>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/contas_receber/contas_receber.js" />"></script>
