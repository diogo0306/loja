package br.com.eclinic.entity.representante;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.medico.SexoEnum;
import br.com.eclinic.entity.medico.UfEnum;
import br.com.eclinic.entity.supervisor.Supervisor;
import br.com.eclinic.validator.Group.CnpjGroup;
import br.com.eclinic.validator.Group.CpfGroup;
import br.com.eclinic.validator.Representante.RepresentanteGroupSequenceProvider;

@Entity
@Table(name = "representante")
@SequenceGenerator(name = "sequenceRepresentante", sequenceName = "representante_id_seq")
@GroupSequenceProvider(RepresentanteGroupSequenceProvider.class)
public class Representante implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String cpfcnpj;
	private String rg;
	private Date dataNascimento;
	private String dataNascimentoFormatada;
	private String nomePai;
	private String nomeMae;
	private String bairro;
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String cidade;
	private String telefone;
	private String telefoneSecundario;
	private String celular;
	private String email;
	private String orgaoExpedidor;
	private Supervisor supervisorVinculo;
	private Contrato contrato;

	private SexoEnum sexoEnum;
	private UfEnum ufEnum;
	private TipoRepresentanteEnum tipoRepresentanteEnum;
	private SituacaoEnum situacaoEnum;


	private String codigoUfTransiente;
	private String codigoSexoTransiente;
	private String codigoRepresentanteTransiente;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rep_id", nullable = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "rep_orgao_expedidor")
	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	@NotBlank(message = "Nome é obrigatório.")
	@Column(name = "rep_nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@CNPJ(groups = CnpjGroup.class)
	@CPF(groups = CpfGroup.class)
	@NotBlank(message = "CPF/CNPJ é obrigatório.")
	@Column(name = "rep_cpf")
	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	@Column(name = "rep_rg")
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@NotNull(message = "Data de nascimento é obrigatório.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "rep_data_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Transient
	public String getDataNascimentoFormatada() {
		if(dataNascimento != null) {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimentoFormatada = formatado.format(dataNascimento);
		}
		return dataNascimentoFormatada;

	}

	public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
		this.dataNascimentoFormatada = dataNascimentoFormatada;
	}

	@Column(name = "rep_nome_pai")
	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	@Column(name = "rep_nome_mae")
	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	@NotBlank(message = "Bairro é obrigatório.")
	@Column(name = "rep_bairro")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotBlank(message = "Endereço é obrigatório.")
	@Column(name = "rep_logradouro")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@NotBlank(message = "CEP é obrigatório.")
	@Column(name = "rep_cep")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@NotBlank(message = "Numero é obrigatório.")
	@Column(name = "rep_numero_residencia")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "rep_complemento")
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@NotBlank(message = "Município é obrigatório.")
	@Column(name = "rep_cidade")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "rep_telefone")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "rep_telefone_secundario")
	public String getTelefoneSecundario() {
		return telefoneSecundario;
	}

	public void setTelefoneSecundario(String telefoneSecundario) {
		this.telefoneSecundario = telefoneSecundario;
	}

	@Column(name = "rep_celular")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Email(message = "E-mail inválido")
	@Column(name = "rep_email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "rep_sexo")
	@Enumerated(EnumType.STRING)
	public SexoEnum getSexoEnum() {
		return sexoEnum;
	}

	public void setSexoEnum(SexoEnum sexoEnum) {
		this.sexoEnum = sexoEnum;
	}

	@Column(name = "rep_uf")
	@Enumerated(EnumType.STRING)
	public UfEnum getUfEnum() {
		return ufEnum;
	}

	public void setUfEnum(UfEnum ufEnum) {
		this.ufEnum = ufEnum;
	}

	@Column(name = "rep_tipo")
	@Enumerated(EnumType.STRING)
	public TipoRepresentanteEnum getTipoRepresentanteEnum() {
		return tipoRepresentanteEnum;
	}

	public void setTipoRepresentanteEnum(TipoRepresentanteEnum tipoRepresentanteEnum) {
		this.tipoRepresentanteEnum = tipoRepresentanteEnum;
	}

	
	@Column(name = "rep_situacao")
	@Enumerated(EnumType.STRING)
	public SituacaoEnum getSituacaoEnum() {
		return situacaoEnum;
	}

	public void setSituacaoEnum(SituacaoEnum situacaoEnum) {
		this.situacaoEnum = situacaoEnum;
	}

	@Transient
	public String getCodigoSexoTransiente() {
		return codigoSexoTransiente;
	}

	public void setCodigoSexoTransiente(String codigoSexoTransiente) {
		this.codigoSexoTransiente = codigoSexoTransiente;
	}

	@Transient
	public String getCodigoUfTransiente() {
		return codigoUfTransiente;
	}

	public void setCodigoUfTransiente(String codigoUfTransiente) {
		this.codigoUfTransiente = codigoUfTransiente;
	}

	@Transient
	public String getCodigoRepresentanteTransiente() {
		return codigoRepresentanteTransiente;
	}

	public void setCodigoRepresentanteTransiente(String codigoRepresentanteTransiente) {
		this.codigoRepresentanteTransiente = codigoRepresentanteTransiente;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sup_id")
	public Supervisor getSupervisorVinculo() {
		return supervisorVinculo;
	}

	public void setSupervisorVinculo(Supervisor supervisorVinculo) {
		this.supervisorVinculo = supervisorVinculo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "con_id")
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}
