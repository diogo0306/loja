package br.com.eclinic.saogabriel.api.entity.contrato;

import java.math.BigDecimal;
import java.util.Date;

import br.com.eclinic.entity.enuns.SituacaoContratoEnum;

public class ContratoDTO {

	private Long id;
	private String nomePaciente;
	private BigDecimal valorContrato;
	private Date inicioVigecia;
	private Date fimVigencia;
	private SituacaoContratoEnum situacaoEnum;
	private String massage;
	
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public SituacaoContratoEnum getSituacaoEnum() {
		return situacaoEnum;
	}
	public void setSituacaoEnum(SituacaoContratoEnum situacaoEnum) {
		this.situacaoEnum = situacaoEnum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getValorContrato() {
		return valorContrato;
	}
	public void setValorContrato(BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}
	public Date getInicioVigecia() {
		return inicioVigecia;
	}
	public void setInicioVigecia(Date inicioVigecia) {
		this.inicioVigecia = inicioVigecia;
	}
	public Date getFimVigencia() {
		return fimVigencia;
	}
	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	
	
}
