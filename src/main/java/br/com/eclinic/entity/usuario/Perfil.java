package br.com.eclinic.entity.usuario;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.Transient;

import br.com.eclinic.entity.cliente.Cliente;

@Entity
@Table(name = "per_perfil")
@SequenceGenerator(name = "sequencePerfil", sequenceName = "per_perfil_per_id_seq")
public class Perfil implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private List<Funcionalidade> listaFuncionalidadesUsuario;
	private Cliente cliente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "per_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "per_descricao")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Transient
	public List<Funcionalidade> getListaFuncionalidadesUsuario() {
		return listaFuncionalidadesUsuario;
	}
	public void setListaFuncionalidadesUsuario(
			List<Funcionalidade> listaFuncionalidadesUsuario) {
		this.listaFuncionalidadesUsuario = listaFuncionalidadesUsuario;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cli_id", nullable = true)
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
