package br.com.slim.venda.historico;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.slim.venda.R;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.util.Util;

public class HistoricoAdapter extends BaseAdapter{

	LayoutInflater minflater;
	ArrayList<PedidoVO> lsPedidoVO;
	
	public HistoricoAdapter(Context context, ArrayList<PedidoVO> lsPedidoVO) {
		this.minflater = LayoutInflater.from(context);
		this.lsPedidoVO = lsPedidoVO;
	}
	
	@Override
	public int getCount() {
		return lsPedidoVO.size();
	}

	@Override
	public Object getItem(int position) {
		return lsPedidoVO.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		PedidoVO pedidoVO = lsPedidoVO.get(position);
		view = minflater.inflate(R.layout.historico_pedido_row, null);
		
		((TextView) view.findViewById(R.id.tvClienteNome)).setText(" "+pedidoVO.getClienteVO().getIdCliente()
				+" - "+ pedidoVO.getClienteVO().getNome());
		((TextView) view.findViewById(R.id.tvIdPedidoEd)).setText(pedidoVO.getIdPedido()+"");
		((TextView) view.findViewById(R.id.tvTotalEd)).setText(Util.arredondaDoubleToString(pedidoVO.getTotalLiquido()));
		((TextView) view.findViewById(R.id.tvDescontoEd)).setText(Util.arredondaDoubleToString(pedidoVO.getDescTotal()));
		((TextView) view.findViewById(R.id.tvDtEmissaoEd)).setText(Convert.toDateTimeStr(pedidoVO.getDtEmisao()));
		return view;
	}

}
