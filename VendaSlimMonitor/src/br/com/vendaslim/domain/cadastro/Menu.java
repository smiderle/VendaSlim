package br.com.vendaslim.domain.cadastro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.vendaslim.domain.Domain;
//UniqueContrains garente que não irá ter dois MENU_PAI E LABEL repetidos. Semelhanete ao UNIQUE SQL;
@Entity
@Table(name="MENUAPLICACAO", uniqueConstraints={
		@UniqueConstraint(columnNames={"MENUPAI", "LABEL"}, name="UK_MENU_APLICACAO")} )
public class Menu implements Domain{
	
	@Id
	private Long idMenu;
	@Column
	private String icon;
	@Column(nullable=false)
	private String label;
	@Column(nullable=false)
	private String ordem;
	@Column
	private String url;
	@Column(nullable=true)
	private boolean separador;
		
	@ManyToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name="MENUPAI", nullable=true)
	private Menu pai;
	
	@OrderBy("ordem")
	@OneToMany(cascade =CascadeType.ALL, mappedBy="pai")
	private List<Menu> filhos = new ArrayList<Menu>();
	
	public List<Menu> getFilhos() {
		return filhos;
	}
	public void setFilhos(List<Menu> filhoes) {
		this.filhos = filhoes;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getOrdem() {
		return ordem;
	}
	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Menu getPai() {
		return pai;
	}
	public void setPai(Menu pai) {
		this.pai = pai;
	}
	public boolean isSeparador() {
		return separador;
	}
	public void setSeparador(boolean separador) {
		this.separador = separador;
	}
	public Long getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}
	
	
}
