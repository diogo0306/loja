package br.com.eclinic.entity.procedimento;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "procedimento")
@SequenceGenerator(name = "sequenceProcedimento", sequenceName = "procedimento_id_seq")
public class Procedimento implements Serializable {

	private static final long serialVersionUID = 1545579515285987110L;
	private Long id;
	private String nome;
	private Long codigo;
	private BigDecimal valorPagamento;
	private BigDecimal valorCobranca;	
	private Integer procedimentoInicial;
	private Integer procedimentoFinal;
	private BigDecimal quantidadeChInicial;
	private BigDecimal quantidadeChFinal;
	private Integer chInicial;
	private Integer chFinal;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proc_id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message = "Nome é obrigatório.")
	@Column(name = "proc_nome")	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "proc_codigo")
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@NotNull(message = "Valor de pagamento é obrigatório.")
	@Column(name = "proc_valor_pagamento")
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	
	@NotNull(message = "Valor de cobrança é obrigatório.")
	@Column(name = "proc_valor_cobranca")
	public BigDecimal getValorCobranca() {
		return valorCobranca;
	}
	public void setValorCobranca(BigDecimal valorCobranca) {
		this.valorCobranca = valorCobranca;
	}
	
	@Column(name = "proc_procedimento_inicial")
	public Integer getProcedimentoInicial() {
		return procedimentoInicial;
	}
	public void setProcedimentoInicial(Integer procedimentoInicial) {
		this.procedimentoInicial = procedimentoInicial;
	}
	
	@Column(name = "proc_procedimento_final")
	public Integer getProcedimentoFinal() {
		return procedimentoFinal;
	}
	public void setProcedimentoFinal(Integer procedimentoFinal) {
		this.procedimentoFinal = procedimentoFinal;
	}
	
	@Column(name = "proc_quantidade_inicial")
	public BigDecimal getQuantidadeChInicial() {
		return quantidadeChInicial;
	}
	public void setQuantidadeChInicial(BigDecimal quantidadeChInicial) {
		this.quantidadeChInicial = quantidadeChInicial;
	}
	
	@Column(name = "proc_quantidade_final")
	public BigDecimal getQuantidadeChFinal() {
		return quantidadeChFinal;
	}
	public void setQuantidadeChFinal(BigDecimal quantidadeChFinal) {
		this.quantidadeChFinal = quantidadeChFinal;
	}
	
	@Transient
	public Integer getChInicial() {
		chInicial = quantidadeChInicial.intValue();
		return chInicial;
	}
	public void setChInicial(Integer chInicial) {
		this.chInicial = chInicial;
	}
	
	@Transient
	public Integer getChFinal() {
		chFinal = quantidadeChFinal.intValue();
		return chFinal;
	}
	public void setChFinal(Integer chFinal) {
		this.chFinal = chFinal;
	}
	
}
