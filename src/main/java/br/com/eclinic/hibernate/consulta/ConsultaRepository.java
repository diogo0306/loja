package br.com.eclinic.hibernate.consulta;

import java.util.GregorianCalendar;
import java.util.List;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.medico.Medico;

public interface ConsultaRepository {
	
	void salvar(Consulta consulta);

	Consulta buscar(Long pk);

	void alterar(Consulta consulta);

	List<Consulta> listar();

	void excluir(Consulta consulta);

	List<Consulta> listarOrder(String atributoOrder);
	
	List<Consulta> consultar(Consulta consulta, Cliente cliente);

	List<Consulta> listarPorCliente(Cliente cliente);	
	
	List<Consulta> consultarConsultaPorData(Cliente cliente,
			GregorianCalendar dataConsulta);
	
	List<Consulta> consultarConsultaPorMes(Cliente cliente, GregorianCalendar mesConsulta);
	
	List<Consulta> consultarConsultaPorFiltro(Consulta consulta, Cliente cliente);

	Consulta buscarPorAgendamento(Agendamento agendamento);
	
	List<Consulta> listarConsultasRealizadasPorMedico(Medico medico, Integer mes, Integer ano);
	
	List<Consulta> listarPorPaciente(Long id);
	
	
	List<Consulta> listarPorMedicoPorId(Long id);

}
