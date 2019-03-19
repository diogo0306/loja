package br.com.eclinic.hibernate.indice;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.indice.IndiceEconomico;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "unchecked"})
@Repository(value = "indiceEconomicoRepository")
public class IndiceEconomicoHibernateDAO extends SGPGenericDAO<IndiceEconomico> implements IndiceEconomicoRepository {

	@Autowired
	public IndiceEconomicoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(IndiceEconomico.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<IndiceEconomico> consultar(IndiceEconomico indiceEconomico) {

		Criteria criteria = createCriteria(IndiceEconomico.class);

		if (StringUtils.isNotBlank(indiceEconomico.getNome())) {
			criteria.add(Restrictions.like("nome", indiceEconomico.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		return Collections.checkedList((List<IndiceEconomico>) criteria.list(), IndiceEconomico.class);
	}
}