package br.com.eclinic.service.acomodacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.eclinic.entity.acomodacao.Acomodacao;
import br.com.eclinic.hibernate.acomodacao.AcomodacaoRepository;

@Service(value = "acomodacaoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AcomodacaoServiceImpl implements AcomodacaoService {
	
	@Autowired
	private AcomodacaoRepository acomodacaoRepository;

	@Override
	public void salvar(Acomodacao acomodacao) {
		this.acomodacaoRepository.salvar(acomodacao);
	}

	@Override
	public void alterar(Acomodacao acomodacao) {
		this.acomodacaoRepository.alterar(acomodacao);
	}

	@Override
	public void excluir(Acomodacao acomodacao) {
		this.acomodacaoRepository.excluir(acomodacao);
	}

	@Override
	public Acomodacao buscar(Long pk) {
		return this.acomodacaoRepository.buscar(pk);
	}

	@Override
	public List<Acomodacao> listar() {
		return this.acomodacaoRepository.listar();
	}

}
