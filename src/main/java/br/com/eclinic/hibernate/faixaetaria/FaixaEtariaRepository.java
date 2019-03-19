package br.com.eclinic.hibernate.faixaetaria;

import java.util.List;

import br.com.eclinic.entity.tabela.FaixaEtaria;
import br.com.eclinic.entity.tabela.TabelaFaixa;

public interface FaixaEtariaRepository {
	
	void salvar (FaixaEtaria faixaEtaria);
	
	void alterar (FaixaEtaria faixaEtaria);
	
	void excluir (FaixaEtaria faixaEtaria);
	
	FaixaEtaria buscar (Long pk);
	
	List<FaixaEtaria> listar();
	
	List<FaixaEtaria> buscarFaixasPorTabela (TabelaFaixa tabelaFaixa);
}
