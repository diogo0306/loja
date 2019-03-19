package br.com.eclinic.service.procedimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.procedimento.Procedimento;
import br.com.eclinic.hibernate.procedimento.ProcedimentoRepository;

@Service(value = "procedimentoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProcedimentoServiceImpl implements ProcedimentoService {

	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Override
	public void salvar(Procedimento procedimento) {
		this.procedimentoRepository.salvar(procedimento);
	}

	@Override
	public void alterar(Procedimento procedimento) {
		this.procedimentoRepository.alterar(procedimento);
	}

	@Override
	public void excluir(Procedimento procedimento) {
		this.procedimentoRepository.excluir(procedimento);
	}

	@Override
	public Procedimento buscar(Long pk) {
		return this.procedimentoRepository.buscar(pk);
	}

	@Override
	public List<Procedimento> listar() {
		return this.procedimentoRepository.listar();
	}

}
