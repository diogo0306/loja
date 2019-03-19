<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
	<c:if test="${messageError != null}">
		<div class="row">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>${messageError}</div>
		</div>
	</c:if>

	<div class="page-header">
		<ol class="breadcrumb">
			<li class=""><a href="">Home</a></li>
			<li class=""><a href="">Tabelas</a></li>
			<li class="active"><a href="">Alterar Tabela</a></li>
		</ol>
	</div>


	<form:form class="form-horizontal" role="form"
		action="${raiz}tabela/alterar" id="id-form" method="POST"
		modelAttribute="exameDTO">

		<div class="panel panel-default">


			<div class="panel-heading">
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					<i class="fa fa-signal"></i> &nbsp;&nbsp; Dados da Tabela
				</h5>
			</div>

			<c:url value="/" var="raiz" />

			<div class="divSeparador2">
				<c:if test="${campos_obrigatorios != null}">
					<div class="row">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>${campos_obrigatorios}</div>
					</div>
				</c:if>

				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="tabela.nome" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="inputNome">Nome*</label>
					</div>
					<div class="col-xs-6">
						<form:input type="text" class="form-control" id="inputNome"
							readonly="true" disabled="true" placeholder="Digite o nome" path="tabela.nome"
							cssErrorClass="field_error form-control" />
							<form:input path="tabela.nome" type="hidden" />
							<form:input path="tabela.id" type="hidden" />
					</div>
				</div>
				<br />

				<div class="row">
					<div class="col-xs-1">
						<label for="inputDescricao">Descrição</label>
					</div>
					<div class="col-xs-6">
						<form:textarea maxlength="254" rows="5" cols="30"
							class="form-control" id="inputTurno"
							placeholder="Até 254 caracteres" path="tabela.descricao"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-offset-1 col-xs-12" style="margin-left: 0%">
				<button class="btn btn-success abrirJanela" style="width: 100%;">Alterar</button>
				&nbsp; <a href="${raiz}tabelas" class="btn btn-danger abrirJanela"
					style="width: 100%;">Voltar</a>
			</div>
		</div>

	</form:form>
</div>


<script type="text/javascript"
	src="<c:url value="/resources/js/exame/tabela.js" />"></script>
