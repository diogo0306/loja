package br.com.eclinic.hibernate.agendamento;

import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.medico.Medico;

public interface AgendamentoRepository {
	
	void salvar(Agendamento agendamento);

	Agendamento buscar(Long pk);

	void alterar(Agendamento agendamento);

	List<Agendamento> listar();

	void excluir(Agendamento agendamento);
	
	List<Agendamento> consultaragendamentosPorFiltro(Agendamento agendamento, Cliente cliente) throws ParseException;

	List<Agendamento> consultarAgendamentoPorData(Cliente cliente,
			GregorianCalendar dataAgendamento);
		
	List<Agendamento> consultarAgendamentoEmAtendimento(Cliente cliente);
	
	List<Agendamento> consultarAgendamentoPorMedico(Medico medico);
	
	List<Agendamento> listarAgendamentosPendentesPorMedico (Medico medico, Cliente cliente);
	
	Agendamento buscarPorConsulta (Long id);

}
