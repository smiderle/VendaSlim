package br.com.vendaslim.controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.separator.Separator;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.vendaslim.domain.cadastro.Menu;
import br.com.vendaslim.domain.repository.IMenuRepository;
import br.com.vendaslim.util.DAOFactory;

@ManagedBean(name="MenuControler")
@SessionScoped
public class MenuControler {

	private static final Logger _logger = LoggerFactory.getLogger(MenuControler.class);	

	private IMenuRepository menuRepository;

	private MenuModel menuModel;

	public MenuModel getMenuModel() {		
		return menuModel;
	}

	@PostConstruct
	public void init(){
		this.menuModel = new DefaultMenuModel();
		
		try{
			menuRepository = DAOFactory.criaMenuRepository();	
			List<Menu> menusPrincipais = menuRepository.listar();
			
			for(Menu menuPrincipal:menusPrincipais){
				if(menuPrincipal.getFilhos().isEmpty()){
					if(menuPrincipal.getPai() == null){
						MenuItem menuItem = new MenuItem();
						menuItem.setValue(menuPrincipal.getLabel());
						menuItem.setUrl(menuPrincipal.getUrl());
						menuItem.setIcon(menuPrincipal.getIcon());
						this.menuModel.addMenuItem(menuItem);
					}
				}else{
					if(menuPrincipal.getPai() == null){
						Submenu submenu = new Submenu();
						submenu.setLabel(menuPrincipal.getLabel());
						submenu.setIcon(menuPrincipal.getIcon());
						for(Menu filho:menuPrincipal.getFilhos()){
							this.loadSubmenu(filho, submenu);
						}
						this.menuModel.addSubmenu(submenu);
					}
				}
			}
		}catch(Exception ex){
			_logger.error("Erro ao carregar menus", ex);
		}
		_logger.info("Carregou menus!");
	}
	
	private void loadSubmenu(Menu menu, Submenu submenu){
		if(menu.getFilhos().isEmpty()){
			if(!menu.isSeparador()){
				MenuItem menuItem = new MenuItem();
				menuItem.setValue(menu.getLabel());
				menuItem.setUrl(menu.getUrl());
				menuItem.setIcon(menu.getIcon());
				submenu.getChildren().add(menuItem);
			}else{
				Separator separator = new Separator();
				submenu.getChildren().add(separator);
			}
		}else{
			Submenu submenu2 = new Submenu();
			submenu2.setLabel(menu.getLabel());
			submenu2.setIcon(menu.getIcon());
			for(Menu filho:menu.getFilhos()){
				this.loadSubmenu(filho, submenu2);
			}
			submenu.getChildren().add(submenu2);
		}
	}
	
	
	
/*	@PostConstruct
	public void init(){
		menuRepository = DAOFactory.criaMenuAplicacaoRepository();	
		this.menuModel = new DefaultMenuModel();		
		//http://www.guj.com.br/java/232507-primefaces-menubar-nao-renderiza-certo-apos-submit
		//http://uaihebert.com/?p=1596 Artigo JSF Muito interessante


		try {
			List<Menu> menusPrincipais = menuRepository.listar();

			for(Menu menuPrincipal : menusPrincipais){
				if(menuPrincipal.getFilhos().isEmpty()){
					MenuItem menuItem = new MenuItem();
					menuItem.setValue(menuPrincipal.getLabel());
					menuItem.setUrl(menuPrincipal.getUrl());
					menuItem.setIcon(menuPrincipal.getIcon());
					this.menuModel.addMenuItem(menuItem);
				} else {
					Submenu submenu = new Submenu();
					submenu.setLabel(menuPrincipal.getLabel());
					submenu.setIcon(menuPrincipal.getIcon());

					for(Menu menuFilho : menuPrincipal.getFilhos()){
						loadSubmenu(menuFilho, submenu);
					}
				}
			}
		} catch(Exception ex){
			_logger.error("Erro ao carregar menus", ex);
		}
		_logger.info("Carregou menus!");
	}

	private void loadSubmenu(Menu menu,Submenu submenu){
		if(menu.getFilhos().isEmpty()){
			if(menu.isSeparador()){
				MenuItem menuItem = new MenuItem();
				menuItem.setValue(menu.getLabel());
				menuItem.setUrl(menu.getUrl());
				menuItem.setIcon(menu.getIcon());
				submenu.getChildren().add(menuItem);
			} else {
				Separator separator = new Separator();
				submenu.getChildren().add(separator);

			}
		} else {
			Submenu submenu2 = new Submenu();
			submenu2.setLabel(menu.getLabel());
			submenu2.setIcon(menu.getIcon());
			for(Menu filho:menu.getFilhos()){
				this.loadSubmenu(filho, submenu2);
			}
			submenu.getChildren().add(submenu2);
		}
	}*/
}
