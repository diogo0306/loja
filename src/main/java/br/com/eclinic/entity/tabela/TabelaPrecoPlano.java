package br.com.eclinic.entity.tabela;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.eclinic.entity.acomodacao.Acomodacao;
import br.com.eclinic.entity.plano.Plano;

@Entity
@Table(name = "tabela_preco_plano")
@SequenceGenerator(name = "sequenceTabelaPrecoPlano", sequenceName = "tab_preco_plano_id_seq")
public class TabelaPrecoPlano implements Serializable {

	private static final long serialVersionUID = -1346249478806814017L;
	private Long id;
	private Plano plano;
	private Acomodacao acomodacao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tab_plano_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name = "pla_id")
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	
	@OneToOne
	@JoinColumn(name = "aco_id")
	public Acomodacao getAcomodacao() {
		return acomodacao;
	}
	public void setAcomodacao(Acomodacao acomodacao) {
		this.acomodacao = acomodacao;
	}
	
}
