package br.com.eclinic.hibernate.usuarioLoja;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.eclinic.entity.produto.Produto;
import br.com.eclinic.entity.usuarioLoja.UsuarioLoja;
import br.com.eclinic.hibernate.infra.SGPGenericDAO;

@Repository(value = "usuarioLojaRepository")
public class UsuarioLojaHibernateDAO extends SGPGenericDAO<UsuarioLoja> implements UsuarioLojaRepository {

	@Autowired
	public UsuarioLojaHibernateDAO(@Qualifier("sessionFactory") SessionFactory factory) {
		super(UsuarioLoja.class);
		super.setSessionFactory(factory);
	}

	@Override
	public List<UsuarioLoja> consultar(UsuarioLoja usuarioLoja) {
		Criteria criteria = createCriteria(UsuarioLoja.class);
		if (StringUtils.isNotBlank(usuarioLoja.getNomeUsuario())) {
			criteria.add(
					Restrictions.like("nomeUsuario", usuarioLoja.getNomeUsuario(), MatchMode.ANYWHERE).ignoreCase());
		}

		if (usuarioLoja.getTipoUsuarioLojaEnum() != null) {
			criteria.add(Restrictions.eq("tipoUsuarioLojaEnum", usuarioLoja.getTipoUsuarioLojaEnum()));
		}

		criteria.addOrder(Order.asc("nomeUsuario"));
		return Collections.checkedList((List<UsuarioLoja>) criteria.list(), UsuarioLoja.class);
	}

}
