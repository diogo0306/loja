package br.com.eclinic.service.especialidade;

import java.util.List;
import br.com.eclinic.entity.especialidade.Especialidade;

public interface EspecialidadeService {
	
	void salvar(Especialidade especialidade);
	
	Especialidade buscar(Long pk);
	
	List<Especialidade> listar();

	void alterar(Especialidade especialidade);
}
