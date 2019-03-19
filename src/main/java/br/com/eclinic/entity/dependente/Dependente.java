package br.com.eclinic.entity.dependente;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.eclinic.entity.medico.SexoEnum;
import br.com.eclinic.entity.paciente.Paciente;

@Entity
@Table(name = "dependente")
@SequenceGenerator(name = "sequenceDependente", sequenceName = "dependente_id_seq")
public class Dependente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String nome;
	private Date dataNascimento;
	private String dataFormatada;
	private String filiacaoMae;
	private SexoEnum sexo;
	private String cpf;
	private String rg;
	private String orgaoExpedidor;
	private Paciente pacienteVinculo;
	private String sexoTransiente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dep_id", nullable = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "dep_nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "dep_data_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Transient
	public String getDataFormatada() {
		if(this.dataNascimento != null){
			GregorianCalendar data = new GregorianCalendar();
			data.setTime(this.dataNascimento);
			dataFormatada = data.get(Calendar.DAY_OF_MONTH) + "/"
			+ (data.get(Calendar.MONTH) + 1) + "/"
			+ data.get(Calendar.YEAR);
		}
		return dataFormatada;
	}
	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}
	
	@Column(name = "dep_sexo")
	@Enumerated(EnumType.STRING)
	public SexoEnum getSexo() {
		return sexo;
	}
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	
	@Column(name = "dep_cpf")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(name = "dep_rg")
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	@Column(name = "dep_orgao_expedidor")
	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}
	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}
	
	@Column(name = "dep_filiacao_mae")
	public String getFiliacaoMae() {
		return filiacaoMae;
	}
	public void setFiliacaoMae(String filiacaoMae) {
		this.filiacaoMae = filiacaoMae;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pac_id", nullable = true)
	public Paciente getPacienteVinculo() {
		return pacienteVinculo;
	}
	public void setPacienteVinculo(Paciente pacienteVinculo) {
		this.pacienteVinculo = pacienteVinculo;
	}
	@Transient
	public String getSexoTransiente() {
		return sexoTransiente;
	}
	public void setSexoTransiente(String sexoTransiente) {
		this.sexoTransiente = sexoTransiente;
	}
	
	
}
