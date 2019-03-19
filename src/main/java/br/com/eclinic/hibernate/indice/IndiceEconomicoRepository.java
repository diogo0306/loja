package br.com.eclinic.hibernate.indice;

import java.util.List;

import br.com.eclinic.entity.indice.IndiceEconomico;

public interface IndiceEconomicoRepository {

	void salvar(IndiceEconomico indice);

	void alterar(IndiceEconomico indice);

	void excluir(IndiceEconomico indice);

	IndiceEconomico buscar(Long pk);

	List<IndiceEconomico> listar();
	
	List<IndiceEconomico> consultar(IndiceEconomico indiceEconomico);

}
