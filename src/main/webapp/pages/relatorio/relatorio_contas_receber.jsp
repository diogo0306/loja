<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Relatórios</span> - Relatório - Contas a Receber
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Gestão</li>
						<li><a href="${raiz}relatorio/contas-receber">Relatório -
								Contas a Receber</a></li>

					</ul>
				</div>
			</div>

			<div class="content">
				<c:if test="${messageError != null}">
					<div class="row">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>${messageError}</div>
					</div>
				</c:if>		

				<div class="panel panel-flat">

					<div class="panel-heading">
						<h5 class="panel-title">Consultar Relatório</h5>
						<div class="heading-elements">
							<ul class="icons-list">
								<li><a data-action="collapse"></a></li>
								<li><a data-action="reload"></a></li>
							</ul>
						</div>
						<a class="heading-elements-toggle"><i class="icon-menu"></i></a>
					</div>

					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}relatorio/contas-receber"
							modelAttribute="contasDTO">

							<div class="form-group">
								<div class="col-xs-1">
									<label for="field_1">Período:</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										id="inputDataInicial" placeholder="Digite a Data"
										path="dataInicial" />
								</div>
								<div class="col-xs-1">
									<label for="field_1">Até:</label>
								</div>
								<div class="col-xs-2">
									<form:input type="text" class="form-control"
										id="inputDataFinal" placeholder="Digite a Data"
										path="dataFinal" />
								</div>
							</div>

							<div class="row">
								<div class="col-xs-offset-1 col-xs-6">
									<button type="submit" formtarget="_blank"
										class="btn btn-success abrirJanela">Gerar Relatório</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/relatorio/relatorio.js" />"></script>
