package br.com.eclinic.hibernate.clienteLoja;

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

import br.com.eclinic.entity.clienteLoja.ClienteLoja;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "clienteLojaRepository")
public class ClienteLojaHibernateDAO extends SGPGenericDAO<ClienteLoja> implements ClienteLojaRepository{
	
	@Autowired
	public ClienteLojaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(ClienteLoja.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<ClienteLoja> consultar(ClienteLoja clienteLoja) {
		Criteria criteria = createCriteria(ClienteLoja.class);
		if (StringUtils.isNotBlank(clienteLoja.getNome())) {
			criteria.add(Restrictions.like("nome", clienteLoja.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (StringUtils.isNotBlank(clienteLoja.getCpf())) {
			criteria.add(Restrictions.like("cpf", clienteLoja.getCpf(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<ClienteLoja>) criteria.list(), ClienteLoja.class);
	}

}
