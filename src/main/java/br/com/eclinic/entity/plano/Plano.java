package br.com.eclinic.entity.plano;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.procedimento.GrupoProcedimento;
import br.com.eclinic.entity.procedimento.Procedimento;

@Entity
@Table(name = "plano")
@SequenceGenerator(name = "sequencePlano", sequenceName = "plano_id_seq")
public class Plano implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;

	private Boolean pagamentoParticipacaoBalcao;
	private Boolean aplicarMudancaFaixa;
	private Integer idadeMaximaDependenteMasculino;
	private Integer idadeMaximaDependenteFeminino;
	private Contrato contrato;
	private Date dataCadastro;
	private String dataCadastoFormatada;
	private BigDecimal valorPlano;
	private String valorPlanoTransiente;
	private AcomodacaoPlanoSaudeEnum acomodacaoPlanoSaudeEnum;
	private TipoPlanoSaudeEnum tipoPlanoSaudeEnum;
	private String codigoTipoPlanoTransiente;
	private String codigoAcomodacaoPlanoTransiente;

	/* private List<TabelaFaixa> tabelaFaixa; */
	private List<Procedimento> procedimentos;
	private List<GrupoProcedimento> gruposProcidimento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pla_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "pla_nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "pla_tipo")
	@Enumerated(EnumType.STRING)
	public TipoPlanoSaudeEnum getTipoPlanoSaudeEnum() {
		return tipoPlanoSaudeEnum;
	}

	public void setTipoPlanoSaudeEnum(TipoPlanoSaudeEnum tipoPlanoSaudeEnum) {
		this.tipoPlanoSaudeEnum = tipoPlanoSaudeEnum;
	}

	@Transient
	public String getCodigoTipoPlanoTransiente() {
		return codigoTipoPlanoTransiente;
	}

	public void setCodigoTipoPlanoTransiente(String codigoTipoPlanoTransiente) {
		this.codigoTipoPlanoTransiente = codigoTipoPlanoTransiente;
	}

	@Column(name = "pla_acom")
	@Enumerated(EnumType.STRING)
	public AcomodacaoPlanoSaudeEnum getAcomodacaoPlanoSaudeEnum() {
		return acomodacaoPlanoSaudeEnum;
	}

	public void setAcomodacaoPlanoSaudeEnum(AcomodacaoPlanoSaudeEnum acomodacaoPlanoSaudeEnum) {
		this.acomodacaoPlanoSaudeEnum = acomodacaoPlanoSaudeEnum;
	}

	@Transient
	public String getCodigoAcomodacaoPlanoTransiente() {
		/* codigoAcomodacaoPlanoTransiente = acomodacaoPlanoSaudeEnum.getDescricao(); */
		return codigoAcomodacaoPlanoTransiente;
	}

	public void setCodigoAcomodacaoPlanoTransiente(String codigoAcomodacaoPlanoTransiente) {
		this.codigoAcomodacaoPlanoTransiente = codigoAcomodacaoPlanoTransiente;
	}

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "plano_procedimento", joinColumns = { @JoinColumn(name = "proc_id") }, inverseJoinColumns = {
			@JoinColumn(name = "pla_id") })
	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	@OneToMany(mappedBy = "plano")
	public List<GrupoProcedimento> getGruposProcidimento() {
		return gruposProcidimento;
	}

	public void setGruposProcidimento(List<GrupoProcedimento> gruposProcidimento) {
		this.gruposProcidimento = gruposProcidimento;
	}

	@Column(name = "pla_aplicar_faixa")
	public Boolean getAplicarMudancaFaixa() {
		return aplicarMudancaFaixa;
	}

	public void setAplicarMudancaFaixa(Boolean aplicarMudancaFaixa) {
		this.aplicarMudancaFaixa = aplicarMudancaFaixa;
	}

	@Column(name = "pla_pagamento_balcao")
	public Boolean getPagamentoParticipacaoBalcao() {
		return pagamentoParticipacaoBalcao;
	}

	public void setPagamentoParticipacaoBalcao(Boolean pagamentoParticipacaoBalcao) {
		this.pagamentoParticipacaoBalcao = pagamentoParticipacaoBalcao;
	}

	/*
	 * @OneToMany(mappedBy = "plano")
	 * 
	 * @LazyCollection(LazyCollectionOption.TRUE) public List<TabelaFaixa>
	 * getTabelaFaixa() { return tabelaFaixa; } public void
	 * setTabelaFaixa(List<TabelaFaixa> tabelaFaixa) { this.tabelaFaixa =
	 * tabelaFaixa; }
	 */

	@Column(name = "pla_idade_max_masc")
	public Integer getIdadeMaximaDependenteMasculino() {
		return idadeMaximaDependenteMasculino;
	}

	public void setIdadeMaximaDependenteMasculino(Integer idadeMaximaDependenteMasculino) {
		this.idadeMaximaDependenteMasculino = idadeMaximaDependenteMasculino;
	}

	@Column(name = "pla_idade_max_fem")
	public Integer getIdadeMaximaDependenteFeminino() {
		return idadeMaximaDependenteFeminino;
	}

	public void setIdadeMaximaDependenteFeminino(Integer idadeMaximaDependenteFeminino) {
		this.idadeMaximaDependenteFeminino = idadeMaximaDependenteFeminino;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "con_id")
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "pla_data_cadastro")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Transient
	public String getDataCadastoFormatada() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		dataCadastoFormatada = formatado.format(dataCadastro);
		return dataCadastoFormatada;
	}

	public void setDataCadastoFormatada(String dataCadastoFormatada) {
		this.dataCadastoFormatada = dataCadastoFormatada;
	}

	@Column(name = "pla_valor_plano")
	public BigDecimal getValorPlano() {
		return valorPlano;
	}

	public void setValorPlano(BigDecimal valorPlano) {
		this.valorPlano = valorPlano;
	}

	@Transient
	public String getValorPlanoTransiente() {
		return valorPlanoTransiente;
	}

	public void setValorPlanoTransiente(String valorPlanoTransiente) {
		this.valorPlanoTransiente = valorPlanoTransiente;
	}

}
