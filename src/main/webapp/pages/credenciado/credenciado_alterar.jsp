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
								class="text-semibold">Gestão</span> - Credenciados - Alterar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}credenciados">Credenciados</a></li>
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
							action="${raiz}credenciado/alterar" id="id-form" method="POST"
							modelAttribute="credenciado">

							<form:input path="id" type="hidden" />

							<fieldset class="content-group">
								<legend class="text-bold">Incluir Credenciado</legend>

								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Nome</label>
									</div>
									<div class="col-xs-5" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputNome"
											placeholder="Digite o nome" path="nome"
											cssErrorClass="field_error form-control" />
										<form:errors path="nome" cssClass="text-danger"
											class="has-error" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Código</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control"
											id="inputCodigo" placeholder="Digite o código" path="codigo"
											cssErrorClass="field_error form-control" />
										<form:errors path="codigo" cssClass="text-danger"
											class="has-error" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Tipo Pessoa</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:select id="tipoPessoaJs" path="tipoPessoa"
											class="form-control js-select-tipo-pessoa"
											cssErrorClass="field_error form-control">

											<c:forEach var="tipopessoa" items="${tipoPessoas}">
												<option value="${tipopessoa}">${tipopessoa.descricao}
												</option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-xs-1">
										<label id="cpfOuCnpjLabel" for="field_4">CNPJ</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input required="true" type="text" class="form-control"
											id="inputCpfCnpj" placeholder="Digite o número"
											path="cpfcnpj" cssErrorClass="field_error form-control" />
										<form:errors path="cpfcnpj" cssClass="text-danger"
											class="has-error" />
									</div>
									<div class="col-xs-1" style="margin-left: +20px">
										<label for="field_3">Situação</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:select id="#" path="situacao" class="form-control"
											cssErrorClass="field_error form-control">
											<c:forEach var="situacaoEnum" items="${situacao}">
												<option value="${situacaoEnum}">${situacaoEnum.descricao}</option>
											</c:forEach>
										</form:select>
									</div>
									<div class="col-xs-1">
										<label for="field_3">Data</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputData"
											placeholder="Digite o data" path="data"
											cssErrorClass="field_error form-control" />
										<form:errors path="data" cssClass="text-danger"
											class="has-error" />
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
											placeholder="Digite o número" path="cnes"
											cssErrorClass="field_error form-control" />
										<form:errors path="cnes" cssClass="text-danger"
											class="has-error" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Nº de Dep. (IRRF)</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control"
											id="inputNumDep" placeholder="Digite o número"
											path="numDepIrrf" cssErrorClass="field_error form-control" />
										<form:errors path="numDepIrrf" cssClass="text-danger"
											class="has-error" />
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
											path="considerarPercentualAcres" name="" label="" value="" />
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
										<form:input type="number" class="form-control"
											id="inputCodigo" placeholder="Digite o código"
											path="codBanco" cssErrorClass="field_error form-control" />
										<form:errors path="codBanco" cssClass="text-danger"
											class="has-error" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Nome do Banco</label>
									</div>
									<div class="col-xs-4" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="input"
											placeholder="Digite o nome" path="nomeBanco"
											cssErrorClass="field_error form-control" />
										<form:errors path="nomeBanco" cssClass="text-danger"
											class="has-error" />
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-xs-1">
										<label for="field_3">Agência</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="inputCodigo"
											placeholder="Digite o número" path="agencia"
											cssErrorClass="field_error form-control" />
										<form:errors path="agencia" cssClass="text-danger"
											class="has-error" />
									</div>
									<div class="col-xs-1">
										<label for="field_3">Conta</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" class="form-control" id="input"
											placeholder="Digite o número" path="conta"
											cssErrorClass="field_error form-control" />
										<form:errors path="conta" cssClass="text-danger"
											class="has-error" />
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
											path="codRepasse" cssErrorClass="field_error form-control" />
										<form:errors path="codRepasse" cssClass="text-danger"
											class="has-error" />
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
										<form:input type="number" class="form-control"
											id="inputCodigo" path="percentualDesc"
											cssErrorClass="field_error form-control" />
										<form:errors path="percentualDesc" cssClass="text-danger"
											class="has-error" />
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
										<form:input type="number" class="form-control"
											id="inputCodigo" path="percentualMateriais"
											cssErrorClass="field_error form-control" />
										<form:errors path="percentualMateriais" cssClass="text-danger"
											class="has-error" />
									</div>

									<div class="col-xs-1">
										<label for="field_3">Medicamentos</label>
									</div>
									<div class="col-xs-2" style="margin-left: 5px">
										<form:input type="number" class="form-control"
											id="inputCodigo" path="percentualMedicamentos"
											cssErrorClass="field_error form-control" />
										<form:errors path="percentualMedicamentos"
											cssClass="text-danger" class="has-error" />
									</div>

									<%-- <div class="col-xs-1">
							<label for="field_3">Data</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="date" class="form-control" id="inputCodigo"
								path="credenciado.dataPercentual"
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
											path="considerarPercentualAcres" name="" label="" value="" />
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
										class=" btn bg-danger abrirJanela">Cancelar</a>

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
	src="<c:url value="/resources/js/credenciado/credenciado.js" />"></script>