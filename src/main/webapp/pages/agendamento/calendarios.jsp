<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header">
				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Agendamento</span> - Calendário
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Agendamento</li>
						<li class="active">Calendário</li>

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
				<c:if test="${message != null}">
					<div class="row">
						<div class="alert alert-info">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>${message}</div>
					</div>
				</c:if>

				<div class="panel panel-flat">
					<div class="panel-heading">
						<div class="heading-elements">
							<ul class="icons-list">
								<li><a data-action="collapse"></a></li>
								<li><a data-action="reload"></a></li>
							</ul>
						</div>
						<a class="heading-elements-toggle"><i class="icon-menu"></i></a>
					</div>

					<div style="margin-top: 10px" align="center">
						<img src="resources/images/avatar_M.png" width="200px"
							height="200px">
					</div>

					<div class="panel-body">
						<form:form method="POST" class="form-horizontal" role="form"
							action="${raiz}calendarios" modelAttribute="agendamentoDTO">
							<fieldset class="content-group">

								<div class="row">

									<br />
									<div class="col-xs-2">
										<label>Nome do Paciente*</label>
									</div>
									<div class="col-md-3">
										<form:input type="text" cssClass="form-control" id="inputNome"
											cssErrorClass="field_error form-control"
											placeholder="Digite o nome" path="nomePacienteSemCadastro" />
									</div>
									<div class="col-xs-2">
										<label>Selecione o dia</label>
									</div>
									<div class="col-md-3">
										<form:input type="date" cssClass="form-control" id="inputData"
											cssErrorClass="field_error form-control"
											path="dataConsultaFormatada" />
									</div>
								</div>
								<br />

								<div class="row">
									<div class="col-xs-2">
										<label>Telefone do Paciente</label>
									</div>
									<div class="col-md-3">
										<form:input type="text" cssClass="form-control"
											id="inputTelefone" cssErrorClass="field_error form-control"
											placeholder="Digite o Telefone" path="telefonePaciente" />
									</div>
									<div class="col-xs-2">
										<label for="field_3">Selecione o Horario:</label>
									</div>
									<div class="col-md-3">
										<form:select id="inputHorario" path="horaConsultaFormatada"
											style="display: none">
										</form:select>
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-xs-2">
										<label>CPF do Paciente</label>
									</div>
									<div class="col-md-3">
										<form:input type="text" cssClass="form-control" id="inputCpf"
											placeholder="Digite o CPF" path="cpfPacienteSemCadastro" />
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-xs-2">
										<label>RG do Paciente</label>
									</div>
									<div class="col-md-3">
										<form:input type="text" cssClass="form-control" id="inputRg"
											placeholder="Digite o RG" path="rgPacienteSemCadastro" />
									</div>
								</div>
								<br />

								<div class="row">
									<div class="col-xs-2">
										<label for="field_3">Selecione o Médico*</label>
									</div>
									<div class="col-md-3">
										<form:select path="medico.id" class="form-control"
											id="inputMedico" cssErrorClass="field_error form-control">
											<form:option value="" label="Selecione" />
											<form:options items="${medicos}" itemLabel="nome"
												itemValue="id" />
										</form:select>
									</div>
									<div class="text-right">
										<button type="submit" class="btn btn-primary">
											Salvar <i class="icon-arrow-right14 position-right"></i>
										</button>
									</div>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>


<script type="text/javascript"
	src="<c:url value="/resources/js/agendamento/calendarios.js" />"></script>
	
	