package br.com.eclinic.service.parametrizacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.entity.parametrizacao.Parametrizacao;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.parametrizacao.ParametrizacaoRepository;

@Service(value = "parametrizacaoService")
public class ParametrizacaoServiceImpl implements ParametrizacaoService{

	@Autowired
	private ParametrizacaoRepository parametrizacaoRepository;
	
	@Override
	public void salvar(Parametrizacao parametrizacao) {
		// TODO Auto-generated method stub
		this.parametrizacaoRepository.salvar(parametrizacao);
	}

	@Override
	public void alterar(Parametrizacao parametrizacao) {
		// TODO Auto-generated method stub
		this.parametrizacaoRepository.alterar(parametrizacao);
	}

	@Override
	public List<Parametrizacao> listar() {
		// TODO Auto-generated method stub
		return this.parametrizacaoRepository.listar();
	}

	@Override
	public void excluir(Parametrizacao parametrizacao) throws NegocioException {
		// TODO Auto-generated method stub
		this.parametrizacaoRepository.excluir(parametrizacao);
	}

	@Override
	public Parametrizacao buscar(Long pk) throws NegocioException {
		// TODO Auto-generated method stub
		return this.parametrizacaoRepository.buscar(pk);
	}

	@Override
	public List<Parametrizacao> listarPorEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		return this.parametrizacaoRepository.listarPorEmpresa(empresa);
	}

	@Override
	public List<Parametrizacao> listarPorCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return this.parametrizacaoRepository.listarPorCliente(cliente);
	}

	@Override
	public List<Parametrizacao> consultar(Parametrizacao parametrizacao) {
		// TODO Auto-generated method stub
		return this.parametrizacaoRepository.consultar(parametrizacao);
	}

}
