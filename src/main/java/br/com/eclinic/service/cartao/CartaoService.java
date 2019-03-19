package br.com.eclinic.service.cartao;

import java.util.List;

import br.com.eclinic.entity.cartao.Cartao;

public interface CartaoService {
	
	void salvar (Cartao cartao);
	
	void alterar (Cartao cartao);
	
	void excluir (Cartao cartao);
	
	Cartao buscar (Long pk);
	
	List<Cartao> listar();

}
