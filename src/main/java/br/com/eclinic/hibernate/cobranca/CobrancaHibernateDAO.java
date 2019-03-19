package br.com.eclinic.hibernate.cobranca;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.paciente.Cobranca;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "cobrancaRepository")
public class CobrancaHibernateDAO extends SGPGenericDAO<Cobranca> implements CobrancaRepository {

	@Autowired
	public CobrancaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Cobranca.class);
		super.setSessionFactory(factory);
	}

}
