package br.com.eclinic.service.medico;

import java.util.Date;
import java.util.List;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.exception.NegocioException;

public interface MedicoService {
	
	
	
	void salvar(Medico medico);

	void alterar(Medico medico);

	List<Medico> listar();

	void excluir(Medico medico) throws NegocioException;

	Medico buscar(Long pk) throws NegocioException;

	List<Medico> listarPorCliente(Cliente cliente);
	
	List<Medico> consultar(Medico medico, Cliente cliente);
	
	void salvarEndereco (Endereco endereco);

	Medico consultarCpf (Medico medico);
	
	List<String> horariosDeAgendamentoDisponiveis(Medico medico, List<Agendamento> agendamentos, Date data);
	
	boolean diaDisponivel(Medico medico, Date data);
	
	List<Medico> listarMedicosPorEspecialidades(Long id);
}
