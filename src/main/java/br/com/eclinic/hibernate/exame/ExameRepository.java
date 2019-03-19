package br.com.eclinic.hibernate.exame;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;

public interface ExameRepository {
	
	void salvar(Exame exame);

	Exame buscar(Long pk);

	void alterar(Exame exame);

	List<Exame> listar();

	void excluir(Exame exame);

	List<Exame> listarOrder(String atributoOrder);
	
	List<Exame> consultar(Exame exame);

	List<Exame> listarPorCliente(Cliente cliente);	
	
	List<Exame> consultarPorTabela(Tabela tabela);
	
	Exame verificarNomeExistente (Exame exame);
	
}
