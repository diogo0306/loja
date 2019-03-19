package br.com.eclinic.service.supervisor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.supervisor.Responsavel;
import br.com.eclinic.hibernate.supervisor.ResponsavelRepository;


@Service(value = "responsavelService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ResponsavelServiceImpl implements ResponsavelService {
	
	@Autowired
	private ResponsavelRepository responsavelRepository;

	@Override
	public void salvar(Responsavel responsavel) {
		responsavelRepository.salvar(responsavel);
		
	}

	@Override
	public void excluir(Responsavel responsavel) {
		responsavelRepository.excluir(responsavel);
		
	}
	@Override
	public void alterar(Responsavel responsavel) {
		responsavelRepository.alterar(responsavel);
		
	}

	@Override
	public Responsavel buscar(Long pk) {
		return responsavelRepository.buscar(pk);

	}

	@Override
	public List<Responsavel> listar() {
		return responsavelRepository.listar();
	}

	@Override
	public List<Responsavel> pesquisarPorMedico(Long id) {
		return this.responsavelRepository.pesquisarPorMedico(id);
	}

}
