package br.com.eclinic.hibernate.acomodacao;

import java.util.List;

import br.com.eclinic.entity.acomodacao.Acomodacao;

public interface AcomodacaoRepository {
	
	void salvar (Acomodacao acomodacao);
	
	void alterar (Acomodacao acomodacao);
	
	void excluir (Acomodacao acomodacao);
	
	Acomodacao buscar (Long pk);
	
	List<Acomodacao> listar();

}
