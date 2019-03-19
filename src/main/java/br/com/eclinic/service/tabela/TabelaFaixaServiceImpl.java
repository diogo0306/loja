package br.com.eclinic.service.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.tabela.TabelaFaixa;
import br.com.eclinic.hibernate.tabela.TabelaFaixaRepository;

@Service(value = "tabelaFaixaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TabelaFaixaServiceImpl implements TabelaFaixaService{

	
	@Autowired
	private TabelaFaixaRepository tabeleaFaixaRepository;
	
	@Override
	public void salvar(TabelaFaixa tabela) {
		tabeleaFaixaRepository.salvar(tabela);
	}

	@Override
	public void alterar(TabelaFaixa tabela) {
		tabeleaFaixaRepository.alterar(tabela);
		
	}

	@Override
	public void excluir(TabelaFaixa tabela) {
		tabeleaFaixaRepository.excluir(tabela);
		
	}

	@Override
	public TabelaFaixa buscar(Long pk) {
		return tabeleaFaixaRepository.buscar(pk);
	}

	@Override
	public List<TabelaFaixa> listar() {
		return tabeleaFaixaRepository.listar();
	}

	@Override
	public List<TabelaFaixa> buscarFaixasPorPlano(Plano plano) {
		return this.tabeleaFaixaRepository.buscarFaixasPorPlano(plano);
	}
	
	

}
