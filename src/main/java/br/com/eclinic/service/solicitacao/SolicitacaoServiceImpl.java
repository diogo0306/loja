package br.com.eclinic.service.solicitacao;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.solicitacao.Solicitacao;
import br.com.eclinic.hibernate.solicitacao.SolicitacaoRepository;

@Service(value = "solicitacaoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SolicitacaoServiceImpl implements SolicitacaoService {
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Override
	public void salvar(Solicitacao solicitacao) {
		solicitacaoRepository.salvar(solicitacao);
		
	}

	@Override
	public void excluir(Solicitacao solicitacao) {
		solicitacaoRepository.excluir(solicitacao);
		
	}
	@Override
	public void alterar(Solicitacao solicitacao) {
		solicitacaoRepository.alterar(solicitacao);
		
	}

	@Override
	public Solicitacao buscar(Long pk) {
		return solicitacaoRepository.buscar(pk);

	}

	@Override
	public List<Solicitacao> listar() {
		return solicitacaoRepository.listar();
	}

	@Override
	public List<Solicitacao> consultar(Solicitacao solicitacao) throws ParseException {
		return solicitacaoRepository.consultar(solicitacao);
	}

	@Override
	public List<Solicitacao> listarSolicitacoesPorRepresentante(Long id) {
		return this.solicitacaoRepository.listarSolicitacoesPorRepresentante(id);
	}

}
