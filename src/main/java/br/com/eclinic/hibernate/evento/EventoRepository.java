package br.com.eclinic.hibernate.evento;

import java.util.List;
import br.com.eclinic.entity.evento.Evento;

public interface EventoRepository {
	
	void salvar(Evento evento);
	
	Evento buscar(long id);
	
	List<Evento> listar();
	
	List<Evento> listarHoraInicial(String horaIni);
	

}
