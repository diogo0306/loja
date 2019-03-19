<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
	<div class="page-header">
	<img src="../resources/images/logoPrincipal.png" style="width: 10%;" /><br />
		<div class="btn-group btn-breadcrumb"
			style="text-align: center; width: 100%;">
			<div>


				<h4
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Cardioclínica Dr. Almir Barbosa</h4>
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Relatório Faturamento do dia ${dataFormatada}</h5>
			</div>
		</div>
	</div>
	<table class="table table-striped table-hover table-bordered">
		<thead>
			<tr>
				<th width="2%">Nome</th>
				<th width="2%">CPF Pagador</th>
				<th width="10%">Valor</th>
			</tr>
		</thead>
		<tbody style="font-size: 12px;">
		<c:set var="valorTotal" value="${0}"/>
		<c:forEach var="itemRelatorio" items="${consultas}">
			<tr>
				<td><c:out value="${itemRelatorio.paciente.nome}" /></td>
				<td><c:out value="${itemRelatorio.cpfPagador}" /></td>
				<td><c:out value="${itemRelatorio.valorConsulta} R$" /></td>
				<c:set var="valorTotal" value="${itemRelatorio.valorConsulta + valorTotal}"/>
			</tr>
		</c:forEach>
		</tbody>
			<tr>
				<th width="2%"></th>
				<th width="2%">Total:</th>
				<th width="10%">${valorTotal} R$</th>
			</tr>
	</table>
</div>