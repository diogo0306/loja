package br.com.eclinic.hibernate.credenciado;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.especialidade.Especialidade;

public interface CredenciadoRepository {
	
	void salvar (Credenciado credenciado);
	
	void alterar (Credenciado credenciado);
	
	void excluir (Credenciado credenciado);
	
	Credenciado buscar (Long pk);
	
	Credenciado consultarCpf (Credenciado credenciado);
	
	List<Credenciado> listar();
	
	List<Credenciado> listarOrder(String atributoOrder);
	
	List<Credenciado> consultar(Credenciado credenciado);
	
	List<Especialidade> listarEspecialidadesPorCredenciado(Long id);
	
	Especialidade especialidadePorId(Session session, BigInteger idEspecialidade);
	
	List<Credenciado> listarCredenciadosPorEspecialidades(Long id);
	
	Credenciado consultarEspecialidadePorId(Session session, BigInteger idEspecialidade);
	
}
