package br.com.eclinic.hibernate.fornecedor;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.fornecedor.Fornecedor;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "fornecedorRepository")
public class FornecedorHibernateDAO extends SGPGenericDAO<Fornecedor> implements FornecedorRepository {

	@Autowired
	public FornecedorHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Fornecedor.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> pesquisar(Fornecedor fornecedor) {
		Criteria criteria = createCriteria(Fornecedor.class);
		if(StringUtils.isNotEmpty(fornecedor.getNome())) {
			criteria.add(Restrictions.like("nome", fornecedor.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Fornecedor>) criteria.list(), Fornecedor.class);
	}

}
