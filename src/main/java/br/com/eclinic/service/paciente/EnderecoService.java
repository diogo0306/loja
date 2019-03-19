package br.com.eclinic.service.paciente;

import java.util.List;

import br.com.eclinic.entity.endereco.Endereco;

public interface EnderecoService {
	
	void salvar (Endereco endereco);
	
	void alterar (Endereco endereco);
	
	Endereco buscar (Long pk);
	
	List<Endereco> listar();

}
