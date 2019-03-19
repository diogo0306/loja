package br.com.eclinic.service.cobrancaAvulsa;

import java.util.List;

import br.com.eclinic.entity.paciente.CobrancaAvulsa;
import br.com.eclinic.exception.NegocioException;

public interface CobrancaAvulsaService {
	
	void salvar (CobrancaAvulsa cobrancaAvulsa);
	
	CobrancaAvulsa buscar (Long pk);
	
	void alterar (CobrancaAvulsa cobrancaAvulsa);
	
	void excluir(CobrancaAvulsa cobrancaAvulsa) throws NegocioException;
	
	List<CobrancaAvulsa> listar();
	
	List<CobrancaAvulsa> listarPorPaciente(Long id);

}
