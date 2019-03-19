package br.com.eclinic.entity.tabela;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tabela_faixa")
@SequenceGenerator(name = "sequenceTabelaFaixa", sequenceName = "tabela_faixa_id_seq")
public class TabelaFaixa implements Serializable {

	private static final long serialVersionUID = 1004534304226144289L;
	private Long id;
	private Long idadeInicial;
	private Long idadeFinal;
	private Long vidaInicial;
	private Long vidaFinal;
	private BigDecimal valor;
	private String valorTransiente;
	/*private Plano plano;*/
	private TipoBeneficiarioEnum tipoBeneficiarioEnum;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tab_fax_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plano_id", nullable = false)
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}*/
	
	@Column(name = "tab_fax_idade_inicial")
	public Long getIdadeInicial() {
		return idadeInicial;
	}

	public void setIdadeInicial(Long idadeInicial) {
		this.idadeInicial = idadeInicial;
	}

	@Column(name = "tab_fax_idade_final")
	public Long getIdadeFinal() {
		return idadeFinal;
	}

	public void setIdadeFinal(Long idadeFinal) {
		this.idadeFinal = idadeFinal;
	}

	@Column(name = "tab_fax_vida_inicial")
	public Long getVidaInicial() {
		return vidaInicial;
	}

	public void setVidaInicial(Long vidaInicial) {
		this.vidaInicial = vidaInicial;
	}

	@Column(name = "tab_fax_vida_final")
	public Long getVidaFinal() {
		return vidaFinal;
	}

	public void setVidaFinal(Long vidaFinal) {
		this.vidaFinal = vidaFinal;
	}
	
	@Column(name = "tab_fax_valor")
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
	@Column(name = "tab_fax_tipo_beneficiario", nullable = false)
	@Enumerated(EnumType.STRING)
	public TipoBeneficiarioEnum getTipoBeneficiarioEnum() {
		return tipoBeneficiarioEnum;
	}

	public void setTipoBeneficiarioEnum(TipoBeneficiarioEnum tipoBeneficiarioEnum) {
		this.tipoBeneficiarioEnum = tipoBeneficiarioEnum;
	}
	
	
}
