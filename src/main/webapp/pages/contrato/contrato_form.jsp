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
				action="${raiz}contrato/salvar" id="id-form" method="POST"
				modelAttribute="contrato">
				<fieldset class="content-group">
					<legend class="text-bold">Incluir Contrato</legend>

					<div class="form-group">

						<label class="control-label col-lg-2">Tipo de pessoa</label>

						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:select id="selectTipoPessoaContrato" required="true"
									path="codigoTipoPessoaContratoTransiente" class="form-control"
									cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${tipoPessoaContrato}"
										itemLabel="descricao" itemValue="codigo" />
								</form:select>
								<form:errors path="codigoTipoPessoaContratoTransiente"
									cssClass="text-danger" class="has-error" />
							</div>
						</div>
					</div>
					<div class="form-group" id="divPaciente">
						<label class="control-label col-lg-2">Beneficiário</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:select id="selectPaciente" path="paciente.id" 
									class="form-control" cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${pacientes}" itemLabel="nome"
										itemValue="id" />
								</form:select>
								<form:errors path="paciente.id" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>
					<div class="form-group" id="divEmpresa">
						<label class="control-label col-lg-2">Empresa</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:select id="selectEmpresa" path="empresa.id" 
									class="form-control" cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${empresas}" itemLabel="nome"
										itemValue="id" />
								</form:select>
								<form:errors path="empresa.id" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-2">Plano</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:select id="selectPlano" path="plano.id" required="true"
									class="form-control" cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${planos}" itemLabel="nome" itemValue="id" />
								</form:select>
								<form:errors path="plano.id" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-2">Representante</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-8">
								<form:select id="selectRepresentante" path="representante.id" required="true"
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
						<label class="control-label col-lg-2">Início da vigência</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-5">
								<form:input type="text" class="form-control" required="true"
									id="inputInicioVigencia" placeholder="Digite o data inicial"
									path="inicioVigencia" cssErrorClass="field_error form-control" />
								<form:errors path="inicioVigencia" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-2">Final da vigência</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-5">
								<form:input type="text" class="form-control" required="true"
									id="inputFimVigencia" placeholder="Digite o data final"
									path="fimVigencia" cssErrorClass="field_error form-control" />
									<form:errors path="fimVigencia" cssClass="text-danger" class="has-error" />
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
								<form:input type="text" class="form-control" required="true"
									id="inputValorContrato" placeholder="Digite o Valor"
									path="valorContratoTransiente"
									cssErrorClass="field_error form-control" />
									<form:errors path="valorContratoTransiente" cssClass="text-danger" class="has-error" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-2">Dia de Vencimento</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-5">
								<form:select id="selectDiaVencimento" required="true"
									path="codigoDiaVencimentoTransiente" class="form-control"
									cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${diaVencimento}" itemLabel="descricao"
										itemValue="codigo" />
								</form:select>
								<form:errors path="codigoDiaVencimentoTransiente" cssClass="text-danger" class="has-error" />
							</div>
						</div>
					</div>


					<div class="text-right">
						<a href="${raiz}contratos" class=" btn bg-danger abrirJanela">Cancelar</a>

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
	src="<c:url value="/resources/js/contrato/contrato.js" />"></script>
