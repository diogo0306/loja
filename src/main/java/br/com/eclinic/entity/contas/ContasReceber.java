package br.com.eclinic.entity.contas;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.eclinic.entity.agendamento.StatusPagamentoEnum;
import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.representante.Representante;
import br.com.eclinic.entity.usuario.Usuario;

@Entity
@Table(name = "contas_receber")
@SequenceGenerator(name = "sequenceContasReceber", sequenceName = "contas_receber_id_seq")
public class ContasReceber implements Serializable {

	private static final long serialVersionUID = 4540497924942081518L;
	private Long id;
	private BigDecimal valorPago;
	private BigDecimal valor;
	private String valorPagoTransiente;
	private String valorTransiente;
	private Date dataPagamento;
	private Date dataVencimento;
	private String dataPagamentoFormatada;
	private String dataVencimentoFormatada;
	private TipoContaEnum tipoContaEnum;
	private StatusPagamentoEnum status;
	private String codigoTipoContaTransiente;
	private String codigoTipoStatusTransiente;
	private Credenciado credenciado;
	private Representante representante;
	private Autorizacao autorizacao;
	private Usuario usuarioAutorizado;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cont_rec_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cont_rec_valor_pago")
	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	@Column(name = "cont_rec_valor")
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Transient
	public String getValorPagoTransiente() {
		return valorPagoTransiente;
	}

	public void setValorPagoTransiente(String valorPagoTransiente) {
		this.valorPagoTransiente = valorPagoTransiente;
	}

	@Transient
	public String getValorTransiente() {
		return valorTransiente;
	}

	public void setValorTransiente(String valorTransiente) {
		this.valorTransiente = valorTransiente;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "cont_rec_data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "cont_rec_data_vencimento")
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Transient
	public String getDataPagamentoFormatada() {
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
		dataPagamentoFormatada = formatada.format(dataPagamento);
		return dataPagamentoFormatada;
	}

	public void setDataPagamentoFormatada(String dataPagamentoFormatada) {
		this.dataPagamentoFormatada = dataPagamentoFormatada;
	}

	@Transient
	public String getDataVencimentoFormatada() {
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
		dataVencimentoFormatada = formatada.format(dataVencimento);
		return dataVencimentoFormatada;
	}

	public void setDataVencimentoFormatada(String dataVencimentoFormatada) {
		this.dataVencimentoFormatada = dataVencimentoFormatada;
	}

	@Column(name = "cont_rec_tipo_conta")
	@Enumerated(EnumType.STRING)
	public TipoContaEnum getTipoContaEnum() {
		return tipoContaEnum;
	}

	public void setTipoContaEnum(TipoContaEnum tipoContaEnum) {
		this.tipoContaEnum = tipoContaEnum;
	}

	@Transient
	public String getCodigoTipoContaTransiente() {
		return codigoTipoContaTransiente;
	}

	public void setCodigoTipoContaTransiente(String codigoTipoContaTransiente) {
		this.codigoTipoContaTransiente = codigoTipoContaTransiente;
	}

	@Transient
	public String getCodigoTipoStatusTransiente() {
		return codigoTipoStatusTransiente;
	}

	public void setCodigoTipoStatusTransiente(String codigoTipoStatusTransiente) {
		this.codigoTipoStatusTransiente = codigoTipoStatusTransiente;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cre_id")
	public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rep_id")
	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	@Column(name = "cont_rec_status")
	@Enumerated(EnumType.STRING)
	public StatusPagamentoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusPagamentoEnum status) {
		this.status = status;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_id")
	public Autorizacao getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usu_id")
	public Usuario getUsuarioAutorizado() {
		return usuarioAutorizado;
	}

	public void setUsuarioAutorizado(Usuario usuarioAutorizado) {
		this.usuarioAutorizado = usuarioAutorizado;
	}
	
}
