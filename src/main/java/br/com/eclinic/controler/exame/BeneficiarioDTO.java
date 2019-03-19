package br.com.eclinic.controler.exame;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.exame.BeneficiarioExame;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.entity.exame.TabelaExame;
import br.com.eclinic.entity.paciente.Paciente;

public class BeneficiarioDTO {
	
	private BeneficiarioExame beneficiarioExame;
	@NotNull(message = "Por favor, informe o beneficiário.")
	private Long idPaciente;
	@NotNull(message = "Por favor, informe o credenciado")
	private Long idCredenciado;
	private Credenciado credenciado;
	private Paciente paciente;
	private Tabela tabela;
	private Exame exame;
	private List<Exame> listaExames;
	private List<TabelaExame> listaTabelaExame;
	private BigDecimal valorTotal;
	private String valorTotalFormatado;
	
	
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Long getIdCredenciado() {
		return idCredenciado;
	}
	public void setIdCredenciado(Long idCredenciado) {
		this.idCredenciado = idCredenciado;
	}
	public String getValorTotalFormatado() {
		return valorTotalFormatado;
	}
	public void setValorTotalFormatado(String valorTotalFormatado) {
		this.valorTotalFormatado = valorTotalFormatado;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BeneficiarioExame getBeneficiarioExame() {
		return beneficiarioExame;
	}
	public void setBeneficiarioExame(BeneficiarioExame beneficiarioExame) {
		this.beneficiarioExame = beneficiarioExame;
	}
	public Credenciado getCredenciado() {
		return credenciado;
	}
	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Tabela getTabela() {
		return tabela;
	}
	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}
	public List<Exame> getListaExames() {
		return listaExames;
	}
	public void setListaExames(List<Exame> listaExames) {
		this.listaExames = listaExames;
	}
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	public List<TabelaExame> getListaTabelaExame() {
		return listaTabelaExame;
	}
	public void setListaTabelaExame(List<TabelaExame> listaTabelaExame) {
		this.listaTabelaExame = listaTabelaExame;
	}
}
