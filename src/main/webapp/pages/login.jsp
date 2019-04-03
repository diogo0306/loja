
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!-- Begin page content -->

<c:url var="urlForm" value="/login" />

<!-- Page container -->
<div class="page-container login-container">
	<!-- Page content -->
	<div class="page-content">
		<!-- Main content -->
		<div class="content-wrapper">
			<!-- Content area -->
			<div class="content">


		 <%-- 		<c:if test="${MENSAGEM_LOGIN_INVALIDO == null}">
					<div class="alert alert-info-diogo" style="text-align: center;">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						ATENÇÃO! Usuário e/ou senha inválido(s). Verifique se você digitou
						corretametamente os campos ou entre em contato com o administrador
					</div>
				</c:if> --%>
				<!-- Advanced login -->
				<form:form method="POST" role="form" action="${urlForm}"
					class="form-horizontal" modelAttribute="usuario" id="validate-form">
					<div class="panel panel-body login-form">
						<div class="text-center">
							<div class="icon-object border-slate-300 text-slate-300">
								<i class="icon-reading"></i>
							</div>
							<h5 class="content-group">
								Realize login <small class="display-block">Dados de
									acesso</small>
							</h5>
						</div>

						<div class="form-group has-feedback has-feedback-left">
							<form:input type="text" class="form-control input-lg"
								placeholder="Login" path="login"></form:input>
							<div class="form-control-feedback">
								<i class="icon-user text-muted"></i>
							</div>
						</div>

						<div class="form-group has-feedback has-feedback-left">
							<form:input type="password" class="form-control input-lg"
								placeholder="Senha" path="senha"></form:input>
							<div class="form-control-feedback">
								<i class="icon-lock2 text-muted"></i>
							</div>
						</div>

						<div class="form-group login-options">
							<div class="row">
								<div class="col-sm-6">
									<label class="checkbox-inline"> <input type="checkbox"
										class="styled" checked="checked"> Lembrar
									</label>
								</div>

								<!-- <div class="col-sm-6 text-right">
									<a href="login_password_recover.html">Esqueceu a senha?</a>
								</div> -->
							</div>
						</div>

						<div class="form-group">
							<button type="submit" class="btn bg-blue btn-block">
								Login <i class="icon-arrow-right14 position-right"></i>
							</button>
						</div>
					</div>
				</form:form>
				<!-- /advanced login -->

				<!-- Footer -->
				<div class="footer text-muted">
					&copy; 2018. <a href="#">Loja</a> by <a
						href="http://www.comeia.org" target="_blank">Diogo</a>
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