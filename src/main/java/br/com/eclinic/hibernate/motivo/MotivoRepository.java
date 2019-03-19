package br.com.eclinic.hibernate.motivo;

import java.util.List;

import br.com.eclinic.entity.motivo.Motivo;

public interface MotivoRepository {
	
	void salvar (Motivo motivo);
	
	void alterar (Motivo motivo);
	
	void excluir (Motivo motivo);
	
	Motivo buscar (Long pk);
	
	List<Motivo> listar();

}
