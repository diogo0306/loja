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

import br.com.eclinic.entity.solicitacao.SolicitacaoExame;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({"unchecked"})
@Repository(value = "solicitacaoExameRepository")
public class SolicitacaoExameHibernateDAO extends SGPGenericDAO<SolicitacaoExame> implements SolicitacaoExameRepository {

	@Autowired
	public SolicitacaoExameHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(SolicitacaoExame.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<SolicitacaoExame> consultar(SolicitacaoExame solicitacaoExame) throws ParseException {
		Criteria criteria = createCriteria(SolicitacaoExame.class);				
		
		if(StringUtils.isNotBlank(solicitacaoExame.getStatus())){
			criteria.add(Restrictions.like("status", solicitacaoExame.getStatus(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		if(solicitacaoExame.getDataFormatada() != null && !solicitacaoExame.getDataFormatada().isEmpty()){
			
			String dia = solicitacaoExame.getDataFormatada().substring(0, 2);
			String mes = solicitacaoExame.getDataFormatada().substring(3, 5);
			String ano = solicitacaoExame.getDataFormatada().substring(6, 10);
			
			GregorianCalendar dataPesquisa = new GregorianCalendar();
			dataPesquisa.setTime(new Date());
			dataPesquisa.set(Calendar.YEAR, Integer.parseInt(ano));
			dataPesquisa.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
			dataPesquisa.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
			dataPesquisa.set(Calendar.HOUR_OF_DAY, 0);
			dataPesquisa.set(Calendar.MINUTE, 0);
			dataPesquisa.set(Calendar.SECOND, 0);
			
			solicitacaoExame.setData(dataPesquisa.getTime());
			
			Date dataInicial = null;
			Date dataFinal = null;

			SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			dataInicial = formatoData
					.parse(dataPesquisa.get(Calendar.YEAR) + "-" + (dataPesquisa.get(Calendar.MONTH) + 1)
							+ "-" + dataPesquisa.get(Calendar.DAY_OF_MONTH) + " 00:00:00");

			dataFinal = formatoData
					.parse(dataPesquisa.get(Calendar.YEAR) + "-" + (dataPesquisa.get(Calendar.MONTH) + 1)
							+ "-" + dataPesquisa.get(Calendar.DAY_OF_MONTH) + " 23:59:59");
			
			criteria.add(Restrictions.ge("data", dataInicial));
			criteria.add(Restrictions.lt("data", dataFinal));
			
		}
		
		criteria.addOrder(Order.asc("nomePaciente"));
		criteria.addOrder(Order.asc("data"));
		
		return Collections.checkedList((List<SolicitacaoExame>) criteria.list(), SolicitacaoExame.class);
	}

}
