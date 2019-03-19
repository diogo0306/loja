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
								class="text-semibold">Gestão</span> - Planos - Alterar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}planos">Planos</a></li>
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
							action="${raiz}plano/alterar" id="id-form" method="POST"
							modelAttribute="plano">
							<form:input path="id" type="hidden" />



							<div class="panel-body">

								<fieldset class="content-group">
									<legend class="text-bold">Incluir Plano</legend>

									<div class="form-group">
										<label class="control-label col-lg-2">Nome do Plano</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-10">
												<form:input type="text" class="form-control" id="inputNome"
													placeholder="Digite o nome" path="nome"
													cssErrorClass="field_error form-control" />
												<form:errors path="nome" cssClass="text-danger"
													class="has-error" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-lg-2">Tipo do Plano</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-6">
												<form:select id="#"
													path="codigoTipoPlanoTransiente"
													class="form-control"
													cssErrorClass="field_error form-control">
													<form:option value="" label="Selecione" />
													<form:options items="${tiposPlano}"
														itemLabel="descricao" itemValue="codigo" />
												</form:select>
												<form:errors path="tipoPlanoSaudeEnum"
													cssClass="text-danger" class="has-error" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-lg-2">Data de Cadastro</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-6">
												<form:input type="text" class="form-control" required="true"
													id="inputDataCadastro" placeholder="Digite o data"
													path="dataCadastro"
													cssErrorClass="field_error form-control" />
												<form:errors path="dataCadastro" cssClass="text-danger"
													class="has-error" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-lg-2">Valor (R$)</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-6">
												<form:input type="text" class="form-control" required="true"
													id="inputValorPlano" placeholder="Digite o Valor"
													path="valorPlanoTransiente"
													cssErrorClass="field_error form-control" />
												<form:errors path="valorPlanoTransiente"
													cssClass="text-danger" class="has-error" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-1"
											style="width: 1%; margin-left: 17%; margin-top: 6px;">
											<form:checkbox id="pagamentoBalcao"
												path="pagamentoParticipacaoBalcao" name="pagamentoBalcao" />
										</div>

										<label for="pagamentoBalcao" class="col-sm-4 control-label"
											style="text-align: left;">Pagamento co-participação
											no Balcão</label>

									</div>
									<div class="form-group">
										<div class="col-sm-1"
											style="width: 1%; margin-left: 17%; margin-top: 6px;">
											<form:checkbox id="aplicarMudancaFaixa"
												path="aplicarMudancaFaixa" name="aplicarMudancaFaixa"
												label="${aplicarMudancaFaixa}"
												value="${aplicarMudancaFaixa}" />
										</div>
										<label for="aplicarMudancaFaixa"
											class="col-sm-5 control-label" style="text-align: left;">Aplicar
											mudança de faixa etária no mês posterior ao aniviversário</label>


									</div>
								</fieldset>
								<fieldset class="content-group">
									<div class="text-right">
										<a href="${raiz}planos" class=" btn bg-danger abrirJanela">Cancelar</a>

										<button type="submit" class="btn btn-primary">
											Alterar <i class="icon-arrow-right14 position-right"></i>
										</button>
									</div>
								</fieldset>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/plano/plano.js" />"></script>
