<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
	<div class="page-header">
	<img src="../resources/images/clinicaFacilLogo.png" style="width: 21%;" /><br />
		<div class="btn-group btn-breadcrumb"
			style="text-align: center; width: 100%;">
			<div>


				<h4
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Cardioclínica Dr. Almir Barbosa</h4><br/>
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Relatório Faturamento Mensal - ${mesConsulta} de ${anoConsulta}</h5><br/>
			</div>
		</div>
	</div>
	<table class="table table-striped table-hover table-bordered">
		<thead>
			<tr>
				<th width="10%">Dia</th>
				<th width="20%">Quantidade Consultas</th>
				<th width="70%">Valor</th>
				
			</tr>
		</thead>
		<tbody style="font-size: 12px;">
		<c:set var="valorTotal" value="${0}"/>
		<c:set var="quantidadeConsultasTotal" value="${0}"/>
			<c:forEach var="itemRelatorio" items="${relatorio}">
				<tr>
					<td><c:out value="Dia ${itemRelatorio.dia}" /></td>
					<td><c:out value="${itemRelatorio.quantidadeConsultas} consulta(s)" /></td>
					<td><c:out value="R$${itemRelatorio.valor} " /></td>					
					<c:set var="valorTotal" value="${itemRelatorio.valor + valorTotal}"/>
					<c:set var="quantidadeConsultasTotal" value="${itemRelatorio.quantidadeConsultas + quantidadeConsultasTotal}"/>
				</tr>
			</c:forEach>
		</tbody>
			<tr>
				<th width="2%">Total:</th>
				<th width="10%">${quantidadeConsultasTotal} consulta(s)</th>
				<th width="10%">R$ ${valorTotal} </th>
			</tr>
	</table>
</div>