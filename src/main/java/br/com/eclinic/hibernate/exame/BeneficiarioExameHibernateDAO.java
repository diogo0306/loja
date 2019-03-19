package br.com.eclinic.hibernate.exame;

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

import br.com.eclinic.entity.exame.BeneficiarioExame;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "beneficiarioExameRepository")
public class BeneficiarioExameHibernateDAO extends SGPGenericDAO<BeneficiarioExame> implements BeneficiarioExameRepository {

	@Autowired
	public BeneficiarioExameHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(BeneficiarioExame.class);
		super.setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiarioExame> consultar(BeneficiarioExame beneficiarioExame) throws ParseException {
		Criteria criteria = createCriteria(BeneficiarioExame.class);
		
		if(StringUtils.isNotBlank(beneficiarioExame.getCodigo())) {
			criteria.add(Restrictions.like("codigo", beneficiarioExame.getCodigo(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		if(StringUtils.isNotBlank(beneficiarioExame.getPaciente().getNome())) {
			criteria.createAlias("paciente", "p");
			criteria.add(Restrictions.like("p.nome", beneficiarioExame.getPaciente().getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		if(StringUtils.isNotBlank(beneficiarioExame.getCredenciado().getNome())) {
			criteria.createAlias("credenciado", "c");
			criteria.add(Restrictions.like("c.nome", beneficiarioExame.getCredenciado().getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		if(beneficiarioExame.getStatus() != null) {
			criteria.add(Restrictions.eq("status", beneficiarioExame.getStatus()));
		}
		
		if(beneficiarioExame.getDataFormatada() != null && !beneficiarioExame.getDataFormatada().isEmpty()) {
			String dia = beneficiarioExame.getDataFormatada().substring(0, 2);
			String mes = beneficiarioExame.getDataFormatada().substring(3, 5);
			String ano = beneficiarioExame.getDataFormatada().substring(6, 10);

			GregorianCalendar dataPesquisa = new GregorianCalendar();
			dataPesquisa.setTime(new Date());
			dataPesquisa.set(Calendar.YEAR, Integer.parseInt(ano));
			dataPesquisa.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
			dataPesquisa.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
			dataPesquisa.set(Calendar.HOUR_OF_DAY, 0);
			dataPesquisa.set(Calendar.MINUTE, 0);
			dataPesquisa.set(Calendar.SECOND, 0);

			beneficiarioExame.setData(dataPesquisa.getTime());

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
		
		criteria.addOrder(Order.asc("data"));
		
		return Collections.checkedList((List<BeneficiarioExame>) criteria.list(), BeneficiarioExame.class);
	}

}
