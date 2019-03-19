package br.com.eclinic.service.solicitacao;

import java.text.ParseException;
import java.util.List;

import br.com.eclinic.entity.solicitacao.SolicitacaoConsulta;

public interface SolicitacaoConsultaService {
	
	void salvar (SolicitacaoConsulta solicitacaoConsulta);
	
	void excluir (SolicitacaoConsulta solicitacaoConsulta);
	
	void alterar (SolicitacaoConsulta solicitacaoConsulta);
	
	SolicitacaoConsulta buscar (Long pk);
	
	List<SolicitacaoConsulta> listar();
	
	List<SolicitacaoConsulta> consultar (SolicitacaoConsulta solicitacaoConsulta) throws ParseException;
	
	List<SolicitacaoConsulta> listarPorPaciente(Long id);

}
