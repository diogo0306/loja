package br.com.eclinic.entity.paciente;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Transient;

@Entity
@Table(name = "cobranca_avulsa")
@SequenceGenerator(name = "sequenceCobrancaAvulsa", sequenceName = "cobranca_avulsa_id_seq")
public class CobrancaAvulsa implements Serializable {

	private static final long serialVersionUID = -677891280115758978L;
	private Long id;
	private String nome;
	private BigDecimal valor;
	private String valorTransiente;
	private Date data;
	private String dataFormatada;
	private Paciente pacienteVinculo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cob_avu_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cob_avu_nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cob_avu_valor")
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Transient
	public String getValorTransiente() {
		return valorTransiente;
	}

	public void setValorTransiente(String valorTransiente) {
		this.valorTransiente = valorTransiente;
	}

	@Column(name = "cob_avu_data")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Transient
	public String getDataFormatada() {
		if (this.data != null) {
			GregorianCalendar data = new GregorianCalendar();
			data.setTime(this.data);
			dataFormatada = data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/"
					+ data.get(Calendar.YEAR);
		}
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pac_id", nullable = true)
	public Paciente getPacienteVinculo() {
		return pacienteVinculo;
	}

	public void setPacienteVinculo(Paciente pacienteVinculo) {
		this.pacienteVinculo = pacienteVinculo;
	}

}
