package br.com.eclinic.hibernate.usuario;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.TipoUsuarioEnum;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "unchecked" })
@Repository(value = "usuarioRepository")
public class UsuarioHibernateDAO extends SGPGenericDAO<Usuario> implements UsuarioRepository {

	private static final String DELETE_FUNCIONALIDADE_USUARIO = "DELETE FROM ufo_usuario_funcionalidade_operacao WHERE "
			+ "ufo_id_perfil = :idPerfil AND ufo_id_funcionalidade =:idFuncionalidade";
	private static final String LISTAR_OPERACOES = "SELECT ope_id, ope_descricao FROM ope_operacao";
	private static final String LISTAR_FUNCIONALIDADES = "SELECT fun_id, fun_descricao, fun_nome, fun_caminho, fun_secao FROM fun_funcionalidade";
	private static final String DELETE_USUARIO_FUNCIONARIO_OPERACAO = "DELETE FROM ufo_usuario_funcionalidade_operacao WHERE ufo_id_perfil = :idPerfil AND ufo_id_funcionalidade =:idFuncionalidade AND ufo_id_operacao = :idOperacao";
	private static final String SELECT_OPERACOES = "SELECT ope_id, ope_descricao FROM ope_operacao WHERE ope_id = :idOperacao";
	private static final String SELECT_FUNCIONALIDADE = "SELECT fun_id, fun_descricao, fun_nome, fun_caminho, fun_secao FROM fun_funcionalidade WHERE fun_id = :idFuncionalidade";
	private static final String SELEC_FUNCIONALIDADES_OPERACAO_USUARIO = "SELECT DISTINCT ufo_id_perfil, ufo_id_funcionalidade FROM ufo_usuario_funcionalidade_operacao WHERE ufo_id_perfil = :idPerfil";
	private static final String SELEC_FUNCIONALIDADES_OPERACAO = "SELECT ufo_id_perfil, ufo_id_funcionalidade, ufo_id_operacao FROM ufo_usuario_funcionalidade_operacao WHERE ufo_id_perfil = :idPerfil AND ufo_id_funcionalidade = :idFuncionalidade";
	private static final String INSERT_FUNCIONALIDADES_OPERACOES_USUARIO = "INSERT INTO ufo_usuario_funcionalidade_operacao "
			+ "(ufo_id_perfil, ufo_id_funcionalidade, ufo_id_operacao) VALUES (:idPerfil, :idFuncionalidade, :idOperacao)";

	@Autowired
	public UsuarioHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Usuario.class);
		super.setSessionFactory(factory);
	}

	public Usuario buscarUsuarioPorLoginSenha(Usuario usuario) {
		Criteria criteria = createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", usuario.getLogin()));
		criteria.add(Restrictions.eq("senha", usuario.getSenha()));

		return (Usuario) criteria.uniqueResult();
	}

	public Usuario consultarPorLogin(String login) {
		Criteria criteria = createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));

		return (Usuario) criteria.uniqueResult();
	}

	@Override
	public void salvarFuncionalidadesOperacao(Funcionalidade funcionalidade, Usuario usuario) {

		Session session = getSession();
		session.getTransaction().begin();

		try {
			for (Operacao operacao : funcionalidade.getListaOperacoesFuncionalidade()) {
				SQLQuery sqlQuery = session.createSQLQuery(INSERT_FUNCIONALIDADES_OPERACOES_USUARIO);
				sqlQuery.setParameter("idPerfil", usuario.getPerfil().getId());
				sqlQuery.setParameter("idFuncionalidade", funcionalidade.getId());
				sqlQuery.setParameter("idOperacao", operacao.getId());
				sqlQuery.executeUpdate();
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
		}

	}

	@Override
	public Usuario consultarFuncionalidadesOperacoes(Usuario usuario) {
		Session session = getSession();

		SQLQuery queryFuncionalidade = session.createSQLQuery(SELEC_FUNCIONALIDADES_OPERACAO_USUARIO);
		queryFuncionalidade.setParameter("perfil.id", usuario.getId());

		List<Object[]> funcionalidadesBanco = (List<Object[]>) queryFuncionalidade.list();

		List<Funcionalidade> listaFuncionalidades = new ArrayList<Funcionalidade>();

		for (Object[] funcionalidadeBanco : funcionalidadesBanco) {
			BigInteger idFuncionalidade = (BigInteger) funcionalidadeBanco[1];
			Funcionalidade funcionalidade = consultarFuncionaliadePorId(session, idFuncionalidade);
			List<Operacao> listaOperacoes = consultarListaOperacoesPorFuncionalidade(usuario, session, funcionalidade);
			funcionalidade.setListaOperacoesFuncionalidade(listaOperacoes);
			listaFuncionalidades.add(funcionalidade);
		}

		usuario.getPerfil().setListaFuncionalidadesUsuario(listaFuncionalidades);

		return usuario;
	}

	private List<Operacao> consultarListaOperacoesPorFuncionalidade(Usuario usuario, Session session,
			Funcionalidade funcionalidade) {
		SQLQuery queryOperacao = session.createSQLQuery(SELEC_FUNCIONALIDADES_OPERACAO);
		queryOperacao.setParameter("idPerfil", usuario.getPerfil().getId());
		queryOperacao.setParameter("idFuncionalidade", funcionalidade.getId());
		List<Object[]> operacoesBanco = (List<Object[]>) queryOperacao.list();
		List<Operacao> listaOperacoes = new ArrayList<Operacao>();
		for (Object[] operacaoBanco : operacoesBanco) {
			BigInteger idOperacao = (BigInteger) operacaoBanco[2];
			Operacao operacao = consultarOperacaoPorId(session, idOperacao);
			listaOperacoes.add(operacao);
		}
		return listaOperacoes;
	}

	private Funcionalidade consultarFuncionaliadePorId(Session session, BigInteger idFuncionalidade) {
		SQLQuery sqlQuery = session.createSQLQuery(SELECT_FUNCIONALIDADE);
		sqlQuery.setParameter("idFuncionalidade", idFuncionalidade.longValue());

		List<Object[]> objects = (List<Object[]>) sqlQuery.list();
		Funcionalidade funcionalidade = new Funcionalidade();

		for (Object[] object : objects) {

			funcionalidade.setId(((BigInteger) object[0]).longValue());
			funcionalidade.setDescricao((String) object[1]);
			funcionalidade.setNome((String) object[2]);
			funcionalidade.setCaminho((String) object[3]);
			funcionalidade.setSecao((String) object[4]);
		}

		return funcionalidade;
	}

	public List<Funcionalidade> listarFuncionalidades() {
		SQLQuery sqlQuery = getSession().createSQLQuery(LISTAR_FUNCIONALIDADES);

		List<Object[]> objects = (List<Object[]>) sqlQuery.list();

		List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();
		for (Object[] object : objects) {

			Funcionalidade funcionalidade = new Funcionalidade();
			funcionalidade.setId(((BigInteger) object[0]).longValue());
			funcionalidade.setDescricao((String) object[1]);
			funcionalidade.setNome((String) object[2]);
			funcionalidade.setCaminho((String) object[3]);
			funcionalidade.setSecao((String) object[4]);

			funcionalidades.add(funcionalidade);
		}

		return funcionalidades;
	}

	public List<Operacao> listarOperacoes() {
		SQLQuery sqlQuery = getSession().createSQLQuery(LISTAR_OPERACOES);

		List<Object[]> objects = (List<Object[]>) sqlQuery.list();

		List<Operacao> operacoes = new ArrayList<Operacao>();
		for (Object[] object : objects) {

			Operacao operacao = new Operacao();
			operacao.setId(((BigInteger) object[0]).longValue());
			operacao.setDescricao((String) object[1]);

			operacoes.add(operacao);
		}

		return operacoes;
	}

	private Operacao consultarOperacaoPorId(Session session, BigInteger idOperacao) {
		SQLQuery sqlQuery = session.createSQLQuery(SELECT_OPERACOES);
		sqlQuery.setParameter("idOperacao", idOperacao.longValue());

		List<Object[]> objects = (List<Object[]>) sqlQuery.list();
		Operacao operacao = new Operacao();

		for (Object[] object : objects) {

			operacao.setId(((BigInteger) object[0]).longValue());
			operacao.setDescricao((String) object[1]);
		}

		return operacao;
	}

	@Override
	public List<Usuario> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Usuario.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Usuario>) criteria.list(), Usuario.class);
	}

	@Override
	public void removerFuncionalidadeOperacaoUsuario(Usuario usuario, Funcionalidade funcionalidade,
			Operacao operacao) {
		SQLQuery sqlQuery = getSession().createSQLQuery(DELETE_USUARIO_FUNCIONARIO_OPERACAO);
		sqlQuery.setParameter("idPerfil", usuario.getPerfil().getId());
		sqlQuery.setParameter("idFuncionalidade", funcionalidade.getId());
		sqlQuery.setParameter("idOperacao", operacao.getId());
		sqlQuery.executeUpdate();
	}

	@Override
	public List<Usuario> consultarPorLogin(String login, Cliente cliente) {

		SQLQuery queryConsultarPorLogin = getSession().createSQLQuery(
				"SELECT DISTINCT usu_id, usu_login, usu_email, usu_telefone, usu_tipo, usu_nome FROM usu_usuario WHERE usu_login like :login AND cli_id = :idCliente");
		queryConsultarPorLogin.setParameter("login", "%" + login + "%");
		queryConsultarPorLogin.setParameter("idCliente", cliente.getId());

		List<Object[]> usuariosBanco = (List<Object[]>) queryConsultarPorLogin.list();
		List<Usuario> usuarios = new ArrayList<Usuario>();

		for (Object[] usuarioBanco : usuariosBanco) {

			Usuario usuario = new Usuario();
			usuario.setId(((BigInteger) usuarioBanco[0]).longValue());
			usuario.setLogin((String) usuarioBanco[1]);
			usuario.setEmail((String) usuarioBanco[2]);
			usuario.setTelefone((String) usuarioBanco[3]);
			usuario.setNome((String) usuarioBanco[5]);
			if (TipoUsuarioEnum.ADMIN.getDescricao().equals(((String) usuarioBanco[4]))) {
				usuario.setTipoUsuario(TipoUsuarioEnum.ADMIN);
			} else {
				usuario.setTipoUsuario(TipoUsuarioEnum.OPERACIONAL);
			}

			usuarios.add(usuario);
		}

		return usuarios;
	}

	@Override
	public List<Usuario> listarPorCliente(Cliente cliente) {
		SQLQuery queryConsultarPorLogin = getSession().createSQLQuery(
				"SELECT DISTINCT usu_id, usu_login, usu_email, usu_telefone,usu_tipo, usu_nome FROM usu_usuario WHERE cli_id = :idCliente ");
		queryConsultarPorLogin.setParameter("idCliente", cliente.getId());

		List<Object[]> usuariosBanco = (List<Object[]>) queryConsultarPorLogin.list();
		List<Usuario> usuarios = new ArrayList<Usuario>();

		for (Object[] usuarioBanco : usuariosBanco) {

			Usuario usuario = new Usuario();
			usuario.setId(((BigInteger) usuarioBanco[0]).longValue());
			usuario.setLogin((String) usuarioBanco[1]);
			usuario.setEmail((String) usuarioBanco[2]);
			usuario.setTelefone((String) usuarioBanco[3]);
			usuario.setNome((String) usuarioBanco[5]);
			if (TipoUsuarioEnum.ADMIN.getDescricao().equals(((String) usuarioBanco[4]))) {
				usuario.setTipoUsuario(TipoUsuarioEnum.ADMIN);
			} else {
				usuario.setTipoUsuario(TipoUsuarioEnum.OPERACIONAL);
			}

			usuarios.add(usuario);
		}

		return usuarios;
	}

	@Override
	public String consultarSenha(Long idUsuario) {
		Criteria criteria = createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("id", idUsuario));
		criteria.setProjection(Projections.property("senha"));
		return (String) criteria.uniqueResult();
	}

	@Override
	public Date consultarDataValidadeSenha(Long idUsuario) {
		Criteria criteria = createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("id", idUsuario));
		criteria.setProjection(Projections.property("dataValidadeSenha"));
		return (Date) criteria.uniqueResult();
	}

	@Override
	public void removerFuncionalidadeOperacaoUsuario(String idFuncionalidade, String idUsuario) {

		SQLQuery sqlQuery = getSession().createSQLQuery(DELETE_FUNCIONALIDADE_USUARIO);
		sqlQuery.setParameter("idPErfil", new Long(idUsuario));
		sqlQuery.setParameter("idFuncionalidade", new Long(idFuncionalidade));
		sqlQuery.executeUpdate();
	}

	// Métodos oauth

	@Override
	public Usuario findByCpf(String cpf) {
		Criteria criteria = createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("cpf", cpf));
		return (Usuario) criteria.uniqueResult();
	}

	@Override
	public boolean existsUsersByCpf(String cpf) {
		// TODO Auto-generated method stub
		return false;
	}

	// API
	@Override
	public Usuario buscarUsuarioPorCpfSenha(Usuario usuario) {
		Criteria criteria = createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("cpf", usuario.getCpf()));
		criteria.add(Restrictions.eq("senha", usuario.getSenha()));

		return (Usuario) criteria.uniqueResult();
	}

	@Override
	public List<Usuario> consultar(Usuario usuario) {
		Criteria criteria = createCriteria(Usuario.class);
		if (StringUtils.isNotBlank(usuario.getNome())) {
			criteria.add(Restrictions.like("nome", usuario.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Usuario>) criteria.list(), Usuario.class);
	}

}
