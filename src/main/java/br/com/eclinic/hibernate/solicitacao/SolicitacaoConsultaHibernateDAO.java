package br.com.eclinic.hibernate.solicitacao;

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

import br.com.eclinic.entity.solicitacao.SolicitacaoConsulta;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "solicitacaoConsultaRepository")
public class SolicitacaoConsultaHibernateDAO extends SGPGenericDAO<SolicitacaoConsulta>
		implements SolicitacaoConsultaRepository {

	@Autowired
	public SolicitacaoConsultaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(SolicitacaoConsulta.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoConsulta> consultar(SolicitacaoConsulta solicitacaoConsulta) throws ParseException {
		Criteria criteria = createCriteria(SolicitacaoConsulta.class);

		if (StringUtils.isNotBlank(solicitacaoConsulta.getNomePaciente())) {
			criteria.add(Restrictions.like("nomePaciente", solicitacaoConsulta.getNomePaciente(), MatchMode.ANYWHERE)
					.ignoreCase());
		}

		if (StringUtils.isNotBlank(solicitacaoConsulta.getCodigo())) {
			criteria.add(Restrictions.eq("codigo", solicitacaoConsulta.getCodigo()).ignoreCase());
		}

		if (StringUtils.isNotBlank(solicitacaoConsulta.getStatus())) {
			criteria.add(Restrictions.like("status", solicitacaoConsulta.getStatus(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (solicitacaoConsulta.getDataFormatada() != null && !solicitacaoConsulta.getDataFormatada().isEmpty()) {

			String dia = solicitacaoConsulta.getDataFormatada().substring(0, 2);
			String mes = solicitacaoConsulta.getDataFormatada().substring(3, 5);
			String ano = solicitacaoConsulta.getDataFormatada().substring(6, 10);

			GregorianCalendar dataPesquisa = new GregorianCalendar();
			dataPesquisa.setTime(new Date());
			dataPesquisa.set(Calendar.YEAR, Integer.parseInt(ano));
			dataPesquisa.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
			dataPesquisa.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
			dataPesquisa.set(Calendar.HOUR_OF_DAY, 0);
			dataPesquisa.set(Calendar.MINUTE, 0);
			dataPesquisa.set(Calendar.SECOND, 0);

			solicitacaoConsulta.setData(dataPesquisa.getTime());

			Date dataInicial = null;
			Date dataFinal = null;

			SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			dataInicial = formatoData
					.parse(dataPesquisa.get(Calendar.YEAR) + "-" + (dataPesquisa.get(Calendar.MONTH) + 1) + "-"
							+ dataPesquisa.get(Calendar.DAY_OF_MONTH) + " 00:00:00");

			dataFinal = formatoData.parse(dataPesquisa.get(Calendar.YEAR) + "-" + (dataPesquisa.get(Calendar.MONTH) + 1)
					+ "-" + dataPesquisa.get(Calendar.DAY_OF_MONTH) + " 23:59:59");

			criteria.add(Restrictions.ge("data", dataInicial));
			criteria.add(Restrictions.lt("data", dataFinal));

		}

		criteria.addOrder(Order.asc("nomePaciente"));
		criteria.addOrder(Order.asc("data"));

		return Collections.checkedList((List<SolicitacaoConsulta>) criteria.list(), SolicitacaoConsulta.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoConsulta> listarPorPaciente(Long id) {
		Criteria criteria = createCriteria(SolicitacaoConsulta.class);
		criteria.add(Restrictions.eq("idPaciente", id));
		return Collections.checkedList((List<SolicitacaoConsulta>) criteria.list(), SolicitacaoConsulta.class);
	}
}