package br.com.eclinic.entity.parametrizacao;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.eclinic.entity.parametrizacao.Parametrizacao;

public class ParametrizacaoDTO {

	private String codigo;
	private Parametrizacao parametrizacao;
	private String mesProcessamentoTransiente;
	private String controleBiometricoTransiente;
	private String editorArquivoTransiente;
	private String tipoCobrancaEmpresaTransiente;
	private String controleContratualTransiente;
	private Date data;
	private Date periodoInicio;
	private String periodoInicialFormatada;
	private Date periodoFim;
	private String periodoFinalFormatada;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Parametrizacao getParametrizacao() {
		return parametrizacao;
	}

	public void setParametrizacao(Parametrizacao parametrizacao) {
		this.parametrizacao = parametrizacao;
	}

	public String getMesProcessamentoTransiente() {
		return mesProcessamentoTransiente;
	}

	public void setMesProcessamentoTransiente(String mesProcessamentoTransiente) {
		this.mesProcessamentoTransiente = mesProcessamentoTransiente;
	}

	public String getControleBiometricoTransiente() {
		return controleBiometricoTransiente;
	}

	public void setControleBiometricoTransiente(String controleBiometricoTransiente) {
		this.controleBiometricoTransiente = controleBiometricoTransiente;
	}

	public String getEditorArquivoTransiente() {
		return editorArquivoTransiente;
	}

	public void setEditorArquivoTransiente(String editorArquivoTransiente) {
		this.editorArquivoTransiente = editorArquivoTransiente;
	}

	public String getTipoCobrancaEmpresaTransiente() {
		return tipoCobrancaEmpresaTransiente;
	}

	public void setTipoCobrancaEmpresaTransiente(String tipoCobrancaEmpresaTransiente) {
		this.tipoCobrancaEmpresaTransiente = tipoCobrancaEmpresaTransiente;
	}

	public String getControleContratualTransiente() {
		return controleContratualTransiente;
	}

	public void setControleContratualTransiente(String controleContratualTransiente) {
		this.controleContratualTransiente = controleContratualTransiente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(Date periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public Date getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(Date periodoFim) {
		this.periodoFim = periodoFim;
	}

	public String getPeriodoInicialFormatada() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		periodoInicialFormatada = formatado.format(periodoInicio);
		return periodoInicialFormatada;
	}

	public void setPeriodoInicialFormatada(String periodoInicialFormatada) {
		this.periodoInicialFormatada = periodoInicialFormatada;
	}

	public String getPeriodoFinalFormatada() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		periodoFinalFormatada = formatado.format(periodoFim);
		return periodoFinalFormatada;
	}

	public void setPeriodoFinalFormatada(String periodoFinalFormatada) {
		this.periodoFinalFormatada = periodoFinalFormatada;
	}

}
