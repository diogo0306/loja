package br.com.eclinic.service.paciente;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.paciente.PacienteDTO;
import br.com.eclinic.entity.receita.Medicamento;
import br.com.eclinic.exception.NegocioException;

public interface PacienteService {

	void salvar(Paciente paciente); //

	void alterar(Paciente paciente); //

	List<Paciente> listar(); //

	void excluir(Paciente paciente) throws NegocioException; //

	Paciente buscar(Long pk) throws NegocioException;

	List<Paciente> listarPorCliente(Cliente cliente);
	
	List<PacienteDTO> listarPorClienteDTO(Cliente cliente);
	
	List<Paciente> consultar(Paciente paciente);

	List<PacienteDTO> consultarDTO(Paciente pacienteConsulta, Cliente cliente);
	
	List<Medicamento> listarMedicamentos();

	Medicamento buscarMedicamento(Long id);

	String buscarMaiorCodigo();
	
	Paciente consultarCpf (Paciente paciente);
	
	Paciente getPaciente(Long id);
	
	PacienteDTO configurarFormBeneficiario(PacienteDTO pacienteDTO, HttpServletRequest request) throws ParseException;
	
	PacienteDTO configurarDependente(PacienteDTO pacienteDTO) throws ParseException;
	
	PacienteDTO configurarCobrancaAvulsa(PacienteDTO pacienteDTO) throws ParseException;
	
	PacienteDTO configurarFormAlterar(PacienteDTO pacienteDTO, HttpServletRequest request) throws ParseException;
	
}
