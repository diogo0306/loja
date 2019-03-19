package br.com.eclinic.hibernate.caixa;

import java.util.List;

import br.com.eclinic.entity.caixa.Caixa;

public interface CaixaRepository {
	
	void salvar(Caixa caixa);
	
	void alterar(Caixa caixa);
	
	void excluir(Caixa caixa);
	
	Caixa buscar(Long pk);
	
	List<Caixa> listar();

}
