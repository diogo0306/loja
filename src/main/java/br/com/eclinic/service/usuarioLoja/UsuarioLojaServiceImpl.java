package br.com.eclinic.service.usuarioLoja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.usuarioLoja.UsuarioLoja;
import br.com.eclinic.hibernate.usuarioLoja.UsuarioLojaRepository;

@Service(value = "usuarioLojaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UsuarioLojaServiceImpl implements UsuarioLojaService {

	@Autowired
	private UsuarioLojaRepository usuarioLojaRepository;

	@Override
	public void salvar(UsuarioLoja usuarioLoja) {
		this.usuarioLojaRepository.salvar(usuarioLoja);
	}

	@Override
	public void alterar(UsuarioLoja usuarioLoja) {
		this.usuarioLojaRepository.alterar(usuarioLoja);
	}

	@Override
	public void excluir(UsuarioLoja usuarioLoja) {
		this.usuarioLojaRepository.excluir(usuarioLoja);
	}

	@Override
	public UsuarioLoja buscar(Long pk) {
		return this.usuarioLojaRepository.buscar(pk);

	}

	@Override
	public List<UsuarioLoja> listar() {
		return this.usuarioLojaRepository.listar();

	}

	@Override
	public List<UsuarioLoja> consultar(UsuarioLoja usuarioLoja) {
		return this.usuarioLojaRepository.consultar(usuarioLoja);

	}

}
