
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />


<form:input path="id" type="hidden" />

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

						<fieldset class="content-group">
							<div class="row">
								<div class="col-xs-1">
									<label for="inputNome">Especialidade</label>
								</div>
								<div class="col-xs-5">
									<form:input required="true" type="text" class="form-control"
										id="inputNome" placeholder="Digite a especialidade"
										path="especialidade" cssErrorClass="field_error form-control" />
									<form:errors path="especialidade" cssClass="text-danger"
										class="has-error" />
								</div>
							</div>
							<br> <br> <a href="${raiz}especialidades"
								class=" btn bg-danger abrirJanela">Cancelar</a>

							<button type="submit" class="btn btn-primary">
								Salvar <i class="icon-arrow-right14 position-right"></i>
							</button>

						</fieldset>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

