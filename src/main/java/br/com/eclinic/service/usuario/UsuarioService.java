package br.com.eclinic.service.usuario;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;

public interface UsuarioService {

	public Usuario realizarLogin(Usuario usuario) throws NegocioException;
	
	// API
	public Usuario realizarCpf(Usuario usuario) throws NegocioException;

	void salvar(Usuario usuario) throws NegocioException;

	void alterar(Usuario usuario);

	List<Usuario> listar();

	void excluir(Usuario usuario) throws NegocioException;

	Usuario buscar(Long pk);

	public Usuario consultarFuncionalidadesOperacoesUsuario(Usuario usuario);

	void removerFuncionalidadeOperacaoUsuario(Usuario usuario,
			Funcionalidade funcionalidade, Operacao operacao);

	public List<Funcionalidade> recuperarFuncionalidadesPorSecao(
			List<Funcionalidade> listaFuncionalidadesUsuario, String secao);

	public List<Usuario> consultarPorLogin(String login, Cliente cliente);

	public List<Funcionalidade> listarFuncionalidades();

	public List<Operacao> listarOperacoes();

	void salvarFuncionalidadesOperacoesUsuario(Funcionalidade funcionalidade,
			Usuario usuario);

	void removerFuncionalidadeOperacaoUsuario(String idFuncionalidade,
			String idUsuario);

	List<Usuario> listarPorCliente(Cliente cliente);

	void alterarSenha(Long idUsuario, String senhaAtual, String novaSenha)
			throws NegocioException;
	
	void resetarSenha(Long idUsuario) throws NegocioException;
	
	Usuario consultarPorLogin(String login);
	
	// Métodos oauth
	Usuario findByCpf(String cpf);
	boolean existsUsersByCpf(String cpf);

}
