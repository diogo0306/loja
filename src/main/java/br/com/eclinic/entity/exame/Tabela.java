package br.com.eclinic.entity.exame;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "tabela")
@SequenceGenerator(name = "sequenceTabela", sequenceName = "tab_id_seq")
public class Tabela implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private List<TabelaExame> tabelaExame;
	private List<Exame> listaExame;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tab_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "tab_nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "tab_descricao")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@OneToMany(mappedBy = "tabela")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE,
			org.hibernate.annotations.CascadeType.ALL ,org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	public List<TabelaExame> getTabelaExame() {
		return tabelaExame;
	}
	public void setTabelaExame(List<TabelaExame> tabelaExame) {
		this.tabelaExame = tabelaExame;
	}
	
	@Transient
	public List<Exame> getListaExame() {
		return listaExame;
	}
	public void setListaExame(List<Exame> listaExame) {
		this.listaExame = listaExame;
	}
	
	
}
