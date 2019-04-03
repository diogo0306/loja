/*package br.com.eclinic.service.cliente;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.endereco.Cidade;
import br.com.eclinic.entity.endereco.Estado;
import br.com.eclinic.entity.endereco.Pais;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.util.JsonEndereco;

public interface ClienteService {
	
	void salvar(Cliente cliente);

	void alterar(Cliente cliente);

	List<Cliente> listar();

	void excluir(Cliente cliente) throws NegocioException;

	Cliente buscar(Long pk);

	List<Cliente> consultarPorDescricao(String descricao);

	JsonEndereco consultarEnderecoPorCep(String cep);

	List<Cidade> consultarCidadesPorEstado(String estado);

	List<Cidade> consultarCidadesPorEstado(Integer idEstado);

	List<Estado> consultarEstadosPorPais(Pais pais);

	List<Estado> consultarEstadosPorPais(Integer iPais);

	List<Pais> consultarPaises();
	
	Pais buscarPaisPorNome(String paisTransiente);

	Estado buscarEstadoPorNome(String estadoTransiente);

	Cidade buscarCidadePorNome(String cidadeTransiente, int idEstado);
	
	Cidade buscarCidadePorId(String cidadeTransiente, int id);
	
	Cliente consultarCnpj (Cliente cliente);

}
*/