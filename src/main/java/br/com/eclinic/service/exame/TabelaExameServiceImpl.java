package br.com.eclinic.service.exame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.exame.BeneficiarioExame;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.entity.exame.TabelaExame;
import br.com.eclinic.hibernate.exame.TabelaExameRepository;

@Service(value = "tabelaExameService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TabelaExameServiceImpl implements TabelaExameService {
	
	@Autowired
	private TabelaExameRepository tabelaExameRepository;

	@Override
	public void salvar(TabelaExame tabelaExame) {
		tabelaExameRepository.salvar(tabelaExame);
	}

	@Override
	public TabelaExame buscar(Long pk) {
		return tabelaExameRepository.buscar(pk);
	}

	@Override
	public void alterar(TabelaExame tabelaExame) {
		tabelaExameRepository.alterar(tabelaExame);
	}

	@Override
	public List<TabelaExame> listar() {
		return tabelaExameRepository.listar();
	}

	@Override
	public void excluir(TabelaExame tabelaExame) {
		tabelaExameRepository.excluir(tabelaExame);
	}

	@Override
	public List<TabelaExame> listarOrder(String atributoOrder) {
		return tabelaExameRepository.listarOrder(atributoOrder);
	}

	@Override
	public TabelaExame verificarPorExameTabela(Tabela tabela, Exame exame) {
		return tabelaExameRepository.verificarPorExameTabela(tabela, exame);
	}

	@Override
	public List<Exame> consultarExamesPorTabela(Tabela tabela) {
		return tabelaExameRepository.consultarExamesPorTabela(tabela);
	}

	@Override
	public List<TabelaExame> consultarPorBeneficiarioExame(BeneficiarioExame beneficiarioExame) {
		return this.tabelaExameRepository.consultarPorBeneficiarioExame(beneficiarioExame);
	}

}
