package br.com.eclinic.entity.evento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.eclinic.entity.medico.Medico;

@Entity
@Table(name = "evento")
@SequenceGenerator(name = "sequenceEvento", sequenceName = "evento_id_seq")
public class Evento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String dataIn;
	private String fin;
	private String nome;
	private Medico medico;
	private String nomePaciente;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eve_id", nullable = false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "eve_data_inicio")
	public String getDataIn() {
		return dataIn;
	}
	public void setDataIn(String dataIn) {
		this.dataIn = dataIn;
	}
	
	@Column(name = "eve_data_final")
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	
	@Column(name = "eve_nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "med_id", nullable = true)
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	@Column(name = "eve_nome_paciente")
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	
	
	
}
