package br.com.eclinic.service.dia;

import java.util.List;

import br.com.eclinic.entity.parametrizacao.Dia;

public interface DiaService {
	
	void salvar (Dia dia);
	
	void excluir (Dia dia);
	
	void alterar (Dia dia);
	
	Dia buscar (Long pk);
	
	List<Dia> listar();

}
