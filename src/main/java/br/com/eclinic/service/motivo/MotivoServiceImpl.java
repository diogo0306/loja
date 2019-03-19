package br.com.eclinic.service.motivo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.motivo.Motivo;
import br.com.eclinic.hibernate.motivo.MotivoRepository;

@Service(value = "motivoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MotivoServiceImpl implements MotivoService{

	@Autowired
	private MotivoRepository motivoRepository;
	
	@Override
	public void salvar(Motivo motivo) {
		// TODO Auto-generated method stub
		this.motivoRepository.salvar(motivo);
	}

	@Override
	public void alterar(Motivo motivo) {
		// TODO Auto-generated method stub
		this.motivoRepository.alterar(motivo);
	}

	@Override
	public void excluir(Motivo motivo) {
		// TODO Auto-generated method stub
		this.motivoRepository.excluir(motivo);
	}

	@Override
	public Motivo buscar(Long pk) {
		// TODO Auto-generated method stub
		return this.motivoRepository.buscar(pk);
	}

	@Override
	public List<Motivo> listar() {
		// TODO Auto-generated method stub
		return this.motivoRepository.listar();
	}

}
