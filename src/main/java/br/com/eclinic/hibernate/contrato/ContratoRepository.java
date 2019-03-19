package br.com.eclinic.hibernate.contrato;

import java.util.List;

import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.plano.Plano;

public interface ContratoRepository {
	
	void salvar (Contrato contrato);
	
	void alterar (Contrato contrato);
	
	void excluir (Contrato contrato);
	
	Contrato buscar (Long pk);
	
	Contrato buscarPorBeneficiario (Long id);
	
	Plano buscarPorContrato (Long pk);
	
	List<Contrato> listar();
	
	List<Contrato> consultar(Contrato contrato);
	
	List<Contrato> listarContratosAtivos(Contrato contrato);
	
	List<Contrato> listarContratosPorRepresentante(Long id);
	
	List<Contrato> listarContratosPorPaciente(Long id);

}
