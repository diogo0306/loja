package br.com.eclinic.service.agendamento;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.agendamento.AgendamentoRepository;

@Service(value = "agendamentoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AgendamentoServiceImpl implements AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Override
	public void salvar(Agendamento agendamento) {
		agendamentoRepository.salvar(agendamento);

	}

	@Override
	public void alterar(Agendamento agendamento) {
		agendamentoRepository.alterar(agendamento);
	}

	@Override
	public void excluir(Long id) throws NegocioException {
		Agendamento agendamento = buscar(id);
		if (agendamento == null) {
			throw new NegocioException(
					"Agendamento não existe mais na base de dados");
		}
		agendamentoRepository.excluir(agendamento);
	}

	@Override
	public Agendamento buscar(Long pk) {
		return agendamentoRepository.buscar(pk);
	}

	@Override
	public List<Agendamento> listar() {
		return agendamentoRepository.listar();
	}

	public AgendamentoRepository getRepository() {
		return agendamentoRepository;
	}

	@Autowired
	public void setComponenteRepository(
			AgendamentoRepository agendamentoRepository) {
		this.agendamentoRepository = agendamentoRepository;
	}

	public static void main(String[] args) {
		Date dataIni = new Date();
		GregorianCalendar startTime = new GregorianCalendar();

		startTime.setTime(dataIni);
		System.out.println(dataIni);
		startTime.add(Calendar.MONTH, 1);
		System.out.println(startTime.getTime());
		startTime.add(Calendar.MONTH, 1);
		System.out.println(startTime.getTime());
		startTime.add(Calendar.MONTH, 1);
		System.out.println(startTime.getTime());
	}

	@Override
	public List<Agendamento> consultarAgendamentoPorFiltro(Agendamento agendamento, Cliente cliente) throws ParseException {
		return agendamentoRepository.consultaragendamentosPorFiltro(agendamento, cliente);
	}

	@Override
	public List<Agendamento> consultarAgendamentoPorData(Cliente cliente,
			GregorianCalendar dataAgendamento) {
		return agendamentoRepository.consultarAgendamentoPorData(cliente,
				dataAgendamento);
	}

	@Override
	public List<Agendamento> consultarAgendamentoPorMedico(Medico medico) {		
		return agendamentoRepository.consultarAgendamentoPorMedico(medico);
	}

	@Override
	public List<Agendamento> listarAgendamentosPendentesPorMedico(Medico medico, Cliente cliente) {
		return this.agendamentoRepository.listarAgendamentosPendentesPorMedico(medico, cliente);
	}

	@Override
	public Agendamento buscarPorConsulta(Long id) {
		return this.agendamentoRepository.buscarPorConsulta(id);
	}
}
