package br.com.eclinic.controler.exame;

import java.util.List;

import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.entity.exame.TabelaExame;

public class ExameDTO {
	
	private Exame exame;
	private Long id;
	private String nome;
	private String descricao;
	private List<Exame> lista;
	private List<Tabela> listaTabela;
	private Tabela tabela;
	private TabelaExame tabelaExame;
	private List<TabelaExame> listaTabelaExame;
	private String valorTransiente;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getValorTransiente() {
		return valorTransiente;
	}
	public void setValorTransiente(String valorTransiente) {
		this.valorTransiente = valorTransiente;
	}
	public TabelaExame getTabelaExame() {
		return tabelaExame;
	}
	public void setTabelaExame(TabelaExame tabelaExame) {
		this.tabelaExame = tabelaExame;
	}
	public List<TabelaExame> getListaTabelaExame() {
		return listaTabelaExame;
	}
	public void setListaTabelaExame(List<TabelaExame> listaTabelaExame) {
		this.listaTabelaExame = listaTabelaExame;
	}
	public List<Tabela> getListaTabela() {
		return listaTabela;
	}
	public void setListaTabela(List<Tabela> listaTabela) {
		this.listaTabela = listaTabela;
	}
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	public List<Exame> getLista() {
		return lista;
	}
	public void setLista(List<Exame> lista) {
		this.lista = lista;
	}
	public Tabela getTabela() {
		return tabela;
	}
	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}
}
