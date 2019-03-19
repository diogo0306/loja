package br.com.eclinic.hibernate.acomodacao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.acomodacao.Acomodacao;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "acomodacaoRepository")
public class AcomodacaoHibernateDAO extends SGPGenericDAO<Acomodacao> implements AcomodacaoRepository {

	@Autowired
	public AcomodacaoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Acomodacao.class);
		super.setSessionFactory(factory);
	}

}
