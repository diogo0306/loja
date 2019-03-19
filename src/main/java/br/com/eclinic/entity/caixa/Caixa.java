package br.com.eclinic.entity.caixa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "caixa")
@SequenceGenerator(name = "sequenceCaixa", sequenceName = "cax_id_seq")
public class Caixa implements Serializable {

	private static final long serialVersionUID = 7162015602089722402L;
	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cax_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
