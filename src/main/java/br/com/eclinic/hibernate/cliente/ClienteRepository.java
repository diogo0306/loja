package br.com.eclinic.hibernate.cliente;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
/*import br.com.eclinic.entity.endereco.Cidade;
import br.com.eclinic.entity.endereco.Estado;
import br.com.eclinic.entity.endereco.Pais;*/
import br.com.eclinic.util.JsonEndereco;

public interface ClienteRepository {

	void salvar(Cliente cliente);

	Cliente buscar(Long pk);

	void alterar(Cliente cliente);

	List<Cliente> listar();

	void excluir(Cliente cliente);

	List<Cliente> listarOrder(String atributoOrder);

	List<Cliente> consultarPorDescricao(String descricao);

	JsonEndereco consultarEnderecoPorCep(String cep);

	/**
	 * Método que lista todas as cidades por estado.
	 */
/*	List<Cidade> consultarCidadesPorEstado(String estado);
*/
	/**
	 * Método que lista todas as cidades por estado.
	 */
/*	List<Cidade> consultarCidadesPorEstado(Integer idEstado);
*/
	/**
	 * Método que lista todos os estados por país.
	 */
/*	List<Estado> consultarEstadosPorPais(Pais pais);
*/
	/**
	 * Método que lista todos os estados por país.
	 */
/*	List<Estado> consultarEstadosPorPais(Integer idPais);
*/
	/**
	 * Método que consulta todos os paises.
	 */
/*	List<Pais> consultarPaises();
*/	
/*	Pais buscarPaisPorNome(String paisTransiente);

	Estado buscarEstadoPorNome(String estadoTransiente);

	Cidade buscarCidadePorNome(String cidadeTransiente, int idEstado);
	
	Cidade buscarCidadePorId(String cidadeTransiente, int id);
*/	
	Cliente consultarCnpj (Cliente cliente);

}
