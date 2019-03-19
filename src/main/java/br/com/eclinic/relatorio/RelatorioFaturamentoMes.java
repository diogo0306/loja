package br.com.eclinic.relatorio;

import java.math.BigDecimal;

public class RelatorioFaturamentoMes {
	String mesFormatado;
	int dia;
	BigDecimal valor;
	int quantidadeConsultas;
	
	public int getQuantidadeConsultas() {
		return quantidadeConsultas;
	}

	public void setQuantidadeConsultas(int quantidadeConsultas) {
		this.quantidadeConsultas = quantidadeConsultas;
	}

	public String getMesFormatado() {
		return mesFormatado;
	}
	
	public RelatorioFaturamentoMes(int dia, BigDecimal valor, int quantidadeConsultas) {
		this.dia = dia;
		this.valor = valor;
		this.quantidadeConsultas = quantidadeConsultas;
	}
	
	public RelatorioFaturamentoMes() {
		// TODO Auto-generated constructor stub
	}
	
	public void setMesFormatado(String mesFormatado) {
		this.mesFormatado = mesFormatado;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
