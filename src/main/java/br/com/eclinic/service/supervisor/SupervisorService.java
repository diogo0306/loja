package br.com.eclinic.service.supervisor;

import java.util.List;

import br.com.eclinic.entity.supervisor.Supervisor;

public interface SupervisorService {
	
	void salvar (Supervisor supervisor);
	
	void excluir (Supervisor supervisor);
	
	void alterar (Supervisor supervisor);
	
	Supervisor buscar (Long pk);
	
	List<Supervisor> listar();
	
	List<Supervisor> consultar (Supervisor supervisor);

}
