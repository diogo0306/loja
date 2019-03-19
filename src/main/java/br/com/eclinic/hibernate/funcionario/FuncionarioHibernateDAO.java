package br.com.eclinic.hibernate.funcionario;

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
import br.com.eclinic.entity.funcionario.Funcionario;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "unchecked"})
@Repository(value = "funcionarioRepository")
public class FuncionarioHibernateDAO extends SGPGenericDAO<Funcionario> implements FuncionarioRepository {

	@Autowired
	public FuncionarioHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Funcionario.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Funcionario> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Funcionario.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Funcionario>) criteria.list(), Funcionario.class);
	}

	@Override
	public List<Funcionario> consultar(Funcionario funcionario, Cliente cliente) {
		Criteria criteria = createCriteria(Funcionario.class);
		if (StringUtils.isNotBlank(funcionario.getNome())) {
			criteria.add(Restrictions.like("nome", funcionario.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(funcionario.getCpf())) {
			criteria.add(Restrictions.like("cpf", funcionario.getCpf(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.createAlias("cliente", "c");
		criteria.add(Restrictions.eq("c.id", cliente.getId()));
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Funcionario>) criteria.list(), Funcionario.class);
	}
}