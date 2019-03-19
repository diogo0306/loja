<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url var="urlForm" value="/selecao-cliente" />

<!-- Page container -->
<div class="page-container login-container">
	<!-- Page content -->
	<div class="page-content">
		<!-- Main content -->
		<div class="content-wrapper">
			<!-- Content area -->
			<div class="content">
				<!-- Advanced login -->
				<form:form method="POST" role="form" action="${urlForm}"
					class="form-horizontal" modelAttribute="usuario" id="validate-form">
					<div class="panel panel-body login-form">
						<div class="text-center">
							<div class="icon-object border-slate-300 text-slate-300">
								<i class="icon-reading"></i>
							</div>
							<h5 class="content-group">
								Unidade <small class="display-block">Selecione a unidade que deseja administrar</small>
							</h5>
						</div>

						<div class="form-group has-feedback has-feedback-left">
							<form:select id="cliente" class="form-control" path="cliente.id">
								<c:forEach var="cliente" items="${clientes}">
									<option value="${cliente.id}">${cliente.id}-
										${cliente.nome}</option>
								</c:forEach>
							</form:select>
							<div class="form-control-feedback">
								<i class="icon-user text-muted"></i>
							</div>
						</div>

						<div class="form-group">
							<button type="submit" class="btn bg-blue btn-block">
								Avançar <i class="icon-arrow-right14 position-right"></i>
							</button>
						</div>
					</div>
				</form:form>
				<!-- /advanced login -->

				<!-- Footer -->
				<div class="footer text-muted">
					&copy; 2018. <a href="#">Eclinic</a> by <a
						href="http://www.comeia.org" target="_blank">Comeia</a>
				</div>
				<!-- /footer -->
			</div>
			<!-- /content area -->
		</div>
		<!-- /main content -->
	</div>
	<!-- /page content -->
</div>
<!-- /page container -->
