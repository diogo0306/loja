package br.com.eclinic.service.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.produto.Produto;
import br.com.eclinic.hibernate.produto.ProdutoRepository;

@Service(value = "produtoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public void salvar(Produto produto) {
		this.produtoRepository.salvar(produto);
	}

	@Override
	public void alterar(Produto produto) {
		this.produtoRepository.alterar(produto);
	}

	@Override
	public void excluir(Produto produto) {
		this.produtoRepository.excluir(produto);

	}

	@Override
	public Produto buscar(Long pk) {
		return this.produtoRepository.buscar(pk);
	}

	@Override
	public List<Produto> listar() {
		return this.produtoRepository.listar();
	}

	@Override
	public List<Produto> consultar(Produto produto) {
		return this.produtoRepository.consultar(produto);
	}

}
