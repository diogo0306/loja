package br.com.eclinic.service.cobranca;

import java.util.List;

import br.com.eclinic.entity.paciente.Cobranca;

public interface CobrancaService {
	
	void salvar (Cobranca cobranca);
	
	Cobranca buscar (Long pk);
	
	void alterar (Cobranca cobranca);
	
	void excluir (Cobranca cobranca);
	
	List<Cobranca> listar();

}
