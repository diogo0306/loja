package br.com.eclinic.hibernate.consulta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.agendamento.StatusAgendamentoEnum;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.consulta.StatusConsultaEnum;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked"})
@Repository(value = "consultaRepository")
public class ConsultaHibernateDAO extends SGPGenericDAO<Consulta> implements ConsultaRepository {

	/**
	 * Construtor padrão.
	 * 
	 */
	@Autowired
	public ConsultaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Consulta.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Consulta> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Consulta.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Consulta>) criteria.list(), Consulta.class);
	}

	@Override
	public List<Consulta> consultar(Consulta consulta, Cliente cliente) {
		Criteria criteria = createCriteria(Consulta.class);
		if (consulta.getDataConsulta() != null) {
			criteria.add(Restrictions.eq("dataConsulta", consulta.getDataConsulta()));
		}

		criteria.createAlias("cliente", "c");
		criteria.add(Restrictions.eq("c.id", cliente.getId()));
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Consulta>) criteria.list(), Consulta.class);
	}

	@Override
	public List<Consulta> consultarConsultaPorMes(Cliente cliente, GregorianCalendar mesConsulta) {
		try {
			Criteria criteria = createCriteria(Consulta.class);
			criteria.createAlias("agendamento", "ag");
			criteria.createAlias("ag.cliente", "cliente");
			criteria.add(Restrictions.eq("cliente.id", cliente.getId()));

			Date dataInicial = null;
			Date dataFinal = null;

			SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			dataInicial = formatoData.parse(mesConsulta.get(Calendar.YEAR) + "-" + (mesConsulta.get(Calendar.MONTH) + 1)
					+ "-" + mesConsulta.get(Calendar.DAY_OF_MONTH) + " 00:00:00");

			dataFinal = formatoData.parse(mesConsulta.get(Calendar.YEAR) + "-" + (mesConsulta.get(Calendar.MONTH) + 1)
					+ "-" + mesConsulta.getActualMaximum(Calendar.DAY_OF_MONTH) + " 23:59:59");

			criteria.add(Restrictions.ge("dataConsulta", dataInicial));
			criteria.add(Restrictions.lt("dataConsulta", dataFinal));
			criteria.addOrder(Order.asc("dataConsulta"));

			return Collections.checkedList((List<Consulta>) criteria.list(), Consulta.class);

		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public List<Consulta> consultarConsultaPorData(Cliente cliente, GregorianCalendar dataConsulta) {
		try {
			Criteria criteria = createCriteria(Consulta.class);
			criteria.createAlias("agendamento", "ag");
			criteria.createAlias("ag.cliente", "cliente");
			criteria.add(Restrictions.eq("cliente.id", cliente.getId()));

			Date dataInicial = null;
			Date dataFinal = null;

			SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			dataInicial = formatoData
					.parse(dataConsulta.get(Calendar.YEAR) + "-" + (dataConsulta.get(Calendar.MONTH) + 1) + "-"
							+ dataConsulta.get(Calendar.DAY_OF_MONTH) + " 00:00:00");

			dataFinal = formatoData.parse(dataConsulta.get(Calendar.YEAR) + "-" + (dataConsulta.get(Calendar.MONTH) + 1)
					+ "-" + dataConsulta.get(Calendar.DAY_OF_MONTH) + " 23:59:59");

			criteria.add(Restrictions.ge("ag.dataConsulta", dataInicial));
			criteria.add(Restrictions.lt("ag.dataConsulta", dataFinal));

			criteria.addOrder(Order.desc("ag.turnoAgendamentoEnum"));
			criteria.addOrder(Order.desc("ag.tipoAgendamentoConsultaEnum"));

			return Collections.checkedList((List<Consulta>) criteria.list(), Consulta.class);
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public List<Consulta> consultarConsultaPorFiltro(Consulta consulta, Cliente cliente) {
		try {
			Criteria criteria = createCriteria(Consulta.class);
			criteria.createAlias("agendamento", "ag");
			criteria.createAlias("ag.cliente", "cliente");

			SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date dataInicial = null;
			Date dataFinal = null;

			if (cliente != null) {
				criteria.add(Restrictions.eq("cliente.id", cliente.getId()));
			}

			if (consulta != null) {
				if (consulta.getDataConsulta() != null) {

					GregorianCalendar dataConsulta = new GregorianCalendar();
					dataConsulta.setTime(consulta.getDataConsulta());

					dataInicial = formatoData
							.parse(dataConsulta.get(Calendar.YEAR) + "-" + (dataConsulta.get(Calendar.MONTH) + 1) + "-"
									+ dataConsulta.get(Calendar.DAY_OF_MONTH) + " 00:00:00");

					dataFinal = formatoData
							.parse(dataConsulta.get(Calendar.YEAR) + "-" + (dataConsulta.get(Calendar.MONTH) + 1) + "-"
									+ dataConsulta.get(Calendar.DAY_OF_MONTH) + " 23:59:59");

					criteria.add(Restrictions.ge("ag.dataConsulta", dataInicial));
					criteria.add(Restrictions.lt("ag.dataConsulta", dataFinal));

				}

				if (consulta.getAgendamento() != null && consulta.getAgendamento().getStatusAgendamentoEnum() != null) {
					criteria.add(Restrictions.eq("ag.statusAgendamentoEnum", StatusAgendamentoEnum.ATENDIDO));
				}
			}

			criteria.addOrder(Order.desc("ag.dataConsulta"));

			return Collections.checkedList((List<Consulta>) criteria.list(), Consulta.class);
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public Consulta buscarPorAgendamento(Agendamento agendamento) {
		Criteria criteria = createCriteria(Consulta.class);
		criteria.add(Restrictions.eq("agendamento.id", agendamento.getId()));
		return (Consulta) criteria.uniqueResult();
	}

	@Override
	public List<Consulta> listarConsultasRealizadasPorMedico(Medico medico, Integer mes, Integer ano) {
		Criteria criteria = createCriteria(Consulta.class);
		criteria.add(Restrictions.eq("medico.id", medico.getId()));
		criteria.add(Restrictions.eq("mes", mes));
		criteria.add(Restrictions.eq("ano", ano));
		criteria.add(Restrictions.eq("status", StatusConsultaEnum.REALIZADO));
		return Collections.checkedList((List<Consulta>) criteria.list(), Consulta.class);
	}

	@Override
	public List<Consulta> listarPorPaciente(Long id) {
		Criteria criteria = createCriteria(Consulta.class);
		criteria.add(Restrictions.eq("paciente.id", id));
		return Collections.checkedList((List<Consulta>) criteria.list(), Consulta.class);
	}


	@Override
	public List<Consulta> listarPorMedicoPorId(Long id)  {
		Criteria criteria = createCriteria(Consulta.class);
		criteria.add(Restrictions.eq("medico.id", id));
		return Collections.checkedList((List<Consulta>) criteria.list(), Consulta.class);
	}

	
}
