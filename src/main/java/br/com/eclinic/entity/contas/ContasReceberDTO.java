package br.com.eclinic.entity.contas;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class ContasReceberDTO {
	
	private ContasReceber contasReceber;
	private Date dataInicial;
	private Date dataFinal;
	private List<ContasReceber> contas;
	
	public ContasReceber getContasReceber() {
		return contasReceber;
	}
	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
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
	
	public List<ContasReceber> getContas() {
		return contas;
	}
	public void setContas(List<ContasReceber> contas) {
		this.contas = contas;
	}

}
