package br.com.eclinic.hibernate.solicitacao;

import java.text.ParseException;
import java.util.List;

import br.com.eclinic.entity.solicitacao.Solicitacao;

public interface SolicitacaoRepository {
	
	void salvar (Solicitacao solicitacao);
	
	void excluir (Solicitacao solicitacao);
	
	void alterar (Solicitacao solicitacao);
	
	Solicitacao buscar (Long pk);
	
	List<Solicitacao> listar();
	
	List<Solicitacao> consultar (Solicitacao solicitacao) throws ParseException;
	
	List<Solicitacao> listarSolicitacoesPorRepresentante(Long id); 

}
