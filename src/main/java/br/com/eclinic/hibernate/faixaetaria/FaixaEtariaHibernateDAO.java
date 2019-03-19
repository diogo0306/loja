package br.com.eclinic.hibernate.faixaetaria;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.tabela.FaixaEtaria;
import br.com.eclinic.entity.tabela.TabelaFaixa;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "faixaEtariaRepository")
public class FaixaEtariaHibernateDAO extends SGPGenericDAO<FaixaEtaria> implements FaixaEtariaRepository {

	@Autowired
	public FaixaEtariaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(FaixaEtaria.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaixaEtaria> buscarFaixasPorTabela(TabelaFaixa tabelaFaixa) {
		Criteria criteria = createCriteria(FaixaEtaria.class);
		criteria.createAlias("tabelaFaixa", "t");
		criteria.add(Restrictions.eq("t.id", tabelaFaixa.getId()));
		return Collections.checkedList((List<FaixaEtaria>) criteria.list(), FaixaEtaria.class);
	}

}
