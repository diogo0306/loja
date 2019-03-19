package br.com.eclinic.service.jornada;

import java.util.List;

import br.com.eclinic.entity.jornada.Jornada;

public interface JornadaService {
	
	void salvar(Jornada jornada);

	Jornada buscar(Long pk);

	void alterar(Jornada jornada);

	List<Jornada> listar();

	void excluir(Jornada jornada);
	
	List<Jornada> pesquisarPorMedico(Long id);

	Jornada buscarPorMedico (Long id);
}
