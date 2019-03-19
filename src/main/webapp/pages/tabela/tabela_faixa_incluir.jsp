
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}tabela_faixa/salvar" id="url-salvar" />

<!-- Begin page content -->
<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">

				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Tabela - Incluir Faixa
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}faixas">Tabela</a></li>
						<li class="active">Incluir Faixa</li>

					</ul>
				</div>
			</div>

			<div class="content">

				<c:if test="${messageError != null}">
					<div class="row">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>${messageError}</div>
					</div>
				</c:if>


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
							action="${raiz}tabelaFaixa/salvar" id="formularioTabelaFaixa"
							method="POST" modelAttribute="tabelaFaixaDTO">
							<fieldset class="content-group">
								<legend class="text-bold">Tabela - Incluir Faixa</legend>


								<div class="form-group">
									<label class="control-label col-lg-2">Nome*</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control" id="inputNome"
												placeholder="Digite o nome" path="tabelaFaixa.nome"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="control-label col-lg-2">Tipo de
										Beneficiário*</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-8">
											<form:select id="selectTipoPlano"
												path="tipoBeneficiarioTransiente" class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${beneficiarios}" itemLabel="descricao"
													itemValue="codigo" />
											</form:select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Idade Inicial</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-2">

											<form:input type="number"
												class="touchspin-set-value form-control"
												path="faixaEtaria.idadeMinima" style="display: block;"></form:input>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Idade Final</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-2">

											<form:input type="number"
												class="touchspin-set-value form-control"
												path="faixaEtaria.idadeMaxima" style="display: block;"></form:input>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Variação (%)</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-2">

											<form:input id="inputVariacao" type="text"
												class="form-control" placeholder="Percentual"
												path="variacaoTransiente"></form:input>
										</div>
									</div>
								</div>

								<div class="text-right">
									<button class="btn btn-success" style="width: 20%;">Adicionar</button>
								</div>
								<br />

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Resumo</legend>

								<table class="table table-hover table-striped table-bordered "
									id="tabelaExames">
									<thead>
										<tr>
											<th width="10%">Tipo</th>
											<th width="10%">Idade Inicial</th>
											<th width="10%">Idade Final</th>
											<th width="5%">Variação</th>
											<th width="5%">Ações</th>
										</tr>
									</thead>
									<tbody id="listaItens">
										<c:forEach var="faixa" items="${tabelaFaixaDTO.faixas}"
											varStatus="status">
											<tr>
												<td><c:out
														value="${faixa.tipoBeneficiarioEnum.descricao}" /></td>
												<td><c:out value="${faixa.idadeMinima}" /></td>
												<td><c:out value="${faixa.idadeMaxima}" /></td>
												<td><c:out value="${faixa.variacao}" /></td>
												<td><a class="glyphicon glyphicon-remove"
													id="btn-excluir_exame_lista" onclick="exluirLista($(this))"></a></td>

												<form:input
													path="faixas[${status.index}].tipoBeneficiarioEnum"
													type="hidden" />
												<form:input path="faixas[${status.index}].idadeMinima"
													type="hidden" />
												<form:input path="faixas[${status.index}].idadeMaxima"
													type="hidden" />
												<form:input path="faixas[${status.index}].variacao"
													type="hidden" />
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</fieldset>

							<div class="text-right">
								<a href="${raiz}tabelaFaixas" class=" btn bg-danger abrirJanela">Cancelar</a>

								<button type="submit" class="btn btn-primary"
									id="btn-salvar_faixa">
									Salvar <i class="icon-arrow-right14 position-right"></i>
								</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>

		</div>
	</div>

</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/tabela/tabela_faixa.js" />"></script>
