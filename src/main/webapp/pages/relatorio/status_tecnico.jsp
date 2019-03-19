<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<input type="hidden" value="${raiz}status_tecnico/tecnicos"
	id="url-tecnicos" />

<!-- Begin page content -->
<div class="container">
	<div class="page-header">
		<h3>Relatório de Status por Técnico</h3>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h6 class="panel-title">Período</h6>
		</div>

		<div class="divSeparador2">

			<form:form method="POST" class="form-horizontal" role="form" action="${raiz}status_tecnico/gerar" modelAttribute="otdRelatorio">
				
				<c:if test="${messageError != null}">
					<div class="row">
						<div class="text-danger col-xs-offset-1">${messageError}</div>
					</div>
				</c:if>
				<br />
				<div class="row">
					<div class="col-xs-1">
						<label for="field-3">Prestadora de Serviço</label>
					</div>
					<div class="col-xs-4">
						<form:select path="idPrestadora" id="field-3"
							cssClass="form-control" cssErrorClass="field_error form-control">
							<form:option value="0">Selecione uma prestadora de serviço</form:option>
							<form:options items="${prestadoras}" itemLabel="nome"
								itemValue="id" />
						</form:select>
					</div>
				</div>
				<br />
		
				<div id="divTecnicos">
					<tiles:insertAttribute name="tecnicos" />
				</div>
		
		
		
				<div class="row">
					<div class="col-xs-1">
						<label for="field-1">Data Início</label>
					</div>
					<div class="col-xs-2">
						<form:input type="text" class="form-control" id="field-1"
							path="dataInicioStr" />
					</div>
					<div class="col-xs-1">
						<label for="field-2">Data Fim</label>
					</div>
					<div class="col-xs-2">
						<form:input type="text" class="form-control" id="field-2"
							path="dataFimStr" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<button type="submit" class="btn btn-success">Gerar PDF</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/relatorio/status_tecnico.js" />"></script>