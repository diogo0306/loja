package br.com.eclinic.service.supervisor;

import java.util.List;

import br.com.eclinic.entity.supervisor.Responsavel;

public interface ResponsavelService {
	
	void salvar (Responsavel responsavel);
	
	void excluir (Responsavel responsavel);
	
	void alterar (Responsavel responsavel);
	
	Responsavel buscar (Long pk);
	
	List<Responsavel> listar();
	
	List<Responsavel> pesquisarPorMedico(Long id);
	
}
