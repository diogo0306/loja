package br.com.eclinic.entity.solicitacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "solicitacao_consulta")
@SequenceGenerator(name = "sequenceSolicitacaoConsulta", sequenceName = "solicitacao_consulta_id_seq")
public class SolicitacaoConsulta implements Serializable {

	private static final long serialVersionUID = 411768789033189641L;
	private Long id;
	private Long idPaciente;
	private Long idCredenciado;
	private Date dataConsulta;
	private String dataConsultaFormatada;
	private String nomePaciente;
	private String cpf;
	private String status;
	private String codigo;
	private StatusEnum statusEnum;
	private Date data;
	private String dataFormatada;
	private String motivo;
	private BigDecimal valorConsulta;
	private String valorConsultaFormatado;
	private byte[] imagem;
	private String observacoes;
	private String orientacoes;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "slc_con_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "slc_con_nome_paciente")
	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	@Column(name = "slc_con_cpf")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "slc_con_status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "slc_con_data")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Transient
	public String getDataFormatada() {
		if (this.data != null) {
			GregorianCalendar dataConfec = new GregorianCalendar();
			dataConfec.setTime(this.data);
			dataFormatada = dataConfec.get(Calendar.DAY_OF_MONTH) + "/" + (dataConfec.get(Calendar.MONTH) + 1) + "/"
					+ dataConfec.get(Calendar.YEAR);
		}
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	@Column(name = "slc_con_motivo")
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Lob
	@Column(name = "slc_con_imagem")
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Column(name = "slc_con_id_paciente")
	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	@Column(name = "slc_con_id_credenciado")
	public Long getIdCredenciado() {
		return idCredenciado;
	}

	public void setIdCredenciado(Long idCredenciado) {
		this.idCredenciado = idCredenciado;
	}

	@Column(name = "slc_con_data_consulta")
	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	@Transient
	public String getDataConsultaFormatada() {
		if (this.dataConsulta != null) {
			GregorianCalendar dataConfec = new GregorianCalendar();
			dataConfec.setTime(this.dataConsulta);
			dataConsultaFormatada = dataConfec.get(Calendar.DAY_OF_MONTH) + "/" + (dataConfec.get(Calendar.MONTH) + 1)
					+ "/" + dataConfec.get(Calendar.YEAR);
		}
		return dataConsultaFormatada;
	}

	public void setDataConsultaFormatada(String dataConsultaFormatada) {
		this.dataConsultaFormatada = dataConsultaFormatada;
	}

	@Column(name = "slc_con_valor_consulta")
	public BigDecimal getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(BigDecimal valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	@Transient
	public String getValorConsultaFormatado() {
		return valorConsultaFormatado;
	}

	public void setValorConsultaFormatado(String valorConsultaFormatado) {
		this.valorConsultaFormatado = valorConsultaFormatado;
	}

	@Column(name = "slc_con_codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "slc_con_status_enum")
	@Enumerated(EnumType.STRING)
	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}
}
