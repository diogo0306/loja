package br.com.eclinic.service.paciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.hibernate.paciente.EnderecoRepository;

@Service(value = "enderecoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EnderecoServiceImpl implements EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void salvar(Endereco endereco) {
		this.enderecoRepository.salvar(endereco);
	}

	@Override
	public void alterar(Endereco endereco) {
		this.enderecoRepository.alterar(endereco);
	}

	@Override
	public Endereco buscar(Long pk) {
		return this.enderecoRepository.buscar(pk);
	}

	@Override
	public List<Endereco> listar() {
		return this.enderecoRepository.listar();
	}

}
