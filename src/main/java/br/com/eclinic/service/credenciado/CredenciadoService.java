package br.com.eclinic.service.credenciado;

import java.util.List;

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.especialidade.Especialidade;

public interface CredenciadoService {
	
	void salvar (Credenciado credenciado);
	
	void alterar (Credenciado credenciado);
	
	void excluir (Credenciado credenciado);
	
	Credenciado buscar (Long pk);
	
	Credenciado consultarCpf (Credenciado credenciado);
	
	List<Credenciado> listar();
	
	List<Credenciado> Consultar(Credenciado credenciado);
	
	List<Credenciado> listarCredenciadosPorEspecialidades(Long id);
	
	List<Especialidade> listarEspecialidadesPorCredenciado(Long id);

}
