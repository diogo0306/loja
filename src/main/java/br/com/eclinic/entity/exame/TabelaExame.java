package br.com.eclinic.entity.exame;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tabela_exame")
@SequenceGenerator(name = "sequenceTabelaExame", sequenceName = "tab_exame_id_seq")
public class TabelaExame implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private Tabela tabela;
	private Exame exame;
	private BigDecimal valor;
	private String valorTransiente;
	private BeneficiarioExame beneficiarioExame;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tab_exa_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tab_id", nullable = true)
	public Tabela getTabela() {
		return tabela;
	}
	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exa_id", nullable = true)
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	
	@Column(name = "tab_exa_valor")
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ben_id")
	public BeneficiarioExame getBeneficiarioExame() {
		return beneficiarioExame;
	}
	public void setBeneficiarioExame(BeneficiarioExame beneficiarioExame) {
		this.beneficiarioExame = beneficiarioExame;
	}

}
