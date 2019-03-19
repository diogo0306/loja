package br.com.eclinic.entity.supervisor;

import java.io.Serializable;

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
@Table(name = "responsavel")
@SequenceGenerator(name = "sequenceResponsavel", sequenceName = "responsavel_id_seq")
public class Responsavel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cpf;
	private Medico medicoVinculo;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_id", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "res_nome", nullable = true)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "res_cpf", nullable = true)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "med_id", nullable = true)
	public Medico getMedicoVinculo() {
		return medicoVinculo;
	}

	public void setMedicoVinculo(Medico medicoVinculo) {
		this.medicoVinculo = medicoVinculo;
	}
			
}
