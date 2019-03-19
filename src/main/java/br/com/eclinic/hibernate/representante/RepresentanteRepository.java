package br.com.eclinic.hibernate.representante;

import java.util.List;

import br.com.eclinic.entity.representante.Representante;

public interface RepresentanteRepository {
	
	void salvar (Representante representante);
	
	void excluir (Representante representante);
	
	void alterar (Representante representante);
	
	Representante buscar (Long pk);
	
	List<Representante> listar();
	
	List<Representante> consultar(Representante representante);
	
	List<Representante> consultarPorDescricao(String descricao);

}