package br.com.eclinic.entity.acomodacao;

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

import br.com.eclinic.entity.plano.Plano;

@Entity
@Table(name = "acomodacao")
@SequenceGenerator(name = "sequenceAcomodacao", sequenceName = "acomodacao_id_seq")
public class Acomodacao implements Serializable {

	private static final long serialVersionUID = -1834095306201904604L;
	private Long id;
	private Plano plano;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aco_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pla_id", nullable = false)
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
}
