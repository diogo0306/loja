package br.com.eclinic.hibernate.agenda;

import java.util.List;

import br.com.eclinic.entity.agenda.Agenda;

public interface AgendaRepository {
	
	void salvar (Agenda agenda);
	
	void alterar (Agenda agenda);
	
	void excluir (Agenda agenda);
	
	Agenda buscar (Long pk);
	
	List<Agenda> listar();

}
