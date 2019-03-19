package br.com.eclinic.hibernate.tabela;

import java.util.List;

import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.tabela.TabelaFaixa;

public interface TabelaFaixaRepository {

	void salvar(TabelaFaixa tabela);

	void alterar(TabelaFaixa tabela);

	void excluir(TabelaFaixa tabela);

	TabelaFaixa buscar(Long pk);

	List<TabelaFaixa> listar();
	
	List<TabelaFaixa> buscarFaixasPorPlano(Plano plano);
}
