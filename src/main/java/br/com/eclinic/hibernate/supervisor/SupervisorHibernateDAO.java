package br.com.eclinic.hibernate.supervisor;

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

import br.com.eclinic.entity.supervisor.Supervisor;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "unchecked" })
@Repository(value = "supervisorRepository")
public class SupervisorHibernateDAO extends SGPGenericDAO<Supervisor> implements SupervisorRepository {

	@Autowired
	public SupervisorHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Supervisor.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Supervisor> consultar(Supervisor supervisor) {
		Criteria criteria = createCriteria(Supervisor.class);

		if (StringUtils.isNotBlank(supervisor.getNome())) {
			criteria.add(Restrictions.like("nome", supervisor.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(supervisor.getCpf())) {
			criteria.add(Restrictions.like("cpf", supervisor.getCpf(), MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Supervisor>) criteria.list(), Supervisor.class);
	}

}
