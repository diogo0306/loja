package br.com.eclinic.hibernate.caixa;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.caixa.Caixa;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "caixaRepository")
public class CaixaHibernateDAO extends SGPGenericDAO<Caixa> implements CaixaRepository {

	@Autowired
	public CaixaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Caixa.class);
		super.setSessionFactory(factory);
	}

}
