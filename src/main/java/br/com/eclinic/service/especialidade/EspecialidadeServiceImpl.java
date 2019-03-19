package br.com.eclinic.service.especialidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.hibernate.especialidade.EspecialidadeRepository;

@Service(value = "especialidadeService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EspecialidadeServiceImpl implements EspecialidadeService {
	
	@Autowired
	EspecialidadeRepository especialidadeRepository;

	@Override
	public void salvar(Especialidade especialidade) {
		this.especialidadeRepository.salvar(especialidade);
	}

	@Override
	public List<Especialidade> listar() {
		return this.especialidadeRepository.listar();
	}

	@Override
	public Especialidade buscar(Long pk) {
		return this.especialidadeRepository.buscar(pk);
	}

	@Override
	public void alterar(Especialidade especialidade) {
		especialidadeRepository.alterar(especialidade);		
	}
}