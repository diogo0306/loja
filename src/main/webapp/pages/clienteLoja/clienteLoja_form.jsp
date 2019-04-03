<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<div class="content">
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

		<div class="panel-body">
			<form:form class="form-horizontal" role="form"
				action="${raiz}clienteLoja/salvar" id="id-form" method="POST"
				modelAttribute="clienteLoja">
				<fieldset class="content-group">
					<legend class="text-bold">Dashboard do Cliente</legend>



					<div class="text-right">
						<a href="${raiz}clientesLoja" class=" btn bg-danger abrirJanela">Cancelar</a>

						<button type="submit" class="btn btn-primary">
							Salvar <i class="icon-arrow-right14 position-right"></i>
						</button>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/js/clienteLoja/clienteLoja.js" />"></script>
