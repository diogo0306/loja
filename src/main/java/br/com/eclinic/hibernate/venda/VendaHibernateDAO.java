package br.com.eclinic.hibernate.venda;

import java.util.Collections;
import java.util.Date;
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
import br.com.eclinic.entity.venda.Venda;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "vendaRepository")
public class VendaHibernateDAO extends SGPGenericDAO<Venda> implements VendaRepository {

	@Autowired
	public VendaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Venda.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Venda> consultar(Venda venda) {
		Criteria criteria = createCriteria(Venda.class);

		if (StringUtils.isNotBlank(venda.getCodigo())) {
			criteria.add(Restrictions.like("codigo", venda.getCodigo(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("codigo"));
		return Collections.checkedList((List<Venda>) criteria.list(), Venda.class);
	}

	@Override
	public List<Venda> consultarRelatorioPorPeriodo(Date dataInicial, Date dataFinal) {
		Criteria criteria = createCriteria(Venda.class);
		if (dataInicial != null && dataFinal != null) {
			criteria.add(Restrictions.between("dataVenda", dataInicial, dataFinal));
		}
		criteria.addOrder(Order.asc("dataVenda"));
		return Collections.checkedList((List<Venda>) criteria.list(), Venda.class);
	}

}
