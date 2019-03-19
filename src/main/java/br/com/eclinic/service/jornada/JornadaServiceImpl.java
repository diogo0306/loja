package br.com.eclinic.service.jornada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.hibernate.jornada.JornadaRepository;

@Service(value = "jornadaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JornadaServiceImpl implements JornadaService {
	
	@Autowired
	JornadaRepository jornadaRepository;

	@Override
	public void salvar(Jornada jornada) {
		this.jornadaRepository.salvar(jornada);
	}

	@Override
	public Jornada buscar(Long pk) {
		return this.jornadaRepository.buscar(pk);
	}

	@Override
	public void alterar(Jornada jornada) {
		this.jornadaRepository.alterar(jornada);
	}

	@Override
	public List<Jornada> listar() {
		return this.jornadaRepository.listar();
	}

	@Override
	public void excluir(Jornada jornada) {
		this.jornadaRepository.excluir(jornada);
	}

	@Override
	public List<Jornada> pesquisarPorMedico(Long id) {
		return this.jornadaRepository.pesquisarPorMedico(id);
	}

	@Override
	public Jornada buscarPorMedico(Long id) {
		return this.jornadaRepository.buscarPorMedico(id);
	}

}
