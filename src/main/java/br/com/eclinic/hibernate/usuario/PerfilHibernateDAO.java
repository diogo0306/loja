package br.com.eclinic.hibernate.usuario;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.Perfil;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked", "unused" })
@Repository(value = "perfilRepository")
public class PerfilHibernateDAO extends SGPGenericDAO<Perfil> implements
		PerfilRepository {

	private static final String DELETE_FUNCIONALIDADE_USUARIO = "DELETE FROM ufo_usuario_funcionalidade_operacao WHERE "
			+ "ufo_id_perfil = :idPerfil AND ufo_id_funcionalidade =:idFuncionalidade";
	private static final String LISTAR_OPERACOES = "SELECT ope_id, ope_descricao FROM ope_operacao";
	private static final String LISTAR_FUNCIONALIDADES = "SELECT fun_id, fun_descricao, fun_nome, fun_caminho, fun_secao, fun_indice_secao FROM fun_funcionalidade order by fun_id";
	private static final String DELETE_USUARIO_FUNCIONARIO_OPERACAO = "DELETE FROM ufo_usuario_funcionalidade_operacao WHERE ufo_id_perfil = :idPerfil AND ufo_id_funcionalidade =:idFuncionalidade AND ufo_id_operacao = :idOperacao";
	private static final String SELECT_OPERACOES = "SELECT ope_id, ope_descricao FROM ope_operacao WHERE ope_id = :idOperacao";
	private static final String SELECT_FUNCIONALIDADE = "SELECT fun_id, fun_descricao, fun_nome, fun_caminho, fun_secao, fun_indice_secao FROM fun_funcionalidade WHERE fun_id = :idFuncionalidade order by fun_indice_secao";
	private static final String SELEC_FUNCIONALIDADES_OPERACAO_USUARIO = "SELECT DISTINCT ufo_id_perfil, ufo_id_funcionalidade FROM ufo_usuario_funcionalidade_operacao WHERE ufo_id_perfil = :idPerfil order by ufo_id_funcionalidade";
	private static final String SELEC_FUNCIONALIDADES_OPERACAO = "SELECT ufo_id_perfil, ufo_id_funcionalidade, ufo_id_operacao FROM ufo_usuario_funcionalidade_operacao WHERE ufo_id_perfil = :idPerfil AND ufo_id_funcionalidade = :idFuncionalidade";
	private static final String INSERT_FUNCIONALIDADES_OPERACOES_USUARIO = "INSERT INTO ufo_usuario_funcionalidade_operacao "
			+ "(ufo_id_perfil, ufo_id_funcionalidade, ufo_id_operacao) VALUES (:idPerfil, :idFuncionalidade, :idOperacao)";

	@Autowired
	public PerfilHibernateDAO(
			@Qualifier("sessionFactory") SessionFactory factory) {
		super(Perfil.class);
		super.setSessionFactory(factory);
	}

	@Override
	public Perfil consultarFuncionalidadesOperacoes(Perfil perfil) {
		Session session = getSession();

		SQLQuery queryFuncionalidade = session
				.createSQLQuery("select fun_id, fun_id from fun_funcionalidade");

		List<Object[]> funcionalidadesBanco = (List<Object[]>) queryFuncionalidade
				.list();

		List<Funcionalidade> listaFuncionalidades = new ArrayList<Funcionalidade>();

		for (Object[] funcionalidadeBanco : funcionalidadesBanco) {
			BigInteger idFuncionalidade = (BigInteger) funcionalidadeBanco[1];
			Funcionalidade funcionalidade = consultarFuncionaliadePorId(
					session, idFuncionalidade);
			List<Operacao> listaOperacoes = consultarListaOperacoesPorFuncionalidade(
					perfil, session, funcionalidade);
			funcionalidade.setListaOperacoesFuncionalidade(listaOperacoes);
			listaFuncionalidades.add(funcionalidade);
		}

		perfil.setListaFuncionalidadesUsuario(listaFuncionalidades);

		return perfil;
	}

	private Funcionalidade consultarFuncionaliadePorId(Session session,
			BigInteger idFuncionalidade) {
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
			funcionalidade.setIndiceSecao(((BigInteger) object[5]).longValue());
		}

		return funcionalidade;
	}

	private List<Operacao> consultarListaOperacoesPorFuncionalidade(
			Perfil perfil, Session session, Funcionalidade funcionalidade) {
		SQLQuery queryOperacao = session
				.createSQLQuery(SELEC_FUNCIONALIDADES_OPERACAO);
		queryOperacao.setParameter("idPerfil", perfil.getId());
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

	private Operacao consultarOperacaoPorId(Session session,
			BigInteger idOperacao) {
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
	public List<Perfil> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Perfil.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Perfil>) criteria.list(),
				Perfil.class);
	}

	@Override
	public void removerFuncionalidadeOperacaoUsuario(Perfil perfil,
			Funcionalidade funcionalidade, Operacao operacao) {
		SQLQuery sqlQuery = getSession().createSQLQuery(
				DELETE_USUARIO_FUNCIONARIO_OPERACAO);
		sqlQuery.setParameter("idPerfil", perfil.getId());
		sqlQuery.setParameter("idFuncionalidade", funcionalidade.getId());
		sqlQuery.setParameter("idOperacao", operacao.getId());
		sqlQuery.executeUpdate();

	}

	@Override
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
			funcionalidade.setIndiceSecao(((BigInteger) object[5]).longValue());

			funcionalidades.add(funcionalidade);
		}

		return funcionalidades;
	}

	@Override
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

	@Override
	public void salvarFuncionalidadesOperacao(Funcionalidade funcionalidade,
			Perfil perfil) {
		
		Session session = getSession();
		session.getTransaction().begin();

		try {
			for (Operacao operacao : funcionalidade
					.getListaOperacoesFuncionalidade()) {
				SQLQuery sqlQuery = session
						.createSQLQuery(INSERT_FUNCIONALIDADES_OPERACOES_USUARIO);
				sqlQuery.setParameter("idPerfil", perfil.getId());
				sqlQuery.setParameter("idFuncionalidade",
						funcionalidade.getId());
				sqlQuery.setParameter("idOperacao", operacao.getId());
				sqlQuery.executeUpdate();
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
		}

	}

	@Override
	public void removerFuncionalidadeOperacaoUsuario(String idFuncionalidade,
			String idPerfil) {
		SQLQuery sqlQuery = getSession().createSQLQuery(
				DELETE_FUNCIONALIDADE_USUARIO);
		sqlQuery.setParameter("idPerfil", new Long(idPerfil));
		sqlQuery.setParameter("idFuncionalidade", new Long(idFuncionalidade));
		sqlQuery.executeUpdate();
	}

	@Override
	public List<Perfil> consultarPorDescricao(String descricao, Cliente cliente) {
		Criteria criteria = createCriteria(Perfil.class);
		criteria.add(Restrictions.like("descricao", descricao, MatchMode.ANYWHERE));
		if(cliente != null){
			criteria.add(Restrictions.eq("cliente.id", cliente.getId()));
		}
		return Collections.checkedList((List<Perfil>) criteria.list(),
				Perfil.class);
	}

}
