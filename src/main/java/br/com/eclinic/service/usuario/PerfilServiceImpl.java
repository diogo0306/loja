package br.com.eclinic.service.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.usuario.Funcionalidade;
import br.com.eclinic.entity.usuario.Operacao;
import br.com.eclinic.entity.usuario.Perfil;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.usuario.PerfilRepository;

@Service(value = "perfilService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PerfilServiceImpl implements PerfilService {

	private PerfilRepository perfilRepository;

	@Override
	public void salvar(Perfil perfil) {
		perfilRepository.salvar(perfil);
	}

	@Override
	public void alterar(Perfil perfil) {
		perfilRepository.alterar(perfil);
	}

	@Override
	public List<Perfil> listar() {
		return perfilRepository.listar();
	}

	@Override
	public void excluir(Perfil perfil) throws NegocioException {
		for (Funcionalidade funcionalidade : perfil.getListaFuncionalidadesUsuario()) {
			this.perfilRepository.removerFuncionalidadeOperacaoUsuario(funcionalidade.getId().toString(), perfil.getId().toString());
		}
		perfilRepository.excluir(perfil);
	}

	@Override
	public Perfil buscar(Long pk) {
		return perfilRepository.buscar(pk);
	}

	@Override
	public Perfil consultarFuncionalidadesOperacoesUsuario(Perfil perfil) {
		return perfilRepository.consultarFuncionalidadesOperacoes(perfil);
	}

	@Override
	public void removerFuncionalidadeOperacaoUsuario(Perfil perfil,
			Funcionalidade funcionalidade, Operacao operacao) {
		perfilRepository.removerFuncionalidadeOperacaoUsuario(perfil,
				funcionalidade, operacao);
	}

	@Override
	public List<Funcionalidade> recuperarFuncionalidadesPorSecao(List<Funcionalidade> listaFuncionalidadesUsuario, String secao) {
		
		List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();

		for (Funcionalidade funcionalidade : listaFuncionalidadesUsuario) {
			if (secao.equals(funcionalidade.getSecao())) {
				funcionalidades.add(funcionalidade);
			}
		}

		return funcionalidades;
	}

	@Override
	public List<Funcionalidade> listarFuncionalidades() {
		return perfilRepository.listarFuncionalidades();
	}

	@Override
	public List<Operacao> listarOperacoes() {
		return perfilRepository.listarOperacoes();
	}

	@Override
	public void salvarFuncionalidadesOperacoesUsuario(
			Funcionalidade funcionalidade, Perfil perfil) {
		perfilRepository.salvarFuncionalidadesOperacao(funcionalidade, perfil);

	}

	@Override
	public void removerFuncionalidadeOperacaoUsuario(String idFuncionalidade,
			String idPerfil) {
		perfilRepository.removerFuncionalidadeOperacaoUsuario(idFuncionalidade, idPerfil);

	}

	public PerfilRepository getPerfilRepository() {
		return perfilRepository;
	}

	@Autowired
	public void setPerfilRepository(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}

	@Override
	public List<Perfil> consultarPorDescricao(String descricao, Cliente cliente) {
		return perfilRepository.consultarPorDescricao(descricao, cliente);
	}

	@Override
	public List<Perfil> listarPorCliente(Cliente cliente) {
		return perfilRepository.listarPorCliente(cliente);
	}

}
