package br.com.eclinic.entity.endereco;

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

import br.com.eclinic.entity.medico.UfEnum;

@Entity
@Table(name = "endereco")
@SequenceGenerator(name = "sequenceEndereco", sequenceName = "end_endereco_end_id_seq")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String bairro;
	private String logradouro;
	private String cep;
	private String complemento;
	private String telefone;
	private String celular;
	private String email;
	private String numero;
	private String cidade;
	private UfEnum estado; 
	
	private String cidadeTransiente;
	private String estadoTransiente;
	private String paisTransiente;
	
	
	@Column(name = "end_cidade")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "end_estado", nullable=false)
	@Enumerated(EnumType.STRING)
	public UfEnum getEstado() {
		return estado;
	}

	public void setEstado(UfEnum estado) {
		this.estado = estado;
	}

	@Transient
	public String getCidadeTransiente() {
		return cidadeTransiente;
	}

	public void setCidadeTransiente(String cidadeTransiente) {
		this.cidadeTransiente = cidadeTransiente;
	}

	@Transient
	public String getEstadoTransiente() {
		return estadoTransiente;
	}

	public void setEstadoTransiente(String estadoTransiente) {
		this.estadoTransiente = estadoTransiente;
	}

	@Transient
	public String getPaisTransiente() {
		return paisTransiente;
	}

	public void setPaisTransiente(String paisTransiente) {
		this.paisTransiente = paisTransiente;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "end_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "end_bairro")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "end_logradouro")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "end_cep")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "end_complemento")
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Column(name = "end_telefone")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "end_celular")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "end_email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "end_numero")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
