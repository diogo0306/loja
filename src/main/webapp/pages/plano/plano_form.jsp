<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<fieldset class="content-group">


	<legend class="text-bold">Incluir Plano</legend>

	<div class="form-group">
		<label class="control-label col-lg-2">Nome do Plano</label>
		<div class="col-lg-4">
			<div class="input-group col-xs-10">
				<form:input type="text" class="form-control" id="inputNome"
					required="true" placeholder="Digite o nome" path="nome"
					cssErrorClass="field_error form-control" />
				<form:errors path="nome" cssClass="text-danger" class="has-error" />
			</div>

		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-lg-2">Tipo do Plano</label>
		<div class="col-lg-4">
			<div class="input-group col-xs-6">
				<form:select id="#" path="codigoTipoPlanoTransiente"
					class="form-control" cssErrorClass="field_error form-control">
					<form:option value="" label="Selecione" />
					<form:options items="${tiposPlano}" itemLabel="descricao"
						itemValue="codigo" />
				</form:select>
				<form:errors path="codigoTipoPlanoTransiente" cssClass="text-danger"
					class="has-error" />
			</div>
		</div>
	</div>


	<div class="form-group">
		<label class="control-label col-lg-2">Data de Cadastro</label>
		<div class="col-lg-4">
			<div class="input-group col-xs-6">
				<form:input type="text" class="form-control" required="true"
					id="inputDataCadastro" placeholder="Digite o data"
					path="dataCadastro" cssErrorClass="field_error form-control" />
				<form:errors path="dataCadastro" cssClass="text-danger"
					class="has-error" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-lg-2">Valor (R$)</label>
		<div class="col-lg-4">
			<div class="input-group col-xs-6">
				<form:input type="text" class="form-control" required="true"
					id="inputValorPlano" placeholder="Digite o Valor"
					path="valorPlanoTransiente"
					cssErrorClass="field_error form-control" />
				<form:errors path="valorPlanoTransiente" cssClass="text-danger"
					class="has-error" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-1"
			style="width: 1%; margin-left: 172px; margin-top: 6px;">
			<form:checkbox id="pagamentoBalcao"
				path="pagamentoParticipacaoBalcao" name="pagamentoBalcao" />
		</div>

		<label for="pagamentoBalcao" class="col-sm-4 control-label"
			style="text-align: left;">Pagamento co-participação no Balcão</label>

	</div>
	<div class="form-group">
		<div class="col-sm-1"
			style="width: 1%; margin-left: 172px; margin-top: 6px;">
			<form:checkbox id="aplicarMudancaFaixa" path="aplicarMudancaFaixa"
				name="aplicarMudancaFaixa" label="${aplicarMudancaFaixa}"
				value="${aplicarMudancaFaixa}" />
		</div>
		<label for="aplicarMudancaFaixa" class="col-sm-5 control-label"
			style="text-align: left;">Aplicar mudança de faixa etária no
			mês posterior ao aniversário</label>


	</div>

</fieldset>

<div class="text-right">
	<a href="${raiz}planos" class=" btn bg-danger abrirJanela">Cancelar</a>

	<button type="submit" class="btn btn-primary" id="btn-salvar_faixa">
		Salvar <i class="icon-arrow-right14 position-right"></i>
	</button>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/plano/plano.js" />"></script>



<%-- <fieldset class="content-group">
    <legend class="text-bold">Idade máxima para dependentes</legend>

    <div class="form-group">
        <label class="control-label col-lg-2">Masculino</label>
        <div class="col-lg-10">
            <div class="input-group col-xs-2">

                <form:input type="number"
                            class="touchspin-set-value form-control"
                            path="plano.idadeMaximaDependenteMasculino"
                            style="display: block;"></form:input>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-lg-2">Feminino</label>
        <div class="col-lg-10">
            <div class="input-group col-xs-2">

                <form:input type="number"
                            class="touchspin-set-value form-control"
                            path="plano.idadeMaximaDependenteFeminino"
                            style="display: block;"></form:input>
            </div>
        </div>
    </div>

</fieldset> --%>
<%-- <fieldset class="content-group">
    <legend class="text-bold">Tabela - Incluir Faixa</legend>

    <br/>

    <div class="row">

        <div class="col-xs-2">
            <label>Tipo de Beneficiário*</label>
        </div>
        <div class="col-xs-2">
            <form:select id="#" path="tabelaFaixa.tipoBeneficiarioEnum"
                         class="form-control" cssErrorClass="field_error form-control">
                <c:forEach var="beneficiario" items="${beneficiarios}">
                    <option value="${beneficiario}">${beneficiario.descricao}</option>
                </c:forEach>
            </form:select>
        </div>

        <div class="col-xs-2">
            <label>Idade Inicial*</label>
        </div>
        <div class="col-xs-1" style="margin-left: -50px">
            <form:input type="number" class="touchspin-set-value form-control"
                        path="tabelaFaixa.idadeInicial" style="display: block;"></form:input>
        </div>

        <div class="col-xs-2">
            <label>Idade Final*</label>
        </div>
        <div class="col-xs-1" style="margin-left: -50px">
            <form:input type="number" class="touchspin-set-value form-control"
                        path="tabelaFaixa.idadeFinal" style="display: block;"></form:input>
        </div>
    </div>
    <br>

    <div class="row">

        <div class="col-xs-2">
            <label>Vidas Inicial*</label>
        </div>
        <div class="col-xs-1">
            <form:input type="number" class="touchspin-set-value form-control"
                        path="tabelaFaixa.vidaInicial" style="display: block;"></form:input>
        </div>

        <div class="col-xs-2" style="margin-left: 86px">
            <label>Vidas Final*</label>
        </div>
        <div class="col-xs-1" style="margin-left: -50px">
            <form:input type="number" class="touchspin-set-value form-control"
                        path="tabelaFaixa.vidaFinal" style="display: block;"></form:input>
        </div>
        <div class="col-xs-1">
            <label>Valor(R$)*</label>
        </div>
        <div class="col-xs-1" style="margin-left: 36px">
            <form:input type="text"
                        class="touchspin-set-value form-control js-decimal"
                        path="tabelaFaixa.valor" style="display: block;"></form:input>
        </div>

    </div>
    <br>
    <div class="row">
        <div class="col-xs-4" style="margin-left: 650px">
            <button class="btn btn-success" style="width: 30%;">Adicionar</button>
        </div>
    </div>
</fieldset> --%>

<%-- <fieldset class="content-group">
	<legend class="text-bold">Tabela - Resumo</legend>
	<br />
	<table
		class="table table-striped table-bordered table-hover datatable-highlight "
		id="tabelaExames">
		<thead>
			<tr>
				<th width="10%">Tipo</th>
				<th width="10%">Idade Inicial</th>
				<th width="10%">Idade Final</th>
				<th width="10%">Vida Inicial</th>
				<th width="10%">Vida Final</th>
				<th width="10%">Valor</th>
				<th width="5%">Ações</th>
			</tr>
		</thead>
		<tbody id="listaItens">
			<c:forEach var="tabela" items="${tabelaFaixaDTO.tabela}"
				varStatus="status">
				<tr>
					<td><c:out value="${tabela.tipoBeneficiarioEnum.descricao}" /></td>
					<td><c:out value="${tabela.idadeInicial}" /></td>
					<td><c:out value="${tabela.idadeFinal}" /></td>
					<td><c:out value="${tabela.vidaInicial}" /></td>
					<td><c:out value="${tabela.vidaFinal}" /></td>
					<td><c:out value="${tabela.valor}" /></td>
					<td><a class="glyphicon glyphicon-remove"
						id="btn-excluir_exame_lista" onclick="exluirLista($(this))"></a></td>

					<form:input path="tabela[${status.index}].tipoBeneficiarioEnum"
						type="hidden" />
					<form:input path="tabela[${status.index}].idadeInicial"
						type="hidden" />
					<form:input path="tabela[${status.index}].idadeFinal" type="hidden" />
					<form:input path="tabela[${status.index}].vidaInicial"
						type="hidden" />
					<form:input path="tabela[${status.index}].vidaFinal" type="hidden" />
					<form:input path="tabela[${status.index}].valor" type="hidden" />
				</tr>
			</c:forEach>
		</tbody>
	</table>
</fieldset> --%>



