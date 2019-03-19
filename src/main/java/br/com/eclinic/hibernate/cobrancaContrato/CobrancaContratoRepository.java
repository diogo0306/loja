package br.com.eclinic.hibernate.cobrancaContrato;

import java.util.List;

import br.com.eclinic.entity.contrato.CobrancaContrato;

public interface CobrancaContratoRepository {
	
	void salvar (CobrancaContrato cobrancaContrato);
	
	CobrancaContrato buscar (Long pk);
	
	void alterar (CobrancaContrato cobrancaContrato);
	
	void excluir (CobrancaContrato cobrancaContrato);
	
	List<CobrancaContrato> listar();

	List<CobrancaContrato> listarPorPaciente(Long id);

}
