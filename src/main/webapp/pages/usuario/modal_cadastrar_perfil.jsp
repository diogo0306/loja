<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<div class="modal fade" id="modalCadastrarPerfil">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title">
                    <b>Cadastrar Perfil</b>
                </h4>
            </div>

            <!-- modal aqui -->

            <div class="modal-body">

                <form:form id="formCadastrarPerfil"
                           action="${raiz}usuario/salvar_perfil" method="POST"
                           modelAttribute="usuario" class="formulario_acoes"
                           style="display:block">

                    <div class="divSeparador2">
                        <div class="row">
                            <div class="col-xs-1">
                                <label for="inputDesdricao">Descrição*</label>
                            </div>
                            <div class="col-xs-5">
                                <div class="col-xs-12" style="margin-top: -5px">
                                    <form:input id="inputDesdricao" class="form-control"
                                                placeholder="Digite a descricao" path="perfil.descricao"
                                                type="text" cssErrorClass="field_error form-control"/>
                                </div>
                            </div>

                            <br/><br/>
                            <!-- Restrições  -->

                            <c:if test="${erroAdicionarPerfil != null}">
                                <div class="row">
                                    <div class="alert alert-danger">
                                        <button type="button" class="close" data-dismiss="alert"
                                                aria-hidden="true">&times;
                                        </button>
                                            ${erroAdicionarPerfil}</div>
                                </div>
                            </c:if>

                            <div class="alert alert-warning" id="alerta-funcionalidade-escolhida" hidden>
                                <button type="button" class="close" data-dismiss="alert"
                                aria-hidden="true">&times; </button> A funcionalidade já foi escolhida
                            </div>

                            <div class="panel panel-default" id="perfil-group">
                                <div class="panel-heading">
                                    <h6 class="panel-title">Perfil</h6>
                                </div>
                                <div class="divSeparador2">
                                   
                                    <br/>
                                    <div class="row">
                                        <div class="col-xs-2">
                                            <label for="funcionalidades">Funcionalidades</label>
                                        </div>
                                        <div class="col-xs-3">
                                            <form:select id="funcionalidades" class="form-control"
                                                         path="perfil.id">
                                                <option value="NULL">Selecione</option>
                                                <c:forEach var="funcionalidade" items="${funcionalidades}">
                                                    <option value="${funcionalidade.id}">
                                                            ${funcionalidade.id} - ${funcionalidade.nome}</option>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                        <div class="col-xs-5">
                                            <div class="form-group">
                                                <%--<label class="col-lg-2 control-label">&nbsp;</label>--%>
                                                <div class="col-lg-6" style="width: 100% !important;">
                                                    <form:checkbox id="chk-inserir"
                                                                   path="funcionalidadeOperacaoDTO.inserir"
                                                                   label="Inserir"/>
                                                    <form:checkbox id="chk-alterar"
                                                                   path="funcionalidadeOperacaoDTO.alterar"
                                                                   label="Alterar"/>
                                                    <form:checkbox id="chk-excluir"
                                                                   path="funcionalidadeOperacaoDTO.excluir"
                                                                   label="Excluir"/>
                                                    <form:checkbox id="chk-visualizar"
                                                                   path="funcionalidadeOperacaoDTO.visualizar"
                                                                   label="Visualizar"/>
                                                </div>

                                            </div>

                                        </div>
                                        <div class="col-lg-2">
                                            <a href="#" id="btn-add-funcionalidade" class="btn btn-primary">Adicionar</a>
                                        </div>
                                    </div>

                                </div>

                                <br/> <br/>

                            </div>
                            <div class="panel panel-default"
                                 style="height: 200px !important;">
                                <div class="panel-heading">
                                    <h6 class="panel-title">Lista de Funcionalidades /
                                        Permissões</h6>
                                </div>
                                <div id="divList"
                                     style="overflow: auto !important; height: 162px !important;">


                                        <%-- <form:form action="${raiz}perfil/excluir-perfil" method="POST"
                                                                    modelAttribute="funcionalidadeOperacao" id="form-excluir">
                                            <form:hidden path="idExcluir" id="input-excluir"/>
                                        </form:form> --%>

                                    <table class="table table-striped table-hover table-bordered " id="tabela-funcionalidades">
                                        <thead>
                                        <tr>
                                            <th>Funcionalidade</th>
                                            <th>Inserir</th>
                                            <th>Alterar</th>
                                            <th>Excluir</th>
                                            <th>Visualizar</th>
                                            <th>Ações</th>
                                        </tr>
                                        </thead>
                                        <%--as linhas da tabela são preenchidas via js, por meio do arquivo [/resources/js/usuario/usuario.js]--%>

                                        <tbody>

                                        </tbody>
                                    </table>


                                </div>

                            </div>

                            <%--
                                os campos 'hidden' abaixo são populados com os dados do formulário
                                de inserir usuário, por meio do arquivo [/resources/js/usuario/usuario.js]
                            --%>
                            <form:hidden path="nome"/>
                            <form:hidden path="login"/>
                            <form:hidden path="telefone"/>
                            <form:hidden path="cpf"/>
                            <form:hidden path="rg"/>
                            <form:hidden path="email"/>
                        </div>
                        <br/>
                    </div>

                </form:form>

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-success" id="btn-add-perfil"
                        onclick="setHiddenFieldsFormCadastrarPerfil();submeterComJanela(formCadastrarPerfil, modalCadastrarPerfil)">
                    Confirmar
                </button>
            </div>
        </div>

        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>