package br.com.eclinic.service.agenda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.agenda.Agenda;
import br.com.eclinic.hibernate.agenda.AgendaRepository;

@Service(value = "agendaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AgendaServiceImpl implements AgendaService {
	
	@Autowired
	private AgendaRepository agendaRepository; 

	@Override
	public void salvar(Agenda agenda) {
		this.agendaRepository.salvar(agenda);
	}

	@Override
	public void alterar(Agenda agenda) {
		this.agendaRepository.alterar(agenda);
	}

	@Override
	public void excluir(Agenda agenda) {
		this.agendaRepository.excluir(agenda);
	}

	@Override
	public Agenda buscar(Long pk) {
		return this.agendaRepository.buscar(pk);
	}

	@Override
	public List<Agenda> listar() {
		return this.agendaRepository.listar();
	}

}
