<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@page contentType="text/html;charset=UTF-8"%>


<c:url value="/" var="raiz" />

<form:input path="id" type="hidden" />

<form:input path="perfil.id" type="hidden" value="${perfil.id}" />

<c:if test="${erroAdicionarPerfil != null}">
	<div class="row">
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>${erroAdicionarPerfil}</div>
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h6 class="panel-title">Perfil - ${perfil.descricao}</h6>
	</div>
	<div class="divSeparador2">
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="*" cssClass="text-danger" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-2">
				<label for="inputNome">Funcionalidades</label>
			</div>
			<div class="col-xs-4">
				<form:select id="funcionalidades" class="form-control"
					path="funcionalidade.id">
					<option value="${null}">Selecione</option>
					<c:forEach var="funcionalidade" items="${funcionalidades}">
						<option value="${funcionalidade.id}">
							${funcionalidade.id} - ${funcionalidade.nome}</option>
					</c:forEach>
				</form:select>
			</div>
			<div class="col-xs-4">
				<div class="form-group">
					<label class="col-lg-2 control-label">&nbsp;</label>
					<div class="col-lg-6" style="width: 100% !important;">
						<form:checkbox id="inserir" path="inserir" label="Inserir" />
						<form:checkbox id="alterar" path="alterar" label="Alterar" />
						<form:checkbox id="excluir" path="excluir" label="Excluir" />
						<form:checkbox id="visualizar" path="visualizar" label="Visualizar" />
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<a href="#" id="btn-add-perfil" class="btn btn-primary">Adicionar</a>
		</div>
	</div>

	<br /> <br />

</div>
<div class="panel panel-default" style="height: 200px !important;">
	<div class="panel-heading">
		<h6 class="panel-title">Lista de Funcionalidades / Permissões</h6>
	</div>
	<div id="divList"
		style="overflow: auto !important; height: 162px !important;">
		<tiles:insertAttribute name="list" />
	</div>

</div>


<div class="row">
	<div class="col-xs-offset-1 col-xs-12" style="margin-left: 0%">
		<c:if test="${alterar != null}">
			<a href="${raiz}perfil/salvar"" class="btn btn-success abrirJanela" style="width: 100%;">Finalizar Cadastro</a>
		</c:if>
		<c:if test="${alterar == null}">
			<a href="${raiz}perfis" class="btn btn-danger abrirJanela" style="width: 100%;">Voltar</a>
	</c:if>
	</div>
	<br />
</div>