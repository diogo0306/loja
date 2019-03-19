package br.com.eclinic.entity.solicitacao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.representante.Representante;

@Entity
@Table(name = "solicitacao")
@SequenceGenerator(name = "sequenceSolicitacao", sequenceName = "solicitacao_id_seq")
public class Solicitacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String cpf;
	private String senha;
	private String status;
	private Date data;
	private String dataFormatada;
	private String celular;
	private String telefone;
	private String tipo;
	private String motivo;
	private byte[] imagem;
	private Representante representante;
	private Paciente paciente;
	private Medico medico;
	private String qreCrm;
	private String especialidade;
	private String message;
	private Credenciado credenciado;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "slc_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "slc_nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "slc_cpf")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "slc_senha")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "slc_status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "slc_data")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Transient
	public String getDataFormatada() {
		if (this.data != null) {
			GregorianCalendar dataConfec = new GregorianCalendar();
			dataConfec.setTime(this.data);
			dataFormatada = dataConfec.get(Calendar.DAY_OF_MONTH) + "/" + (dataConfec.get(Calendar.MONTH) + 1) + "/"
					+ dataConfec.get(Calendar.YEAR);
		}
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	@Column(name = "slc_celular")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Lob
	@Column(name = "slc_imagem")
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Column(name = "slc_tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "slc_telefone")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "slc_motivo")
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rep_id")
	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pac_id")
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "med_id")
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cred_id")
	public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}

	@Column(name = "slc_qre_crm")
	public String getQreCrm() {
		return qreCrm;
	}

	public void setQreCrm(String qreCrm) {
		this.qreCrm = qreCrm;
	}

	@Column(name = "slc_especialidade")
	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@Transient
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
