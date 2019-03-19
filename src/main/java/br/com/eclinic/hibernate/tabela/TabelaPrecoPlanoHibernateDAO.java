package br.com.eclinic.hibernate.tabela;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.tabela.TabelaPrecoPlano;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "tabelaPrecoPlanoRepository")
public class TabelaPrecoPlanoHibernateDAO extends SGPGenericDAO<TabelaPrecoPlano> implements TabelaPrecoPlanoRepository {

	@Autowired
	public TabelaPrecoPlanoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(TabelaPrecoPlano.class);
		super.setSessionFactory(factory);
	}

}
