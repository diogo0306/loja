package br.com.eclinic.entity.funcionario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.entity.medico.SexoEnum;

@Entity
@Table(name = "funcionario")
@SequenceGenerator(name = "sequenceFuncionario", sequenceName = "funcionario_id_seq")
public class Funcionario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Date dataNascimento;
	private String idade;
	private String orgao;
	private String cargo;
	private String cpf;
	private String pis;
	private SexoEnum genero;
	private Endereco endereco;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "func_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "func_nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "func_data_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Column(name = "func_idade")
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	@Column(name = "func_orgao")
	public String getOrgao() {
		return orgao;
	}
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	
	@Column(name = "func_cpf")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(name = "func_pis")
	public String getPis() {
		return pis;
	}
	public void setPis(String pis) {
		this.pis = pis;
	}
	
	@Column(name = "func_cargo")
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@Column(name = "func_genero", nullable = false)
	@Enumerated(EnumType.STRING)
	public SexoEnum getGenero() {
		return genero;
	}
	public void setGenero(SexoEnum genero) {
		this.genero = genero;
	}
	
	@OneToOne
	@JoinColumn(name = "func_endereco")
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
