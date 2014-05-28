package br.com.slim.venda.titulos;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Bitmap.Config;
import br.com.slim.venda.config.ConfigVO;
import br.com.slim.venda.pedido.PedidoDAO;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedidopgto.PedidoPgtoDAO;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.util.Util;

public class TituloBO {
	
	//Somente pedidos com alguma parcela em aberto
	public ArrayList<PedidoVO> getAllTitulosPendentes(Context context, int idCliente){
		PedidoDAO pedidoDAO = new PedidoDAO(context);
		ArrayList<PedidoVO> lsPedidoVO = pedidoDAO.getAllPedidosTituloPendente(idCliente);
		PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(context);
		for (PedidoVO pedidoVO : lsPedidoVO) {
			ArrayList<PedidoPgtoVO> lsPedidoPgtoVO = 
					pedidoPgtoDAO.getAllByPedido(pedidoVO.getIdPedido(), UsuarioVO.idFilial);
			pedidoVO.setPedidosPgtoVO(lsPedidoPgtoVO);
		}
		return lsPedidoVO;		
	}
	
	//Traz todos os pedidos de determinado cliente
	public ArrayList<PedidoVO> getAllTitulosTodos(Context context, int idCliente){
		PedidoDAO pedidoDAO = new PedidoDAO(context);
		ArrayList<PedidoVO> lsPedidoVO = pedidoDAO.getAllPedidosTituloTodos(idCliente);
		PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(context);
		for (PedidoVO pedidoVO : lsPedidoVO) {
			ArrayList<PedidoPgtoVO> lsPedidoPgtoVO = 
					pedidoPgtoDAO.getAllByPedido(pedidoVO.getIdPedido(), UsuarioVO.idFilial);
			pedidoVO.setPedidosPgtoVO(lsPedidoPgtoVO);
		}
		return lsPedidoVO;		
	}
	/**
	 * Retorna o valor com aplicado o juros
	 * @param valor
	 * @param dtVencimento
	 * @return
	 */
	public double calculaJuros(double valor, long dtVencimento){
		double totalJuros = 0;
		long diferenca = new Date().getTime() - dtVencimento;
		if(diferenca > 0){
			int dias = (int) diferenca /1000/60/60/24;
			double jurosAoDia = ConfigVO.juros /30;
			double jurosPercentual = dias * jurosAoDia;
			double juroReais  = valor * jurosPercentual/100;
			totalJuros = Util.arredondaDouble(valor +juroReais);
		} else {
			totalJuros = valor;
		}
		return totalJuros;
	}

}
