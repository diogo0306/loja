package br.com.eclinic.service.evento;

import java.util.List;
import br.com.eclinic.entity.evento.Evento;

public interface EventoService {
	
	void salvar(Evento evento);
	
	Evento buscar(long id);
	
	List<Evento> listar();
	
	List<Evento> listarHoraInicial(String horaIni);

}
