package br.com.eclinic.entity.credenciado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.eclinic.entity.enuns.TipoPessoaEnum;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.entity.medico.UfEnum;

@Entity
@Table(name = "credenciado")
@SequenceGenerator(name = "sequenceCredenciado", sequenceName = "credenciado_id_seq")
public class Credenciado implements Serializable {

	private static final long serialVersionUID = -5598586790750521294L;

	private Long id;
	private TipoPessoaEnum tipoPessoa;
	private String codigo;
	private String nome;
	private SituacaoEnum situacao;
	private Date data;
	private String cnes;
	private String numDepIrrf;
	private Boolean considerarPercentualAcres;
	private String codBanco;
	private String agencia;
	private String conta;
	private String nomeBanco;
	private String codRepasse;
	private BigDecimal percentualDesc;
	private BigDecimal percentualMateriais;
	private BigDecimal percentualMedicamentos;
	private Date dataPercentual;
	private Boolean todasEspecialidades;
	private String codigoTipoPessoaTransiente;
	private String codigoSituacaoTransiente;
	private String dataPercentualFormatada;
	private String dataFormatada;
	private List<Especialidade> especialidades;
	private List<Consulta> consultas;
	private String cep;
	private String cidade;
	private String logradouro;
	private String telefone;
	private String email;
	private String celular;
	private String cpf;
	private String cnpj;
	private String rg;
	private String bairro;
	private String numero;
	private BigDecimal valorCobrado;
	private BigDecimal valorPago;
	private String valorCobradoTransiente;
	private String valorPagoTransiente;
	private Especialidade especialidade;
	private SituacaoEnum situacaoEnum;
	private UfEnum ufEnum;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cre_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cre_tipo_pessoa")
	@Enumerated(EnumType.STRING)
	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	@Column(name = "cre_codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@NotNull
	@NotBlank(message = "Nome é obrigatório.")
	@Column(name = "cre_nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cre_situacao")
	@Enumerated(EnumType.STRING)
	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "cre_data")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "cre_cnes")
	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	@Column(name = "cre_num_dep_irrf")
	public String getNumDepIrrf() {
		return numDepIrrf;
	}

	public void setNumDepIrrf(String numDepIrrf) {
		this.numDepIrrf = numDepIrrf;
	}

	@Column(name = "cre_considerar_percentual_acres")
	public Boolean getConsiderarPercentualAcres() {
		return considerarPercentualAcres;
	}

	public void setConsiderarPercentualAcres(Boolean considerarPercentualAcres) {
		this.considerarPercentualAcres = considerarPercentualAcres;
	}

	@Column(name = "cre_cod_banco")
	public String getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	@Column(name = "cre_agencia")
	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	@Column(name = "cre_conta")
	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	@Column(name = "cre_nome_banco")
	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	@Column(name = "cre_cod_repasse")
	public String getCodRepasse() {
		return codRepasse;
	}

	public void setCodRepasse(String codRepasse) {
		this.codRepasse = codRepasse;
	}

	@Column(name = "cre_percentual_desc")
	public BigDecimal getPercentualDesc() {
		return percentualDesc;
	}

	public void setPercentualDesc(BigDecimal percentualDesc) {
		this.percentualDesc = percentualDesc;
	}

	@Column(name = "cre_percentual_materiais")
	public BigDecimal getPercentualMateriais() {
		return percentualMateriais;
	}

	public void setPercentualMateriais(BigDecimal percentualMateriais) {
		this.percentualMateriais = percentualMateriais;
	}

	@Column(name = "cre_percentual_medicamentos")
	public BigDecimal getPercentualMedicamentos() {
		return percentualMedicamentos;
	}

	public void setPercentualMedicamentos(BigDecimal percentualMedicamentos) {
		this.percentualMedicamentos = percentualMedicamentos;
	}

	@Column(name = "data_percentual")
	public Date getDataPercentual() {
		return dataPercentual;
	}

	public void setDataPercentual(Date dataPercentual) {
		this.dataPercentual = dataPercentual;
	}

	@Column(name = "cre_todas_especialidades")
	public Boolean getTodasEspecialidades() {
		return todasEspecialidades;
	}

	public void setTodasEspecialidades(Boolean todasEspecialidades) {
		this.todasEspecialidades = todasEspecialidades;
	}

	@Transient
	public String getCodigoTipoPessoaTransiente() {
		return codigoTipoPessoaTransiente;
	}

	public void setCodigoTipoPessoaTransiente(String codigoTipoPessoaTransiente) {
		this.codigoTipoPessoaTransiente = codigoTipoPessoaTransiente;
	}

	@Transient
	public String getCodigoSituacaoTransiente() {
		return codigoSituacaoTransiente;
	}

	public void setCodigoSituacaoTransiente(String codigoSituacaoTransiente) {
		this.codigoSituacaoTransiente = codigoSituacaoTransiente;
	}

	@Transient
	public String getDataPercentualFormatada() {
		return dataPercentualFormatada;
	}

	public void setDataPercentualFormatada(String dataPercentualFormatada) {
		this.dataPercentualFormatada = dataPercentualFormatada;
	}

	@Transient
	public String getDataFormatada() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		dataFormatada = formatado.format(data);
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	@Column(name = "cre_cep")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "cre_cidade")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "cre_logradouro")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "cre_telefone")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "cre_email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "cre_celular")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "cre_cpf")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "cre_cnpj")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name = "cre_rg")
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(name = "cre_bairro")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "cre_numero")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "cre_valor_cobrado")
	public BigDecimal getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(BigDecimal valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	@Column(name = "cre_valor_pago")
	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	@Transient
	public String getValorCobradoTransiente() {
		return valorCobradoTransiente;
	}

	public void setValorCobradoTransiente(String valorCobradoTransiente) {
		this.valorCobradoTransiente = valorCobradoTransiente;
	}

	@Transient
	public String getValorPagoTransiente() {
		return valorPagoTransiente;
	}

	public void setValorPagoTransiente(String valorPagoTransiente) {
		this.valorPagoTransiente = valorPagoTransiente;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "especialidades_credenciado", joinColumns = {
			@JoinColumn(name = "cre_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "espec_id", nullable = false, updatable = false) })
	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	@OneToMany(mappedBy = "credenciado")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	@Transient
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	@Column(name = "med_situacao")
	@Enumerated(EnumType.STRING)
	public SituacaoEnum getSituacaoEnum() {
		return situacaoEnum;
	}

	public void setSituacaoEnum(SituacaoEnum situacaoEnum) {
		this.situacaoEnum = situacaoEnum;
	}

	@Column(name = "med_uf")
	@Enumerated(EnumType.STRING)
	public UfEnum getUfEnum() {
		return ufEnum;
	}

	public void setUfEnum(UfEnum ufEnum) {
		this.ufEnum = ufEnum;
	}
}
