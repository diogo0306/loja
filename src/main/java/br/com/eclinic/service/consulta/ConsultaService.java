package br.com.eclinic.service.consulta;

import java.util.GregorianCalendar;
import java.util.List;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.agendamento.StatusAgendamentoEnum;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.relatorio.RelatorioFaturamentoMes;

public interface ConsultaService {

	void salvar(Consulta consulta);

	void alterar(Consulta consulta);

	List<Consulta> listar();

	void excluir(Long id) throws NegocioException;

	Consulta buscar(Long pk);

	List<Consulta> consultarConsultaPorFiltro(Consulta consulta, Cliente cliente);

	List<Consulta> consultarConsultaPorData(Cliente cliente, GregorianCalendar calendar);
	
	void atenderConsulta(Agendamento agendamento, Cliente cliente);
	
	void finalizarConsulta(Agendamento agendamento);

	int getQuantidadePorStatus(List<Agendamento> agendamentos, StatusAgendamentoEnum confirmado);
	
	List<RelatorioFaturamentoMes> getRelatorioFaturamentoMes(Cliente cliente, GregorianCalendar calendar);

	Consulta buscarPorAgendamento(Agendamento agendamentoAlteracao);
	
	List<Consulta> listarConsultasRealizadasPorMedico(Medico medico, Integer mes, Integer ano);
	
	List<Consulta> listarPorPaciente(Long id);
	
	List<Consulta> listarPorMedicoPorId(Long id);
}
