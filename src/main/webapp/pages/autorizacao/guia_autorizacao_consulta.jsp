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

					<ul class="breadcrumb-elements">
						<li
							style="font-weight: bold; font-size: 1.7rem; margin-right: 60px">AUTORIZAÇÃO
							Nº: ${autorizacao.numeroAutorizacao}</li>

						<p></p>
						<li style="font-weight: bold; font-size: 1.7rem;">DATA:
							${autorizacao.dataFormatada}</li>
					</ul>
				</div>

				<h3
					style="text-align: center; font-weight: bold; font-size: 1.7rem;">
					GUIA DE AUTORIZAÇÃO
					<P>CONSULTA</P>
				</h3>

				<h3
					style="text-align: left; font-weight: bold; font-size: 1.4rem; margin-left: 90px">BENEFICIÁRIO:
					${autorizacao.paciente.nome}</h3>
				<h3
					style="text-align: left; font-weight: bold; font-size: 1.4rem; margin-left: 90px;">SOLICITANTE:
					${autorizacao.nomeSolicitante}</h3>
				<h3
					style="text-align: left; font-weight: bold; font-size: 1.4rem; margin-left: 90px">EXECUTANTE:
					${autorizacao.credenciado.nome}</h3>

				<div class="container">
					<br>
					<table class="table table-bordered">
						<h2
							style="text-align: center; font-weight: bold; font-size: 1.7rem;">PROCEDIMENTOS
							AUTORIZADOS</h2>
						<thead>
							<tr>
								<th style="font-weight: bold; font-size: 1.4rem;">CÓGIGO</th>
								<th style="font-weight: bold; font-size: 1.4rem;">DESCRIÇÃO</th>
								<th style="font-weight: bold; font-size: 1.4rem;">QTDE</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>000000001</td>
								<td>CONSULTA</td>
								<td>01</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div
					style="margin-top: 60px; margin-left: 90px; margin-bottom: 20px; border: 0; border-top: 1px solid #000; width: 20%; text-align: left;">
					<p style="margin-left: 80px; font-weight: bold; font-size: 1.3rem;">Data
						de execução</p>
				</div>

				<div
					style="margin-top: -40px; margin-left: 900px; margin-bottom: 20px; border: 0; border-top: 1px solid #000; width: 20%; text-align: right;">
					<p
						style="margin-right: 60px; font-weight: bold; font-size: 1.3rem;">Assinatura
						do executante</p>
				</div>

				<div
					style="margin-top: 40px; margin-left: 900px; margin-bottom: 20px; border: 0; border-top: 1px solid #000; width: 20%; text-align: right;">
					<p
						style="margin-right: 60px; font-weight: bold; font-size: 1.3rem;">Assinatura
						do usuário</p>
				</div>
				
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				

			</div>
			<!-- /page header -->
		</div>
		<!-- /main content -->
	</div>
	<!-- /page content -->
</div>
<!-- /page container -->


