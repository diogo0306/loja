package br.com.eclinic.entity.contrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.eclinic.entity.agendamento.StatusPagamentoEnum;
import br.com.eclinic.entity.paciente.Paciente;

@Entity
@Table(name = "cobranca_contrato")
@SequenceGenerator(name = "sequenceCobrancaContrato", sequenceName = "cobranca_contrato_id_seq")
public class CobrancaContrato implements Serializable {

	private static final long serialVersionUID = -2736552925023426069L;
	private Long id;
	private String nome;
	private BigDecimal valor;
	private String valorTransiente;
	private String competencia;
	private Contrato contrato;
	private int dataCobranca;
	private Date dataVencimento;
	private Date dataPagamento;
	private Paciente pacienteVinculo;
	private Integer parcela;
	private String dataFormatada;
	private StatusPagamentoEnum statusPagamento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cob_cont_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cob_data")
	public int getDataCobranca() {
		return dataCobranca;
	}

	public void setDataCobranca(int dataCobranca) {
		this.dataCobranca = dataCobranca;
	}

	@Column(name = "cob_cont_nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cob_cont_valor")
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Transient
	public String getValorTransiente() {
		return valorTransiente;
	}

	public void setValorTransiente(String valorTransiente) {
		this.valorTransiente = valorTransiente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "con_id")
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	@Column(name = "cob_cont_competencia")
	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pac_id", nullable = true)
	public Paciente getPacienteVinculo() {
		return pacienteVinculo;
	}

	public void setPacienteVinculo(Paciente pacienteVinculo) {
		this.pacienteVinculo = pacienteVinculo;
	}

	@Column(name = "cob_cont_data_vencimento")
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Column(name = "cob_cont_data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "cob_cont_parcela")
	public Integer getParcela() {
		return parcela;
	}

	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}

	@Transient
	public String getDataFormatada() {
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	@Column(name = "cob_cont_status_pagamento")
	@Enumerated(EnumType.STRING)
	public StatusPagamentoEnum getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamentoEnum statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	
}
