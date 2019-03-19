package br.com.eclinic.hibernate.procedimento;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.procedimento.Procedimento;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "procedimentoRepository")
public class ProcedimentoHibernateDAO extends SGPGenericDAO<Procedimento> implements ProcedimentoRepository {

	@Autowired
	public ProcedimentoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Procedimento.class);
		super.setSessionFactory(factory);
	}

	
	
}
