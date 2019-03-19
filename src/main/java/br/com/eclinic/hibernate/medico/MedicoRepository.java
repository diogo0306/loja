package br.com.eclinic.hibernate.medico;

import java.util.Date;
import java.util.List;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.entity.medico.Medico;

public interface MedicoRepository {
	
	void salvar(Medico medico);

	Medico buscar(Long pk);

	void alterar(Medico medico);

	List<Medico> listar();

	void excluir(Medico medico);

	List<Medico> listarOrder(String atributoOrder);
	
	List<Medico> consultar(Medico medico, Cliente cliente);

	List<Medico> listarPorCliente(Cliente cliente);	
	
	void salvarEndereco (Endereco endereco);
	
	Medico consultarCpf (Medico medico);
	
	List<String> horariosDeAgendamentoDisponiveis(Medico medico, List<Agendamento> agendamentos, Date data);
	
	boolean diaDisponivel(Medico medico, Date data);
	
	List<Medico> listarMedicosPorEspecialidades(Long id);

}
