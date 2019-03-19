<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="modal fade" id="modalCadastrarExame">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<b>Cadastrar Exame</b>
				</h4>
			</div>

			<!-- modal aqui -->

			<div class="modal-body">

				<form:form action="${raiz}tabela_exame/salvar-exame" method="POST"
					modelAttribute="exame" class="formulario_acoes"
					id="formCadastrarExame">

					<div class="divSeparador2">

						<div class="row" id="msgFieldDescricaoRequeridoExame" hidden>
							<div class="col-xs-offset-1 col-xs-6" style="margin-left: 25%">
								<span class="text-danger">Campo obrigatório.</span>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-1" style="width: 25%">
								<label for="descricao">Nome*</label>
							</div>
							<div class="col-lg-6" style="width: 75%">
								<form:input type="text" class="form-control"
									id="inputNomeExame" placeholder="Digite o nome" path="nome"
									cssErrorClass="field_error form-control" />
							</div>

						</div>
						<br />

						<div class="row">
							<div class="col-xs-1" style="width: 25%">
								<label for="inputDescricao">Descrição</label>
							</div>
							<div class="col-xs-6" style="margin: 0px -356.828px 0px 0px; width: 478px; height: 130px">
								<form:textarea maxlength="254" rows="6" cols="60"
									class="form-control" id="inputDescricao"
									placeholder="Até 254 caracteres" path="descricao"
									cssErrorClass="field_error form-control" />
							</div>
						</div>

					</div>

				</form:form>

			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-success"
					onclick="submeterFormModalExame()">Confirmar</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>