package br.com.eclinic.service.dependente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.hibernate.dependente.DependenteRepository;

@Service(value = "dependenteService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DependenteServiceImpl implements DependenteService {

	@Autowired
	private DependenteRepository dependenteRepository;

	@Override
	public void salvar(Dependente dependente) {
		dependenteRepository.salvar(dependente);
	}

	@Override
	public void excluir(Dependente dependente) {
		dependenteRepository.excluir(dependente);
	}

	@Override
	public void alterar(Dependente dependente) {
		dependenteRepository.alterar(dependente);
	}

	@Override
	public Dependente buscar(Long pk) {
		return this.dependenteRepository.buscar(pk);
	}

	@Override
	public List<Dependente> listar() {
		return this.dependenteRepository.listar();
	}

	@Override
	public List<Dependente> listarPorPaciente(Long id) {
		return this.dependenteRepository.listarPorPaciente(id);
	}

}
