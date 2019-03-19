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
					Relatório Cadastro de Pacientes - ${dataInicial} a ${dataFinal}</h5><br/>
			</div>
		</div>
	</div>
	<table class="table table-striped table-hover table-bordered">
		<thead>
			<tr>
				<th width="2%">Registro</th>
				<th width="10%">Data Cad.</th>
				<th width="24%">Nome</th>
				<th width="9%">CPF</th>
				<th width="9%">RG</th>
				<th width="14%">F. Mãe</th>
				<th width="9%">Data Nasc.</th>
				<th width="14%">Endereço</th>
				<th width="14%">Bairro</th>
				<th width="14%">Cidade</th>
				<th width="14%">Telefone</th>
			</tr>
		</thead>
		<tbody style="font-size: 12px;">
			<c:forEach var="paciente" items="${pacientes}">
				<tr>
					<td><c:out value="${paciente.codigoPacienteLegado}" /></td>
					<td><c:out value="${paciente.dataCadastroFormatada}" /></td>
					<td><c:out value="${paciente.nome}" /></td>
					<td><c:out value="${paciente.cpf}" /></td>
					<td><c:out value="${paciente.rg}" /></td>
					<td><c:out value="${paciente.filiacaoMae}" /></td>
					<td><c:out value="${paciente.dataNascimentoFormatada}" /></td>
					<td><c:out value="${paciente.endereco}" /></td>
					<td><c:out value="${paciente.bairro}" /></td>
					<td><c:out value="${paciente.cidade}" /></td>
					<td><c:out value="${paciente.telefone}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/paciente/paciente.js" />"></script>
