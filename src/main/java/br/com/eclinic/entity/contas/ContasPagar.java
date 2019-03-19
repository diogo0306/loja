package br.com.eclinic.entity.contas;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "contas_pagar")
@SequenceGenerator(name = "sequenceContasPagar", sequenceName = "contas_pagar_id_seq")
public class ContasPagar implements Serializable {

	private static final long serialVersionUID = 5393123941402388317L;
	private Long id;
	private BigDecimal valor;
	private Date dataPagamento;
	private Date dataRealizado;
	private TipoContaPagamentoEnum tipo;
	private String codigoTipoTransiente;
	private Representante representante;
	private Credenciado credenciado;
	private String dataFormatada;
	private String valorFormatado;
	private Autorizacao autorizacao;
	private StatusPagamentoEnum status;
	private Usuario usuarioAutorizado;
	private TipoContaEnum tipoPagamento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cont_pag_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cont_pag_valor")
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "cont_pag_data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "cont_pag_tipo")
	@Enumerated(EnumType.STRING)
	public TipoContaPagamentoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoContaPagamentoEnum tipo) {
		this.tipo = tipo;
	}

	@Transient
	public String getCodigoTipoTransiente() {
		return codigoTipoTransiente;
	}

	public void setCodigoTipoTransiente(String codigoTipoTransiente) {
		this.codigoTipoTransiente = codigoTipoTransiente;
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
	@JoinColumn(name = "cre_id")
	public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_id")
	public Autorizacao getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	@Column(name = "cont_pag_status")
	@Enumerated(EnumType.STRING)
	public StatusPagamentoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusPagamentoEnum status) {
		this.status = status;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usu_id")
	public Usuario getUsuarioAutorizado() {
		return usuarioAutorizado;
	}

	public void setUsuarioAutorizado(Usuario usuarioAutorizado) {
		this.usuarioAutorizado = usuarioAutorizado;
	}

	@Column(name = "cont_pag_tipo_pagamento")
	@Enumerated(EnumType.STRING)
	public TipoContaEnum getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoContaEnum tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "cont_pag_data_realizado")
	public Date getDataRealizado() {
		return dataRealizado;
	}

	public void setDataRealizado(Date dataRealizado) {
		this.dataRealizado = dataRealizado;
	}
	
}
