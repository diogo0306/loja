package br.com.eclinic.service.indice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.indice.IndiceEconomico;
import br.com.eclinic.hibernate.indice.IndiceEconomicoRepository;

@Service(value = "indiceEconomicoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IndiceEconomicoServiceImpl implements IndiceEconomicoService {
	
	@Autowired
	private IndiceEconomicoRepository indiceEconomicoRepository;

	@Override
	public void salvar(IndiceEconomico indice) {
		this.indiceEconomicoRepository.salvar(indice);
	}

	@Override
	public void alterar(IndiceEconomico indice) {
		this.indiceEconomicoRepository.alterar(indice);
		
	}

	@Override
	public void excluir(IndiceEconomico indice) {
		this.indiceEconomicoRepository.excluir(indice);
		
	}

	@Override
	public IndiceEconomico buscar(Long pk) {
		
		return this.indiceEconomicoRepository.buscar(pk);
	}

	@Override
	public List<IndiceEconomico> listar() {
		
		return this.indiceEconomicoRepository.listar();
	}

	@Override
	public List<IndiceEconomico> consultar(IndiceEconomico indiceEconomico) {
		// TODO Auto-generated method stub
		return indiceEconomicoRepository.consultar(indiceEconomico);
	}

}
