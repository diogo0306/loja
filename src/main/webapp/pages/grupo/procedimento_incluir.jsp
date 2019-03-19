<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<c:if test="${messageError != null}">
				<div class="row">
					<div class="alert alert-danger">

						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>${messageError}</div>
				</div>
			</c:if>
			<c:if test="${message != null}">
				<div class="row">
					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>${message}</div>
				</div>
			</c:if>

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
						<li class="active">Incluir Procedimento</li>

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
							action="${raiz}procedimento/salvar" id="formularioProcedimento"
							method="POST" modelAttribute="procedimento" object="procedimento">

							<div class="panel-body">
								<fieldset class="content-group">
									<legend class="text-bold">Procedimento</legend>

									<div class="form-group">
										<label class="control-label col-lg-2">Nome</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-10">
												<form:input type="text" class="form-control" id="inputNome"
													required="true" placeholder="Digite o nome" path="nome"
													cssErrorClass="field_error form-control" />
												<form:errors path="nome" cssClass="text-danger"
													class="has-error" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-2">Código</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-6">
												<form:input type="number" class="form-control"
													id="inputNome" required="true" placeholder="Digite o nome"
													path="codigo" cssErrorClass="field_error form-control" />
												<form:errors path="codigo" cssClass="text-danger"
													class="has-error" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-lg-2">Valor
											Pagamento (R$)</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-6">
												<form:input type="text" class="form-control js-decimal"
													id="inputNome" required="true" placeholder="Digite o nome"
													path="valorPagamento"
													cssErrorClass="field_error form-control" />
												<form:errors path="valorPagamento" cssClass="text-danger"
													class="has-error" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-2">Valor
											Cobrança (R$)</label>
										<div class="col-lg-4">
											<div class="input-group col-xs-6">
												<form:input type="text" class="form-control js-decimal"
													id="inputNome" required="true" placeholder="Digite o nome"
													path="valorCobranca"
													cssErrorClass="field_error form-control" />
												<form:errors path="valorCobranca" cssClass="text-danger"
													class="has-error" />
											</div>
										</div>
									</div>
									<!-- <div class="form-group">
									<label class="control-label col-lg-2">Procedimento*</label>
									<div class="col-xs-2">
										<div class="input-group col-xs-8">
											<form:input type="number" class="form-control" id="inputNome"
												path="procedimentoInicial"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<label class="control-label col-xs-1">a </label>
									<div class="col-xs-2">
										<div class="input-group col-xs-8">
											<form:input type="number" class="form-control" id="inputNome"
												path="procedimentoFinal"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Quantidade de CH*</label>
									<div class="col-xs-2">
										<div class="input-group col-xs-8">
											<form:input type="number" class="form-control"
												path="quantidadeChInicial"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
									<label class="control-label col-xs-1">a </label>
									<div class="col-xs-2">
										<div class="input-group col-xs-8">
											<form:input type="number" class="form-control"
												path="quantidadeChFinal"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>  -->

									<div class="text-right">
										<a href="${raiz}grupos" class=" btn bg-danger abrirJanela">Cancelar</a>

										<button type="submit" class="btn btn-primary">
											Salvar <i class="icon-arrow-right14 position-right"></i>
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
	src="<c:url value="/resources/js/procedimento/procedimento.js" />"></script>
