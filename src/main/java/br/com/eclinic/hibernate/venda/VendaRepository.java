package br.com.eclinic.hibernate.venda;

import java.util.Date;
import java.util.List;

import br.com.eclinic.entity.venda.Venda;

public interface VendaRepository {

	void salvar(Venda venda);

	void alterar(Venda venda);

	void excluir(Venda venda);

	Venda buscar(Long pk);

	List<Venda> listar();

	List<Venda> consultar(Venda venda);
	
	List<Venda> consultarRelatorioPorPeriodo(Date dataInicial, Date dataFinal);

}
