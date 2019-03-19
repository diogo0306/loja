package br.com.eclinic.hibernate.autorizacao;

import java.math.BigDecimal;
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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.autorizacao.SituacaoAutorizacaoEnum;
import br.com.eclinic.entity.autorizacao.StatusAutorizacaoEnum;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings("unchecked")
@Repository(value = "autorizacaoRepository")
public class AutorizacaoHibernateDAO extends SGPGenericDAO<Autorizacao> implements AutorizacaoRepository {
	
	private static final String SELEC_TABELA_AUTORIZACAO_EXAME = "SELECT DISTINCT exa_id, auto_id FROM autorizacao_exame WHERE auto_id = :idAutorizacao";
	private static final String SELECT_EXAME = "SELECT exa_id, exa_nome, exa_valor, exa_codigo FROM exame WHERE exa_id = :idExame order by exa_nome";

	@Autowired
	public AutorizacaoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Autorizacao.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Autorizacao> consultar(Autorizacao autorizacao) {
		Criteria criteria = createCriteria(Autorizacao.class);
		if (autorizacao.getCredenciado() != null && autorizacao.getCredenciado().getId() != null) {
			criteria.add(Restrictions.eq("credenciado.id", autorizacao.getCredenciado().getId()));
		}
		if (StringUtils.isNotEmpty(autorizacao.getNumeroAutorizacao())) {
			criteria.add(Restrictions.like("numeroAutorizacao", autorizacao.getNumeroAutorizacao(), MatchMode.ANYWHERE)
					.ignoreCase());
		}
		if (autorizacao.getTipo() != null) {
			criteria.add(Restrictions.eq("tipo", autorizacao.getTipo()));
		}
		if (autorizacao.getDataAutorizacao() != null) {
			criteria.add(Restrictions.eq("dataAutorizacao", autorizacao.getDataAutorizacao()));
		}
		return Collections.checkedList((List<Autorizacao>) criteria.list(), Autorizacao.class);
	}

	@Override
	public List<Autorizacao> listarAutorizacaoPorPaciente(Long id) {
		Criteria criteria = createCriteria(Autorizacao.class);
		criteria.add(Restrictions.eq("credenciado.id", id));
		return Collections.checkedList((List<Autorizacao>) criteria.list(), Autorizacao.class);
	}

	@Override
	public List<Exame> listarExamesPorAutorizacao(Long id) {
		Session session = getSession();

		SQLQuery queryExames = session.createSQLQuery(SELEC_TABELA_AUTORIZACAO_EXAME);
		queryExames.setParameter("idAutorizacao", id);

		List<Object[]> examesBanco = (List<Object[]>) queryExames
				.list();
		
		List<Exame> listaExame = new ArrayList<Exame>();
		
		for(Object[] exames : examesBanco){
			BigInteger idExame = (BigInteger) exames[0];
			Exame exame = consultarExamePorId(session, idExame);
			listaExame.add(exame);
		}
		
		return listaExame;
	}
	
	@Override
	public Exame consultarExamePorId(Session session, BigInteger idExame) {
		
		SQLQuery sqlQuery = session.createSQLQuery(SELECT_EXAME);
		sqlQuery.setParameter("idExame", idExame.longValue());
		
		List<Object[]> objects = (List<Object[]>) sqlQuery.list();
		Exame exame = new Exame();
		
		for(Object[] object : objects){
			exame.setId(((BigInteger) object[0]).longValue());
			exame.setNome((String) object[1]);	
			exame.setValor((BigDecimal) object[2]);
			exame.setCodigo((String) object[3]);
		}
		
		return exame;
	}

	@Override
	public List<Autorizacao> consultarRelatorioAutorizacoes(Date dataInicial, Date dataFinal) {
		Criteria criteria = createCriteria(Autorizacao.class);
		if(dataInicial != null && dataFinal != null) {
			criteria.add(Restrictions.between("dataHoraExecucao", dataInicial, dataFinal));
		}
		criteria.add(Restrictions.eq("status", StatusAutorizacaoEnum.REALIZADO));
		criteria.add(Restrictions.eq("situacao", SituacaoAutorizacaoEnum.AUTORIZADO));
		criteria.addOrder(Order.asc("dataHoraExecucao"));
		return Collections.checkedList((List<Autorizacao>) criteria.list(), Autorizacao.class);
	}
}