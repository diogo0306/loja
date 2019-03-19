package br.com.eclinic.hibernate.procedimento;

import java.util.List;

import br.com.eclinic.entity.procedimento.RelacaoGrupoProcedimento;

public interface RelacaoGrupoProcedimentoRepository {
	
	void salvar (RelacaoGrupoProcedimento relacaoGrupoProcedimento);
	
	void alterar (RelacaoGrupoProcedimento relacaoGrupoProcedimento);
	
	void excluir (RelacaoGrupoProcedimento relacaoGrupoProcedimento);
	
	RelacaoGrupoProcedimento buscar (Long pk);
	
	List<RelacaoGrupoProcedimento> listar();

}
