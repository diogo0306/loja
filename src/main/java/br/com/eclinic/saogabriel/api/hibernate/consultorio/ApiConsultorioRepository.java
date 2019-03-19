package br.com.eclinic.saogabriel.api.hibernate.consultorio;

import java.util.List;
import br.com.eclinic.saogabriel.api.entity.consultorio.Consultorio;

public interface ApiConsultorioRepository {
	
	void salvar(Consultorio consultorio);
	
	Consultorio buscar(Long pk);
	
	List<Consultorio> listar();

	void excluir(Long pk);
	
	List<Consultorio> listarConsultoriosPorMedico(Long id);

}
