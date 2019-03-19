package br.com.eclinic.service.hospital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.hospital.Hospital;
import br.com.eclinic.hibernate.hospital.HospitalRepository;

@Service(value = "hospitalService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class HospitalServiceImpl implements HospitalService {
	
	@Autowired
	private HospitalRepository hospitalRepository;

	@Override
	public void salvar(Hospital hospital) {
		this.hospitalRepository.salvar(hospital);
	}

	@Override
	public void alterar(Hospital hospital) {
		this.hospitalRepository.alterar(hospital);
	}

	@Override
	public void excluir(Hospital hospital) {
		this.hospitalRepository.excluir(hospital);
	}

	@Override
	public Hospital buscar(Long pk) {
		return this.hospitalRepository.buscar(pk);
	}

	@Override
	public List<Hospital> listar() {
		return this.hospitalRepository.listar();
	}

	@Override
	public List<Hospital> pesquisar(Hospital hospital) {
		return this.hospitalRepository.pesquisar(hospital);
	}

}
