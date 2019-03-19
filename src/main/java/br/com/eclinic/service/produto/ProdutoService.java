package br.com.eclinic.service.produto;

import java.util.List;

import br.com.eclinic.entity.produto.Produto;

public interface ProdutoService {

	void salvar(Produto produto);

	void alterar(Produto produto);

	void excluir(Produto produto);

	Produto buscar(Long pk);

	List<Produto> listar();

	List<Produto> consultar(Produto produto);

}
