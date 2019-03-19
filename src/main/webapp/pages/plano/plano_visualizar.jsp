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
								class="text-semibold">Gestão</span> - Planos - Visualizar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}planos">Planos</a></li>
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
							action="${raiz}plano/salvar" id="id-form" method="POST"
							modelAttribute="plano">
							<fieldset class="content-group">
								<legend class="text-bold">Visualizar Plano</legend>

								<form:input path="id" type="hidden" />

								<div class="form-group">
									<label class="control-label col-lg-2">Nome do Plano</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-10">
											<form:input type="text"
												class="touchspin-set-value form-control" disabled="true"
												path="nome" style="display: block;"></form:input>

										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Tipo do Plano</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-6">
											<form:input type="text"
												class="touchspin-set-value form-control" disabled="true"
												path="tipoPlanoSaudeEnum" style="display: block;"></form:input>

										</div>
									</div>

								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Acomodação</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-6">
											<form:input type="text"
												class="touchspin-set-value form-control" disabled="true"
												path="acomodacaoPlanoSaudeEnum" style="display: block;"></form:input>
										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="control-label col-lg-2">Data de Cadastro</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control" disabled="true"
												id="inputDataCadastro" placeholder="Digite o data"
												path="dataCadastro" cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Valor (R$)</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control" disabled="true"
												id="inputValorPlano" placeholder="Digite o Valor"
												path="valorPlano" cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 17%; margin-top: 6px;">
										<form:checkbox id="pagamentoBalcao" disabled="true"
											path="pagamentoParticipacaoBalcao" name="pagamentoBalcao" />
									</div>

									<label for="pagamentoBalcao" class="col-sm-4 control-label"
										style="text-align: left;">Pagamento co-participação no
										Balcão</label>

								</div>
								<div class="form-group">
									<div class="col-sm-1"
										style="width: 1%; margin-left: 17%; margin-top: 6px;">
										<form:checkbox id="aplicarMudancaFaixa" disabled="true"
											path="aplicarMudancaFaixa" name="aplicarMudancaFaixa"
											label="${aplicarMudancaFaixa}" value="${aplicarMudancaFaixa}" />
									</div>
									<label for="aplicarMudancaFaixa" class="col-sm-5 control-label"
										style="text-align: left;">Aplicar mudança de faixa
										etária no mês posterior ao aniviversário</label>


								</div>

							</fieldset>
							<fieldset class="content-group">
								<%-- <legend class="text-bold">Idade máxima para dependentes</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Masculino</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-2">

											<form:input type="number" disabled="true"
												class="touchspin-set-value form-control"
												path="idadeMaximaDependenteMasculino"
												style="display: block;"></form:input>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Feminino</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-2">

											<form:input type="number" disabled="true"
												class="touchspin-set-value form-control"
												path="idadeMaximaDependenteFeminino" style="display: block;"></form:input>
										</div>
									</div>
								</div> --%>

							</fieldset>
							<%-- <fieldset class="content-group">
								<legend class="text-bold">Tabela - Resumo</legend>

								<br />
								<table
									class="table table-striped table-bordered table-hover datatable-highlight "
									id="tabelaExames">
									<thead>
										<tr>
											<th width="10%">Tipo</th>
											<th width="10%">Idade Inicial</th>
											<th width="10%">Idade Final</th>
											<th width="10%">Vida Inicial</th>
											<th width="10%">Vida Final</th>
											<th width="10%">Valor</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="tabela" items="${plano.tabelaFaixa}"
											varStatus="status">
											<tr>
												<td><c:out
														value="${tabela.tipoBeneficiarioEnum.descricao}" /></td>
												<td><c:out value="${tabela.idadeInicial}" /></td>
												<td><c:out value="${tabela.idadeFinal}" /></td>
												<td><c:out value="${tabela.vidaInicial}" /></td>
												<td><c:out value="${tabela.vidaFinal}" /></td>
												<td><c:out value="${tabela.valor}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</fieldset> --%>
							<br />
							<div class="text-right">
								<a href="${raiz}planos" class=" btn bg-danger abrirJanela">Voltar</a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
