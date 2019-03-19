package br.com.eclinic.hibernate.especialidade;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "especialidadeRepository")
public class EspecialidadeHibernateDAO extends SGPGenericDAO<Especialidade> implements EspecialidadeRepository {

	@Autowired
	public EspecialidadeHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Especialidade.class);
		super.setSessionFactory(factory);
	}
	
}