package br.com.eclinic.service.dia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.parametrizacao.Dia;
import br.com.eclinic.hibernate.dia.DiaRepository;

@Service(value = "diaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DiaServiceImpl implements DiaService {

	@Autowired
	private DiaRepository diaRepository;
	
	@Override
	public void salvar(Dia dia) {
		// TODO Auto-generated method stub
		this.diaRepository.salvar(dia);
	}

	@Override
	public void excluir(Dia dia) {
		// TODO Auto-generated method stub
		this.diaRepository.excluir(dia);
	}

	@Override
	public void alterar(Dia dia) {
		// TODO Auto-generated method stub
		this.diaRepository.alterar(dia);
	}

	@Override
	public Dia buscar(Long pk) {
		// TODO Auto-generated method stub
		return this.diaRepository.buscar(pk);
	}

	@Override
	public List<Dia> listar() {
		// TODO Auto-generated method stub
		return this.diaRepository.listar();
	}

}
