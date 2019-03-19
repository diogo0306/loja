package br.com.eclinic.service.exame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.hibernate.exame.TabelaRepository;

@Service(value = "tabelaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TabelaServiceImpl implements TabelaService {
	
	@Autowired
	private TabelaRepository tabelaRepository;

	@Override
	public void salvar(Tabela tabela) {
		tabelaRepository.salvar(tabela);
	}

	@Override
	public Tabela buscar(Long pk) {
		return tabelaRepository.buscar(pk);
	}

	@Override
	public void alterar(Tabela tabela) {
		tabelaRepository.alterar(tabela);
	}

	@Override
	public List<Tabela> listar() {
		return tabelaRepository.listar();
	}

	@Override
	public void excluir(Tabela tabela) {
		tabelaRepository.excluir(tabela);
	}

	@Override
	public List<Tabela> listarOrder(String atributoOrder) {
		return tabelaRepository.listarOrder(atributoOrder);
	}

	@Override
	public List<Tabela> consultar(Tabela tabela) {
		return tabelaRepository.consultar(tabela);
	}

	@Override
	public List<Tabela> listarPorCliente(Cliente cliente) {
		return tabelaRepository.listarPorCliente(cliente);
	}

	@Override
	public Tabela verificarNomeExistente(Tabela tabela) {
		return tabelaRepository.verificarNomeExistente(tabela);
	}

}
