package br.com.eclinic.hibernate.agenda;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.agenda.Agenda;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "agendaRepository")
public class AgendaHibernateDAO extends SGPGenericDAO<Agenda> implements AgendaRepository {

	@Autowired
	public AgendaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Agenda.class);
		super.setSessionFactory(factory);
	}

}

