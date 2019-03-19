package br.com.eclinic.service.tabela;

import java.util.List;

import br.com.eclinic.entity.tabela.TabelaPrecoPlano;

public interface TabelaPrecoPlanoService {
	
	void salvar (TabelaPrecoPlano tabela);
	
	void alterar (TabelaPrecoPlano tabela);
	
	void excluir (TabelaPrecoPlano tabela);
	
	TabelaPrecoPlano buscar (Long pk);
	
	List<TabelaPrecoPlano> listar();

}
