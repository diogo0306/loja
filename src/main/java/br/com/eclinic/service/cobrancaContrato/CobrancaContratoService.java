package br.com.eclinic.service.cobrancaContrato;

import java.util.List;

import br.com.eclinic.entity.contrato.CobrancaContrato;

public interface CobrancaContratoService {
	
	void salvar (CobrancaContrato cobrancaContrato);
	
	CobrancaContrato buscar (Long pk);
	
	void alterar (CobrancaContrato cobrancaContrato);
	
	void excluir (CobrancaContrato cobrancaContrato);
	
	List<CobrancaContrato> listar();

	List<CobrancaContrato> listarPorPaciente(Long id);

}
