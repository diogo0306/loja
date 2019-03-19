package br.com.eclinic.service.solicitacao;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.solicitacao.SolicitacaoExame;
import br.com.eclinic.hibernate.solicitacao.SolicitacaoExameRepository;

@Service(value = "solicitacaoExameService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SolicitacaoExameServiceImpl implements SolicitacaoExameService {
	
	@Autowired
	private SolicitacaoExameRepository solicitacaoExameRepository;

	@Override
	public void salvar(SolicitacaoExame solicitacaoExame) {
		this.solicitacaoExameRepository.salvar(solicitacaoExame);
	}

	@Override
	public void excluir(SolicitacaoExame solicitacaoExame) {
		this.solicitacaoExameRepository.excluir(solicitacaoExame);
	}

	@Override
	public void alterar(SolicitacaoExame solicitacaoExame) {
		this.solicitacaoExameRepository.alterar(solicitacaoExame);
	}

	@Override
	public SolicitacaoExame buscar(Long pk) {
		return this.solicitacaoExameRepository.buscar(pk);
	}

	@Override
	public List<SolicitacaoExame> listar() {
		return this.solicitacaoExameRepository.listar();
	}

	@Override
	public List<SolicitacaoExame> consultar(SolicitacaoExame solicitacaoExame) throws ParseException {
		return this.solicitacaoExameRepository.consultar(solicitacaoExame);
	}

}
