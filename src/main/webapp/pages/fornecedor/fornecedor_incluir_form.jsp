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
								class="text-semibold">Gestão</span> - Fornecedores
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}fornecedor">Fornecedores</a></li>
						<li class="active">Incluir Fornecedor</li>

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
							action="${raiz}fornecedor/salvar" method="POST"
							modelAttribute="fornecedor">
							<fieldset class="content-group">
								<legend class="text-bold">Fornecedor - Incluir</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Nome</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-10">
											<form:input type="text" class="form-control" id="inputNome"
												placeholder="Digite o nome" path="nome" required="true"
												cssErrorClass="field_error form-control" />
											<form:errors path="nome" cssClass="text-danger"
												class="has-error" />
										</div>

									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Valor Cobrado
										Material (R$)</label>
									<div class="col-lg-4">
										<div class="input-group col-xs-6">
											<form:input type="text" class="form-control js-decimal"
												id="inputNome" placeholder="Digite o valor"
												path="valorTransiente" required="true"
												cssErrorClass="field_error form-control" />
											<form:errors path="valorTransiente" cssClass="text-danger"
												class="has-error" />
										</div>
									</div>
								</div>

								<div class="text-right">
									<a href="${raiz}hospitais" class=" btn bg-danger abrirJanela">Cancelar</a>

									<button type="submit" class="btn btn-primary">
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
	src="<c:url value="/resources/js/fornecedor/fornecedor.js" />"></script>
