package br.com.eclinic.hibernate.parametrizacao;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.entity.parametrizacao.Parametrizacao;

public interface ParametrizacaoRepository {
	
	void salvar(Parametrizacao parametrizacao);

	Parametrizacao buscar(Long pk);

	void alterar(Parametrizacao parametrizacao);

	List<Parametrizacao> listar();

	void excluir(Parametrizacao parametrizacao);

	List<Parametrizacao> listarOrder(String atributoOrder);
	
	List<Parametrizacao> consultar(Parametrizacao parametrizacao);
	
	List<Parametrizacao> listarPorEmpresa(Empresa empresa);
	
	List<Parametrizacao> listarPorCliente(Cliente cliente);
	
	

}
