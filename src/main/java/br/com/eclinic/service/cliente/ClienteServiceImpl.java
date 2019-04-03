/*package br.com.eclinic.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.endereco.Cidade;
import br.com.eclinic.entity.endereco.Estado;
import br.com.eclinic.entity.endereco.Pais;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.cliente.ClienteRepository;
import br.com.eclinic.util.JsonEndereco;

@Service(value = "clienteService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ClienteServiceImpl implements ClienteService{

	private static final String CLIENTE_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS = "Cliente não existe mais na base de dados";
	private ClienteRepository clienteRepository;
	
	@Override
	public void salvar(Cliente cliente) {
		clienteRepository.salvar(cliente);
		
	}

	@Override
	public void alterar(Cliente cliente) {
		clienteRepository.alterar(cliente);
		
	}

	@Override
	public List<Cliente> listar() {
		return clienteRepository.listar();
	}

	@Override
	public void excluir(Cliente cliente) throws NegocioException {
		Cliente clienteBase = buscar(cliente.getId());
		if (clienteBase == null) {
			throw new NegocioException(CLIENTE_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS);
		}
		clienteRepository.excluir(clienteBase);
		
	}

	@Override
	public Cliente buscar(Long pk) {
		return clienteRepository.buscar(pk);
	}

	@Override
	public List<Cliente> consultarPorDescricao(String descricao) {
		return clienteRepository.consultarPorDescricao(descricao);
	}
	
	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	@Autowired
	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public JsonEndereco consultarEnderecoPorCep(String cep) {
		return clienteRepository.consultarEnderecoPorCep(cep);
	}

	*//**
	 * Consulta todas as cidades por estado.
	 * 
	 * @param siglaEstado
	 *            o estado a ser usado como filtro.
	 *//*
	public List<Cidade> consultarCidadesPorEstado(String siglaEstado) {
		return this.clienteRepository.consultarCidadesPorEstado(siglaEstado);
	}
	
	*//**
	 * Consulta todas as cidades por estado.
	 * 
	 * @param idEstado
	 *            o estado a ser usado como filtro.
	 *//*
	public List<Cidade> consultarCidadesPorEstado(Integer idEstado) {
		return this.clienteRepository.consultarCidadesPorEstado(idEstado);
	}

	*//**
	 * Consulta todos os estados por país.
	 * 
	 * @param pais
	 *            o país usado como filtro.
	 *//*
	public List<Estado> consultarEstadosPorPais(Pais pais) {
		return this.clienteRepository.consultarEstadosPorPais(pais);
	}
	
	*//**
	 * Consulta todos os estados por país.
	 * 
	 * @param pais
	 *            o país usado como filtro.
	 *//*
	public List<Estado> consultarEstadosPorPais(Integer idPais) {
		return this.clienteRepository.consultarEstadosPorPais(idPais);
	}

	*//**
	 * Consulta todos países no banco de dados.
	 * 
	 *//*
	public List<Pais> consultarPaises() {
		return this.clienteRepository.consultarPaises();
	}
	
	@Override
	public Pais buscarPaisPorNome(String paisTransiente) {
		return this.clienteRepository.buscarPaisPorNome(paisTransiente);
	}

	@Override
	public Estado buscarEstadoPorNome(String estadoTransiente) {
		return this.clienteRepository.buscarEstadoPorNome(estadoTransiente);
	}

	@Override
	public Cidade buscarCidadePorNome(String cidadeTransiente, int idEstado) {
		return this.clienteRepository.buscarCidadePorNome(cidadeTransiente,idEstado);
	}
	

	@Override
	public Cidade buscarCidadePorId(String cidadeTransiente, int id) {
		return this.clienteRepository.buscarCidadePorId(cidadeTransiente, id);
	}

	@Override
	public Cliente consultarCnpj(Cliente cliente) {
		return this.clienteRepository.consultarCnpj(cliente);
	}

}
*/