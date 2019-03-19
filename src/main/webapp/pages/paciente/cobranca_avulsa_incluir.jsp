<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}salvar/cobranca_avulsa"
	id="url-salvar" />

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
								class="text-semibold">Gestão</span> - Beneficiários - Incluir
							Cobrança
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Beneficiários</li>
						<li><a href="${raiz}solicitacoes">Solicitações</a></li>
						<li class="active">Detalhar</li>

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
							action="${raiz}paciente/adicionar/cobranca_avulsa"
							id="formularioIncluirCobrancaAvulsa" method="POST"
							modelAttribute="pacienteDTO">

							<fieldset class="content-group">
								<legend class="text-bold">Dados do Beneficiário</legend>

								<div class="form-group">
									<label for="inputPcmso" class="col-lg-1 control-label">Nome</label>
									<div class="col-lg-4">
										<form:input path="paciente.nome" class="form-control"
											readonly="true" disabled="true" id="inputEmpresa"
											cssErrorClass="field_error form-control" />
										<form:input path="paciente.nome" type="hidden" />
										<form:input path="paciente.id" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">CPF</label>
									<div class="col-lg-2">
										<form:input path="paciente.documentacao.cpf"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="paciente.documentacao.cpf" type="hidden" />
									</div>
									<label for="inputPcmso" class="col-lg-1 control-label">Telefone</label>
									<div class="col-lg-2">
										<form:input path="paciente.documentacao.telefone"
											class="form-control" readonly="true" disabled="true"
											id="inputEmpresa" cssErrorClass="field_error form-control" />
										<form:input path="paciente.documentacao.telefone"
											type="hidden" />
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Incluir Cobrança</legend>

								<div class="form-group">

									<div class="col-xs-1">
										<label for="inputNome">Descrição</label>
									</div>
									<div class="col-xs-4">
										<form:input type="text" class="form-control" id="inputNome"
											required="true" placeholder="Digite a Descrição da Cobrança"
											path="cobrancaAvulsa.nome"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label for="inputDataNascimento2">Vencimento</label>
									</div>
									<div class="col-xs-2">
										<form:input type="date" class="form-control" id=""
											placeholder="Digite a Data"
											path="cobrancaAvulsa.dataFormatada"
											cssErrorClass="field_error form-control" />
									</div>

									<div class="col-xs-1">
										<label>Valor</label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control"
											id="inputValorCobranca" placeholder="Digite o valor"
											path="cobrancaAvulsa.valorTransiente"
											cssErrorClass="field_error form-control" />
									</div>
								</div>

								<div class="text-right">
									<button class="btn btn-success" style="width: 20%;">Adicionar</button>
								</div>


							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Lista de Cobranças</legend>

								<div class="form-group">
									<table class="table table-hover table-striped table-bordered "
										id="tabelaCobranca">
										<thead>
											<tr>
												<th width="40%">Descrição</th>

												<th width="10%">Data</th>
												<th width="10%">Valor(R$)</th>
												<th width="10%">Ações</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="cobrancaAvulsa"
												items="${pacienteDTO.listaCobrancaAvulsa}"
												varStatus="status">
												<tr>
													<td><c:out value="${cobrancaAvulsa.nome}" /></td>
													<td><c:out value="${cobrancaAvulsa.dataFormatada}" /></td>
													<td><c:out value="${cobrancaAvulsa.valorTransiente}" /></td>

													<td><a href="#"
														class="fas fa-times-circle removerLinha">Excluir
															Cobrança</a></td>
													<form:input
														path="listaCobrancaAvulsa[${status.index}].nome"
														type="hidden" />

													<form:input
														path="listaCobrancaAvulsa[${status.index}].dataFormatada"
														type="hidden" />
													<form:input
														path="listaCobrancaAvulsa[${status.index}].valorTransiente"
														type="hidden" />
													<form:input
														path="listaCobrancaAvulsa[${status.index}].valor"
														type="hidden" />
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>

								<div class="text-right">
									<a href="${raiz}pacientes" class=" btn bg-danger abrirJanela">Voltar</a>

									<button type="submit" class="btn btn-primary"
										id="btn-salvar-cobranca">
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
	src="<c:url value="/resources/js/paciente/cobranca_avulsa.js" />"></script>

