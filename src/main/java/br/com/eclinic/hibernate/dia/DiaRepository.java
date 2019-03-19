package br.com.eclinic.hibernate.dia;

import java.util.List;

import br.com.eclinic.entity.parametrizacao.Dia;

public interface DiaRepository {
	
	void salvar (Dia dia);
	
	void excluir (Dia dia);
	
	void alterar (Dia dia);
	
	Dia buscar (Long pk);
	
	List<Dia> listar();

}
