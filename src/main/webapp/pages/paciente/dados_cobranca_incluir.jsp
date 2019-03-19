
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
								class="text-semibold">Gestão</span> - Beneficiários - Adicionar
							Dados de Cobrança
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}pacientes">Beneficiários</a></li>
						<li class="active">Dados de Cobrança</li>

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
							action="${raiz}paciente/adicionar/dados_cobranca" id="id-form"
							method="POST" modelAttribute="cobranca">
							<fieldset class="content-group">
								<legend class="text-bold">Incluir Dados de Cobrança</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Nome*</label>
									<div class="col-lg-8">
										<div class="input-group col-xs-7">
											<form:input type="text" class="form-control" id="inputNome"
												disabled="true" placeholder="Digite o nome"
												path="nomePaciente" cssErrorClass="field_error form-control" />
											<form:input path="idPaciente" type="hidden" />
											<form:input path="nomePaciente" type="hidden" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Dia de
										Vencimento*</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="number" min="1" max="31" class="form-control" id="inputNome"
												placeholder="Dia" path="diaVencimento"
												cssErrorClass="field_error form-control" />
											<form:errors path="diaVencimento" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2"><strong>Taxa
											de Adesão*</strong> </label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:select id="selectTipoTaxa" path="tipoTaxaTransiente"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${tipoTaxa}" itemLabel="descricao"
													itemValue="codigo" />
											</form:select>
										</div>
									</div>
								</div>

								<div class="form-group" id="divValor">
									<label class="control-label col-lg-2">Valor:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control" id="inputValor"
												placeholder="Valor" path="taxaAdesaoTransiente"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>
								<div class="form-group" id="divPercentual">
									<label class="control-label col-lg-2">Percentual:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control"
												id="inputPercentual" placeholder="Percentual"
												path="taxaAdesaoTransiente"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2"><strong>Banco/Agência/Conta</strong>
									</label>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Código:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control"
												placeholder="Código" path="codigoBanco"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Banco:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control"
												placeholder="Nome" path="nomeBanco"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Agência:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="number" class="form-control"
												placeholder="Agência" path="agencia"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Conta:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control"
												placeholder="Conta" path="conta"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2"><strong>Titular(Financeiro)
											- CPF/Nome</strong> </label>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">CPF:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control"
												placeholder="Cpf" path="cpfTitular" id="inputCPF"
												cssErrorClass="field_error form-control" />
											<form:errors path="cpfTitular" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Nome:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-10">
											<form:input type="text" class="form-control"
												placeholder="Nome" path="nomeTitular"
												cssErrorClass="field_error form-control" />
											<form:errors path="nomeTitular" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>
								</div>
								<br>

								<div class="form-group">
									<label class="control-label col-lg-1"><strong>Débito
											Automático</strong> </label>
									<div class="col-sm-1" style="margin-top: 8px;">
										<form:checkbox id="debitoAutomatico" path="debitoAutomatico"
											name="debitoAutomatico" label="${debitoAutomatico}"
											value="${debitoAutomatico}" />
									</div>
								</div>
								
								<div class="form-group" id="divCodigoBanco">
									<label class="control-label col-lg-2">Codigo:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control"
												placeholder="Código" path="codigoBancoDebito"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>
								<div class="form-group" id="divBanco">
									<label class="control-label col-lg-2">Banco:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control"
												placeholder="Nome" path="nomeBancoDebito"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>
								<div class="form-group" id="divAgencia">
									<label class="control-label col-lg-2">Agência:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="number" class="form-control"
												placeholder="Agência" path="agenciaDebito"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>
								<div class="form-group" id="divConta">
									<label class="control-label col-lg-2">Conta:</label>
									<div class="col-lg-3">
										<div class="input-group col-xs-6">
											<form:input type="number" class="form-control"
												placeholder="Conta" path="contaDebito"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2"><strong>Controle</strong>
									</label>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Tipo</label>
									<div class="col-lg-5">
										<div class="input-group col-xs-8">
											<form:select id="selectTipoPlano" path="tipoControleTransiente"
												class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${tipos}" itemLabel="descricao"
													itemValue="codigo" />
											</form:select>
										</div>
									</div>
								</div>


								<br>
								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 17%; margin-top: 6px;">
										<form:checkbox id="incluirProRata" path="incluirProRata"
											label="${incluirProRata}" value="${incluirProRata}"
											name="incluirProRata" />
									</div>

									<label for="incluirProRata" class="col-sm-4 control-label"
										style="text-align: left;">Incluir pro rata.</label>

								</div>
								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 17%; margin-top: 6px;">
										<form:checkbox id="gerarCobrancaInativos"
											path="gerarCobrancaInativos" name="gerarCobrancaInativos"
											label="${gerarCobrancaInativos}"
											value="${gerarCobrancaInativos}" />
									</div>
									<label for="aplicarMudancaFaixa" class="col-sm-5 control-label"
										style="text-align: left;">Gerar cobrança de inativos
										(Coparticipação/Franquia).</label>


								</div>
								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 17%; margin-top: 6px;">
										<form:checkbox id="gerarCobrancaInativos"
											path="buscarValoresUltimaVigencia"
											name="buscarValoresUltimaVigencia"
											label="${buscarValoresUltimaVigencia}"
											value="${buscarValoresUltimaVigencia}" />
									</div>
									<label for="aplicarMudancaFaixa" class="col-sm-5 control-label"
										style="text-align: left;">Buscar valores da última
										vigência independente do vencimento.</label>


								</div>
								<br>

								<div class="text-right">
									<a href="${raiz}pacientes" class=" btn bg-danger abrirJanela">Voltar</a>

									<button type="submit" id="botao-salvar" class="btn btn-primary">
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
	src="<c:url value="/resources/js/paciente/cobranca.js" />"></script>
