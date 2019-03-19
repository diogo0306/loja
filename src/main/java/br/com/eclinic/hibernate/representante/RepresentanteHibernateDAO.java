package br.com.eclinic.hibernate.representante;

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

import br.com.eclinic.entity.representante.Representante;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "unchecked" })
@Repository(value = "representanteRepository")
public class RepresentanteHibernateDAO extends SGPGenericDAO<Representante> implements RepresentanteRepository {

	@Autowired
	public RepresentanteHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Representante.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Representante> consultarPorDescricao(String descricao) {
		Criteria criteria = createCriteria(Representante.class);
		criteria.add(Restrictions.like("nome", descricao, MatchMode.ANYWHERE).ignoreCase());
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Representante>) criteria.list(), Representante.class);
	}

	@Override
	public List<Representante> consultar(Representante representante) {

		Criteria criteria = createCriteria(Representante.class);

		if (StringUtils.isNotBlank(representante.getNome())) {
			criteria.add(Restrictions.like("nome", representante.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (StringUtils.isNotBlank(representante.getCpfcnpj())) {
			criteria.add(Restrictions.like("cpf", representante.getCpfcnpj(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (StringUtils.isNotBlank(representante.getRg())) {
			criteria.add(Restrictions.like("rg", representante.getRg(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Representante>) criteria.list(), Representante.class);

	}

}
