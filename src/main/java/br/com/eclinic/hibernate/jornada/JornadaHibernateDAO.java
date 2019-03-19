package br.com.eclinic.hibernate.jornada;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked" })
@Repository(value = "jornadaRepository")
public class JornadaHibernateDAO extends SGPGenericDAO<Jornada> implements JornadaRepository {

	@Autowired
	public JornadaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Jornada.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Jornada> pesquisarPorMedico(Long id) {
		Criteria criteria = createCriteria(Jornada.class);
		criteria.add(Restrictions.eq("medicoVinculo.id", id));
		return Collections.checkedList((List<Jornada>) criteria.list(), Jornada.class);
	}

	@Override
	public Jornada buscarPorMedico(Long id) {
		Criteria criteria = createCriteria(Jornada.class);
		criteria.add(Restrictions.eq("medicoVinculo.id", id));
		criteria.setMaxResults(1);
		return (Jornada) criteria.uniqueResult();
	}

}
