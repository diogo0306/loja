package br.com.eclinic.entity.parametrizacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.eclinic.entity.empresa.Empresa;

@Entity
@Table(name = "parametrizacao")
@SequenceGenerator(name = "sequenceParametrizacao", sequenceName = "parametrizacao_id_seq")
public class Parametrizacao implements Serializable {

	private static final long serialVersionUID = -977720590788461678L;

	private Long id;

	private String codigo;
	private MesEnum mesProcessamento;
	private Long anoProcessamento;
	private Date periodoInicio;
	private String periodoInicialFormatada;
	private Date periodoFim;
	private String periodoFinalFormatada;
	private ControleBiometricoEnum controleBiometrico;
	private EditorTextoEnum editorArquivo;
	private Boolean utilizarIndiceClinicaMedica;
	private Boolean utilizarLayoutProprioGuia;
	private Boolean permitirAtualizarValorLiberacaoGuia;
	private Boolean emitirValoresGuiaSADT;
	private Boolean emitirValoresGuiaAmbulatorio;
	private Boolean emitirValoresGuiaInternacao;
	private BigDecimal valorLiberacaoOnLine;
	private BigDecimal valorLiberacaoSistema;
	private SimNaoEnum consistirRetornoConsulta;
	private Long consistirRetornoConsultaPrazo;
	private Long gerarValidadeCarteiraPrazo;

	private Empresa empresa;
	private Boolean integrarCobrancaFinanceiro;
	private TipoCobrancaEmpresaEnum tipoCobrancaEmpresa;
	private ControleContratualEnum controleContratual;
	private String nossoNumero;
	private Long prazoAtrasadoMensalidade;
	private Long prazoCancelamentoContrato;
	private BigDecimal multaAtraso;
	private Long prazoAplicacaoMulta;
	private BigDecimal jurosFixoDiario;

	private List<Mensagem> mensagens;
	private List<Dia> diasVencimento;

	private String codigoMesProcessamentoTransiente;
	private String codigoControleBiometricoTransiente;
	private String codigoEditorTextoTransiente;
	private String codigoSimNaoTransiente;
	private String codigoTipoCobrançaEmpresaTransiente;
	private String codigoControleContratualTransiente;

	private Boolean um;
	private Boolean dois;
	private Boolean tres;
	private Boolean quatro;
	private Boolean cinco;
	private Boolean seis;
	private Boolean sete;
	private Boolean oito;
	private Boolean nove;
	private Boolean dez;
	private Boolean onze;
	private Boolean doze;
	private Boolean treze;
	private Boolean quatorze;
	private Boolean quinze;
	private Boolean dezesseis;
	private Boolean dezessete;
	private Boolean dezoito;
	private Boolean dezenove;
	private Boolean vinte;
	private Boolean vinte_um;
	private Boolean vinte_dois;
	private Boolean vinte_tres;
	private Boolean vinte_quatro;
	private Boolean vinte_cinco;
	private Boolean vinte_seis;
	private Boolean vinte_sete;
	private Boolean vinte_oito;
	private Boolean vinte_nove;
	private Boolean trinta;
	private Boolean trinta_um;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "par_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "par_mes_processamento")
	@Enumerated(EnumType.STRING)
	public MesEnum getMesProcessamento() {
		return mesProcessamento;
	}

	public void setMesProcessamento(MesEnum mesProcessamento) {
		this.mesProcessamento = mesProcessamento;
	}

	@Column(name = "par_ano_processamento")
	public Long getAnoProcessamento() {
		return anoProcessamento;
	}

	public void setAnoProcessamento(Long anoProcessamento) {
		this.anoProcessamento = anoProcessamento;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "par_periodo_inicio")
	public Date getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(Date periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	@Transient
	public String getPeriodoInicialFormatada() {
		
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		periodoInicialFormatada = formatado.format(periodoInicio);
        
		return periodoInicialFormatada;
	}

	public void setPeriodoInicialFormatada(String periodoInicialFormatada) {
		this.periodoInicialFormatada = periodoInicialFormatada;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "par_periodo_fim")
	public Date getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(Date periodoFim) {
		this.periodoFim = periodoFim;
	}

	@Transient
	public String getPeriodoFinalFormatada() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		periodoFinalFormatada = formatado.format(periodoFim);
		return periodoFinalFormatada;
	}

	public void setPeriodoFinalFormatada(String periodoFinalFormatada) {
		this.periodoFinalFormatada = periodoFinalFormatada;

	}

	@Column(name = "par_controle_biometrico")
	@Enumerated(EnumType.STRING)
	public ControleBiometricoEnum getControleBiometrico() {
		return controleBiometrico;
	}

	public void setControleBiometrico(ControleBiometricoEnum controleBiometrico) {
		this.controleBiometrico = controleBiometrico;
	}

	@Column(name = "par_editor_texto")
	@Enumerated(EnumType.STRING)
	public EditorTextoEnum getEditorArquivo() {
		return editorArquivo;
	}

	public void setEditorArquivo(EditorTextoEnum editorArquivo) {
		this.editorArquivo = editorArquivo;
	}

	@Column(name = "par_utilizar_ind_cli_med")
	public Boolean getUtilizarIndiceClinicaMedica() {
		return utilizarIndiceClinicaMedica;
	}

	public void setUtilizarIndiceClinicaMedica(Boolean utilizarIndiceClinicaMedica) {
		this.utilizarIndiceClinicaMedica = utilizarIndiceClinicaMedica;
	}

	@Column(name = "par_utilizar_lay_pro_guia")
	public Boolean getUtilizarLayoutProprioGuia() {
		return utilizarLayoutProprioGuia;
	}

	public void setUtilizarLayoutProprioGuia(Boolean utilizarLayoutProprioGuia) {
		this.utilizarLayoutProprioGuia = utilizarLayoutProprioGuia;
	}

	@Column(name = "par_per_atu_val_lib_guia")
	public Boolean getPermitirAtualizarValorLiberacaoGuia() {
		return permitirAtualizarValorLiberacaoGuia;
	}

	public void setPermitirAtualizarValorLiberacaoGuia(Boolean permitirAtualizarValorLiberacaoGuia) {
		this.permitirAtualizarValorLiberacaoGuia = permitirAtualizarValorLiberacaoGuia;
	}

	@Column(name = "par_emi_val_gui_sadt")
	public Boolean getEmitirValoresGuiaSADT() {
		return emitirValoresGuiaSADT;
	}

	public void setEmitirValoresGuiaSADT(Boolean emitirValoresGuiaSADT) {
		this.emitirValoresGuiaSADT = emitirValoresGuiaSADT;
	}

	@Column(name = "par_emi_val_gui_ambulatorio")
	public Boolean getEmitirValoresGuiaAmbulatorio() {
		return emitirValoresGuiaAmbulatorio;
	}

	public void setEmitirValoresGuiaAmbulatorio(Boolean emitirValoresGuiaAmbulatorio) {
		this.emitirValoresGuiaAmbulatorio = emitirValoresGuiaAmbulatorio;
	}

	@Column(name = "par_emi_val_gui_internacao")
	public Boolean getEmitirValoresGuiaInternacao() {
		return emitirValoresGuiaInternacao;
	}

	public void setEmitirValoresGuiaInternacao(Boolean emitirValoresGuiaInternacao) {
		this.emitirValoresGuiaInternacao = emitirValoresGuiaInternacao;
	}

	@Column(name = "par_valor_lib_on_line")
	public BigDecimal getValorLiberacaoOnLine() {
		return valorLiberacaoOnLine;
	}

	public void setValorLiberacaoOnLine(BigDecimal valorLiberacaoOnLine) {
		this.valorLiberacaoOnLine = valorLiberacaoOnLine;
	}

	@Column(name = "par_valor_lib_sistema")
	public BigDecimal getValorLiberacaoSistema() {
		return valorLiberacaoSistema;
	}

	public void setValorLiberacaoSistema(BigDecimal valorLiberacaoSistema) {
		this.valorLiberacaoSistema = valorLiberacaoSistema;
	}

	@Column(name = "par_cons_retorno_consulta")
	@Enumerated(EnumType.STRING)
	public SimNaoEnum getConsistirRetornoConsulta() {
		return consistirRetornoConsulta;
	}

	public void setConsistirRetornoConsulta(SimNaoEnum consistirRetornoConsulta) {
		this.consistirRetornoConsulta = consistirRetornoConsulta;
	}

	@Column(name = "par_cons_retorno_consulta_prazo")
	public Long getConsistirRetornoConsultaPrazo() {
		return consistirRetornoConsultaPrazo;
	}

	public void setConsistirRetornoConsultaPrazo(Long consistirRetornoConsultaPrazo) {
		this.consistirRetornoConsultaPrazo = consistirRetornoConsultaPrazo;
	}

	@Column(name = "par_ger_val_car_prazo")
	public Long getGerarValidadeCarteiraPrazo() {
		return gerarValidadeCarteiraPrazo;
	}

	public void setGerarValidadeCarteiraPrazo(Long gerarValidadeCarteiraPrazo) {
		this.gerarValidadeCarteiraPrazo = gerarValidadeCarteiraPrazo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "par_int_cob_financeiro")
	public Boolean getIntegrarCobrancaFinanceiro() {
		return integrarCobrancaFinanceiro;
	}

	public void setIntegrarCobrancaFinanceiro(Boolean integrarCobrancaFinanceiro) {
		this.integrarCobrancaFinanceiro = integrarCobrancaFinanceiro;
	}

	@Column(name = "par_tip_cob_empresa")
	@Enumerated(EnumType.STRING)
	public TipoCobrancaEmpresaEnum getTipoCobrancaEmpresa() {
		return tipoCobrancaEmpresa;
	}

	public void setTipoCobrancaEmpresa(TipoCobrancaEmpresaEnum tipoCobrancaEmpresa) {
		this.tipoCobrancaEmpresa = tipoCobrancaEmpresa;
	}

	@Column(name = "par_cont_contratual")
	@Enumerated(EnumType.STRING)
	public ControleContratualEnum getControleContratual() {
		return controleContratual;
	}

	public void setControleContratual(ControleContratualEnum controleContratual) {
		this.controleContratual = controleContratual;
	}

	@Column(name = "par_nosso_numero")
	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	@Column(name = "par_prazo_atr_mensalidade")
	public Long getPrazoAtrasadoMensalidade() {
		return prazoAtrasadoMensalidade;
	}

	public void setPrazoAtrasadoMensalidade(Long prazoAtrasadoMensalidade) {
		this.prazoAtrasadoMensalidade = prazoAtrasadoMensalidade;
	}

	@Column(name = "par_prazo_can_contrato")
	public Long getPrazoCancelamentoContrato() {
		return prazoCancelamentoContrato;
	}

	public void setPrazoCancelamentoContrato(Long prazoCancelamentoContrato) {
		this.prazoCancelamentoContrato = prazoCancelamentoContrato;
	}

	@Column(name = "par_multa_atraso")
	public BigDecimal getMultaAtraso() {
		return multaAtraso;
	}

	public void setMultaAtraso(BigDecimal multaAtraso) {
		this.multaAtraso = multaAtraso;
	}

	@Column(name = "par_prazo_apl_multa")
	public Long getPrazoAplicacaoMulta() {
		return prazoAplicacaoMulta;
	}

	public void setPrazoAplicacaoMulta(Long prazoAplicacaoMulta) {
		this.prazoAplicacaoMulta = prazoAplicacaoMulta;
	}

	@Column(name = "par_juros_fixo_diario")
	public BigDecimal getJurosFixoDiario() {
		return jurosFixoDiario;
	}

	public void setJurosFixoDiario(BigDecimal jurosFixoDiario) {
		this.jurosFixoDiario = jurosFixoDiario;
	}

	@OneToMany(mappedBy = "parametrizacao")
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	@OneToMany(mappedBy = "parametrizacao")
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Dia> getDiasVencimento() {
		return diasVencimento;
	}

	public void setDiasVencimento(List<Dia> diasVencimento) {
		this.diasVencimento = diasVencimento;
	}

	@Transient
	public String getCodigoMesProcessamentoTransiente() {
		return codigoMesProcessamentoTransiente;
	}

	public void setCodigoMesProcessamentoTransiente(String codigoMesProcessamentoTransiente) {
		this.codigoMesProcessamentoTransiente = codigoMesProcessamentoTransiente;
	}

	@Transient
	public String getCodigoControleBiometricoTransiente() {
		return codigoControleBiometricoTransiente;
	}

	public void setCodigoControleBiometricoTransiente(String codigoControleBiometricoTransiente) {
		this.codigoControleBiometricoTransiente = codigoControleBiometricoTransiente;
	}

	@Transient
	public String getCodigoEditorTextoTransiente() {
		return codigoEditorTextoTransiente;
	}

	public void setCodigoEditorTextoTransiente(String codigoEditorTextoTransiente) {
		this.codigoEditorTextoTransiente = codigoEditorTextoTransiente;
	}

	@Transient
	public String getCodigoSimNaoTransiente() {
		return codigoSimNaoTransiente;
	}

	public void setCodigoSimNaoTransiente(String codigoSimNaoTransiente) {
		this.codigoSimNaoTransiente = codigoSimNaoTransiente;
	}

	@Transient
	public String getCodigoTipoCobrançaEmpresaTransiente() {
		return codigoTipoCobrançaEmpresaTransiente;
	}

	public void setCodigoTipoCobrançaEmpresaTransiente(String codigoTipoCobrançaEmpresaTransiente) {
		this.codigoTipoCobrançaEmpresaTransiente = codigoTipoCobrançaEmpresaTransiente;
	}

	@Transient
	public String getCodigoControleContratualTransiente() {
		return codigoControleContratualTransiente;
	}

	public void setCodigoControleContratualTransiente(String codigoControleContratualTransiente) {
		this.codigoControleContratualTransiente = codigoControleContratualTransiente;
	}

	@Column(name = "par_codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getUm() {
		return um;
	}

	public void setUm(Boolean um) {
		this.um = um;
	}

	public Boolean getDois() {
		return dois;
	}

	public void setDois(Boolean dois) {
		this.dois = dois;
	}

	public Boolean getTres() {
		return tres;
	}

	public void setTres(Boolean tres) {
		this.tres = tres;
	}

	public Boolean getQuatro() {
		return quatro;
	}

	public void setQuatro(Boolean quatro) {
		this.quatro = quatro;
	}

	public Boolean getCinco() {
		return cinco;
	}

	public void setCinco(Boolean cinco) {
		this.cinco = cinco;
	}

	public Boolean getSeis() {
		return seis;
	}

	public void setSeis(Boolean seis) {
		this.seis = seis;
	}

	public Boolean getSete() {
		return sete;
	}

	public void setSete(Boolean sete) {
		this.sete = sete;
	}

	public Boolean getOito() {
		return oito;
	}

	public void setOito(Boolean oito) {
		this.oito = oito;
	}

	public Boolean getNove() {
		return nove;
	}

	public void setNove(Boolean nove) {
		this.nove = nove;
	}

	public Boolean getDez() {
		return dez;
	}

	public void setDez(Boolean dez) {
		this.dez = dez;
	}

	public Boolean getOnze() {
		return onze;
	}

	public void setOnze(Boolean onze) {
		this.onze = onze;
	}

	public Boolean getDoze() {
		return doze;
	}

	public void setDoze(Boolean doze) {
		this.doze = doze;
	}

	public Boolean getTreze() {
		return treze;
	}

	public void setTreze(Boolean treze) {
		this.treze = treze;
	}

	public Boolean getQuatorze() {
		return quatorze;
	}

	public void setQuatorze(Boolean quatorze) {
		this.quatorze = quatorze;
	}

	public Boolean getQuinze() {
		return quinze;
	}

	public void setQuinze(Boolean quinze) {
		this.quinze = quinze;
	}

	public Boolean getDezesseis() {
		return dezesseis;
	}

	public void setDezesseis(Boolean dezesseis) {
		this.dezesseis = dezesseis;
	}

	public Boolean getDezessete() {
		return dezessete;
	}

	public void setDezessete(Boolean dezessete) {
		this.dezessete = dezessete;
	}

	public Boolean getDezoito() {
		return dezoito;
	}

	public void setDezoito(Boolean dezoito) {
		this.dezoito = dezoito;
	}

	public Boolean getDezenove() {
		return dezenove;
	}

	public void setDezenove(Boolean dezenove) {
		this.dezenove = dezenove;
	}

	public Boolean getVinte() {
		return vinte;
	}

	public void setVinte(Boolean vinte) {
		this.vinte = vinte;
	}

	public Boolean getVinte_um() {
		return vinte_um;
	}

	public void setVinte_um(Boolean vinte_um) {
		this.vinte_um = vinte_um;
	}

	public Boolean getVinte_dois() {
		return vinte_dois;
	}

	public void setVinte_dois(Boolean vinte_dois) {
		this.vinte_dois = vinte_dois;
	}

	public Boolean getVinte_tres() {
		return vinte_tres;
	}

	public void setVinte_tres(Boolean vinte_tres) {
		this.vinte_tres = vinte_tres;
	}

	public Boolean getVinte_quatro() {
		return vinte_quatro;
	}

	public void setVinte_quatro(Boolean vinte_quatro) {
		this.vinte_quatro = vinte_quatro;
	}

	public Boolean getVinte_cinco() {
		return vinte_cinco;
	}

	public void setVinte_cinco(Boolean vinte_cinco) {
		this.vinte_cinco = vinte_cinco;
	}

	public Boolean getVinte_seis() {
		return vinte_seis;
	}

	public void setVinte_seis(Boolean vinte_seis) {
		this.vinte_seis = vinte_seis;
	}

	public Boolean getVinte_sete() {
		return vinte_sete;
	}

	public void setVinte_sete(Boolean vinte_sete) {
		this.vinte_sete = vinte_sete;
	}

	public Boolean getVinte_oito() {
		return vinte_oito;
	}

	public void setVinte_oito(Boolean vinte_oito) {
		this.vinte_oito = vinte_oito;
	}

	public Boolean getVinte_nove() {
		return vinte_nove;
	}

	public void setVinte_nove(Boolean vinte_nove) {
		this.vinte_nove = vinte_nove;
	}

	public Boolean getTrinta() {
		return trinta;
	}

	public void setTrinta(Boolean trinta) {
		this.trinta = trinta;
	}

	public Boolean getTrinta_um() {
		return trinta_um;
	}

	public void setTrinta_um(Boolean trinta_um) {
		this.trinta_um = trinta_um;
	}

	@Column(name = "par_um")
	public Boolean isUm() {
		return um;
	}

	@Column(name = "par_dois")
	public Boolean isDois() {
		return dois;
	}

	@Column(name = "par_tres")
	public Boolean isTres() {
		return tres;
	}

	@Column(name = "par_quatro")
	public Boolean isQuatro() {
		return quatro;
	}

	@Column(name = "par_cinco")
	public Boolean isCinco() {
		return cinco;
	}

	@Column(name = "par_seis")
	public Boolean isSeis() {
		return seis;
	}

	@Column(name = "par_sete")
	public Boolean isSete() {
		return sete;
	}

	@Column(name = "par_oito")
	public Boolean isOito() {
		return oito;
	}

	@Column(name = "par_nove")
	public Boolean isNove() {
		return nove;
	}

	@Column(name = "par_dez")
	public Boolean isDez() {
		return dez;
	}

	@Column(name = "par_onze")
	public Boolean isOnze() {
		return onze;
	}

	@Column(name = "par_doze")
	public Boolean isDoze() {
		return doze;
	}

	@Column(name = "par_treze")
	public Boolean isTreze() {
		return treze;
	}

	@Column(name = "par_quatorze")
	public Boolean isQuatorze() {
		return quatorze;
	}

	@Column(name = "par_quinze")
	public Boolean isQuinze() {
		return quinze;
	}

	@Column(name = "par_dezesseis")
	public Boolean isDezesseis() {
		return dezesseis;
	}

	@Column(name = "par_dezessete")
	public Boolean isDezessete() {
		return dezessete;
	}

	@Column(name = "par_dezoito")
	public Boolean isDezoito() {
		return dezoito;
	}

	@Column(name = "par_dezenove")
	public Boolean isDezenove() {
		return dezenove;
	}

	@Column(name = "par_vinte")
	public Boolean isVinte() {
		return vinte;
	}

	@Column(name = "par_vinte_um")
	public Boolean isVinte_um() {
		return vinte_um;
	}

	@Column(name = "par_vinte_dois")
	public Boolean isVinte_dois() {
		return vinte_dois;
	}

	@Column(name = "par_vinte_tres")
	public Boolean isVinte_tres() {
		return vinte_tres;
	}

	@Column(name = "par_vinte_quatro")
	public Boolean isVinte_quatro() {
		return vinte_quatro;
	}

	@Column(name = "par_vinte_cinco")
	public Boolean isVinte_cinco() {
		return vinte_cinco;
	}

	@Column(name = "par_vinte_seis")
	public Boolean isVinte_seis() {
		return vinte_seis;
	}

	@Column(name = "par_vinte_sete")
	public Boolean isVinte_sete() {
		return vinte_sete;
	}

	@Column(name = "par_vinte_oito")
	public Boolean isVinte_oito() {
		return vinte_oito;
	}

	@Column(name = "par_vinte_nove")
	public Boolean isVinte_nove() {
		return vinte_nove;
	}

	@Column(name = "par_trinta")
	public Boolean isTrinta() {
		return trinta;
	}

	@Column(name = "par_trinta_um")
	public Boolean isTrinta_um() {
		return trinta_um;
	}
}
