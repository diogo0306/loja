package br.com.eclinic.service.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.empresa.Empresa;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.empresa.EmpresaRepository;

@Service(value = "empresaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EmpresaServiceImpl implements EmpresaService{

	private static final String NOME = "nome";
	private static final String EMPRESA_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS = "Empresa não existe ais na base de dados";
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	@Override
	public void salvar(Empresa empresa) {
		empresaRepository.salvar(empresa);
	}
	@Override
	public void alterar(Empresa empresa) {
		empresaRepository.alterar(empresa);		
	}
	@Override
	public List<Empresa> listar() {
		return empresaRepository.listarOrder(NOME);
	}
	
	@Override
	public void excluir(Empresa empresa) throws NegocioException {
		Empresa empresas = buscar(new Long(empresa.getId()));
		if (empresas == null) {
			throw new NegocioException(EMPRESA_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS);
		}
		empresaRepository.excluir(empresas);
		
	}
	@Override
	public Empresa buscar(Long pk) throws NegocioException {
		return empresaRepository.buscar(pk);
	}
	
	@Override
	public List<Empresa> consultar(Empresa Empresa) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Empresa> consultarPorDescricao(String descricao) {
		return empresaRepository.consultarPorDescricao(descricao);
	}
	
	
}
