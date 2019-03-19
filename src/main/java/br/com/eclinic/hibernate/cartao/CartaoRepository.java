package br.com.eclinic.hibernate.cartao;

import java.util.List;

import br.com.eclinic.entity.cartao.Cartao;

public interface CartaoRepository {
	
	void salvar (Cartao cartao);
	
	void alterar (Cartao cartao);
	
	void excluir (Cartao cartao);
	
	Cartao buscar (Long pk);
	
	List<Cartao> listar();

}
