<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/" var="raiz" />

<!-- Begin page content -->
<div class="container">
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

	<div class="page-header">
		<div class="btn-group btn-breadcrumb">
			<a href="#" class="btn btn-default"><i
				class="glyphicon glyphicon-home"></i></a> <a href="#"
				class="btn btn-default">Pcmso - Novo</a>
		</div>
	</div>

	<form:form class="form-horizontal" role="form"
		action="${raiz}pcmso/salvar" id="id-form" method="POST"
		modelAttribute="pcmsoPpraDTO">


		<div class="panel panel-default">
			<div class="panel-heading">
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					<i class="fa fa-credit-card"></i> &nbsp;&nbsp; PCMSO
				</h5>
			</div>

			<div class="divSeparador2">

				<div class="form-group">
					<label for="inputEmpresa" class="col-lg-1 control-label">Empresa:</label>
					<div class="col-lg-3">
						<form:select path="cliente.id" class="form-control"
							id="inputEmpresa" cssErrorClass="field_error form-control">
							<form:option value="0" label="Selecione" />
							<form:options items="${empresas}" itemLabel="nome" itemValue="id" />
							<form:input path="cliente.id" type="hidden" />
							<form:input path="cliente.nome" type="hidden" />
						</form:select>
					</div>

					<label for="inputSetor" class="col-lg-1 control-label">Setor:
					</label>
					<div class="col-lg-3">
						<form:select path="setor.id" class="form-control" id="inputSetor"
							cssErrorClass="field_error form-control">
							<form:option value="0" label="Selecione" />
							<form:options items="${setores}" itemLabel="descricao"
								itemValue="id" />
							<form:input path="setor.id" type="hidden" />
							<form:input path="setor.descricao" type="hidden" />
						</form:select>
					</div>
					<label for="inputFuncao" class="col-lg-1 control-label">Função:
					</label>
					<div class="col-lg-3">
						<form:select path="cargoFuncao.id" class="form-control"
							id="inputCargo" cssErrorClass="field_error form-control">
							<form:option value="0" label="Selecione" />
							<form:options items="${cargos}" itemLabel="nome" itemValue="id" />
							<form:input path="cargoFuncao.id" type="hidden" />
							<form:input path="cargoFuncao.nome" type="hidden" />
						</form:select>
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="divSeparador2">
				<div class="form-group">
					<table class="table table-hover table-striped table-bordered ">
						<thead>
							<tr>
								<th width="10%">Grupo de Risco</th>
								<th width="10%">Risco</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h5
					style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
					<i class="fa fa-credit-card"></i> &nbsp;&nbsp; Tipo de Exame:
				</h5>
			</div>

			<div class="divSeparador2"
				style="max-height: 400px; overflow: scroll; overflow-x: hidden;">

				<div class="form-group">
					<div class="col-lg-1" style="width: 3%; margin-left: 3%;">
						<input type="checkbox"></input>
					</div>
					<label for="inputFisico" class="col-lg-2 control-label"
						style="text-align: left;">Admissional:</label>
					<div class="col-lg-3">
						<select path="riscoFisico.id" class="form-control"
							id="inputRiscosFisicos" cssErrorClass="field_error form-control">
							<option value="" label="Selecione" />
						</select>
					</div>
					&nbsp; <a href="" class="btn btn-primary abrirJanela">Adicionar</a>
				</div>
				<br />

				<div class="form-group">
					<div class="col-lg-1" style="width: 3%; margin-left: 3%;">
						<input type="checkbox"></input>
					</div>
					<label for="inputQuimico" class="col-lg-2 control-label"
						style="text-align: left;">Demissional:</label>
					<div class="col-lg-3">
						<select path="riscoQuimico.id" class="form-control"
							id="inputRiscosQuimicos" cssErrorClass="field_error form-control">
							<option value="" label="Selecione" />
						</select>
					</div>
					&nbsp; <a href="" class="btn btn-primary abrirJanela">Adicionar</a>
				</div>
				<br />

				<div class="form-group">
					<div class="col-lg-1" style="width: 3%; margin-left: 3%;">
						<input type="checkbox"></input>
					</div>
					<label for="inputBiologico" class="col-lg-2 control-label"
						style="text-align: left;">Mudança de Função:</label>
					<div class="col-lg-3">
						<select path="riscoBiologico.id" class="form-control"
							id="inputRiscosBiologicos"
							cssErrorClass="field_error form-control">
							<option value="" label="Selecione" />
						</select>
					</div>
					&nbsp; <a href="" class="btn btn-primary abrirJanela">Adicionar</a>
				</div>
				<br />

				<div class="form-group">
					<div class="col-lg-1" style="width: 3%; margin-left: 3%;">
						<input type="checkbox"></input>
					</div>
					<label for="inputErgonomico" class="col-lg-2 control-label"
						style="text-align: left;">Periódico:</label>
					<div class="col-lg-3">
						<select path="riscoErgonomico.id" class="form-control"
							id="inputRiscosErgonomicos"
							cssErrorClass="field_error form-control">
							<option value="" label="Selecione" />
						</select>
					</div>
					<div class="form-inline">
						&nbsp; <a href="" class="btn btn-primary abrirJanela">Adicionar</a>
						&nbsp; &nbsp; <label for="pwd">Periodicidade:</label> <select
							path="riscoErgonomico.id" class="form-control"
							id="inputRiscosErgonomicos"
							cssErrorClass="field_error form-control">
							<option value="" label="Selecione" />
						</select>
					</div>
				</div>
				<br />

				<div class="form-group">
					<div class="col-lg-1" style="width: 3%; margin-left: 3%;">
						<input type="checkbox"></input>
					</div>
					<label for="inputAcidental" class="col-lg-2 control-label"
						style="text-align: left;">Retorno ao Trabalho:</label>
					<div class="col-lg-3">
						<select path="riscoAcidental.id" class="form-control"
							id="inputRiscosAcidentais"
							cssErrorClass="field_error form-control">
							<option value="" label="Selecione" />
							<select>
					</div>
					&nbsp; <a href="" class="btn btn-primary abrirJanela">Adicionar</a>
				</div>
			</div>
		</div>

		<div class="form-inline">
			<button class="btn btn-success abrirJanela" style="width: 100%;">Adicionar</button>
		</div>
		<br />

		<div class="panel panel-default">
			<div class="divSeparador2">
				<div class="form-group">
					<table class="table table-hover table-striped table-bordered ">
						<thead>
							<tr>
								<th width="10%">Setor</th>
								<th width="10%">Função</th>
								<th width="10%">Grupo de Risco</th>
								<th width="10%">Risco</th>
								<th width="10%">Tipo</th>
								<th width="10%">Exame</th>
								<th width="10%">Periodicidade</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

		<div class="form-inline">
			<button class="btn btn-success" style="width: 100%;" id="">Salvar</button>
		</div>
		<br />

	</form:form>
</div>



<script type="text/javascript"
	src="<c:url value="/resources/js/pcmso/pcmso.js" />"></script>
