package br.com.eclinic.entity.agendamento;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.entity.medico.Medico;

public class AgendamentoDTO {

	private static final String PACIENTE_SEM_CADASTRO = " (PACIENTE SEM CADASTRO)";

	private static final String VAZIO = "";
	
	private Long id;
	private String nomePaciente;
	private String nomePacienteSemCadastro;
	private String rgPaciente;
	private String cpfPaciente;
	private String rgPacienteSemCadastro;
	private String cpfPacienteSemCadastro;
	private Date dataConsulta;
	private Date horaConsulta;
	private StatusAgendamentoEnum statusAgendamentoEnum;
	private TipoAgendamentoConsultaEnum tipoAgendamentoConsultaEnum;
	private String dataConsultaFormatada;
	private String horaConsultaFormatada;
	private String telefonePaciente;
	private Medico medico;
	private String nomeEvento;
	private List<Jornada> jornadas;
	private List<Agendamento> agendamentos;
	

	public List<Jornada> getJornadas() {
		return jornadas;
	}

	public void setJornadas(List<Jornada> jornadas) {
		this.jornadas = jornadas;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Date getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(Date horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	public String getHoraConsultaFormatada() {
		if (this.horaConsultaFormatada == null
				|| VAZIO.equals(this.horaConsultaFormatada == null)) {
			if (this.horaConsulta != null) {
				Calendar calendario = new GregorianCalendar();
				calendario.setTime(horaConsulta);

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				format.setCalendar(calendario);
				this.horaConsultaFormatada = format
						.format(calendario.getTime());
			}
		}
		return horaConsultaFormatada;
	}

	public void setHoraConsultaFormatada(String horaConsultaFormatada) {
		this.horaConsultaFormatada = horaConsultaFormatada;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getTelefonePaciente() {
		return telefonePaciente;
	}

	public void setTelefonePaciente(String telefonePaciente) {
		this.telefonePaciente = telefonePaciente;
	}

	public String getNome() {
		if (nomePaciente == null || nomePaciente.isEmpty()) {
			return nomePacienteSemCadastro + PACIENTE_SEM_CADASTRO;
		} else {
			return nomePaciente;
		}
	}

	public String getCpf() {
		if (cpfPaciente == null || cpfPaciente.isEmpty()) {
			return cpfPacienteSemCadastro;
		} else {
			return cpfPaciente;
		}
	}

	public String getRg() {
		if (rgPaciente == null ||  rgPaciente.isEmpty()) {
			return rgPacienteSemCadastro;
		} else {
			return rgPaciente;
		}
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getRgPaciente() {
		return rgPaciente;
	}

	public void setRgPaciente(String rgPaciente) {
		this.rgPaciente = rgPaciente;
	}

	public String getCpfPaciente() {
		return cpfPaciente;
	}

	public void setCpfPaciente(String cpfPaciente) {
		this.cpfPaciente = cpfPaciente;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public StatusAgendamentoEnum getStatusAgendamentoEnum() {
		return statusAgendamentoEnum;
	}

	public void setStatusAgendamentoEnum(
			StatusAgendamentoEnum statusAgendamentoEnum) {
		this.statusAgendamentoEnum = statusAgendamentoEnum;
	}


	public String getNomePacienteSemCadastro() {
		return nomePacienteSemCadastro;
	}

	public void setNomePacienteSemCadastro(String nomePacienteSemCadastro) {
		this.nomePacienteSemCadastro = nomePacienteSemCadastro;
	}

	public String getRgPacienteSemCadastro() {
		return rgPacienteSemCadastro;
	}

	public void setRgPacienteSemCadastro(String rgPacienteSemCadastro) {
		this.rgPacienteSemCadastro = rgPacienteSemCadastro;
	}

	public String getCpfPacienteSemCadastro() {
		return cpfPacienteSemCadastro;
	}

	public void setCpfPacienteSemCadastro(String cpfPacienteSemCadastro) {
		this.cpfPacienteSemCadastro = cpfPacienteSemCadastro;
	}
	
	public String getDataConsultaFormatada() {
		if (this.dataConsultaFormatada == null
				|| VAZIO.equals(this.dataConsultaFormatada == null)) {
			if (this.dataConsulta != null) {
				Calendar calendario = new GregorianCalendar();
				calendario.setTime(dataConsulta);

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				format.setCalendar(calendario);
				this.dataConsultaFormatada = format
						.format(calendario.getTime());
			}

		}
		return this.dataConsultaFormatada;
	}

	public void setDataConsultaFormatada(String dataConsultaFormatada) {
		this.dataConsultaFormatada = dataConsultaFormatada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoAgendamentoConsultaEnum getTipoAgendamentoConsultaEnum() {
		return tipoAgendamentoConsultaEnum;
	}

	public void setTipoAgendamentoConsultaEnum(
			TipoAgendamentoConsultaEnum tipoAgendamentoConsultaEnum) {
		this.tipoAgendamentoConsultaEnum = tipoAgendamentoConsultaEnum;
	}

}
