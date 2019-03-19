package br.com.eclinic.hibernate.fornecedor;

import java.util.List;

import br.com.eclinic.entity.fornecedor.Fornecedor;

public interface FornecedorRepository {
	
	void salvar (Fornecedor fornecedor);
	
	void alterar (Fornecedor fornecedor);
	
	void excluir (Fornecedor fornecedor);
	
	Fornecedor buscar (Long pk);
	
	List<Fornecedor> listar();
	
	List<Fornecedor> pesquisar(Fornecedor fornecedor);

}
