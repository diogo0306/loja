package br.com.eclinic.hibernate.motivo;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import br.com.eclinic.entity.motivo.Motivo;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "motivoRepository")
public class MotivoHibernateDAO extends SGPGenericDAO<Motivo> implements MotivoRepository {
	
	@Autowired
	public MotivoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Motivo.class);
		super.setSessionFactory(factory);
	}

}
