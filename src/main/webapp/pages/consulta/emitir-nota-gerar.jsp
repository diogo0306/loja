<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
	<div class="page-header">
		<img src="../resources/images/clinicaFacilLogo.png"
			style="width: 21%;" /><br />
		<div class="btn-group btn-breadcrumb"
			style="text-align: center; width: 100%;">
			<div>
				<h4
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Recibo</h4>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Cardioclínica Dr. Almir Barbosa - Av. Dr. Pedro Jordão - 95 - (81)
					3722 - 6522</h5>
				<br />
			</div>
			<div
				style="text-align: left; border-width: 2px; border-style: solid; padding: 5px;">
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Data: ${dataEmissao}</h5>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Paciente: ${consulta.agendamento.paciente.nome}</h5>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					CPF: ${consulta.agendamento.paciente.cpf}</h5>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Observações: ${consulta.observacao}</h5>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Valor:R$ ${consulta.valorConsulta}</h5>
				<br />
			</div>
		</div>
	</div>
	<table class="table table-striped table-hover table-bordered">
		<thead>
		</thead>
		<tbody style="font-size: 12px;">
		</tbody>
	</table>
</div>