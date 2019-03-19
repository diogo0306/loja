package br.com.eclinic.hibernate.dia;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.parametrizacao.Dia;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "diaRepository")
public class DiaHibernateDAO extends SGPGenericDAO<Dia> implements DiaRepository{

	@Autowired
	public DiaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Dia.class);
		super.setSessionFactory(factory);
	}

}
