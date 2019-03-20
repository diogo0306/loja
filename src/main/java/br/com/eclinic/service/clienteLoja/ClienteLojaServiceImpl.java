package br.com.eclinic.service.clienteLoja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.clienteLoja.ClienteLoja;
import br.com.eclinic.hibernate.clienteLoja.ClienteLojaRepository;

@Service(value = "clienteLojaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ClienteLojaServiceImpl implements ClienteLojaService {

	@Autowired
	private ClienteLojaRepository clienteLojaRepository;

	@Override
	public void salvar(ClienteLoja clienteLoja) {
		this.clienteLojaRepository.salvar(clienteLoja);

	}

	@Override
	public void alterar(ClienteLoja clienteLoja) {
		this.clienteLojaRepository.alterar(clienteLoja);

	}

	@Override
	public void excluir(ClienteLoja clienteLoja) {
		this.clienteLojaRepository.excluir(clienteLoja);

	}

	@Override
	public ClienteLoja buscar(Long pk) {
		return this.clienteLojaRepository.buscar(pk);
	}

	@Override
	public List<ClienteLoja> listar() {
		return this.clienteLojaRepository.listar();
	}

	@Override
	public List<ClienteLoja> consultar(ClienteLoja clienteLoja) {
		return this.clienteLojaRepository.consultar(clienteLoja);
	}

}
