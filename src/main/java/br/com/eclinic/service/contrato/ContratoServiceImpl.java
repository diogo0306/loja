package br.com.eclinic.service.contrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.hibernate.contrato.ContratoRepository;

@Service(value = "contratoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ContratoServiceImpl implements ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	@Override
	public void salvar(Contrato contrato) {
		this.contratoRepository.salvar(contrato);
	}

	@Override
	public void alterar(Contrato contrato) {
		this.contratoRepository.alterar(contrato);
	}

	@Override
	public void excluir(Contrato contrato) {
		this.contratoRepository.excluir(contrato);
	}

	@Override
	public Contrato buscar(Long pk) {
		return this.contratoRepository.buscar(pk);
	}

	@Override
	public List<Contrato> listar() {
		return this.contratoRepository.listar();
	}

	@Override
	public List<Contrato> consultar(Contrato contrato) {
		return this.contratoRepository.consultar(contrato);
	}

	@Override
	public List<Contrato> listarContratosAtivos(Contrato contrato) {
		return this.contratoRepository.listarContratosAtivos(contrato);
	}

	@Override
	public List<Contrato> listarContratosPorRepresentante(Long id) {
		return this.contratoRepository.listarContratosPorRepresentante(id);
	}
	public List<Contrato> listarContratosPorPaciente(Long id) {
		return this.contratoRepository.listarContratosPorPaciente(id);
	}

	@Override
	public Plano buscarPorContrato(Long pk) {
		return this.contratoRepository.buscarPorContrato(pk);
	}

	@Override
	public Contrato buscarPorBeneficiario(Long id) {
		return this.contratoRepository.buscarPorBeneficiario(id);
	}
}
