
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
								class="text-semibold">Gestão</span> - Profissional - Visualizar
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}medicos">Profissionais</a></li>
						<li class="active">Visualizar</li>

					</ul>
				</div>
			</div>

			<form:form class="form-horizontal" role="form"
				action="${raiz}especialidade/salvar" id="formularioEspecialidade"
				method="POST" modelAttribute="especialidade" object="especialidade">

				<form:input path="id" type="hidden" />

				<div class="page-container">
					<div class="page-content">
						<div class="content-wrapper">

							<!-- Page header -->
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

										<fieldset class="content-group">
											<div class="row">
												<div class="col-xs-1">
													<label for="inputNome">Especialidade</label>
												</div>
												<div class="col-xs-5">
													<form:input disabled="true" type="text"
														class="form-control" id="inputNome"
														placeholder="Digite a especialidade" path="especialidade"
														cssErrorClass="field_error form-control" />
												</div>
											</div>
											<div class="text-right">
												<a href="${raiz}especialidades" class=" btn bg-danger abrirJanela">Voltar</a>
											</div>
										</fieldset>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
