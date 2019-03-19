package br.com.eclinic.entity.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "fun_funcionalidade")
@SequenceGenerator(name = "sequenceFuncionalidade", sequenceName = "fun_funcionalidade_fun_id_seq")
public class Funcionalidade implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private String nome;
	private String caminho;
	private String secao;
	private List<Operacao> listaOperacoesFuncionalidade;
	private Long indiceSecao;
	
	
	@Column(name = "fun_indice_secao")
	public Long getIndiceSecao() {
		return indiceSecao;
	}
	public void setIndiceSecao(Long indiceSecao) {
		this.indiceSecao = indiceSecao;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fun_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "fun_descricao")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionalidade other = (Funcionalidade) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Transient
	public List<Operacao> getListaOperacoesFuncionalidade() {
		return listaOperacoesFuncionalidade;
	}
	public void setListaOperacoesFuncionalidade(
			List<Operacao> listaOperacoesFuncionalidade) {
		this.listaOperacoesFuncionalidade = listaOperacoesFuncionalidade;
	}
	
	@Column(name = "fun_nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "fun_caminho")
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	@Column(name = "fun_secao")
	public String getSecao() {
		return secao;
	}
	public void setSecao(String secao) {
		this.secao = secao;
	}

}
