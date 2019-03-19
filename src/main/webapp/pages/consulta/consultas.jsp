<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<script type="text/javascript"
	src="<c:url value="/resources/js/pages/dashboard.js" />"></script>

<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Consultas</span> - Agendamentos
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li class="active">Consultas</li>
						<li><a href="${raiz}consultas"> Agendamentos</a></li>

					</ul>
				</div>
			</div>


			<div class="content">

				<div class="row">
					<div class="col-md-16">
						<div class="col-lg-3">
							<!-- Members online -->
							<div class="panel bg-teal-400">
								<div class="panel-body">
									<h3 class="no-margin">${quantidadeAgendamentos}</h3>
									Agendamentos
								</div>
								<div class="container-fluid">
									<div id="members-online"></div>
								</div>
							</div>
							<!-- /members online -->
						</div>

						<div class="col-lg-3">
							<!-- Current server load -->
							<div class="panel bg-pink-400">
								<div class="panel-body">
									<h3 class="no-margin">${quantidadeConfirmados}</h3>
									Confirmados
								</div>
								<div id="server-load"></div>
							</div>
							<!-- /current server load -->
						</div>

						<div class="col-lg-3">

							<!-- Today's revenue -->
							<div class="panel bg-blue-400">
								<div class="panel-body">
									<h3 class="no-margin">${quantidadeAtendidos}</h3>
									Atendidos
								</div>
								<div id="today-revenue"></div>
							</div>
							<!-- /today's revenue -->

						</div>

						<div class="col-lg-3">
							<!-- Members online -->
							<div class="panel bg-teal-400">
								<div class="panel-body">
									<h3 class="no-margin">${quantidadeAguardando}</h3>
									Aguardando
								</div>
								<div class="container-fluid">
									<div id="members-online"></div>
								</div>
							</div>
							<!-- /members online -->
						</div>

					</div>
				</div>



				<div class="ui-eClinic panel panel-default">
					<div class="ui-eClinic panel-heading">
						<div class="row">
							<div class="col-lg-3 col-md-3">
								<h5>
									<b>Consultas em ${dataConsulta}</b>
								</h5>
							</div>
							<div class="col-lg-3 col-md-3">
								<form:form method="POST" class="form-horizontal" role="form"
									style="margin-top: 4px;" action="${raiz}consultas"
									modelAttribute="agendamento">

								&nbsp; <a href="${raiz}consulta/pacientes"
										class="btn btn-success abrirJanela">Nova Consulta</a>

								</form:form>

							</div>
							<div class="col-lg-3 col-md-3">
								<form:form method="POST" class="form-horizontal" role="form"
									style="margin-top: 4px;" action="${raiz}consultas"
									modelAttribute="agendamento">


									<c:forEach var="agendamento" items="${agendamentosFunc}">
										<a class="abrirJanela" href="${raiz}${agendamento.caminho}"><i
											class="btn btn-success abrirJanela"></i>&nbsp;Novo
											Agendamento</a>
									</c:forEach>

								</form:form>

							</div>

						</div>

					</div>
					<div class="ui-eClinic panel-body" style="padding: 0px;">

						<div id="divList" class="ui-eClinic tabelas">
							<tiles:insertAttribute name="list" />
						</div>

					</div>


					<div class="panel-footer" style="height: 50px; padding: 0px 15px;">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/agendamento/agendamento.js" />"></script>
