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
				action="${raiz}parametrizacao/salvar" id="id-form" method="POST"
				modelAttribute="parametrizacaoDTO">
		<%-- 		<fieldset class="content-group">
					<legend class="text-bold">Empresa</legend>
					<div class="row">
						<div class="form-group">
							<label class="control-label col-lg-1">Nome da Empresa</label>
							<div class="col-lg-5">
								<div class="input-group col-xs-8">
									
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-1">Empresas</label>
							<div class="col-lg-5">
								<div class="input-group col-xs-8">
									<form:select id="selectEmpresa"
										path="pametrizacao.empresa.id" class="form-control"
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
							<form:select id="inputMes"
								path="parametrizacao.codigoMesProcessamentoTransiente" required="true"
								class="form-control" cssErrorClass="field_error form-control">
								<form:option value="" label="Selecione" />
								<form:options items="${mesProcessamento}" itemLabel="descricao"
									itemValue="codigo" />
							</form:select>

						</div>
						<div class="col-xs-1">
							<label for="field_3">Ano</label>
						</div>
						<div class="col-xs-2" style="margin-left: -0px">
							<form:input type="text" maxlength="4" id="selectAnoProcessamento" placeholder="Digite o ano" 
								path="parametrizacao.anoProcessamento" required="true" class="form-control"
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
								id="inputPeriodoInicio" placeholder="Digite a data inicial"
								path="parametrizacao.periodoInicio" required="true"
								cssErrorClass="field_error form-control" />
						</div>
						<div class="col-xs-1">
							<label for="inputNumero">Final</label>
						</div>
						<div class="col-xs-2" style="margin-left: -0px">
							<form:input type="date" class="form-control" id="inputPeriodoFim"
								placeholder="Digite a data final"
								path="parametrizacao.periodoFim" required="true"
								cssErrorClass="field_error form-control" />
						</div>
					</div>
					<br />

				</fieldset>

				<fieldset class="content-group">
					<legend class="text-bold">Controle Biométrico</legend>

					<div class="row">

						<div class="col-xs-5">
							<form:select id="selectControleBiometrico"
								path="parametrizacao.codigoControleBiometricoTransiente" required="true"
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
								path="parametrizacao.codigoEditorTextoTransiente" required="true"
								class="form-control" cssErrorClass="field_error form-control">
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
								path="parametrizacao.utilizarIndiceClinicaMedica"
								name="indiceClinica" label="${agenda.domingo}"
								value="${agenda.domingo}" />
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
								path="parametrizacao.utilizarLayoutProprioGuia"
								name="utilizarLayout" label="${agenda.segunda}"
								value="${agenda.segunda}" />
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
								path="parametrizacao.permitirAtualizarValorLiberacaoGuia"
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
							<form:checkbox id="emitorValores"
								path="parametrizacao.emitirValoresGuiaSADT" name="emitorValores"
								label="${agenda.quarta}" value="${agenda.quarta}" />
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
								path="parametrizacao.emitirValoresGuiaAmbulatorio"
								name="guiaAmbulatorio" label="${agenda.quinta}"
								value="${agenda.quinta}" />
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
								path="parametrizacao.emitirValoresGuiaInternacao"
								name="valorInternacao" label="${agenda.sexta}"
								value="${agenda.sexta}" />
						</div>
						<label for="inputDomingo" class="col-sm-10 control-label"
							style="text-align: left; margin-top: -26px">Emitir
							valores em guias de internação</label>
					</div>


				</fieldset>

				<fieldset class="content-group">
					<legend class="text-bold">Guias - Autorizar procedimentos
						de até</legend>

					<div class="row">
						<div class="col-xs-3">
							<label for="inputNome">Liberação via autorizador on-line*</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="text" id="selectValorLiberacao" maxlength="10"
								path="parametrizacao.valorLiberacaoOnLine" required="true" class="form-control"
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
							<form:input type="text" id="inputLiberacao" maxlength="10"
								path="parametrizacao.valorLiberacaoOnLine" required="true" class="form-control"
								cssErrorClass="field_error form-control" />

						</div>
						<div class="col-xs-1">
							<label for="field_3">CHs</label>
						</div>
					</div>



				</fieldset>


				<fieldset class="content-group">
					<legend class="text-bold">Guias - Consistir retorno para
						consultas</legend>

					<div class="row">
						<div class="col-xs-3">
							<label for="simNao">Selecione</label>
						</div>

						<div class="col-xs-2" style="margin-left: -20px">
							<form:select id="simNao"
								path="parametrizacao.codigoSimNaoTransiente" required="true"
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
								path="parametrizacao.consistirRetornoConsultaPrazo" required="true" maxlength="5"
								class="form-control" cssErrorClass="field_error form-control" />
						</div>

					</div>

				</fieldset>

				<br>

				<fieldset class="content-group">
					<legend class="text-bold">Gerar validade da(s) carteirinha
						na emissão e/ou exportação considerando:</legend>

					<div class="row">
						<div class="col-xs-3">
							<label for="meses">Meses</label>
						</div>

						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="number" id="meses" maxlength="5"
								path="parametrizacao.gerarValidadeCarteiraPrazo" required="true"
								class="form-control" cssErrorClass="field_error form-control" />

						</div>

					</div>
				</fieldset>

				<br>

				<fieldset class="content-group">
					<legend class="text-bold">Dados referentes às
						mensalidades/boletos (Beneficiários e/ou Cobranças)</legend>

					<div class="row">
						<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
							<form:checkbox id="integrarCobranca"
								path="parametrizacao.integrarCobrancaFinanceiro"
								name="integrarCobranca" />
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

					<%-- <div class="row">

						<div class="col-xs-3">
							<label for="inputBairro">Banco</label>
						</div>
						<div class="col-xs-2" style="margin-left: -50px">
							<form:input type="number" class="form-control"
							path="parametrizacao."
								placeholder="Número do banco"
								cssErrorClass="field_error form-control" />
						</div>
						<form:input type="text" id="" path="parametrizacao.nome"
								class="form-control" cssErrorClass="field_error form-control" />
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
								placeholder="Número da conta
						cssErrorClass=" field_error form-control" />
						</div>
					</div>

					<br> --%>

					<div class="row">

						<div class="col-xs-3">
							<label for="tipoCobranca">Tipo de cobrança</label>
						</div>
						<div class="col-xs-2" style="margin-left: -50px">
							<form:select
								path="parametrizacao.codigoTipoCobrançaEmpresaTransiente" required="true"
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
							<form:select
								path="parametrizacao.codigoControleContratualTransiente" required="true"
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
							<form:input type="number" class="form-control" id="nossoNumero" required="true"
								placeholder="Digite o número" path="parametrizacao.nossoNumero"
								cssErrorClass="field_error form-control" />
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
								path="parametrizacao.prazoAtrasadoMensalidade" required="true"
								class="form-control" cssErrorClass="field_error form-control" />

						</div>

					</div>

					<br>

					<div class="row">
						<div class="col-xs-4">
							<label for="consistirCancelamento">Consistir cancelamento
								de contrato de pessoa física inadimplente à</label>
						</div>

						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="number" id="consistirCancelamento"
								path="parametrizacao.prazoCancelamentoContrato" required="true"
								class="form-control" cssErrorClass="field_error form-control" />

						</div>

					</div>
					<br>
					<div class="row">

						<div class="col-xs-2">
							<label for="inputMulta">Multa</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="number" class="form-control" id="inputMulta"
								placeholder="" path="parametrizacao.multaAtraso" required="true"
								cssErrorClass="field_error form-control" />
						</div>
						<div class="col-xs-2">
							<label for="inputDiaAtraso">% a partir do</label>
						</div>
						<div class="col-xs-2">
							<form:input type="number" class="form-control"
								id="inputDiaAtraso" placeholder=""
								path="parametrizacao.prazoAplicacaoMulta" required="true"
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
								placeholder="" path="parametrizacao.jurosFixoDiario" required="true"
								cssErrorClass="field_error form-control" />
						</div>
					</div>
					<br>

				</fieldset>

		<!-- 		<fieldset class="content-group">
					<legend class="text-bold">Informações Complementares de
						Cobrança</legend>
					<div class="text-left">
						<a href="#" class="btn btn-primary" data-toggle="modal"
							data-target="#modalExemplo">Vencimento de Cobranças</a>
					</div>
					<br>
					<div class="text-left">
						<a href="#" class="btn btn-primary"> Mensagens de Cobrança </a>
					</div>

				</fieldset> -->

				<fieldset>

					<br>
					<div class="text-right">
						<a href="${raiz}parametrizacoes"
							class=" btn bg-danger abrirJanela">Cancelar</a>

						<button type="submit" class="btn btn-primary">
							Salvar <i class="icon-arrow-right14 position-right"></i>
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
										<form:checkbox id="um" path="parametrizacao.um" name="um"
											label="${parametrizacao.um}" value="${parametrizacao.um}" />
									</div>
									<label for="inputUm" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">1</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="dois" path="parametrizacao.dois"
											name="dois" label="${parametrizacao.dois}"
											value="${parametrizacao.dois}" />
									</div>
									<label for="inputDois" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">2</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="tres" path="parametrizacao.tres"
											name="tres" label="${parametrizacao.tres}"
											value="${parametrizacao.tres}" />
									</div>
									<label for="inputTres" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">3</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="quatro" path="parametrizacao.quatro"
											name="quatro" label="${parametrizacao.quatro}"
											value="${parametrizacao.quatro}" />
									</div>
									<label for="inputQuatro" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">4</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="cinco" path="parametrizacao.cinco"
											name="cinco" label="${parametrizacao.cinco}"
											value="${parametrizacao.cinco}" />
									</div>
									<label for="inputCinco" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">5</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="seis" path="parametrizacao.seis"
											name="seis" label="${parametrizacao.seis}"
											value="${parametrizacao.seis}" />
									</div>
									<label for="inputSeis" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">6</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="sete" path="parametrizacao.sete"
											name="sete" label="${parametrizacao.sete}"
											value="${parametrizacao.sete}" />
									</div>
									<label for="inputSete" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">7</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="oito" path="parametrizacao.oito"
											name="oito" label="${parametrizacao.oito}"
											value="${parametrizacao.oito}" />
									</div>
									<label for="inputOito" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">8</label>
								</div>
								<div class="row">

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="nove" path="parametrizacao.nove"
											name="nove" label="${parametrizacao.nove}"
											value="${parametrizacao.nove}" />
									</div>
									<label for="inputNove" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">9</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="dez" path="parametrizacao.dez" name="dez"
											label="${parametrizacao.dez}" value="${parametrizacao.dez}" />
									</div>
									<label for="inputDois" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">10</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="onze" path="parametrizacao.onze"
											name="onze" label="${parametrizacao.onze}"
											value="${parametrizacao.onze}" />
									</div>
									<label for="inputOnze" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">11</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="doze" path="parametrizacao.doze"
											name="doze" label="${parametrizacao.doze}"
											value="${parametrizacao.doze}" />
									</div>
									<label for="inputDoze" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">12</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="treze" path="parametrizacao.treze"
											name="treze" label="${parametrizacao.treze}"
											value="${parametrizacao.treze}" />
									</div>
									<label for="inputTreze" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">13</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="quatorze" path="parametrizacao.quatorze"
											name="quatorze" label="${parametrizacao.quatorze}"
											value="${parametrizacao.quatorze}" />
									</div>
									<label for="inputQuatorze" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">14</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="quinze" path="parametrizacao.quinze"
											name="quinze" label="${parametrizacao.quinze}"
											value="${parametrizacao.quinze}" />
									</div>
									<label for="inputQuinze" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">15</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="dezesseis" path="parametrizacao.dezesseis"
											name="dezesseis" label="${parametrizacao.dezesseis}"
											value="${parametrizacao.dezesseis}" />
									</div>
									<label for="inputDezesseis" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">16</label>
								</div>
								<div class="row">

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="dezessete" path="parametrizacao.dezessete"
											name="dezessete" label="${parametrizacao.dezessete}"
											value="${parametrizacao.dezessete}" />
									</div>
									<label for="inputDezessete" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">17</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="dezoito" path="parametrizacao.dezoito"
											name="dezoito" label="${parametrizacao.dezoito}"
											value="${parametrizacao.dezoito}" />
									</div>
									<label for="inputDezoito" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">18</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="dezenove" path="parametrizacao.dezenove"
											name="dezenove" label="${parametrizacao.dezenove}"
											value="${parametrizacao.dezenove}" />
									</div>
									<label for="inputDezenove" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">19</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte" path="parametrizacao.vinte"
											name="vinte" label="${parametrizacao.vinte}"
											value="${parametrizacao.vinte}" />
									</div>
									<label for="inputVinte" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">20</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte_um" path="parametrizacao.vinte_um"
											name="vinte_um" label="${parametrizacao.vinte_um}"
											value="${parametrizacao.vinte_um}" />
									</div>
									<label for="inputVinte_um" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">21</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte_dois"
											path="parametrizacao.vinte_dois" name="vinte_dois"
											label="${parametrizacao.vinte_dois}"
											value="${parametrizacao.vinte_dois}" />
									</div>
									<label for="inputVinte_dois" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">22</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte_tres"
											path="parametrizacao.vinte_tres" name="vinte_tres"
											label="${parametrizacao.vinte_tres}"
											value="${parametrizacao.vinte_tres}" />
									</div>
									<label for="inputVinte_tres" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">23</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte_quatro"
											path="parametrizacao.vinte_quatro" name="vinte_quatro"
											label="${parametrizacao.vinte_quatro}"
											value="${parametrizacao.vinte_quatro}" />
									</div>
									<label for="inputVinte_quatro" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">24</label>
								</div>
								<div class="row">

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte_cinco"
											path="parametrizacao.vinte_cinco" name="vinte_cinco"
											label="${parametrizacao.vinte_cinco}"
											value="${parametrizacao.vinte_cinco}" />
									</div>
									<label for="inputVinte_cinco" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">25</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte_seis"
											path="parametrizacao.vinte_seis" name="vinte_seis"
											label="${parametrizacao.vinte_seis}"
											value="${parametrizacao.vinte_seis}" />
									</div>
									<label for="inputVinte_seis" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">26</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte_sete"
											path="parametrizacao.vinte_sete" name="vinte_sete"
											label="${parametrizacao.vinte_sete}"
											value="${parametrizacao.vinte_sete}" />
									</div>
									<label for="inputVinte_sete" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">27</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte_oito"
											path="parametrizacao.vinte_oito" name="vinte_oito"
											label="${parametrizacao.vinte_oito}"
											value="${parametrizacao.vinte_oito}" />
									</div>
									<label for="inputVinte_oito" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">28</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="vinte_nove"
											path="parametrizacao.vinte_nove" name="vinte_nove"
											label="${parametrizacao.vinte_nove}"
											value="${parametrizacao.vinte_nove}" />
									</div>
									<label for="inputVinte_nove" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">29</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="trinta" path="parametrizacao.trinta"
											name="trinta" label="${parametrizacao.trinta}"
											value="${parametrizacao.trinta}" />
									</div>
									<label for="inputTrinta" class="col-sm-1 control-label"
										style="text-align: left; margin-top: 0px">30</label>

									<div class="col-sm-1" style="width: 1%; margin-left: 0%;">
										<form:checkbox id="trinta_um" path="parametrizacao.trinta_um"
											name="trinta_um" label="${parametrizacao.trinta_um}"
											value="${parametrizacao.trinta_um}" />
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



<script type="text/javascript"
	src="<c:url value="/resources/js/parametrizacao/parametrizacao.js" />"></script>
