package br.com.slim.venda.pedido;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import br.com.slim.venda.item.ItemModel;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.pedidoItem.PedidoItemBO;
import br.com.slim.venda.pedidoItem.PedidoItemDAO;
import br.com.slim.venda.pedidoItem.PedidoItemVO;
import br.com.slim.venda.pedidopgto.PedidoPgtoDAO;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.util.Util;

public class PedidoModel {
	Context context;
	
	PedidoDAO pedidoDAO;
		
	public PedidoModel(Context context) {
		this.context = context;
	}
	
	public int save(PedidoVO pedidoVO){
		if(pedidoVO.getDtEmisao() <= 0){
			return insert(pedidoVO);			
		} else {
			return update(pedidoVO);
		}
	}
	
	public int update(PedidoVO pedidoVO){
		pedidoDAO = new PedidoDAO(context);
		excluir(pedidoVO);
		return insert(pedidoVO);
	}
	
	public int insert(PedidoVO pedidoVO){
		PedidoDAO pedidoDAO = new PedidoDAO(context);
		ItemModel itemBO = new ItemModel(context);
		PedidoItemDAO pedidoItemDAO = new PedidoItemDAO(context);
		pedidoVO.setDtEmisao(new Date().getTime());
		pedidoVO.setSincronizado(false);
		
		double valorTotal = 0;
		double descontoTotal = 0;
		int sequencia = 1;
		for(PedidoItemVO pedidoItemVO : pedidoVO.getPedidoItemVO()){
			pedidoItemVO.setIdPedido(pedidoVO.getIdPedido());
			pedidoItemVO.setIdSequencia(sequencia++);
			pedidoItemDAO.insert(pedidoItemVO);
			
			descontoTotal += pedidoItemVO.getDesconto() * pedidoItemVO.getQuantidade();
			valorTotal += pedidoItemVO.getPrecoVenda() * pedidoItemVO.getQuantidade();
			
			itemBO.diminuiEstoque(pedidoItemVO);
		}		
		
		pedidoVO.setTotalLiquido(valorTotal);
		pedidoVO.setTotalBruto(valorTotal + descontoTotal);
		pedidoVO.setDescTotal(descontoTotal);
		int inserts = pedidoDAO.insert(pedidoVO);
		
		
		PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(context);
		for(PedidoPgtoVO pedidoPgtoVO : pedidoVO.getPedidosPgtoVO()){
			if(pedidoVO.getParcelamentoVO().getCarencia() == 0 && pedidoVO.getParcelamentoVO().getNroParcela() == 1){
				pedidoPgtoVO.setValorPago(pedidoPgtoVO.getValorParcela());
				pedidoPgtoVO.setParcelaPaga(PedidoPgtoVO.PAGAMENTO_TOTAL);
				pedidoPgtoVO.setDtPagamento(new Date().getTime());
			}
			pedidoPgtoVO.setIdPedido(pedidoVO.getIdPedido());
			pedidoPgtoDAO.insert(pedidoPgtoVO);
		}
		pedidoPgtoDAO.close();
		pedidoVO = new PedidoVO();
		return inserts;
	}
	
	public int excluir(PedidoVO pedidoVO){
		PedidoDAO pedidoDAO = new PedidoDAO(context);
		PedidoItemDAO pedidoItemDAO = new PedidoItemDAO(context);
		PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(context);
		ItemModel itemBO = new ItemModel(context);
		
		int deletes = 0;
		itemBO.aumentaEstoque(pedidoVO.getPedidoItemVO());
		deletes += pedidoDAO.deleteByPedido(pedidoVO);
		deletes += pedidoItemDAO.deleteByPedido(pedidoVO);
		deletes += pedidoPgtoDAO.deleteByPedido(pedidoVO);
		return deletes;
	}
	
	
	/**
	 * Carrega todas as informações do pedido;
	 * @param pedidoVO
	 */
	public void carregaPedido(PedidoVO pedidoVO){
		PedidoItemBO pedidoItemBO = new PedidoItemBO(context);
		pedidoItemBO.carregaPedidoItemByPedido(pedidoVO);		
		
	}
	
	
	public double somaTotal(PedidoVO pedidoVO){
		double total = 0.0;
		if(pedidoVO.getPedidoItemVO() != null){
			for(PedidoItemVO pedidoItemVO : pedidoVO.getPedidoItemVO()){
				total += pedidoItemVO.getPrecoVenda() * pedidoItemVO.getQuantidade();
			}
		}
		return Util.arredondaDouble(total);
	}
	
	public void geraPagamento(PedidoVO pedidoVO, ParcelamentoVO parcelamentoVO){
					
		double total = somaTotal(pedidoVO);

		double valorParcelas =1;
		if(parcelamentoVO.getNroParcela() > 1)
			valorParcelas = Util.arredondaDouble(total / parcelamentoVO.getNroParcela());
		double valorPrimeiraParcela = Util.arredondaDouble(total - (valorParcelas *  (parcelamentoVO.getNroParcela()-1))); 


		Calendar dtPrimeiraParcela = Calendar.getInstance();
		dtPrimeiraParcela.add(Calendar.DAY_OF_MONTH, parcelamentoVO.getCarencia());

		PedidoPgtoVO pedidoPgtoVO = new PedidoPgtoVO();
		pedidoPgtoVO.setParcelamentoVO(parcelamentoVO);
		pedidoPgtoVO.setDtVencimento(dtPrimeiraParcela.getTime().getTime());
		pedidoPgtoVO.setIdSequencia(1);
		pedidoPgtoVO.setIdEmpresa(UsuarioVO.idEmpresa);
		pedidoPgtoVO.setValorParcela(valorPrimeiraParcela);
		pedidoVO.getPedidosPgtoVO().clear();
		pedidoVO.getPedidosPgtoVO().add(pedidoPgtoVO);
		for(int i = 1;i< parcelamentoVO.getNroParcela();i++){
			pedidoPgtoVO = new PedidoPgtoVO();
			dtPrimeiraParcela.add(Calendar.DAY_OF_MONTH, parcelamentoVO.getDiasEntreParcela());
			pedidoPgtoVO.setDtVencimento(dtPrimeiraParcela.getTime().getTime());
			pedidoPgtoVO.setParcelamentoVO(parcelamentoVO);
			pedidoPgtoVO.setIdSequencia(i+1);
			pedidoPgtoVO.setIdEmpresa(UsuarioVO.idEmpresa);
			pedidoPgtoVO.setValorParcela(valorParcelas);					
			pedidoVO.getPedidosPgtoVO().add(pedidoPgtoVO);
		}
	}
	
}
