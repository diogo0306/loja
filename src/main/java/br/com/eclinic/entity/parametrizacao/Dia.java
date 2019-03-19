package br.com.eclinic.entity.parametrizacao;

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

@Entity
@Table(name = "dia")
@SequenceGenerator(name = "sequenceDia", sequenceName = "dia_id_seq")
public class Dia implements Serializable{

	private static final long serialVersionUID = -6419027298235162920L;
	
	private Long id;
	private Long dia;
	private Parametrizacao parametrizacao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dia_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "dia_dia")
	public Long getDia() {
		return dia;
	}
	public void setDia(Long dia) {
		this.dia = dia;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "par_id", nullable = false)
	public Parametrizacao getParametrizacao() {
		return parametrizacao;
	}
	public void setParametrizacao(Parametrizacao parametrizacao) {
		this.parametrizacao = parametrizacao;
	}		
}
