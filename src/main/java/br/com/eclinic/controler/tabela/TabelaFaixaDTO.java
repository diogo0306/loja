package br.com.eclinic.controler.tabela;

import java.math.BigDecimal;
import java.util.List;

import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.tabela.TabelaFaixa;
import br.com.eclinic.entity.tabela.TipoBeneficiarioEnum;

public class TabelaFaixaDTO {
	
	
	private Plano plano;
	private TabelaFaixa tabelaFaixa;
	private List<TabelaFaixa> tabela;
	private TipoBeneficiarioEnum tipoBeneficiarioEnum;
	

	private BigDecimal valor;
	private String valorTransiente;
	
	
	
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	public TabelaFaixa getTabelaFaixa() {
		return tabelaFaixa;
	}
	public void setTabelaFaixa(TabelaFaixa tabelaFaixa) {
		this.tabelaFaixa = tabelaFaixa;
	}
	
	public List<TabelaFaixa> getTabela() {
		return tabela;
	}
	public void setTabela(List<TabelaFaixa> tabela) {
		this.tabela = tabela;
	}
	public TipoBeneficiarioEnum getTipoBeneficiarioEnum() {
		return tipoBeneficiarioEnum;
	}
	public void setTipoBeneficiarioEnum(TipoBeneficiarioEnum tipoBeneficiarioEnum) {
		this.tipoBeneficiarioEnum = tipoBeneficiarioEnum;
	}
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getValorTransiente() {
		return valorTransiente;
	}
	public void setValorTransiente(String valorTransiente) {
		this.valorTransiente = valorTransiente;
	}
	
	
	
	
	
}
