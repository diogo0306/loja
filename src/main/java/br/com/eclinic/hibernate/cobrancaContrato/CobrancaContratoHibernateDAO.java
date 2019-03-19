package br.com.eclinic.hibernate.cobrancaContrato;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.contrato.CobrancaContrato;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "cobrancaContratoRepository")
public class CobrancaContratoHibernateDAO extends SGPGenericDAO<CobrancaContrato> implements CobrancaContratoRepository{
	
	@Autowired
	public CobrancaContratoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(CobrancaContrato.class);
		super.setSessionFactory(factory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CobrancaContrato> listarPorPaciente(Long id) {
		Criteria criteria = createCriteria(CobrancaContrato.class);
		criteria.createAlias("pacienteVinculo", "p");
		criteria.add(Restrictions.eq("p.id", id));
		return Collections.checkedList((List<CobrancaContrato>) criteria.list(), CobrancaContrato.class);
	}
}
