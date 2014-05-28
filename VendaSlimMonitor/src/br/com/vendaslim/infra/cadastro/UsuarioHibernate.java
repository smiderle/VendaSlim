package br.com.vendaslim.infra.cadastro;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Usuario;
import br.com.vendaslim.domain.cadastro.UsuarioAcesso;
import br.com.vendaslim.domain.repository.IUsuarioRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

public class UsuarioHibernate extends RepositoryHibernate implements IUsuarioRepository{
	
	@Override
	public void save(Usuario usuario) {		
		session.save(usuario);		
	}
	
	@Override
	public Usuario findByLogin(String login) {
		String hql = "select u from Usuario u where u.login = :login";
		Query query = session.createQuery(hql);
		query.setString("login", login);
		return (Usuario) query.uniqueResult();
	}
	
	public void getMaxId(){
	}

	@Override
	public List<Usuario> list(Empresa empresa) {
		return session.createCriteria(Usuario.class).add(Restrictions.eq("filial.empresa", empresa)).list();
		
	}

	@Override
	public void registraAcesso(UsuarioAcesso usuarioAcesso) {
		this.session.save(usuarioAcesso);
	}
}
