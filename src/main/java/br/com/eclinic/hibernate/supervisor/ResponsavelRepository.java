package br.com.eclinic.hibernate.supervisor;

import java.util.List;

import br.com.eclinic.entity.supervisor.Responsavel;

public interface ResponsavelRepository {
	
	void salvar (Responsavel responsavel);
	
	void excluir (Responsavel responsavel);
	
	void alterar (Responsavel responsavel);
	
	Responsavel buscar (Long pk);
	
	List<Responsavel> listar();
	
	List<Responsavel> pesquisarPorMedico(Long id);

}