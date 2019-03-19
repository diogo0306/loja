package br.com.eclinic.hibernate.exame;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.exame.BeneficiarioExame;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.entity.exame.TabelaExame;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked" })
@Repository(value = "tabelaExameRepository")
public class TabelaExameHibernateDAO extends SGPGenericDAO<TabelaExame> implements TabelaExameRepository {
	
	private static final String SELEC_TABELA_EXAME = "SELECT DISTINCT exa_id, tab_id, tab_exa_valor FROM tabela_exame WHERE tab_id = :idTabela and ben_id IS NULL";
	private static final String SELEC_TABELA = "SELECT DISTINCT tab_exa_id, exa_id, tab_id, tab_exa_valor FROM tabela_exame WHERE tab_id = :idTabela and exa_id = :idExame and ben_id IS NULL";
	private static final String SELECT_EXAME = "SELECT exa_id, exa_descricao, exa_nome FROM exame WHERE exa_id = :idExame order by exa_nome";
	private static final String EXAME = "SELECT exa_nome FROM exame WHERE exa_id = :idExame";
	private static final String TABELA = "SELECT tab_nome FROM tabela WHERE tab_id = :idTabela";

	@Autowired
	public TabelaExameHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(TabelaExame.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<TabelaExame> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(TabelaExame.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<TabelaExame>) criteria.list(),
				TabelaExame.class);
	}

	@Override
	public TabelaExame verificarPorExameTabela(Tabela tabela, Exame exame) {
		
		Session session = getSession();
		
		SQLQuery queryTabelaExames = session.createSQLQuery(SELEC_TABELA);
		queryTabelaExames.setParameter("idTabela", tabela.getId()).setParameter("idExame", exame.getId());		
		SQLQuery queryTabela = session.createSQLQuery(TABELA);
		queryTabela.setParameter("idTabela", tabela.getId());		
		SQLQuery queryExames = session.createSQLQuery(EXAME);
		queryExames.setParameter("idExame", exame.getId());
		
		Object[] tabelaBanco = (Object[]) queryTabelaExames.uniqueResult();
		String tab = (String) queryTabela.uniqueResult();
		String exa = (String) queryExames.uniqueResult();
		
		tabela.setNome(tab);
		exame.setNome(exa);
		
		TabelaExame tabelaExame = new TabelaExame();
		tabelaExame.setId(((BigInteger) tabelaBanco[0]).longValue());
		tabelaExame.setExame(exame);
		tabelaExame.setTabela(tabela);
		tabelaExame.setValor((BigDecimal) tabelaBanco[3]);
		
		String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(tabelaExame.getValor().doubleValue()); 
		
		tabelaExame.setValorTransiente(valor);
		
		return tabelaExame;
	}

	@Override
	public List<Exame> consultarExamesPorTabela(Tabela tabela) {
		
		Session session = getSession();

		SQLQuery queryExames = session.createSQLQuery(SELEC_TABELA_EXAME);
		queryExames.setParameter("idTabela", tabela.getId());

		List<Object[]> examesBanco = (List<Object[]>) queryExames
				.list();
		
		List<Exame> listaExame = new ArrayList<Exame>();
		
		for(Object[] exames : examesBanco){
			BigInteger idExame = (BigInteger) exames[0];
			Exame exame = consultarExamePorId(session, idExame);
			exame.setValor((BigDecimal) exames[2]);
			exame.setValorTransiente(exame.getValor().toString().replace(".", ","));
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
			exame.setDescricao((String) object[1]);
			exame.setNome((String) object[2]);		
		}
		
		return exame;
	}

	@Override
	public List<TabelaExame> consultarPorBeneficiarioExame(BeneficiarioExame beneficiarioExame) {
		Criteria criteria = createCriteria(TabelaExame.class);
		criteria.createAlias("beneficiarioExame", "b");
		criteria.add(Restrictions.eq("b.id", beneficiarioExame.getId()));
		criteria.addOrder(Order.asc("valor"));
		return Collections.checkedList((List<TabelaExame>) criteria.list(), TabelaExame.class);
	}

}
