package br.com.eclinic.hibernate.hospital;

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

import br.com.eclinic.entity.hospital.Hospital;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "hospitalRepository")
public class HospitalHibernateDAO extends SGPGenericDAO<Hospital> implements HospitalRepository {

	@Autowired
	public HospitalHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Hospital.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hospital> pesquisar(Hospital hospital) {
		Criteria criteria = createCriteria(Hospital.class);
		if(StringUtils.isNotEmpty(hospital.getNome())) {
			criteria.add(Restrictions.like("nome", hospital.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Hospital>) criteria.list(), Hospital.class);
	}

}
