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
				action="${raiz}supervisor/salvar" id="id-form" method="POST"
				modelAttribute="supervisor">
				<fieldset class="content-group">


					<legend class="text-bold">Incluir Supervisor</legend>

					<div class="row">
						<div class="col-xs-1">
							<label for="inputNome">Nome*</label>
						</div>
						<div class="col-xs-5" style="margin-left: -20px">
							<form:input required="true" type="text" class="form-control" id="inputNome"
								placeholder="Digite o nome" path="nome"
								cssErrorClass="field_error form-control" />
							<form:errors path="nome" cssClass="text-danger" class="has-error" />
						</div>
					</div>
					<br />

					<div class="row">
						<div class="col-xs-1">
							<label for="inputNome">CPF*</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input required="true" type="text" class="form-control" id="inputCpf"
								placeholder="Digite o CPF" path="cpf"
								cssErrorClass="field_error form-control" />
							<form:errors path="cpf" cssClass="text-danger" class="has-error" />
						</div>
					</div>
					<br />

					<div class="row">
						<div class="col-xs-1">
							<label for="inputNome">Telefone</label>
						</div>
						<div class="col-xs-2" style="margin-left: -20px">
							<form:input type="text" class="form-control" id="inputTelefone"
								placeholder="(DDD) 9999-9999" path="telefone"
								cssErrorClass="field_error form-control" />
							<form:errors path="telefone" cssClass="text-danger"
								class="has-error" />
						</div>
					</div>
					<br />


					<div class="text-right">
						<a href="${raiz}supervisores" class=" btn bg-danger abrirJanela">Cancelar</a>

						<button type="submit" class="btn btn-primary">
							Salvar <i class="icon-arrow-right14 position-right"></i>
						</button>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>
