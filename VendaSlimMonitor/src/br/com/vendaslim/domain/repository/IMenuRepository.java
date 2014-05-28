package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Menu;

public interface IMenuRepository extends Repository{	
	public List<Menu> listar();
}
