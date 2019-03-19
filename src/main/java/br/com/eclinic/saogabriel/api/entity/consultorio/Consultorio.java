package br.com.eclinic.saogabriel.api.entity.consultorio;

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
import br.com.eclinic.entity.medico.Medico;

@Entity
@Table(name = "consultorio")
public class Consultorio implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nomeConsultorio;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String telefone;
	private String celular;
	private Medico medicos;

	@Id
	@SequenceGenerator(name = "id_Sequence_consultorio", sequenceName = "ID_SEQ_ESPECIALIDADE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence_consultorio")
	@Column(name = "consul_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "consul_nome_consultorio")
	public String getNomeConsultorio() {
		return nomeConsultorio;
	}

	public void setNomeConsultorio(String nomeConsultorio) {
		this.nomeConsultorio = nomeConsultorio;
	}

	@Column(name = "consul_cep")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "consul_endereco")
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "consul_numero_consultorio")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "consul_bairro")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "consul_cidade")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "consul_telefone")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "consul_celular")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "med_id", nullable = true)
	public Medico getMedicos() {
		return medicos;
	}

	public void setMedicos(Medico medicos) {
		this.medicos = medicos;
	}
}
