package br.com.eclinic.service.parametrizacao;

import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.entity.parametrizacao.Parametrizacao;
import br.com.eclinic.exception.NegocioException;

public interface ParametrizacaoService {
	
	void salvar(Parametrizacao parametrizacao); //

	void alterar(Parametrizacao parametrizacao); //

	List<Parametrizacao> listar(); //

	void excluir(Parametrizacao parametrizacao) throws NegocioException; //

	Parametrizacao buscar(Long pk) throws NegocioException;
	
	List<Parametrizacao> listarPorEmpresa(Empresa empresa);
	
	List<Parametrizacao> listarPorCliente(Cliente cliente);
	
	List<Parametrizacao> consultar(Parametrizacao parametrizacao);
	

}
