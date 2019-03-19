package br.com.eclinic.hibernate.contas;

import java.util.Date;
import java.util.List;

import br.com.eclinic.entity.contas.ContasReceber;

public interface ContasReceberRepository {

	void salvar(ContasReceber contasReceber);

	void alterar(ContasReceber contasReceber);

	void excluir(ContasReceber contasReceber);

	ContasReceber buscar(Long pk);
	
	List<ContasReceber> listar();
	
	List<ContasReceber> consultar(ContasReceber contasReceber);
	
	List<ContasReceber> consultarRelatorioPorPeriodo(Date dataInicial, Date dataFinal);

}
