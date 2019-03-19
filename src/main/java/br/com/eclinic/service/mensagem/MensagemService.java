package br.com.eclinic.service.mensagem;

import java.util.List;
import br.com.eclinic.entity.parametrizacao.Mensagem;
import br.com.eclinic.exception.NegocioException;

public interface MensagemService {
	
	void salvar(Mensagem mensagem);

	void alterar(Mensagem mensagem);

	List<Mensagem> listar();

	void excluir(Mensagem mensagem) throws NegocioException;

	Mensagem buscar(Long pk) throws NegocioException;

}
