package br.com.eclinic.entity.indice;

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
@Table(name = "indice_economico")
@SequenceGenerator(name = "sequenceIndiceEconomico", sequenceName = "indice_economico_id_seq")
public class IndiceEconomico implements Serializable {

	private static final long serialVersionUID = 2894793221780602264L;
	
	private Long id;
	private String nome;
	private String competencia;
	private BigDecimal percentual;
	private String percentualTransiente;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ind_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ind_nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "ind_competencia")
	public String getCompetencia() {
		return competencia;
	}
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	
	@Column(name = "ind_percentual")
	public BigDecimal getPercentual() {
		return percentual;
	}
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}
	
	@Transient
	public String getPercentualTransiente() {
		return percentualTransiente;
	}
	public void setPercentualTransiente(String percentualTransiente) {
		this.percentualTransiente = percentualTransiente;
	}
	

}
