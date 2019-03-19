package br.com.eclinic.hibernate.autorizacao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.exame.Exame;

public interface AutorizacaoRepository {
	
	void salvar (Autorizacao autorizacao);
	
	void alterar (Autorizacao autorizacao);
	
	void excluir (Autorizacao autorizacao);
	
	Autorizacao buscar (Long pk);
	
	List<Autorizacao> listar();
	
	List<Autorizacao> consultar (Autorizacao autorizacao);
	
	List<Autorizacao> listarAutorizacaoPorPaciente(Long id);
	
	List<Exame> listarExamesPorAutorizacao(Long id);
	
	Exame consultarExamePorId(Session session, BigInteger idExame);
	
	List<Autorizacao> consultarRelatorioAutorizacoes(Date dataInicial, Date dataFinal);

}
