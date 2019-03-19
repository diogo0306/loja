<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="modal fade" id="modalCadastrarPlano">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title">
                    <b>Cadastrar Plano</b>
                </h4>
            </div>


            <div class="modal-body">
                <form:form action="${raiz}paciente/plano/salvar" method="POST"
                           modelAttribute="plano" class="formulario_acoes"
                           id="formCadastrarPlano">
                <div class="divSeparador2">
                    <div class="row">
                        <div class="col-xs-offset-1 col-xs-6">
                            <form:errors path="nome" cssClass="text-danger" />
                        </div>
                    </div>
                    <c:if test="${campos_obrigatorios != null}">
                        <div class="row">
                            <div class="alert alert-danger">
                                <button type="button" class="close" data-dismiss="alert"
                                        aria-hidden="true">&times;</button>${campos_obrigatorios}</div>
                        </div>
                    </c:if>
                    <div class="row">

                        <div class="row" id="msgFieldNomeRequerido" hidden>
                            <div class="col-xs-offset-1 col-xs-6" style="margin-left: 20%">
                                <span class="text-danger">Campo obrigatório.</span>
                            </div>
                        </div>

                        <div class="col-xs-2">
                            <label for="nome">Nome*</label>
                        </div>
                        <div class="col-xs-10">
                            <form:input type="text" class="form-control"
                                   placeholder="Digite o nome" path="nome"
                                   cssErrorClass="field_error form-control" />
                        </div>
                    </div>
                    <br />

                    <div class="row">

                        <div class="row" id="msgFieldTipoRequerido" hidden>
                            <div class="col-xs-offset-1 col-xs-6" style="margin-left: 20%">
                                <span class="text-danger">Campo obrigatório.</span>
                            </div>
                        </div>

                        <div class="col-xs-2">
                            <label for="msgFieldTipoRequerido">Tipo*</label>
                        </div>

                        <div class="col-xs-6">
                            <form:select
                                         path="codigoTipoPlanoTransiente" class="form-control"
                                         cssErrorClass="field_error form-control">
                                <form:option value="" label="Selecione" />
                                <form:options items="${tiposPlano}" itemLabel="descricao"
                                              itemValue="codigo" />
                            </form:select>
                           <%-- <select id="selectTipoPlano"
                                    path="codigoTipoPlanoTransiente" class="form-control"
                                    cssErrorClass="field_error form-control">
                                <option value="" label="Selecione"></option>
                                <options items="${tiposPlano}" itemLabel="descricao" itemValue="codigo"></options>
                            </select>--%>
                        </div>
                    </div>
                    <br />
                </div>
                </form:form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary"
                        onclick="submeterFormModalPlanoSaude(formCadastrarPlano, modalCadastrarPlano)">Confirmar</button>
            </div>

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<%--/paciente/plano/salvar--%>
