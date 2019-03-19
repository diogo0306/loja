package br.com.eclinic.service.plano;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.exception.NegocioException;

public interface PlanoService {
	
	void salvar(Plano plano);

	void alterar(Plano plano);

	List<Plano> listar();

	void excluir(Plano plano) throws NegocioException;

	Plano buscar(Long pk) throws NegocioException;

	List<Plano> listarPorCliente(Cliente cliente);
	
	List<Plano> consultar(Plano plano, Cliente cliente);

}
