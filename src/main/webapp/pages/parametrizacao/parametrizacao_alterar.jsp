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
								class="text-semibold">Gestão</span> - Parâmetros - Alterar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}parametrizacoes">Parametrizações</a></li>
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
							action="${raiz}parametrizacao/alterar" id="id-form" method="POST"
							modelAttribute="parametrizacao">

							<form:input path="id" type="hidden" />

							<fieldset class="content-group">
								<legend class="text-bold">Alterar Parâmetros</legend>

						<%-- 		<fieldset class="content-group">
									<legend class="text-bold">Empresa</legend>
									<div class="row">
										<div class="form-group">
											<label class="control-label col-lg-2">Empresas</label>
											<div class="col-lg-5">
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
									</div>
								</fieldset> --%>
								
								<fieldset class="content-group">
									<legend class="text-bold">Processamento</legend>

									<div class="row">
										<div class="col-xs-1">
											<label for="inputMes">Mês</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:select id="selectMes"
												path="codigoMesProcessamentoTransiente" class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${mesProcessamento}"
													itemLabel="descricao" itemValue="codigo" />
											</form:select>

										</div>
										<div class="col-xs-1">
											<label for="field_3">Ano</label>
										</div>
										<div class="col-xs-2" style="margin-left: -0px">
											<form:input type="text" id="selectAnoProcessamento" maxlength="4"
												path="anoProcessamento" class="form-control"
												cssErrorClass="field_error form-control" />

										</div>
									</div>

									<br />

								</fieldset>

								<fieldset class="content-group">
									<legend class="text-bold">Período</legend>

									<div class="row">

										<div class="col-xs-1">
											<label for="inputRua">Inicial</label>
										</div>
										<div class="col-xs-2" style="margin-left: -20px">
											<form:input type="date" class="form-control"
												id="inputPeriodoInicio" placeholder="Digite o data inicial"
												path="periodoInicio"
												cssErrorClass="field_error form-control" />
										</div>
										<div class="col-xs-1">
											<label for="inputNumero">Final</label>
										</div>
										<div class="col-xs-2" style="margin-left: -0px">
											<form:input type="date" class="form-control"
												id="inputPeriodoFim" placeholder="Digite o data final"
												path="periodoFim"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<br />

								</fieldset>
							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Controle Biométrico</legend>

								<div class="row">

									<div class="col-xs-5">
										<form:select id="selectControleBiometrico"
											path="codigoControleBiometricoTransiente"
											class="form-control" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${controleBiometrico}"
												itemLabel="descricao" itemValue="codigo" />
										</form:select>
									</div>
								</div>
								<br />

							</fieldset>


							<fieldset class="content-group">
								<legend class="text-bold">Editor de Arquivo (txt)</legend>

								<div class="row">

									<div class="col-xs-5">
										<form:select id="editorArquivo"
											path="codigoEditorTextoTransiente" class="form-control"
											cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${editorArquivo}" itemLabel="descricao"
												itemValue="codigo" />
										</form:select>
									</div>
								</div>
								<br />

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Guias</legend>

								<div class="row">

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="utilizarIndiceClinicaMedica"
											path="utilizarIndiceClinicaMedica" name="indiceClinica"
											label="${agenda.domingo}" value="${agenda.domingo}" />
									</div>
									<label for="utilizarIndiceClinicaMedica"
										class="col-sm-10 control-label"
										style="text-align: left; margin-top: -6px">Utilizar
										índice de clínica médica quando não houver índice ou valor
										gerenciado para o procedimento</label>

								</div>

								<div class="row">

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="utilizarLayout"
											path="utilizarLayoutProprioGuia" name="utilizarLayout"
											label="${agenda.segunda}" value="${agenda.segunda}" />
									</div>
									<label for="inputDomingo" class="col-sm-10 control-label"
										style="text-align: left; margin-top: -6px">Utilizar
										layout do próprio modelo da guia</label>

								</div>

								<br>

								<div class="row">

									<div class="col-sm-1"
										style="width: 1%; margin-left: 0%; margin-top: -19px">
										<form:checkbox id="permitirAterar"
											path="permitirAtualizarValorLiberacaoGuia"
											name="permitirAterar" label="${agenda.terca}"
											value="${agenda.terca}" />
									</div>
									<label for="inputDomingo" class="col-sm-10 control-label"
										style="text-align: left; margin-top: -26px">Permitir
										alterar valores na liberação da guia</label>

								</div>

								<br>

								<div class="row">

									<div class="col-sm-1"
										style="width: 1%; margin-left: 0%; margin-top: -19px">
										<form:checkbox id="emitorValores" path="emitirValoresGuiaSADT"
											name="emitorValores" label="${agenda.quarta}"
											value="${agenda.quarta}" />
									</div>
									<label for="inputDomingo" class="col-sm-10 control-label"
										style="text-align: left; margin-top: -26px">Emitir
										valores em guias de S.A.D.T.</label>
								</div>
								<br>
								<div class="row">

									<div class="col-sm-1"
										style="width: 1%; margin-left: 0%; margin-top: -19px">
										<form:checkbox id="guiaAmbulatorio"
											path="emitirValoresGuiaAmbulatorio" name="guiaAmbulatorio"
											label="${agenda.quinta}" value="${agenda.quinta}" />
									</div>
									<label for="inputDomingo" class="col-sm-10 control-label"
										style="text-align: left; margin-top: -26px">Emitir
										valores em guias de ambulatório</label>
								</div>

								<br>

								<div class="row">

									<div class="col-sm-1"
										style="width: 1%; margin-left: 0%; margin-top: -19px">
										<form:checkbox id="valorInternacao"
											path="emitirValoresGuiaInternacao" name="valorInternacao"
											label="${agenda.sexta}" value="${agenda.sexta}" />
									</div>
									<label for="inputDomingo" class="col-sm-10 control-label"
										style="text-align: left; margin-top: -26px">Emitir
										valores em guias de internação</label>
								</div>


							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Guias - Autorizar
									procedimentos de até</legend>

								<div class="row">
									<div class="col-xs-3">
										<label for="inputNome">Liberação via autorizador
											on-line*</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" id="selectValorLiberacao"
											path="valorLiberacaoOnLine" class="form-control"
											cssErrorClass="field_error form-control" />

									</div>
									<div class="col-xs-1">
										<label for="field_3">CHs</label>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-xs-3">
										<label for="inputLiberacao">Liberação via sistema</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="text" id="inputLiberacao"
											path="valorLiberacaoOnLine" class="form-control"
											cssErrorClass="field_error form-control" />

									</div>
									<div class="col-xs-1">
										<label for="field_3">CHs</label>
									</div>
								</div>



							</fieldset>


							<fieldset class="content-group">
								<legend class="text-bold">Guias - Consistir retorno
									para consultas</legend>

								<div class="row">
									<div class="col-xs-3">
										<label for="simNao">Selecione</label>
									</div>

									<div class="col-xs-2" style="margin-left: -20px">
										<form:select id="simNao" path="codigoSimNaoTransiente"
											class="form-control" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${consistirRetornoConsulta}"
												itemLabel="descricao" itemValue="codigo" />
										</form:select>
									</div>

								</div>
								<br>
								<div class="row">
									<div class="col-xs-3">
										<label for="dias">Dia(s)</label>
									</div>

									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" id="dias"
											path="consistirRetornoConsultaPrazo" class="form-control"
											cssErrorClass="field_error form-control" />
									</div>

								</div>

							</fieldset>

							<br>

							<fieldset class="content-group">
								<legend class="text-bold">Gerar validade da(s)
									carteirinha na emissão e/ou exportação considerando:</legend>

								<div class="row">
									<div class="col-xs-3">
										<label for="meses">Meses</label>
									</div>

									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" id="meses"
											path="gerarValidadeCarteiraPrazo" class="form-control"
											cssErrorClass="field_error form-control" />

									</div>

								</div>
							</fieldset>


							<fieldset class="content-group">
								<legend class="text-bold">Dados referentes às
									mensalidades/boletos (Beneficiários e/ou Cobranças)</legend>

								<div class="row">
									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="integrarCobranca"
											path="integrarCobrancaFinanceiro" name="integrarCobranca" />
									</div>
									<label for="integrarCobranca" class="col-sm-10 control-label"
										style="text-align: left; margin-top: -6px">Integrar
										cobranças com o financeiro</label>
								</div>

								<br>

								<div class="row">

									<%-- 		<div class="col-xs-3">
							<label for="empresaIntegrar">Empresa para
								integr./cadastros</label>
						</div>
						<div class="col-xs-2" style="margin-left: -50px">
							<form:select path="" class="form-control"
								cssErrorClass="field_error form-control">
								<form:option value="" label="Selecione" />
								<form:options items="${uf}" itemLabel="descricao"
									itemValue="codigo" />
							</form:select>
						</div> --%>

								</div>

								<br>

								<!-- 	<div class="row">

									<div class="col-xs-3">
										<label for="inputBairro">Banco</label>
									</div>
									<div class="col-xs-2" style="margin-left: -50px">
										<input type="number" class="form-control"
											placeholder="Número do banco"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-1">
										<label for="inputBairro">Agência</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<input type="number" class="form-control"
											placeholder="Número da agência"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputBairro">Conta</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<input type="number" class="form-control"
											placeholder="Número da conta"
											cssErrorClass="
												field_error form-control" />
									</div>
								</div>

								<br> -->

								<div class="row">

									<div class="col-xs-3">
										<label for="tipoCobranca">Tipo de cobrança</label>
									</div>
									<div class="col-xs-2" style="margin-left: -50px">
										<form:select path="codigoTipoCobrançaEmpresaTransiente"
											class="form-control" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${tipoCobrancaEmpresa}"
												itemLabel="descricao" itemValue="codigo" />
										</form:select>
									</div>

								</div>
								<br>
								<div class="row">

									<div class="col-xs-3">
										<label for="controleContratual">Controle contratual</label>
									</div>
									<div class="col-xs-2" style="margin-left: -50px">
										<form:select path="codigoControleContratualTransiente"
											class="form-control" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${controleContratual}"
												itemLabel="descricao" itemValue="codigo" />
										</form:select>
									</div>

								</div>
								<br>
								<div class="row">

									<div class="col-xs-3">
										<label for="nossoNumero">Nosso Número</label>
									</div>
									<div class="col-xs-2" style="margin-left: -50px">
										<form:input type="number" class="form-control"
											id="nossoNumero" placeholder="Digite o número"
											path="nossoNumero" cssErrorClass="field_error form-control" />
									</div>

								</div>

								<br>
							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Dados referentes às
									mensalidades/boletos (Beneficiários e/ou Cobranças) - Atraso de
									mesalidade</legend>

								<div class="row">
									<div class="col-xs-4">
										<label for="consistirAtraso">Consistir atraso no
											atendimeto a partir de</label>
									</div>

									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" id="consistirAtraso"
											path="prazoAtrasadoMensalidade" class="form-control"
											cssErrorClass="field_error form-control" />

									</div>

								</div>

								<br>

								<div class="row">
									<div class="col-xs-4">
										<label for="consistirCancelamento">Consistir
											cancelamento de contrato de pessoa física inadimplente à</label>
									</div>

									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" id="consistirCancelamento"
											path="prazoCancelamentoContrato" class="form-control"
											cssErrorClass="field_error form-control" />

									</div>

								</div>
								<br>
								<div class="row">

									<div class="col-xs-2">
										<label for="inputMulta">Multa</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control" id="inputMulta"
											placeholder="" path="multaAtraso"
											cssErrorClass="field_error form-control" />
									</div>
									<div class="col-xs-2">
										<label for="inputDiaAtraso">% a partir do</label>
									</div>
									<div class="col-xs-2">
										<form:input type="number" class="form-control"
											id="inputDiaAtraso" placeholder="" path="prazoAplicacaoMulta"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputCep2">Dias de atraso</label>
									</div>

								</div>

								<br>
								<div class="row">

									<div class="col-xs-2">
										<label for="jurosFixos">Juros fixos diários</label>
									</div>
									<div class="col-xs-2" style="margin-left: -20px">
										<form:input type="number" class="form-control" id="jurosFixos"
											placeholder="" path="jurosFixoDiario"
											cssErrorClass="field_error form-control" />
									</div>
								</div>
								<br>

							</fieldset>
<!-- 
							<fieldset class="content-group">
								<legend class="text-bold">Informações Complementares de
									Cobrança</legend>
								<div class="text-left">
									<a href="#" class="btn btn-primary" data-toggle="modal"
										data-target="#modalExemplo">Vencimento de Cobranças</a>
								</div>
								<br>
								<div class="text-left">
									<a href="#" class="btn btn-primary"> Mensagens de Cobrança
									</a>
								</div>

							</fieldset> -->

							<fieldset>


								<div class="text-right">
									<a href="${raiz}parametrizacoes"
										class=" btn bg-danger abrirJanela">Cancelar</a>

									<button type="submit" class="btn btn-primary">
										Alterar <i class="icon-arrow-right14 position-right"></i>
									</button>
								</div>
							</fieldset>

					<%-- 		<!-- Modal -->
							<div class="modal fade" id="modalExemplo" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Selecione
												o(s) dia(s) de vencimento</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Fechar">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">

											<div class="row">

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="um" path="um" name="um" label="${um}"
														value="${um}" />
												</div>
												<label for="inputUm" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">1</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="dois" path="dois" name="dois"
														label="${dois}" value="${dois}" />
												</div>
												<label for="inputDois" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">2</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="tres" path="tres" name="tres"
														label="${tres}" value="${tres}" />
												</div>
												<label for="inputTres" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">3</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="quatro" path="quatro" name="quatro"
														label="${quatro}" value="${quatro}" />
												</div>
												<label for="inputQuatro" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">4</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="cinco" path="cinco" name="cinco"
														label="${cinco}" value="${cinco}" />
												</div>
												<label for="inputCinco" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">5</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="seis" path="seis" name="seis"
														label="${seis}" value="${seis}" />
												</div>
												<label for="inputSeis" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">6</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="sete" path="sete" name="sete"
														label="${sete}" value="${sete}" />
												</div>
												<label for="inputSete" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">7</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="oito" path="oito" name="oito"
														label="${oito}" value="${oito}" />
												</div>
												<label for="inputOito" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">8</label>
											</div>
											<div class="row">

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="nove" path="nove" name="nove"
														label="${nove}" value="${nove}" />
												</div>
												<label for="inputNove" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">9</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="dez" path="dez" name="dez"
														label="${dez}" value="${dez}" />
												</div>
												<label for="inputDois" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">10</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="onze" path="onze" name="onze"
														label="${onze}" value="${onze}" />
												</div>
												<label for="inputOnze" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">11</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="doze" path="doze" name="doze"
														label="${doze}" value="${doze}" />
												</div>
												<label for="inputDoze" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">12</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="treze" path="treze" name="treze"
														label="${treze}" value="${treze}" />
												</div>
												<label for="inputTreze" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">13</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="quatorze" path="quatorze"
														name="quatorze" label="${quatorze}" value="${quatorze}" />
												</div>
												<label for="inputQuatorze" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">14</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="quinze" path="quinze" name="quinze"
														label="${quinze}" value="${quinze}" />
												</div>
												<label for="inputQuinze" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">15</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="dezesseis" path="dezesseis"
														name="dezesseis" label="${dezesseis}" value="${dezesseis}" />
												</div>
												<label for="inputDezesseis" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">16</label>
											</div>
											<div class="row">

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="dezessete" path="dezessete"
														name="dezessete" label="${dezessete}" value="${dezessete}" />
												</div>
												<label for="inputDezessete" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">17</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="dezoito" path="dezoito" name="dezoito"
														label="${dezoito}" value="${dezoito}" />
												</div>
												<label for="inputDezoito" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">18</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="dezenove" path="dezenove"
														name="dezenove" label="${dezenove}" value="${dezenove}" />
												</div>
												<label for="inputDezenove" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">19</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte" path="vinte" name="vinte"
														label="${vinte}" value="${vinte}" />
												</div>
												<label for="inputVinte" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">20</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte_um" path="vinte_um"
														name="vinte_um" label="${vinte_um}" value="${vinte_um}" />
												</div>
												<label for="inputVinte_um" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">21</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte_dois" path="vinte_dois"
														name="vinte_dois" label="${vinte_dois}"
														value="${vinte_dois}" />
												</div>
												<label for="inputVinte_dois" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">22</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte_tres" path="vinte_tres"
														name="vinte_tres" label="${vinte_tres}"
														value="${vinte_tres}" />
												</div>
												<label for="inputVinte_tres" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">23</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte_quatro" path="vinte_quatro"
														name="vinte_quatro" label="${vinte_quatro}"
														value="${vinte_quatro}" />
												</div>
												<label for="inputVinte_quatro"
													class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">24</label>
											</div>
											<div class="row">

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte_cinco" path="vinte_cinco"
														name="vinte_cinco" label="${vinte_cinco}"
														value="${vinte_cinco}" />
												</div>
												<label for="inputVinte_cinco" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">25</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte_seis" path="vinte_seis"
														name="vinte_seis" label="${vinte_seis}"
														value="${vinte_seis}" />
												</div>
												<label for="inputVinte_seis" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">26</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte_sete" path="vinte_sete"
														name="vinte_sete" label="${vinte_sete}"
														value="${vinte_sete}" />
												</div>
												<label for="inputVinte_sete" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">27</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte_oito" path="vinte_oito"
														name="vinte_oito" label="${vinte_oito}"
														value="${vinte_oito}" />
												</div>
												<label for="inputVinte_oito" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">28</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="vinte_nove" path="vinte_nove"
														name="vinte_nove" label="${vinte_nove}"
														value="${vinte_nove}" />
												</div>
												<label for="inputVinte_nove" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">29</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="trinta" path="trinta" name="trinta"
														label="${trinta}" value="${trinta}" />
												</div>
												<label for="inputTrinta" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">30</label>

												<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
													<form:checkbox id="trinta_um" path="trinta_um"
														name="trinta_um" label="${trinta_um}" value="${trinta_um}" />
												</div>
												<label for="inputTrinta_um" class="col-sm-1 control-label"
													style="text-align: left; margin-top: 0px">31</label>

											</div>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Fechar</button>
											<button type="button" class="btn btn-primary">Adicionar</button>
										</div>
									</div>
								</div>
							</div> --%>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/parametrizacao/parametrizacao.js" />"></script>