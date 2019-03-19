
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

				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Tabela - Detalhar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}faixas">Tabela</a></li>
						<li class="active">Detalhar</li>

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
							action="${raiz}tabelaFaixa/visualizar" id="formularioTabelaFaixa"
							method="POST" modelAttribute="tabelaFaixa">
							<fieldset class="content-group">
								<legend class="text-bold">Tabela - Detalhar</legend>


								<div class="form-group">
									<label class="control-label col-lg-2">Nome</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-8">
											<form:input type="text" disabled="true" class="form-control" id="inputNome"
												placeholder="Digite o nome" path="nome"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

							</fieldset>

							<fieldset class="content-group">
								<legend class="text-bold">Faixas Etárias</legend>

								<table class="table table-hover table-striped table-bordered "
									id="tabelaExames">
									<thead>
										<tr>
											<th width="10%">Tipo</th>
											<th width="10%">Idade Inicial</th>
											<th width="10%">Idade Final</th>
											<th width="5%">Variação</th>											
										</tr>
									</thead>
									<tbody id="listaItens">
										<c:forEach var="faixa" items="${tabelaFaixa.faixas}"
											varStatus="status">
											<tr>
												<td><c:out
														value="${faixa.tipoBeneficiarioEnum.descricao}" /></td>
												<td><c:out value="${faixa.idadeMinima}" /></td>
												<td><c:out value="${faixa.idadeMaxima}" /></td>
												<td><c:out value="${faixa.variacao}" /></td>
												
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
								<a href="${raiz}tabelaFaixas" class=" btn bg-danger abrirJanela">Voltar</a>							
							</div>
						</form:form>
					</div>
				</div>
			</div>

		</div>
	</div>

</div>
