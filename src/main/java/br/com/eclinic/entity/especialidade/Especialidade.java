package br.com.eclinic.entity.especialidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eclinic.entity.medico.Medico;

@Entity
@Table(name = "especialidade")
@SequenceGenerator(name = "sequenceEspecialidade", sequenceName = "espec_id_seq")
public class Especialidade implements Serializable {

	private static final long serialVersionUID = -4290901370105338789L;
	private Long id;
	private String especialidade;
	private List<Medico> medicos;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "espec_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Especialidade é obrigatório.")
	@Column(name = "espec_descricao")
	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@OneToMany(mappedBy = "especialidade")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
}