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
								class="text-semibold">Gestão</span> - Produtos - Visualizar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}produtos">Produtos</a></li>
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
							action="${raiz}produto/salvar" id="id-form" method="POST"
							modelAttribute="produto">
							<fieldset class="content-group">
								<legend class="text-bold">Visualizar Produto</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Nome</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-10">

											<form:input type="text" class="form-control" disabled="true"
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
											<form:input type="text" class="form-control" disabled="true"
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
											<form:input type="text" class="form-control"
												id="#" placeholder=""
												path="tipoProdutoEnum.descricao"
												cssErrorClass="field_error form-control" disabled="true" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Preço de Custo</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control" required="true" disabled="true"
												id="inputPrecoCusto" placeholder="Digite preço"
												path="precoCusto"
												cssErrorClass="field_error form-control" />
										</div>
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Preço de Venda</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="text" class="form-control" required="true"
												id="inputPrecoVenda" placeholder="Digite preço" disabled="true"
												path="precoVenda"
												cssErrorClass="field_error form-control" />
											<form:errors path="precoVenda"
												cssClass="text-danger" class="has-error" />
										</div>
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Quantidade (Und)</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-5">
											<form:input type="number" class="form-control" disabled="true"
												required="true" id="#" placeholder="Digite a quantidade"
												path="estoque" cssErrorClass="field_error form-control" />
											<form:errors path="estoque" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>

								</div>


								<div class="text-right">
									<a href="${raiz}produtos" class=" btn bg-danger abrirJanela">Voltar</a>
								</div>

							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
