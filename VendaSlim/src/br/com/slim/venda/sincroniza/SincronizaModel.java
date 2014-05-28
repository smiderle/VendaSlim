package br.com.slim.venda.sincroniza;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import br.com.slim.venda.config.ConfigVO;
import br.com.slim.venda.pedido.PedidoDAO;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedidoItem.PedidoItemBO;
import br.com.slim.venda.util.GeraEmail;
import br.com.slim.venda.util.UtilContext;
import br.com.slim.venda.util.email.GMailSender;
import br.com.slim.venda.widget.Alert;

public class SincronizaModel {
	Context context;
	public SincronizaModel(Context c) {
		this.context = c;
	}
	
	public void send(PedidoVO pedidoVO) throws Exception{
		UtilContext utilContext = new UtilContext(context);
		if(utilContext.haveNetworkConnection()){
			PedidoItemBO pedidoItemBO = new PedidoItemBO(context);
			pedidoItemBO.carregaPedidoItemByPedido(pedidoVO);
			sendEmail(pedidoVO);
			setPedidoSincronizado(pedidoVO);
		} else {
			new Alert().showDialog(context, "Conexão falhou", "Parece que o você não existe uma conexão com a internet. Por favor, verifique para prosseguir.", "OK", null);
		}
	}
	
	
	public void sendEmail(PedidoVO pedidoVO) throws Exception{}
	
	
	public void setPedidoSincronizado(PedidoVO pedidoVO){
		PedidoDAO pedidoDAO = new PedidoDAO(context);
		pedidoDAO.updateSetSincronizado(pedidoVO);
	}
	
	public void salvar(SincronizaVO sincronizaVO){
		SincronizaDAO sincronizaDAO = new SincronizaDAO(context);
		
		sincronizaDAO.insertOrUpdate(sincronizaVO);
		
		sincronizaDAO.close();
	}
	
	
	public long getHoraUltimaSinc(String tabela){
		SincronizaDAO sincronizaDAO = new SincronizaDAO(context);
		long dtHrSinc = sincronizaDAO.getHoraUltimaSinc(tabela);
		sincronizaDAO.close();
		
		return dtHrSinc;
	}
}
