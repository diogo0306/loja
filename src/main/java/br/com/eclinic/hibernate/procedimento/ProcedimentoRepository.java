package br.com.eclinic.hibernate.procedimento;

import java.util.List;

import br.com.eclinic.entity.procedimento.Procedimento;

public interface ProcedimentoRepository {
	
	void salvar (Procedimento procedimento);
	
	void alterar (Procedimento procedimento);
	
	void excluir (Procedimento procedimento);
	
	Procedimento buscar (Long pk);
	
	List<Procedimento> listar();

}
