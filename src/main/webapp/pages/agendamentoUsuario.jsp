<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript"
	src="resources/js/plugins/ui/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript"
	src="resources/js/plugins/ui/fullcalendar/lang/pt-br.js"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/agendamento/agendamento_medico.js" />"></script>

<c:url value="/" var="raiz" />

<input name="dataAtual" id="dataAtual" type="hidden" value="${dataAtual}" >

<div class="content">
	<div class="panel panel-flat">
		<div class="panel-body">
			<fieldset class="content-group">
				<legend class="text-bold">AGENDAMENTO ON-LINE</legend>
			</fieldset>

			<div class="row" style="margin-top: -30px; margin-bottom: 14px;">
				<div class="col-xs-2">
					<label for="field_3">Selecione o Profissional*</label>
				</div>
				<div class="col-xs-5">
					<form:select path="medico.id" class="form-control" id="selectMedico"
						cssErrorClass="field_error form-control">
						<form:option value="" label="Selecione" />
						<form:options items="${medicos}" itemLabel="nome" itemValue="id" />
					</form:select>
				</div>
			</div>
			<div class="col-md-12">
				<div class="fullcalendar-external" ></div>
			</div>

		</div>


	</div>

</div>

