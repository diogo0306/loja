<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" %>

<c:url value="/" var="raiz"/>

<div class="page-container">
    <div class="page-content">
        <div class="content-wrapper">

            <!-- Page header -->
            <div class="page-header">     
                <div class="page-header-content">
                    <div class="page-title">
                        <h4>
                            <i class="icon-arrow-left52 position-left"></i> <span
                                class="text-semibold">Gestão</span> - Profissional - Incluir
                        </h4>
                    </div>
                </div>

                <div class="breadcrumb-line">
                    <ul class="breadcrumb">
                        <li class="active"><i class="icon-home2 position-left"></i><a
                                href="${raiz}"> Home</a></li>
                        <li>Gestão</li>
                        <li><a href="${raiz}profissionais">Profissionais</a></li>
                        <li class="active">Incluir</li>

                    </ul>
                </div>
            </div>

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

                    <form:form class="form-horizontal" role="form"
                               action="${raiz}medico/salvar_medico" id="formularioMedico"
                               method="POST" modelAttribute="medicoDTO">
                        <tiles:insertAttribute name="camposForm"/>
                    </form:form>

                </div>
            </div>
        </div>
    </div>
</div>
