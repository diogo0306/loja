package br.com.eclinic.entity.cliente;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.usuario.Perfil;
import br.com.eclinic.entity.usuario.Usuario;

@Entity
@Table(name = "cliente")
@SequenceGenerator(name = "sequenceCliente", sequenceName = "cliente_id_seq")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String fantasia;
	private String cnpj;
	private String cnae;
	private String cnaeDescricao;
	private List<Perfil> perfis;
	private List<Usuario> usuarios;
	private List<Paciente> pacientes;
	private List<Medico> medicos;
	private List<Agendamento> agendamentos;
	private List<Exame> exames;
	private List<Empresa> empresa;

	private String bairro;
	private String logradouro;
	private String cep;
	private String complemento;
	private String telefone;
	private String celular;
	private String email;
	private String telefoneSecundario;
	private String celularSecundario;

	private String pais;
	private String estado;
	private String cidade;
	private Tabela tabela;

	public Cliente() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cli_nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cli_fantasia")
	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	@Column(name = "cli_cnpj", nullable = false, unique = true)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@OneToMany(mappedBy = "cliente")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	@OneToMany(mappedBy = "cliente")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	@OneToMany(mappedBy = "cliente")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	@Column(name = "cli_bairro")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "cli_logradouro")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "cli_cep")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "cli_complemento")
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Column(name = "cli_telefone")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "cli_celular")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "cli_email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "cli_pais", nullable = true)
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Column(name = "cli_estado", nullable = true)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "cli_cidade", nullable = true)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@OneToMany(mappedBy = "cliente")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	@OneToMany(mappedBy = "cliente")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	@OneToMany(mappedBy = "cliente", targetEntity = Empresa.class, fetch = FetchType.LAZY)
	public List<Empresa> getEmpresa() {
		return empresa;
	}

	public void setEmpresa(List<Empresa> empresa) {
		this.empresa = empresa;
	}

	@Column(name = "cli_cnae")
	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	@Column(name = "cli_cnae_descricao")
	public String getCnaeDescricao() {
		return cnaeDescricao;
	}

	public void setCnaeDescricao(String cnaeDescricao) {
		this.cnaeDescricao = cnaeDescricao;
	}

	@Column(name = "cli_telefone_secundario")
	public String getTelefoneSecundario() {
		return telefoneSecundario;
	}

	public void setTelefoneSecundario(String telefoneSecundario) {
		this.telefoneSecundario = telefoneSecundario;
	}

	@Column(name = "cli_celular_secundario")
	public String getCelularSecundario() {
		return celularSecundario;
	}

	public void setCelularSecundario(String celularSecundario) {
		this.celularSecundario = celularSecundario;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tab_id", nullable = true)
	public Tabela getTabela() {
		return tabela;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

}
