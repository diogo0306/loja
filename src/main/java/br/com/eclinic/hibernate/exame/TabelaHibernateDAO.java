package br.com.eclinic.hibernate.exame;

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

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "tabelaRepository")
public class TabelaHibernateDAO extends SGPGenericDAO<Tabela> implements TabelaRepository {
	
	@Autowired
	public TabelaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Tabela.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tabela> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Tabela.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Tabela>) criteria.list(),
				Tabela.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tabela> consultar(Tabela tabela) {
		Criteria criteria = createCriteria(Tabela.class);
		if (StringUtils.isNotBlank(tabela.getNome())) {
			criteria.add(Restrictions.like("nome", tabela.getNome(),
					MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Tabela>) criteria.list(),
				Tabela.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tabela> listarPorCliente(Cliente cliente) {
		Criteria criteria = createCriteria(Tabela.class);
		criteria.createAlias("cliente", "c");
		criteria.add(Restrictions.eq("c.id", cliente.getId()));
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Tabela>) criteria.list(),
				Tabela.class);
	}

	@Override
	public Tabela verificarNomeExistente(Tabela tabela) {
		Criteria criteria = createCriteria(Tabela.class);
		criteria.add(Restrictions.eq("nome", tabela.getNome()).ignoreCase());
		return (Tabela) criteria.uniqueResult();
	}
}
