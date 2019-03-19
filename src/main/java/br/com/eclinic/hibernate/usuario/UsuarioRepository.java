package br.com.eclinic.hibernate.usuario;

import java.util.Date;
import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.Usuario;

public interface UsuarioRepository {
	
	public Usuario buscarUsuarioPorLoginSenha(Usuario usuario);
	
	// API
	public Usuario buscarUsuarioPorCpfSenha(Usuario usuario);
	
	public Usuario consultarFuncionalidadesOperacoes(Usuario usuario);
	
	void salvar(Usuario usuario);

	Usuario buscar(Long pk);

	void alterar(Usuario usuario);

	List<Usuario> listar();

	void excluir(Usuario usuario);

	List<Usuario> listarOrder(String atributoOrder);

	public void removerFuncionalidadeOperacaoUsuario(Usuario usuario,
			Funcionalidade funcionalidade, Operacao operacao);

	public List<Usuario> consultarPorLogin(String login, Cliente cliente);
	
	public List<Funcionalidade> listarFuncionalidades();
	
	public List<Operacao> listarOperacoes();

	void salvarFuncionalidadesOperacao(Funcionalidade funcionalidade,
			Usuario usuario);

	public void removerFuncionalidadeOperacaoUsuario(String idFuncionalidade,
			String idUsuario);
	
	List<Usuario> listarPorCliente(Cliente cliente);
	
	String consultarSenha(Long idUsuario);

	Date consultarDataValidadeSenha(Long idUsuario);
	
	Usuario consultarPorLogin(String login);
	
	// Métodos oauth
	Usuario findByCpf(String cpf);
	boolean existsUsersByCpf(String cpf);

}
