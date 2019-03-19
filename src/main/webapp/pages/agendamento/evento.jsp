<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>




<c:url value="/" var="raiz" />

	<div class="container">

		<div class="row">

			<div class="col-xs-1">
				<label for="inputNome">Nome*</label>
			</div>

			<div class="col-xs-8">
				<input type="text" class="form-control" id="inputNome"
					placeholder="Digite o Nome Completo" path="evento.nome"
					cssErrorClass="field_error form-control" />
			</div>

		</div>
		
		
			<div class="row">
				<button class="btn btn-success" 
					id="">Salvar</button>
			</div>
		
	</div>






<script type="text/javascript"
	src="<c:url value="/resources/js/agendamento/agendamento.js" />"></script>