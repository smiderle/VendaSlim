package br.com.slim.venda.pedidoItem;

import java.util.ArrayList;

import android.content.Context;

import br.com.slim.venda.item.ItemDAO;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.pedido.PedidoVO;

public class PedidoItemBO {
	
	private Context context;
	public PedidoItemBO(Context context) {
		this.context = context;
	}
	public void carregaPedidoItemByPedido(PedidoVO pedidoVO){
		PedidoItemDAO pedidoItemDAO = new PedidoItemDAO(context);
		ArrayList<PedidoItemVO> lsPedidoItemVO  = 
				pedidoItemDAO.getAllByIdPedido(pedidoVO.getIdPedido(), pedidoVO.getIdFilial());
		for (PedidoItemVO pedidoItemVO : lsPedidoItemVO) {
			ItemDAO itemDAO = new ItemDAO(context);
			ItemVO itemVO = itemDAO.getItemByID(pedidoItemVO.getItemVO().getIdItem(), pedidoItemVO.getItemVO().getIdEmpresa());
			pedidoItemVO.setItemVO(itemVO);
		}
		pedidoVO.setPedidoItemVO(lsPedidoItemVO);
	}
}
