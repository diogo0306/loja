package br.com.eclinic.entity.jornada;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.eclinic.entity.medico.Medico;

@Entity
@Table(name = "jornada")
@SequenceGenerator(name = "sequenceJornada", sequenceName = "jornada_id_seq")
public class Jornada implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String inicio;
	private String fim;
	private Date inicioJornada;
	private Date fimJornada;
	private Medico medicoVinculo;
	
	@Column(name = "jor_inicio_jornada")
	public Date getInicioJornada() {
		return inicioJornada;
	}
	public void setInicioJornada(Date inicioJornada) {
		this.inicioJornada = inicioJornada;
	}
	
	@Column(name = "jor_fim_jornada")
	public Date getFimJornada() {
		return fimJornada;
	}
	public void setFimJornada(Date fimJornada) {
		this.fimJornada = fimJornada;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jor_id", nullable = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "jor_inicio")
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	
	@Column(name = "jor_fim")
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "med_id")
	public Medico getMedicoVinculo() {
		return medicoVinculo;
	}
	public void setMedicoVinculo(Medico medicoVinculo) {
		this.medicoVinculo = medicoVinculo;
	}
}
