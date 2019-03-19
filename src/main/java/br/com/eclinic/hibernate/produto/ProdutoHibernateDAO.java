package br.com.eclinic.hibernate.produto;

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

import br.com.eclinic.entity.produto.Produto;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "produtoRepository")
public class ProdutoHibernateDAO extends SGPGenericDAO<Produto> implements ProdutoRepository {

	@Autowired
	public ProdutoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Produto.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Produto> consultar(Produto produto) {
		Criteria criteria = createCriteria(Produto.class);
		if (StringUtils.isNotBlank(produto.getNome())) {
			criteria.add(Restrictions.like("nome", produto.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (StringUtils.isNotBlank(produto.getCodigo())) {
			criteria.add(Restrictions.like("codigo", produto.getCodigo(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Produto>) criteria.list(), Produto.class);
	}

}
