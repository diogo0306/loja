package br.com.eclinic.service.caixa;

import java.util.List;

import br.com.eclinic.entity.caixa.Caixa;

public interface CaixaService {
	
	void salvar(Caixa caixa);
	
	void alterar(Caixa caixa);
	
	void excluir(Caixa caixa);
	
	Caixa buscar(Long pk);
	
	List<Caixa> listar();

}
