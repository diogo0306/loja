package br.com.eclinic.controler.agendamento;

public class AgendamentoMedicoDTO {
	
	private String nomePaciente;
	private String dataConsulta;
	private String horaConsulta;
	private String dataFinal;
	private String dataFinalConsulta;
	private Long horaFim;
	private String statusAgendamento;
	private String id;
	
	public String getStatusAgendamento() {
		return statusAgendamento;
	}
	public void setStatusAgendamento(String statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}
	public Long getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(Long horaFim) {
		this.horaFim = horaFim;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public String getHoraConsulta() {
		return horaConsulta;
	}
	public void setHoraConsulta(String horaConsulta) {
		this.horaConsulta = horaConsulta;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getDataFinalConsulta() {
		return dataFinalConsulta;
	}
	public void setDataFinalConsulta(String dataFinalConsulta) {
		this.dataFinalConsulta = dataFinalConsulta;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
