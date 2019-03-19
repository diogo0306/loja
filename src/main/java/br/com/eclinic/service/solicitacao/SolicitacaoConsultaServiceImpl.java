package br.com.eclinic.service.solicitacao;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.solicitacao.SolicitacaoConsulta;
import br.com.eclinic.hibernate.solicitacao.SolicitacaoConsultaRepository;

@Service(value = "solicitacaoConsultaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SolicitacaoConsultaServiceImpl implements SolicitacaoConsultaService {
	
	@Autowired
	private SolicitacaoConsultaRepository solicitacaoConsultaRepository;

	@Override
	public void salvar(SolicitacaoConsulta solicitacaoConsulta) {
		this.solicitacaoConsultaRepository.salvar(solicitacaoConsulta);
	}

	@Override
	public void excluir(SolicitacaoConsulta solicitacaoConsulta) {
		this.solicitacaoConsultaRepository.excluir(solicitacaoConsulta);
	}

	@Override
	public void alterar(SolicitacaoConsulta solicitacaoConsulta) {
		this.solicitacaoConsultaRepository.alterar(solicitacaoConsulta);
	}

	@Override
	public SolicitacaoConsulta buscar(Long pk) {
		return this.solicitacaoConsultaRepository.buscar(pk);
	}

	@Override
	public List<SolicitacaoConsulta> listar() {
		return this.solicitacaoConsultaRepository.listar();
	}

	@Override
	public List<SolicitacaoConsulta> consultar(SolicitacaoConsulta solicitacaoConsulta) throws ParseException {
		return this.solicitacaoConsultaRepository.consultar(solicitacaoConsulta);
	}

	@Override
	public List<SolicitacaoConsulta> listarPorPaciente(Long id) {
		return this.solicitacaoConsultaRepository.listarPorPaciente(id);
	}
}