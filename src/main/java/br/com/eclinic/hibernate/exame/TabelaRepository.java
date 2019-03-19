package br.com.eclinic.hibernate.exame;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.exame.Tabela;

public interface TabelaRepository {
	
	void salvar(Tabela tabela);

	Tabela buscar(Long pk);

	void alterar(Tabela tabela);

	List<Tabela> listar();

	void excluir(Tabela tabela);

	List<Tabela> listarOrder(String atributoOrder);
	
	List<Tabela> consultar(Tabela tabela);

	List<Tabela> listarPorCliente(Cliente cliente);
	
	Tabela verificarNomeExistente (Tabela tabela);
	
}
