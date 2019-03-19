package br.com.eclinic.service.evento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.evento.Evento;
import br.com.eclinic.hibernate.evento.EventoRepository;

@Service(value = "eventoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public void salvar(Evento evento) {
		eventoRepository.salvar(evento);
	}

	@Override
	public Evento buscar(long id) {
		return eventoRepository.buscar(id);
	}

	@Override
	public List<Evento> listar() {
		return eventoRepository.listar();
	}

	@Override
	public List<Evento> listarHoraInicial(String horaIni) {
		return eventoRepository.listarHoraInicial(horaIni);
	}
}