package br.com.eclinic.service.procedimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.procedimento.GrupoProcedimento;
import br.com.eclinic.entity.procedimento.Procedimento;
import br.com.eclinic.hibernate.procedimento.GrupoProcedimentoRepository;

@Service(value = "grupoProcedimentoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GrupoProcedimentoServiceImpl implements GrupoProcedimentoService {

	@Autowired
	private GrupoProcedimentoRepository grupoProcedimentoRepository;
	
	@Override
	public void salvar(GrupoProcedimento grupoProcedimento) {
		this.grupoProcedimentoRepository.salvar(grupoProcedimento);
	}

	@Override
	public void alterar(GrupoProcedimento grupoProcedimento) {
		this.grupoProcedimentoRepository.alterar(grupoProcedimento);
	}

	@Override
	public void excluir(GrupoProcedimento grupoProcedimento) {
		this.grupoProcedimentoRepository.excluir(grupoProcedimento);
	}

	@Override
	public GrupoProcedimento buscar(Long pk) {
		return this.grupoProcedimentoRepository.buscar(pk);
	}

	@Override
	public List<GrupoProcedimento> listar() {
		return this.grupoProcedimentoRepository.listar();
	}

	@Override
	public List<Procedimento> consultarProcedimentosPorGrupo(GrupoProcedimento grupoProcedimento) {
		return this.grupoProcedimentoRepository.consultarProcedimentosPorGrupo(grupoProcedimento);
	}

	@Override
	public List<GrupoProcedimento> consultar(GrupoProcedimento grupoProcedimento) {
		return this.grupoProcedimentoRepository.consultar(grupoProcedimento);
	}

}
