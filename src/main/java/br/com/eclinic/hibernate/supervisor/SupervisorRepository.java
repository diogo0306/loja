package br.com.eclinic.hibernate.supervisor;

import java.util.List;

import br.com.eclinic.entity.supervisor.Supervisor;

public interface SupervisorRepository {
	
	void salvar (Supervisor supervisor);
	
	void excluir (Supervisor supervisor);
	
	void alterar (Supervisor supervisor);
	
	Supervisor buscar (Long pk);
	
	List<Supervisor> listar();
	
	List<Supervisor> consultar (Supervisor supervisor);

}
