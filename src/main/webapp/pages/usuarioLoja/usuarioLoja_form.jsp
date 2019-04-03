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
				action="${raiz}usuarioLoja/salvar" id="id-form" method="POST"
				modelAttribute="usuarioLoja">
				<fieldset class="content-group">
					<legend class="text-bold">Cadastrar Usuário</legend>

					<div class="form-group">
						<label class="control-label col-lg-2">Nome de Usuário</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-12">

								<form:input type="text" class="form-control" required="true"
									id="#" placeholder="Digite o nome de usuário"
									path="nomeUsuario" cssErrorClass="field_error form-control" />
								<form:errors path="nomeUsuario" cssClass="text-danger"
									class="has-error" />

							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-2">Senha</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-6">
								<form:input type="text" class="form-control" required="true"
									id="#" placeholder="Digite a senha" path="senha"
									cssErrorClass="field_error form-control" />
								<form:errors path="senha" cssClass="text-danger"
									class="has-error" />
							</div>
						</div>

					</div>

					<div class="form-group">
						<label class="control-label col-lg-2">Tipo de Usuário</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-6">
								<form:select id="selectTipoUsuarioLoja" required="true"
									path="codigoTipoUsuarioLojaTransiente" class="form-control"
									cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${tipos}" itemLabel="descricao"
										itemValue="codigo" />
								</form:select>
								<form:errors path="codigoTipoUsuarioLojaTransiente"
									cssClass="text-danger" class="has-error" />
							</div>
						</div>

					</div>

					<div class="text-right">
						<a href="${raiz}usuariosLoja" class=" btn bg-danger abrirJanela">Cancelar</a>

						<button type="submit" class="btn btn-primary">
							Salvar <i class="icon-arrow-right14 position-right"></i>
						</button>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>

