package br.com.eclinic.hibernate.paciente;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "enderecoRepository")
public class EnderecoHibernateDAO extends SGPGenericDAO<Endereco> implements EnderecoRepository {

	@Autowired
	public EnderecoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Endereco.class);
		super.setSessionFactory(factory);
	}

}
