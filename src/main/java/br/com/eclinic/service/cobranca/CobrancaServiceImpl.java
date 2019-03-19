package br.com.eclinic.service.cobranca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.paciente.Cobranca;
import br.com.eclinic.hibernate.cobranca.CobrancaRepository;

@Service(value = "cobrancaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CobrancaServiceImpl implements CobrancaService {
	
	@Autowired
	private CobrancaRepository cobrancaRepository;

	@Override
	public void salvar(Cobranca cobranca) {
		this.cobrancaRepository.salvar(cobranca);
	}

	@Override
	public Cobranca buscar(Long pk) {
		return this.cobrancaRepository.buscar(pk);
	}

	@Override
	public void alterar(Cobranca cobranca) {
		this.cobrancaRepository.alterar(cobranca);
	}

	@Override
	public void excluir(Cobranca cobranca) {
		this.cobrancaRepository.excluir(cobranca);
	}

	@Override
	public List<Cobranca> listar() {
		return this.cobrancaRepository.listar();
	}

}
