package br.com.eclinic.hibernate.infra;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.cliente.Cliente;

@SuppressWarnings({"unchecked"})
public class SGPGenericDAO<T extends Serializable> extends HibernateDaoSupport {

	private static final String CLIENTE_ID = "cliente.id";
	private Class<T> classePersistente;

	/**
	 * Construtor, recebe a classe persistente que utilizar� o DAO.
	 * 
	 * @param classePersistente
	 *            O tipo a ser persistido.
	 */
	@Autowired
	public SGPGenericDAO(Class<T> classePersistente) {
		this.classePersistente = classePersistente;

	}

	/**
	 * Recupera da base de dados um determinado objeto pelo id.
	 * 
	 * @param id
	 *            O id do objeto para buscar
	 * @return O objeto carregado atrav�s do id
	 */
	public T buscar(Long id) {
		return classePersistente.cast(getHibernateTemplate().get(
				getClassePersistente(), id));
	}

	/**
	 * Método que cria uma crit�ria baseado na classe informada.
	 * 
	 * @param classe
	 *            classe para a crit�ria.
	 */
	protected Criteria createCriteria(Class<?> classe) {
		SessionFactory sessionFactory = getSessionFactory();
		Criteria criteria = sessionFactory.openSession().createCriteria(classe);
		return criteria;
	}

	/**
	 * Método que cria uma crit�ria baseado na classe do DAO.
	 * 
	 */
	protected Criteria createCriteria() {
		SessionFactory sessionFactory = getSessionFactory();
		Criteria criteria = sessionFactory.openSession().createCriteria(
				getClassePersistente());
		return criteria;
	}

	/**
	 * Lista todas as entidades de uma determinada classe no banco de dados.
	 * 
	 * o estado a ser usado no filtro.
	 */
	public List<T> listar() {
		return (List<T>) getHibernateTemplate().loadAll(classePersistente);
	}
	
	/**
	 * Lista todas as entidades de uma determinada classe no banco de dados.
	 * 
	 * o estado a ser usado no filtro.
	 */
	public List<T> listarPorCliente(Cliente cliente) {
		Criteria criteria = createCriteria(getClassePersistente());
		criteria.add(Restrictions.eq(CLIENTE_ID, cliente.getId()));
		return Collections.checkedList((List<T>) criteria.list(),
				getClassePersistente());
	}

	/**
	 * Salva o objeto no banco de dados.
	 * 
	 *@param obj
	 *            o objeto a ser salvo.
	 */
	@Transactional
	public void salvar(T obj) {
		getHibernateTemplate().save(obj);
	}
	

	/**
	 * Atualiza um determinado objeto na base de dados.
	 * 
	 * @param obj
	 *            A inst�ncia do objeto para atualizar
	 */
	@Transactional
	public void alterar(T obj) {
		getHibernateTemplate().update(obj);
	}

	/**
	 * Salva ou atualiza um determinado objeto na base de dados.
	 * 
	 * @param obj
	 *            A inst�ncia do objeto para atualizar
	 */
	@Transactional
	public void saveOrUpdate(T obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * Exclui um determinado objeto na base de dados.
	 * 
	 * @param obj
	 *            A inst�ncia do objeto para excluir
	 */
	@Transactional
	public void excluir(T obj) {
		getHibernateTemplate().delete(obj);
	}

	/**
	 * Obtém a classe persistente.
	 * 
	 * @param classePersistente
	 *            O tipo a ser persistido.
	 */
	public Class<T> getClassePersistente() {
		return classePersistente;
	}
}
