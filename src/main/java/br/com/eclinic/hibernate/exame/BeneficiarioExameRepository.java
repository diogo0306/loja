package br.com.eclinic.hibernate.exame;

import java.text.ParseException;
import java.util.List;

import br.com.eclinic.entity.exame.BeneficiarioExame;

public interface BeneficiarioExameRepository {
	
	void salvar(BeneficiarioExame beneficiarioExame);

	BeneficiarioExame buscar(Long pk);

	void alterar(BeneficiarioExame beneficiarioExame);

	List<BeneficiarioExame> listar();

	void excluir(BeneficiarioExame beneficiarioExame);
	
	List<BeneficiarioExame> consultar (BeneficiarioExame beneficiarioExame) throws ParseException;

}
