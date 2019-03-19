package br.com.eclinic.hibernate.mensagem;

import java.util.List;
import br.com.eclinic.entity.parametrizacao.Mensagem;

public interface MensagemRepository {
	
	void salvar(Mensagem mensagem);

	Mensagem buscar(Long pk);

	void alterar(Mensagem mensagem);

	List<Mensagem> listar();

	void excluir(Mensagem mensagem);

	List<Mensagem> listarOrder(String atributoOrder);
	
}
