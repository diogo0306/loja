package br.com.eclinic.hibernate.especialidade;

import java.util.List;

import br.com.eclinic.entity.especialidade.Especialidade;

public interface EspecialidadeRepository  {
	
	Especialidade buscar(Long pk);
	
	void salvar(Especialidade especialidade);

	List<Especialidade> listar();
	
	void alterar(Especialidade especialidade);


}
