package br.com.eclinic.hibernate.plano;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.plano.Plano;

public interface PlanoRepository {
	
	void salvar(Plano planoSaude);

	Plano buscar(Long pk);

	void alterar(Plano planoSaude);

	List<Plano> listar();

	void excluir(Plano planoSaude);

	List<Plano> listarOrder(String atributoOrder);
	
	List<Plano> consultar(Plano planoSaude, Cliente cliente);

	List<Plano> listarPorCliente(Cliente cliente);	

}
