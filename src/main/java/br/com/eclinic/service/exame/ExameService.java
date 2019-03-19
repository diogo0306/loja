package br.com.eclinic.service.exame;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.exception.NegocioException;

public interface ExameService {

	void salvar(Exame exame);

	void alterar(Exame exame);

	List<Exame> listar();

	void excluir(Exame exame) throws NegocioException;

	Exame buscar(Long pk);

	List<Exame> consultar(Exame exame);

	Exame verificarNomeExistente (Exame exame);

	List<Exame> listarPorCliente(Cliente cliente);
	
}
