package br.com.eclinic.entity.receita;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "substancia")
@SequenceGenerator(name = "sequenceSubstancia", sequenceName = "substancia_id_seq")
public class Substancia implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String dosagem;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDosagem() {
		return dosagem;
	}
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

}
