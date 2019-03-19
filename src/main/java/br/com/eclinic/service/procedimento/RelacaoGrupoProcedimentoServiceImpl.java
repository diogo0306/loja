package br.com.eclinic.service.procedimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.procedimento.RelacaoGrupoProcedimento;
import br.com.eclinic.hibernate.procedimento.RelacaoGrupoProcedimentoRepository;

@Service(value = "relacaoGrupoProcedimentoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RelacaoGrupoProcedimentoServiceImpl implements RelacaoGrupoProcedimentoService {
	
	@Autowired
	private RelacaoGrupoProcedimentoRepository relacaoGrupoProcedimentoRepository;

	@Override
	public void salvar(RelacaoGrupoProcedimento relacaoGrupoProcedimento) {
		this.relacaoGrupoProcedimentoRepository.salvar(relacaoGrupoProcedimento);
	}

	@Override
	public void alterar(RelacaoGrupoProcedimento relacaoGrupoProcedimento) {
		this.relacaoGrupoProcedimentoRepository.alterar(relacaoGrupoProcedimento);
	}

	@Override
	public void excluir(RelacaoGrupoProcedimento relacaoGrupoProcedimento) {
		this.relacaoGrupoProcedimentoRepository.excluir(relacaoGrupoProcedimento);
	}

	@Override
	public RelacaoGrupoProcedimento buscar(Long pk) {
		return this.relacaoGrupoProcedimentoRepository.buscar(pk);
	}

	@Override
	public List<RelacaoGrupoProcedimento> listar() {
		return this.relacaoGrupoProcedimentoRepository.listar();
	}

}
