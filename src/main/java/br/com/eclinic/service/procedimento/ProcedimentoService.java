package br.com.eclinic.service.procedimento;

import java.util.List;

import br.com.eclinic.entity.procedimento.Procedimento;

public interface ProcedimentoService {
	
	void salvar (Procedimento procedimento);
	
	void alterar (Procedimento procedimento);
	
	void excluir (Procedimento procedimento);
	
	Procedimento buscar (Long pk);
	
	List<Procedimento> listar();

}
