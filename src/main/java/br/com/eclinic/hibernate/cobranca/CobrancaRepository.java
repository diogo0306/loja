package br.com.eclinic.hibernate.cobranca;

import java.util.List;

import br.com.eclinic.entity.paciente.Cobranca;

public interface CobrancaRepository {
	
	void salvar (Cobranca cobranca);
	
	Cobranca buscar (Long pk);
	
	void alterar (Cobranca cobranca);
	
	void excluir (Cobranca cobranca);
	
	List<Cobranca> listar();

}
