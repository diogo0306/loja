package br.com.eclinic.service.supervisor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.supervisor.Supervisor;
import br.com.eclinic.hibernate.supervisor.SupervisorRepository;

@Service(value = "supervisorService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SupervisorServiceImpl implements SupervisorService {
	
	@Autowired
	private SupervisorRepository supervisorRepository;

	@Override
	public void salvar(Supervisor supervisor) {
		supervisorRepository.salvar(supervisor);
	}

	@Override
	public void excluir(Supervisor supervisor) {
		supervisorRepository.excluir(supervisor);
	}

	@Override
	public void alterar(Supervisor supervisor) {
		supervisorRepository.alterar(supervisor);
	}

	@Override
	public Supervisor buscar(Long pk) {
		return supervisorRepository.buscar(pk);
	}

	@Override
	public List<Supervisor> listar() {
		return supervisorRepository.listar();
	}

	@Override
	public List<Supervisor> consultar(Supervisor supervisor) {
		return this.supervisorRepository.consultar(supervisor);
	}

}
