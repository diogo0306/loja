package br.com.eclinic.hibernate.tabela;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.tabela.TabelaFaixa;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings("unchecked")
@Repository(value = "tabelaFaixaRepository")
public class TabelaFaixaHibernateDAO extends SGPGenericDAO<TabelaFaixa> implements TabelaFaixaRepository{
	
	@Autowired
	public TabelaFaixaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(TabelaFaixa.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<TabelaFaixa> buscarFaixasPorPlano(Plano plano) {
		Criteria criteria = createCriteria(TabelaFaixa.class);
		criteria.createAlias("plano", "p");
		criteria.add(Restrictions.eq("p.id", plano.getId()));
		return Collections.checkedList((List<TabelaFaixa>) criteria.list(), TabelaFaixa.class);
	}

}
