package br.com.eclinic.hibernate.dependente;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked" })
@Repository(value = "dependenteRepository")
public class DependenteHibernateDAO extends SGPGenericDAO<Dependente> implements DependenteRepository {

	@Autowired
	public DependenteHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Dependente.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Dependente> listarPorPaciente(Long id) {
		Criteria criteria = createCriteria(Dependente.class);
		criteria.createAlias("pacienteVinculo", "p");
		criteria.add(Restrictions.eq("p.id", id));
		return Collections.checkedList((List<Dependente>) criteria.list(), Dependente.class);
	}

}
