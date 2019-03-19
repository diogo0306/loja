package br.com.eclinic.service.representante;

import java.util.List;

import br.com.eclinic.entity.representante.Representante;

public interface RepresentanteService {
	
	void salvar (Representante representante);
	
	void excluir (Representante representante);
	
	void alterar (Representante representante);
	
	Representante buscar (Long pk);
	
	List<Representante> listar();
	
	List<Representante> consultar(Representante representante);
	
	List<Representante> consultarPorDescricao(String descricao);
	

}
