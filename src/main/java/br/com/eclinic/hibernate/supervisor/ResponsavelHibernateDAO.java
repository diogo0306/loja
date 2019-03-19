package br.com.eclinic.hibernate.supervisor;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.entity.supervisor.Responsavel;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings("unchecked")
@Repository(value = "responsavelRepository")
public class ResponsavelHibernateDAO extends SGPGenericDAO<Responsavel> implements ResponsavelRepository {

	@Autowired
	public ResponsavelHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Responsavel.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Responsavel> pesquisarPorMedico(Long id) {
		Criteria criteria = createCriteria(Jornada.class);
		criteria.add(Restrictions.eq("medicoVinculo.id", id));
		return Collections.checkedList((List<Responsavel>) criteria.list(), Responsavel.class);
	}

}