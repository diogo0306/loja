package br.com.eclinic.hibernate.contas;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.agendamento.StatusPagamentoEnum;
import br.com.eclinic.entity.contas.ContasPagar;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "contasPagarRepository")
public class ContasPagarHibernateDAO extends SGPGenericDAO<ContasPagar> implements ContasPagarRepository {

	@Autowired
	public ContasPagarHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(ContasPagar.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasPagar> consultar(ContasPagar contasPagar) {		
		Criteria criteria = createCriteria(ContasPagar.class);
		if (contasPagar.getDataPagamento() != null) {
			criteria.add(Restrictions.eq("dataPagamento", contasPagar.getDataPagamento()));
		}		
		if (contasPagar.getTipo() != null) {
			criteria.add(Restrictions.eq("tipo", contasPagar.getTipo()));
		}
		
		return Collections.checkedList((List<ContasPagar>) criteria.list(), ContasPagar.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasPagar> consultarRelatorioPorPeriodo(Date dataInicial, Date dataFinal) {
		Criteria criteria = createCriteria(ContasPagar.class);
		if(dataInicial != null && dataFinal != null) {
			criteria.add(Restrictions.between("dataRealizado", dataInicial, dataFinal));
		}
		criteria.add(Restrictions.eq("status", StatusPagamentoEnum.PENDENTE));
		criteria.addOrder(Order.asc("dataRealizado"));
		return Collections.checkedList((List<ContasPagar>) criteria.list(), ContasPagar.class);
	}

}
