package br.com.eclinic.hibernate.paciente;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.paciente.PacienteDTO;

public interface PacienteRepository {
	
	void salvar(Paciente paciente);

	Paciente buscar(Long pk);

	void alterar(Paciente paciente);

	List<Paciente> listar();

	void excluir(Paciente paciente);

	List<Paciente> listarOrder(String atributoOrder);
	
	List<Paciente> consultar(Paciente paciente);

	public List<Paciente> listarPorCliente(Cliente cliente);
	
	public List<PacienteDTO> listarPorClienteDTO(Cliente cliente);

	List<PacienteDTO> consultarDTO(Paciente pacienteConsulta, Cliente cliente);

	String buscarMaiorCodigo();
	
	Paciente consultarCpf (Paciente paciente);
	
	Paciente getPaciente(Long id);

}
