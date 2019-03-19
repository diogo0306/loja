package br.com.eclinic.hibernate.cartao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.cartao.Cartao;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "cartaoRepository")
public class CartaoHibernateDAO extends SGPGenericDAO<Cartao> implements CartaoRepository {

	@Autowired
	public CartaoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Cartao.class);
		super.setSessionFactory(factory);
	}

}
