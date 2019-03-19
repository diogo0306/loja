package br.com.eclinic.entity.motivo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "motivo")
@SequenceGenerator(name = "sequenceMotivo", sequenceName = "motivo_id_seq")
public class Motivo implements Serializable{

	private static final long serialVersionUID = -7631332348891600957L;
	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mot_id", nullable = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
