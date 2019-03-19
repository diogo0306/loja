<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<div class="page-container">
	<div class="page-content">
		<div class="content-wrapper">
			<!-- Page header -->
			<div class="page-header">

				<div class="page-header-content">
					<div class="page-title">
						<h4>
							<i class="icon-arrow-left52 position-left"></i> <span
								class="text-semibold">Gestão</span> - Exames/Procedimentos -
							Incluir
						</h4>
					</div>
				</div>

				<div class="breadcrumb-line">
					<ul class="breadcrumb">
						<li class="active"><i class="icon-home2 position-left"></i><a
							href="${raiz}"> Home</a></li>
						<li>Gestão</li>
						<li><a href="${raiz}exames">Exames/Procedimentos</a></li>
						<li class="active">Incluir</li>

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
							action="${raiz}exame/salvar" id="formularioExame" method="POST"
							modelAttribute="exame">
							<fieldset class="content-group">
								<legend class="text-bold">Incluir Exames/Procedimentos</legend>



								<div class="form-group">
									<div class="col-xs-1">
										<label for="field_1">Nome: </label>
									</div>
									<div class="col-xs-4">
										<form:input type="text" class="form-control" id="inputNome"
											placeholder="Digite o nome do exame/procedimento"
											required="true" path="nome" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-1">
										<label for="field_1">Código: </label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputCodigo"
											placeholder="Digite o código" required="true" path="codigo" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-1">
										<label for="field_1">Valor: </label>
									</div>
									<div class="col-xs-2">
										<form:input type="text" class="form-control" id="inputValor"
											placeholder="R$" required="true" path="valorTransiente" />
									</div>
								</div>
								<div class="form-group">
									<div class="form-group">
										<div class="col-xs-1">
											<label for="inputDescricao">Descrição</label>
										</div>
										<div class="col-xs-6">
											<form:textarea maxlength="254" rows="5" cols="30"
												class="form-control" id="inputTurno"
												placeholder="Até 254 caracteres" path="descricao"
												cssErrorClass="field_error form-control" />
										</div>
									</div>
								</div>

								<div class="text-right">
									<a href="${raiz}exames" class=" btn bg-danger abrirJanela">Cancelar</a>

									<button type="submit" class="btn btn-primary"
										id="btn-salvar_exame">
										Salvar <i class="icon-arrow-right14 position-right"></i>
									</button>
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
	src="<c:url value="/resources/js/exame/exame.js" />"></script>
