<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<form:input path="id" type="hidden" />
<div class="panel panel-default">
	<div class="panel-heading">
		<h5
				style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
				<i class="fa fa-sitemap"></i> &nbsp;&nbsp; Perfis - Dados do Perfil
			</h5>
	</div>
	<div class="divSeparador2">
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="descricao" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">Descrição*</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="descricao"
					placeholder="Digite a descricao" path="descricao"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
	</div>


</div>

<div class="row">
	<div class="col-xs-offset-1 col-xs-12" style="margin-left: 0%">
		<c:if test="${alterarTela == null}">
			<button class="btn btn-success abrirJanela" style="width: 100%;">Salvar</button>
		</c:if>
		<c:if test="${alterarTela != null}">
			<button class="btn btn-success abrirJanela" style="width: 100%;">Alterar</button>&nbsp;
			<a href="${raiz}perfis" class="btn btn-danger abrirJanela" style="width: 100%;">Voltar</a>
	</c:if>
	</div>
	<br />
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/usuario/usuario.js" />"></script>
