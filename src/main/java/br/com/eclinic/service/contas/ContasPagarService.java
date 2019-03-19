package br.com.eclinic.service.contas;

import java.util.Date;
import java.util.List;

import br.com.eclinic.entity.contas.ContasPagar;

public interface ContasPagarService {
	
	void salvar (ContasPagar contasPagar);
	
	void alterar (ContasPagar contasPagar);
	
	void excluir (ContasPagar contasPagar);
	
	ContasPagar buscar (Long pk);
	
	List<ContasPagar> listar();
	
	List<ContasPagar> consultar(ContasPagar contasPagar);
	
	List<ContasPagar> consultarRelatorioPorPeriodo(Date dataInicial, Date dataFinal);
	
	List<ContasPagar> configurarGuiaRelatorio(List<ContasPagar> lista);

}
