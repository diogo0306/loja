package br.com.eclinic.service.cobrancaContrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.contrato.CobrancaContrato;
import br.com.eclinic.hibernate.cobrancaContrato.CobrancaContratoRepository;

@Service(value = "cobrancaContratoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CobrancaContratoServiceImpl implements CobrancaContratoService {

	@Autowired
	private CobrancaContratoRepository cobrancaContratoRepository;
	
	@Override
	public void salvar(CobrancaContrato cobrancaContrato) {
		// TODO Auto-generated method stub
		this.cobrancaContratoRepository.salvar(cobrancaContrato);
	}

	@Override
	public CobrancaContrato buscar(Long pk) {
		// TODO Auto-generated method stub
		return this.cobrancaContratoRepository.buscar(pk);
	}

	@Override
	public void alterar(CobrancaContrato cobrancaContrato) {
		// TODO Auto-generated method stub
		this.cobrancaContratoRepository.alterar(cobrancaContrato);
	}

	@Override
	public void excluir(CobrancaContrato cobrancaContrato) {
		// TODO Auto-generated method stub
		this.cobrancaContratoRepository.excluir(cobrancaContrato);
	}

	@Override
	public List<CobrancaContrato> listar() {
		// TODO Auto-generated method stub
		return this.cobrancaContratoRepository.listar();
	}
	
	@Override
	public List<CobrancaContrato> listarPorPaciente(Long id) {
		return this.cobrancaContratoRepository.listarPorPaciente(id);
	}

}
