package br.com.eclinic.hibernate.medicamento;

import java.util.List;

import br.com.eclinic.entity.receita.Medicamento;

public interface MedicamentoRepository {
	
	void salvar(Medicamento medicamento);

	Medicamento buscar(Long pk);

	void alterar(Medicamento medicamento);

	List<Medicamento> listar();

	void excluir(Medicamento medicamento);

	List<Medicamento> listarOrder(String atributoOrder);
	
}
