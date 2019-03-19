package br.com.eclinic.entity.cartao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.entity.paciente.Paciente;

@Entity
@Table(name = "cartao")
@SequenceGenerator(name = "sequenceCartao", sequenceName = "cartao_id_seq")
public class Cartao implements Serializable {

	private static final long serialVersionUID = 5497638303897844498L;
	private Long id;
	private Contrato contrato;
	private Paciente paciente;
	private Dependente dependente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "con_id", nullable = false)
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	@OneToOne
	@JoinColumn(name = "pac_id")
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@OneToOne
	@JoinColumn(name = "dep_id")
	public Dependente getDependente() {
		return dependente;
	}
	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}
}
