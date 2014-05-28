package br.com.vendaslim.controler;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Cidade;
import br.com.vendaslim.domain.repository.ICidadeRepository;
import br.com.vendaslim.util.DAOFactory;

public class CidadeControler {
	
	private ICidadeRepository cidadeRepository;
	
		public CidadeControler() {
			this.cidadeRepository = DAOFactory.criaCidadeRepository();
		}
		
		
		public Cidade buscaPorCodigo(Integer idCidade){
			return this.cidadeRepository.buscaPorCodigo(idCidade);
		}
		
		public List<Cidade> buscaPorFiltro(String filtro){
			Integer idCidade = 0;			
			try {
				idCidade = Integer.parseInt(filtro);
			} catch (NumberFormatException e) {}
			
			return this.cidadeRepository.lista(filtro, idCidade);
		}

}
