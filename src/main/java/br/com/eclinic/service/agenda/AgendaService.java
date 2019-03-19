package br.com.eclinic.service.agenda;

import java.util.List;

import br.com.eclinic.entity.agenda.Agenda;

public interface AgendaService {
	
	void salvar (Agenda agenda);
	
	void alterar (Agenda agenda);
	
	void excluir (Agenda agenda);
	
	Agenda buscar (Long pk);
	
	List<Agenda> listar();

}
