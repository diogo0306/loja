package br.com.eclinic.hibernate.empresa;

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
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "unchecked" })
@Repository(value = "empresaRepository")
public class EmpresaHibernateDAO extends SGPGenericDAO<Empresa> implements EmpresaRepository {

	@Autowired
	public EmpresaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Empresa.class);
		super.setSessionFactory(factory);
	}
	
	@Override
	public List<Empresa> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Empresa.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Empresa>) criteria.list(), Empresa.class);
	}

	@Override
	public List<Empresa> consultar(Empresa empresa) {
		Criteria criteria = createCriteria(Empresa.class);
		if (StringUtils.isNotBlank(empresa.getNome())) {
			criteria.add(Restrictions.like("nome", empresa.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Empresa>) criteria.list(), Empresa.class);
	}

	@Override
	public List<Empresa> consultarPorDescricao(String descricao) {
		Criteria criteria = createCriteria(Empresa.class);
		criteria.add(Restrictions.like("nome", descricao, MatchMode.ANYWHERE).ignoreCase());
		criteria.addOrder(Order.asc("nome"));
		return  Collections.checkedList((List<Empresa>) criteria.list(), Empresa.class);
	}
	
	@Override
	public List<Empresa> listar(Empresa empresa){
		Criteria criteria = createCriteria(Empresa.class);
		criteria.setMaxResults(100);
		return Collections.checkedList((List<Empresa>) criteria.list(), Empresa.class);
	};

	

	

}
