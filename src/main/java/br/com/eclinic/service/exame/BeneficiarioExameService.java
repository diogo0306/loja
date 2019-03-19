package br.com.eclinic.service.exame;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import br.com.eclinic.controler.exame.BeneficiarioDTO;
import br.com.eclinic.entity.exame.BeneficiarioExame;

public interface BeneficiarioExameService {
	
	void salvar(BeneficiarioExame beneficiarioExame);

	BeneficiarioExame buscar(Long pk);

	void alterar(BeneficiarioExame beneficiarioExame);

	List<BeneficiarioExame> listar();

	void excluir(BeneficiarioExame beneficiarioExame);
	
	void configurarForm(BeneficiarioDTO beneficiarioDTO);
	
	public BigDecimal configurarTotalizadorValores (BeneficiarioDTO beneficiarioDTO);
	
	public void ajustarLista (BeneficiarioDTO beneficiarioDTO);
	
	List<BeneficiarioExame> consultar (BeneficiarioExame beneficiarioExame) throws ParseException;;
	
}
