package br.com.eclinic.hibernate.cobrancaAvulsa;

import java.util.List;

import br.com.eclinic.entity.paciente.CobrancaAvulsa;

public interface CobrancaAvulsaRepository {
	
	void salvar (CobrancaAvulsa cobrancaAvulsa);
	
	CobrancaAvulsa buscar (Long pk);
	
	void alterar (CobrancaAvulsa cobrancaAvulsa);
	
	void excluir (CobrancaAvulsa cobrancaAvulsa);
	
	List<CobrancaAvulsa> listar();
	
	List<CobrancaAvulsa> listarPorPaciente(Long id);

}
