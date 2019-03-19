package br.com.eclinic.service.plano;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.plano.PlanoRepository;


@Service(value = "planoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PlanoServiceImpl implements PlanoService{
	
	private static final String NOME = "nome";
	private static final String MEDICO_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS = "Médico não existe mais na base de dados";
	private PlanoRepository planoRepository;
	
	@Override
	public void salvar(Plano plano) {
		planoRepository.salvar(plano);
		
	}

	@Override
	public void alterar(Plano plano) {
		planoRepository.alterar(plano);
		
	}

	@Override
	public List<Plano> listar() {
		return planoRepository.listarOrder(NOME);
	}

	@Override
	public void excluir(Plano plano) throws NegocioException {
		Plano planoBase = buscar(new Long(plano.getId()));
		if (planoBase == null) {
			throw new NegocioException(MEDICO_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS);
		}
		planoRepository.excluir(planoBase);
		
	}

	@Override
	public Plano buscar(Long pk) throws NegocioException {
		return planoRepository.buscar(pk);
	}


	@Override
	public List<Plano> listarPorCliente(Cliente cliente) {
		return this.planoRepository.listarPorCliente(cliente);
	}

	public PlanoRepository getPlanoRepository() {
		return planoRepository;
	}

	@Autowired
	public void setPlanoRepository(PlanoRepository planoRepository) {
		this.planoRepository = planoRepository;
	}

	@Override
	public List<Plano> consultar(Plano plano, Cliente cliente) {
		return planoRepository.consultar(plano, cliente);
	}

}
