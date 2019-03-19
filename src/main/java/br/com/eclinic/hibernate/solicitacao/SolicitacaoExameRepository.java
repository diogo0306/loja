package br.com.eclinic.hibernate.solicitacao;

import java.text.ParseException;
import java.util.List;

import br.com.eclinic.entity.solicitacao.SolicitacaoExame;

public interface SolicitacaoExameRepository {
	
	void salvar (SolicitacaoExame solicitacaoExame);
	
	void excluir (SolicitacaoExame solicitacaoExame);
	
	void alterar (SolicitacaoExame solicitacaoExame);
	
	SolicitacaoExame buscar (Long pk);
	
	List<SolicitacaoExame> listar();
	
	List<SolicitacaoExame> consultar (SolicitacaoExame solicitacaoExame) throws ParseException;

}
