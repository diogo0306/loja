package br.com.eclinic.service.motivo;

import java.util.List;
import br.com.eclinic.entity.motivo.Motivo;

public interface MotivoService {
	
    void salvar (Motivo motivo);
	
	void alterar (Motivo motivo);
	
	void excluir (Motivo motivo);
	
	Motivo buscar (Long pk);
	
	List<Motivo> listar();

}
