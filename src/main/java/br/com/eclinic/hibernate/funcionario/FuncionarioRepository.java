package br.com.eclinic.hibernate.funcionario;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.funcionario.Funcionario;

public interface FuncionarioRepository {
	
	void salvar (Funcionario funcionario);
	
	void alterar (Funcionario funcionario);
	
	void excluir (Funcionario funcionario);
	
	Funcionario buscar (Long pk);
	
	List<Funcionario> listar();
	
	List<Funcionario> listarOrder (String atributoOrder);
	
	List<Funcionario> consultar (Funcionario funcionario, Cliente cliente);

}
