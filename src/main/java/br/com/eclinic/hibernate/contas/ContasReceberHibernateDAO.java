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
import br.com.eclinic.entity.contas.ContasReceber;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "contasReceberRepository")
public class ContasReceberHibernateDAO extends SGPGenericDAO<ContasReceber> implements ContasReceberRepository {

	@Autowired
	public ContasReceberHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(ContasReceber.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasReceber> consultar(ContasReceber contasReceber) {
		Criteria criteria = createCriteria(ContasReceber.class);
		if (contasReceber.getDataPagamento() != null) {
			criteria.add(Restrictions.eq("dataPagamento", contasReceber.getDataPagamento()));
		}
		if (contasReceber.getTipoContaEnum() != null) {
			criteria.add(Restrictions.eq("tipoContaEnum", contasReceber.getTipoContaEnum()));
		}
		if (contasReceber.getStatus() != null) {
			criteria.add(Restrictions.eq("status", contasReceber.getStatus()));
		}

		return Collections.checkedList((List<ContasReceber>) criteria.list(), ContasReceber.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasReceber> consultarRelatorioPorPeriodo(Date dataInicial, Date dataFinal) {
		Criteria criteria = createCriteria(ContasReceber.class);
		if(dataInicial != null && dataFinal != null) {
			criteria.add(Restrictions.between("dataPagamento", dataInicial, dataFinal));
		}
		criteria.add(Restrictions.eq("status", StatusPagamentoEnum.PAGO));
		criteria.addOrder(Order.asc("dataPagamento"));
		return Collections.checkedList((List<ContasReceber>) criteria.list(), ContasReceber.class);
	}

}
