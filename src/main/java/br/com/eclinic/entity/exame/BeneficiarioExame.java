package br.com.eclinic.entity.exame;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.paciente.Paciente;

@Entity
@Table(name = "beneficiario_exame")
@SequenceGenerator(name = "sequenceBeneficiarioExame", sequenceName = "beneficiario_exame_id_seq")
public class BeneficiarioExame implements Serializable {

	
	private static final long serialVersionUID = 4447585438586656503L;
	
	private Long id;
	private Credenciado credenciado;
	private Paciente paciente;
	List<TabelaExame> listaTabelaExame;
	private Date data;
	private Date dataPagamento;
	private String dataFormatada;
	private BigDecimal valorTotal;
	private String valorTotalFormatado;
	private String codigo;
	private TipoStatusEnum status;
	private String tipoTransiente;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ben_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name = "cre_id")
	@NotNull(message = "Informe o credenciado.")
	public Credenciado getCredenciado() {
		return credenciado;
	}
	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}
	
	@OneToOne
	@JoinColumn(name = "pac_id")
	@NotNull(message = "Informe o beneficiário.")
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@OneToMany(mappedBy = "beneficiarioExame")
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<TabelaExame> getListaTabelaExame() {
		return listaTabelaExame;
	}
	public void setListaTabelaExame(List<TabelaExame> listaTabelaExame) {
		this.listaTabelaExame = listaTabelaExame;
	}
	
	@Column(name = "ben_data")
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
			dataFormatada = dataConfec.get(Calendar.DAY_OF_MONTH) + "/"
					+ (dataConfec.get(Calendar.MONTH) + 1) + "/" + dataConfec.get(Calendar.YEAR);
		}
		return dataFormatada;
	}
	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}
	
	@Column(name = "ben_valor_total")
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	@Transient
	public String getValorTotalFormatado() {
		return valorTotalFormatado;
	}
	public void setValorTotalFormatado(String valorTotalFormatado) {
		this.valorTotalFormatado = valorTotalFormatado;
	}
	
	@Column(name = "ben_codigo")
	@NotEmpty
	@NotBlank
	@NotNull
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "ben_status")
	@Enumerated(EnumType.STRING)
	@NotNull
	public TipoStatusEnum getStatus() {
		return status;
	}
	public void setStatus(TipoStatusEnum status) {
		this.status = status;
	}
	
	@Column(name = "ben_data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	@Transient
	public String getTipoTransiente() {
		return tipoTransiente;
	}
	public void setTipoTransiente(String tipoTransiente) {
		this.tipoTransiente = tipoTransiente;
	}
	
}
