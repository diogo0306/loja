package br.com.eclinic.saogabriel.api.service.consultorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.saogabriel.api.entity.consultorio.Consultorio;
import br.com.eclinic.saogabriel.api.hibernate.consultorio.ApiConsultorioRepository;

@Service(value = "consultorioService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ApiConsultorioServiceImpl implements ApiConsultorioService {
	
	@Autowired
	ApiConsultorioRepository apiConsultorioRepository;

	@Override
	public void salvar(Consultorio consultorio) {
		this.apiConsultorioRepository.salvar(consultorio);
	}

	@Override
	public Consultorio buscar(Long pk) {
		return this.apiConsultorioRepository.buscar(pk);
	}

	@Override
	public List<Consultorio> listar() {
		return this.apiConsultorioRepository.listar();
	}

	@Override
	public void excluir(Long pk) {
		this.apiConsultorioRepository.excluir(pk);
	}

	@Override
	public List<Consultorio> listarConsultoriosPorMedico(Long id) {
		return this.apiConsultorioRepository.listarConsultoriosPorMedico(id);
	}
}
