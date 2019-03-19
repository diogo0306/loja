package br.com.eclinic.hibernate.cobrancaAvulsa;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.paciente.CobrancaAvulsa;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "cobrancaAvulsaRepository")
public class CobrancaAvulsaHibernateDAO extends SGPGenericDAO<CobrancaAvulsa> implements CobrancaAvulsaRepository {

	@Autowired
	public CobrancaAvulsaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(CobrancaAvulsa.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CobrancaAvulsa> listarPorPaciente(Long id) {
		Criteria criteria = createCriteria(CobrancaAvulsa.class);
		criteria.createAlias("pacienteVinculo", "p");
		criteria.add(Restrictions.eq("p.id", id));
		return Collections.checkedList((List<CobrancaAvulsa>) criteria.list(), CobrancaAvulsa.class);
	}

}
