package br.com.eclinic.service.documentacao;

import java.util.List;

import br.com.eclinic.entity.documentacao.Documentacao;

public interface DocumentacaoService {
	
	void salvar (Documentacao documentacao);
	
	void alterar (Documentacao documentacao);
	
	Documentacao buscar (Long pk);
	
	List<Documentacao> listar();

}
