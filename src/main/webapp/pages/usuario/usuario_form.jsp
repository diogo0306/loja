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
				action="${raiz}usuario/salvar" id="id-form" method="POST"
				modelAttribute="usuario">
				<fieldset class="content-group">
					<legend class="text-bold">Cadastrar Usuário - Dados
						Pessoais</legend>

					<div class="form-group">
						<label class="control-label col-lg-1">Nome</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-12">

								<form:input type="text" class="form-control" required="true"
									id="#" placeholder="Digite o nome" path="nome"
									cssErrorClass="field_error form-control" />
								<form:errors path="nome" cssClass="text-danger"
									class="has-error" />

							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-1">CPF</label>
						<div class="col-lg-2">
							<div class="input-group col-xs-12">

								<form:input type="text" class="form-control" required="true"
									id="cpfUsuario" placeholder="Digite o cpf" path="cpf"
									cssErrorClass="field_error form-control" />
								<form:errors path="cpf" cssClass="text-danger" class="has-error" />

							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-1">RG</label>
						<div class="col-lg-2">
							<div class="input-group col-xs-12">

								<form:input type="text" class="form-control" required="true"
									id="#" placeholder="Digite o rg" path="rg"
									cssErrorClass="field_error form-control" />
								<form:errors path="rg" cssClass="text-danger" class="has-error" />

							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-1">Telefone</label>
						<div class="col-lg-2">
							<div class="input-group col-xs-12">

								<form:input type="text" class="form-control" required="true"
									id="telefoneUsuario" placeholder="Digite o telefone"
									path="telefone" cssErrorClass="field_error form-control" />
								<form:errors path="telefone" cssClass="text-danger"
									class="has-error" />

							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-1">E-mail</label>
						<div class="col-lg-4">
							<div class="input-group col-xs-12">

								<form:input type="text" class="form-control" required="true"
									id="#" placeholder="Digite o e-mail" path="email"
									cssErrorClass="field_error form-control" />
								<form:errors path="email" cssClass="text-danger"
									class="has-error" />

							</div>
						</div>
					</div>

					<legend class="text-bold">Cadastrar Usuário - Dados de
						Acesso</legend>

					<div class="form-group">
						<label class="control-label col-lg-1">Tipo/Usuário</label>
						<div class="col-lg-2">
							<div class="input-group col-xs-12">
								<form:select id="#" required="true"
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

					<div class="form-group">
						<label class="control-label col-lg-1">Tipo/Usuário</label>
						<div class="col-lg-2">
							<div class="input-group col-xs-12">
								<form:select id="#" required="true"
									path="codigoTipoUsuarioTransiente" class="form-control"
									cssErrorClass="field_error form-control">
									<form:option value="" label="Selecione" />
									<form:options items="${tipo}" itemLabel="descricao"
										itemValue="codigo" />
								</form:select>
								<form:errors path="codigoTipoUsuarioTransiente"
									cssClass="text-danger" class="has-error" />
							</div>
						</div>

					</div>


					<div class="form-group">
						<label class="control-label col-lg-1">Login</label>
						<div class="col-lg-2">
							<div class="input-group col-xs-12">

								<form:input type="text" class="form-control" required="true"
									id="#" placeholder="Digite o login" path="login"
									cssErrorClass="field_error form-control" />
								<form:errors path="login" cssClass="text-danger"
									class="has-error" />

							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-lg-1">Senha</label>
						<div class="col-lg-2">
							<div class="input-group col-xs-12">

								<form:input type="text" class="form-control" required="true"
									id="#" placeholder="Digite a senha" path="senha"
									cssErrorClass="field_error form-control" />
								<form:errors path="senha" cssClass="text-danger"
									class="has-error" />

							</div>
						</div>
					</div>

					<div class="text-right">
						<a href="${raiz}usuarios" class=" btn bg-danger abrirJanela">Cancelar</a>

						<button type="submit" class="btn btn-primary">
							Salvar <i class="icon-arrow-right14 position-right"></i>
						</button>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>


<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<form:input path="id" type="hidden" />
<form:input path="alterarSenha" type="hidden" />
<div class="panel panel-default">
	<div class="panel-heading">
		<h5
			style="font-weight: bold !important; margin-bottom: 0px !important; margin-top: 0px !important;">
			<i class="fa fa-user"></i> &nbsp;&nbsp; Usuários - Dados do Usuário
		</h5>
	</div>
	<div class="divSeparador2">
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="nome" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">Nome*</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="nome"
					placeholder="Digite o Nome" path="nome"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="login" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">Login*</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="login"
					placeholder="Digite o login" path="login"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">Senha*</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="senha"
					placeholder="Digite a senha" path="senha"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="telefone" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">Telefone</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="telefoneUsuario"
					placeholder="Digite o telefone" path="telefone"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="cpf" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">CPF</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="cpfUsuario"
					placeholder="Digite o cpf" path="cpf"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="rg" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">RG</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="rg"
					placeholder="Digite o RG" path="rg"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="email" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="field_1">Email</label>
			</div>
			<div class="col-xs-3">
				<form:input type="text" cssClass="form-control" id="email"
					placeholder="Digite o email" path="email"
					cssErrorClass="field_error form-control" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6">
				<form:errors path="perfil.id" cssClass="text-danger" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1">
				<label for="inputCargo">Perfil*</label>
			</div>
			<div class="col-xs-4">
				<form:select id="inputCargo" path="perfil.id" class="form-control"
					cssErrorClass="field_error form-control">
					<form:option value="">Selecione um Perfil</form:option>
					<form:options items="${perfis}" itemValue="id"
						itemLabel="descricao" />
				</form:select>




			</div>
			&nbsp; <a href="#" data-toggle="modal" class="btn btn-success"
				data-target="#modalCadastrarPerfil" title="Cadastrar Perfil"> <span
				class="glyphicon glyphicon-plus"></span>
			</a>
		</div>
	</div>


</div>

<div class="row">
	<div class="col-xs-offset-1 col-xs-12" style="margin-left: 0%">
		<c:if test="${alterarTela == null}">
			<button class="btn btn-success abrirJanela" style="width: 100%;">Salvar</button>
		</c:if>
		<c:if test="${alterarTela != null}">
			<button class="btn btn-success abrirJanela">Alterar</button>
			<a href="${raiz}usuarios" class="btn btn-danger abrirJanela">Voltar</a>
		</c:if>
	</div>
	<br />
</div> --%>

<script type="text/javascript"
	src="<c:url value="/resources/js/usuario/usuario.js" />"></script>
