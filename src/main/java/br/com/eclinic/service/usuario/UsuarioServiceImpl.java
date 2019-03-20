package br.com.eclinic.service.usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.clienteLoja.ClienteLoja;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.Usuario;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.usuario.UsuarioRepository;
import br.com.eclinic.util.Util;

@Service(value = "usuarioService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UsuarioServiceImpl implements UsuarioService {

	private static final String SENHA_EXPIRADA = "Senha expirada, solicitar ao administrador a renovação da senha.";
	private static final String USUÁRIO_SENHA_INVALIDO = "Usuário/Senha inválido(s)";
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario realizarLogin(Usuario usuario) throws NegocioException {
		Usuario retorno = usuarioRepository.buscarUsuarioPorLoginSenha(usuario);
		
		if (retorno == null) {
			throw new NegocioException(USUÁRIO_SENHA_INVALIDO);
		}
		
		if (retorno.getDataValidadeSenha() != null && Util.dataDiff(retorno.getDataValidadeSenha(), new Date()) > 90) {
			throw new NegocioException(SENHA_EXPIRADA);
		}
		
		return retorno;
	}
	
	public void resetarSenha(Long idUsuario) throws NegocioException {
		Usuario usuario = usuarioRepository.buscar(idUsuario);
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(usuario.getDataValidadeSenha());
		calendarDate.add(Calendar.MONTH, 3);
		usuario.setDataValidadeSenha(calendarDate.getTime());
		
		try {
			usuarioRepository.alterar(usuario);
		} catch (Exception e) {
			throw new NegocioException("Não foi possível resetar a senha do usuário.");
		}
		
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	@Autowired
	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public void salvar(Usuario usuario) throws NegocioException {
		validarUsuarioExistente(usuario);
		this.usuarioRepository.salvar(usuario);

	}
	
	private void validarUsuarioExistente(Usuario usuario) throws NegocioException {
		Usuario usuarioBase = this.usuarioRepository.consultarPorLogin(usuario.getLogin());
		if (usuarioBase != null) {
			throw new NegocioException("Login inválido");
		}
	}

	@Override
	public void alterar(Usuario usuario) {
		usuario.setSenha(this.usuarioRepository.consultarSenha(usuario.getId()));
		usuario.setDataValidadeSenha(this.usuarioRepository.consultarDataValidadeSenha(usuario.getId()));
		this.usuarioRepository.alterar(usuario);

	}

	@Override
	public List<Usuario> listar() {
		return this.usuarioRepository.listar();
	}

	@Override
	public void excluir(Usuario usuario) throws NegocioException {
		this.usuarioRepository.excluir(usuario);

	}

	@Override
	public Usuario buscar(Long pk) {
		return this.usuarioRepository.buscar(pk);
	}

	@Override
	public void salvarFuncionalidadesOperacoesUsuario(Funcionalidade funcionalidade,Usuario usuario) {
		this.usuarioRepository.salvarFuncionalidadesOperacao(funcionalidade,usuario);

	}

	@Override
	public void removerFuncionalidadeOperacaoUsuario(Usuario usuario,
			Funcionalidade funcionalidade, Operacao operacao) {
		this.usuarioRepository.removerFuncionalidadeOperacaoUsuario(usuario,
				funcionalidade, operacao);
	}

	@Override
	public Usuario consultarFuncionalidadesOperacoesUsuario(Usuario usuario) {
		return this.usuarioRepository
				.consultarFuncionalidadesOperacoes(usuario);
	}

	@Override
	public List<Funcionalidade> recuperarFuncionalidadesPorSecao(
			List<Funcionalidade> listaFuncionalidadesUsuario, String secao) {
		
		List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();
		
		for (Funcionalidade funcionalidade : listaFuncionalidadesUsuario) {
			if(secao.equals(funcionalidade.getSecao())){
				funcionalidades.add(funcionalidade);
			}
		}
		
		return funcionalidades;
	}

	@Override
	public List<Usuario> consultarPorLogin(String login, Cliente cliente) {
		return this.usuarioRepository.consultarPorLogin(login,cliente);
	}
	
	@Override
	public Usuario consultarPorLogin(String login) {
		return this.usuarioRepository.consultarPorLogin(login);
	}

	@Override
	public List<Funcionalidade> listarFuncionalidades() {
		return this.usuarioRepository.listarFuncionalidades();
	}

	@Override
	public List<Operacao> listarOperacoes() {
		return this.usuarioRepository.listarOperacoes();
	}

	@Override
	public void removerFuncionalidadeOperacaoUsuario(String idFuncionalidade,
			String idUsuario) {
		this.usuarioRepository.removerFuncionalidadeOperacaoUsuario(idFuncionalidade,idUsuario);
		
	}
	
	@Override
	public void alterarSenha(Long idUsuario,
			String senhaAtual, String novaSenha) throws NegocioException {
		
		String senhaAtualCriptografada = Util.criptografia(senhaAtual);
		String senhaAtualBanco = this.usuarioRepository.consultarSenha(idUsuario);
		
		if (!senhaAtualCriptografada.equals(senhaAtualBanco)) {
			throw new NegocioException("Senha atual não informada corretamente");
		}
		
		Usuario usuario = this.buscar(idUsuario);
		usuario.setSenha(Util.criptografia(novaSenha));
		usuario.setAlterarSenha("N");
		this.usuarioRepository.alterar(usuario);
	}

	@Override
	public List<Usuario> listarPorCliente(Cliente cliente) {
		return this.usuarioRepository.listarPorCliente(cliente);
	}

	// Métodos oauth
	@Override
	public Usuario findByCpf(String cpf) {
		return null;
	}

	@Override
	public boolean existsUsersByCpf(String cpf) {
		return false;
	}

	// API
	@Override
	public Usuario realizarCpf(Usuario usuario) throws NegocioException {
		Usuario retorno = usuarioRepository.buscarUsuarioPorCpfSenha(usuario);
		
		if (retorno == null) {
			throw new NegocioException(USUÁRIO_SENHA_INVALIDO);
		}
		
		return retorno;
	}

	@Override
	public List<Usuario> consultar(Usuario usuario) {
		return this.usuarioRepository.consultar(usuario);
	}

	
}
