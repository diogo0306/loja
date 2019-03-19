package br.com.eclinic.entity.documentacao;

import java.io.Serializable;

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

import br.com.eclinic.entity.medico.OrgaoEmissorEnum;

@Entity
@Table(name = "documentacao")
@SequenceGenerator(name = "sequenceDocumentacao", sequenceName = "documentacao_id_seq")
public class Documentacao implements Serializable {

	private static final long serialVersionUID = -4475472043206605319L;
	private Long id;
	private String cpf;
	private String rg;
	private String crm;
	private String telefone;
	private String celular;
	private String email;
	private OrgaoEmissorEnum orgaoEmissorEnum;
	private String orgaoEmissorTransiente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "doc_cpf")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(name = "doc_rg")
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	@Column(name = "doc_crm")
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	@Column(name = "doc_telefone")
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Column(name = "doc_celular")
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	@Column(name = "doc_email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "doc_orgao_emissor")
	@Enumerated(EnumType.STRING)
	public OrgaoEmissorEnum getOrgaoEmissorEnum() {
		return orgaoEmissorEnum;
	}
	public void setOrgaoEmissorEnum(OrgaoEmissorEnum orgaoEmissorEnum) {
		this.orgaoEmissorEnum = orgaoEmissorEnum;
	}
	
	@Transient
	public String getOrgaoEmissorTransiente() {
		return orgaoEmissorTransiente;
	}
	public void setOrgaoEmissorTransiente(String orgaoEmissorTransiente) {
		this.orgaoEmissorTransiente = orgaoEmissorTransiente;
	}
	
}
