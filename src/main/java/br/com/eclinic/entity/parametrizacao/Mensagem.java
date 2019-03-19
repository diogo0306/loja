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
@Table(name = "mensagem")
@SequenceGenerator(name = "sequenceMensagem", sequenceName = "mensagem_id_seq")
public class Mensagem implements Serializable{

	private static final long serialVersionUID = 3865482545964482098L;
	
	private Long id;
	private String mensagem;
	private Parametrizacao parametrizacao;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "men_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "men_mensagem")
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
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
