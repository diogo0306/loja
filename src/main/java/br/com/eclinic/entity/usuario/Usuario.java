package br.com.eclinic.entity.usuario;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.representante.Representante;

@SuppressWarnings("unused")
@Entity
@Table(name = "usu_usuario")
@SequenceGenerator(name = "sequenceUsuario", sequenceName = "usu_usuario_usu_id_seq")
public class Usuario implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String login;
	private String senha;
	private TipoUsuarioEnum tipoUsuario;
	private boolean isAdmin;
	private String telefone;
	private String cpf;
	private String rg;
	private String email;
	private Cliente cliente;
	private List<Cliente> clientesPermissao;
	private boolean permitido;
	private String alterarSenha;
	private Date dataValidadeSenha;
	private Perfil perfil;
	private Cliente clienteAdicionar;
	private String nome;
	private FuncionalidadeOperacaoDTO funcionalidadeOperacaoDTO;
	private Paciente paciente;
	private Representante representante;
	private Medico medico;
	private Credenciado credenciado;
	
	/**
	* Recupera o id da entidade.
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="usu_login")
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name="usu_senha")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Transient
	public boolean getIsAdmin() {
		boolean isAdmin = false;
		if(TipoUsuarioEnum.ADMIN.equals(this.tipoUsuario)){
			isAdmin = true;
		}
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@Column(name = "usu_tipo")
	@Enumerated(EnumType.STRING)
	public TipoUsuarioEnum getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	@Column(name = "usu_telefone")
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Column(name = "usu_cpf")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(name = "usu_rg")
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	@Column(name = "usu_email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cli_id")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean isPermitido(String descricaoFuncionalidade, String operacao) {
		return permitido;
	}
	public void setPermitido(boolean permitido) {
		this.permitido = permitido;
	}
	
	@Column(name = "usu_alterar_senha")
	public String getAlterarSenha() {
		return alterarSenha;
	}
	public void setAlterarSenha(String alterarSenha) {
		this.alterarSenha = alterarSenha;
	}
	
	@Column(name = "usu_data_validade_senha")
	public Date getDataValidadeSenha() {
		return dataValidadeSenha;
	}
	public void setDataValidadeSenha(Date dataValidadeSenha) {
		this.dataValidadeSenha = dataValidadeSenha;
	}
	
	@Transient
	public boolean isExibirTelaAlterarSenha() {
		boolean retorno = false;
		
		if (StringUtils.isNotBlank(this.alterarSenha) && this.alterarSenha.equals("S")) {
			retorno = true;
		}
		
		return retorno;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "per_id")
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_cliente", joinColumns = { 
			@JoinColumn(name = "sec_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "cli_id", 
					nullable = false, updatable = false) })
	public List<Cliente> getClientesPermissao() {
		return clientesPermissao;
	}
	
	public void setClientesPermissao(List<Cliente> clientesPermissao) {
		this.clientesPermissao = clientesPermissao;
	}
	
	@Transient
	public Cliente getClienteAdicionar() {
		return clienteAdicionar;
	}
	public void setClienteAdicionar(Cliente clienteAdicionar) {
		this.clienteAdicionar = clienteAdicionar;
	}
	
	@Column(name = "usu_nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Transient
	public FuncionalidadeOperacaoDTO getFuncionalidadeOperacaoDTO() {
		return funcionalidadeOperacaoDTO;
	}
	public void setFuncionalidadeOperacaoDTO(FuncionalidadeOperacaoDTO funcionalidadeOperacaoDTO) {
		this.funcionalidadeOperacaoDTO = funcionalidadeOperacaoDTO;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pac_id")
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rep_id")
	public Representante getRepresentante() {
		return representante;
	}
	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "med_id")
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cred_id")
	public Credenciado getCredenciado() {
		return credenciado;
	}
	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}
}
