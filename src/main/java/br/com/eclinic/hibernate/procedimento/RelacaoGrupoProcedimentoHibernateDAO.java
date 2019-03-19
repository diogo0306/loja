package br.com.eclinic.hibernate.procedimento;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.procedimento.RelacaoGrupoProcedimento;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "relacaoGrupoProcedimentoRepository")
public class RelacaoGrupoProcedimentoHibernateDAO extends SGPGenericDAO<RelacaoGrupoProcedimento> implements RelacaoGrupoProcedimentoRepository {

	@Autowired
	public RelacaoGrupoProcedimentoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(RelacaoGrupoProcedimento.class);
		super.setSessionFactory(factory);
	}

}
