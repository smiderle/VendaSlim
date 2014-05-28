package br.com.slim.venda.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import br.com.slim.venda.analitico.EstoqueAnaliticoDAO;
import br.com.slim.venda.analitico.EstoqueAnaliticoModel;
import br.com.slim.venda.analitico.EstoqueAnaliticoVO;
import br.com.slim.venda.integration.domain.GrupoProdutoIntegration;
import br.com.slim.venda.integration.repository.GrupoProdutoIntegrationDAO;
import br.com.slim.venda.pedidoItem.PedidoItemVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.util.Util;

public class ItemModel {
	
	Context context;
	ItemDAO itemDAO;
	public ItemModel(Context context) {
		this.context = context;
		itemDAO = new ItemDAO(context);
	}
	
	//Recalcula o precço aplicando a tabela de preço
	public void aplicaTabPreco(TabelaPrecoVO tabPrecoVO, ArrayList<ItemVO> lsItemVO){
		if(tabPrecoVO != null){
			if(tabPrecoVO.getIdTabPreco() != null && tabPrecoVO.getIdTabPreco() != 0){
				for(ItemVO itemVO : lsItemVO){
					itemVO.setPrecoTabPreco(Util.aplicaPercentual(itemVO.getPreco(), 
							tabPrecoVO.getPercentual(), tabPrecoVO.isAcrescimo()));
				}
			}
		}
	}
	
	public ArrayList<ItemVO> getAll(TabelaPrecoVO tabPrecoVO){
		ArrayList<ItemVO> lsItemVO = itemDAO.getAllActive();
		if(tabPrecoVO != null)
			aplicaTabPreco(tabPrecoVO,lsItemVO);
		return lsItemVO;
	}
	
	public ArrayList<ItemVO>  getAllByPrefixo(String prefixo, TabelaPrecoVO tabPrecoVO){		
		ArrayList<ItemVO> lsItemVO = itemDAO.getAllByPrefixo(prefixo);
		aplicaTabPreco(tabPrecoVO,lsItemVO);
		return lsItemVO;
	}
	
	public ArrayList<ItemVO>  getAllByPrefixoActive(String prefixo, TabelaPrecoVO tabPrecoVO){		
		ArrayList<ItemVO> lsItemVO = itemDAO.getAllByPrefixoActive(prefixo);
		aplicaTabPreco(tabPrecoVO,lsItemVO);
		return lsItemVO;
	}
	
	public ArrayList<ItemVO> getAllByGrupo(Integer idGrupo, TabelaPrecoVO tabPrecoVO){
		ArrayList<ItemVO> lsItemVO  = itemDAO.getAllByIdItemGrupo(idGrupo);
		aplicaTabPreco(tabPrecoVO,lsItemVO);
		return lsItemVO;
	}
	
	public Integer getNextId(){
		return itemDAO.getNextId()+1;
	}
	
	public void diminuiEstoque(PedidoItemVO pedidoItemVO){
		double estoqueAtual = pedidoItemVO.getItemVO().getEstoque();
		pedidoItemVO.getItemVO().setEstoque(estoqueAtual - pedidoItemVO.getQuantidade());
		itemDAO.updateEstoque(pedidoItemVO.getItemVO());
		//movimentaAnalitico(pedidoItemVO, EstoqueAnaliticoVO.ANALITICO_VENDA_EFETIVADA);
		EstoqueAnaliticoModel analiticoModel = new EstoqueAnaliticoModel(context);
		analiticoModel.movimentaAnalitico(pedidoItemVO.getItemVO(), 
				EstoqueAnaliticoVO.ANALITICO_VENDA_EFETIVADA, pedidoItemVO.getQuantidade());
	}
	
	public void aumentaEstoque(ArrayList<PedidoItemVO> lsPedidoItemVO){
		itemDAO = new ItemDAO(context);
		for (PedidoItemVO pedidoItemVO : lsPedidoItemVO) {
			double estoqueAtual = pedidoItemVO.getItemVO().getEstoque();
			pedidoItemVO.getItemVO().setEstoque(estoqueAtual + pedidoItemVO.getQuantidade());
			itemDAO.updateEstoque(pedidoItemVO.getItemVO());
			//movimentaAnalitico(pedidoItemVO, EstoqueAnaliticoVO.ANALITICO_VENDA_CANCELADA);
			EstoqueAnaliticoModel analiticoModel = new EstoqueAnaliticoModel(context);
			analiticoModel.movimentaAnalitico(pedidoItemVO.getItemVO(), 
					EstoqueAnaliticoVO.ANALITICO_VENDA_CANCELADA, pedidoItemVO.getQuantidade());
		}
	}
	
	public void movimentaAnalitico(PedidoItemVO pedidoItemVO, int tipoMovimento) {
		EstoqueAnaliticoDAO analiticoDAO = EstoqueAnaliticoDAO.getInstance(context);
		EstoqueAnaliticoVO estoqueAnalitico = new EstoqueAnaliticoVO();
		estoqueAnalitico.setItemVO(pedidoItemVO.getItemVO());
		estoqueAnalitico.setQuantidade(pedidoItemVO.getQuantidade());
		estoqueAnalitico.setIdEmpresa(UsuarioVO.idEmpresa);
		estoqueAnalitico.setTipoMov(tipoMovimento);
		estoqueAnalitico.setDateMov(new Date().getTime());
		analiticoDAO.insert(estoqueAnalitico);
	}
	
	public long insert(ItemVO itemVO){
		itemDAO = new ItemDAO(context);		
		return itemDAO.insert(itemVO);		
	}
	
	public long update(ItemVO itemVO){
		itemDAO = new ItemDAO(context);
		return itemDAO.update(itemVO);
	}
	
	public long save(ItemVO itemVO, boolean isEdition){
		itemDAO = new ItemDAO(context);
		if(isEdition){
			return itemDAO.update(itemVO);
		} else {
			return itemDAO.insert(itemVO);
		}
	}
	
	public void salvar(List<ItemVO> lsItem){
		ItemDAO itemDAO = new ItemDAO(this.context);
		for (ItemVO itemVO : lsItem) {
			itemDAO.insertOrUpdate(itemVO);
		}
		itemDAO.close();
	}	
	
	public void closeConnection(){
		itemDAO.close();
	}
}
