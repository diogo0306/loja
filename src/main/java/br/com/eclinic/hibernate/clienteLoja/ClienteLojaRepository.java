package br.com.eclinic.hibernate.clienteLoja;

import java.util.List;

import br.com.eclinic.entity.clienteLoja.ClienteLoja;

public interface ClienteLojaRepository {

	void salvar(ClienteLoja clienteLoja);

	void alterar(ClienteLoja clienteLoja);

	void excluir(ClienteLoja clienteLoja);

	ClienteLoja buscar(Long pk);

	List<ClienteLoja> listar();

	List<ClienteLoja> consultar(ClienteLoja clienteLoja);
	
}
