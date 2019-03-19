<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />

<table class="ui-eClinic table table-striped table-hover">
	<thead>
		<tr>
			<th>Status</th>
			<th>Nome Paciente</th>
			<th>RG</th>
			<th>CPF</th>
			<th>Turno</th>
			<th>Tipo</th>
			<th>Confirmar</th>
			<th>Atender</th>
			<th>Finalizar</th>
			<th>Cancelar</th>
			<th>Recibo</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach var="agendamento" items="${agendamentos}">
			<tr>
				<td><label
					class="label ${agendamento.statusAgendamentoEnum.estilo}"><strong>${agendamento.statusAgendamentoEnum.descricao}</strong></label></td>
				<td>${agendamento.nomePaciente}</td>

				<td>${agendamento.paciente.documentacao.rg}</td>
				<td>${agendamento.paciente.documentacao.cpf}</td>
				<td>${agendamento.turnoAgendamentoEnum.descricao}</td>
				<td>${agendamento.tipoAgendamentoConsultaEnum.descricao}</td>

				<td><c:if
						test="${agendamento.statusAgendamentoEnum.descricao == txEnumAguardando && agendamento.statusAgendamentoEnum.descricao != txEnumAtendido}">
						<a href="#" data-toggle="modal"
							data-target="#modalConfirmacao${agendamento.id}"
							title="Confimar Consulta"> <span
							class="glyphicon glyphicon-ok"></span>
						</a>
					</c:if>


					<div class="modal fade" id="modalConfirmacao${agendamento.id}">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">

								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">
										<b>Confirmação Consulta</b>
									</h4>
								</div>


								<div class="modal-body">

									<form:form action="${raiz}consulta/confirmar" method="POST"
										modelAttribute="agendamento" class="formulario_acoes"
										id="formConfirmacao${agendamento.id}">

										<form:hidden path="id" value="${agendamento.id}" />


										<div class="divSeparador2">

											<c:if test="${agendamento.paciente.id == null}">
												<div class="row">
													<div class="col-xs-2">
														<label>Nome do Paciente</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															value="${agendamento.nomePacienteNaoCadastrado}"
															readonly="true" id="inputNome"
															cssErrorClass="field_error form-control"
															path="nomePacienteNaoCadastrado" />
													</div>
													<div class="col-xs-2">
														<label for="field_3">Médico</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															value="${agendamento.medico.nome}" id="inputMedico"
															readonly="true" path="medico.nome" />
													</div>
												</div>
												<br />

												<div class="row">
													<div class="col-xs-2">
														<label for="inputCep">RG do Paciente</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															id="inputRg"
															value="${agendamento.rgPacienteNaoCadastrado}"
															path="rgPacienteNaoCadastrado" />
													</div>
													<div class="col-xs-2">
														<label for="inputCep">CPF do Paciente</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															id="inputCpf"
															value="${agendamento.cpfPacienteNaoCadastrado}"
															path="cpfPacienteNaoCadastrado" />
													</div>
												</div>
												<br />

												<div class="row">
													<div class="col-xs-2">
														<label for="inputNome">Data Nascimento</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" class="form-control"
															id="inputDataNascimentoNaoCadastrado"
															placeholder="Digite a Data"
															path="paciente.dataNascimentoFormatada"
															cssErrorClass="field_error form-control" />
													</div>
													<div class="col-xs-2">
														<label for="inputNome">Orgão Expedidor</label>
													</div>
													<div class="col-xs-4">
														<form:select id="selectTipoSexo"
															path="paciente.documentacao.orgaoEmissorTransiente"
															class="form-control"
															cssErrorClass="field_error form-control">
															<form:option value="" label="Selecione" />
															<form:options items="${orgaos}" itemLabel="descricao"
																itemValue="codigo" />
														</form:select>
													</div>
												</div>
												<br />

												<div class="row">
													<div class="col-xs-2">
														<label for="field_1">Cidade</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															id="inputRua" placeholder="Digite a Rua"
															path="paciente.endereco.cidade"
															cssErrorClass="field_error form-control" />
													</div>
													<div class="col-xs-2">
														<label for="field_1">Endereço</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															id="inputRua" placeholder="Digite a Rua"
															path="paciente.endereco.logradouro"
															cssErrorClass="field_error form-control" />
													</div>
												</div>
												<br />
												<div class="row">
													<div class="col-xs-2">
														<label for="field_1">Bairro</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															id="inputBairro" placeholder="Digite o bairro"
															path="paciente.endereco.bairro"
															cssErrorClass="field_error form-control" />
													</div>
													<div class="col-xs-2">
														<label for="inputNome">Celular</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" class="form-control"
															id="inputCelular" placeholder="Digite o Celular"
															path="paciente.documentacao.celular"
															cssErrorClass="field_error form-control" />
													</div>

												</div>
												<br />
												<div class="row">
													<div class="col-xs-2">
														<label for="inputNome">CEP</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" class="form-control"
															id="inputCelular" placeholder="Digite o CEP"
															path="paciente.endereco.cep"
															cssErrorClass="field_error form-control" />
													</div>
													<div class="col-xs-2">
														<label for="field_3">Número</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" class="form-control"
															id="inputCelular" placeholder="Digite o número"
															path="paciente.endereco.numero"
															cssErrorClass="field_error form-control" />
													</div>
												</div>
												<br />
												<div class="row">
													<div class="col-xs-2">
														<label for="inputNome">UF</label>
													</div>
													<div class="col-xs-4">
														<form:select id="selectTipoSexo"
															path="paciente.endereco.estadoTransiente"
															class="form-control"
															cssErrorClass="field_error form-control">
															<form:option value="" label="Selecione" />
															<form:options items="${uf}" itemLabel="descricao"
																itemValue="codigo" />
														</form:select>
													</div>
													<div class="col-xs-2">
														<label for="field_3">Sexo</label>
													</div>
													<div class="col-xs-4">
														<form:select id="selectTipoPlano"
															path="paciente.codigoSexoTransiente" class="form-control"
															cssErrorClass="field_error form-control">
															<form:option value="" label="Selecione" />
															<form:options items="${sexos}" itemLabel="descricao"
																itemValue="codigo" />
														</form:select>
													</div>
												</div>
												<br />

											</c:if>



											<c:if test="${agendamento.paciente.id != null}">
												<div class="row">
													<div class="col-xs-2">
														<label>Nome do Paciente</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															id="inputNome" readonly="true"
															value="${agendamento.paciente.nome}" path="paciente.nome" />
													</div>
													<div class="col-xs-2">
														<label for="field_3">Médico</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															value="${agendamento.medico.nome}" id="inputMedico"
															readonly="true" path="medico.nome" />
													</div>

												</div>
												<br />
												<div class="row">
													<div class="col-xs-2">
														<label for="inputCep">RG do Paciente</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															value="${agendamento.paciente.documentacao.rg}"
															id="inputRg" readonly="true"
															path="paciente.documentacao.rg" />
													</div>
												</div>
												<br />
												<div class="row">
													<div class="col-xs-2">
														<label for="inputCep">CPF do Paciente</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															value="${agendamento.paciente.documentacao.cpf}"
															id="inputCpf2" readonly="true"
															path="paciente.documentacao.cpf" />
													</div>
												</div>
												<br />
												<div class="row">
													<div class="col-xs-2">
														<label for="field_3">Tipo Agendamento</label>
													</div>
													<div class="col-xs-4">
														<form:input type="text" cssClass="form-control"
															value="${agendamento.tipoAgendamentoEnum.descricao}"
															id="inputRg" readonly="true" path="medico.nome" />
													</div>
												</div>

											</c:if>
											<br />
											<div class="row">
												<div class="col-xs-2">
													<label for="field_3">CPF Contratante</label>
												</div>
												<div class="col-xs-4">
													<form:input type="text" cssClass="form-control cpf"
														id="inputCpfPagador" path="cpfPagador" />
												</div>
												<div class="col-xs-2">
													<label for="field_3">Valor Consulta R$</label>
												</div>
												<div class="col-xs-4">
													<form:input type="text" cssClass="form-control valor"
														value="${agendamento.valorConsulta}" readonly="true"
														path="valorConsulta" />
												</div>
											</div>
											<br />
											<div class="row">
												<div class="col-xs-2">
													<label for="field_3">Observação</label>
												</div>
												<div class="col-xs-4">
													<form:input type="text" cssClass="form-control"
														value="${agendamento.observacaoConsulta}" id="inputRg"
														path="observacaoConsulta" />
												</div>
											</div>

										</div>
									</form:form>
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancelar</button>
									<button type="button" class="btn btn-primary abrirJanela"
										onclick="submeterComJanela(formConfirmacao${agendamento.id}, modalConfirmacao${agendamento.id})">Confirmar</button>
								</div>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div> <!-- /.modal --> </a></td>

				<td><c:if
						test="${agendamento.statusAgendamentoEnum.descricao == txEnumConfirmado && agendamento.statusAgendamentoEnum.descricao != txEnumAtendido}">
						<form:form action="${raiz}consulta/atender" method="POST"
							modelAttribute="agendamento" class="formulario_acoes"
							id="formAtendimento${agendamento.id}">
							<form:hidden path="id" value="${agendamento.id}" />
							<a href="#" data-toggle="modal"
								data-target="#modalAtendimento${agendamento.id}"
								title="Atender Paciente"> <span
								class="glyphicon glyphicon-log-in"></span>
							</a>
						</form:form>
						<div class="modal fade" id="modalAtendimento${agendamento.id}">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title">Atender</h4>
									</div>
									<div class="modal-body">
										<p>Deseja ATENDER o paciente ${agendamento.nomePaciente} ?</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Não</button>

										<button type="button" class="btn btn-primary abrirJanela"
											onclick="submeterComJanela(formAtendimento${agendamento.id},modalAtendimento${agendamento.id} )">Sim</button>

									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
					</c:if></td>

				<td><c:if
						test="${agendamento.statusAgendamentoEnum.descricao == txEnumEmAtendimento}">
						<form:form action="${raiz}consulta/finalizar_atendimento"
							method="POST" modelAttribute="agendamento"
							class="formulario_acoes"
							id="formFecharAtendimento${agendamento.id}">
							<form:hidden path="id" value="${agendamento.id}" />
							<form:hidden path="consulta.observacoesMedicas"
								value="${agendamento.consulta.observacoesMedicas}" />
							<a href="#" data-toggle="modal"
								data-target="#modalFecharAtendimento${agendamento.id}"
								title="Finalizar atendimento"> <span
								class="glyphicon glyphicon-log-out"></span>
							</a>
						</form:form>
						<div class="modal fade"
							id="modalFecharAtendimento${agendamento.id}">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title">Finalizar Atendimento</h4>
									</div>
									<div class="modal-body">
										<form:form action="${raiz}consulta/finalizar_atendimento" method="POST"
											modelAttribute="agendamento" class="formulario_acoes"
											id="formConfirmacao${agendamento.id}">

											<form:hidden path="id" value="${agendamento.id}" />
											<form:hidden path="consulta.observacoesMedicas"
												value="${agendamento.consulta.observacoesMedicas}" />


											<div class="divSeparador2">
												<div class="row">
													<div class="col-xs-6">
														<label for="field_6">Observações Médicas</label>
													</div>
													<div class="col-xs-12">
														<form:textarea type="text" cssClass="form-control"
															value="${agendamento.consulta.observacoesMedicas}"
															id="inputRg" path="consulta.observacoesMedicas" />
													</div>
												</div>

											</div>
										</form:form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Não</button>
										<button type="button" class="btn btn-primary abrirJanela"
											onclick="submeterComJanela(formFecharAtendimento${agendamento.id},modalFecharAtendimento${agendamento.id} )">Sim</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
					</c:if></td>



				<td><c:if
						test="${agendamento.statusAgendamentoEnum.descricao != txEnumCancelado
					&& agendamento.statusAgendamentoEnum.descricao != txEnumConfirmado && agendamento.statusAgendamentoEnum.descricao != txEnumAtendido}">
						<form:form action="${raiz}consulta/cancelar" method="POST"
							modelAttribute="agendamento" class="formulario_acoes"
							id="formCancelamento${agendamento.id}">
							<form:hidden path="id" value="${agendamento.id}" />
							<a href="#" data-toggle="modal"
								data-target="#modalCancelamento${agendamento.id}"
								title="Cancelar Consulta"> <span
								class="glyphicon glyphicon-remove"></span>
							</a>
						</form:form>
					</c:if>
					<div class="modal fade" id="modalCancelamento${agendamento.id}">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">Cancelamento</h4>
								</div>
								<div class="modal-body">
									<p>Deseja CANCELAR a consulta de
										${agendamento.nomePaciente} ?</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Não</button>
									<button type="button" class="btn btn-primary abrirJanela"
										onclick="submeterComJanela(formCancelamento${agendamento.id},modalCancelamento${agendamento.id} )">Sim</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div> <!-- /.modal --> </a></td>

				<td><c:if
						test="${agendamento.statusAgendamentoEnum.descricao == txEnumAtendido}">
						<form:form action="${raiz}consulta/emitir-nota" method="POST"
							modelAttribute="agendamento" class="formulario_acoes"
							target="_blank" id="formEmissao${agendamento.id}">
							<form:hidden path="id" value="${agendamento.id}" />
							<a href="#" data-toggle="modal"
								data-target="#modalEmissao${agendamento.id}" title="Emitir Nota">
								<span class="glyphicon glyphicon-file"></span>
							</a>
						</form:form>
					</c:if>
					<div class="modal fade" id="modalEmissao${agendamento.id}">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">Emitir Nota</h4>
								</div>
								<div class="modal-body">
									<p>Deseja EMITIR NOTA do paciente
										${agendamento.nomePaciente} ?</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Não</button>
									<button type="button" class="btn btn-primary"
										onclick="submeterComJanela(formEmissao${agendamento.id},modalEmissao${agendamento.id})">Sim</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div> <!-- /.modal --> </a></td>



			</tr>
		</c:forEach>
	</tbody>
</table>
