package br.com.eclinic.entity.paciente;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "cobranca")
@SequenceGenerator(name = "sequenceCobranca", sequenceName = "cobranca_id_seq")
public class Cobranca implements Serializable {

	private static final long serialVersionUID = -1844637540760421883L;
	private Long id;
	private Integer diaVencimento;
	private TipoTaxaEnum tipoTaxaEnum;
	private BigDecimal taxaAdesao;
	private String codigoBanco;
	private String nomeBanco;
	private String agencia;
	private String conta;
	private String nomeTitular;
	private String cpfTitular;
	private String codigoBancoDebito;
	private String nomeBancoDebito;
	private String agenciaDebito;
	private String contaDebito;
	private TipoControleEnum tipoControleEnum;
	private Boolean gerarCobrancaInativos;
	private Boolean incluirProRata;
	private Boolean buscarValoresUltimaVigencia;
	private Boolean debitoAutomatico;
	private Long idPaciente;
	private String nomePaciente;
	private String tipoTaxaTransiente;
	private String tipoControleTransiente;
	private String taxaAdesaoTransiente;
	private String taxaAdesaoPercentualTransiente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cob_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "cob_tipo_taxa")
	@Enumerated(EnumType.STRING)
	public TipoTaxaEnum getTipoTaxaEnum() {
		return tipoTaxaEnum;
	}
	public void setTipoTaxaEnum(TipoTaxaEnum tipoTaxaEnum) {
		this.tipoTaxaEnum = tipoTaxaEnum;
	}
	
	
	@Column(name = "cob_dia_vencimento")
	@NotNull(message = "Informe o dia do vencimento.")
	public Integer getDiaVencimento() {
		return diaVencimento;
	}
	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	
	@Column(name = "cob_taxa_adesao")
	public BigDecimal getTaxaAdesao() {
		return taxaAdesao;
	}
	public void setTaxaAdesao(BigDecimal taxaAdesao) {
		this.taxaAdesao = taxaAdesao;
	}
	
	@Column(name = "cob_codigo_banco")
	public String getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	
	@Column(name = "cob_nome_banco")
	public String getNomeBanco() {
		return nomeBanco;
	}
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	
	@Column(name = "cob_agencia")
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	@Column(name = "cob_conta")
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	
	@Column(name = "cob_nome_titular")
	@NotEmpty @NotBlank(message = "Informe o nome do titular.")
	public String getNomeTitular() {
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	
	@Column(name = "cob_cpf_titular")
	@CPF(message = "Informe um CPF válido.")
	@NotEmpty @NotBlank(message = "Informe o CPF do titular.")
	public String getCpfTitular() {
		return cpfTitular;
	}
	public void setCpfTitular(String cpfTitular) {
		this.cpfTitular = cpfTitular;
	}
	
	@Column(name = "cob_codigo_banco_debito")
	public String getCodigoBancoDebito() {
		return codigoBancoDebito;
	}
	public void setCodigoBancoDebito(String codigoBancoDebito) {
		this.codigoBancoDebito = codigoBancoDebito;
	}
	
	@Column(name = "cob_agencia_debito")
	public String getAgenciaDebito() {
		return agenciaDebito;
	}
	public void setAgenciaDebito(String agenciaDebito) {
		this.agenciaDebito = agenciaDebito;
	}
	
	@Column(name = "cob_conta_debito")
	public String getContaDebito() {
		return contaDebito;
	}
	public void setContaDebito(String contaDebito) {
		this.contaDebito = contaDebito;
	}
	
	@Column(name = "cob_tipo_controle")
	@Enumerated(EnumType.STRING)
	public TipoControleEnum getTipoControleEnum() {
		return tipoControleEnum;
	}
	public void setTipoControleEnum(TipoControleEnum tipoControleEnum) {
		this.tipoControleEnum = tipoControleEnum;
	}
	
	@Column(name = "cob_gerar_cobranca_inativos")
	public Boolean getGerarCobrancaInativos() {
		return gerarCobrancaInativos;
	}
	public void setGerarCobrancaInativos(Boolean gerarCobrancaInativos) {
		this.gerarCobrancaInativos = gerarCobrancaInativos;
	}
	
	@Column(name = "cob_incluir_pro_rata")
	public Boolean getIncluirProRata() {
		return incluirProRata;
	}
	public void setIncluirProRata(Boolean incluirProRata) {
		this.incluirProRata = incluirProRata;
	}
	
	@Column(name = "cob_buscar_valores_ultima_vigencia")
	public Boolean getBuscarValoresUltimaVigencia() {
		return buscarValoresUltimaVigencia;
	}
	public void setBuscarValoresUltimaVigencia(Boolean buscarValoresUltimaVigencia) {
		this.buscarValoresUltimaVigencia = buscarValoresUltimaVigencia;
	}
	
	@Column(name = "cob_nome_banco_debito")	
	public String getNomeBancoDebito() {
		return nomeBancoDebito;
	}
	public void setNomeBancoDebito(String nomeBancoDebito) {
		this.nomeBancoDebito = nomeBancoDebito;
	}
	
	@Transient
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	
	@Transient
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	
	@Column(name = "cob_debito_automatico")
	public Boolean getDebitoAutomatico() {
		return debitoAutomatico;
	}
	public void setDebitoAutomatico(Boolean debitoAutomatico) {
		this.debitoAutomatico = debitoAutomatico;
	}
	
	@Transient
	public String getTipoTaxaTransiente() {
		return tipoTaxaTransiente;
	}
	public void setTipoTaxaTransiente(String tipoTaxaTransiente) {
		this.tipoTaxaTransiente = tipoTaxaTransiente;
	}
	
	@Transient
	public String getTipoControleTransiente() {
		return tipoControleTransiente;
	}
	public void setTipoControleTransiente(String tipoControleTransiente) {
		this.tipoControleTransiente = tipoControleTransiente;
	}
	
	@Transient
	public String getTaxaAdesaoTransiente() {
		return taxaAdesaoTransiente;
	}
	public void setTaxaAdesaoTransiente(String taxaAdesaoTransiente) {
		this.taxaAdesaoTransiente = taxaAdesaoTransiente;
	}
	
	@Transient
	public String getTaxaAdesaoPercentualTransiente() {
		return taxaAdesaoPercentualTransiente;
	}
	public void setTaxaAdesaoPercentualTransiente(String taxaAdesaoPercentualTransiente) {
		this.taxaAdesaoPercentualTransiente = taxaAdesaoPercentualTransiente;
	}
	
}
