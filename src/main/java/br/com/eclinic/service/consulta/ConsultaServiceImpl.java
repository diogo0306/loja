package br.com.eclinic.service.consulta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.agendamento.StatusAgendamentoEnum;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.consulta.StatusConsultaEnum;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.agendamento.AgendamentoRepository;
import br.com.eclinic.hibernate.consulta.ConsultaRepository;
import br.com.eclinic.hibernate.paciente.PacienteRepository;
import br.com.eclinic.relatorio.RelatorioFaturamentoMes;
import br.com.eclinic.util.Util;

@Service(value = "consultaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ConsultaServiceImpl implements ConsultaService {

	private ConsultaRepository consultaRepository;
	private AgendamentoRepository agendamentoRepository;
	private PacienteRepository pacienteRepository;

	public AgendamentoRepository getAgendamentoRepository() {
		return agendamentoRepository;
	}

	@Autowired
	public void setAgendamentoRepository(AgendamentoRepository agendamentoRepository) {
		this.agendamentoRepository = agendamentoRepository;
	}

	public PacienteRepository getPacienteRepository() {
		return pacienteRepository;
	}

	@Autowired
	public void setPacienteRepository(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	public ConsultaRepository getConsultaRepository() {
		return consultaRepository;
	}

	@Autowired
	public void setConsultaRepository(ConsultaRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}

	@Override
	public void salvar(Consulta consulta) {
		/*if (consulta.getAgendamento().getPaciente().getId() == null) {
			pacienteRepository.salvar(consulta.getAgendamento().getPaciente());
		}
		if (consulta.getAgendamento().getId() == null) {
			agendamentoRepository.salvar(consulta.getAgendamento());
		} else {
			agendamentoRepository.alterar(consulta.getAgendamento());
		}*/
		consultaRepository.salvar(consulta);
	}

	@Override
	public void alterar(Consulta consulta) {
		consultaRepository.alterar(consulta);

	}

	@Override
	public List<Consulta> listar() {
		return consultaRepository.listar();
	}

	@Override
	public void excluir(Long id) throws NegocioException {
		Consulta consulta = buscar(id);
		if (consulta == null) {
			throw new NegocioException("Consulta não existe mais na base de dados");
		}
		consultaRepository.excluir(consulta);

	}

	@Override
	public Consulta buscar(Long pk) {
		return consultaRepository.buscar(pk);
	}

	@Override
	public List<Consulta> consultarConsultaPorFiltro(Consulta consulta, Cliente cliente) {
		return consultaRepository.consultarConsultaPorFiltro(consulta, cliente);
	}

	@Override
	public List<Consulta> consultarConsultaPorData(Cliente cliente, GregorianCalendar calendar) {
		return consultaRepository.consultarConsultaPorData(cliente, calendar);
	}

	@Override
	public void atenderConsulta(Agendamento agendamento, Cliente cliente) {

		List<Agendamento> agendamentosEmAtendimento = agendamentoRepository.consultarAgendamentoEmAtendimento(cliente);

		for (Agendamento emAtendimento : agendamentosEmAtendimento) {
			// Verificar depois a real necessidade de sempre buscar por id.
			emAtendimento = agendamentoRepository.buscar(emAtendimento.getId());
			emAtendimento.setStatusAgendamentoEnum(StatusAgendamentoEnum.ATENDIDO);
			agendamentoRepository.alterar(emAtendimento);
		}

		Agendamento agendamentoAlteracao = agendamentoRepository.buscar(agendamento.getId());
		agendamentoAlteracao.setStatusAgendamentoEnum(StatusAgendamentoEnum.EM_ATENDIMENTO);

		agendamentoRepository.alterar(agendamentoAlteracao);
	}

	@Override
	public void finalizarConsulta(Agendamento agendamento) {

		Agendamento agendamentoAlteracao = agendamentoRepository.buscar(agendamento.getId());
		agendamentoAlteracao.setStatusAgendamentoEnum(StatusAgendamentoEnum.ATENDIDO);
		Consulta consultaBanco = consultaRepository.buscar(agendamentoAlteracao.getConsulta().getId());
		consultaBanco.setStatus(StatusConsultaEnum.REALIZADO);
		
		consultaRepository.alterar(consultaBanco);
		agendamentoRepository.alterar(agendamentoAlteracao);
	}

	@Override
	public int getQuantidadePorStatus(List<Agendamento> agendamentos, StatusAgendamentoEnum status) {
		int quantidade = 0;
		for (Agendamento agendamento : agendamentos) {
			if (status.equals(agendamento.getStatusAgendamentoEnum())) {
				quantidade = quantidade + 1;
			}
		}
		return quantidade;
	}

	@Override
	public List<RelatorioFaturamentoMes> getRelatorioFaturamentoMes(Cliente cliente, GregorianCalendar calendar) {
		List<Consulta> consultas = consultaRepository.consultarConsultaPorMes(cliente, calendar);

		List<RelatorioFaturamentoMes> relatorio = new ArrayList<RelatorioFaturamentoMes>();

		int indexConsulta = 0;
		for (int dia = 1; dia <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); dia++) {

			BigDecimal valorDia = BigDecimal.ZERO;
			int quantidadeConsultas = 0;
			while (!consultas.isEmpty() && indexConsulta < consultas.size() && dia == Util
					.getCalendar(consultas.get(indexConsulta).getDataConsulta()).get(Calendar.DAY_OF_MONTH)) {
				valorDia = valorDia.add(consultas.get(indexConsulta).getValorConsulta());
				indexConsulta++;
				quantidadeConsultas = quantidadeConsultas + 1;
			}

			relatorio.add(new RelatorioFaturamentoMes(dia, valorDia, quantidadeConsultas));

		}

		return relatorio;
	}

	@Override
	public Consulta buscarPorAgendamento(Agendamento agendamento) {
		return consultaRepository.buscarPorAgendamento(agendamento);
	}

	@Override
	public List<Consulta> listarConsultasRealizadasPorMedico(Medico medico, Integer mes, Integer ano) {
		return consultaRepository.listarConsultasRealizadasPorMedico(medico, mes, ano);
	}

	@Override
	public List<Consulta> listarPorPaciente(Long id) {
		return this.consultaRepository.listarPorPaciente(id);
	}

	@Override
	public List<Consulta> listarPorMedicoPorId(Long id) {
		return this.consultaRepository.listarPorMedicoPorId(id);
	}
}
