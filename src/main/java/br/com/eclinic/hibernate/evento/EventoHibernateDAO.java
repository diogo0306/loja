package br.com.eclinic.hibernate.evento;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.eclinic.entity.evento.Evento;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked" })
public class EventoHibernateDAO extends SGPGenericDAO<Evento> implements EventoRepository {

	public EventoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Evento.class);
		super.setSessionFactory(factory);
		
	}
	
	@Override
	public void salvar(Evento evento) {
		getHibernateTemplate().save(evento);
	}

	@Override
	public Evento buscar(long id) {
		
		Criteria criteria = createCriteria(Evento.class);
		criteria.add(Restrictions.eq("cpf", id));
		return (Evento) criteria.uniqueResult();
	}

	@Override
	public List<Evento> listarHoraInicial(String horaIni) {
		Criteria criteria = createCriteria(Evento.class);
		criteria.addOrder(Order.asc(horaIni));
		return Collections.checkedList((List<Evento>) criteria.list(),
				Evento.class);
	}

	@Override
	public List<Evento> listar(){
		Criteria criteria = createCriteria(Evento.class);
		return Collections.checkedList((List<Evento>) criteria.list(),
				Evento.class);
	}
}