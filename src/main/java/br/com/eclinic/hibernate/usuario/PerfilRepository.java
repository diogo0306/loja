package br.com.eclinic.hibernate.usuario;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.Perfil;

public interface PerfilRepository {

	public Perfil consultarFuncionalidadesOperacoes(Perfil perfil);

	void salvar(Perfil perfil);

	Perfil buscar(Long pk);

	void alterar(Perfil perfil);

	List<Perfil> listar();

	void excluir(Perfil perfil);

	List<Perfil> listarOrder(String atributoOrder);

	public void removerFuncionalidadeOperacaoUsuario(Perfil perfil,
			Funcionalidade funcionalidade, Operacao operacao);

	public List<Funcionalidade> listarFuncionalidades();

	public List<Operacao> listarOperacoes();

	void salvarFuncionalidadesOperacao(Funcionalidade funcionalidade,
			Perfil perfil);

	public void removerFuncionalidadeOperacaoUsuario(String idFuncionalidade,
			String idPerfil);

	public List<Perfil> consultarPorDescricao(String descricao, Cliente cliente);

	public List<Perfil> listarPorCliente(Cliente cliente);

}
