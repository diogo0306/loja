package br.com.eclinic.service.fornecedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.fornecedor.Fornecedor;
import br.com.eclinic.hibernate.fornecedor.FornecedorRepository;

@Service(value = "fornecedorService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FornecedorServiceImpl implements FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Override
	public void salvar(Fornecedor fornecedor) {
		this.fornecedorRepository.salvar(fornecedor);
	}

	@Override
	public void alterar(Fornecedor fornecedor) {
		this.fornecedorRepository.alterar(fornecedor);
	}

	@Override
	public void excluir(Fornecedor fornecedor) {
		this.fornecedorRepository.excluir(fornecedor);
	}

	@Override
	public Fornecedor buscar(Long pk) {
		return this.fornecedorRepository.buscar(pk);
	}

	@Override
	public List<Fornecedor> listar() {
		return this.fornecedorRepository.listar();
	}

	@Override
	public List<Fornecedor> pesquisar(Fornecedor fornecedor) {
		return this.fornecedorRepository.pesquisar(fornecedor);
	}

}
