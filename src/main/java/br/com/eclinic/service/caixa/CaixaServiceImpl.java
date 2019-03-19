package br.com.eclinic.service.caixa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.caixa.Caixa;
import br.com.eclinic.hibernate.caixa.CaixaRepository;

@Service(value = "caixaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CaixaServiceImpl implements CaixaService {
	
	@Autowired
	private CaixaRepository caixaRepository;

	@Override
	public void salvar(Caixa caixa) {
		this.caixaRepository.salvar(caixa);
	}

	@Override
	public void alterar(Caixa caixa) {
		this.caixaRepository.alterar(caixa);
	}

	@Override
	public void excluir(Caixa caixa) {
		this.caixaRepository.excluir(caixa);
	}

	@Override
	public Caixa buscar(Long pk) {
		return this.caixaRepository.buscar(pk);
	}

	@Override
	public List<Caixa> listar() {
		return this.caixaRepository.listar();
	}

}
