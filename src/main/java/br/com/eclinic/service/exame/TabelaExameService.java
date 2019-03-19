package br.com.eclinic.service.exame;

import java.util.List;

import br.com.eclinic.entity.exame.BeneficiarioExame;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.entity.exame.TabelaExame;

public interface TabelaExameService {
	
	void salvar(TabelaExame tabelaExame);

	TabelaExame buscar(Long pk);

	void alterar(TabelaExame tabelaExame);

	List<TabelaExame> listar();

	void excluir(TabelaExame tabelaExame);

	List<TabelaExame> listarOrder(String atributoOrder);
	
	TabelaExame verificarPorExameTabela (Tabela tabela, Exame exame);
	
	List<Exame> consultarExamesPorTabela(Tabela tabela);
	
	List<TabelaExame> consultarPorBeneficiarioExame (BeneficiarioExame beneficiarioExame);

}
