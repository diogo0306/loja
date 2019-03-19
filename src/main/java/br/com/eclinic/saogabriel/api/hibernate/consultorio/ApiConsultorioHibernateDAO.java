package br.com.eclinic.saogabriel.api.hibernate.consultorio;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.hibernate.infra.SGPGenericDAO;
import br.com.eclinic.saogabriel.api.entity.consultorio.Consultorio;

@SuppressWarnings("unchecked")
@Repository(value = "consultorioRepository")
public class ApiConsultorioHibernateDAO extends SGPGenericDAO<Consultorio> implements ApiConsultorioRepository {

	/**
	 * Construtor padrão.
	 * 
	 */
	@Autowired
	public ApiConsultorioHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Consultorio.class);
		super.setSessionFactory(factory);
	}
	
	@Override
	public List<Consultorio> listarConsultoriosPorMedico(Long id) {
		Criteria criteria = createCriteria(Consultorio.class);
		criteria.createAlias("medicos", "med");

		if (id != 0) {
			criteria.add(Restrictions.eq("med.id", id));
		}

		return Collections.checkedList((List<Consultorio>) criteria.list(), Consultorio.class);
	}

	@Override
	public void excluir(Long pk) {
		
		Consultorio consultorio = new Consultorio();
		consultorio.setId(pk);
		
		getSession().delete(consultorio);
	}
}