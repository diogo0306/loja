package br.com.eclinic.entity.usuario;

import java.util.Date;
import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;

public class UsuarioDTO {
	
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
	private FuncionalidadeOperacaoDTO funcionalidadeOperacaoDTO;
	
	public FuncionalidadeOperacaoDTO getFuncionalidadeOperacaoDTO() {
		return funcionalidadeOperacaoDTO;
	}
	public void setFuncionalidadeOperacaoDTO(FuncionalidadeOperacaoDTO funcionalidadeOperacaoDTO) {
		this.funcionalidadeOperacaoDTO = funcionalidadeOperacaoDTO;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoUsuarioEnum getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getClientesPermissao() {
		return clientesPermissao;
	}
	public void setClientesPermissao(List<Cliente> clientesPermissao) {
		this.clientesPermissao = clientesPermissao;
	}
	public boolean isPermitido() {
		return permitido;
	}
	public void setPermitido(boolean permitido) {
		this.permitido = permitido;
	}
	public String getAlterarSenha() {
		return alterarSenha;
	}
	public void setAlterarSenha(String alterarSenha) {
		this.alterarSenha = alterarSenha;
	}
	public Date getDataValidadeSenha() {
		return dataValidadeSenha;
	}
	public void setDataValidadeSenha(Date dataValidadeSenha) {
		this.dataValidadeSenha = dataValidadeSenha;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Cliente getClienteAdicionar() {
		return clienteAdicionar;
	}
	public void setClienteAdicionar(Cliente clienteAdicionar) {
		this.clienteAdicionar = clienteAdicionar;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	private Cliente clienteAdicionar;
	private String nome;
	
	
	
	
}
