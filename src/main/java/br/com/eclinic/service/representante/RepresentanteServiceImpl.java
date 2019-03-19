package br.com.eclinic.service.representante;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.eclinic.entity.representante.Representante;
import br.com.eclinic.hibernate.representante.RepresentanteRepository;

@Service(value = "representanteService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RepresentanteServiceImpl implements RepresentanteService {
	
	@Autowired
	private RepresentanteRepository representanteRepository;

	@Override
	public void salvar(Representante representante) {
		representanteRepository.salvar(representante);
		
	}

	@Override
	public void excluir(Representante representante) {
		representanteRepository.excluir(representante);
		
	}
	@Override
	public void alterar(Representante representante) {
		representanteRepository.alterar(representante);
		
	}

	@Override
	public Representante buscar(Long pk) {
		return representanteRepository.buscar(pk);

	}

	@Override
	public List<Representante> listar() {
		return representanteRepository.listar();
	}
	
	@Override
	public List<Representante> consultar(Representante representante) {
		return representanteRepository.consultar(representante);
	}
	
	@Override
	public List<Representante> consultarPorDescricao(String descricao) {
		return representanteRepository.consultarPorDescricao(descricao);
	}
	

}
