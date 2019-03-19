package br.com.eclinic.service.procedimento;

import java.util.List;

import br.com.eclinic.entity.procedimento.GrupoProcedimento;
import br.com.eclinic.entity.procedimento.Procedimento;

public interface GrupoProcedimentoService {
	
	void salvar (GrupoProcedimento grupoProcedimento);
	
	void alterar (GrupoProcedimento grupoProcedimento);
	
	void excluir (GrupoProcedimento grupoProcedimento);
	
	GrupoProcedimento buscar (Long pk);
	
	List<GrupoProcedimento> listar();
	
	List<Procedimento> consultarProcedimentosPorGrupo (GrupoProcedimento grupoProcedimento);
	
	List<GrupoProcedimento> consultar (GrupoProcedimento grupoProcedimento);

}
