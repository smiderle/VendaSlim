package br.com.slim.venda.analitico;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.pedidoItem.PedidoItemVO;
import br.com.slim.venda.usuario.UsuarioVO;

public class EstoqueAnaliticoModel {

	EstoqueAnaliticoDAO estoqueAnaliticoDAO ;
	Context context;
	
	public EstoqueAnaliticoModel(Context context) {
		this.context = context;
	}
	
	
	/*public void insert(ItemVO itemVO, int tipoMov){
		estoqueAnaliticoDAO = EstoqueAnaliticoDAO.getInstance(context);
		
	}*/
	
	public ArrayList<EstoqueAnaliticoVO> getAllByItem(ItemVO itemVO){
		estoqueAnaliticoDAO = EstoqueAnaliticoDAO.getInstance(context);
		return estoqueAnaliticoDAO.getAllByItem(itemVO);
	}
	
	public ArrayList<EstoqueAnaliticoVO> getAllByItemTipo(ItemVO itemVO, int tipoMov){
		estoqueAnaliticoDAO = EstoqueAnaliticoDAO.getInstance(context);
		return estoqueAnaliticoDAO.getAllByItemType(itemVO, tipoMov);
	}
	
	public void movimentaAnalitico(ItemVO itemVO, int tipoMovimento, double quantidade) {
		EstoqueAnaliticoDAO analiticoDAO = EstoqueAnaliticoDAO.getInstance(context);
		EstoqueAnaliticoVO estoqueAnalitico = new EstoqueAnaliticoVO();
		estoqueAnalitico.setItemVO(itemVO);
		estoqueAnalitico.setQuantidade(quantidade);
		estoqueAnalitico.setIdEmpresa(UsuarioVO.idEmpresa);
		estoqueAnalitico.setTipoMov(tipoMovimento);
		estoqueAnalitico.setDateMov(new Date().getTime());
		analiticoDAO.insert(estoqueAnalitico);
	}
}
