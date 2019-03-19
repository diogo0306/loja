package br.com.eclinic.entity.autorizacao;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.fornecedor.Fornecedor;
import br.com.eclinic.entity.hospital.Hospital;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.usuario.Usuario;

@Entity
@Table(name = "autorizacao")
@SequenceGenerator(name = "sequenceAutorizacao", sequenceName = "auto_id_seq")
public class Autorizacao implements Serializable {

	private static final long serialVersionUID = -5941013085385059998L;
	private Long id;
	private String numeroAutorizacao;
	private Date dataAutorizacao;
	private Date dataCancelamento;
	private Date dataHoraExecucao;
	private BigDecimal valor;
	private BigDecimal valorPago;
	private BigDecimal valorPagoSala;
	private BigDecimal valorPagoMaterial;
	private TipoAutorizacaoEnum tipo;
	private StatusAutorizacaoEnum status;
	private SituacaoAutorizacaoEnum situacao;
	private CanalEnum canal;
	private String nomeSolicitante;
	private String observacoes;
	private String orientacoes;
	private Paciente paciente;
	private Credenciado credenciado;
	private Hospital hospital;
	private Fornecedor fornecedor;
	private String codicoTipoTransiente;
	private String dataFormatada;
	private String valorFormatado;
	private String valorPagoFormatado;
	private String valorPagoSalaFormatado;
	private String valorPagoMaterialFormatado;
	private String valorTotalFormatado;
	private Exame exame;
	private Usuario usuarioAutorizado;
	private List<Exame> exames;
	private String dataExecucaoFormatada;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auto_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "auto_numero_autorizacao")
	public String getNumeroAutorizacao() {
		return numeroAutorizacao;
	}
	public void setNumeroAutorizacao(String numeroAutorizacao) {
		this.numeroAutorizacao = numeroAutorizacao;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "auto_data_autorizacao")
	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}
	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "auto_data_cancelamento")
	public Date getDataCancelamento() {
		return dataCancelamento;
	}
	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	
	@Column(name = "auto_tipo")
	@Enumerated(EnumType.STRING)
	public TipoAutorizacaoEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoAutorizacaoEnum tipo) {
		this.tipo = tipo;
	}
	
	@Column(name = "auto_status")
	@Enumerated(EnumType.STRING)
	public StatusAutorizacaoEnum getStatus() {
		return status;
	}
	public void setStatus(StatusAutorizacaoEnum status) {
		this.status = status;
	}
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "auto_observacoes")
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "auto_orientacoes")
	public String getOrientacoes() {
		return orientacoes;
	}
	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
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
	@JoinColumn(name = "cre_id")
	public Credenciado getCredenciado() {
		return credenciado;
	}
	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}
	
	@Column(name = "auto_valor")
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Transient
	public String getCodicoTipoTransiente() {
		return codicoTipoTransiente;
	}
	public void setCodicoTipoTransiente(String codicoTipoTransiente) {
		this.codicoTipoTransiente = codicoTipoTransiente;
	}
	
	@Transient
	public String getDataFormatada() {
		return dataFormatada;
	}
	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}
	
	@Transient
	public String getValorFormatado() {
		return valorFormatado;
	}
	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}
	
	@Column(name = "auto_nome_solicitante")
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hosp_id")
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "for_id")
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@Column(name = "auto_valor_pago_sala")
	public BigDecimal getValorPagoSala() {
		return valorPagoSala;
	}
	public void setValorPagoSala(BigDecimal valorPagoSala) {
		this.valorPagoSala = valorPagoSala;
	}
	
	@Column(name = "auto_valor_pago_material")
	public BigDecimal getValorPagoMaterial() {
		return valorPagoMaterial;
	}
	public void setValorPagoMaterial(BigDecimal valorPagoMaterial) {
		this.valorPagoMaterial = valorPagoMaterial;
	}
	
	@Transient
	public String getValorPagoSalaFormatado() {
		return valorPagoSalaFormatado;
	}
	public void setValorPagoSalaFormatado(String valorPagoSalaFormatado) {
		this.valorPagoSalaFormatado = valorPagoSalaFormatado;
	}
	
	@Transient
	public String getValorPagoMaterialFormatado() {
		return valorPagoMaterialFormatado;
	}
	public void setValorPagoMaterialFormatado(String valorPagoMaterialFormatado) {
		this.valorPagoMaterialFormatado = valorPagoMaterialFormatado;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "autorizacao_exame", joinColumns = { 
	@JoinColumn(name = "auto_id", nullable = false, updatable = false) },
	inverseJoinColumns = { @JoinColumn(name = "exa_id",
	nullable = false, updatable = false) })
	public List<Exame> getExames() {
		return exames;
	}
	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "auto_data_hora_execucao")
	public Date getDataHoraExecucao() {
		return dataHoraExecucao;
	}
	public void setDataHoraExecucao(Date dataHoraExecucao) {
		this.dataHoraExecucao = dataHoraExecucao;
	}
	
	@Column(name = "auto_situacao")
	@Enumerated(EnumType.STRING)
	public SituacaoAutorizacaoEnum getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoAutorizacaoEnum situacao) {
		this.situacao = situacao;
	}
	
	@Column(name = "auto_valor_pago")
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	
	@Transient
	public String getValorPagoFormatado() {
		return valorPagoFormatado;
	}
	public void setValorPagoFormatado(String valorPagoFormatado) {
		this.valorPagoFormatado = valorPagoFormatado;
	}
	
	@Column(name = "auto_canal")
	@Enumerated(EnumType.STRING)
	public CanalEnum getCanal() {
		return canal;
	}
	public void setCanal(CanalEnum canal) {
		this.canal = canal;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exa_id")
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usu_id")
	public Usuario getUsuarioAutorizado() {
		return usuarioAutorizado;
	}
	public void setUsuarioAutorizado(Usuario usuarioAutorizado) {
		this.usuarioAutorizado = usuarioAutorizado;
	}
	
	@Transient
	public String getValorTotalFormatado() {
		return valorTotalFormatado;
	}
	public void setValorTotalFormatado(String valorTotalFormatado) {
		this.valorTotalFormatado = valorTotalFormatado;
	}
	
	@Transient
	public String getDataExecucaoFormatada() {
		return dataExecucaoFormatada;
	}
	public void setDataExecucaoFormatada(String dataExecucaoFormatada) {
		this.dataExecucaoFormatada = dataExecucaoFormatada;
	}
	
}
