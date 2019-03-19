package br.com.eclinic.entity.receita;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.eclinic.entity.paciente.Paciente;

@Entity
@Table(name = "receita")
@SequenceGenerator(name = "sequenceReceita", sequenceName = "receita_id_seq")
public class Receita implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private Long id;
	private Paciente paciente;
	private List<Medicamento> medicamentos;
	private Date data;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rec_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pac_id", nullable = true)
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="receita_medicamento", 
				joinColumns={@JoinColumn(name="rec_id")}, 
				inverseJoinColumns={@JoinColumn(name="mdc_id")})
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	
	@Column(name = "rec_data", nullable = false)
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	

}
