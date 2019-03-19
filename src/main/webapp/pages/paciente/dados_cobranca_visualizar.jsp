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
								class="text-semibold">Gestão</span> - Beneficiário - Dados de Cobrança
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}pacientes">Beneficiários - Dados de Cobrança</a></li>
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
							action="${raiz}paciente/" id="formularioPaciente" method="POST"
							modelAttribute="paciente">
							<fieldset class="content-group">
								<legend class="text-bold">Dados</legend>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">Nome</label>
									</div>

									<div class="col-xs-8">
										<form:input type="text" class="form-control" id="inputNome"
											placeholder="Digite o Nome Completo" path="nome"
											disabled="true" cssErrorClass="field_error form-control" />
									</div>

								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="field_3">Dia de Vencimento</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control"
											id="inputComplemento" disabled="true"
											path="dadosCobranca.diaVencimento"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Tipo de Taxa</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCpf"
											path="dadosCobranca.tipoTaxaEnum.descricao" disabled="true"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Valor</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputRg"
											path="dadosCobranca.taxaAdesaoTransiente" disabled="true"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2"><strong>Banco/Agência/Conta</strong>
									</label>
								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="field_3">Banco</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCidade"
											path="dadosCobranca.codigoBanco" disabled="true"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Nome do Banco</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control"
											id="inputComplemento" disabled="true"
											path="dadosCobranca.nomeBanco"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Agência</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputNumero"
											disabled="true" placeholder="Digite o número"
											path="dadosCobranca.agencia"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Conta</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" disabled="true"
											id="inputComplemento" path="dadosCobranca.conta"
											cssErrorClass="field_error form-control" />
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-lg-2"><strong>Titular(Financeiro)
											- Nome/CPF</strong> </label>
								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">Nome</label>
									</div>

									<div class="col-xs-8">
										<form:input type="text" class="form-control" id="inputNome"
											disabled="true" path="dadosCobranca.nomeTitular"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">CPF</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputNome"
											path="dadosCobranca.cpfTitular" disabled="true"
											cssErrorClass="field_error form-control" />
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-lg-1"><strong>Débito
											Automático</strong> </label>
									<div class="col-sm-1" style="margin-top: 8px;">
										<form:checkbox id="debitoAutomatico" disabled="true"
											path="dadosCobranca.debitoAutomatico" name="debitoAutomatico"
											label="${dadosCobranca.debitoAutomatico}"
											value="${dadosCobranca.debitoAutomatico}" />
									</div>
								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="field_3">Banco</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCidade"
											placeholder="Não informado"
											path="dadosCobranca.codigoBancoDebito" disabled="true"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Nome do Banco</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control"
											id="inputComplemento" placeholder="Não informado"
											disabled="true" path="dadosCobranca.nomeBancoDebito"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Agência</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputNumero"
											disabled="true" placeholder="Não informado"
											path="dadosCobranca.agenciaDebito"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputNome">Conta</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" disabled="true"
											id="inputComplemento" placeholder="Não informado"
											path="dadosCobranca.contaDebito"
											cssErrorClass="field_error form-control" />
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-lg-2"><strong>Controle</strong>
									</label>
								</div>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="field_3">Tipo</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCidade"
											path="dadosCobranca.tipoControleEnum.descricao"
											disabled="true" cssErrorClass="field_error form-control" />
									</div>
								</div>

								<br>
								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 17%; margin-top: 6px;">
										<form:checkbox id="incluirProRata"
											path="dadosCobranca.incluirProRata" disabled="true"
											label="${dadosCobranca.incluirProRata}"
											value="${dadosCobranca.incluirProRata}" name="incluirProRata" />
									</div>

									<label for="incluirProRata" class="col-sm-4 control-label"
										style="text-align: left;">Incluir pro rata.</label>

								</div>
								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 17%; margin-top: 6px;">
										<form:checkbox id="gerarCobrancaInativos"
											path="dadosCobranca.gerarCobrancaInativos"
											name="gerarCobrancaInativos" disabled="true"
											label="${dadosCobranca.gerarCobrancaInativos}"
											value="${dadosCobranca.gerarCobrancaInativos}" />
									</div>
									<label for="aplicarMudancaFaixa" class="col-sm-5 control-label"
										style="text-align: left;">Gerar cobrança de inativos
										(Coparticipação/Franquia).</label>


								</div>
								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 17%; margin-top: 6px;">
										<form:checkbox id="gerarCobrancaInativos"
											path="dadosCobranca.buscarValoresUltimaVigencia"
											name="buscarValoresUltimaVigencia" disabled="true"
											label="${dadosCobranca.buscarValoresUltimaVigencia}"
											value="${dadosCobranca.buscarValoresUltimaVigencia}" />
									</div>
									<label for="aplicarMudancaFaixa" class="col-sm-5 control-label"
										style="text-align: left;">Buscar valores da última
										vigência independente do vencimento.</label>
								</div>

							</fieldset>

							<div class="text-right">
								<a href="${raiz}pacientes" class=" btn bg-danger abrirJanela">Voltar</a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

