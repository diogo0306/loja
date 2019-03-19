package br.com.eclinic.entity.credenciado;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.eclinic.entity.credenciado.Credenciado;

public class CredenciadoDTO {

	private Credenciado credenciado;
	private String codigoTipoPessoaTransiente;
	private String codigoSituacaoTransiente;
	private Date data;
	private String dataFormatada;
	private Date dataPercentual;

	public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}

	public String getCodigoTipoPessoaTransiente() {
		return codigoTipoPessoaTransiente;
	}

	public void setCodigoTipoPessoaTransiente(String codigoTipoPessoaTransiente) {
		this.codigoTipoPessoaTransiente = codigoTipoPessoaTransiente;
	}

	public String getCodigoSituacaoTransiente() {
		return codigoSituacaoTransiente;
	}

	public void setCodigoSituacaoTransiente(String codigoSituacaoTransiente) {
		this.codigoSituacaoTransiente = codigoSituacaoTransiente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataPercentual() {
		return dataPercentual;
	}

	public void setDataPercentual(Date dataPercentual) {
		this.dataPercentual = dataPercentual;
	}

	public String getDataFormatada() {
		if (this.data != null) {
			GregorianCalendar data = new GregorianCalendar();
			data.setTime(this.data);
			dataFormatada = data.get(Calendar.DAY_OF_MONTH) + "/"
					+ (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR);
		}
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}
	
	

}
