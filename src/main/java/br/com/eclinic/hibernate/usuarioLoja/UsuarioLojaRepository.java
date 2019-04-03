package br.com.eclinic.hibernate.usuarioLoja;

import java.util.List;

import br.com.eclinic.entity.produto.Produto;
import br.com.eclinic.entity.usuarioLoja.UsuarioLoja;

public interface UsuarioLojaRepository {

	void salvar(UsuarioLoja usuarioLoja);

	void alterar(UsuarioLoja usuarioLoja);

	void excluir(UsuarioLoja usuarioLoja);

	UsuarioLoja buscar(Long pk);

	List<UsuarioLoja> listar();

	List<UsuarioLoja> consultar(UsuarioLoja usuarioLoja);

}