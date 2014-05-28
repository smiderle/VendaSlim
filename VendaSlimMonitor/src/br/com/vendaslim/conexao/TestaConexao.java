package br.com.vendaslim.conexao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;

import br.com.vendaslim.domain.cadastro.Menu;
import br.com.vendaslim.domain.cadastro.Usuario;
import br.com.vendaslim.domain.repository.IMenuRepository;
import br.com.vendaslim.util.DAOFactory;

public class TestaConexao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(8);
			usuario.setNome("teste");
			usuario.setSenha("345");
			usuario.setLogin("teste");
			session.save(usuario);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		IMenuRepository menuRepository = DAOFactory.criaMenuRepository();	
		
		
		
		

		List<Menu> menusPrincipais = menuRepository.listar();

		for(Menu menuPrincipal : menusPrincipais){
			if(menuPrincipal.getFilhos().isEmpty()){
				MenuItem menuItem = new MenuItem();
				menuItem.setValue(menuPrincipal.getLabel());
				menuItem.setUrl(menuPrincipal.getUrl());
				menuItem.setIcon(menuPrincipal.getIcon());
				
			} else {
				Submenu submenu = new Submenu();
				submenu.setLabel(menuPrincipal.getLabel());
				submenu.setIcon(menuPrincipal.getIcon());

				for(Menu menuFilho : menuPrincipal.getFilhos()){
					
				}
			}
		}
	
		
		
		/*
		Session sessao =  null;
		try {
			
			sessao = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sessao.createCriteria(Menu.class);
			List<Menu> lsMenuAplicacao = criteria.list();
			
			
			for (Menu menuAplicacao : lsMenuAplicacao) {
				System.out.print(menuAplicacao.getLabel()+"*****************");
				System.out.println(menuAplicacao.getOrdem()+"*****************");
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		finally{
			sessao.close();
		}
	*/}
}
