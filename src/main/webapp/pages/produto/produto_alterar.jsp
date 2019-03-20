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
								class="text-semibold">Gestão</span> - Produto - Alterar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}produtos">Produtos</a></li>
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
							action="${raiz}produto/alterar" id="id-form" method="POST"
							modelAttribute="produto">

							<form:input path="id" type="hidden" />


							<fieldset class="content-group">
								<legend class="text-bold">Alterar Produto</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Nome</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-10">

											<form:input type="text" class="form-control" required="true"
												id="#" placeholder="Digite o nome" path="nome"
												cssErrorClass="field_error form-control" />
											<form:errors path="nome" cssClass="text-danger"
												class="has-error" />

										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Código</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control" required="true"
												id="#" placeholder="Digite o código" path="codigo"
												cssErrorClass="field_error form-control" />
											<form:errors path="codigo" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Tipo de Produto</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:select id="#" required="true"
												path="codigoTipoProdutoTransiente" class="form-control"
												cssErrorClass="field_error form-control">
												<form:option value="" label="Selecione" />
												<form:options items="${tipos}" itemLabel="descricao"
													itemValue="codigo" />
											</form:select>
											<form:errors path="codigoTipoProdutoTransiente"
												cssClass="text-danger" class="has-error" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Preço de Custo</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control" required="true"
												id="inputPrecoCusto" placeholder="Digite preço"
												path="precoCustoTransiente"
												cssErrorClass="field_error form-control" />
											<form:errors path="precoCustoTransiente"
												cssClass="text-danger" class="has-error" />
										</div>
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Preço de Venda</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control" required="true"
												id="inputPrecoVenda" placeholder="Digite preço"
												path="precoVendaTransiente"
												cssErrorClass="field_error form-control" />
											<form:errors path="precoVendaTransiente"
												cssClass="text-danger" class="has-error" />
										</div>
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Quantidade (Und)</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="number" class="form-control"
												required="true" id="#" placeholder="Digite a quantidade"
												path="estoque" cssErrorClass="field_error form-control" />
											<form:errors path="estoque" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>

								</div>


								<div class="text-right">
									<a href="${raiz}produtos" class=" btn bg-danger abrirJanela">Cancelar</a>

									<button type="submit" class="btn btn-primary">
										Alterar <i class="icon-arrow-right14 position-right"></i>
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
	src="<c:url value="/resources/js/produto/produto.js" />"></script>