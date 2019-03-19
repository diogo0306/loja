<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<input type="hidden" id="exibir-paginacao" value="${exibirPaginacao}" />
<input type="hidden" id="primeira-pagina" value="${isPrimeiraPagina}" />
<input type="hidden" id="ultima-pagina" value="${isUltimaPagina}" />

<ul class="pager registro">
	<li style="font-weight: bold" class="linkVoltar"><c:out
			value="${quantidadeRegistrosPagina} de ${quantidadeRegistros} Registros" /></li>
</ul>
<ul class="pager registro">

	<li style="font-weight: bold"><a href="#" class="linkVoltar">Anterior</a></li>
	<li style="font-weight: bold"><c:out
			value="Página ${paginacaoDe} de ${paginacaoAte}" /></li>
	<li style="font-weight: bold"><a href="#" class="linkAvancar">Próximo</a></li>
</ul>


<script type="text/javascript"
	src="<c:url value="/resources/js/paginacao.js" />"></script>