package br.com.eclinic.entity.usuarioLoja;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "usuarioLoja")
@SequenceGenerator(name = "sequenceUsuarioLoja", sequenceName = "usuarioLoja_id_seq")
public class UsuarioLoja implements Serializable {

	private static final long serialVersionUID = 2011394181924553077L;
	private Long id;
	private String nomeUsuario;
	private String senha;
	private TipoUsuarioLojaEnum tipoUsuarioLojaEnum;
	private String codigoTipoUsuarioLojaTransiente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuarioLoja_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "usuarioLoja_nome_usuario")
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Column(name = "usuarioLoja_senha")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "usuarioLoja_tipo_usuario")
	@Enumerated(EnumType.STRING)
	public TipoUsuarioLojaEnum getTipoUsuarioLojaEnum() {
		return tipoUsuarioLojaEnum;
	}

	public void setTipoUsuarioLojaEnum(TipoUsuarioLojaEnum tipoUsuarioLojaEnum) {
		this.tipoUsuarioLojaEnum = tipoUsuarioLojaEnum;
	}

	@Transient
	public String getCodigoTipoUsuarioLojaTransiente() {
		return codigoTipoUsuarioLojaTransiente;
	}

	public void setCodigoTipoUsuarioLojaTransiente(String codigoTipoUsuarioLojaTransiente) {
		this.codigoTipoUsuarioLojaTransiente = codigoTipoUsuarioLojaTransiente;
	}

}
