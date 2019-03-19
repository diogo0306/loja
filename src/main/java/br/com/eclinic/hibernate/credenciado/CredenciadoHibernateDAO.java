package br.com.eclinic.hibernate.credenciado;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "unchecked" })
@Repository(value = "credenciadoRepository")
public class CredenciadoHibernateDAO extends SGPGenericDAO<Credenciado> implements CredenciadoRepository {
	
	private static final String SELEC_ESPECIALIDADE_CREDENCIADO = "SELECT DISTINCT cre_id, espec_id FROM especialidades_credenciado WHERE cre_id = :idCredenciado";
	private static final String SELEC_ESPECIALIDADE = "SELECT DISTINCT espec_id, espec_descricao FROM especialidade WHERE espec_id = :idEspecialidade";

	private static final String SELECT_ESPECIALIDADE_CREDENCIADOS = "SELECT DISTINCT cre_id, espec_id FROM especialidades_credenciado WHERE espec_id = :idEspecialidade";
	private static final String SELECT_CREDENCIADO = "SELECT DISTINCT cre_id, cre_nome, cre_cep, cre_cidade, cre_logradouro, cre_valor_cobrado FROM credenciado WHERE cre_id = :idCredenciado";
	
	@Autowired
	public CredenciadoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Credenciado.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Credenciado> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Plano.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Credenciado>) criteria.list(), Credenciado.class);
	}

	@Override
	public List<Credenciado> consultar(Credenciado credenciado) {
		Criteria criteria = createCriteria(Credenciado.class);
		if (StringUtils.isNotBlank(credenciado.getNome())) {
			criteria.add(Restrictions.like("nome", credenciado.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Credenciado>) criteria.list(), Credenciado.class);
	}

	@Override
	public List<Especialidade> listarEspecialidadesPorCredenciado(Long id) {
		Session session = getSession();
		
		SQLQuery query = session.createSQLQuery(SELEC_ESPECIALIDADE_CREDENCIADO);
		query.setParameter("idCredenciado", id);

		List<Object[]> credenciadoBanco = (List<Object[]>) query
				.list();
		
		List<Especialidade> listaEspecialidades = new ArrayList<Especialidade>();
		
		for(Object[] credenciados : credenciadoBanco){
			BigInteger idEspecialidade = (BigInteger) credenciados[1];
			Especialidade especialidade = especialidadePorId(session, idEspecialidade);	
			listaEspecialidades.add(especialidade);
		}
		return listaEspecialidades;
	}

	@Override
	public Especialidade especialidadePorId(Session session, BigInteger idEspecialidade) {
		
		SQLQuery sqlQuery = session.createSQLQuery(SELEC_ESPECIALIDADE);
		sqlQuery.setParameter("idEspecialidade", idEspecialidade.longValue());
		
		List<Object[]> objects = (List<Object[]>) sqlQuery.list();
		Especialidade especialidade = new Especialidade();
		
		for(Object[] object : objects){
			
			especialidade.setId(((BigInteger) object[0]).longValue());
			especialidade.setEspecialidade((String) object[1]);
		}
		
		return especialidade;
	}
	
	@Override
	public List<Credenciado> listarCredenciadosPorEspecialidades(Long id) {
		Session session = getSession();
		
		SQLQuery query = session.createSQLQuery(SELECT_ESPECIALIDADE_CREDENCIADOS);
		query.setParameter("idEspecialidade", id);

		List<Object[]> credenciadoBanco = (List<Object[]>) query
				.list();
		
		List<Credenciado> listaCredenciados = new ArrayList<Credenciado>();
		
		for(Object[] credenciados : credenciadoBanco){
			BigInteger idEspecialidade = (BigInteger) credenciados[1];
			Credenciado credenciado = consultarEspecialidadePorId(session, idEspecialidade);	
			listaCredenciados.add(credenciado);
		}
		return listaCredenciados;
	}

	@Override
	public Credenciado consultarEspecialidadePorId(Session session, BigInteger idCredenciado) {
		
		SQLQuery sqlQuery = session.createSQLQuery(SELECT_CREDENCIADO);
		sqlQuery.setParameter("idCredenciado", idCredenciado.longValue());
		
		List<Object[]> objects = (List<Object[]>) sqlQuery.list();
		Credenciado credenciado = new Credenciado();
		
		for(Object[] object : objects){
			
			credenciado.setId(((BigInteger) object[0]).longValue());
			credenciado.setNome((String) object[1]);
			credenciado.setCep((String) object[2]);
			credenciado.setCidade((String) object[3]);
			credenciado.setLogradouro((String) object[4]);
			credenciado.setValorPago((BigDecimal) object[5]); 
		}
		
		return credenciado;
	}

	@Override
	public Credenciado consultarCpf(Credenciado credenciado) {
		Criteria criteria = createCriteria(Credenciado.class);
		criteria.add(Restrictions.eq("cpf", credenciado.getCpf()));
		return (Credenciado) criteria.uniqueResult();
	}
}
