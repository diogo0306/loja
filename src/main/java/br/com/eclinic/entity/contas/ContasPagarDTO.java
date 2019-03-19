package br.com.eclinic.entity.contas;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class ContasPagarDTO {
	
	private ContasPagar contasPagar;
	private Date dataInicial;
	private Date dataFinal;
	private List<ContasPagar> contas;
	
	public ContasPagar getContasPagar() {
		return contasPagar;
	}
	public void setContasPagar(ContasPagar contasPagar) {
		this.contasPagar = contasPagar;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public List<ContasPagar> getContas() {
		return contas;
	}
	public void setContas(List<ContasPagar> contas) {
		this.contas = contas;
	}
}
