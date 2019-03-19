package br.com.eclinic.hibernate.procedimento;

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

import br.com.eclinic.entity.procedimento.GrupoProcedimento;
import br.com.eclinic.entity.procedimento.Procedimento;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked"})
@Repository(value = "grupoProcedimentoRpository")
public class GrupoProcedimentoHibernateDAO extends SGPGenericDAO<GrupoProcedimento> implements GrupoProcedimentoRepository {
	
	private static final String SELEC_RELACAO_GRUPO_PROCEDIMENTO = "SELECT DISTINCT grup_proc_id, proc_id FROM relacao_grupo_procedimento WHERE grup_proc_id = :idGrupo";
	private static final String SELECT_PROCEDIMENTO = "SELECT proc_id, proc_nome, proc_codigo, proc_valor_cobranca, proc_valor_pagamento"
			+ " FROM procedimento WHERE proc_id = :idProcedimento order by proc_nome";

	@Autowired
	public GrupoProcedimentoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(GrupoProcedimento.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Procedimento> consultarProcedimentosPorGrupo(GrupoProcedimento grupoProcedimento) {
		
		Session session = getSession();

		SQLQuery queryExames = session.createSQLQuery(SELEC_RELACAO_GRUPO_PROCEDIMENTO);
		queryExames.setParameter("idGrupo", grupoProcedimento.getId());

		List<Object[]> procedimentosBanco = (List<Object[]>) queryExames
				.list();
		
		List<Procedimento> procedimentos = new ArrayList<Procedimento>();
		
		for(Object[] proc : procedimentosBanco){
			BigInteger idProcedimento = (BigInteger) proc[1];
			Procedimento procedimento = consultarProcedimentoPorId(session, idProcedimento);
			procedimentos.add(procedimento);
		}
		
		return procedimentos;
	}
	
	private Procedimento consultarProcedimentoPorId(Session session, BigInteger idProcedimento) {
		
		SQLQuery sqlQuery = session.createSQLQuery(SELECT_PROCEDIMENTO);
		sqlQuery.setParameter("idProcedimento", idProcedimento.longValue());
		
		List<Object[]> objects = (List<Object[]>) sqlQuery.list();
		Procedimento procedimento = new Procedimento();
		
		for(Object[] object : objects){
			
			procedimento.setId(((BigInteger) object[0]).longValue());
			procedimento.setNome((String) object[1]);
			procedimento.setCodigo(((BigInteger) object[2]).longValue());
			procedimento.setValorCobranca((BigDecimal) object[3]);
			procedimento.setValorPagamento((BigDecimal) object[4]);
		}
		
		return procedimento;
	}

	@Override
	public List<GrupoProcedimento> consultar(GrupoProcedimento grupoProcedimento) {
		Criteria criteria = createCriteria(GrupoProcedimento.class);
		if(StringUtils.isNotBlank(grupoProcedimento.getNome())) {
			criteria.add(Restrictions.like("nome", grupoProcedimento.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<GrupoProcedimento>) criteria.list(), GrupoProcedimento.class);
	}

}
