/*package br.com.eclinic.hibernate.cliente;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.endereco.Cidade;
import br.com.eclinic.entity.endereco.Estado;
import br.com.eclinic.entity.endereco.Pais;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;
import br.com.eclinic.util.EnderecoCorreios;
import br.com.eclinic.util.JsonEndereco;

@SuppressWarnings({"unchecked" })
@Repository(value = "clienteRepository")
public class ClienteHibernateDAO extends SGPGenericDAO<Cliente> implements ClienteRepository{
	
	*//**
	 * Construtor padrão.
	 * 
	 *//*
	@Autowired
	public ClienteHibernateDAO(
			@Qualifier("sessionFactory") SessionFactory factory) {
		super(Cliente.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Cliente> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Cliente.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Cliente>) criteria.list(),
				Cliente.class);
	}

	@Override
	public List<Cliente> consultarPorDescricao(String descricao) {
		Criteria criteria = createCriteria(Cliente.class);
		criteria.add(Restrictions.like("nome", descricao, MatchMode.ANYWHERE).ignoreCase());
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Cliente>) criteria.list(),
				Cliente.class);
	}

	@Override
	public JsonEndereco consultarEnderecoPorCep(String cep) {
		Criteria criteria = createCriteria(EnderecoCorreios.class);
		criteria.add(Restrictions.eq("cep", cep));

		EnderecoCorreios endereco = (EnderecoCorreios) criteria.uniqueResult();

		JsonEndereco jsonEndereco = new JsonEndereco();
		if (endereco != null) {
			jsonEndereco.setBairro(endereco.getBairro());
			jsonEndereco.setCep(endereco.getCep());
			jsonEndereco.setCidade(endereco.getCidade());
			jsonEndereco.setEstado(endereco.getEstado());
			jsonEndereco.setRua(endereco.getLogradouro());
		}

		return jsonEndereco;
	}

	*//**
	 * Consulta todas as cidades de um determinado estado.
	 * 
	 * @param siglaEstado
	 *            o estado a ser usado no filtro.
	 *//*
	public List<Cidade> consultarCidadesPorEstado(String siglaEstado) {
		Criteria criteria = createCriteria(Cidade.class);
		criteria.createAlias("estado", "e");
		criteria.add(Restrictions.eq("e.siglaEstado", siglaEstado));

		return Collections.checkedList((List<Cidade>) criteria.list(),
				Cidade.class);
	}
	
	*//**
	 * Consulta todas as cidades de um determinado estado.
	 * 
	 * @param siglaEstado
	 *            o estado a ser usado no filtro.
	 *//*
	public List<Cidade> consultarCidadesPorEstado(Integer idEstado) {
		Criteria criteria = createCriteria(Cidade.class);
		criteria.createAlias("estado", "e");
		criteria.add(Restrictions.eq("e.id", idEstado));

		return Collections.checkedList((List<Cidade>) criteria.list(),
				Cidade.class);
	}
	
	*//**
	 * Consulta todos os estados por pais.
	 * 
	 * @param idPais
	 *            o país a ser usado no filtro.
	 *//*
	public List<Estado> consultarEstadosPorPais(Integer idPais) {
		Criteria criteria = createCriteria(Estado.class);
		criteria.add(Restrictions.eq("pais.id", idPais));
		return Collections.checkedList((List<Estado>) criteria.list(),
				Estado.class);
	}

	*//**
	 * Consulta todos os estados por pais.
	 * 
	 * @param pais
	 *            o país a ser usado no filtro.
	 *//*
	public List<Estado> consultarEstadosPorPais(Pais pais) {
		Criteria criteria = createCriteria(Estado.class);
		criteria.add(Restrictions.eq("pais", pais));
		return Collections.checkedList((List<Estado>) criteria.list(),
				Estado.class);
	}

	*//**
	 * Consulta todos os pa�ses.
	 * 
	 *//*
	public List<Pais> consultarPaises() {
		Criteria criteria = createCriteria(Pais.class);
		criteria.add(Restrictions.isNotNull("siglaPais"));
		return Collections
				.checkedList((List<Pais>) criteria.list(), Pais.class);
	}

	@Override
	public Pais buscarPaisPorNome(String paisTransiente) {
		Criteria criteria = createCriteria(Pais.class);
		criteria.add(Restrictions.eq("nomePais", paisTransiente));

		return (Pais) criteria.uniqueResult();
	}

	@Override
	public Estado buscarEstadoPorNome(String estadoTransiente) {
		Criteria criteria = createCriteria(Estado.class);
		criteria.add(Restrictions.eq("siglaEstado", estadoTransiente));
		return (Estado) criteria.uniqueResult();
	}

	@Override
	public Cidade buscarCidadePorNome(String cidadeTransiente, int idEstado) {
		Criteria criteria = createCriteria(Cidade.class);
		criteria.add(Restrictions.eq("nomeCidade", cidadeTransiente));
		criteria.createAlias("estado", "e");
		criteria.add(Restrictions.eq("e.id", idEstado));

		return (Cidade) criteria.uniqueResult();
	}


	@Override
	public Cidade buscarCidadePorId(String cidadeTransiente, int id) {
		Criteria criteria = createCriteria(Cidade.class);
		criteria.add(Restrictions.eq("id", Integer.parseInt(cidadeTransiente)));
		criteria.createAlias("estado", "e");
		criteria.add(Restrictions.eq("e.id", id));

		return (Cidade) criteria.uniqueResult();
	}

	@Override
	public Cliente consultarCnpj (Cliente cliente) {
		Criteria criteria = createCriteria(Cliente.class);
		criteria.add(Restrictions.eq("cnpj", cliente.getCnpj()));
		return (Cliente) criteria.uniqueResult();
	}


}
*/