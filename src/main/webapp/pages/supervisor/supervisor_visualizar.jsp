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
								class="text-semibold">Gestão</span> - Supervisor - Visualizar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}supervisores">Supervisores</a></li>
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
							action="${raiz}supervisores/salvar" id="id-form" method="POST"
							modelAttribute="supervisor">
							<fieldset class="content-group">
								<legend class="text-bold">Visualizar Supervisores</legend>

								<div class="form-group">
									<label class="control-label col-lg-2">Nome</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-8">
											<form:input type="text" class="form-control" id="inputNome"
												placeholder="Digite o nome" path="nome" disabled="true"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">CPF</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-8">
											<form:input type="text"
												class="touchspin-set-value form-control" disabled="true"
												path="cpf" style="display: block;"></form:input>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2">Telefone</label>
									<div class="col-lg-10">
										<div class="input-group col-xs-2">
											<form:input type="text"
												class="touchspin-set-value form-control" disabled="true"
												placeholder="(DDD) 9999-9999" path="telefone"
												style="display: block;"></form:input>
										</div>
									</div>
								</div>

								<div class="text-right">
									<a href="${raiz}supervisores"
										class=" btn bg-danger abrirJanela">Voltar</a>
								</div>

							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>