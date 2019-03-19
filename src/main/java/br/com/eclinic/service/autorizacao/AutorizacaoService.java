package br.com.eclinic.service.autorizacao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.autorizacao.AutorizacaoDTO;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.exception.NegocioException;

public interface AutorizacaoService {
	
	void salvar (Autorizacao autorizacao);
	
	void alterar (Autorizacao autorizacao);
	
	void excluir (Autorizacao autorizacao);
	
	Autorizacao buscar (Long pk);
	
	List<Autorizacao> listar();

	List<Autorizacao> consultar (Autorizacao autorizacao);
	
	AutorizacaoDTO configurarForm(AutorizacaoDTO autorizacaoDTO, HttpServletRequest request);
	
	AutorizacaoDTO configurarExame(AutorizacaoDTO autorizacaoDTO, HttpServletRequest request);
	
	List<Autorizacao> listarAutorizacaoPorPaciente(Long id);
	
	Autorizacao configurarFormDetalhar(Autorizacao autorizacao) throws NegocioException;
	
	List<Exame> listarExamesPorAutorizacao(Long id);
	
	Autorizacao configurarGuiaConsulta(Autorizacao autorizacao) throws NegocioException;
	
	Autorizacao configurarGuiaProc(Autorizacao autorizacao) throws NegocioException;
	
	Autorizacao configurarGuiaCirurgia(Autorizacao autorizacao) throws NegocioException;
	
	List<Autorizacao> consultarRelatorioAutorizacoes(Date dataInicial, Date dataFinal);
	
	List<Autorizacao> configurarGuiaRelatorio(List<Autorizacao> lista);
}
