package br.com.eclinic.service.exame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.exame.ExameRepository;


@Service(value = "exameService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ExameServiceImpl implements ExameService{
	
	private static final String EXAME_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS = "Exame não existe mais na base de dados";
	private ExameRepository exameRepository;
	
	@Override
	public void salvar(Exame exame) {
		exameRepository.salvar(exame);
		
	}

	@Override
	public void alterar(Exame exame) {
		exameRepository.alterar(exame);
		
	}

	@Override
	public List<Exame> listar() {
		return exameRepository.listar();
	}

	@Override
	public void excluir(Exame exame) throws NegocioException {
		Exame exameBase = buscar(new Long(exame.getId()));
		if (exameBase == null) {
			throw new NegocioException(EXAME_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS);
		}
		exameRepository.excluir(exameBase);
		
	}

	@Override
	public Exame buscar(Long pk) {
		return exameRepository.buscar(pk);
	}

	@Override
	public List<Exame> consultar(Exame exame) {
		return exameRepository.consultar(exame);
	}

	@Override
	public List<Exame> listarPorCliente(Cliente cliente) {
		return exameRepository.listarPorCliente(cliente);
	}
	
	public ExameRepository getExameRepository() {
		return exameRepository;
	}
	
	@Autowired
	public void setExameRepository(ExameRepository exameRepository) {
		this.exameRepository = exameRepository;
	}

	@Override
	public Exame verificarNomeExistente(Exame exame) {
		return exameRepository.verificarNomeExistente(exame);
	}
	
}
