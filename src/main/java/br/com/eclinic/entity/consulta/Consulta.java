package br.com.eclinic.entity.consulta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.paciente.Paciente;

@Entity
@Table(name = "consulta")
@SequenceGenerator(name = "sequenceConsulta", sequenceName = "consulta_id_seq")
public class Consulta implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dataConsulta;
	private Paciente paciente;
	private Medico medico;
	private Agendamento agendamento;
	private List<Exame> exames;
	private BigDecimal valorConsulta;
	private String observacoesMedicas;
	private BigDecimal pesoPaciente;
	private BigDecimal alturaPaciente;
	private BigDecimal padPaciente;
	private BigDecimal pasPaciente;
	private BigDecimal frequenciaCardiacaPaciente;
	private BigDecimal temperaturaPaciente;
	private String observacaoHDA;
	private String observacaoAvaliacaoSistemas;
	private String observacaoHDConduta;
	private String observacao;
	private String cpfPagador;
	private Boolean emitirNota;
	private String tipoConsulta;
	private StatusConsultaEnum status;
	private Integer mes;
	private Integer ano;
	private String dataFormatada;
	private String valorFormatado;
	private String codigoConsulta;
	private Credenciado credenciado;

	@Column(name = "con_tipo_consulta")
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	@Column(name = "con_emitir_nota")
	public Boolean isEmitirNota() {
		return emitirNota;
	}

	public void setEmitirNota(Boolean emitirNota) {
		this.emitirNota = emitirNota;
	}

	@Column(name = "con_cpf_pagador")
	public String getCpfPagador() {
		return cpfPagador;
	}

	public void setCpfPagador(String cpfPagador) {
		this.cpfPagador = cpfPagador;
	}

	@Column(name = "con_observacao")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "con_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "con_data_consulta", nullable = false)
	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pac_id")
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "med_id", nullable = true)
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "consultas")
	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	@Column(name = "con_valor_consulta")
	public BigDecimal getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(BigDecimal valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	@Column(name = "con_observacoes_medicas")
	public String getObservacoesMedicas() {
		return observacoesMedicas;
	}

	public void setObservacoesMedicas(String observacoesMedicas) {
		this.observacoesMedicas = observacoesMedicas;
	}

	@Column(name = "con_peso_paciente")
	public BigDecimal getPesoPaciente() {
		return pesoPaciente;
	}

	public void setPesoPaciente(BigDecimal pesoPaciente) {
		this.pesoPaciente = pesoPaciente;
	}

	@Column(name = "con_altura_paciente")
	public BigDecimal getAlturaPaciente() {
		return alturaPaciente;
	}

	public void setAlturaPaciente(BigDecimal alturaPaciente) {
		this.alturaPaciente = alturaPaciente;
	}

	@Column(name = "con_pad_paciente")
	public BigDecimal getPadPaciente() {
		return padPaciente;
	}

	public void setPadPaciente(BigDecimal padPaciente) {
		this.padPaciente = padPaciente;
	}

	@Column(name = "con_pas_paciente")
	public BigDecimal getPasPaciente() {
		return pasPaciente;
	}

	public void setPasPaciente(BigDecimal pasPaciente) {
		this.pasPaciente = pasPaciente;
	}

	@Column(name = "con_frequencia_cardiaca_paciente")
	public BigDecimal getFrequenciaCardiacaPaciente() {
		return frequenciaCardiacaPaciente;
	}

	public void setFrequenciaCardiacaPaciente(BigDecimal frequenciaCardiacaPaciente) {
		this.frequenciaCardiacaPaciente = frequenciaCardiacaPaciente;
	}

	@Column(name = "con_temperatura_paciente")
	public BigDecimal getTemperaturaPaciente() {
		return temperaturaPaciente;
	}

	public void setTemperaturaPaciente(BigDecimal temperaturaPaciente) {
		this.temperaturaPaciente = temperaturaPaciente;
	}

	@Column(name = "con_observacao_hda")
	public String getObservacaoHDA() {
		return observacaoHDA;
	}

	public void setObservacaoHDA(String observacaoHDA) {
		this.observacaoHDA = observacaoHDA;
	}

	@Column(name = "con_observacoes_avaliacao_sistemas")
	public String getObservacaoAvaliacaoSistemas() {
		return observacaoAvaliacaoSistemas;
	}

	public void setObservacaoAvaliacaoSistemas(String observacaoAvaliacaoSistemas) {
		this.observacaoAvaliacaoSistemas = observacaoAvaliacaoSistemas;
	}

	@Column(name = "con_observacao_hd_conduta")
	public String getObservacaoHDConduta() {
		return observacaoHDConduta;
	}

	public void setObservacaoHDConduta(String observacaoHDConduta) {
		this.observacaoHDConduta = observacaoHDConduta;
	}

	@OneToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "age_id")
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	@Column(name = "con_status")
	@Enumerated(EnumType.STRING)
	public StatusConsultaEnum getStatus() {
		return status;
	}

	public void setStatus(StatusConsultaEnum status) {
		this.status = status;
	}

	@Column(name = "con_mes")
	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	@Column(name = "con_ano")
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Transient
	public String getDataFormatada() {
		if (this.dataConsulta != null) {
			Calendar calendario = new GregorianCalendar();
			calendario.setTime(dataConsulta);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.setCalendar(calendario);
			this.dataFormatada = format.format(calendario.getTime());
		}
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	@Transient
	public String getValorFormatado() {
		return valorFormatado;
	}

	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}

	@Column(name = "con_codigo_consulta")
	public String getCodigoConsulta() {
		return codigoConsulta;
	}

	public void setCodigoConsulta(String codigoConsulta) {
		this.codigoConsulta = codigoConsulta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cre_id")
	public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}

}
