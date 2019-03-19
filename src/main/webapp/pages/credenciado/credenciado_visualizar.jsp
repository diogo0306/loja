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
								class="text-semibold">Gestão</span> - Credenciado - Visualizar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}credenciados">Credenciados</a></li>
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
							action="${raiz}credenciado/salvar" id="id-form" method="POST"
							modelAttribute="credenciado">
							<fieldset class="content-group">
								<legend class="text-bold">Visualizar Credenciado</legend>

								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Nome</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputNome"
											placeholder="Digite o nome" path="nome" disabled="true"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Código</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control"
											id="inputCodigo" placeholder="Digite o código" path="codigo"
											disabled="true" cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Pessoa</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text"
											class="touchspin-set-value form-control" disabled="true"
											path="tipoPessoa.descricao" style="display: block;"></form:input>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-xs-1">
										<label for="field_4">CNPJ</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputCnpj"
											placeholder="Digite o número" path="cpfcnpj" disabled="true"
											cssErrorClass="field_error form-control"
											style="display: block;" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Situação</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text"
											class="touchspin-set-value form-control" disabled="true"
											path="situacao.descricao" style="display: block;"></form:input>
									</div>
									<div class="col-xs-1">
										<label for="field_3">Data</label>
									</div>
									<div class="col-xs-2" >
										<form:input type="text" class="form-control" id="inputData"
											placeholder="Digite o data" path="dataFormatada"
											disabled="true" style="display: block;"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<br>

								<legend class="text-bold">Dados Gerais - Complemento</legend>
								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">C.N.E.S</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputCnes"
											placeholder="Digite o número" path="cnes" disabled="true"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Nº de Dep. (IRRF)</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control"
											id="inputNumDep" placeholder="Digite o número"
											disabled="true" path="numDepIrrf"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="field_3"></label>
									</div>

								</div>
								<br> <br>
								<div class="row">

									<div class="col-sm-1"
										style="width: 1%; margin-left: 67px; margin-top: -19px">
										<form:checkbox id="emitorValores"
											path="considerarPercentualAcres" name="" label=""
											disabled="true" value="" />
									</div>
									<label for="inputDomingo" class="col-sm-10 control-label"
										style="text-align: left; margin-top: -26px">Considerar
										% de acréscimo após horário de atendimento</label>
								</div>

								<legend class="text-bold">Dados Gerais - Dados
									Bancários</legend>

								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Banco</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control" disabled="true"
											id="inputCodigo" placeholder="Digite o código"
											path="codBanco" cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Nome do Banco</label>
									</div>
									<div class="col-xs-4" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="input"
											disabled="true" placeholder="Digite o nome" path="nomeBanco"
											cssErrorClass="field_error form-control" />
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Agência</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputCodigo"
											disabled="true" placeholder="Digite o número" path="agencia"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Conta</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="input"
											disabled="true" placeholder="Digite o número" path="conta"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<br>

								<legend class="text-bold">Dados Gerais - Repasse</legend>

								<div class="row">

									<div class="col-xs-1">
										<label for="field_3">Código</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control"
											id="inputCodigo" placeholder="Digite o código"
											disabled="true" path="codRepasse"
											cssErrorClass="field_error form-control" />
									</div>

								</div>
								<br>

								<legend class="text-bold">Dados Gerais - Percentual</legend>

								<div class="row">

									<div class="col-xs-6">
										<label for="field_3">Percentual de desconto oferecido
											pelo prestador no atendimento aos beneficiários</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control" disabled="true"
											id="inputCodigo" path="percentualDesc"
											cssErrorClass="field_error form-control" />
									</div>

								</div>

								<br>

								

								<!-- <div class="row">

						<div class="col-xs-3">
							<label for="field_3">Honorários Médicos - Contas Internas</label>
						</div>
						<div class="col-xs-3" style="margin-left: -20px">
							<select class="form-control"></select>
						</div>

						<div class="col-xs-3">
							<label for="field_3">Honorários Médicos - Contas Externas</label>
						</div>
						<div class="col-xs-3" style="margin-left: -20px">
							<select class="form-control"></select>
						</div>

					</div> -->
								<!-- 	<br>
					<div class="row">

						<div class="col-xs-3">
							<label for="field_3">Diárias e Taxas Hospitares</label>
						</div>
						<div class="col-xs-3" style="margin-left: -20px">
							<select class="form-control"></select>
						</div>

						<div class="col-xs-3">
							<label for="field_3">Materiais e Medicamentos</label>
						</div>
						<div class="col-xs-3" style="margin-left: -20px">
							<select class="form-control"></select>
						</div>

					</div>

					<br> -->

								<legend class="text-bold">Percentual de Incidência</legend>

								<div class="row">

									<div class="col-xs-1">
										<label for="field_3">Materiais</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control" disabled="true"
											id="inputCodigo" path="percentualMateriais"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="field_3">Medicamentos</label>
									</div>
									<div class="col-xs-2" style="margin-left: 5px">
										<form:input type="number" class="form-control"
											id="inputCodigo" path="percentualMedicamentos"
											disabled="true" cssErrorClass="field_error form-control" />
									</div>

									<%-- <div class="col-xs-1">
							<label for="field_3">Data</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="date" class="form-control" id="inputCodigo"
								path="dataPercentual"
								cssErrorClass="field_error form-control" />
						</div> --%>
								</div>
								<br>
								<legend class="text-bold">Especialidades</legend>
								<br>
								<div class="row">

									<div class="col-sm-1"
										style="width: 1%; margin-left: 0px; margin-top: -19px">
										<form:checkbox id="emitorValores"
											path="considerarPercentualAcres" name="" label=""
											disabled="true" value="" />
									</div>
									<label for="inputDomingo" class="col-sm-10 control-label"
										style="text-align: left; margin-top: -26px">Marcar
										todas as especialidades</label><br>

									<!-- 	<div class="col-xs-3">
							<label for="field_3">Selecione a especialidade</label>
						</div>
						<div class="col-xs-5" style="margin-left: -20px">
							<select class="form-control"></select>
						</div> -->
								</div>
								<br>


								<div class="text-right">
									<a href="${raiz}credenciados"
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

<script type="text/javascript"
	src="<c:url value="/resources/js/credenciado/credenciado.js" />"></script>



