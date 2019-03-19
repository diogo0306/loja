package br.com.eclinic.hibernate.agendamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
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

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.agendamento.StatusAgendamentoEnum;
import br.com.eclinic.entity.agendamento.StatusPagamentoEnum;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked"})
@Repository(value = "agendamentoRepository")
public class AgendamentoHibernateDAO extends SGPGenericDAO<Agendamento> implements AgendamentoRepository {

	/**
	 * Construtor padrão.
	 * 
	 */
	@Autowired
	public AgendamentoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Agendamento.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Agendamento> consultaragendamentosPorFiltro(Agendamento agendamento, Cliente cliente) throws ParseException {
		Criteria criteria = createCriteria(Agendamento.class);
		criteria.add(Restrictions.eq("cliente.id", cliente.getId()));
		criteria.createAlias("paciente", "p");
		criteria.createAlias("paciente.documentacao", "d");

		if (agendamento != null) {
			if (agendamento.getPaciente() != null) {
				if (StringUtils.isNotBlank(agendamento.getPaciente().getNome())) {
					criteria.add(Restrictions
							.like("p.nome", agendamento.getPaciente().getNome(), MatchMode.ANYWHERE)
							.ignoreCase());
				}
				if (StringUtils.isNotBlank(agendamento.getPaciente().getDocumentacao().getRg())) {
					criteria.add(Restrictions
							.like("d.rg", agendamento.getPaciente().getDocumentacao().getRg(), MatchMode.ANYWHERE)
							.ignoreCase());
				}
				if (StringUtils.isNotBlank(agendamento.getPaciente().getDocumentacao().getCpf())) {
					criteria.add(Restrictions
							.like("d.cpf", agendamento.getPaciente().getDocumentacao().getCpf(), MatchMode.ANYWHERE)
							.ignoreCase());
				}
			}

			if (agendamento.getDataConsultaFormatada() != null && !agendamento.getDataConsultaFormatada().isEmpty()) {

				String dia = agendamento.getDataConsultaFormatada().substring(0, 2);
				String mes = agendamento.getDataConsultaFormatada().substring(3, 5);
				String ano = agendamento.getDataConsultaFormatada().substring(6, 10);

				GregorianCalendar dataPesquisa = new GregorianCalendar();
				dataPesquisa.setTime(new Date());
				dataPesquisa.set(Calendar.YEAR, Integer.parseInt(ano));
				dataPesquisa.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
				dataPesquisa.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
				dataPesquisa.set(Calendar.HOUR_OF_DAY, 0);
				dataPesquisa.set(Calendar.MINUTE, 0);
				dataPesquisa.set(Calendar.SECOND, 0);

				agendamento.setDataConsulta(dataPesquisa.getTime());
					
				Date dataInicial = null;
				Date dataFinal = null;

				SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				dataInicial = formatoData
						.parse(dataPesquisa.get(Calendar.YEAR) + "-" + (dataPesquisa.get(Calendar.MONTH) + 1) + "-"
								+ dataPesquisa.get(Calendar.DAY_OF_MONTH) + " 00:00:00");

				dataFinal = formatoData.parse(dataPesquisa.get(Calendar.YEAR) + "-" + (dataPesquisa.get(Calendar.MONTH) + 1)
						+ "-" + dataPesquisa.get(Calendar.DAY_OF_MONTH) + " 23:59:59");

				criteria.add(Restrictions.ge("dataConsulta", dataInicial));
				criteria.add(Restrictions.lt("dataConsulta", dataFinal));
				
			}			
		}

		criteria.addOrder(Order.desc("dataConsulta"));

		return Collections.checkedList((List<Agendamento>) criteria.list(), Agendamento.class);
	}

	public List<Agendamento> consultarAgendamentoPorData(Cliente cliente, GregorianCalendar dataAgendamento) {

		try {
			Criteria criteria = createCriteria(Agendamento.class);
			criteria.add(Restrictions.eq("cliente.id", cliente.getId()));

			Date dataInicial = null;
			Date dataFinal = null;

			SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			dataInicial = formatoData
					.parse(dataAgendamento.get(Calendar.YEAR) + "-" + (dataAgendamento.get(Calendar.MONTH) + 1)
							+ "-" + dataAgendamento.get(Calendar.DAY_OF_MONTH) + " 00:00:00");

			dataFinal = formatoData
					.parse(dataAgendamento.get(Calendar.YEAR) + "-" + (dataAgendamento.get(Calendar.MONTH) + 1)
							+ "-" + dataAgendamento.get(Calendar.DAY_OF_MONTH) + " 23:59:59");

			criteria.add(Restrictions.ge("dataConsulta", dataInicial));
			criteria.add(Restrictions.lt("dataConsulta", dataFinal));

			criteria.addOrder(Order.desc("turnoAgendamentoEnum"));
			criteria.addOrder(Order.desc("tipoAgendamentoConsultaEnum"));

			return Collections.checkedList((List<Agendamento>) criteria.list(), Agendamento.class);
		} catch (ParseException e) {
			return null;
		}

	}

	@Override
	public List<Agendamento> consultarAgendamentoEmAtendimento(Cliente cliente) {
		
		Criteria criteria = createCriteria(Agendamento.class);
		criteria.add(Restrictions.eq("cliente.id", cliente.getId()));
		criteria.add(Restrictions.eq("statusAgendamentoEnum", StatusAgendamentoEnum.EM_ATENDIMENTO));
		
		return Collections.checkedList((List<Agendamento>) criteria.list(), Agendamento.class);
	}

	@Override
	public List<Agendamento> consultarAgendamentoPorMedico(Medico medico) {
		Criteria criteria = createCriteria(Agendamento.class);
		criteria.add(Restrictions.eq("medico.id", medico.getId()));
		return Collections.checkedList((List<Agendamento>) criteria.list(), Agendamento.class);
	}

	@Override
	public List<Agendamento> listarAgendamentosPendentesPorMedico(Medico medico, Cliente cliente) {
		Criteria criteria = createCriteria(Agendamento.class);
		criteria.add(Restrictions.eq("medico.id", medico.getId()));
		criteria.add(Restrictions.eq("cliente.id", cliente.getId()));
		criteria.add(Restrictions.eq("statusAgendamentoEnum", StatusAgendamentoEnum.AGUARDANDO));
		criteria.add(Restrictions.eq("statusPagamentoEnum", StatusPagamentoEnum.PAGO));
		return Collections.checkedList((List<Agendamento>) criteria.list(), Agendamento.class);
	}

	@Override
	public Agendamento buscarPorConsulta(Long id) {
		Criteria criteria = createCriteria(Agendamento.class);
		criteria.add(Restrictions.eq("consulta.id", id));
		criteria.setMaxResults(1);
		return (Agendamento) criteria.uniqueResult();
	}
}
