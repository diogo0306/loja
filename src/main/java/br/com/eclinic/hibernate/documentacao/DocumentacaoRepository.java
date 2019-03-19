package br.com.eclinic.hibernate.documentacao;

import java.util.List;

import br.com.eclinic.entity.documentacao.Documentacao;

public interface DocumentacaoRepository {
	
	void salvar (Documentacao documentacao);
	
	void alterar (Documentacao documentacao);
	
	Documentacao buscar (Long pk);
	
	List<Documentacao> listar();

}
