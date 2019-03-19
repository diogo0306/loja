package br.com.eclinic.entity.solicitacao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "solicitacao_exame")
@SequenceGenerator(name = "sequenceSolicitacaoExame", sequenceName = "solicitacao_exame_id_seq")
public class SolicitacaoExame implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long idPaciente;
	private Long idExame;
	private Long idCredenciado;
	private String telefonePaciente;
	private String nomePaciente;
	private String nomeExame;
	private String cpf;
	private String status;
	private Date data;
	private Date dataExame;
	private String dataExameFormatada;
	private String dataFormatada;
	private String motivo;
	private byte[] imagem;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "slc_exa_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "slc_exa_id_paciente")
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	
	@Column(name = "slc_exa_nome_paciente")
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	
	@Column(name = "slc_exa_nome_exame")
	public String getNomeExame() {
		return nomeExame;
	}
	public void setNomeExame(String nomeExame) {
		this.nomeExame = nomeExame;
	}
	
	@Column(name = "slc_exa_cpf")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(name = "slc_exa_status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "slc_exa_data")
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Transient
	public String getDataFormatada() {
		if(this.data != null){
			GregorianCalendar dataConfec = new GregorianCalendar();
			dataConfec.setTime(this.data);
			dataFormatada = dataConfec.get(Calendar.DAY_OF_MONTH) + "/"
			+ (dataConfec.get(Calendar.MONTH) + 1) + "/"
			+ dataConfec.get(Calendar.YEAR);
		}
		return dataFormatada;
	}
	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}
	
	@Column(name = "slc_exa_motivo")
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	@Lob
	@Column(name = "slc_exa_imagem")
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	@Column(name = "slc_exa_data_exame")
	public Date getDataExame() {
		return dataExame;
	}
	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}
	
	@Column(name = "slc_exa_telefone_paciente")
	public String getTelefonePaciente() {
		return telefonePaciente;
	}
	public void setTelefonePaciente(String telefonePaciente) {
		this.telefonePaciente = telefonePaciente;
	}
	
	@Transient
	public String getDataExameFormatada() {
		if(this.dataExame != null){
			GregorianCalendar dataConfec = new GregorianCalendar();
			dataConfec.setTime(this.dataExame);
			dataExameFormatada = dataConfec.get(Calendar.DAY_OF_MONTH) + "/"
			+ (dataConfec.get(Calendar.MONTH) + 1) + "/"
			+ dataConfec.get(Calendar.YEAR);
		}
		return dataExameFormatada;
	}
	public void setDataExameFormatada(String dataExameFormatada) {
		this.dataExameFormatada = dataExameFormatada;
	}
	
	@Column(name = "slc_exa_id_exame")
	public Long getIdExame() {
		return idExame;
	}
	public void setIdExame(Long idExame) {
		this.idExame = idExame;
	}
	
	@Column(name = "slc_exa_id_credenciado")
	public Long getIdCredenciado() {
		return idCredenciado;
	}
	public void setIdCredenciado(Long idCredenciado) {
		this.idCredenciado = idCredenciado;
	}
	
}
