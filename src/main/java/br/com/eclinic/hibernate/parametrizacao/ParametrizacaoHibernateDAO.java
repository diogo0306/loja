package br.com.eclinic.hibernate.parametrizacao;

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

import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.entity.parametrizacao.Parametrizacao;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "unchecked" })
@Repository(value = "parametrizacaoRepository")
public class ParametrizacaoHibernateDAO extends SGPGenericDAO<Parametrizacao> implements ParametrizacaoRepository {

	@Autowired
	public ParametrizacaoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Parametrizacao.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Parametrizacao> listarOrder(String atributoOrder) {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(Parametrizacao.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Parametrizacao>) criteria.list(), Parametrizacao.class);
	}

	@Override
	public List<Parametrizacao> consultar(Parametrizacao parametrizacao) {
		Criteria criteria = createCriteria(Parametrizacao.class);
		if (StringUtils.isNotBlank(parametrizacao.getCodigo())) {
			criteria.add(Restrictions.like("codigo", parametrizacao.getCodigo(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("codigo"));
		return Collections.checkedList((List<Parametrizacao>) criteria.list(), Parametrizacao.class);
	}

	@Override
	public List<Parametrizacao> listarPorEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(Parametrizacao.class);
		criteria.add(Restrictions.ne("codigo", ""));
		criteria.createAlias("empresa", "e");
		criteria.add(Restrictions.eq("c.id", empresa.getId()));
		criteria.addOrder(Order.asc("codigo"));
		return Collections.checkedList((List<Parametrizacao>) criteria.list(), Parametrizacao.class);

	}

}
