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

@Entity
@Table(name = "faixa_etaria")
@SequenceGenerator(name = "sequenceFaixaEtaria", sequenceName = "faixa_etaria_id_seq")
public class FaixaEtaria implements Serializable {

	private static final long serialVersionUID = 5778210483707607027L;
	private Long id;
	private Integer idadeMinima;
	private Integer idadeMaxima;
	private BigDecimal variacao;
	private TipoBeneficiarioEnum tipoBeneficiarioEnum;
	// private TabelaFaixa tabelaFaixa;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fax_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "fax_idade_minima")
	public Integer getIdadeMinima() {
		return idadeMinima;
	}
	public void setIdadeMinima(Integer idadeMinima) {
		this.idadeMinima = idadeMinima;
	}
	
	@Column(name = "fax_idade_maxima")
	public Integer getIdadeMaxima() {
		return idadeMaxima;
	}
	public void setIdadeMaxima(Integer idadeMaxima) {
		this.idadeMaxima = idadeMaxima;
	}
	
	@Column(name = "fax_variacao")
	public BigDecimal getVariacao() {
		return variacao;
	}
	public void setVariacao(BigDecimal variacao) {
		this.variacao = variacao;
	}
	
	@Column(name = "fax_tipo_beneficiario", nullable = false)
	@Enumerated(EnumType.STRING)
	public TipoBeneficiarioEnum getTipoBeneficiarioEnum() {
		return tipoBeneficiarioEnum;
	}
	public void setTipoBeneficiarioEnum(TipoBeneficiarioEnum tipoBeneficiarioEnum) {
		this.tipoBeneficiarioEnum = tipoBeneficiarioEnum;
	}
/*	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tab_fax_id", nullable = false)
	public TabelaFaixa getTabelaFaixa() {
		return tabelaFaixa;
	}
	public void setTabelaFaixa(TabelaFaixa tabelaFaixa) {
		this.tabelaFaixa = tabelaFaixa;
	}
*/
}
