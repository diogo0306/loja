package br.com.eclinic.service.documentacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.documentacao.Documentacao;
import br.com.eclinic.hibernate.documentacao.DocumentacaoRepository;

@Service(value = "documentacaoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DocumentacaoServiceImpl implements DocumentacaoService {
	
	@Autowired
	private DocumentacaoRepository documentacaoRepository;

	@Override
	public void salvar(Documentacao documentacao) {
		this.documentacaoRepository.salvar(documentacao);
	}

	@Override
	public void alterar(Documentacao documentacao) {
		this.documentacaoRepository.alterar(documentacao);
	}

	@Override
	public Documentacao buscar(Long pk) {
		return this.documentacaoRepository.buscar(pk);
	}

	@Override
	public List<Documentacao> listar() {
		return this.documentacaoRepository.listar();
	}

}
