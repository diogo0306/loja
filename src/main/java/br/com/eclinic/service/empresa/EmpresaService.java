package br.com.eclinic.service.empresa;

import java.util.List;

import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.exception.NegocioException;

public interface EmpresaService {
	
	void salvar (Empresa empresa);
	
	void alterar(Empresa empresa);
	
	List<Empresa> listar();
	
	void excluir(Empresa empresa) throws NegocioException;
	
	Empresa buscar(Long pk) throws NegocioException;
	
	List<Empresa> consultar (Empresa Empresa);
	
	List<Empresa> consultarPorDescricao (String descricao);
	
}

