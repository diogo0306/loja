package br.com.eclinic.entity.contrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.eclinic.entity.cartao.Cartao;
import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.entity.enuns.SituacaoContratoEnum;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.representante.Representante;

@Entity
@Table(name = "contrato")
@SequenceGenerator(name = "sequenceContrato", sequenceName = "contrato_id_seq")
public class Contrato implements Serializable {

	private static final long serialVersionUID = 8178092961364537489L;
	private Long id;
	private Empresa empresa;
	private List<Cartao> cartoes;
	private Date inicioVigencia;
	private Date fimVigencia;
	private String inicioVigenciaFormatada;
	private String fimVigenciaFormatada;
	private List<Representante> representantes;
	private List<Plano> planos;
	private String numero;
	private BigDecimal valorContrato;
	private BigDecimal taxa;
	private BigDecimal valorTotal;
	private String valorTaxaTransiente;
	private String valorContratoTransiente;
	private TipoPessoaContratoEnum tipoPessoaContratoEnum;
	private String codigoTipoPessoaContratoTransiente;
	private DiaVencimentoEnum diaVencimentoEnum;
	private String codigoDiaVencimentoTransiente;
	private Plano plano;
	private Paciente paciente;
	private Representante representante;
	private SituacaoContratoEnum situacaoEnum;
	private String codigoSituacaoTransiente;
	private List<CobrancaContrato> cobrancas;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "con_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "contrato")
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id", nullable = true)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pac_id", nullable = true)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@OneToOne
	@JoinColumn(name = "pla_id")
	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "cont_ini_vigencia")
	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "cont_fin_vigencia")
	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	@Transient
	public String getInicioVigenciaFormatada() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		inicioVigenciaFormatada = formatado.format(inicioVigencia);
		return inicioVigenciaFormatada;

	}

	public void setInicioVigenciaFormatada(String inicioVigenciaFormatada) {
		this.inicioVigenciaFormatada = inicioVigenciaFormatada;
	}

	@Transient
	public String getFimVigenciaFormatada() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		fimVigenciaFormatada = formatado.format(fimVigencia);
		return fimVigenciaFormatada;
	}

	public void setFimVigenciaFormatada(String fimVigenciaFormatada) {
		this.fimVigenciaFormatada = fimVigenciaFormatada;
	}

	@OneToMany(mappedBy = "contrato")
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}

	@OneToMany(mappedBy = "contrato")
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Plano> getPlanos() {
		return planos;
	}

	public void setPlanos(List<Plano> planos) {
		this.planos = planos;
	}

	@Column(name = "con_numero")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "cont_tipo_pessoa_contrato")
	@Enumerated(EnumType.STRING)
	public TipoPessoaContratoEnum getTipoPessoaContratoEnum() {
		return tipoPessoaContratoEnum;
	}

	public void setTipoPessoaContratoEnum(TipoPessoaContratoEnum tipoPessoaContratoEnum) {
		this.tipoPessoaContratoEnum = tipoPessoaContratoEnum;
	}

	@Transient
	public String getCodigoTipoPessoaContratoTransiente() {
		return codigoTipoPessoaContratoTransiente;
	}

	public void setCodigoTipoPessoaContratoTransiente(String codigoTipoPessoaContratoTransiente) {
		this.codigoTipoPessoaContratoTransiente = codigoTipoPessoaContratoTransiente;
	}

	@Column(name = "cont_dia_vencimento")
	@Enumerated(EnumType.STRING)
	public DiaVencimentoEnum getDiaVencimentoEnum() {
		return diaVencimentoEnum;
	}

	public void setDiaVencimentoEnum(DiaVencimentoEnum diaVencimentoEnum) {
		this.diaVencimentoEnum = diaVencimentoEnum;
	}

	@Transient
	public String getCodigoSituacaoTransiente() {
		return codigoSituacaoTransiente;
	}

	public void setCodigoSituacaoTransiente(String codigoSituacaoTransiente) {
		this.codigoSituacaoTransiente = codigoSituacaoTransiente;
	}

	@Transient
	public String getCodigoDiaVencimentoTransiente() {
		return codigoDiaVencimentoTransiente;
	}

	public void setCodigoDiaVencimentoTransiente(String codigoDiaVencimentoTransiente) {
		this.codigoDiaVencimentoTransiente = codigoDiaVencimentoTransiente;
	}

	@Column(name = "con_valor_cont")
	public BigDecimal getValorContrato() {
		return valorContrato;
	}

	public void setValorContrato(BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}

	@Transient
	public String getValorContratoTransiente() {
		return valorContratoTransiente;
	}

	public void setValorContratoTransiente(String valorContratoTransiente) {
		this.valorContratoTransiente = valorContratoTransiente;
	}

	@OneToOne
	@JoinColumn(name = "rep_id")
	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	@Column(name = "con_situacao_enum")
	@Enumerated(EnumType.STRING)
	public SituacaoContratoEnum getSituacaoEnum() {
		return situacaoEnum;
	}

	public void setSituacaoEnum(SituacaoContratoEnum situacaoEnum) {
		this.situacaoEnum = situacaoEnum;
	}

	@OneToMany(fetch = FetchType.LAZY)
	public List<CobrancaContrato> getCobrancas() {
		return cobrancas;
	}

	public void setCobrancas(List<CobrancaContrato> cobrancas) {
		this.cobrancas = cobrancas;
	}

	@Column(name = "con_taxa")
	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	@Transient
	public String getValorTaxaTransiente() {
		return valorTaxaTransiente;
	}

	public void setValorTaxaTransiente(String valorTaxaTransiente) {
		this.valorTaxaTransiente = valorTaxaTransiente;
	}

	@Column(name = "con_valor_total")
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
