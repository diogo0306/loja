<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

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
				action="${raiz}indice/salvar" id="id-form" method="POST"
				modelAttribute="indice">
				<fieldset class="content-group">
					<legend class="text-bold">Incluir Índice</legend>

					<div class="form-group">
						<label class="control-label col-lg-2">Nome</label>
						<div class="col-lg-10">
							<div class="input-group col-xs-8">
								<form:input type="text" class="form-control" id="inputNome"
									required="true" placeholder="Digite o nome" path="nome"
									cssErrorClass="field_error form-control" />
								<form:errors path="nome" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-2">Mês/ano</label>
						<div class="col-lg-10">
							<div class="input-group col-xs-2">
								<form:input type="text" class="form-control"
									id="inputCompetencia" required="true" placeholder="Mês/Ano"
									path="competencia" cssErrorClass="field_error form-control" />
								<form:errors path="competencia" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>


					<div class="form-group">
						<label class="control-label col-lg-2">Percentual %</label>
						<div class="col-lg-10">
							<div class="input-group col-xs-2">
								<form:input type="text" class="form-control js-decimal"
									id="inputPercentual" required="true"
									placeholder="Digite o percentual" path="percentual"
									cssErrorClass="field_error form-control" />
								<form:errors path="percentual" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>
					</div>


					<div class="text-right">
						<a href="${raiz}indices" class=" btn bg-danger abrirJanela">Cancelar</a>

						<button type="submit" class="btn btn-primary">
							Salvar <i class="icon-arrow-right14 position-right"></i>
						</button>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/indice/indice.js" />"></script>
