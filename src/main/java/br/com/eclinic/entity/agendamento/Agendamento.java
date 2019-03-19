package br.com.eclinic.entity.agendamento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.plano.Plano;

@Entity
@Table(name = "agendamento")
@SequenceGenerator(name = "sequenceAgendamento", sequenceName = "agendamento_id_seq")
public class Agendamento implements Serializable {

	private static final String SEM_CADASTRO = " (SEM CADASTRO)";
	private static final String VAZIO = "";
	private static final long serialVersionUID = 1L;
	private Long id;
	private String hora;
	private Date dataConsulta;
	private Date dataHoraConfirmacao;
	private Date horaConsulta;
	private TipoAgendamentoEnum tipoAgendamentoEnum;
	private Medico medico;
	private Plano planoSaude;
	private Paciente paciente;
	private List<Exame> exames;
	private StatusAgendamentoEnum statusAgendamentoEnum;
	private Cliente cliente;
	private String dataConsultaFormatada;
	private TipoAgendamentoConsultaEnum tipoAgendamentoConsultaEnum;
	private String nomePacienteNaoCadastrado;
	private String cpfPacienteNaoCadastrado;
	private String rgPacienteNaoCadastrado;
	private String telefonePacienteNaoCadastrado;
	private String nomeEvento;

	private String idStatusAgendamentoTransiente;
	private String idTipoAgendamentoTransiente;
	private String idTurnoConsultaTransiente;
	private String idTipoAgendamentoConsultaTransiente;
	private TurnoAgendamentoEnum turnoAgendamentoEnum;
	
	private String valorConsulta;
	private String observacaoConsulta;
	private String cpfPagador;
	private Consulta consulta;
	private Boolean pacienteNovo;
	private StatusPagamentoEnum statusPagamentoEnum;
	private String codigoConsulta;
	
	
	@Column(name = "age_nome_evento")
	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	@OneToOne
	@JoinColumn(name = "con_id")
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	@Transient
	public String getCpfPagador() {
		return cpfPagador;
	}

	public void setCpfPagador(String cpfPagador) {
		this.cpfPagador = cpfPagador;
	}

	@Transient
	public String getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(String valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	@Transient
	public String getObservacaoConsulta() {
		return observacaoConsulta;
	}

	public void setObservacaoConsulta(String observacaoConsulta) {
		this.observacaoConsulta = observacaoConsulta;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "age_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "age_hora")
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	@Column(name = "age_hora_consulta")
	public Date getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(Date horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	@Column(name = "age_data_consulta", nullable = false)
	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	@Column(name = "age_data_hora_confirmacao", nullable = true)
	public Date getDataHoraConfirmacao() {
		return dataHoraConfirmacao;
	}

	public void setDataHoraConfirmacao(Date dataHoraConfirmacao) {
		this.dataHoraConfirmacao = dataHoraConfirmacao;
	}

	@Column(name = "age_tipo")
	@Enumerated(EnumType.STRING)
	public TipoAgendamentoEnum getTipoAgendamentoEnum() {
		return tipoAgendamentoEnum;
	}

	public void setTipoAgendamentoEnum(TipoAgendamentoEnum tipoAgendamentoEnum) {
		this.tipoAgendamentoEnum = tipoAgendamentoEnum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "med_id", nullable = true)
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@OneToOne
	@JoinColumn(name = "pla_id")
	public Plano getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(Plano planoSaude) {
		this.planoSaude = planoSaude;
	}

	@OneToOne
	@JoinColumn(name = "pac_id")
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "agendamentos")
	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	@Column(name = "age_status", nullable = false)
	@Enumerated(EnumType.STRING)
	public StatusAgendamentoEnum getStatusAgendamentoEnum() {
		return statusAgendamentoEnum;
	}

	public void setStatusAgendamentoEnum(
			StatusAgendamentoEnum statusAgendamentoEnum) {
		this.statusAgendamentoEnum = statusAgendamentoEnum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cli_id", nullable = true)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	@Transient
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

	@Transient
	public String getIdStatusAgendamentoTransiente() {
		return idStatusAgendamentoTransiente;
	}

	public void setIdStatusAgendamentoTransiente(
			String idStatusAgendamentoTransiente) {
		this.idStatusAgendamentoTransiente = idStatusAgendamentoTransiente;
	}

	@Transient
	public String getIdTipoAgendamentoTransiente() {
		if (this.tipoAgendamentoEnum != null) {
			this.idTipoAgendamentoTransiente = String
					.valueOf(this.tipoAgendamentoEnum.getCodigo());
		}
		return idTipoAgendamentoTransiente;
	}

	public void setIdTipoAgendamentoTransiente(
			String idTipoAgendamentoTransiente) {
		this.idTipoAgendamentoTransiente = idTipoAgendamentoTransiente;
	}

	@Column(name = "age_tipo_agendamento_consulta")
	@Enumerated(EnumType.STRING)
	public TipoAgendamentoConsultaEnum getTipoAgendamentoConsultaEnum() {
		return tipoAgendamentoConsultaEnum;
	}

	@Column(name = "age_turno_agendamento")
	@Enumerated(EnumType.STRING)
	public TurnoAgendamentoEnum getTurnoAgendamentoEnum() {
		return this.turnoAgendamentoEnum;
	}

	public void setTurnoAgendamentoEnum(TurnoAgendamentoEnum turnoAgendamentoEnum) {
		this.turnoAgendamentoEnum = turnoAgendamentoEnum;
	}

	public void setTipoAgendamentoConsultaEnum(
			TipoAgendamentoConsultaEnum tipoAgendentoConsultaEnum) {
		this.tipoAgendamentoConsultaEnum = tipoAgendentoConsultaEnum;
	}

	@Transient
	public String getIdTipoAgendamentoConsultaTransiente() {

		if (this.tipoAgendamentoConsultaEnum != null) {
			this.idTipoAgendamentoConsultaTransiente = String
					.valueOf(this.tipoAgendamentoConsultaEnum.getCodigo());
		}

		return idTipoAgendamentoConsultaTransiente;
	}

	public void setIdTipoAgendamentoConsultaTransiente(
			String idTipoAgendamentoConsultaTransiente) {
		this.idTipoAgendamentoConsultaTransiente = idTipoAgendamentoConsultaTransiente;
	}

	@Column(name = "age_nome_paciente_nao_cadastrado")
	public String getNomePacienteNaoCadastrado() {
		return nomePacienteNaoCadastrado;
	}

	public void setNomePacienteNaoCadastrado(String nomePacienteNaoCadastrado) {
		this.nomePacienteNaoCadastrado = nomePacienteNaoCadastrado;
	}

	@Column(name = "age_cpf_paciente_nao_cadastrado")
	public String getCpfPacienteNaoCadastrado() {
		return cpfPacienteNaoCadastrado;
	}

	public void setCpfPacienteNaoCadastrado(String cpfPacienteNaoCadastrado) {
		this.cpfPacienteNaoCadastrado = cpfPacienteNaoCadastrado;
	}

	@Column(name = "age_rg_paciente_nao_cadastrado")
	public String getRgPacienteNaoCadastrado() {
		return rgPacienteNaoCadastrado;
	}

	public void setRgPacienteNaoCadastrado(String rgPacienteNaoCadastrado) {
		this.rgPacienteNaoCadastrado = rgPacienteNaoCadastrado;
	}

	@Transient
	public String getNomePaciente() {
		if (paciente == null || paciente.getId() == null
				|| paciente.getId() == 0) {
			return nomePacienteNaoCadastrado + SEM_CADASTRO;
		} else {
			return paciente.getNome();
		}
	}
	@Transient
	public String getCpfPaciente() {
		if (paciente == null || paciente.getId() == null
				|| paciente.getId() == 0) {
			return cpfPacienteNaoCadastrado;
		} else {
			return paciente.getCpf();
		}
	}
	@Transient
	public String getRgPaciente() {
		if (paciente == null || paciente.getId() == null
				|| paciente.getId() == 0) {
			return rgPacienteNaoCadastrado;
		} else {
			return paciente.getRg();
		}
	}
	
	@Transient
	public String getTelefonePaciente() {
		if (paciente == null || paciente.getId() == null
				|| paciente.getId() == 0) {
		}
		return telefonePacienteNaoCadastrado;
	}
	
	@Transient
	public String getIdTurnoConsultaTransiente() {
		return idTurnoConsultaTransiente;
	}

	public void setIdTurnoConsultaTransiente(String idTurnoConsultaTransiente) {
		this.idTurnoConsultaTransiente = idTurnoConsultaTransiente;
	}
	
	@Column(name = "age_telefone_paciente_nao_cadastrado")
	public String getTelefonePacienteNaoCadastrado() {
		return telefonePacienteNaoCadastrado;
	}

	public void setTelefonePacienteNaoCadastrado(String telefonePacienteNaoCadastrado) {
		this.telefonePacienteNaoCadastrado = telefonePacienteNaoCadastrado;
	}
	
	@Transient
	public static Comparator<Agendamento> getComparatorPorPrioridade(){
		return new Comparator<Agendamento>() {

			@Override
			public int compare(Agendamento a1, Agendamento a2) {
				if(a1.getStatusAgendamentoEnum().equals(a2.getStatusAgendamentoEnum())){
					if(a1.getDataHoraConfirmacao() == null || a2.getDataHoraConfirmacao() == null){
						return 0;
					}else if (a1.getDataHoraConfirmacao().after(a2.getDataHoraConfirmacao())){
						return 1;
					}else{
						return -1;
					}
				}
				else if(a1.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.EM_ATENDIMENTO)){
					return -1;
				}
				else if(a2.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.EM_ATENDIMENTO)){
					return 1;
				}
				else if(a1.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.CONFIRMADO_URGENCIA)){
					return -1;
				}
				else if(a2.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.CONFIRMADO_URGENCIA)){
					return 1;
				}
				else if(a1.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.CONFIRMADO)){
					return -1;
				}
				else if(a2.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.CONFIRMADO)){
					return 1;
				}
				else if(a1.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.AGUARDANDO)){
					return -1;
				}
				else if(a2.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.AGUARDANDO)){
					return 1;
				}
				else if(a1.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.CANCELADO)){
					return -1;
				}
				else if(a2.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.CANCELADO)){
					return 1;
				}
				else if(a1.getStatusAgendamentoEnum().equals(StatusAgendamentoEnum.ATENDIDO)){
					return -1;
				}else{
					return 1;
				}
			}
		};
	}

	@Transient
	public Boolean getPacienteNovo() {
		return pacienteNovo;
	}

	public void setPacienteNovo(Boolean pacienteNovo) {
		this.pacienteNovo = pacienteNovo;
	}

	@Column(name = "age_status_pagamento")
	@Enumerated(EnumType.STRING)
	public StatusPagamentoEnum getStatusPagamentoEnum() {
		return statusPagamentoEnum;
	}

	public void setStatusPagamentoEnum(StatusPagamentoEnum statusPagamentoEnum) {
		this.statusPagamentoEnum = statusPagamentoEnum;
	}

	@Column(name = "con_condigo_consulta")
	public String getCodigoConsulta() {
		return codigoConsulta;
	}

	public void setCodigoConsulta(String codigoConsulta) {
		this.codigoConsulta = codigoConsulta;
	}
	
}

