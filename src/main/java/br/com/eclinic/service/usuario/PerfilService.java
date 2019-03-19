package br.com.eclinic.service.usuario;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.Perfil;
import br.com.eclinic.exception.NegocioException;

public interface PerfilService {
	void salvar(Perfil perfil);

	void alterar(Perfil perfil);

	List<Perfil> listar();

	void excluir(Perfil perfil) throws NegocioException;

	Perfil buscar(Long pk);
	
	public Perfil consultarFuncionalidadesOperacoesUsuario(Perfil perfil);

	void removerFuncionalidadeOperacaoUsuario(Perfil perfil,
			Funcionalidade funcionalidade, Operacao operacao);

	public List<Funcionalidade> recuperarFuncionalidadesPorSecao(
			List<Funcionalidade> listaFuncionalidadesUsuario, String secao);

	public List<Funcionalidade> listarFuncionalidades();

	public List<Operacao> listarOperacoes();

	void salvarFuncionalidadesOperacoesUsuario(Funcionalidade funcionalidade,
			Perfil perfil);

	void removerFuncionalidadeOperacaoUsuario(String idFuncionalidade,
			String idPerfil);
	
	public List<Perfil> consultarPorDescricao(String descricao, Cliente cliente);
	
	List<Perfil> listarPorCliente(Cliente cliente);

}
