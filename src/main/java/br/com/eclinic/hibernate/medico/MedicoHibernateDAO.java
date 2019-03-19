package br.com.eclinic.hibernate.medico;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings("unchecked")
@Repository(value = "medicoRepository")
public class MedicoHibernateDAO extends SGPGenericDAO<Medico> implements MedicoRepository {

	/**
	 * Construtor padrão.
	 * 
	 */
	@Autowired
	public MedicoHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Medico.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<Medico> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Medico.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Medico>) criteria.list(), Medico.class);
	}

	@Override
	public List<Medico> consultar(Medico medico, Cliente cliente) {
		Criteria criteria = createCriteria(Medico.class);
		if (StringUtils.isNotBlank(medico.getNome())) {
			criteria.add(Restrictions.like("nome", medico.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (StringUtils.isNotBlank(medico.getCrm())) {
			criteria.add(Restrictions.like("crm", medico.getCrm(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (StringUtils.isNotBlank(medico.getCpf())) {
			criteria.add(Restrictions.like("cpf", medico.getCpf(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (StringUtils.isNotBlank(medico.getRg())) {
			criteria.add(Restrictions.like("rg", medico.getRg(), MatchMode.ANYWHERE).ignoreCase());
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Medico>) criteria.list(), Medico.class);
	}

	@Override
	public void salvarEndereco(Endereco endereco) {
		getHibernateTemplate().save(endereco);
	}

	@Override
	public Medico consultarCpf(Medico medico) {
		Criteria criteria = createCriteria(Medico.class);
		criteria.add(Restrictions.eq("cpf", medico.getCpf()));
		return (Medico) criteria.uniqueResult();
	}

	@Override
	public List<String> horariosDeAgendamentoDisponiveis(Medico medico, List<Agendamento> agendamentos, Date data) {
		List<String> lista = new ArrayList<String>();
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		Date horaAgendamento;
		Date horaFinal;
		String horaInicial;
		String horaFim;
		BigDecimal duracao;
		int horai = 0;
		int minutoi = 0;

		for (int i = 0; i < medico.getJornadas().size(); i++) {

			try {
				horaInicial = medico.getJornadas().get(i).getInicio();
				horaFim = medico.getJornadas().get(i).getFim();

				horaAgendamento = formatoHora.parse(horaInicial);
				horaFinal = formatoHora.parse(horaFim);

				/*
				 * if(horaFim.length()==4){ controladorHoraFim =
				 * Integer.parseInt(horaFim.substring(0,1)); }else { controladorHoraFim =
				 * Integer.parseInt(horaFim.substring(0,2)); }
				 */

				// int controladorHoraFim = Integer.parseInt(horaFim);

				/*
				 * horaInicial = formatoHora.format(horaAgendamento); horaFim =
				 * formatoHora.format(horaFinal);
				 */

				// !horaInicial.equals(horaFim)||horai < controladorHoraFim

				while (horaAgendamento.before(horaFinal)) {

					lista.add(horaInicial);

					duracao = medico.getAgenda().getDuracaoConsulta();					

					int quantidadeNumerosI = 0;
					int quantidadeNumerosF = 0;

					if (horaInicial.length() == 4) {
						quantidadeNumerosI = 1;
						quantidadeNumerosF = 2;
					} else {
						quantidadeNumerosI = 2;
						quantidadeNumerosF = 3;
					}

					String hora = horaInicial.substring(0, quantidadeNumerosI);
					String minuto = horaInicial.substring(quantidadeNumerosF);

					horai = Integer.parseInt(hora);
					minutoi = Integer.parseInt(minuto);

					BigInteger duracaoi = duracao.remainder(BigDecimal.ONE).movePointRight(duracao.scale()).abs()
							.toBigInteger();

					int duracaoFinal = duracaoi.intValue();

					if ((minutoi + duracaoFinal) > 59) {
						minutoi = duracaoFinal - (60 - minutoi);
						horai += 1;
					} else {
						minutoi += duracaoFinal;
					}

					hora = String.valueOf(horai);
					minuto = String.valueOf(minutoi);

					if (minuto.equals("0")) {
						minuto = "00";
					}
					
					int horaAtual = Integer.parseInt(hora);
					
					if(horaAtual < 10) {
						hora = "0" + hora;
					}
					
					if(duracao.compareTo(new BigDecimal(1.0)) >= 0 && duracao.compareTo(new BigDecimal(2.0)) < 0) {
						int h = Integer.parseInt(hora);
						h = h + 1;
						hora = String.valueOf(h);
					}
					
					if(duracao.compareTo(new BigDecimal(2.0)) >= 0) {
						int h = Integer.parseInt(hora);
						h = h + 2;
						hora = String.valueOf(h);
					}

					horaInicial = hora + ":" + minuto;
					horaAgendamento = formatoHora.parse(horaInicial);

					/*
					 * calendar.setTime(horaAgendamento); calendar.add(Calendar.ALL_STYLES,
					 * duracao);
					 */

					/*
					 * if(duracao >= 1.0) {
					 * 
					 * horaAgendamento.setHours((int) (horaAgendamento.getHours()+duracao)); }else {
					 * 
					 * horaAgendamento.setMinutes((int) (horaAgendamento.getMinutes() + duracao)); }
					 */

				}

			} catch (Exception e) {

			}

		}

		return lista;
	}

	@Override
	public boolean diaDisponivel(Medico medico, Date data) {

		int diaDaSemana;

		boolean domingo = medico.getAgenda().getDomingo();
		boolean segunda = medico.getAgenda().getSegunda();
		boolean terca = medico.getAgenda().getTerca();
		boolean quarta = medico.getAgenda().getQuarta();
		boolean quinta = medico.getAgenda().getQuinta();
		boolean sexta = medico.getAgenda().getSexta();
		boolean sabado = medico.getAgenda().getSabado();

		Calendar hoje = Calendar.getInstance();
		hoje.setTime(data);

		diaDaSemana = hoje.get(Calendar.DAY_OF_WEEK);

		if (diaDaSemana == 1 && domingo == true) {
			return true;
		} else if (diaDaSemana == 2 && segunda == true) {
			return true;
		} else if (diaDaSemana == 3 && terca == true) {
			return true;
		} else if (diaDaSemana == 4 && quarta == true) {
			return true;
		} else if (diaDaSemana == 5 && quinta == true) {
			return true;
		} else if (diaDaSemana == 6 && sexta == true) {
			return true;
		} else if (diaDaSemana == 7 && sabado == true) {
			return true;
		} else {
			return false;
		}

	}
	
	@Override
	public List<Medico> listarMedicosPorEspecialidades(Long id) {
		
		Criteria criteria = createCriteria(Medico.class);
		criteria.createAlias("especialidade", "es");

		if (id != 0) {
			criteria.add(Restrictions.eq("es.id", id));
		}

		return Collections.checkedList((List<Medico>) criteria.list(), Medico.class);
	}

}
