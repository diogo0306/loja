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
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked", "unused"})
@Repository(value = "exameRepository")
public class ExameHibernateDAO extends SGPGenericDAO<Exame> implements
		ExameRepository {

	/**
	 * Construtor padrão.
	 * 
	 */
	@Autowired
	public ExameHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Exame.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Exame> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Exame.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Exame>) criteria.list(),
				Exame.class);
	}

	@Override
	public List<Exame> consultar(Exame exame) {
		Criteria criteria = createCriteria(Exame.class);
		if (StringUtils.isNotBlank(exame.getNome())) {
			criteria.add(Restrictions.like("nome", exame.getNome(),
					MatchMode.ANYWHERE).ignoreCase());
		}
		
		if (StringUtils.isNotBlank(exame.getCodigo())) {
			criteria.add(Restrictions.like("codigo", exame.getCodigo(),
					MatchMode.ANYWHERE).ignoreCase());
		}
		
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Exame>) criteria.list(),
				Exame.class);
	}

	@Override
	public List<Exame> listarPorCliente(Cliente cliente) {
		Criteria criteria = createCriteria(Exame.class);
		criteria.createAlias("cliente", "c");
		criteria.add(Restrictions.eq("c.id", cliente.getId()));
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Exame>) criteria.list(),
				Exame.class);
	}

	@Override
	public List<Exame> consultarPorTabela(Tabela tabela) {
		Criteria criteria = createCriteria(Exame.class);
		return null;
	}

	@Override
	public Exame verificarNomeExistente(Exame exame) {
		Criteria criteria = createCriteria(Exame.class);
		criteria.add(Restrictions.eq("nome", exame.getNome()).ignoreCase());
		return (Exame) criteria.uniqueResult();
	}

}
