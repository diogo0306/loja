<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />
<input type="hidden" value="${raiz}tabela_exame/salvar_valor"
	id="url-salvar" />


<%--Alerta que será tratado via javascript, de acordo com o resultado da inserção de um risco pelo modal--%>
<div class="row" id='alerta-insercao-tabela'></div>

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
			<fieldset class="content-group">
				<legend class="text-bold">Tabela - Inserir Valor</legend>

				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="tabelaExame.tabela.id" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<label for="inputTabela" style="text-align: left; margin-top: -1%"
						class="col-lg-1 control-label">Tabela de Preço*</label>
					<div class="col-lg-3">
						<form:select path="tabelaExame.tabela.id" class="form-control"
							id="selectTabela" cssErrorClass="field_error form-control">
							<form:option value="" label="Selecione" />
							<form:options items="${tabelas}" itemLabel="nome" itemValue="id" />
							<form:input path="tabelaExame.tabela.id" type="hidden" />
							<form:input path="tabelaExame.tabela.nome" type="hidden" />
						</form:select>
					</div>

					<div class="col-xs-2" style="margin-left: -20px; margin-top: 0px;">
						&nbsp; <a href="#" data-toggle="modal" class="btn btn-success"
							data-target="#modalCadastrarTabela" title="Cadastrar Tabela">
							<span class="glyphicon glyphicon-plus"></span>
						</a>
					</div>

				</div>
				<br />

				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="tabelaExame.exame.id" cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<label for="inputTabela" style="text-align: left; margin-top: -1%"
						class="col-lg-1 control-label">Exame*</label>
					<div class="col-lg-3">
						<form:select path="tabelaExame.exame.id" class="form-control"
							id="selectExame" cssErrorClass="field_error form-control">
							<form:option value="" label="Selecione" />
							<form:options items="${exames}" itemLabel="nome" itemValue="id" />
							<form:input path="tabelaExame.exame.id" type="hidden" />
							<form:input path="tabelaExame.exame.nome" type="hidden" />
						</form:select>
					</div>

					<div class="col-xs-2" style="margin-left: -20px; margin-top: 0px;">
						&nbsp; <a href="#" data-toggle="modal" class="btn btn-success"
							data-target="#modalCadastrarExame" title="Cadastrar Exame"> <span
							class="glyphicon glyphicon-plus"></span>
						</a>
					</div>

				</div>
				<br />

				<div class="row">
					<div class="col-xs-offset-1 col-xs-6">
						<form:errors path="tabelaExame.valorTransiente"
							cssClass="text-danger" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-1">
						<label for="inputValor">Valor*</label>
					</div>
					<div class="col-xs-2">
						<form:input type="text" class="form-control" id="inputValor"
							placeholder="Digite o Valor" path="tabelaExame.valorTransiente"
							cssErrorClass="field_error form-control" />
					</div>
				</div>
				<br />

				<div class="form-inline">
					<button class="btn btn-success abrirJanela" style="width: 7%;">
						Adicionar <i class=" icon-folder-plus4"></i>
					</button>
				</div>
				<br />

			</fieldset>

			<fieldset class="content-group">
				<legend class="text-bold">Resumo</legend>

				<table class="table table-hover table-striped table-bordered "
					id="tabelaExames">
					<thead>
						<tr>
							<th width="10%">Tabela</th>
							<th width="10%">Exame</th>
							<th width="5%">Valor</th>
							<th width="5%">Ações</th>
						</tr>
					</thead>
					<tbody id="listaItens">
						<c:forEach var="exame" items="${exameDTO.listaTabelaExame}"
							varStatus="status">
							<tr>
								<td><c:out value="${exame.tabela.nome}" /></td>
								<td><c:out value="${exame.exame.nome}" /></td>
								<td><c:out value="${exame.valor}" /></td>
								<td><a class="glyphicon glyphicon-remove"
									id="btn-excluir_exame_lista" onclick="exluirLista($(this))"></a></td>

								<form:input path="listaTabelaExame[${status.index}].exame.nome"
									type="hidden" />
								<form:input path="listaTabelaExame[${status.index}].exame.id"
									type="hidden" />
								<form:input path="listaTabelaExame[${status.index}].id"
									type="hidden" />
								<form:input path="listaTabelaExame[${status.index}].valor"
									type="hidden" />
								<form:input path="listaTabelaExame[${status.index}].tabela.nome"
									type="hidden" />
								<form:input path="listaTabelaExame[${status.index}].tabela.id"
									type="hidden" />
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</fieldset>

			<div class="text-right">
				<a href="${raiz}tabelas" class=" btn bg-danger abrirJanela">Cancelar</a>

				<button type="submit" class="btn btn-primary" id="btn-salvar_valor">
					Salvar <i class="icon-arrow-right14 position-right"></i>
				</button>
			</div>

		</div>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/exame/tabela_exame.js" />"></script>

