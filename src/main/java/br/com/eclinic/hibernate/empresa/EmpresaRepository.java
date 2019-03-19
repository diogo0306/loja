package br.com.eclinic.hibernate.empresa;

import java.util.List;

import br.com.eclinic.entity.empresa.Empresa;

public interface EmpresaRepository {
	
	void salvar(Empresa empresa);

	Empresa buscar(Long pk);

	void alterar(Empresa empresa);

	List<Empresa> listar(Empresa empresa);

	
	void excluir(Empresa empresa);

	List<Empresa> listarOrder(String atributoOrder);
	

	List<Empresa> consultar(Empresa empresa);

	List<Empresa>consultarPorDescricao (String descricao);
	

}
