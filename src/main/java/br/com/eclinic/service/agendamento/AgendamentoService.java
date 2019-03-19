package br.com.eclinic.service.agendamento;

import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.exception.NegocioException;

public interface AgendamentoService {

	void salvar(Agendamento agendamento);

	void alterar(Agendamento agendamento);

	List<Agendamento> listar();

	void excluir(Long id) throws NegocioException;

	Agendamento buscar(Long pk);

	List<Agendamento> consultarAgendamentoPorFiltro(Agendamento agendamento, Cliente cliente) throws ParseException;

	List<Agendamento> consultarAgendamentoPorData(Cliente cliente,
			GregorianCalendar calendar);
	
	List<Agendamento> consultarAgendamentoPorMedico(Medico medico);
	
	List<Agendamento> listarAgendamentosPendentesPorMedico (Medico medico, Cliente cliente);
	
	Agendamento buscarPorConsulta (Long id);

}