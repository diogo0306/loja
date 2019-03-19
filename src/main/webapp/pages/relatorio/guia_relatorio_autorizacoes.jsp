<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!-- Begin page content -->
<!-- Page container -->
<div class="container">
	<!-- Page content -->
	<div class="page-content">
		<!-- Main content -->
		<div class="content-wrapper">
			<!-- Page header -->
			<div class="page-header">
				<br>
				<div class="breadcrumb-line">

					<img alt="" src="../resources/images/SG-NOVO-LOGO.png"
						style="width: 140px; margin-left: 90px; margin-top: -16px;">
				</div>

				<h3
					style="text-align: center; font-weight: bold; font-size: 1.7rem;">
					RELATÓRIO DE AUTORIZAÇÕES</h3>

				<div class="container">
					<br>
					<table class="table table-bordered">
						<h2
							style="text-align: center; font-weight: bold; font-size: 1.7rem;">AUTORIZAÇÕES
							REALIZADAS</h2>
						<thead>
							<tr>
								<th style="font-weight: bold; font-size: 1.4rem;">CÓGIGO</th>
								<th style="font-weight: bold; font-size: 1.4rem;">TIPO</th>
								<th style="font-weight: bold; font-size: 1.4rem;">DATA/HORA DE EXECUÇÃO</th>
								<th style="font-weight: bold; font-size: 1.4rem;">CREDENCIADO</th>
								<th style="font-weight: bold; font-size: 1.4rem;">VALOR</th>
								<th style="font-weight: bold; font-size: 1.4rem;">STATUS</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="auto" items="${autorizacoes}">
								<tr>
									<td><c:out value="${auto.numeroAutorizacao}" /></td>
									<td><c:out value="${auto.tipo.descricao}" /></td>
									<td><c:out value="${auto.dataExecucaoFormatada}" /></td>
									<td><c:out value="${auto.credenciado.nome}" /></td>
									<td><c:out value="${auto.valorPagoFormatado}" /></td>
									<td><c:out value="${auto.status.descricao}" /></td>												
								</tr>
							</c:forEach>	
						</tbody>
					</table>
				</div>
			</div>
			<!-- /page header -->
		</div>
		<!-- /main content -->
	</div>
	<!-- /page content -->
</div>
<!-- /page container -->


