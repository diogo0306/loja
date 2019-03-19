package br.com.eclinic.hibernate.paciente;

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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.paciente.PacienteDTO;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@SuppressWarnings({ "deprecation", "unchecked" })
@Repository(value = "pacienteRepository")
public class PacienteHibernateDAO extends SGPGenericDAO<Paciente> implements PacienteRepository {

	/**
	 * Construtor padrão.
	 * 
	 */
	@Autowired
	public PacienteHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(Paciente.class);
		super.setSessionFactory(factory);
	} //

	@Override
	public List<Paciente> listarOrder(String atributoOrder) {
		Criteria criteria = createCriteria(Paciente.class);
		criteria.addOrder(Order.asc(atributoOrder));
		return Collections.checkedList((List<Paciente>) criteria.list(), Paciente.class);
	}

	@Override
	public List<Paciente> consultar(Paciente paciente) {
		Criteria criteria = createCriteria(Paciente.class);
		if (StringUtils.isNotBlank(paciente.getNome())) {
			criteria.add(Restrictions.like("nome", paciente.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(paciente.getCpf())) {
			criteria.add(Restrictions.like("cpf", paciente.getCpf(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(paciente.getRg())) {
			criteria.add(Restrictions.like("rg", paciente.getRg(), MatchMode.ANYWHERE).ignoreCase());
		}
		
		if (paciente.getDataInclusao() != null) {
			
			Date dataConsultaInicial = paciente.getDataInclusao();
			Date dataConsultaFinal = new Date();
			
			if(paciente.getDataInclusaoFinalFormatada() != null){
				
				String dia = paciente.getDataInclusaoFinalFormatada().substring(0, 2);
				String mes = paciente.getDataInclusaoFinalFormatada().substring(3, 5);
				String ano = paciente.getDataInclusaoFinalFormatada().substring(6, 10);
				
				
				GregorianCalendar dataInclusaoFinalPesquisa = new GregorianCalendar();
				dataInclusaoFinalPesquisa.setTime(dataConsultaFinal);
				dataInclusaoFinalPesquisa.set(Calendar.YEAR, Integer.parseInt(ano));
				dataInclusaoFinalPesquisa.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
				dataInclusaoFinalPesquisa.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
				dataInclusaoFinalPesquisa.set(Calendar.HOUR_OF_DAY, 23);
				dataInclusaoFinalPesquisa.set(Calendar.MINUTE, 59);
				dataInclusaoFinalPesquisa.set(Calendar.SECOND, 59);
				dataConsultaFinal = dataInclusaoFinalPesquisa.getTime();
				
			}else{
			
				dataConsultaFinal.setDate(dataConsultaInicial.getDate());
				dataConsultaFinal.setMonth(dataConsultaInicial.getMonth());
				dataConsultaFinal.setYear(dataConsultaInicial.getYear());
				dataConsultaFinal.setHours(23);
				dataConsultaFinal.setMinutes(59);
				dataConsultaFinal.setSeconds(59);
			}
			
			
			criteria.add(Restrictions.between("dataInclusao", dataConsultaInicial, dataConsultaFinal));
		}

		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Paciente>) criteria.list(), Paciente.class);
	}

	@Override
	public List<Paciente> listarPorCliente(Cliente cliente) {
		Criteria criteria = createCriteria(Paciente.class);
		criteria.add(Restrictions.ne("nome", ""));
		criteria.createAlias("cliente", "c");
		criteria.add(Restrictions.eq("c.id", cliente.getId()));
		criteria.addOrder(Order.asc("nome"));
		return Collections.checkedList((List<Paciente>) criteria.list(), Paciente.class);
	}

	@Override
	public List<PacienteDTO> listarPorClienteDTO(Cliente cliente) {
		Criteria criteria = createCriteria(Paciente.class);
		criteria.add(Restrictions.ne("nome", ""));
		criteria.createAlias("cliente", "c");
		criteria.add(Restrictions.eq("c.id", cliente.getId()));
		criteria.addOrder(Order.asc("nome"));

		criteria.setProjection(Projections.projectionList().add(Projections.property("id").as("id"))
				.add(Projections.property("nome").as("nome")).add(Projections.property("rg").as("rg"))
				.add(Projections.property("cpf").as("cpf"))
				.add(Projections.property("codigoPacienteLegado").as("codigoPacienteLegado"))
				.add(Projections.property("dataNascimento").as("dataNascimento"))
				.add(Projections.property("dataNascimento").as("dataNascimento"))
				.add(Projections.property("dataInclusao").as("dataCadastro"))
				.add(Projections.property("documentacao").as("documentacao"))
				.add(Projections.property("endereco").as("endereco")))
				.setResultTransformer(new AliasToBeanResultTransformer(PacienteDTO.class));

		return Collections.checkedList((List<PacienteDTO>) criteria.list(), PacienteDTO.class);
	}

	@Override
	public List<PacienteDTO> consultarDTO(Paciente pacienteConsulta, Cliente cliente) {
		Criteria criteria = createCriteria(Paciente.class);
		criteria.createAlias("documentacao", "d");
		
		if (StringUtils.isNotBlank(pacienteConsulta.getNome())) {
			criteria.add(Restrictions.like("nome", pacienteConsulta.getNome(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(pacienteConsulta.getCpf())) {
			criteria.add(Restrictions.like("d.cpf", pacienteConsulta.getCpf(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(pacienteConsulta.getRg())) {
			criteria.add(Restrictions.like("d.rg", pacienteConsulta.getRg(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (pacienteConsulta.getDataInclusao() != null) {
			
			Date dataConsultaInicial = pacienteConsulta.getDataInclusao();
			Date dataConsultaFinal = new Date();
			
			if(pacienteConsulta.getDataInclusaoFinalFormatada() != null){
				
				String dia = pacienteConsulta.getDataInclusaoFinalFormatada().substring(0, 2);
				String mes = pacienteConsulta.getDataInclusaoFinalFormatada().substring(3, 5);
				String ano = pacienteConsulta.getDataInclusaoFinalFormatada().substring(6, 10);
				
				
				GregorianCalendar dataInclusaoFinalPesquisa = new GregorianCalendar();
				dataInclusaoFinalPesquisa.setTime(dataConsultaFinal);
				dataInclusaoFinalPesquisa.set(Calendar.YEAR, Integer.parseInt(ano));
				dataInclusaoFinalPesquisa.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
				dataInclusaoFinalPesquisa.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
				dataInclusaoFinalPesquisa.set(Calendar.HOUR_OF_DAY, 23);
				dataInclusaoFinalPesquisa.set(Calendar.MINUTE, 59);
				dataInclusaoFinalPesquisa.set(Calendar.SECOND, 59);
				dataConsultaFinal = dataInclusaoFinalPesquisa.getTime();
				
			}else{
			
				dataConsultaFinal.setDate(dataConsultaInicial.getDate());
				dataConsultaFinal.setMonth(dataConsultaInicial.getMonth());
				dataConsultaFinal.setYear(dataConsultaInicial.getYear());
				dataConsultaFinal.setHours(23);
				dataConsultaFinal.setMinutes(59);
				dataConsultaFinal.setSeconds(59);
			}
			
			
			criteria.add(Restrictions.between("dataInclusao", dataConsultaInicial, dataConsultaFinal));
		}

		criteria.createAlias("cliente", "c");
		criteria.add(Restrictions.eq("c.id", cliente.getId()));
		criteria.addOrder(Order.asc("nome"));

		criteria.setProjection(Projections.projectionList().add(Projections.property("id").as("id"))
				.add(Projections.property("nome").as("nome")).add(Projections.property("rg").as("rg"))
				.add(Projections.property("cpf").as("cpf"))
				.add(Projections.property("codigoPacienteLegado").as("codigoPacienteLegado"))
				.add(Projections.property("dataNascimento").as("dataNascimento"))
				.add(Projections.property("dataNascimento").as("dataNascimento"))
				.add(Projections.property("dataInclusao").as("dataCadastro"))
				.add(Projections.property("documentacao").as("documentacao"))
				.add(Projections.property("endereco").as("endereco")))
				.setResultTransformer(new AliasToBeanResultTransformer(PacienteDTO.class));

		return Collections.checkedList((List<PacienteDTO>) criteria.list(), PacienteDTO.class);
	}

	@Override
	public String buscarMaiorCodigo() {
		Criteria criteria = createCriteria(Paciente.class);
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);
		Paciente paciente = (Paciente) criteria.uniqueResult();
		if(paciente == null) {
			return "1";
		}
		return paciente.getCodigoPacienteLegado();
	}

	@Override
	public Paciente consultarCpf(Paciente paciente) {
		Criteria criteria = createCriteria(Paciente.class);
		criteria.createAlias("documentacao", "d");
		criteria.add(Restrictions.eq("d.cpf", paciente.getCpf()));
		criteria.setMaxResults(1);
		return (Paciente) criteria.uniqueResult();
	}

	@Override
	public Paciente getPaciente(Long id) {
		Criteria criteria = createCriteria(Paciente.class);
		criteria.add(Restrictions.eq("id", id));
		
		
		return (Paciente) criteria.uniqueResult();
	}
}
