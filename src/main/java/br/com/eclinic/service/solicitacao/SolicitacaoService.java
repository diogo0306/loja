package br.com.eclinic.service.solicitacao;

import java.text.ParseException;
import java.util.List;

import br.com.eclinic.entity.solicitacao.Solicitacao;

public interface SolicitacaoService {
	
	void salvar (Solicitacao solicitacao);
	
	void excluir (Solicitacao solicitacao);
	
	void alterar (Solicitacao solicitacao);
	
	Solicitacao buscar (Long pk);
	
	List<Solicitacao> listar();
	
	List<Solicitacao> consultar (Solicitacao solicitacao) throws ParseException;
	
	List<Solicitacao> listarSolicitacoesPorRepresentante(Long id);

}
