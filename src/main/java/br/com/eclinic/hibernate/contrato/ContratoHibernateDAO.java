package br.com.eclinic.hibernate.contrato;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings("unchecked")
@Repository(value = "contratoRepository")
public class ContratoHibernateDAO extends SGPGenericDAO<Contrato> implements ContratoRepository {

	@Autowired
	public ContratoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Contrato.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Contrato> consultar(Contrato contrato) {
		Criteria criteria = createCriteria(Contrato.class);
		if (StringUtils.isNotBlank(contrato.getNumero())) {
			criteria.add(Restrictions.like("numero", contrato.getNumero(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("numero"));
		return Collections.checkedList((List<Contrato>) criteria.list(), Contrato.class);
	}

	@Override
	public List<Contrato> listarContratosAtivos(Contrato contrato) {
		Criteria criteria = createCriteria(Contrato.class);
		// criteria.add(Restrictions.eq("diaVencimentoEnum",
		// contrato.getDiaVencimentoEnum()));
		criteria.add(Restrictions.eq("representante.id", contrato.getRepresentante().getId()));
		criteria.add(Restrictions.eq("situacaoEnum", SituacaoEnum.ATIVO));
		criteria.addOrder(Order.asc("valorContrato"));
		return Collections.checkedList((List<Contrato>) criteria.list(), Contrato.class);
	}

	@Override
	public List<Contrato> listarContratosPorRepresentante(Long id) {
		Criteria criteria = createCriteria(Contrato.class);
		criteria.add(Restrictions.eq("representante.id", id));
		// criteria.add(Restrictions.eq("situacaoEnum", SituacaoContratoEnum.INATIVO));
		return Collections.checkedList((List<Contrato>) criteria.list(), Contrato.class);
	}

	@Override
	public List<Contrato> listarContratosPorPaciente(Long id) {
		Criteria criteria = createCriteria(Contrato.class);
		criteria.add(Restrictions.eq("paciente.id", id));
		return Collections.checkedList((List<Contrato>) criteria.list(), Contrato.class);
	}

	@Override
	public Plano buscarPorContrato(Long pk) {
		Criteria criteria = createCriteria(Plano.class);
		criteria.add(Restrictions.eq("plano.id", pk));
		return (Plano) criteria.uniqueResult();
	}

	@Override
	public Contrato buscarPorBeneficiario(Long id) {
		Criteria criteria = createCriteria(Contrato.class);
		criteria.add(Restrictions.eq("paciente.id", id));
		return (Contrato) criteria.uniqueResult();
	}
}