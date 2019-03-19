package br.com.eclinic.service.medico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.medico.MedicoRepository;

@Service(value = "medicoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MedicoServiceImpl implements MedicoService{
	
	private static final String MEDICO_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS = "Médico não existe mais na base de dados";
	private MedicoRepository medicoRepository;
	
	@Override
	public void salvar(Medico medico) {
		medicoRepository.salvar(medico);
	}

	@Override
	public void alterar(Medico medico) {
		medicoRepository.alterar(medico);
	}

	@Override
	public List<Medico> listar() {
		return medicoRepository.listar();
	}

	@Override
	public void excluir(Medico medico) throws NegocioException {
		Medico medicoBase = buscar(medico.getId());
		if (medicoBase == null) {
			throw new NegocioException(MEDICO_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS);
		}
		medicoRepository.excluir(medicoBase);
	}

	@Override
	public Medico buscar(Long pk) throws NegocioException {
		return medicoRepository.buscar(pk);
	}

	@Override
	public List<Medico> listarPorCliente(Cliente cliente) {
		return this.medicoRepository.listarPorCliente(cliente);
	}

	public MedicoRepository getMedicoRepository() {
		return medicoRepository;
	}

	@Autowired
	public void setMedicoRepository(MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}

	@Override
	public List<Medico> consultar(Medico medico, Cliente cliente) {
		return medicoRepository.consultar(medico, cliente);
	}

	@Override
	public void salvarEndereco(Endereco endereco) {
		this.medicoRepository.salvarEndereco(endereco);
	}

	@Override
	public Medico consultarCpf(Medico medico) {
		return medicoRepository.consultarCpf(medico);
	}

	@Override
	public List<String> horariosDeAgendamentoDisponiveis(Medico medico, List<Agendamento> agendamentos, Date data) {
		return medicoRepository.horariosDeAgendamentoDisponiveis(medico, agendamentos, data);
	}

	@Override
	public boolean diaDisponivel(Medico medico, Date data) {
		return medicoRepository.diaDisponivel(medico, data);
	}

	@Override
	public List<Medico> listarMedicosPorEspecialidades(Long id) {
		return medicoRepository.listarMedicosPorEspecialidades(id);
	}
}
