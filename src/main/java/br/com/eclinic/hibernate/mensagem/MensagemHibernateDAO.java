package br.com.eclinic.hibernate.mensagem;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.parametrizacao.Mensagem;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "mensagemRepository")
public class MensagemHibernateDAO extends SGPGenericDAO<Mensagem> implements MensagemRepository {

	@Autowired
	public MensagemHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Mensagem.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Mensagem> listarOrder(String atributoOrder) {
		// TODO Auto-generated method stub
		return null;
	}

}
