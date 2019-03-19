package br.com.eclinic.hibernate.plano;

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

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "unchecked" })
@Repository(value = "planoRepository")
public class PlanoHibernateDAO extends SGPGenericDAO<Plano> implements PlanoRepository {
	/**
	 * Construtor padrão.
	 * 
	 */
	@Autowired
	public PlanoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Plano.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Plano> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Plano.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Plano>) criteria.list(), Plano.class);
	}

	@Override
	public List<Plano> consultar(Plano plano, Cliente cliente) {
		Criteria criteria = createCriteria(Plano.class);
		if (StringUtils.isNotBlank(plano.getNome())) {
			criteria.add(Restrictions.like("nome", plano.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Plano>) criteria.list(), Plano.class);
	}

}
