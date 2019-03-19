package br.com.eclinic.service.credenciado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.hibernate.credenciado.CredenciadoRepository;

@Service(value = "credendicadoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CredenciadoServiceImpl implements CredenciadoService {

	@Autowired
	private CredenciadoRepository credenciadoRepository;

	@Override
	public void salvar(Credenciado credenciado) {
		this.credenciadoRepository.salvar(credenciado);
	}

	@Override
	public void alterar(Credenciado credenciado) {
		this.credenciadoRepository.alterar(credenciado);
	}

	@Override
	public void excluir(Credenciado credenciado) {
		this.credenciadoRepository.excluir(credenciado);
	}

	@Override
	public Credenciado buscar(Long pk) {
		return this.credenciadoRepository.buscar(pk);
	}

	@Override
	public List<Credenciado> listar() {
		return this.credenciadoRepository.listar();
	}

	@Override
	public List<Credenciado> Consultar(Credenciado credenciado) {
		return credenciadoRepository.consultar(credenciado);
	}

	@Override
	public List<Credenciado> listarCredenciadosPorEspecialidades(Long id) {
		return this.credenciadoRepository.listarCredenciadosPorEspecialidades(id);
	}

	@Override
	public List<Especialidade> listarEspecialidadesPorCredenciado(Long id) {
		return this.credenciadoRepository.listarEspecialidadesPorCredenciado(id);
	}

	@Override
	public Credenciado consultarCpf(Credenciado credenciado) {
		return null;
	}
}
