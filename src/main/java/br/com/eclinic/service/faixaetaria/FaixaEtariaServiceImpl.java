package br.com.eclinic.service.faixaetaria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.tabela.FaixaEtaria;
import br.com.eclinic.entity.tabela.TabelaFaixa;
import br.com.eclinic.hibernate.faixaetaria.FaixaEtariaRepository;

@Service(value = "faixaEtariaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FaixaEtariaServiceImpl implements FaixaEtariaService {
	
	@Autowired
	private FaixaEtariaRepository faixaEtariaRepository;

	@Override
	public void salvar(FaixaEtaria faixaEtaria) {
		this.faixaEtariaRepository.salvar(faixaEtaria);
	}

	@Override
	public void alterar(FaixaEtaria faixaEtaria) {
		this.faixaEtariaRepository.alterar(faixaEtaria);;
	}

	@Override
	public void excluir(FaixaEtaria faixaEtaria) {
		this.faixaEtariaRepository.excluir(faixaEtaria);
	}

	@Override
	public FaixaEtaria buscar(Long pk) {
		return this.faixaEtariaRepository.buscar(pk);
	}

	@Override
	public List<FaixaEtaria> listar() {
		return this.faixaEtariaRepository.listar();
	}

	@Override
	public List<FaixaEtaria> buscarFaixasPorTabela(TabelaFaixa tabelaFaixa) {
		return this.faixaEtariaRepository.buscarFaixasPorTabela(tabelaFaixa);
	}

}
