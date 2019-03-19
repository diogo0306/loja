package br.com.eclinic.hibernate.documentacao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.documentacao.Documentacao;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "documentacaoRepository")
public class DocumentacaoHibernateDAO extends SGPGenericDAO<Documentacao> implements DocumentacaoRepository {

	@Autowired
	public DocumentacaoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Documentacao.class);
		super.setSessionFactory(factory);
	}

}
