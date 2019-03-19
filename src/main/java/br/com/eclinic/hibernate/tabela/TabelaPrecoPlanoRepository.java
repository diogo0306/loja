package br.com.eclinic.hibernate.tabela;

import java.util.List;

import br.com.eclinic.entity.tabela.TabelaPrecoPlano;

public interface TabelaPrecoPlanoRepository {
	
	void salvar (TabelaPrecoPlano tabela);
	
	void alterar (TabelaPrecoPlano tabela);
	
	void excluir (TabelaPrecoPlano tabela);
	
	TabelaPrecoPlano buscar (Long pk);
	
	List<TabelaPrecoPlano> listar();

}
