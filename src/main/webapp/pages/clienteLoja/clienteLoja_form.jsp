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
				action="${raiz}clienteLoja/salvar" id="id-form" method="POST"
				modelAttribute="clienteLoja">
				<fieldset class="content-group">
					<legend class="text-bold">Cadastrar Cliente</legend>

					<div class="form-group">
						<label class="control-label col-lg-2">Nome</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-12">

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
						<label class="control-label col-lg-2">CPF</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-5">
								<form:input type="text" class="form-control" required="true"
									id="inputCpf" placeholder="Digite o cpf" path="cpf"
									cssErrorClass="field_error form-control" />
								<form:errors path="cpf" cssClass="text-danger" class="has-error" />
							</div>
						</div>

					</div>

					<div class="form-group">
						<label class="control-label col-lg-2">Celular</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-5">
								<form:input type="text" class="form-control" required="true"
									id="inputCelular" placeholder="Digite o celular" path="celular"
									cssErrorClass="field_error form-control" />
								<form:errors path="celular" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>

					</div>

					<div class="form-group">
						<label class="control-label col-lg-2">E-mail</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-12">
								<form:input type="text" class="form-control" required="true"
									id="#" placeholder="Digite o e-mail" path="email"
									cssErrorClass="field_error form-control" />
								<form:errors path="email" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>

					</div>

					<div class="text-right">
						<a href="${raiz}produtos" class=" btn bg-danger abrirJanela">Cancelar</a>

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
	src="<c:url value="/resources/js/clienteLoja/clienteLoja.js" />"></script>
