package br.com.vendaslim.controler;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cadastro.FilialPK;
import br.com.vendaslim.domain.repository.IFilialRepository;
import br.com.vendaslim.util.DAOFactory;
import br.com.vendaslim.web.ContextoBean;
import br.com.vendaslim.web.util.ContextoUtil;

public class FilialControler {
	
	private IFilialRepository filialDAO;
	
	public FilialControler() {
		filialDAO = DAOFactory.criaFilialDAO();
	}
	
	public List<Filial> list(Empresa empresa){
		return this.filialDAO.list(empresa);
	}
	
	public void save(Filial filial){
		this.filialDAO.save(filial);
	}
	
	public Filial buscaPorDescricao(String nomeFantasia, Empresa empresa){
		if(empresa == null)
			empresa = ContextoUtil.getContextBean().getEmpresaLogado();
		return this.filialDAO.buscaPorNome(nomeFantasia, empresa);
	}
	
	
	/**
	 * Recupera a empresa em que o usuario esta conectado e 
	 * retorna a filial com o id passado por paramentro
	 * @param filialID
	 * @return
	 */
	public Filial findByFilialId(int idFilial){
		Filial filialLogado = ContextoUtil.getContextBean().getFilialLogado();
		FilialPK filialPK = new FilialPK();
		filialPK.setEmpresa(filialLogado.getEmpresa());
		filialPK.setIdFilial(idFilial);
		return this.filialDAO.findById(filialPK);
	}
}
