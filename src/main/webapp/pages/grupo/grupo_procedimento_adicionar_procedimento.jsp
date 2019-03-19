<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />
<input type="hidden"
	value="${raiz}grupoProcedimento/procedimento/salvar" id="url-salvar" />

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
								class="text-semibold">Gestão</span> - Grupos/Procedimentos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}grupos">Grupos/Procedimentos</a></li>
						<li class="active">Adicionar Procedimento</li>

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
							action="${raiz}grupoProcedimento/procedimento/adicionar"
							id="formularioGrupoProcedimentoSalvar" method="POST"
							modelAttribute="grupoProcedimentoDTO">
							<fieldset class="content-group">
								<legend class="text-bold">Grupos - Adicionar
									Procedimento</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Grupo:</label>
									<div class="col-lg-6">
										<div class="input-group col-lg-12">
											<form:input type="text" class="form-control" disabled="true"
												path="grupoProcedimento.nome" />
											<form:input path="grupoProcedimento.id" type="hidden" />
											<form:input path="grupoProcedimento.nome" type="hidden" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Procedimento*</label>
									<div class="col-lg-6">
										<div class="input-group col-lg-12">
											<form:select id="selectProcedimento" path="procedimento.id"
												required="true" class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${procedimentos}" itemLabel="nome"
													itemValue="id" />
												<form:input path="procedimento.id" type="hidden" />
												<form:input path="procedimento.nome" type="hidden" />
											</form:select>
										</div>
									</div>
								</div>

								<br />

								<div class="text-right">
									<button type="submit" class="btn btn-primary">
										Adicionar <i class="icon-arrow-right14 position-right"></i>
									</button>
								</div>
							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Resumo</legend>

								<table class="table table-hover table-striped table-bordered "
									id="tabelaExames">
									<thead>
										<tr>
											<th width="5%">Codigo</th>
											<th width="30%">Nome</th>
											<th width="10%">Valor Pagamento</th>
											<th width="10%">Valor Cobrança</th>
											<th width="5%">Ações</th>
										</tr>
									</thead>
									<tbody id="listaItens">
										<c:forEach var="procedimento"
											items="${grupoProcedimentoDTO.listaProcedimentos}"
											varStatus="status">
											<tr>
												<td><c:out value="${procedimento.codigo}" /></td>
												<td><c:out value="${procedimento.nome}" /></td>
												<td><c:out value="R$ ${procedimento.valorPagamento} " /></td>
												<td><c:out value="R$ ${procedimento.valorCobranca}" /></td>

												<td><a class="glyphicon glyphicon-remove"
													id="btn-excluir_procedimento"
													onclick="exluirLista($(this))"></a></td>

												<form:input path="listaProcedimentos[${status.index}].codigo"
													type="hidden" />
												<form:input
													path="listaProcedimentos[${status.index}].nome"
													type="hidden" />
												<form:input
													path="listaProcedimentos[${status.index}].valorPagamento"
													type="hidden" />
												<form:input
													path="listaProcedimentos[${status.index}].valorCobranca"
													type="hidden" />
												<form:input path="listaProcedimentos[${status.index}].id"
													type="hidden" />
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</fieldset>

							<div class="text-right">
								<a href="${raiz}grupos" class=" btn bg-danger abrirJanela">Voltar</a>

								<button type="submit" class="btn btn-primary"
									id="btn-salvar_procedimento">
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
	src="<c:url value="/resources/js/grupo/grupo.js" />"></script>