package br.com.eclinic.hibernate.medicamento;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.receita.Medicamento;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "medicamentoRepository")
public class MedicamentoHibernateDao extends SGPGenericDAO<Medicamento>
		implements MedicamentoRepository {

	@Autowired
	public MedicamentoHibernateDao(
			@Qualifier("sessionFactory") SessionFactory factory) {
		super(Medicamento.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicamento> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Medicamento.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Medicamento>) criteria.list(),
				Medicamento.class);
	}


}
