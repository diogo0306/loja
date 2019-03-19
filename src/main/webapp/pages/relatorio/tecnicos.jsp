<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:url value="/" var="raiz" />

<div class="row">
  <div class="col-xs-1">
    <label for="field-4">Técnico</label>
  </div>
  <div class="col-xs-4">
  	 
	<select name="idTecnico" class="form-control">
		<option value="0">Selecione um técnico</option>
		<c:forEach items="${tecnicos}" var="tecnico">
			<option value="${tecnico.id}" <c:if test="${ordemServico.tecnico.id == tecnico.id}">selected="selected"</c:if>>${tecnico.nome}</option>
		</c:forEach>
	</select>  	
	 
  </div>
</div>
<br />
