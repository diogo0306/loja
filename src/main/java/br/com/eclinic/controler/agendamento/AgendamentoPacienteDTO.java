package br.com.eclinic.controler.agendamento;

public class AgendamentoPacienteDTO {

	private Long idMedico;
	private String dataInicio;
	private String datafim;
	private String nomePaciente;
	private String idAgendamento;
	
	public Long getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDatafim() {
		return datafim;
	}
	public void setDatafim(String datafim) {
		this.datafim = datafim;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getIdAgendamento() {
		return idAgendamento;
	}
	public void setIdAgendamento(String idAgendamento) {
		this.idAgendamento = idAgendamento;
	}
	
}
