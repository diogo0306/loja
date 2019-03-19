package br.com.eclinic.entity.exame;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.consulta.Consulta;

@Entity
@Table(name = "exame")
@SequenceGenerator(name = "sequenceExame", sequenceName = "exame_id_seq")
public class Exame implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private BigDecimal valor;
	private String valorTransiente;
	private Integer diasDuracao;
	private List<Agendamento> agendamentos;
	private List<Consulta> consultas;
	private Cliente cliente;
	private String descricao;
	private String codigo;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exa_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "exa_nome", nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "exa_valor")
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Column(name = "exa_dias_duracao")
	public Integer getDiasDuracao() {
		return diasDuracao;
	}
	public void setDiasDuracao(Integer diasDuracao) {
		this.diasDuracao = diasDuracao;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "exame_agendamento", joinColumns = { 
			@JoinColumn(name = "exa_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "age_id", 
					nullable = false, updatable = false) })
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "exame_consulta", joinColumns = { 
			@JoinColumn(name = "exa_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "con_id", 
					nullable = false, updatable = false) })
	public List<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	@Transient
	public String getValorTransiente() {
		return valorTransiente;
	}
	public void setValorTransiente(String valorTransiente) {
		this.valorTransiente = valorTransiente;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cli_id", nullable = true)
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Column(name = "exa_descricao")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "exa_codigo")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}		
}
