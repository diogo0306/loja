package br.com.eclinic.service.mensagem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.parametrizacao.Mensagem;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.mensagem.MensagemRepository;

@Service(value = "mensagemService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MensagemServiceImpl implements MensagemService{

	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Override
	public void salvar(Mensagem mensagem) {
		// TODO Auto-generated method stub
		this.mensagemRepository.salvar(mensagem);
	}

	@Override
	public void alterar(Mensagem mensagem) {
		// TODO Auto-generated method stub
		this.mensagemRepository.alterar(mensagem);
	}

	@Override
	public List<Mensagem> listar() {
		// TODO Auto-generated method stub
		return this.mensagemRepository.listar();
	}

	@Override
	public void excluir(Mensagem mensagem) throws NegocioException {
		// TODO Auto-generated method stub
		this.mensagemRepository.excluir(mensagem);
	}

	@Override
	public Mensagem buscar(Long pk) throws NegocioException {
		// TODO Auto-generated method stub
		return this.mensagemRepository.buscar(pk);
	}

}
