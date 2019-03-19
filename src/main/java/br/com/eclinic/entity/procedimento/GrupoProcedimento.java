package br.com.eclinic.entity.procedimento;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eclinic.entity.plano.Plano;

@Entity
@Table(name = "grupo_procedimento")
@SequenceGenerator(name = "sequenceGrupoProcedimento", sequenceName = "grupo_procedimento_id_seq")
public class GrupoProcedimento implements Serializable {

	private static final long serialVersionUID = 5309419867275117376L;
	private Long id;
	private String codigo;
	private TipoAtendimentoEnum tipoAtendimento;
	private String tipoTransiente;
	private String nome;
	private Boolean imprimir;
	private BigDecimal valorPagamento;
	private BigDecimal valorCobranca;
	private String horainicial;
	private String horarFinal;
	
	
	
	
	private Plano plano;
	private List<RelacaoGrupoProcedimento> relacoes;
	private List<Procedimento> procedimentos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grup_proc_id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank @NotEmpty(message = "Informe o nome.")
	@Column(name = "grup_proc_nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pla_id")
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	
	@Column(name = "grup_proc_codigo")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "grup_proc_tipo_atendimento")
	@Enumerated(EnumType.STRING)
	public TipoAtendimentoEnum getTipoAtendimento() {
		return tipoAtendimento;
	}
	public void setTipoAtendimento(TipoAtendimentoEnum tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}
	
	@Column(name = "grup_proc_imprimir")
	public Boolean getImprimir() {
		return imprimir;
	}
	public void setImprimir(Boolean imprimir) {
		this.imprimir = imprimir;
	}
	
	@Column(name = "grup_proc_valor_pagamento")
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	
	@Column(name = "grup_proc_valor_cobranca")
	public BigDecimal getValorCobranca() {
		return valorCobranca;
	}
	public void setValorCobranca(BigDecimal valorCobranca) {
		this.valorCobranca = valorCobranca;
	}
	@Column(name = "grup_proc_hora_inicial")
	public String getHorainicial() {
		return horainicial;
	}
	public void setHorainicial(String horainicial) {
		this.horainicial = horainicial;
	}
	
	@Column(name = "grup_proc_hora_final")
	public String getHorarFinal() {
		return horarFinal;
	}
	public void setHorarFinal(String horarFinal) {
		this.horarFinal = horarFinal;
	}
	
	
	@NotEmpty(message = "Selecione o tipo.")
	@Transient
	public String getTipoTransiente() {
		return tipoTransiente;
	}
	public void setTipoTransiente(String tipoTransiente) {
		this.tipoTransiente = tipoTransiente;
	}
	
	@OneToMany(mappedBy = "grupoProcedimento")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE,
			org.hibernate.annotations.CascadeType.ALL ,org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	public List<RelacaoGrupoProcedimento> getRelacoes() {
		return relacoes;
	}
	public void setRelacoes(List<RelacaoGrupoProcedimento> relacoes) {
		this.relacoes = relacoes;
	}
	
	@Transient
	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}
	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}
	
}
