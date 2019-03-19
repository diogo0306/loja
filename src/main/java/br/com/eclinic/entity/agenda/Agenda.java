package br.com.eclinic.entity.agenda;

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

@Entity
@Table(name = "agenda")
@SequenceGenerator(name = "sequenceAgenda", sequenceName = "agenda_id_seq")
public class Agenda implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean domingo;
	private Boolean segunda;
	private Boolean terca;
	private Boolean quarta;
	private Boolean quinta;
	private Boolean sexta;
	private Boolean sabado;
	private Integer quantidadeConsultas;
	private BigDecimal duracaoConsulta;
	private Integer duracaoMinutos;
	private String quantidade;
	private String duracao;
	
	@Column(name = "age_quantidade_consultas")
	public Integer getQuantidadeConsultas() {
		return quantidadeConsultas;
	}
	public void setQuantidadeConsultas(Integer quantidadeConsultas) {
		this.quantidadeConsultas = quantidadeConsultas;
	}
	
	@Column(name = "age_duracao_consulta")
	public BigDecimal getDuracaoConsulta() {
		return duracaoConsulta;
	}
	public void setDuracaoConsulta(BigDecimal duracaoConsulta) {
		this.duracaoConsulta = duracaoConsulta;
	}
	
	@Transient
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	@Transient
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
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
	
	@Column(name = "age_domingo")
	public Boolean getDomingo() {
		return domingo;
	}
	public void setDomingo(Boolean domingo) {
		this.domingo = domingo;
	}
	
	@Column(name = "age_segunda")
	public Boolean getSegunda() {
		return segunda;
	}
	public void setSegunda(Boolean segunda) {
		this.segunda = segunda;
	}
	
	@Column(name = "age_terca")
	public Boolean getTerca() {
		return terca;
	}
	public void setTerca(Boolean terca) {
		this.terca = terca;
	}
	
	@Column(name = "age_quarta")
	public Boolean getQuarta() {
		return quarta;
	}
	public void setQuarta(Boolean quarta) {
		this.quarta = quarta;
	}
	
	@Column(name = "age_quinta")
	public Boolean getQuinta() {
		return quinta;
	}
	public void setQuinta(Boolean quinta) {
		this.quinta = quinta;
	}
	
	@Column(name = "age_sexta")
	public Boolean getSexta() {
		return sexta;
	}
	public void setSexta(Boolean sexta) {
		this.sexta = sexta;
	}
	
	@Column(name = "age_sabado")
	public Boolean getSabado() {
		return sabado;
	}
	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}
	
	@Column(name = "age_duracao_minutos")
	public Integer getDuracaoMinutos() {
		return duracaoMinutos;
	}
	public void setDuracaoMinutos(Integer duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}
	
}
