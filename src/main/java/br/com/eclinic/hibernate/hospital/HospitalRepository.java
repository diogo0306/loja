package br.com.eclinic.hibernate.hospital;

import java.util.List;

import br.com.eclinic.entity.hospital.Hospital;

public interface HospitalRepository {
	
	void salvar (Hospital hospital);
	
	void alterar (Hospital hospital);
	
	void excluir (Hospital hospital);
	
	Hospital buscar (Long pk);
	
	List<Hospital> listar();
	
	List<Hospital> pesquisar (Hospital hospital);

}
