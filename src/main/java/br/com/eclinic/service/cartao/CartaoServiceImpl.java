package br.com.eclinic.service.cartao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.cartao.Cartao;
import br.com.eclinic.hibernate.cartao.CartaoRepository;

@Service(value = "cartaoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

public class CartaoServiceImpl implements CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;

	@Override
	public void salvar(Cartao cartao) {
		this.cartaoRepository.salvar(cartao);
	}

	@Override
	public void alterar(Cartao cartao) {
		this.cartaoRepository.alterar(cartao);
	}

	@Override
	public void excluir(Cartao cartao) {
		this.cartaoRepository.excluir(cartao);
	}

	@Override
	public Cartao buscar(Long pk) {
		return this.cartaoRepository.buscar(pk);
	}

	@Override
	public List<Cartao> listar() {
		return this.cartaoRepository.listar();
	}

}
