<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
	<div class="page-header">
		<div class="btn-group btn-breadcrumb"
			style="text-align: center; width: 100%;">
			<div>
				<h4
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Comprovante de Pagamento</h4>
				<br />
			</div>
			<div
				style="text-align: left; border-width: 2px; border-style: solid; padding: 5px;">
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Data de Pagamento: ${dataPagamentoFormatada}</h5>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Beneficiário: ${beneficiarioExame.paciente.nome}</h5>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					CPF: ${beneficiarioExame.paciente.documentacao.cpf}</h5>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Código: ${beneficiarioExame.codigo}</h5>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Credenciado: ${beneficiarioExame.credenciado.nome}</h5>
				<br />
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					Valor: ${beneficiarioExame.valorTotalFormatado}</h5>
				<br />
			</div>
			<div
				style="text-align: left; border-width: 2px; border-style: solid; padding: 5px;">
				<table class="table table-striped table-hover table-bordered"
					style="font-weight: bold !important">
					<thead style="font-weight: bold !important">
						<tr style="font-weight: bold !important">
							<th width="10%">Exame</th>
							<th width="10%">Valor</th>
						</tr>
					</thead>
					<tbody style="font-size: 12px; font-weight: bold !important;">
						<c:forEach var="beneficiario"
							items="${beneficiarioExame.listaTabelaExame}">
							<tr style="font-weight: bold !important">
								<td><c:out value="${beneficiario.exame.nome}" /></td>
								<td><c:out value="${beneficiario.valorTransiente}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br />
			</div>
		</div>
	</div>
</div>