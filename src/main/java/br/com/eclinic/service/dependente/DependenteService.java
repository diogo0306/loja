package br.com.eclinic.service.dependente;

import java.util.List;

import br.com.eclinic.entity.dependente.Dependente;

public interface DependenteService {

	void salvar(Dependente dependente);

	void excluir(Dependente dependente);

	void alterar(Dependente dependente);

	Dependente buscar(Long pk);

	List<Dependente> listar();

	List<Dependente> listarPorPaciente(Long id);

}
