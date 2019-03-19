package br.com.eclinic.service.cobrancaAvulsa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.eclinic.entity.paciente.CobrancaAvulsa;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.cobrancaAvulsa.CobrancaAvulsaRepository;

@Service(value = "cobrancaAvulsaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CobrancaAvulsaServiceImpl implements CobrancaAvulsaService{

	@Autowired
	private CobrancaAvulsaRepository cobrancaAvulsaRepository;
	
	@Override
	public void salvar(CobrancaAvulsa cobrancaAvulsa) {
		// TODO Auto-generated method stub
		this.cobrancaAvulsaRepository.salvar(cobrancaAvulsa);
	}

	@Override
	public CobrancaAvulsa buscar(Long pk) {
		// TODO Auto-generated method stub
		return this.cobrancaAvulsaRepository.buscar(pk);
	}

	@Override
	public void alterar(CobrancaAvulsa cobrancaAvulsa) {
		// TODO Auto-generated method stub
		this.cobrancaAvulsaRepository.alterar(cobrancaAvulsa);
	}
	
	@Override
	public List<CobrancaAvulsa> listar() {
		// TODO Auto-generated method stub
		return this.cobrancaAvulsaRepository.listar();
	}
	
	@Override
	public List<CobrancaAvulsa> listarPorPaciente(Long id) {
		return this.cobrancaAvulsaRepository.listarPorPaciente(id);
	}

	@Override
	public void excluir(CobrancaAvulsa cobrancaAvulsa) throws NegocioException {
		// TODO Auto-generated method stub
		this.cobrancaAvulsaRepository.excluir(cobrancaAvulsa);
	}

}
