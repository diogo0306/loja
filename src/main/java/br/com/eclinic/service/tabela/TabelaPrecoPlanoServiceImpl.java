package br.com.eclinic.service.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.tabela.TabelaPrecoPlano;
import br.com.eclinic.hibernate.tabela.TabelaPrecoPlanoRepository;

@Service(value = "tabelaPrecoPlanoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TabelaPrecoPlanoServiceImpl implements TabelaPrecoPlanoService {

	@Autowired
	private TabelaPrecoPlanoRepository tabelaPrecoPlanoRepository;
	
	@Override
	public void salvar(TabelaPrecoPlano tabela) {
		this.tabelaPrecoPlanoRepository.salvar(tabela);
	}

	@Override
	public void alterar(TabelaPrecoPlano tabela) {
		this.tabelaPrecoPlanoRepository.alterar(tabela);
	}

	@Override
	public void excluir(TabelaPrecoPlano tabela) {
		this.tabelaPrecoPlanoRepository.excluir(tabela);
	}

	@Override
	public TabelaPrecoPlano buscar(Long pk) {
		return this.tabelaPrecoPlanoRepository.buscar(pk);
	}

	@Override
	public List<TabelaPrecoPlano> listar() {
		return this.tabelaPrecoPlanoRepository.listar();
	}

}
